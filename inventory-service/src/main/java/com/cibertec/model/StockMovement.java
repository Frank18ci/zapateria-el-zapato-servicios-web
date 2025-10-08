package com.cibertec.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="stocks")
public class StockMovement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	private Long variantId;

	private String binCode; 
	
	@Column(length = 30, nullable = false)
	private String movementType; 
	
	@Column(nullable = false)
	private Integer quantity;

	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal unitCost;

	@Column(length = 3, nullable = false ,columnDefinition = "CHAR(3)")
	private String currencyCode;
	
	@Column(nullable = false ,columnDefinition="DATETIME DEFAULT NOW()")
	private LocalDateTime createdAt; 

	private String reason;

	private String refDoc;

	
	@ManyToOne
	@JoinColumn(name = "warehouse_id", nullable = false)
	private Warehouse warehouse;

 
}
