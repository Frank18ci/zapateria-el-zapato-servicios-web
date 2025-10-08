package com.cibertec.repository;

import com.cibertec.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
}
