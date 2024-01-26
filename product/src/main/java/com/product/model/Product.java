package com.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

  private int productNumber;
  private String name;
  private int numInStock;
}
