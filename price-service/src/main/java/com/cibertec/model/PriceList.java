package com.cibertec.model;



import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="price_list")
public class PriceList {
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

		@Column(nullable = false, unique = true)
	    private String name;

	    @Column(name = "currency_code", length = 3 , columnDefinition="CHAR(3)", nullable = false)
	    private String currencyCode;

	    @Column(name = "valid_from")
	    private LocalDate validFrom;

	    @Column(name = "valid_to")
	    private LocalDate validTo;

	    @OneToMany(mappedBy = "priceList", cascade = CascadeType.ALL)
	    private List<Price> prices;
	
}
