package inventory.productService.dtos;

import lombok.Data;

@Data
public class ProductDTO {

	private String name;
	private String description;
	private String size;
	private String material;
	private String packaging;
}
