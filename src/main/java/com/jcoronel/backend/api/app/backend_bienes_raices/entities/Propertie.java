package com.jcoronel.backend.api.app.backend_bienes_raices.entities;

import java.time.LocalDate;

import com.jcoronel.backend.api.app.backend_bienes_raices.validation.IsString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Table(name = "propiedades")
@Entity
public class Propertie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @IsString
    @NotBlank
    @Size(min = 5 , max = 45)
    @Column(name = "titulo")
    private String title;

    @NotNull
    @Column(name = "precio")
    private Double price;

    //@IsString
    @NotBlank
    @Column(name = "imagen")
    private String image;

    @IsString
    @NotBlank
    @Column(name = "descripcion")
    private String description;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(name = "habitaciones")
    private Integer rooms;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(name = "bano")
    private Integer bathrooms;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(name = "estacionamiento")
    private Integer parking;
   
    @Column(name = "creado")
    private LocalDate date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Vendor vendor;

    public Propertie(){}

    public Propertie(String title, Double price, String image, String description, Integer rooms,
            Integer bathrooms, Integer parking, LocalDate date, Vendor vendor) {
        this.title = title;
        this.price = price;
        this.image = image;
        this.description = description;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.parking = parking;
        this.date = date;
        this.vendor = vendor;
    }

    public void prePersist() {
        date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getRooms() {
        return rooms;
    }
    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }
    public Integer getBathrooms() {
        return bathrooms;
    }
    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }
    public Integer getParking() {
        return parking;
    }
    public void setParking(Integer parking) {
        this.parking = parking;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Vendor getVendor() {
        return vendor;
    }
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
