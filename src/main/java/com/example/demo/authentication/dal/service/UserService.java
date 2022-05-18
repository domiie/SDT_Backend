package com.example.demo.authentication.dal.service;

import com.example.demo.authentication.dal.entity.RoleEntity;
import com.example.demo.authentication.dal.entity.UserEntity;
import com.example.demo.authentication.dal.repository.RoleRepository;
import com.example.demo.authentication.dal.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    private static UserDto mapToUserDto(UserEntity userEntity){
        UserDto userDto = new UserDto();

        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPhone(userEntity.getPhone());
        List<String> rolesNames = new LinkedList<>();
        for(RoleEntity roles: userEntity.getRoles()){
            rolesNames.add(roles.getRoleName());
        }
        userDto.setRoles(rolesNames);

        return userDto;
    }

    private Set<RoleEntity> retrieveRolesFromDto(UserDto user){
        Set<RoleEntity> roles = new HashSet<>();
            Optional<RoleEntity> foundRoleById = roleRepository.findById(user.getRoleId());
            roles.add(foundRoleById.get());
        return roles;
    }

    @Transactional
    public Long createUser(UserDto user){
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(user.getUsername());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());
        userEntity.setRoles(retrieveRolesFromDto(user));
        userEntity.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        this.userRepository.save(userEntity);

        return userEntity.getId();
    }

    @Transactional
    public List<UserDto> getUsers(String role){
        List<UserDto> users = new LinkedList<>();
        if(role!=null){
            Optional<RoleEntity> userRole = roleRepository.findByRoleName(role);
            for (UserEntity user : userRepository.findAll()) {
                if(user.getRoles().contains(userRole.get())){
                    UserDto foundUser = mapToUserDto(user);
                    users.add(foundUser);
                }
            }
        } else {
            for(UserEntity user : userRepository.findAll()){
                UserDto foundUser = mapToUserDto(user);
                users.add(foundUser);
            }
        }
        return users;
    }

    @Transactional
    public UserDto getUserByUserId(Long userId){
        Optional<UserEntity> byId = userRepository.findById(userId);
        return byId.map(UserService::mapToUserDto).orElse(null);
    }

    @Transactional
    public void updateUser(Long userId, UserDto user){
        Optional<UserEntity> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            byId.get().setUsername(user.getUsername());
            byId.get().setFirstName(user.getFirstName());
            byId.get().setLastName(user.getLastName());
            byId.get().setEmail(user.getEmail());
            byId.get().setPhone(user.getPhone());
        }
    }

    @Transactional
    public void updatePassword(Long userId, PasswordDto data){
        Optional<UserEntity> byId = userRepository.findById(userId);
        if(byId.isPresent()){
            if(!passwordEncoder.matches(data.getCurrentPassword(), byId.get().getPasswordHash())){
                throw new UserServiceException("You entered the wrong current password");
            } else {
                byId.get().setPasswordHash(passwordEncoder.encode(data.getNewPassword()));
            }
        }
    }

    @Transactional
    public void deleteUser(Long userId){
        Optional<UserEntity> byId = userRepository.findById(userId);
        byId.ifPresent(userRepository::delete);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = findByUsername(username);

        if(userEntity == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }

        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPasswordHash(),
            mapRolesToAuthorities(userEntity.getRoles()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }
}
