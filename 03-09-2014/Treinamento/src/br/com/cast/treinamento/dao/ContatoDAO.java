package br.com.cast.treinamento.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.treinamento.entity.Contato;

public final class ContatoDAO {

	private static final ContatoDAO INSTANCIA = new ContatoDAO();
	private static final List<Contato> REPOSITORIO = new ArrayList<Contato>();
	private static Long SEQUENCE = 1L;
	
	
	private ContatoDAO(){
		super();
	}

	public static ContatoDAO getInstancia() {
		return INSTANCIA;
	}
	
	public List<Contato> listarTodos(){
		return REPOSITORIO;
		
	}
	
	public void salvar(Contato contato){
		if(contato.getId() == null){
			contato.setId(SEQUENCE++);
			REPOSITORIO.add(contato);
		}else{
			REPOSITORIO.set(REPOSITORIO.indexOf(contato), contato);
		}
	}

	public void excluir(Contato contatoSelecionado) {
		REPOSITORIO.remove(contatoSelecionado);
		
	}

}
