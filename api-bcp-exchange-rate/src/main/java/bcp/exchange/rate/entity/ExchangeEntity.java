package bcp.exchange.rate.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import bcp.exchange.rate.entity.generic.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Entity(name="ExchangeEntity")
@Table(name="exchange_rate")
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeEntity extends GenericEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="exchange_rate_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int exchangeRateId;
	
	@Size(max=3, message="Moneda de tipo de cambio debe ser maximo {max} caracteres.")
	@Column(name="exchange_rate_currency")
	private String exchangeRateCurrency;
	
	@Digits(integer=20, fraction=10 , message="El tipo de cambio acepta {integer} caracteres en la parte entera y {fraction} en la parte decimal.")
	@Column(name="exchange_rate")
	private BigDecimal exchangeRate;
	
	@Size(max=1, message="El tipo acepta maximo {max} caracteres. C = Compra ; V = Venta.")
	@Column(name="type")
	private String type;
	
	@Size(max=3, message="Moneda origen de tipo de cambio debe ser maximo {max} caracteres.")
	@Column(name="origin_currency")
	private String originCurrency;
	
	@Size(max=3, message="Moneda origen de tipo de cambio debe ser maximo {max} caracteres.")
	@Column(name="destination_currency")
	private String destinationCurrency;

}
