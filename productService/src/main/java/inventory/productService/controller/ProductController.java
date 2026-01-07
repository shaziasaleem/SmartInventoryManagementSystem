package inventory.productService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import inventory.productService.dtos.ProductDTO;
import inventory.productService.model.Product;
import inventory.productService.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService =productService;
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> testAuth() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    System.out.println("Auth Principal: " + auth.getPrincipal());
	    System.out.println("Authorities: " + auth.getAuthorities());
	    return ResponseEntity.ok("Token accepted: " + auth.getName());
	}
	
	/*
	 * @GetMapping("/allproducts") public ResponseEntity<List<ProductDTO>>
	 * getallproduct(){ System.out.println("in all products api");
	 * 
	 * List<ProductDTO> all =productService.findAll(); return
	 * ResponseEntity.ok(all); }
	 */
	@GetMapping("/allproducts")
	public Page<ProductDTO> getallproduct(@RequestParam int page,
										@RequestParam int size,
										@RequestParam String[] sort
			){
		System.out.println("in all products api");
		PageRequest pages = PageRequest.of(page, size);
		Sort sorting = Sort.by(sort[0]);
		if(sort.length >1 && sort[1].equalsIgnoreCase("desc")) {
			sorting = sorting.descending();
		}else {
			sorting= sorting.ascending();
		}
		List<ProductDTO> allproducts =productService.findAll();
		int start = (int)pages.getOffset();
		int end = Math.min((start+pages.getPageSize()),allproducts.size());
		Page<ProductDTO> paginatedData = new PageImpl<ProductDTO>(allproducts.subList(start, end), pages, allproducts.size());
		
		return paginatedData;
	}
	
	@GetMapping("/product/{name}")
	public ResponseEntity<Optional<ProductDTO>> getproductbyname(@PathVariable String name){
		return ResponseEntity.ok(productService.findByName(name));	
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Optional<ProductDTO>> getproductbyid(@PathVariable Long id){
		return ResponseEntity.ok(productService.findById(id));	
	}
	
	@PostMapping("/saveproduct")
	public ResponseEntity<ProductDTO> saveproduct(@RequestBody Product request){
		return ResponseEntity.ok(productService.saveProduct(request));
	}
	
	@DeleteMapping("/deleteproduct/{id}")
	
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		return ResponseEntity.ok(productService.deleteById(id));
	}
}
