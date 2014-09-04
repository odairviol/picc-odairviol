package br.com.cast.treinamento.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.cast.treinamento.entity.Contato;

public final class ContatoDAO extends BaseDAO {

	private static ContatoDAO INSTANCIA;

	private ContatoDAO(Context contexto) {
		super(contexto);
	}

	public static ContatoDAO getInstancia(Context contexto) {
		if (INSTANCIA == null) {
			INSTANCIA = new ContatoDAO(contexto);
		}
		return INSTANCIA;
	}

	public List<Contato> listarTodos() {
		try {
			SQLiteDatabase db = super.getReadableDatabase();
			String[] columns = { "id", "nome", "endereco", "site", "telefone", "relevancia" };
			Cursor cursor = db.query("tb_contato", columns, null, null, null, null, null);
			List<Contato> contatos = new ArrayList<Contato>();
			while (cursor.moveToNext()) {
				Contato contato = new Contato();
				contato.setId(cursor.getLong(0));
				int indiceColunaNome = cursor.getColumnIndex("nome");
				contato.setNome(cursor.getString(indiceColunaNome));
				contato.setEndereco(cursor.getString(2));
				contato.setSite(cursor.getString(3));
				contato.setTelefone(cursor.getString(4));
				contato.setRelevancia(cursor.getFloat(5));
				contatos.add(contato);

			}
			return contatos;
		} catch (SQLException excecao) {
			Log.e("DAO", excecao.getMessage());
			throw excecao;
		} finally {
			super.close();
		}

	}

	public void salvar(Contato contato) {
		SQLiteDatabase db = super.getWritableDatabase();
		try {
			ContentValues values = new ContentValues();
			values.put("nome", contato.getNome());
			values.put("endereco", contato.getEndereco());
			values.put("site", contato.getSite());
			values.put("telefone", contato.getTelefone());
			values.put("relevancia", contato.getRelevancia());
			db.beginTransaction();
			if (contato.getId() == null) {
				db.insert("tb_contato", null, values);
			} else {
				String[] parametroWhere = new String[] { contato.getId().toString() };
				db.update("tb_contato", values, "id=?", parametroWhere);
			}
			db.setTransactionSuccessful();
		} catch (SQLException excecao) {
			Log.e("DAO", excecao.getMessage());
			throw excecao;
		} finally {
			db.endTransaction();
			super.close();
		}
	}

	public void excluir(Contato contatoSelecionado) {
		SQLiteDatabase db = super.getWritableDatabase();
		try {
			db.beginTransaction();
			String[] parametroWhere = new String[] { contatoSelecionado.getId().toString() };
			db.delete("tb_contato", "id=?", parametroWhere);
			db.setTransactionSuccessful();
		} catch (SQLException excecao) {
			Log.e("DAO", excecao.getMessage());
			throw excecao;
		} finally {
			db.endTransaction();
			super.close();
		}
	}

}
