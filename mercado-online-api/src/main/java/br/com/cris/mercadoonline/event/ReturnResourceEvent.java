package br.com.cris.mercadoonline.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class ReturnResourceEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private Integer id;
	
	public ReturnResourceEvent(Object source, HttpServletResponse response, Integer id) {
		
		super(source);
		
		this.response = response;
		this.id = id;
	}
}
