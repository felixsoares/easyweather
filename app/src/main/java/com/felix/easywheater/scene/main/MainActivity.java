package com.felix.easywheater.scene.main;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.felix.easywheater.R;
import com.felix.easywheater.scene.main.model.ViewModelMain;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    private ImageView imgWeather;
    private TextView txtCity, txtCountry,txtRad, txtUmidade, txtSensacao, txtInfo, txtVento;
    private SearchView searchView;
    private MainActivityController controller;
    private RelativeLayout llMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setupListners();
    }

    private void init(){
        controller = new MainActivityController(this);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setVisibility(View.VISIBLE);
        searchView.setQueryHint("Search View");

        imgWeather = (ImageView) findViewById(R.id.imgWeather);
        txtCity = (TextView) findViewById(R.id.txtCity);
        txtCountry = (TextView) findViewById(R.id.txtCountry);
        txtRad = (TextView) findViewById(R.id.txtRad);
        txtUmidade = (TextView) findViewById(R.id.txtUmidade);
        txtSensacao = (TextView) findViewById(R.id.txtSensacao);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        txtVento = (TextView) findViewById(R.id.txtVento);

        llMainContent = (RelativeLayout) findViewById(R.id.llMainContent);
        llMainContent.setVisibility(View.GONE);
    }

    private void setupListners(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                controller.searchCity(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onSearchWeatherResponse(ViewModelMain viewModel) {
        if(viewModel != null){
            if(viewModel.getStatus()){
                setupContent(viewModel);
            }else{
                Toast.makeText(getApplicationContext(), viewModel.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Erro ao buscar no servidor, tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setupContent(ViewModelMain viewModel){
        llMainContent.setVisibility(View.VISIBLE);

        txtCity.setText(viewModel.getLocation().getName());
        txtCountry.setText(viewModel.getLocation().getCountry());
        txtRad.setText(viewModel.getCurrent().getTemp_c()+"º");

        txtInfo.setText(viewModel.getCurrent().getCondition().getText());
        txtSensacao.setText(viewModel.getCurrent().getFeelslike_c()+"º");
        txtUmidade.setText(viewModel.getCurrent().getHumidity()+"%");
        txtVento.setText(viewModel.getCurrent().getWind_kph()+" km/h");

        if(viewModel.getCurrent().getIs_day() == 0){
            if(viewModel.getCurrent().getTemp_c() < 0){
                txtCity.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                txtCountry.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                txtRad.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                imgWeather.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.freeze));
            }else if(viewModel.getCurrent().getTemp_c() > 0 && viewModel.getCurrent().getTemp_c() < 10){
                imgWeather.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.cold));
            }else{
                imgWeather.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.night));
            }
        }else{
            if(viewModel.getCurrent().getTemp_c() < 0){
                txtCity.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                txtCountry.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                txtRad.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                imgWeather.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.freeze));
            }else if(viewModel.getCurrent().getTemp_c() > 0 && viewModel.getCurrent().getTemp_c() < 10){
                imgWeather.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.cold));
            }else if(viewModel.getCurrent().getTemp_c() > 10 && viewModel.getCurrent().getTemp_c() < 22){
                imgWeather.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.good));
            }else{
                Calendar calendar = Calendar.getInstance();
                if(viewModel.getCurrent().getTemp_c() > 22 && calendar.get(Calendar.HOUR_OF_DAY) > 17){
                    imgWeather.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sunny));
                }else{
                    imgWeather.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.desert));
                }
            }
        }

    }
}
