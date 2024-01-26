package com.product;

import com.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  private final Product product = new Product(1, "CocaCola", 100);
  @Autowired
  StockFeignClient stockFeignClient;

  @RequestMapping("/product/{id}")
  public String getProduct(@PathVariable int id) {
    return product + " With StockInfo" + stockFeignClient.getStock(id);
  }
  @RequestMapping("/")
  public String home() {
    return "Welcome to Product";
  }

  @FeignClient("stock-service")
  @RibbonClient(name="stock-service")
  interface StockFeignClient {
    @RequestMapping("/stock/product/{id}")
    String getStock(@PathVariable("id") int id);
  }
}