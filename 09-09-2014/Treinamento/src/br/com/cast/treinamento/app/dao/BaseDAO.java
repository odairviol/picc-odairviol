package br.com.cast.treinamento.app.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import br.com.cast.treinamento.app.dao.mapping.ContatoEntity;

public abstract class BaseDAO extends SQLiteOpenHelper {

	public static final String TAG = "DAO";
	private static final String DB_NAME = "TreinamentoAndroid.db";
	private static final int DB_VERSION = 8;

	private Context contexto;

	public BaseDAO(Context contexto) {
		super(contexto, DB_NAME, null, DB_VERSION);
		this.contexto = contexto;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createSQL = "creates.sql";
		try {
			executaScriptAsset(db, createSQL);
		} catch (Exception e) {
			db.execSQL(ContatoEntity.CREATE);
		}
		//db.execSQL(ContatoEntity.CREATE);
	}

	private void executaScriptAsset(SQLiteDatabase db, String createSQL)throws Exception {
		db.beginTransaction();
		try {
			InputStream iptStream = contexto.getAssets().open(createSQL);
			BufferedReader bffreader = new BufferedReader(new InputStreamReader(iptStream));
			String linha;
			while ((linha = bffreader.readLine()) != null) {
				SQLiteStatement statement = db.compileStatement(linha);
				statement.execute();
			}
			db.setTransactionSuccessful();
		} catch (IOException e) {
			Log.i(TAG + "I/O", e.getMessage());
			throw e;
		}catch (SQLException e) {
			Log.i(TAG + "SQL", e.getMessage());
			throw e;
		}finally{
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String drops = "drops.sql";
		try {
			executaScriptAsset(db, drops);
		} catch (Exception e) {
			db.execSQL(ContatoEntity.DROP);
		}
		//db.execSQL(ContatoEntity.DROP);
		this.onCreate(db);
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		this.onUpgrade(db, oldVersion, newVersion);
		
	}
	
}
