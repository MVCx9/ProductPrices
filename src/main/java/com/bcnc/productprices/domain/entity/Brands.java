package com.bcnc.productprices.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "BRANDS")
public class Brands {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "brandId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductPrice> prices;

}
