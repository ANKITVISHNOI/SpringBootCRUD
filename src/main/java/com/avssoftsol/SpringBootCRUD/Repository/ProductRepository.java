package com.avssoftsol.SpringBootCRUD.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avssoftsol.SpringBootCRUD.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
