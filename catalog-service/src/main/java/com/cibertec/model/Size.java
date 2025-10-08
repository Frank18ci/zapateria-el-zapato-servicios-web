package com.cibertec.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"scale_code", "code"})
        }
)
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false)
    private String scaleCode; // e.g., "US", "EU", "UK"
    @Column(length = 10, nullable = false)
    private String code; // e.g., "8", "42", "9"
    @Column(scale = 10, precision = 2)
    private BigDecimal numericValue; // e.g., 8.0, 42.0, 9.0
    @Column(scale = 10, precision = 2)
    private BigDecimal mmLength; // e.g., 260.0, 270.0

    @OneToMany(mappedBy = "size")
    private List<ProductVariant> productVariants;
}
