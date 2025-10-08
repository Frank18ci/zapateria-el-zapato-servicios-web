package com.cibertec.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Width {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 10, unique = true, columnDefinition = "VARCHAR(10)")
    private String code; // e.g., "N", "M", "W"
    @Column(length = 100)
    private String description;

    @OneToMany(mappedBy = "width")
    private List<ProductVariant> productVariants;
}
