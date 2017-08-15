package com.felix.easywheater.scene.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.felix.easywheater.R;
import com.felix.easywheater.scene.main.model.ViewModelMain;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

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
        searchView.setVisibility(View.GONE);

        txtCity.setText(viewModel.getLocation().getName());
        txtCountry.setText(viewModel.getLocation().getCountry());
        txtRad.setText(viewModel.getCurrent().getTemp_c()+"º");

        txtInfo.setText(viewModel.getCurrent().getCondition().getText());
        txtSensacao.setText(viewModel.getCurrent().getFeelslike_c()+"º");
        txtUmidade.setText(viewModel.getCurrent().getHumidity()+"%");
        txtVento.setText(viewModel.getCurrent().getWind_kph()+" km/h");
    }
}
