package uz.dev.apelsin.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Customer customer;

    @OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY, mappedBy = "order", orphanRemoval = true)
    @JsonManagedReference
    private List<Detail> details ;

    @OneToOne(mappedBy = "order")
    @JsonBackReference
    private Invoice invoice;

}
