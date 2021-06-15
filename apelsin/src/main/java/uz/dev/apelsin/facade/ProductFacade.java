package uz.dev.apelsin.facade;

import org.springframework.stereotype.Component;
import uz.dev.apelsin.dto.ProductDTO;
import uz.dev.apelsin.model.Product;
@Component
public class ProductFacade {

    public ProductDTO productToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setPhoto(product.getPhoto());
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setName(product.getName());
        return productDTO;
    }

}
