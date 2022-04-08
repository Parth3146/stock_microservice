package com.example.g3s.service;

import com.example.g3s.exception.QuantityNotValidException;
import com.example.g3s.exception.StockNotFoundException;
import com.example.g3s.model.Stock;
import com.example.g3s.model.StockInfo;
import com.example.g3s.repository.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepo stockRepo;

    public Stock addStock(StockInfo stock) {

        //if (stock.getQuantity() < 0) throw new QuantityNotValidException("Enter a positive value!");
        Stock s = new Stock(stock);
        s.setId(getRandomNumberString());
        s.setType(Stock.TypeEnum.STOCK);
        stockRepo.save(s);
        return s;
    }

    public Stock findStockById(String id) {

        if (stockRepo.findById(id).isEmpty()) throw new StockNotFoundException("No stock found with given stockId!");
        return stockRepo.findById(id).get();
    }

    public Stock removeStock(StockInfo stockInfo, String id){
        Stock s = stockRepo.findById(id).get();
        Long oldQ = s.getAttributes().getQuantity();
        Long newQ = stockInfo.getQuantity();
        if (oldQ-newQ < 0) throw new QuantityNotValidException("Resultant quantity is less than zero!");
        s.getAttributes().setQuantity(oldQ-newQ);
        stockRepo.deleteById(id);
        stockRepo.save(s);
        return s;
    }

    public List<Stock> findStocks(){
        return stockRepo.findAll().stream().collect(Collectors.toList());
    }

    public static String getRandomNumberString() {
        // It will generate 4 digit random Number.
        // from 0 to 9999
        Random rnd = new Random();
        int number = rnd.nextInt(9999);

        // this will convert any number sequence into 4 character.
        return String.format("%04d", number);
    }
}
