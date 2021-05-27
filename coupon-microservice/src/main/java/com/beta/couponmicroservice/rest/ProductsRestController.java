package com.beta.couponmicroservice.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beta.couponmicroservice.model.Category;
import com.beta.couponmicroservice.model.ProductDetails;
import com.beta.couponmicroservice.model.Products;
import com.beta.couponmicroservice.services.CategoryService;
import com.beta.couponmicroservice.services.ProductServices;

@RestController
@RequestMapping("/api")
public class ProductsRestController {

	@Autowired
	private ProductServices productsService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(method = RequestMethod.GET, value = "/categories/{categoryId}/products")
	public ResponseEntity<List<Products>> getAllProducts(@PathVariable Long categoryId) {
		try {
			List<Products> products = productsService.getAllProdcuts(categoryId);
			if (products != null) {
				return ResponseEntity.status(HttpStatus.OK).body(products);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/categories/{categoryId}/products/{id}")
	public ResponseEntity<Products> getProducts(@PathVariable Long id) {
		try {
			Products product = productsService.getProduct(id);
			if (product != null) {
				return ResponseEntity.status(HttpStatus.OK).body(product);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/products")
	public ResponseEntity<Products> addProducts(@RequestBody ProductDetails productdetails) {
		try {

			Set<Long> categoryIds = productdetails.getCategories();
			Set<Category> categories = new HashSet<Category>();
			for (Long id : categoryIds) {
				categories.add(categoryService.getCategory(id));
			}
			Products product = new Products(productdetails.getName(), productdetails.getDescription(),
					productdetails.getQuantity(), productdetails.getPrice(), categories);
			product = productsService.addProduct(product);
		
			if (product != null) {
				return ResponseEntity.status(HttpStatus.OK).body(product);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

//	@RequestMapping(method = RequestMethod.PUT,value="/categories/{categoryId}/produtcs/{id}")
//	public ResponseEntity<String> updateProducts(@RequestBody Products products,@PathVariable String categoryId,@PathVariable Long id ) {
//		productsService.updateProduct(products);
//	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
	public String deleteProducts(@PathVariable Long id) {
		try {
			productsService.deleteProduct(id);
			return "Deleted";
		} catch (Exception e) {
			return "Error";
		}
	}

}
