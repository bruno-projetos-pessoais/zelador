package br.ufscar.sin.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class DBHandler {

	private static final String DATABASE_NAME = "zelador1.db";
	private static final int DATABASE_VERSION = 1;

	private static final String TABELA_OCORRENCIA = "ocorrencia";
	private static final String TABELA_CATEGORIA = "categoria";

	private Context context;
	private SQLiteDatabase db;

	private static HashMap<String, String> mProjection;

	public DBHandler(Context context) {
		this.context = context;
		OpenHelper openHelper = new OpenHelper(this.context);
		this.db = openHelper.getWritableDatabase();
	}

	public Long inserirOcorrencia(String categoria, String detalhamento,
			String nome, Integer gravidade, Date data_hora, String status/*
																		 * ,
																		 * Double
																		 * latitude
																		 * ,
																		 * Double
																		 * longitude
																		 */) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss");
		ContentValues conteudo = new ContentValues();
		conteudo.put("categoria", categoria);
		conteudo.put("detalhamento", detalhamento);
		conteudo.put("nome", nome);
		conteudo.put("gravidade", gravidade);
		conteudo.put("data_hora", simpleDateFormat.format(data_hora));
		conteudo.put("status", status);
		// conteudo.put("latitude", latitude);
		// conteudo.put("longitude", longitude);
		return db.insert(TABELA_OCORRENCIA, null, conteudo);
	}
	
	public int atualizarOcorrenciaPorId(ContentValues conteudo, Long id) {
		String[] argumentos = new String[1];
		argumentos[0] = id.toString();
		return db.update(TABELA_OCORRENCIA, conteudo, "id = ?", argumentos);
	}

	public Cursor listaOcorrencias() {
		return db.rawQuery("SELECT * FROM " + TABELA_OCORRENCIA
				+ " order by id DESC", null);
	}

	public Cursor recuperaOcorrencia(Long id) {
		return db.rawQuery("SELECT * FROM " + TABELA_OCORRENCIA
				+ " WHERE id = " + id, null);
	}

	private class OpenHelper extends SQLiteOpenHelper {

		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.i("", "Criando tabela de ocorrencias");
			criarTabelaOcorrencias(db);
			Log.i("", "Tabela de ocorrencias criada");
		}

		private void criarTabelaOcorrencias(SQLiteDatabase db) { 
			db.execSQL("CREATE TABLE "
					+ TABELA_OCORRENCIA
					+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "categoria TEXT, detalhamento TEXT, "
					+ "nome TEXT, gravidade INTEGER, "
					+ "data_hora TEXT, status TEXT, latitude REAL, longitude REAL, fotografia BLOB, id_servidor INTEGER);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}
	}
}
