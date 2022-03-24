package bcp.exchange.rate.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

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
public class ExchangeRateDto {
	
	private int exchangeRateId;
	
	@Size(max=3, message="Moneda de tipo de cambio debe ser maximo {max} caracteres")
	private String exchangeRateCurrency;
	
	@Digits(integer=20, fraction=10 , message="El tipo de cambio acepta {integer} caracteres en la parte entera y {fraction} en la parte decimal.")
	private BigDecimal exchangeRate; 
	
	@Size(max=1, message="El tipo acepta maximo {max} caracteres. C = Compra ; V = Venta.")
	private String type;
	
	@Size(max=3, message="Moneda origen de tipo de cambio debe ser maximo {max} caracteres.")
	private String originCurrency;
	
	@Size(max=3, message="Moneda origen de tipo de cambio debe ser maximo {max} caracteres.")
	private String destinationCurrency;
	
	@Size(max=1, message="El estado debe ser maximo {max} caracter.")
	private String status;
	
	private LocalDate registrationDate;
	
	private LocalDate updateDate;
	
	private String registrationUser;
	
	private String updateUser;
	

}
