package com.example.g3s.controller;

import com.example.g3s.model.StockInfo;
import com.example.g3s.model.StockResponse;
import com.example.g3s.service.StockService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/stock")
@Api(description = "Stock API having endpoints which are used to interact with stock microservice")
public class StockController {

    @Autowired
    private StockService stockService;

    private StockResponse stockResponse;

    @PostMapping("/addStock")
    public ResponseEntity<StockResponse> addStock(@RequestBody @Valid StockInfo stock) {
        stockResponse = stockService.addStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockResponse);
    }

    @GetMapping("/{id}")
    public StockResponse findStockById(@PathVariable String id) {
        stockResponse = stockService.findStockById(id);
        return stockResponse;
    }

    @PutMapping("/removeStock/{id}")
    public ResponseEntity<StockResponse> removeStock(@RequestBody @Valid StockInfo stock, @PathVariable String id){
        stockResponse = stockService.removeStock(stock, id);
        return ResponseEntity.status(HttpStatus.OK).body(stockResponse);
    }

    @GetMapping("/getAll")
    public StockResponse getAllStocks(){
        stockResponse = stockService.findStocks();
        return stockResponse;
    }
}