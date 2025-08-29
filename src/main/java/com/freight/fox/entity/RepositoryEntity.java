package com.freight.fox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "repositories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepositoryEntity {
	@Id
	private Long id;
	@Column(columnDefinition = "TEXT")
	private String name;
	@Column(columnDefinition = "TEXT")
	private String description;
	@Column(columnDefinition = "TEXT")
	private String owner;
	private String language;
	private Long stars;
	private Long forks;
	private ZonedDateTime lastUpdated;
}
