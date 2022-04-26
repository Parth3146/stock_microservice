package com.example.g3s.service;

import com.example.g3s.exception.QuantityNotValidException;
import com.example.g3s.exception.StockNotFoundException;
import com.example.g3s.model.Stock;
import com.example.g3s.model.StockInfo;
import com.example.g3s.model.StockResponse;
import com.example.g3s.repository.StockRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StockService {

    @Autowired
    private StockRepo stockRepo;

    private StockResponse stockResponse;
    public StockResponse addStock(StockInfo stock) {
        List<Stock> list = new ArrayList<>();
        for (Stock s: stockRepo.findAll()){
            if (s.getAttributes().getProductId().equals(stock.getProductId())){
                Stock stock1 = new Stock(stock);
                stock1.setType(Stock.TypeEnum.STOCK);
                stock1.setId(s.getId());
                stock1.getAttributes().setQuantity(
                        s.getAttributes().getQuantity() + stock.getQuantity()
                );
                stockRepo.delete(s);
                stockRepo.save(stock1);
                list.add(s);
                stockResponse = new StockResponse(list);
                return stockResponse;
            }
        }
        Stock s = new Stock(stock);
        s.setId(getRandomNumberString());
        s.setType(Stock.TypeEnum.STOCK);
        stockRepo.save(s);
        list.add(s);
        stockResponse = new StockResponse(list);
        return stockResponse;
    }

    public StockResponse findStockByProductId(String id) {

        List<Stock> list = new ArrayList<>();
        for (Stock s: stockRepo.findAll()){
            if(s.getAttributes().getProductId().equals(id)) {
                list.add(s);
                stockResponse = new StockResponse(list);
                return stockResponse;
            }
        }
        //if (stockRepo.findById(id).isEmpty())
        throw new StockNotFoundException("No stock found with given ProductId!");
    }

    public StockResponse findStockByStockId(String id) {
        if (stockRepo.findById(id).isEmpty())
            throw new StockNotFoundException("No stock found with given stockId!");
        List<Stock> list = new ArrayList<>();
        list.add(stockRepo.findById(id).get());
        stockResponse = new StockResponse(list);
        return stockResponse;
    }

    public StockResponse removeStock(StockInfo stockInfo, String id){
        List<Stock> list = new ArrayList<>();
        boolean flag = false;
        for (Stock s: stockRepo.findAll()){
            if(s.getAttributes().getProductId().equals(id)){
                flag = true;
                Long oldQ = s.getAttributes().getQuantity();
                Long newQ = stockInfo.getQuantity();
                if (oldQ-newQ < 0) throw new QuantityNotValidException("Resultant quantity is less than zero!");
                s.getAttributes().setQuantity(oldQ-newQ);
                stockRepo.delete(s);
                stockRepo.save(s);
                list.add(s);
                stockResponse = new StockResponse(list);
                break;
            }
        }
        if (!flag)
            throw new StockNotFoundException("No stock found with given stockId!");
//        Stock s = stockRepo.findById(id).get();
//        Long oldQ = s.getAttributes().getQuantity();
//        Long newQ = stockInfo.getQuantity();
//        if (oldQ-newQ < 0) throw new QuantityNotValidException("Resultant quantity is less than zero!");
//        s.getAttributes().setQuantity(oldQ-newQ);
//        stockRepo.deleteById(id);
//        stockRepo.save(s);
//        List<Stock> list = new ArrayList<>();
//        list.add(s);
//        stockResponse = new StockResponse(list);
        return stockResponse;
    }

    public StockResponse findStocks(){
        stockResponse = new StockResponse(new ArrayList<>(stockRepo.findAll()));
        return stockResponse;
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
