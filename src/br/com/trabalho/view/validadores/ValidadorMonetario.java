package br.com.trabalho.view.validadores;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ValidadorMonetario")
public class ValidadorMonetario implements Validator {
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException{
		
		BigDecimal valor = (BigDecimal) value;
		
		if(valor == null){
			return;
		}
		
		if(valor.longValue() == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Valor deve ser maior que zero", "Valor deve ser maior que zero"));
		}
	}
}