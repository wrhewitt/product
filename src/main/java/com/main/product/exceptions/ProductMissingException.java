package com.main.product;

public class ProductMissingException extends RuntimeException {
  ProductMissingException(Long id){
      super("Product does not exist for id " + id);
  }  
}
