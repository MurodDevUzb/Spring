package uz.dev.apelsin.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private Timestamp time;
    @Column(precision = 8,scale = 2)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Invoice invoice;
}
