package com.main.product;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepo extends JpaRepository<Product, Long> {
    
}
