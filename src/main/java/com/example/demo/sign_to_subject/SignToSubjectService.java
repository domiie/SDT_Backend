package com.example.demo.sign_to_subject;

import com.example.demo.authentication.dal.UserEntity;
import com.example.demo.authentication.dal.UserRepository;
import com.example.demo.subject.SubjectEntity;
import com.example.demo.subject.SubjectRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SignToSubjectService {
    private SubjectRepository subjectRepository;
    private UserRepository userRepository;
    private SignToSubjectRepository signToSubjectRepository;

    private static SignToSubjectDto mapToSignToSubjectDto(SignToSubjectEntity signToSubjectEntity){
        SignToSubjectDto signToSubjectDto = new SignToSubjectDto();

        signToSubjectDto.setId(signToSubjectEntity.getId());
        signToSubjectDto.setUserId(signToSubjectEntity.getUser().getId());
        signToSubjectDto.setSubjectId(signToSubjectEntity.getSubject().getId());

        return signToSubjectDto;
    }

    public SignToSubjectService(SignToSubjectRepository signToSubjectRepository, SubjectRepository subjectRepository, UserRepository userRepository) {
        this.signToSubjectRepository = signToSubjectRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Long createSubjectSignUp(SignToSubjectDto signToSubjectDto){
        SignToSubjectEntity subjectSignUp = new SignToSubjectEntity();

        Optional<SubjectEntity> b1 = subjectRepository.findById(signToSubjectDto.getSubjectId());
        Optional<UserEntity> c1 = userRepository.findById(signToSubjectDto.getUserId());
        SubjectEntity subjectEntity = b1.get();

        if(b1.isPresent() && c1.isPresent()) {
            subjectSignUp.setSubject(subjectEntity);
            subjectSignUp.setUser(c1.get());
            this.signToSubjectRepository.save(subjectSignUp);
        }

        return subjectSignUp.getId();
    }

    @Transactional
    public SignToSubjectDto getSubjectSignUp(Long subjectSignUpId){
        Optional<SignToSubjectEntity> byId = signToSubjectRepository.findById(subjectSignUpId);
        if(byId.isPresent()){
            return  mapToSignToSubjectDto(byId.get());
        }
        return null;
    }

    @Transactional
    public List<SignToSubjectDto> getSubjectSignUps(Long subjectSignUpId) {
        List<SignToSubjectDto> signups = new LinkedList<>();
        for (SignToSubjectEntity b1 : signToSubjectRepository.findAll()) {
            SignToSubjectDto b2 = mapToSignToSubjectDto(b1);
            signups.add(b2);
        }
        return signups;
    }

    @Transactional
    public void deleteSubjectSignUp(Long subjectSignUpId){
        Optional<SignToSubjectEntity> byId = signToSubjectRepository.findById(subjectSignUpId);
        if (byId.isPresent()) {
            signToSubjectRepository.delete(byId.get());
        }
    }
}
