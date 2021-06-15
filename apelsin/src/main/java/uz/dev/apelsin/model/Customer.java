package uz.dev.apelsin.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 14)
    private String name;
    @Column(name = "country", length = 3)
    private String country;
    @Column(columnDefinition = "TEXT")
    private String address;
    @Column(name = "phone", length = 50)
    private String phone;

    @OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER, mappedBy = "customer", orphanRemoval = true)
    @JsonManagedReference
    private List<Order> orders;

}
