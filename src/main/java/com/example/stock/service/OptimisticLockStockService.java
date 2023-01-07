package com.example.stock.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;

@Service
public class OptimisticLockStockService {

	private final StockRepository stockRepository;

	public OptimisticLockStockService(StockRepository stockRepository){
		this.stockRepository = stockRepository;
	}

	@Transactional
	public void decrease(Long id, Long quantity){
		Stock stock = stockRepository.findByIdWithOptimisticLock(id);
		stock.decrease(quantity);

		stockRepository.saveAndFlush(stock);
	}
}
