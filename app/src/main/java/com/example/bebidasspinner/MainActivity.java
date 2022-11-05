package com.example.bebidasspinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RadioGroup bebidasLayout;
    private Spinner spinnerEuros;
    private Spinner spinnerCentavos;
    private TextView resultado;
    int precio;
    int precioSeleccionado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_window);

        bebidasLayout = findViewById(R.id.bebidasLayout);

        resultado = findViewById(R.id.resultado);

        String[] bebidas = getResources().getStringArray(R.array.bebidas);
        int[] precios = getResources().getIntArray(R.array.precios);

        for (int i = 0; i < bebidas.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(bebidas[i]);
            radioButton.setTag(precios[i]);
            bebidasLayout.addView(radioButton);
        }

        spinnerEuros = (Spinner) findViewById(R.id.eurosSpinner);
        Integer[] euros = {0, 1, 2, 3, 4, 5};
        spinnerEuros.setAdapter(new ArrayAdapter<Integer>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, euros));
        spinnerEuros.setOnItemSelectedListener(this);

        spinnerCentavos = (Spinner) findViewById(R.id.centavosSpinner);
        Integer[] centavos = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90};
        spinnerCentavos.setAdapter(new ArrayAdapter<Integer>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, centavos));
        spinnerCentavos.setOnItemSelectedListener(this);

        precio = 0;
        precioSeleccionado = 0;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        RadioButton radioButton = (RadioButton) findViewById(bebidasLayout.getCheckedRadioButtonId());
        if (radioButton != null) {
            precio = Integer.parseInt(radioButton.getTag().toString());

            precioSeleccionado = (Integer.parseInt(spinnerEuros.getSelectedItem().toString()) * 100) + Integer.parseInt(spinnerCentavos.getSelectedItem().toString());

            System.out.println("Precio botella --> " + precio);
            System.out.println("Precio seleccionado --> " + precioSeleccionado);

            if (precio == precioSeleccionado) {
                resultado.setText(R.string.correcto);
            } else {
                resultado.setText(R.string.incorrecto);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
