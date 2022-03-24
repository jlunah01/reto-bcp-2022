package bcp.exchange.rate.dto.expose;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ExchangeRateRq implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BigDecimal amount;
	
	@NotBlank(message="destinationCurrency is mandatory")
	public String originCurrency;
	
	@NotBlank(message="destinationCurrency is mandatory")
	public String destinationCurrency;
	
	public String customerType;
	
}
