package bcp.exchange.rate.util;

public class Queries {
	
	public static final String SELECT_EXCHANGE_RATE =
            " SELECT e " +
                " FROM ExchangeEntity e " +
            	" WHERE e.originCurrency=:origin AND e.destinationCurrency=:destiny AND e.status = '1' ";
	
	public static final String AND_TYPE_EXCHANGE_RATE = 
			" AND e.type = 'C' " ;
	
	public static final String AND_EXCHANGE_RATE =
			" AND e.exchangeRateCurrency=:exchangeRateCurrency AND e.type=:type" ;
	

}
