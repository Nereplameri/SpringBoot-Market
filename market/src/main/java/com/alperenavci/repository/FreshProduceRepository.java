package com.alperenavci.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alperenavci.entity.FreshProduce;

@Repository
public interface FreshProduceRepository extends JpaRepository<FreshProduce, Long>{
	
	@Query("SELECT f FROM FreshProduce f WHERE f.name = :name")
	List<FreshProduce> findByNameAll(@Param("name") String name);

	@Query(value = "from FreshProduce")
	Page<FreshProduce> findAllPageable(Pageable pageable);
	
}
