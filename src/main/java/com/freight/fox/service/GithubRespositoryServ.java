package com.freight.fox.service;

import java.util.List;

import com.freight.fox.dto.Request;
import com.freight.fox.entity.RepositoryEntity;

public interface GithubRespositoryServ {
	public List<RepositoryEntity> searchAndSaveRepositories(Request request);
	public List<RepositoryEntity> retrieveStoredResults(String language, Long starCount, String sortBy);
}
