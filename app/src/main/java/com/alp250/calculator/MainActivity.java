package com.alp250.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mariuszgromada.math.mxparser.Expression;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String expresion="";

    TextView pantalla;

    TextView[] numeros=new TextView[10];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pantalla=findViewById(R.id.pantalla);

        for (int i = 0; i < 10; i++) {
            String botonId="num"+i;
            int idAbuscar=getResources().getIdentifier(botonId,"id",getPackageName());
            if (idAbuscar!=0){
                numeros[i]=findViewById(idAbuscar);
                if (numeros[i]!=null){
                    numeros[i].setOnClickListener(this);
                }
            }
        }
        TextView ac=findViewById(R.id.ac);
        if (ac!=null){
            ac.setOnClickListener(this);
        }
        TextView c=findViewById(R.id.c);
        if (c!=null){
            c.setOnClickListener(this);
        }
        TextView porcentaje=findViewById(R.id.porcentaje);
        if (porcentaje!=null){
            porcentaje.setOnClickListener(this);
        }
        TextView division=findViewById(R.id.division);
        if (division!=null){
            division.setOnClickListener(this);
        }
        TextView multiplicacion=findViewById(R.id.multiplicacion);
        if (multiplicacion!=null){
            multiplicacion.setOnClickListener(this);
        }
        TextView suma=findViewById(R.id.sumar);
        if (suma!=null){
            suma.setOnClickListener(this);
        }
        TextView resta=findViewById(R.id.restar);
        if (resta!=null){
            resta.setOnClickListener(this);
        }
        TextView punto=findViewById(R.id.punto);
        if (punto!=null){
            punto.setOnClickListener(this);
        }
        TextView igual=findViewById(R.id.igual);
        if (igual!=null){
            igual.setOnClickListener(this);
        }

    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        for (int i = 0; i < 10; i++) {
            if (id== (numeros[i]!=null?numeros[i].getId():-1)){
                expresion+=i;
                pantalla.setText(expresion);
                return;
            }
        }
        if (id==R.id.ac){
            expresion="";
            pantalla.setText("");
            return;
        }
        if (id==R.id.c){
            expresion=expresion.substring(0,(expresion.contains("+")?expresion.indexOf("+"):expresion.contains("-")?expresion.indexOf("-"):expresion.contains("*")?expresion.indexOf("*"):expresion.contains("/")?expresion.indexOf("/"):expresion.contains("%")?expresion.indexOf("%"):expresion.length()));
            pantalla.setText(expresion);
            return;
        }
        if (id==R.id.sumar){
            expresion+="+";
            pantalla.setText(expresion);
            return;
        }
        if (id==R.id.restar){
            expresion+="-";
            pantalla.setText(expresion);
            return;
        }
        if (id==R.id.multiplicacion){
            expresion+="*";
            pantalla.setText(expresion);
            return;
        }
        if (id==R.id.division){
            expresion+="/";
            pantalla.setText(expresion);
            return;
        }
        if (id==R.id.punto){
            expresion+=".";
            pantalla.setText(expresion);
            return;
        }
        if (id==R.id.porcentaje){
            expresion+="%";
            pantalla.setText(expresion);
        }
        if (id==R.id.igual){
            Expression expression = new Expression(expresion);
            double resultado = expression.calculate();
            if (Double.isNaN(resultado)){
                pantalla.setText("SINTAX ERROR");
            }else {
            pantalla.setText(String.valueOf(resultado));
            expresion=String.valueOf(resultado);
        }

    }

}}