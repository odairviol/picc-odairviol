package br.com.cast.treinamento;

import java.util.List;

import br.com.cast.treinamento.entity.Contato;
import br.com.cast.treinamento.service.ContatoService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaContatosActivity extends ActionBarActivity {

	private ListView listViewContatos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_contatos);
		
		listViewContatos = (ListView) super.findViewById(R.id.listViewContatos);
		
		listViewContatos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
				String mensagem = getString(R.string.voce_clicou_no_item, adapter.getItemAtPosition(posicao));
				Toast.makeText(view.getContext(), mensagem, Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		
		listViewContatos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao, long arg3) {
				String mensagem = getString(R.string.voce_clicou_no_item, adapter.getItemAtPosition(posicao));
				Toast.makeText(view.getContext(), mensagem, Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		List<Contato> contatos = ContatoService.getInstancia().listarTodos();
		final ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);
		listViewContatos.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lista_contatos, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		int id = item.getItemId();
		switch (id) {
		case R.id.action_novo:
			intent = new Intent(this, ContatoActivity.class);
			super.startActivity(intent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
