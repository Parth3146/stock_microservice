package com.example.g3s.controller;

import com.example.g3s.model.StockInfo;
import com.example.g3s.model.StockResponse;
import com.example.g3s.service.StockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
    @ApiOperation("Used to create and add stock of the product")
    public ResponseEntity<StockResponse> addStock(@ApiParam("Information about the stock") @RequestBody @Valid StockInfo stock) {
        stockResponse = stockService.addStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockResponse);
    }

    @GetMapping("/p/{productId}")
    @ApiOperation("Used to find stock by given ProductId")
    public StockResponse findStockByProductId(@ApiParam("ProductId to find associated stock")@PathVariable String productId) {
        stockResponse = stockService.findStockByProductId(productId);
        return stockResponse;
    }

    @GetMapping("/{stockId}")
    @ApiOperation("Used to find stock by given StockId")
    public StockResponse findStockByStockId(@ApiParam("A unique StockId to find for a stock")@PathVariable String stockId) {
        stockResponse = stockService.findStockByStockId(stockId);
        return stockResponse;
    }

    @PutMapping("/removeStock/{productId}")
    @ApiOperation("Decrease the quantity of a product from the stock providing ProductId")
    public ResponseEntity<StockResponse> removeStock(@RequestBody @Valid StockInfo stock, @PathVariable String productId){
        stockResponse = stockService.removeStock(stock, productId);
        return ResponseEntity.status(HttpStatus.OK).body(stockResponse);
    }

    @GetMapping("/getAll")
    @ApiOperation("Used to get every available stock information")
    public StockResponse getAllStocks(){
        stockResponse = stockService.findStocks();
        return stockResponse;
    }
}