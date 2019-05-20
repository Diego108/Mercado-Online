package br.com.cris.mercadoonline.handler;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cris.mercadoonline.utils.Errors;
import br.com.cris.mercadoonline.utils.ResourceUtils;

@ControllerAdvice
public class ResourceCatchHandle extends ResponseEntityExceptionHandler  {

	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemDoUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDoDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		
		List<Errors> listaDeErros = Arrays.asList(new Errors(mensagemDoUsuario, mensagemDoDesenvolvedor));
		
		return super.handleExceptionInternal(ex, listaDeErros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String mensagemDoUsuario = messageSource.getMessage("mensagem.invalida.request-incorreto", null, LocaleContextHolder.getLocale());
		String mensagemDoDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		
		List<Errors> listaDeErros =  Arrays.asList(new Errors(mensagemDoUsuario, mensagemDoDesenvolvedor));
		
		return super.handleExceptionInternal(ex, listaDeErros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Errors> listaDeErros = ResourceUtils.criarListaDeErros(ex.getBindingResult(), messageSource);
		
		return super.handleExceptionInternal(ex, listaDeErros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
		
		String mensagemDoUsuario = this.messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
		String mensagemDoDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		
		List<Errors> listaDeErros = Arrays.asList(new Errors(mensagemDoUsuario, mensagemDoDesenvolvedor));
		
		return super.handleExceptionInternal(ex, listaDeErros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}
