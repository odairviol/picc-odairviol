package br.com.cast.treinamento.widget;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.cast.treinamento.R;
import br.com.cast.treinamento.entity.Contato;

@SuppressLint("ViewHolder")
public class ContatoAdapter extends BaseAdapter {

	private List<Contato> itens;
	private Activity contexto;
	
	public ContatoAdapter(Activity contexto, List<Contato> itens) {
		super();
		this.itens = itens;
		this.contexto = contexto;
	}

	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public Contato getItem(int posicao) {
		return itens.get(posicao);
	}

	@Override
	public long getItemId(int posicao) {
		Contato contato = getItem(posicao);
		return contato.getId();
	}

	@Override
	public View getView(int posicao, View view, ViewGroup viewPai) {
		LayoutInflater layoutInflater = contexto.getLayoutInflater();
		View layoutItem = layoutInflater.inflate(R.layout.item_listview_contato, null);
		TextView lblNome = (TextView) layoutItem.findViewById(R.id.lblNome);
		TextView lblTelefone = (TextView) layoutItem.findViewById(R.id.lblTelefone);
		lblNome.setText(getItem(posicao).getNome());
		lblTelefone.setText(getItem(posicao).getTelefone());
		
		if(posicao%2 == 0) {
			layoutItem.setBackgroundColor(contexto.getResources().getColor(R.color.ouro));
		}
		return layoutItem;
	}
}
