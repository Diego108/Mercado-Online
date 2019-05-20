package br.com.cris.mercadoonline.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


public class ResourceUtils {

	public static List<Errors> criarListaDeErros(BindingResult bindingResult , MessageSource messageSource) {

		List<Errors> listaDeErros = new ArrayList<Errors>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {

			listaDeErros.add(new Errors(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()),
					fieldError.toString()));
		}

		return listaDeErros;
	}
}
