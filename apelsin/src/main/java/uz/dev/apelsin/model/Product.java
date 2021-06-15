package uz.dev.apelsin.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 10)
    private String name;
    @Column(name = "description", length = 20)
    private String description;
    @Column(precision = 6,scale = 2)
    private int price;
    @Lob
    @Column(name = "photo", length = 1024)
    private String photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Category category;

    @OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY, mappedBy = "product",orphanRemoval = true)
    @JsonManagedReference
    private List<Detail> details;
}
