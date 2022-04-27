package com.example.demo.authentication.dal;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private static UserDto mapToUserDto(UserEntity userEntity){
        UserDto userDto = new UserDto();

        userDto.setId(userEntity.getId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPhone(userEntity.getPhone());

        return userDto;
    }

    @Transactional
    public Long createUser(UserDto user){
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());

        this.userRepository.save(userEntity);

        return userEntity.getId();
    }

    @Transactional
    public List<UserDto> getUsers(String lastname){
        List<UserDto> users = new LinkedList<>();
        for(UserEntity c1 : userRepository.findAll()){
            UserDto c2 = mapToUserDto(c1);
            users.add(c2);
        }
        return users;
    }

    @Transactional
    public UserDto getUser(Long userId){
        Optional<UserEntity> byId = userRepository.findById(userId);
        if(byId.isPresent()){
            return  mapToUserDto(byId.get());
        }
        return null;
    }

    @Transactional
    public void updateUser(Long userId, UserDto user){
        Optional<UserEntity> byId = userRepository.findById(userId);

        if (byId.isPresent()) {
            byId.get().setFirstName(user.getFirstName());
            byId.get().setLastName(user.getLastName());
            byId.get().setEmail(user.getEmail());
            byId.get().setPhone(user.getPhone());
        }

    }

    @Transactional
    public void deleteUser(Long userId){
        Optional<UserEntity> byId = userRepository.findById(userId);

        if (byId.isPresent()) {
            userRepository.delete(byId.get());
        }
    }

}
