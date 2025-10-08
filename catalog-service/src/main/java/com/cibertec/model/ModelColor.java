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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"model_id", "color_id"})
})
public class ModelColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private ShoeModel model;
    @ManyToOne(optional = false)
    private Color color;
    @Column(length = 10, columnDefinition = "VARCHAR(10)")
    private String colorCode; // e.g., "RD", "BL", "GR"

    @OneToMany(mappedBy = "modelColor")
    private List<ProductVariant> productVariants;
}
