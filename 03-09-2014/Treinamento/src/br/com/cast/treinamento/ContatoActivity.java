package br.com.cast.treinamento;

import java.io.Serializable;
import java.util.Map;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import br.com.cast.treinamento.domain.exception.ExcecaoNegocio;
import br.com.cast.treinamento.entity.Contato;
import br.com.cast.treinamento.service.ContatoService;

public class ContatoActivity extends LifeCicleActivity {

	public static final String CHAVE_CONTATO = "CHAVE_CONTATO";

	private EditText txtNome, txtEndereco, txtSite, txtTelefone;

	private RatingBar ratingBarRelevancia;

	private Button btnSalvar;

	private Contato contato;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contato);

		bindingElementosLayout();

		contato = (Contato) getIntent().getSerializableExtra(CHAVE_CONTATO);
		if (contato == null) {
			contato = new Contato();
		} else {
			carregarElementosLayout();
		}

		btnSalvar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				carregarContato();
				try {
					ContatoService.getInstancia().salvar(contato);
					ContatoActivity.this.finish();
				} catch (ExcecaoNegocio e) {
					for (Map.Entry<Integer, Integer> erro : e.getMapaErros().entrySet()) {
						EditText campoErro = (EditText) findViewById(erro.getKey());
						Drawable icone = getResources().getDrawable(R.drawable.ic_warning);
						icone.setBounds(0, 0, 50, 50);
						campoErro.setError(getString(erro.getValue()), icone);
					}
				}
			}
		});
	}

	private void carregarElementosLayout() {
		txtNome.setText(contato.getNome());
		txtEndereco.setText(contato.getEndereco());
		txtSite.setText(contato.getSite());
		txtTelefone.setText(contato.getTelefone());
		ratingBarRelevancia.setRating(contato.getRelevancia());
	}
	
	private void carregarContato() {
		contato.setNome(txtNome.getText().toString());
		contato.setEndereco(txtEndereco.getText().toString());
		contato.setSite(txtSite.getText().toString());
		contato.setTelefone(txtTelefone.getText().toString());
		contato.setRelevancia(ratingBarRelevancia.getRating());
	}

	protected boolean validaSite(EditText txtSite) {
		return true;
	}

	protected boolean validaCampos(EditText... componentes) {
		boolean preencheu = true;
		for (EditText componente : componentes) {
			preencheu &= null != componente.getText().toString();
		}
		return preencheu;
	}

	private void bindingElementosLayout() {
		txtNome = (EditText) findViewById(R.id.txtNome);
		txtEndereco = (EditText) findViewById(R.id.txtEndereco);
		txtSite = (EditText) findViewById(R.id.txtSite);
		txtTelefone = (EditText) findViewById(R.id.txtTelefone);
		ratingBarRelevancia = (RatingBar) findViewById(R.id.ratingBarRelevancia);
		btnSalvar = (Button) findViewById(R.id.btnSalvar);
	}

	@Override
	public String getActivityName() {
		return this.getClass().getSimpleName();
	}

}
