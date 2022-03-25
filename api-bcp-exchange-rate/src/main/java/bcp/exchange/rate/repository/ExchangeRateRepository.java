package bcp.exchange.rate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bcp.exchange.rate.entity.ExchangeEntity;
import java.util.Optional;
import bcp.exchange.rate.util.Queries;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeEntity, Integer>{
	
	@Query(Queries.SELECT_EXCHANGE_RATE + Queries.AND_TYPE_EXCHANGE_RATE)
	Optional<ExchangeEntity> findExchangeRate(@Param("origin") String origin, @Param("destiny") String destiny);
	
	@Query(Queries.SELECT_EXCHANGE_RATE	+ Queries.AND_EXCHANGE_RATE)
	Optional<ExchangeEntity> findExchangeRate(@Param("exchangeRateCurrency") String exchangeRateCurrency, @Param("type") String type, @Param("origin") String origin, @Param("destiny") String destiny);

}
