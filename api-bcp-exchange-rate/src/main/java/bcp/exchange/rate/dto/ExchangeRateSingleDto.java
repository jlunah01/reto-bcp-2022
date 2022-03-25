package bcp.exchange.rate.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateSingleDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal amount;
	private BigDecimal amountExchangeRate;
	private String originCurrency;
	private String destinationCurrency;
	private BigDecimal exchangeRate;

}
