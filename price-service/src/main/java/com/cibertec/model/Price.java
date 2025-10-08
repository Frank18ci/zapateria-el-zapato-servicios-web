package com.cibertec.model;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="price", uniqueConstraints={ @UniqueConstraint(columnNames = {"variant_id", "price_list_id"}) })
public class Price {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "unit_price" , precision = 12, scale = 2, nullable = false)
	    private BigDecimal unitPrice;

	    @Column(name = "variant_id", nullable = false)
	    private Long variantId; 

	    @ManyToOne
	    @JoinColumn(name = "price_list_id", nullable = false)
	    private PriceList priceList;

}
