package bcp.exchange.rate.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcp.exchange.rate.controller.generic.GenericController;
import bcp.exchange.rate.dto.ExchangeRateDto;
import bcp.exchange.rate.dto.ExchangeRateSingleDto;
import bcp.exchange.rate.service.ExchangeRateService;
import bcp.exchange.rate.util.commons.ObjectResponse;
import bcp.exchange.rate.util.constants.Constants;
import bcp.exchange.rate.util.enums.CrudEnum;
import bcp.exchange.rate.util.exceptions.ServiceException;


@RestController
@RequestMapping("/exchangerate")
public class ExchangeRateController extends GenericController{
	
	private static Logger LOG = LoggerFactory.getLogger(ExchangeRateController.class);
	
	@Autowired
	private ExchangeRateService exchangeRateService;
	
	@GetMapping("/get/v1/{amount}/{originCurrency}/{destinationCurrency}")
	public ResponseEntity<ObjectResponse> exchangeRate(@PathVariable BigDecimal amount, @PathVariable String originCurrency, @PathVariable String destinationCurrency){
		
		LOG.info("Ingreso a Controller exchangeRate");
		
		try {
			if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0 ||	StringUtils.isBlank(originCurrency) || StringUtils.isBlank(destinationCurrency)) {
				return super.badRequest(Constants.ERROR_MESSAGE_PARAMETERS);
			}
			
			Optional<ExchangeRateSingleDto> rs = exchangeRateService.exchangeRate(amount,originCurrency,destinationCurrency);
			
			return super.ok(rs.get(), CrudEnum.CONSULTA);
			
		} catch (ServiceException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String stackTrace = sw.toString();
			LOG.error("Exception : " + stackTrace);
			return super.customError(Constants.READ_ERROR_MESSAGE);
		}
	}
	

	@PostMapping
	public ResponseEntity<ObjectResponse> save(@RequestBody @Validated ExchangeRateDto exchangeRateDto, BindingResult result, OAuth2Authentication oauth) {
		
		LOG.info("Ingreso a Controller save");
		
		if (result.hasErrors()) {
			return super.badRequest(result);
		}
		
		String user = StringUtils.EMPTY;
		
		if(oauth.getUserAuthentication().getPrincipal() != null) {
			user = oauth.getUserAuthentication().getPrincipal().toString();
		}

		try {

			Optional<ExchangeRateDto> resultExchangeRateDto = exchangeRateService.findExchangeRate(exchangeRateDto);

			if (resultExchangeRateDto.get().getExchangeRateId() != NumberUtils.INTEGER_ZERO) {
				return super.badRequest(Constants.DUPLICATE_DATA_MESSAGE);
			}
			
			ExchangeRateDto responseExchangeRateDto = exchangeRateService.save(exchangeRateDto,CrudEnum.REGISTRO.name(),user);
			if (responseExchangeRateDto != null) {
				return super.ok(responseExchangeRateDto, CrudEnum.REGISTRO);
			}
			
			return super.customError(Constants.CREATE_ERROR_MESSAGE);
			
		} catch (ServiceException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String stackTrace = sw.toString();
			LOG.error("Exception : " + stackTrace);
			return super.customError(Constants.CREATE_ERROR_MESSAGE);
		}
		
	}
	
	@PutMapping
	public ResponseEntity<ObjectResponse> update(@RequestBody @Validated ExchangeRateDto exchangeRateDto, BindingResult result, OAuth2Authentication oauth) {

		LOG.info("Ingreso a Controller update");
		
		if (result.hasErrors()) {
			return super.badRequest(result);
		}
		
		String user = StringUtils.EMPTY;
		
		if(oauth.getUserAuthentication().getPrincipal() != null) {
			user = oauth.getUserAuthentication().getPrincipal().toString();
		}

		try {

			Optional<ExchangeRateDto> resultExchangeRateDto = exchangeRateService.findExchangeRate(exchangeRateDto);

			if (resultExchangeRateDto.get().getExchangeRateId() != NumberUtils.INTEGER_ZERO) {
				
				ExchangeRateDto	oExchangeRateDto = resultExchangeRateDto.get();

				exchangeRateDto.setExchangeRateId(resultExchangeRateDto.get().getExchangeRateId());
				exchangeRateDto.setStatus(resultExchangeRateDto.get().getStatus());
				exchangeRateDto.setRegistrationDate(resultExchangeRateDto.get().getRegistrationDate());
				exchangeRateDto.setRegistrationUser(resultExchangeRateDto.get().getRegistrationUser());
				BeanUtils.copyProperties(exchangeRateDto, oExchangeRateDto);

				ExchangeRateDto responseExchangeRateDto = exchangeRateService.save(oExchangeRateDto, CrudEnum.ACTUALIZACION.name(),user);
				if (responseExchangeRateDto != null) {
					return super.ok(responseExchangeRateDto, CrudEnum.ACTUALIZACION);
				}
				
				return super.customError(Constants.UPDATE_ERROR_MESSAGE);
			}
			
			return super.notFound();
			
		} catch (ServiceException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String stackTrace = sw.toString();
			LOG.error("Exception : " + stackTrace);
			return super.customError(Constants.UPDATE_ERROR_MESSAGE);
		}
	}

}
