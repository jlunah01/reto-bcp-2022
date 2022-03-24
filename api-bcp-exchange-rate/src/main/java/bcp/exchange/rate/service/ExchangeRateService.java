package bcp.exchange.rate.service;

import java.math.BigDecimal;
import java.util.Optional;

import bcp.exchange.rate.dto.ExchangeRateDto;
import bcp.exchange.rate.dto.expose.ExchangeRateRs;
import bcp.exchange.rate.util.exceptions.ServiceException;


public interface ExchangeRateService {
	
	Optional<ExchangeRateRs> exchangeRate(BigDecimal amount, String originCurrency, String destinationCurrency)  throws ServiceException;
	
	ExchangeRateDto save(ExchangeRateDto exchangeRateDto, String option) throws ServiceException;
	
	Optional<ExchangeRateDto> findExchangeRate(ExchangeRateDto exchangeRateDto)  throws ServiceException;
}
