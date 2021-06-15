package uz.dev.apelsin.dto;

import lombok.Data;
import org.springframework.core.serializer.Serializer;
import uz.dev.apelsin.model.Category;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class ProductDTO {

    private Long id;

    private String name;
    private String description;
    private int price;
    private String photo;
    private String category;
}
