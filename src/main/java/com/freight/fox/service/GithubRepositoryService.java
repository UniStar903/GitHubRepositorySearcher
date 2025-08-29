package com.freight.fox.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.freight.fox.dto.Items;
import com.freight.fox.dto.Request;
import com.freight.fox.dto.Root;
import com.freight.fox.entity.RepositoryEntity;
import com.freight.fox.repository.RepositoryEntityRepository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubRepositoryService implements GithubRespositoryServ{

    @Autowired
    RepositoryEntityRepository repository;
    
    @Autowired
    RestTemplate restTemplate;
    
    @Value("${git.hub.base.search.repository.url}")
    private String url; 
	
    public List<RepositoryEntity> searchAndSaveRepositories(Request request) {
		ResponseEntity<Root> root = restTemplate.exchange(
			//https://api.github.com/search/repositories?q=spring+boot+language:Java&s=stars
				url+"?q="+request.getQuery().replace(' ', '+')+"+language:"+request.getLanguage()+"&sort="+request.getSort()
				, HttpMethod.GET, null, Root.class);
		System.out.println(url+"?q="+request.getQuery().replace(' ', '+')+"+language:"+request.getLanguage()+"&sort="+request.getSort());
		Root rootBody = root.getBody();
		if (rootBody == null || rootBody.getItems() == null)
	        return Collections.emptyList();
		List<RepositoryEntity> RepoEntity = new ArrayList();
	    for (Items f : rootBody.getItems()) {
	        RepositoryEntity re = RepositoryEntity.builder()
	                .id(f.getId())
	                .name(f.getName())
	                .description(f.getDescription())
	                .owner(f.getOwner().getLogin())
	                .language(f.getLanguage())
	                .stars(f.getStargazersCount())
	                .forks(f.getForksCount())
	                .lastUpdated(ZonedDateTime.parse(f.getUpdatedAt()))
	                .build();
	        RepoEntity.add(repository.save(re));
	    }
	    return RepoEntity;
    }
    
    public List<RepositoryEntity> retrieveStoredResults(String language, Long starCount, String sortBy) {
    	Sort sort = switch (sortBy.toLowerCase()) {
        case "forks" -> Sort.by(Sort.Direction.DESC, "forks");
        case "updated" -> Sort.by(Sort.Direction.DESC, "lastUpdated");
        default -> Sort.by(Sort.Direction.DESC, "stars");
    };

    if (language != null && !language.isBlank())
        return repository.findByLanguageAndStarsGreaterThanEqual(language, starCount, sort);
    else
        return repository.findByStarsGreaterThanEqual(starCount, sort);
    }
}
