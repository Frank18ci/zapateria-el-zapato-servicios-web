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
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, unique = true, nullable = false)
    private String name;
    @Column(length = 7, unique = true, nullable = false, columnDefinition = "CHAR(7)")
    private String hex;

    @OneToMany(mappedBy = "color")
    private List<ModelColor> modelColors;
}
