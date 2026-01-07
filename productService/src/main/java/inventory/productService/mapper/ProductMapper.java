package inventory.productService.mapper;

import inventory.productService.dtos.ProductDTO;
import inventory.productService.model.Product;

public class ProductMapper {

	public static ProductDTO toDTO(Product product) {
		if(product == null) return null;
		ProductDTO prodDto = new ProductDTO();
		prodDto.setName(product.getName());
		prodDto.setDescription(product.getDescription());
		prodDto.setSize(product.getSize());
		prodDto.setMaterial(product.getMaterial());
		prodDto.setPackaging(product.getPackaging());
		return prodDto;
	}
	
	public static Product toEntity(ProductDTO product) {
		if(product == null) return null;
		
		Product prodDto = new Product();
		prodDto.setName(product.getName());
		prodDto.setDescription(product.getDescription());
		prodDto.setSize(product.getSize());
		prodDto.setMaterial(product.getMaterial());
		prodDto.setPackaging(product.getPackaging());
		return prodDto;
	}
}
