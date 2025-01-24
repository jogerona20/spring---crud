package com.rojo.crud.Product;

import jakarta.persistence.*;
import jdk.jfr.Name;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    private float price;

    private LocalDate fehca;

    @Transient
    private int antiguadad;

    public Product() {
    }

    public Product(Long id, String name, float price, LocalDate fehca) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.fehca = fehca;
    }

    public Product(String name, float price, LocalDate fehca) {
        this.name = name;
        this.price = price;
        this.fehca = fehca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getFehca() {
        return fehca;
    }

    public void setFehca(LocalDate fehca) {
        this.fehca = fehca;
    }

    public int getAntiguadad() {
        return Period.between(this.fehca, LocalDate.now()).getYears();
    }

    public void setAntiguadad(int antiguadad) {
        this.antiguadad = antiguadad;
    }
}
