package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping(path="")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> index(@RequestParam(defaultValue = "0") int min, @RequestParam(defaultValue = "0") int max) {
        if (max == 0 && min == 0) {
            return productRepository.findAll(Sort.by(Sort.Order.asc("price")));
        }
        return productRepository.findProductsByPriceBetweenOrderByPriceAsc(min, max);
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        return productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }
}
