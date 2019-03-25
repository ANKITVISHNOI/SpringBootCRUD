package com.avssoftsol.SpringBootCRUD.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avssoftsol.SpringBootCRUD.Model.Product;
import com.avssoftsol.SpringBootCRUD.Repository.ProductRepository;

/**
 * @author ANKIT
 *
 */

@RestController
@RequestMapping("/product")
public class ProductController 	{
	@Autowired 
	ProductRepository productRepository;

	@GetMapping("/listProduct")
	public List<Product> getProductList(){
		return productRepository.findAll();
	}

	@PostMapping("/saveProduct")
	public Product saveProduct(@Valid @RequestBody Product product){
		return productRepository.save(product);
	}

	@GetMapping("/getById/{id}")
	public Optional<Product> getProductById(@PathVariable(value = "id") Long id){
		return productRepository.findById(id);
	}

	@PutMapping("/updateProduct/{id}")
	public Product updateProduct(@PathVariable(value = "id") Long id, @Valid @RequestBody Product product){
		Product productOld = productRepository.getOne(id);
		if (productOld != null) {
			productOld.setProductName(product.getProductName());
			productOld.setProductPrice(product.getProductPrice());
			productOld.setProductType(product.getProductType());
		}
		return productRepository.save(productOld);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value="id") Long id){
		Product product = productRepository.getOne(id);
		productRepository.delete(product);
		return ResponseEntity.ok().build();
	}

}
