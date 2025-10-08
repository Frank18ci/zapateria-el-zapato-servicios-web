package com.cibertec.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"brand_id", "code"})
})
public class ShoeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Category category;
    @Column(length = 10, columnDefinition = "VARCHAR(10)")
    private String genderCode; // e.g., "M", "F", "U"
    @Column(length = 50, nullable = false)
    private String code;
    @Column(length = 100, nullable = false)
    private String name;
    private String description;
    @Column(columnDefinition = "SMALLINT")
    private Integer releaseYear;
    private LocalDateTime discontinuedAt;

    @OneToMany(mappedBy = "model")
    private List<ModelColor> modelColors;
}
