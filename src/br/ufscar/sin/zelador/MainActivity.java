package br.ufscar.sin.zelador;

import br.ufscar.sin.db.DBHandler;
import br.ufscar.sin.db.DBSingleton;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button mRegistrarOcorrenciasButton;
	private Button mVerSituacaoAtualButton;
	private Button mListarMinhasOcorrenciasButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DBHandler dbHandler = DBSingleton.getDBHandler(this);

		setContentView(R.layout.activity_main);

		mRegistrarOcorrenciasButton = (Button) findViewById(R.id.registrarOcorrenciasButton);
		mVerSituacaoAtualButton = (Button) findViewById(R.id.verSituacaoAtualButton);
		mListarMinhasOcorrenciasButton = (Button) findViewById(R.id.listaMinhasOcorrenciasButton);

		mRegistrarOcorrenciasButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent registarOcorrenciaIntent = new Intent(MainActivity.this,
						RegistroOcorrenciaActivity.class);
				startActivity(registarOcorrenciaIntent);
			}
		});

		mVerSituacaoAtualButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent listaOcorrenciaIntent = new Intent(MainActivity.this,
						ListaOcorrenciaActivity.class);
				startActivity(listaOcorrenciaIntent);
			}
		});

		mListarMinhasOcorrenciasButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent listaMinhasOcorrenciasIntent = new Intent(MainActivity.this,
						ListaMinhasOcorrenciasActivity.class);
				startActivity(listaMinhasOcorrenciasIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
