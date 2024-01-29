package com.product;

import com.product.model.Product;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
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
  @TimeLimiter(name = "get-product-circuit-breaker", fallbackMethod = "productBreaker")
  public CompletableFuture<String> getProduct(@PathVariable int id) {
    return CompletableFuture.supplyAsync(
        () -> product + " With StockInfo" + stockFeignClient.getStock(id));
  }

  public CompletableFuture<String> productBreaker(int id, TimeoutException rnp) {
    return CompletableFuture.supplyAsync(() -> "Fall back as Stock cannot execute");
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
