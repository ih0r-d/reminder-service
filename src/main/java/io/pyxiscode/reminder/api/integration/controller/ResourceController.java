package io.pyxiscode.reminder.api.integration.controller;

import io.pyxiscode.reminder.api.integration.service.EventResourceService;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
@RequestMapping("/api/v1")
public class ResourceController {

    private final EventResourceService resourceService;

    public ResourceController(EventResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PutMapping("/events/{id}/publishing")
    @ResponseBody
    public PersistentEntityResource publish(@PathVariable String id,
                                            PersistentEntityResourceAssembler assembler) {
        return resourceService.publishResource(id, assembler);
    }

    @PutMapping("/events/{id}/expiration")
    @ResponseBody
    public PersistentEntityResource expire(@PathVariable String id,
                                           PersistentEntityResourceAssembler assembler) {
        return resourceService.expireResource(id, assembler);
    }


}
