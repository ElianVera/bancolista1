package com.example.bancolista;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.WebService;
import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity2 extends AppCompatActivity
        implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new
                WebService("https://api-uat.kushkipagos.com/transfer/v1/bankList",
                datos, MainActivity2.this, MainActivity1.this);
        ws.execute("GET","Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");

        TextView txtbienvenida = (TextView)findViewById(R.id.txtbienvenida);

        Bundle bundle = this.getIntent().getExtras();

        txtbienvenida.setText("Hola!, Bienvenido \n " +
                bundle.getString("Usuario"));

    }
    public void processFinish(String result) throws JSONException {
        TextView txtBanco = (TextView)findViewById(R.id.txtlista);
        String lstBancos="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstBancos = lstBancos + "\n" +
                    banco.getString("name").toString();
        }
        txtBanco.setText("Respuesta WS Lista de Bancos" + lstBancos);
    }
}