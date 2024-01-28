package com.product;

import com.product.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {

  private final Product product = new Product(1, "CocaCola", 100);
  @Autowired
  StockFeignClient stockFeignClient;

  @RequestMapping("/product/{id}")
  public String getProduct(@PathVariable int id) {
    log.info("Payment received.");
    return product + " With StockInfo" + stockFeignClient.getStock(id);
  }

  @RequestMapping("/")
  public String home() {
    return "Welcome to Product";
  }

  @FeignClient("stock-service")
  @RibbonClient(name = "stock-service")
  interface StockFeignClient {

    @RequestMapping("/stock/product/{id}")
    String getStock(@PathVariable("id") int id);
  }
}
