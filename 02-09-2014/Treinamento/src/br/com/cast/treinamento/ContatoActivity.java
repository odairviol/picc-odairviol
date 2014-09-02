package br.com.cast.treinamento;

import br.com.cast.treinamento.entity.Contato;
import br.com.cast.treinamento.service.ContatoService;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class ContatoActivity extends ActionBarActivity {

	private EditText txtNome, txtEndereco, txtSite, txtTelefone;
	
	private RatingBar ratingBarRelevancia;
	
	private Button btnSalvar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contato);
		
		criaComponentesActivity();
		
		btnSalvar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(validaCampos(txtNome, txtEndereco, txtSite, txtTelefone) && validaSite(txtSite)){
					Contato contato = new Contato();
					contato.setNome(txtNome.getText().toString());
					contato.setEndereco(txtEndereco.getText().toString());
					contato.setSite(txtSite.getText().toString());
					contato.setTelefone(txtTelefone.getText().toString());
					contato.setRelevancia(ratingBarRelevancia.getRating());
					ContatoService.getInstancia().salvar(contato);
					ContatoActivity.this.finish();
				}
			}
		});
		
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

	private void criaComponentesActivity() {
		txtNome = (EditText) findViewById(R.id.txtNome);
		txtEndereco = (EditText) findViewById(R.id.txtEndereco);
		txtSite = (EditText) findViewById(R.id.txtSite);
		txtTelefone = (EditText) findViewById(R.id.txtTelefone);
		ratingBarRelevancia = (RatingBar) findViewById(R.id.ratingBarRelevancia);
		btnSalvar = (Button) findViewById(R.id.btnSalvar);
	}

}
