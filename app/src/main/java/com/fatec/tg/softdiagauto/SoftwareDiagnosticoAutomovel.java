package com.fatec.tg.softdiagauto;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SoftwareDiagnosticoAutomovel extends Activity {
    private static final int REQUEST_ENABLE_BT = 27;
    private TextView txtSaudacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_diagnostico_automovel);
        this.txtSaudacao=(TextView)findViewById(R.id.txtSaudacao);
        //verificarUsuario();
    }

	/*
	@Override
	protected void onResume() {
		super.onResume();
		this.txtSaudacao=(TextView)findViewById(R.id.txtSaudacao);
		verificarUsuario();
	}
	*/

    //Verificar o nome do usuário.
    //Deve ser executada somente ao iniciar o aplicativo.
    public void verificarUsuario() {
        SharedPreferences pref = getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
        String nome = pref.getString("Nome", "");
        if (nome=="") {
            perguntarNome();
        } else {
            txtSaudacao.setText("Bem vindo, " + nome + "!");
        }
    }

    //Função para exibir um prompt para o usuário digitar o nome.
    public void perguntarNome() {
        AlertDialog.Builder inputAlert = new AlertDialog.Builder(this);
        inputAlert.setTitle("Bem vindo!");
        inputAlert.setMessage("Digite seu nome, por favor.");
        final EditText userInput = new EditText(this);
        inputAlert.setView(userInput);
        inputAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nome = userInput.getText().toString();
                if((nome.trim().length()==0) || (nome==null)) {
                    perguntarNome();
                } else {
                    SharedPreferences pref = getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putString("Nome", nome);
                    editor.commit();
                    txtSaudacao.setText("Bem vindo, " + nome + "!");
                }
            }
        });
        inputAlert.create().show();
    }

    //Fun��o para alterar o nome do usu�rio.
    public void alterarNome() {
        SharedPreferences pref = getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.remove("Nome");
        editor.commit();
        perguntarNome();
    }

    //Função para inicio da conexão com o leitor.
    public void conectarLeitor(View v) {
        verificarBluetooth();
    }

    //Função para verificar o status do serviço Bluetooth.
    public void verificarBluetooth() {
        String msg="";
        BluetoothAdapter meuBT = BluetoothAdapter.getDefaultAdapter();

        //Verificar se o dispositivo suporta a tecnologia Bluetooth.
        if (meuBT == null) {
            msg = "Seu dispositivo Android não suporta a tecnologia Bluetooth!";
        } else {
            if (!(meuBT.isEnabled())) {
                msg = "É necessário ligar o serviço Bluetooth!";
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult (enableBtIntent, REQUEST_ENABLE_BT);
            } else {
                iniciarLeitor();
            }
        }

        if(msg.length() > 0) {
            exibirToast(msg);
        }
    }

    //Verificar o retorno da Activity.
    protected void onActivityResult(int requestCode , int resultCode , Intent data ) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if ( resultCode == RESULT_OK ) {
                iniciarLeitor();
            } else if (resultCode != RESULT_CANCELED) {
                exibirToast("Ocorreu um erro inesperado ao ligar o serviço Bluetooth!");
            }
        }
    }

    //Chamar Activity para conexão com o Hardware.
    public void iniciarLeitor() {
        startActivity(new Intent(this,ConexaoHardware.class));
    }

    //Exibir uma mensagem para o usuário, utilizando AlertDialog.
    public void exibirAlerta(String msg) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Aviso");
        alerta.setMessage(msg);
        alerta.setPositiveButton("OK", null);
        alerta.create().show();
    }

    //Exibir uma mensagem para o usuário, utilizando Toast.
    public void exibirToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //Sair do aplicativo.
    public void sairApp(View v) {
        finish();
    }

	/*
	//Buscar dispositivos pareados.
	Set<BluetoothDevice> pairedDevices = meuBT.getBondedDevices();
	if (pairedDevices.size() > 0) {
		try {
			ArrayList<String> dispositivos = new ArrayList<String>();
			String dn=null,da=null;
			for (BluetoothDevice device : pairedDevices) {
				dn=device.getName();
				da=device.getAddress();
				if(((dn.length()==0) || (da.length()==0))
						|| ((dn==null) || (da==null))) {
					dn="Sem informa��o";
					da="Sem informa��o";
				}
				dispositivos.add(dn);
			}
			msg = "Dispositivos encontrados: " + dispositivos.toString();
		} catch(Exception e) {
			msg=e.toString();
		}
	}
	msg = "\nTotal de dispositivos: " + pairedDevices.size();
	*/

}
