package br.com.cris.mercadoonline.event;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ReturnResourceListener implements ApplicationListener<ReturnResourceEvent>{

	@Override
	public void onApplicationEvent(ReturnResourceEvent returnResourceEvent) {
		
		HttpServletResponse response = returnResourceEvent.getResponse();
		Integer id = returnResourceEvent.getId();
		
		adicionarHeaderLocation(response, id);
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Integer id) {
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}
