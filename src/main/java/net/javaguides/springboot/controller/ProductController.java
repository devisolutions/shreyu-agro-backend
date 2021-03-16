package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private final ProductRepository productRepository;

    public ProductController(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public Product addProducts(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{code}")
    public Product getProductByCode(@PathVariable Long code) {
        return productRepository.findById(code).orElse(null);
    }

    @DeleteMapping("/{code}")
    public void deleteProductByCode(@PathVariable Long code) {
        productRepository.deleteById(code);
    }

    @PostMapping("/{code}")
    public Product updateProduct(@PathVariable Long code, @RequestBody Product product) {
        if (productRepository.existsById(code)) {
            return productRepository.save(product);
        } else {
            throw new ResourceNotFoundException("Cannot update product, as product does not exists");
        }
    }

}
