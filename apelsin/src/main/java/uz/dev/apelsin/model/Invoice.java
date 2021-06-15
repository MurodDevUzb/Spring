package uz.dev.apelsin.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 8,scale = 2)
    private int amount;
    private Date issued;
    private Date due;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    @JsonBackReference
    private Order order;

    @OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY, mappedBy = "invoice", orphanRemoval = true)
    @JsonManagedReference
    private List<Payment> payments;



}
