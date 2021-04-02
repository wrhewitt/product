package com.main.product;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
class ProductController {

	ProductRepo productRepo;

	ProductController(ProductRepo ProductRepo){
		productRepo = ProductRepo;
	}

	@GetMapping("/products")
	List<Product> getProducts(){
		return productRepo.findAll();
	}

	@GetMapping("/products/{id}")
	Product getProducts(@PathVariable Long id){
		return productRepo.findById(id)
		.orElseThrow(() -> new ProductMissingException(id));
	}

	@PostMapping
	Product createProduct(Product product){
		return productRepo.save(product);
	}

	@PutMapping("/products/{id}") 
	Product upSertProduct(@RequestBody Product newProduct, @PathVariable Long id ){
		return productRepo.findById(id).map(product -> {
			product.setName(newProduct.getName());
			product.setDescription(newProduct.getDescription());
			return productRepo.save(product);
		}).orElseGet(() -> {
			newProduct.setId(id);
			return productRepo.save(newProduct);
		});
	}

	@RequestMapping("/products/{id}")
	void deleteProduct(@PathVariable Long id) {
		productRepo.deleteById(id);
	}

    @RequestMapping("/")
	String index() {
		return "Greetings from Spring Boot!";
	}
}
