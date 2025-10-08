package com.cibertec.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"model_color_id", "size_id", "width_id"})
})
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private ModelColor modelColor;
    @ManyToOne(optional = false)
    private Size size;
    @ManyToOne(optional = false)
    private Width width;
    @Column(nullable = false, length = 50, unique = true)
    private String skuCode;
    @Column(length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'ACTIVE'")
    private String status; // e.g., "ACTIVE", "INACTIVE"
}
