package com.freight.fox.dto;

import java.util.List;

import com.freight.fox.entity.RepositoryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private String message;
    private List<RepositoryEntity> repositories;
	public Response(List<RepositoryEntity> repositories) {
		super();
		this.repositories = repositories;
	}	
}