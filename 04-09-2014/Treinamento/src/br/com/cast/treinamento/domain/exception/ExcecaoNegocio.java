package br.com.cast.treinamento.domain.exception;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExcecaoNegocio extends Exception {

	private static final long serialVersionUID = -4854183896045130432L;

	private final Map<Integer, Integer> mapaErros = new LinkedHashMap<>();

	public Map<Integer, Integer> getMapaErros() {
		return mapaErros;
	}

}
