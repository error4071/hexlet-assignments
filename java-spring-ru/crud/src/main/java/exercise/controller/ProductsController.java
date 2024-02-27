package exercise.controller;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import exercise.mapper.JsonNullableMapper;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.model.Product;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Iterable<ProductDTO> index() {
        return productRepository.findAll().stream().map(x -> productMapper.map(x)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> create(@RequestBody ProductCreateDTO productData) {
        if (categoryRepository.findById(productData.getCategoryId()).isPresent()) {
            var product = productMapper.map(productData);
            productRepository.save(product);
            var result = productMapper.map(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            return ResponseEntity.badRequest().body(new ProductDTO());
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductDTO show(@PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("400 Bad request"));
        var productDto = productMapper.map(product);
        return productDto;
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductUpdateDTO productData, @PathVariable Long id) {
        if (categoryRepository.findById(productData.getCategoryId().get()).isPresent()) {

            var product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

            var category = categoryRepository.findById(productData.getCategoryId().get())
                    .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));

            product.setCategory(category);

            if (JsonNullableMapper.isPresent(productData.getPrice())) {
                product.setPrice(JsonNullableMapper.unwrap(productData.getPrice()));
            }

            if (JsonNullableMapper.isPresent(productData.getTitle())) {
                product.setTitle(JsonNullableMapper.unwrap(productData.getTitle()));
            }

            productRepository.save(product);

            return ResponseEntity.ok(productMapper.map(product));

        } else {
            return ResponseEntity.badRequest().body(new ProductDTO());
        }
    }


    @DeleteMapping(path = "/{id}")

    public void destroy(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
    // END
}
