package com.example.bancolista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import WebServices.Asynchtask;
import WebServices.WebService;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void Enviar(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        Bundle b = new Bundle();
        b.putString("NOMBRE", "enviar");
        intent.putExtras(b);
        startActivity(intent);
    }

    public void clickLogin(View v){
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();EditText txtUsuario= findViewById(R.id.txtusuario); EditText txtClaves= findViewById(R.id.txtclave);
        WebService ws= new WebService(
                "https://revistas.uteq.edu.ec/ws/login.php?usr=" + txtUsuario.getText().toString() +
                        "&pass=" + txtClaves.getText().toString(),
                datos,
                MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }
    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtrespuesta = findViewById(R.id.txtrespuesta);
        // Verificar la respuesta del servicio web para determinar si la autenticación fue exitosa
        if (result.equals("Login Correcto!")) {
            // Usuario correcto, iniciar MainActivity2
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            Bundle b = new Bundle();
            b.putString("NOMBRE", "Enviar");
            intent.putExtras(b);
            startActivity(intent);
        } else {
            // Usuario incorrecto, mostrar un mensaje de error en el TextView
            txtrespuesta.setText("Clave o Usuario incorrecto. Por favor, inténtalo de nuevo.");
        }
    }
}