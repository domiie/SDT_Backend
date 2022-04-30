package com.example.demo.organization;
import com.example.demo.student.StudentDto;
import com.example.demo.student.StudentEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository){
        this.organizationRepository = organizationRepository;
    }

    private static OrganizationDto retrieveOrganizationDto(OrganizationEntity organizationEntity){
        OrganizationDto organizationDto = new OrganizationDto();

        organizationDto.setId(organizationEntity.getId());
        organizationDto.setTitle(organizationEntity.getTitle());
        organizationDto.setAbbreviation(organizationEntity.getAbbreviation());
        organizationDto.setAddress(organizationEntity.getAddress());

        return organizationDto;
    }

    @Transactional
    public Long createOrganization(OrganizationDto organizationDto){
        OrganizationEntity organizationEntity = new OrganizationEntity();

        organizationEntity.setTitle(organizationDto.getTitle());
        organizationEntity.setAbbreviation(organizationDto.getAbbreviation());
        organizationEntity.setAddress(organizationDto.getAddress());

        this.organizationRepository.save(organizationEntity);

        return organizationEntity.getId();

    }

    @Transactional
    public List<OrganizationDto> getOrganization(){
        List<OrganizationDto> organizations = new LinkedList<>();

        for(OrganizationEntity o1 : organizationRepository.findAll()){
            OrganizationDto o2 = retrieveOrganizationDto(o1);
            organizations.add(o2);
        }

        return organizations;
    }

}
