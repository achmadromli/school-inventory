package net.guides.springboot.todomanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.guides.springboot.todomanagement.model.PerolehanBarang;

public interface PerolehanBarangRepository extends JpaRepository<PerolehanBarang, Long> {

}
