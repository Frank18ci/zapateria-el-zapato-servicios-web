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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false, name = "password_hash")
    private String password;
    @Column(length = 50, nullable = false, unique = true)
    private String username;
    @Column(length = 100)
    private String fullName;
    @Column(length = 50, nullable = false, columnDefinition = "varchar(50) default 'ACTIVE'") // values: ACTIVE, INACTIVE, LOCKED, PENDING
    private String status;
    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT NOW()", insertable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT NOW() ON UPDATE NOW()", insertable = false)
    private LocalDateTime updatedAt;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
