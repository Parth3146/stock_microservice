package com.example.g3s.controller;

import com.example.g3s.model.Stock;
import com.example.g3s.model.StockInfo;
import com.example.g3s.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/addStock")
    public ResponseEntity<Stock> addStock(@RequestBody @Valid StockInfo stock) {
        Stock s = stockService.addStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }

    @GetMapping("/{id}")
    public Stock findStockById(@PathVariable String id) {
        return stockService.findStockById(id);
    }

    @PutMapping("/removeStock/{id}")
    public ResponseEntity<Stock> removeStock(@RequestBody @Valid StockInfo stock, @PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.removeStock(stock, id));
    }

    @GetMapping("/getAll")
    public List<Stock> getAllStocks(){
        return stockService.findStocks();
    }
}
