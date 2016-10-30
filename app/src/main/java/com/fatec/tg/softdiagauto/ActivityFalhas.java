package com.fatec.tg.softdiagauto;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gabriel Rubio on 21/10/2016.
 */

public class ActivityFalhas extends AppCompatActivity {
    ListView l1;
    String[] t1 = {"P0001", "P0002"};
    String[] d1 = {"Falha no sensor de aceleração", "Falha no sensor de rotação°"};
    int[]  i1= {0};
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar act = getActionBar();

        if(act == null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        else
            act.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_falhas);
        l1 = (ListView) findViewById(R.id.listViewFalhas);
        l1.setAdapter(new ActivityFalhas.dataListAdapter(t1, d1,i1));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_falhas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.gerar_relatorio_falhas:
                Toast.makeText(this, "Relatório ActivityFalhas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.filtrar_falhas:
                Toast.makeText(this, "Filtro ActivityFalhas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.apagar_falhas:
                Toast.makeText(this, "Apagar falhas", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

    public void confirmarApagarFalhas(MenuItem item) { // Método para verificar se o usuário deseja apagar as falhas
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Apagar falhas");
        alertDialogBuilder.setMessage("Deseja apagar as falhas?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //apagarFalhas();
                    }
                }).setNegativeButton("Não",	new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //action para "não" ou apenas fechar a janela
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    class dataListAdapter extends BaseAdapter {
        String[] Title, Detail;


        dataListAdapter() {
            Title = null;
            Detail = null;

        }

        public dataListAdapter(String[] text, String[] text1, int[] text3) {
            Title = text;
            Detail = text1;

        }

        public int getCount() {
            // TODO Auto-generated method stub
            return Title.length;
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View row;
            row = inflater.inflate(R.layout.activity_list_view, parent, false);
            TextView title, detail;
            title = (TextView) row.findViewById(R.id.title);
            detail = (TextView) row.findViewById(R.id.detail);
            title.setText(Title[position]);
            detail.setText(Detail[position]);


            return (row);
        }
    }
}
