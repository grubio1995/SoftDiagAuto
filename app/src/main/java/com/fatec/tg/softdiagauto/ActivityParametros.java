package com.fatec.tg.softdiagauto;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fatec.tg.softdiagauto.model.Sensor;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel Rubio on 19/10/2016.
 */

public class ActivityParametros extends AppCompatActivity {

    ListView l1;
    Double[] d2 ={700.0,56.0,12.0};
    Double [] tempo ={0.00,0.01,0.02,0.03,0.04,0.05,0.06,0.07,0.08,0.09,0.010};
    int[] i1 ={R.drawable.rotacao_motor,R.drawable.termometro,R.drawable.bateria};

    ArrayList<Sensor> sensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar act = getActionBar();

        if(act == null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        else
            act.setDisplayHomeAsUpEnabled(true);

        sensors = new ArrayList<>();
        sensors.add(new Sensor("Rotação do motor","RPM",R.drawable.rotacao_motor,1));
        sensors.add(new Sensor("Temperatura da água","ºC",R.drawable.termometro,1));
        sensors.add(new Sensor("Tensão da bateria","V",R.drawable.bateria,1));


        setContentView(R.layout.activity_parametros);
        l1=(ListView)findViewById(R.id.listViewParametros);
        l1.setAdapter(new dataListAdapter(sensors));
        gerarGrafico(tempo[0],d2[0]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_parametros,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.gerar_relatorio_parametros:
                Toast.makeText(this, "Relatório Parâmetros", Toast.LENGTH_SHORT).show();
                break;
            case R.id.filtrar_parametros:
                Toast.makeText(this, "Filtro Parâmetros", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void gerarGrafico(Double tempo,Double valor){

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(
                new DataPoint[] {
                        new DataPoint(tempo,valor)
                });
        graph.addSeries(series);
    }

    public void passarDados(){

    }

    class dataListAdapter extends BaseAdapter {
        ArrayList<Sensor> sensors;

        public dataListAdapter(ArrayList<Sensor> sensors) {

            this.sensors = new ArrayList<>();

            for(Sensor sensor: sensors){
                if(sensor.getStatus() == 1){
                    this.sensors.add(sensor);
                }
            }
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return sensors.size();
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
            ImageView i1;
            title = (TextView) row.findViewById(R.id.title);
            detail = (TextView) row.findViewById(R.id.detail);
            i1=(ImageView)row.findViewById(R.id.img);
            title.setText(sensors.get(position).getNome());
            detail.setText(String.valueOf(sensors.get(position).getValores().get(19))+
                            sensors.get(position).getUnidade());
            i1.setImageResource(sensors.get(position).getIconDrawable());

            return (row);



        }


    }
}