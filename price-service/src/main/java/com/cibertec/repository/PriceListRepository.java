package com.cibertec.repository;

import com.cibertec.model.PriceList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListRepository extends JpaRepository<PriceList, Long> {
    Page<PriceList> findByNameContaining(String name, Pageable pageable);
}
