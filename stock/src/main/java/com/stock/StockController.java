package com.stock;

import com.stock.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

  private final Product product = new Product(1, "CocaCola", 100);

  @RequestMapping("/stock/product/{id}")
  public String getStockNumber(@PathVariable int id) throws InterruptedException {
    Thread.sleep(3000);
    return "Product id = " + id + " has " + product.getNumInStock() + " in stock";
  }
}
