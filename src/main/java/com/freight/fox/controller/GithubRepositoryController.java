package com.freight.fox.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import com.freight.fox.dto.Request;
import com.freight.fox.dto.Response;
import com.freight.fox.entity.RepositoryEntity;
import com.freight.fox.enums.Sort;
import com.freight.fox.service.GithubRepositoryService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GithubRepositoryController {

    private final GithubRepositoryService githubRepositoryService;

    @PostMapping("/search")
    public Response searchAndSaveInRepositories(
    		@RequestBody Request request
    ) {
    	List<RepositoryEntity> repos = githubRepositoryService.searchAndSaveRepositories(request);
        return new Response("Repositories fetched and saved successfully", repos);
    }
    
    @GetMapping("/repositories")
    public Response retrieveStoredRepositoryDetails(
    		@RequestParam(defaultValue = "") String language,
    		@RequestParam(defaultValue = "0") String starCount,
    		@RequestParam(defaultValue = "stars") Sort sort
    		) {
        return new Response(githubRepositoryService.retrieveStoredResults(language, Long.decode(starCount), String.valueOf(sort)));
    }
    
}
