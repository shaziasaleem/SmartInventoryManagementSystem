package inventory.productService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import inventory.productService.dtos.ProductDTO;
import inventory.productService.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAll();
	Optional<Product> findByName(String name);
	Optional<Product> findById(Long id);
	Product save(Product prod);
	void deleteById(Long id);
}
