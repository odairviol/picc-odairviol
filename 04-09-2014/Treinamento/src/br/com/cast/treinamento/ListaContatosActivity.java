package br.com.cast.treinamento;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.cast.treinamento.entity.Contato;
import br.com.cast.treinamento.service.ContatoService;
import br.com.cast.treinamento.widget.ContatoAdapter;

public class ListaContatosActivity extends LifeCicleActivity {

	private ListView listViewContatos;
	
	private Contato contatoSelecionado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_contatos);
		
		listViewContatos = (ListView) super.findViewById(R.id.listViewContatos);
		super.registerForContextMenu(listViewContatos);
		
		listViewContatos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
				contatoSelecionado = (Contato) adapter.getItemAtPosition(posicao);
//				String mensagem = getString(R.string.voce_clicou_no_item, adapter.getItemAtPosition(posicao));
//				Toast.makeText(view.getContext(), mensagem, Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		carregarListView();
	}

	private void carregarListView() {
		List<Contato> contatos = ContatoService.getInstancia(ListaContatosActivity.this).listarTodos();
		ContatoAdapter adapter = new ContatoAdapter(this, contatos);
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
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.lista_contatos_context, menu);
		super.onCreateContextMenu(menu, view, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_editar:
			editar();
			break;
		case R.id.action_excluir:
			excluir();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	private void excluir() {
		new AlertDialog.Builder(this)
		.setTitle(R.string.dialog_confirmacao)
		.setMessage(getString(R.string.deseja_excluir_esse_contato, contatoSelecionado.getNome()))
		.setPositiveButton(R.string.dilog_sim, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String mensagem = getString(R.string.contato_excluido, contatoSelecionado.getNome());
				ContatoService.getInstancia(ListaContatosActivity.this).excluir(contatoSelecionado);
				Toast.makeText(ListaContatosActivity.this, mensagem, Toast.LENGTH_SHORT).show();
			}
		})
		.setNeutralButton(R.string.dialog_nao, null)
		.create()
		.show();
		onResume();
	}

	private void editar() {
		Intent intent;
		intent = new Intent(this, ContatoActivity.class);
		intent.putExtra(ContatoActivity.CHAVE_CONTATO, contatoSelecionado);
		super.startActivity(intent);
	}

	@Override
	public String getActivityName() {
		return this.getClass().getSimpleName();
	}
}
