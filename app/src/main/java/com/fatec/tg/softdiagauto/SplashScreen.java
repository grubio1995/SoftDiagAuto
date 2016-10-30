package com.fatec.tg.softdiagauto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Gabriel Rubio on 16/10/2016.
 */

public class SplashScreen extends Activity {

    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        iniciaSplash();
    }

    public void iniciaSplash(){

        setContentView(R.layout.splash_screen);


        new Thread(new Runnable() {

            @Override
            public void run() {

               try{
                    while(cont == 1 || cont <= 5){

                        Thread.sleep(1000);
                        cont++;
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                if(cont == 6){

                    finish();

                    Intent it = new Intent(SplashScreen.this,ActivityMenuPrincipal.class);
                    startActivity(it);
                    cont++;
                }
            }
        }).start();{

        }

    }
}
