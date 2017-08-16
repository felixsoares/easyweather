package com.felix.easywheater.scene.main;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.felix.easywheater.code.util.Constantes;
import com.felix.easywheater.code.util.ProgressHUD;
import com.felix.easywheater.scene.main.model.ViewModelMain;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;

/**
 * Created by user on 15/08/2017.
 */

public class MainActivityController {

    public MainActivity controlerVC;

    public MainActivityController(MainActivity controlerVC){
        this.controlerVC = controlerVC;
    }

    public void searchCity(final String city){
        if(!city.equals("")){
            final ViewModelMain[] viewModelMain = {null};
            final ProgressHUD[] mProgressHUD = {null};

            new AsyncTask<Void, Void, String>(){

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    mProgressHUD[0] = ProgressHUD.show(controlerVC, "Aguarde");
                }

                @Override
                protected String doInBackground(Void... voids) {
                    try {
                        String locale = "";

                        if(Locale.getDefault().getLanguage().equals("pt")){
                            locale = "&lang=pt";
                        }

                        URL url = new URL(Constantes.HOST+city+locale);
                        InputStreamReader reader = new InputStreamReader(url.openStream());

                        if(reader != null) {
                            viewModelMain[0] = new Gson().fromJson(reader, ViewModelMain.class);

                            if (viewModelMain[0].current != null) {
                                viewModelMain[0].status = true;
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return "";
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    if (mProgressHUD[0] != null && mProgressHUD[0].isShowing()) {
                        mProgressHUD[0].dismiss();
                    }
                    controlerVC.onSearchWeatherResponse(viewModelMain[0]);
                }

            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }else {
            controlerVC.onSearchWeatherResponse(new ViewModelMain(false, "Cidade obrigatório"));
        }
    }

}
