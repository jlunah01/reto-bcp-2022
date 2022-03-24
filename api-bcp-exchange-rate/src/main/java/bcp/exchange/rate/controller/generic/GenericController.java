package bcp.exchange.rate.controller.generic  ;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import bcp.exchange.rate.util.commons.ObjectMessage;
import bcp.exchange.rate.util.commons.ObjectResponse;
import bcp.exchange.rate.util.constants.Constants;
import bcp.exchange.rate.util.enums.CrudEnum;

public class GenericController {

	protected ResponseEntity<ObjectResponse> badRequest(Object msg) {

		if (msg instanceof BindingResult) {
			msg = this.getErrrors((BindingResult) msg);
		}

		return ResponseEntity.badRequest().body(ObjectResponse.builder()
				.message(ObjectMessage.builder().code(Constants.WARNING).message(msg).build()).data(null).build());
	}
	
	protected List<Map<String, String>> getErrrors(BindingResult result) {

		List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
			Map<String, String> error = new HashMap<>();
			error.put(err.getField(), err.getDefaultMessage());
			return error;
		}
		).collect(Collectors.toList());
		return errors;
	}

	protected ResponseEntity<ObjectResponse> ok(Object obj, CrudEnum crud) {

		String msg = "";
		HttpStatus httpStatus = HttpStatus.OK;
		switch (crud) {

		case CONSULTA: 
			msg = Constants.READ_SUCCESSFUL_MESSAGE;
			break;
		case REGISTRO:
			msg = Constants.CREATE_SUCCESSFUL_MESSAGE;
			httpStatus = HttpStatus.CREATED;
			break;
		case ACTUALIZACION:
			msg = Constants.UPDATE_SUCCESSFUL_MESSAGE;
			break;
		case ELIMINACION:
			msg = Constants.DELETE_SUCCESSFUL_MESSAGE;
			break;

		}
		return ResponseEntity.status(httpStatus).body(ObjectResponse.builder()
				.message(ObjectMessage.builder().code(Constants.SUCCESSFUL).message(msg).build()).data(obj).build());
	}
	
	public ResponseEntity<ObjectResponse> customError(Object msg) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ObjectResponse.builder()
				.message(ObjectMessage.builder().code(Constants.ERROR).message("Error interno").build()).data(null).build());
	}
	
	protected ResponseEntity<ObjectResponse> notFound() {
		return customNotFound();
	}

	private ResponseEntity<ObjectResponse> customNotFound() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ObjectResponse.builder()
				.message(ObjectMessage.builder().code(Constants.WARNING).message(Constants.NO_DATA_MESSAGE).build()).data(null).build());
	}
	
}
