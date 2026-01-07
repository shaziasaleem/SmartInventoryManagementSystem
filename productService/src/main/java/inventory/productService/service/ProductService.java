package inventory.productService.service;

import java.util.List;
import java.util.Optional;

import inventory.productService.dtos.ProductDTO;
import inventory.productService.dtos.ProductRequest;
import inventory.productService.model.Product;

public interface ProductService{
	List<ProductDTO> findAll();
	Optional<ProductDTO> findByName(String name);
	Optional<ProductDTO> findById(Long id);
	ProductDTO save(ProductDTO prod);
	boolean deleteById(Long id);
	
	//ProductDTO saveProduct(ProductDTO request);
	ProductDTO saveProduct(Product request);
}