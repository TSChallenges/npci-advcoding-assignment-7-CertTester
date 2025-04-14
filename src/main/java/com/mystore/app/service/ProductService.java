package com.mystore.app.service;

import com.mystore.app.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private Integer currentId = 1;

    private List<Product> products = new ArrayList<>(
            Arrays.asList(
                    new Product(currentId++, "Smartphone", "Electronics", 40000.00, 50),
                    new Product(currentId++, "Laptop", "Electronics", 50000.00, 30),
                    new Product(currentId++, "Wireless Headphones", "Electronics", 6000.00, 100),
                    new Product(currentId++, "T-shirt", "Clothing", 999.00, 200),
                    new Product(currentId++, "Jeans", "Clothing", 1499.00, 150),
                    new Product(currentId++, "Leather Jacket", "Clothing", 3000.00, 75),
                    new Product(currentId++, "Running Shoes", "Footwear", 499.00, 120),
                    new Product(currentId++, "Sneakers", "Footwear", 599.00, 200),
                    new Product(currentId++, "Office Chair", "Furniture", 2900.00, 40),
                    new Product(currentId++, "Desk", "Furniture", 4000.00, 60),
                    new Product(currentId++, "Blender", "Appliances", 200.00, 80),
                    new Product(currentId++, "Microwave Oven", "Appliances", 4999.00, 50),
                    new Product(currentId++, "Coffee Maker", "Appliances", 1399.00, 90),
                    new Product(currentId++, "Smart Watch", "Electronics", 999.00, 150),
                    new Product(currentId++, "Bluetooth Speaker", "Electronics", 2499.00, 250)
            )
    );

    public Product addProduct(Product product) {
        product.setId(currentId++);
        products.add(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProduct(Integer id) {
        return findProductById(id);
    }

    public Product updateProduct(Integer id, Product product) {
        Product p = findProductById(id);
        if (p == null) return null;
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());
        p.setStockQuantity(product.getStockQuantity());
        return p;
    }

    public String deleteProduct(Integer id) {
        Product p = findProductById(id);
        if (p == null) return "Product Not Found";
        products.remove(p);
        return "Product Deleted Successfully";
    }

    private Product findProductById(Integer id) {
        for (Product p: products) {
            if (p.getId().intValue() == id.intValue()) {
                return p;
            }
        }
        return null;
    }

    // TODO: Method to search products by name
  public List<Product> searchProductsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>(products);
        }
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }


    // TODO: Method to filter products by category
    public List<Product> filterProductsByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return new ArrayList<>(products);
        }
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // TODO: Method to filter products by price range
     public List<Product> filterProductsByPriceRange(Double minPrice, Double maxPrice) {
        if (minPrice == null && maxPrice == null) {
            return new ArrayList<>(products);
        }
        return products.stream()
                .filter(p -> (minPrice == null || p.getPrice() >= minPrice) &&
                             (maxPrice == null || p.getPrice() <= maxPrice))
                .collect(Collectors.toList());
    }

    // TODO: Method to filter products by stock quantity range
public List<Product> filterProductsByStockQuantityRange(Integer minStock, Integer maxStock) {
        if (minStock == null && maxStock == null) {
            return new ArrayList<>(products);
        }
        return products.stream()
                .filter(p -> (minStock == null || p.getStockQuantity() >= minStock) &&
                             (maxStock == null || p.getStockQuantity() <= maxStock))
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts(String name, String category, Double minPrice, Double maxPrice, Integer minStock, Integer maxStock) {
        return products.stream()
                .filter(p -> name == null || p.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(p -> category == null || p.getCategory().equalsIgnoreCase(category))
                .filter(p -> minPrice == null || p.getPrice() >= minPrice)
                .filter(p -> maxPrice == null || p.getPrice() <= maxPrice)
                .filter(p -> minStock == null || p.getStockQuantity() >= minStock)
                .filter(p -> maxStock == null || p.getStockQuantity() <= maxStock)
                .collect(Collectors.toList());
    }

    public Optional<Product> getProductById(Integer id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Product addProduct(Product product) {
        product.setId(currentId++);
        products.add(product);
        return product;
    }

    public Optional<Product> updateProduct(Integer id, Product updatedProduct) {
        Optional<Product> existingProduct = getProductById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            if (updatedProduct.getName() != null) {
                product.setName(updatedProduct.getName());
            }
            if (updatedProduct.getCategory() != null) {
                product.setCategory(updatedProduct.getCategory());
            }
            if (updatedProduct.getPrice() != null) {
                product.setPrice(updatedProduct.getPrice());
            }
            if (updatedProduct.getStockQuantity() != null) {
                product.setStockQuantity(updatedProduct.getStockQuantity());
            }
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public boolean deleteProduct(Integer id) {
        return products.removeIf(product -> product.getId().equals(id));
    }
}
