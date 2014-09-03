package br.com.cast.treinamento.service;

import java.util.List;

import android.text.TextUtils;
import br.com.cast.treinamento.R;
import br.com.cast.treinamento.dao.ContatoDAO;
import br.com.cast.treinamento.domain.exception.ExcecaoNegocio;
import br.com.cast.treinamento.entity.Contato;

public final class ContatoService {

	private static final ContatoService INSTANCIA = new ContatoService();

	private ContatoService() {
		super();
	}

	public static ContatoService getInstancia() {
		return INSTANCIA;
	}

	public List<Contato> listarTodos() {
		return ContatoDAO.getInstancia().listarTodos();

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
		ContatoDAO.getInstancia().salvar(contato);
	}

	public void excluir(Contato contatoSelecionado) {
		ContatoDAO.getInstancia().excluir(contatoSelecionado);
		
	}
}
