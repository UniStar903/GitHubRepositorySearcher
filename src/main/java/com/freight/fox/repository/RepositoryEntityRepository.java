package com.freight.fox.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freight.fox.entity.RepositoryEntity;

@Repository
public interface RepositoryEntityRepository extends JpaRepository<RepositoryEntity, Long>{
	List<RepositoryEntity> findByLanguageAndStarsGreaterThanEqual(String language, Long stars, Sort sort);
	List<RepositoryEntity> findByStarsGreaterThanEqual(Long stars, Sort sort);

}
