package com.cibertec.repository;

import com.cibertec.model.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
    Page<Color> findByNameContaining(String name, Pageable pageable);
}
