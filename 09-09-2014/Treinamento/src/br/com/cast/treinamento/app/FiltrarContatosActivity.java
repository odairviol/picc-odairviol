package br.com.cast.treinamento.app;

import java.util.Map.Entry;

import br.com.cast.treinamento.app.domain.Contato;
import br.com.cast.treinamento.app.domain.exception.BusinessException;
import br.com.cast.treinamento.app.service.ContatoService;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class FiltrarContatosActivity extends LifeCicleActivity {

	private EditText txtNomeFiltro, txtTelefoneFiltro;
	private Button btnFiltrar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filtrar_contatos);
		super.getSupportActionBar().setSubtitle(R.string.subtitle_filtra_contatos_activity);
		bindingElementosLayout();

		btnFiltrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Contato contato = new Contato();
				contato.setNome(txtNomeFiltro.getText().toString());
				contato.setTelefone(txtTelefoneFiltro.getText().toString());
				redirecionar(contato);
				FiltrarContatosActivity.this.finish();
			}

		});
	}
	
	private void redirecionar(Contato contato) {
		Intent intentListagem = new Intent(this, ListaContatosActivity.class);
		intentListagem.putExtra(ContatoActivity.CHAVE_CONTATO, contato);
		super.startActivity(intentListagem);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.filtrar_contatos, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void bindingElementosLayout() {
		txtNomeFiltro = (EditText) findViewById(R.id.txtNomeFiltro);
		txtTelefoneFiltro = (EditText) findViewById(R.id.txtTelefoneFiltro);
		btnFiltrar = (Button) findViewById(R.id.btnFiltrar);
	}

	@Override
	public String getActivityName() {
		return this.getClass().getSimpleName();
	}
}
