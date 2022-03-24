package bcp.exchange.rate.dto.expose;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateRs implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BigDecimal amount;
	public BigDecimal amountExchangeRate;
	public String originCurrency;
	public String destinationCurrency;
	public BigDecimal exchangeRate;
	
	public String message;

}
