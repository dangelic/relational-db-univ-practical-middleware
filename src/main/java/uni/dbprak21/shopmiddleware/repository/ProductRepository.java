package uni.dbprak21.shopmiddleware.repository;

import uni.dbprak21.shopmiddleware.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String>{

}