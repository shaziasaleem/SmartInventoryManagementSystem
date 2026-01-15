package inventory.productService.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import inventory.productService.dtos.ProductDTO;
import inventory.productService.mapper.ProductMapper;
import inventory.productService.model.Product;
import inventory.productService.repository.ProductRepository;
import inventory.productService.service.ProductService;
import inventory.productService.utils.UserKafkaConsumer;
import system.inventory.commonutilities.commonDto.LoggedInUserDTO;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	private final UserKafkaConsumer userKafkaConsumer;

	
	public ProductServiceImpl(ProductRepository productRepository,UserKafkaConsumer userKafkaConsumer) {
		this.productRepository = productRepository;
		this.userKafkaConsumer = userKafkaConsumer;
	}

	@Override
	public List<ProductDTO> findAll() {
		List<Product> allproducts= productRepository.findAll();
		return allproducts.stream().map(ProductMapper :: toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<ProductDTO> findByName(String name) {
		Optional<Product> product = productRepository.findByName(name);
		return product.map(ProductMapper::toDTO);
	}
	@Override
	public Optional<ProductDTO> findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		
		return product.map(ProductMapper::toDTO);
	}
	
	@Override
	public ProductDTO save(ProductDTO prod) {
		Product request = ProductMapper.toEntity(prod);
		Product product =productRepository.save(request);
		return ProductMapper.toDTO(product);
	}

	@Override
	public boolean deleteById(Long id) {
		try {
			productRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public ProductDTO saveProduct(Product request) {
	    LoggedInUserDTO user = userKafkaConsumer.getCurrentLoggedInUser();
	    System.out.println("user from kafka :"+user);
	    request.setCreatedByUserId(user.getId()); // Use it here
	    Product product =productRepository.save(request);
		return ProductMapper.toDTO(product);
	}

}
