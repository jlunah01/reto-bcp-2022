package bcp.exchange.rate.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import bcp.exchange.rate.dto.ExchangeRateDto;
import bcp.exchange.rate.dto.expose.ExchangeRateRs;
import bcp.exchange.rate.entity.ExchangeEntity;
import bcp.exchange.rate.repository.ExchangeRateRepository;
import bcp.exchange.rate.service.ExchangeRateService;
import bcp.exchange.rate.util.constants.Constants;
import bcp.exchange.rate.util.enums.CrudEnum;
import bcp.exchange.rate.util.exceptions.ServiceException;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
	
	private static Logger LOG = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);
	
	@Autowired
	private ExchangeRateRepository exchangeRateRepository;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public Optional<ExchangeRateRs> exchangeRate(BigDecimal amount, String originCurrency, String destinationCurrency) throws ServiceException{
		LOG.info("Ingreso a Servicio");
		
		String origin = StringUtils.trimToEmpty(originCurrency);
		String destiny = StringUtils.trimToEmpty(destinationCurrency);
		
		
		ExchangeRateRs response = new ExchangeRateRs();
		
		Observable<ExchangeEntity> exchangeRateObservable = Observable.create(emitter -> {
			Optional<ExchangeEntity> exchangeEntity = exchangeRateRepository.findExchangeRate(origin,destiny);
			emitter.onNext(exchangeEntity.get());
			emitter.onComplete();
		});
		
		DisposableObserver<ExchangeEntity> disposableObserver = exchangeRateObservable.subscribeWith(new  DisposableObserver<ExchangeEntity>() {

		    @Override
		    public void onNext(ExchangeEntity exchangeEntity) {

		    	response.setAmountExchangeRate(StringUtils.equals(StringUtils.trimToEmpty(((ExchangeEntity) exchangeEntity).getType()),Constants.BUY) 
						? amount.multiply(((ExchangeEntity) exchangeEntity).getExchangeRate()) 
								: amount.divide(((ExchangeEntity) exchangeEntity).getExchangeRate(),Constants.ROUND_DECIMAL,RoundingMode.CEILING)); 
				response.setAmount(amount);
				response.setDestinationCurrency(destiny);
				response.setOriginCurrency(origin);
				response.setExchangeRate(((ExchangeEntity) exchangeEntity).getExchangeRate());
				response.setMessage(Constants.SUCCESSFUL_MESSAGE);
		    	
		    }

		    @Override
		    public void onError(Throwable e) {
		    }

		    @Override
		    public void onComplete() {
		    }
		});
		
		disposableObserver.dispose();
			
		return Optional.of(response);
	}

	@Override
	public ExchangeRateDto save(ExchangeRateDto exchangeRateDto, String option) throws ServiceException {

		try {
			
			if(CrudEnum.REGISTRO.name().equalsIgnoreCase(option)) {
				exchangeRateDto.setStatus(NumberUtils.INTEGER_ONE.toString());
				exchangeRateDto.setRegistrationDate(LocalDate.now());
			}else if(CrudEnum.ACTUALIZACION.name().equalsIgnoreCase(option)) {
				exchangeRateDto.setUpdateDate(LocalDate.now());
			}
			
			ExchangeEntity exchangeEntity = this.getExchangeEntity(exchangeRateDto);
			
			ExchangeEntity responseExchangeEntity = exchangeRateRepository.save(exchangeEntity);
				
			return this.getExchangeRateDto(responseExchangeEntity);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}
	
	private ExchangeEntity getExchangeEntity(ExchangeRateDto exchangeRateDto) {
		return objectMapper.convertValue(exchangeRateDto, ExchangeEntity.class);
	}
	
	private ExchangeRateDto getExchangeRateDto(ExchangeEntity exchangeEntity) {
		return objectMapper.convertValue(exchangeEntity, ExchangeRateDto.class);
	}

	@Override
	public Optional<ExchangeRateDto> findExchangeRate(ExchangeRateDto exchangeRateDto) throws ServiceException {
		
		ExchangeRateDto responseExchangeRateDto = new ExchangeRateDto();
		
		Observable<ExchangeEntity> exchangeRateObservable = Observable.create(emitter -> {
			Optional<ExchangeEntity> exchangeEntity = exchangeRateRepository.findExchangeRate(exchangeRateDto.getExchangeRateCurrency(),exchangeRateDto.getType(),exchangeRateDto.getOriginCurrency(),exchangeRateDto.getDestinationCurrency());
			emitter.onNext(exchangeEntity.get());
			emitter.onComplete();
		});
		
		DisposableObserver<ExchangeEntity> disposableObserver = exchangeRateObservable.subscribeWith(new  DisposableObserver<ExchangeEntity>() {

		    @Override
		    public void onNext(ExchangeEntity exchangeEntity) {

		    	BeanUtils.copyProperties(getExchangeRateDto(exchangeEntity), responseExchangeRateDto);
		    	
		    }

		    @Override
		    public void onError(Throwable e) {
		    }

		    @Override
		    public void onComplete() {
		    }
		});
		
		disposableObserver.dispose();
			
		return Optional.of(responseExchangeRateDto);
		
	}

}
