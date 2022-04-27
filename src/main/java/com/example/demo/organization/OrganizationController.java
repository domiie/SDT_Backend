package com.example.demo.organization;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizationController {

    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @PostMapping("api/organization")
    public Long createOrganization(@RequestBody OrganizationDto organizationDto){
              return organizationService.createOrganization(organizationDto);
    }

    @GetMapping("api/organization")
    public List<OrganizationDto> getOrganization(){
        return organizationService.getOrganization();
    }

}
