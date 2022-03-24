package bcp.exchange.rate.entity.generic;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class GenericEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name="ind_del")
	protected String status;
	
	@Column(name="fec_reg", columnDefinition = "DATE")
	protected LocalDate registrationDate;
	
	@Column(name="fec_act", columnDefinition = "DATE")
	protected LocalDate updateDate;
	
	@Column(name="usu_reg")
	protected String registrationUser;
	
	@Column(name="usu_act")
	protected String updateUser;
	
}
