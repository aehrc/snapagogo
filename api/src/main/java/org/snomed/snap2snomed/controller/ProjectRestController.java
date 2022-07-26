package org.snomed.snap2snomed.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.snomed.snap2snomed.controller.dto.ProjectDto;
import org.snomed.snap2snomed.problem.auth.NoSuchUserProblem;
import org.snomed.snap2snomed.problem.auth.NotAuthorisedProblem;
import org.snomed.snap2snomed.security.WebSecurity;
import org.snomed.snap2snomed.service.ProjectService;
import org.snomed.snap2snomed.service.ProjectService.ProjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class ProjectRestController {

  @Autowired private ProjectService projectService;
  @Autowired private WebSecurity webSecurity;

  @Parameter(name = "text", in = ParameterIn.QUERY, required = false, allowEmptyValue = true,
             description = "Filters the results by ensuring titles or descriptions contain the specified text.")
  @Parameter(name = "role", in = ParameterIn.QUERY, required = false, allowEmptyValue = true,
             description = "Filters the results by ensuring the user has the specified role.")
  @Parameter(name = "page", in = ParameterIn.QUERY, required = false, allowEmptyValue = false,
          description = "Zero-based page index (0..N)")
  @Parameter(name = "size", in = ParameterIn.QUERY, required = false, allowEmptyValue = false,
          description = "The size of the page to be returned")
  @Parameter(name = "sort", in = ParameterIn.QUERY, required = false, allowEmptyValue = false,
          description = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.",
          array = @ArraySchema(schema = @Schema(type = "string")))
  @GetMapping(path="/projects/fetch", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PagedModel<EntityModel<ProjectDto>>> filterProjects(
      @RequestParam(required = false) String text,
      @RequestParam(required = false) String role,
      @Parameter(hidden = true) Pageable pageable,
      @Parameter(hidden = true) PagedResourcesAssembler<ProjectDto> assembler)  {

    if (!webSecurity.isValidUser()) {
      throw new NoSuchUserProblem();
    }

    ProjectFilter filter = new ProjectFilter(text, role);

    return ResponseEntity.ok(projectService.getFilteredProjects(pageable, assembler, filter));
  }

  @ResponseStatus(value= HttpStatus.NO_CONTENT)
  @DeleteMapping(path="/projects/delete/{projectId}")
  public void deleteProject(@PathVariable Long projectId) {
    if (!webSecurity.isValidUser()) {
      throw new NoSuchUserProblem();
    }

    if (!webSecurity.isAdminUser() && !webSecurity.isProjectOwnerForId(projectId)) {
      throw new NotAuthorisedProblem("Only a project owner can delete a project");
    }

    projectService.deleteProject(projectId);

  }
}
