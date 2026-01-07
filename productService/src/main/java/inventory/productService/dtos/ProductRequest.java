package inventory.productService.dtos;

import lombok.Data;

@Data
public class ProductRequest {

	private String name;
	private String description;
	private String size;
	private String material;
	private String packaging;
}
