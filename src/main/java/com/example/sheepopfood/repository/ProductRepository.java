package com.example.sheepopfood.repository;

import com.example.sheepopfood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


        @Query(value = "SELECT p FROM Product p " +
                "JOIN Category c ON p.category.id = c.id " +
                "WHERE p.name LIKE %:name% OR c.name LIKE %:category_name%")
        List<Product> findByNameContainingOrCategory_Name(String name, String category_name);

        @Query("SELECT p FROM Product p WHERE p.name LIKE :name ORDER BY p.price ASC")
        List<Product> findByNameContainingOrderByPriceAsc(String name);

        @Query("SELECT p FROM Product p WHERE p.name LIKE :name  ORDER BY p.price DESC")
        List<Product> findByNameContainingOrderByPriceDesc(String name);


//    @Query(value = "SELECT p FROM Product p " +
//            "JOIN Category c ON p.category.id = c.id " +
//            "WHERE p.category.name LIKE %:name% ")

//    @Query("SELECT Product p FROM Product  p" +
//            "LEFT JOIN Category c ON Product.category.id= c.id " +
//
//            "WHERE c.id = :id ")
//    List<Product> findProductByTagId(Integer id);

        @Query("select p from Product  p left join Category c on p.category.id = c.id where c.id = :id")
        List<Product> findProductByCategory_Id(Integer id);

}
