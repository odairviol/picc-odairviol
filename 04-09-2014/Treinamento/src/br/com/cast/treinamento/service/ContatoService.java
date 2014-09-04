package br.com.cast.treinamento.service;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import br.com.cast.treinamento.R;
import br.com.cast.treinamento.dao.ContatoDAO;
import br.com.cast.treinamento.domain.exception.ExcecaoNegocio;
import br.com.cast.treinamento.entity.Contato;

public final class ContatoService {

	private static ContatoService INSTANCIA;
	
	private Context contexto;

	private ContatoService(Context contexto) {
		super();
		this.contexto = contexto;
	}

	public static ContatoService getInstancia(Context contexto) {
		if (INSTANCIA == null) {
			INSTANCIA = new ContatoService(contexto);
			
		}

		return INSTANCIA;
	}

	public List<Contato> listarTodos() {
		return ContatoDAO.getInstancia(getContexto()).listarTodos();

	}

	public void salvar(Contato contato) throws ExcecaoNegocio {
		ExcecaoNegocio excecao = new ExcecaoNegocio();
		if (TextUtils.isEmpty(contato.getNome())) {
			excecao.getMapaErros().put(R.id.txtNome, R.string.preenchimento_obrigatorio);
		}
		if (TextUtils.isEmpty(contato.getEndereco())) {
			excecao.getMapaErros().put(R.id.txtEndereco, R.string.preenchimento_obrigatorio);
		}
		if (TextUtils.isEmpty(contato.getSite())) {
			excecao.getMapaErros().put(R.id.txtSite, R.string.preenchimento_obrigatorio);
		}
		if (TextUtils.isEmpty(contato.getTelefone())) {
			excecao.getMapaErros().put(R.id.txtTelefone, R.string.preenchimento_obrigatorio);
		}
		if(!excecao.getMapaErros().isEmpty()){
			throw excecao;
		}
		ContatoDAO.getInstancia(getContexto()).salvar(contato);
	}

	public void excluir(Contato contatoSelecionado) {
		ContatoDAO.getInstancia(getContexto()).excluir(contatoSelecionado);
		
	}

	public Context getContexto() {
		return contexto;
	}

	public void setContexto(Context contexto) {
		this.contexto = contexto;
	}
	
}
