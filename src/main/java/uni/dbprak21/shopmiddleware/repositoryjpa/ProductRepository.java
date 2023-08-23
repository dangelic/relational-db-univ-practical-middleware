package uni.dbprak21.shopmiddleware.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.dbprak21.shopmiddleware.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> { };
