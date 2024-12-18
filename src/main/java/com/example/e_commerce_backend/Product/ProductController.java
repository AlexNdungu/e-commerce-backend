package com.example.e_commerce_backend.Product;

import lombok.AllArgsConstructor;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    private static final String UPLOAD_DIR = "uploads";

    // Build add product api
    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("description") String description,
            @RequestParam("categoryId") String categoryId,
            @RequestParam(value = "file", required = false) MultipartFile file
    ){

        try{
            ProductDto productDto = new ProductDto();
            productDto.setName(name);
            productDto.setPrice(price);
            productDto.setDescription(description);
            productDto.setCategoryId(categoryId);

            if (file != null && !file.isEmpty()) {

                // Create a unique file name to avoid overwriting files
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path currentDirPath = Paths.get("").toAbsolutePath();
                Path uploadDirPath = currentDirPath.resolve(UPLOAD_DIR);
                Path filePath = uploadDirPath.resolve(fileName);
                Files.createDirectories(uploadDirPath);  // Creates the "Uploads" folder if it doesn't exist
                file.transferTo(filePath.toFile());

                productDto.setImagePath(fileName);
            }

            ProductDto savedProduct = productService.createProduct(productDto);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Build get all products api
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

}
