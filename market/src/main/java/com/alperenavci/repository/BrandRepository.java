package com.alperenavci.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alperenavci.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
	
	Optional<Brand> findByName(String name);
	
	@Query(value = "from Brand")
	Page<Brand> findAllPageable(Pageable pageable);
}
