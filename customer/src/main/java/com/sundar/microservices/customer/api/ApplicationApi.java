package com.sundar.microservices.customer.api;

import com.sundar.microservices.customer.api.model.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/api"}, produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class ApplicationApi {

    @Value("${project.name}")
    private String name;

    @Value("${project.version}")
    private String version;

    @GetMapping(path = {"/version"})
    public Project getVersion(){
        log.info("Checking Health point...{}", "Customer-service");

        Project project = new Project();
        project.setVersion(version);
        project.setName(name);

        return project;
    }

}
