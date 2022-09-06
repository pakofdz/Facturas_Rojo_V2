package com.example.facturasrojov2;

//Hector was here
//no me funciona el git
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class CapturaFacturas extends AppCompatActivity {
    String [] clientes = {"DICONSA SA DE CV","CAVISSU SOLUCIONES"};
    String [] cfdi = {"G03 Gastos en general"};
    String [] formaPago = {"01 Efectivo","02 Cheque Nominativo","03 Transferencia electrónica de fondos (incluye SPEI)"};
    String [] conceptos = {"Servicios de herreria","Puertas para garaje y operadores","Enrejado","Servicio de instalación o colocación de puertas de cocheras"};
    String [] unidad = {"PIEZA","LOTE","SERVICIO"};
    String [] cantidad = {"1","2","3","4","5","6","7","8","9","10"};

    AutoCompleteTextView autoCompleteCl,autoCompleteFP,autoCompleteCon,autoCompleteUni,autoCompleteCant,autoCompleteCDFI;

    ArrayAdapter<String> adapterItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_facturas);

        autoCompleteCl = findViewById(R.id.autoCompleteCliente);
        autoCompleteCDFI = findViewById(R.id.autoCompleteCfdi);
        autoCompleteFP = findViewById(R.id.autoCompleteFormaPago);
        autoCompleteCon = findViewById(R.id.autoCompleteConceptos);
        autoCompleteUni = findViewById(R.id.autoCompleteUnidad);
        autoCompleteCant = findViewById(R.id.autoCompleteCantidad);

        //COMBO BOX CLIENTES
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,clientes);
        autoCompleteCl.setAdapter(adapterItems);
        autoCompleteCl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        //COMBO BOX FORMAS DE PAGO
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,formaPago);
        autoCompleteFP.setAdapter(adapterItems);
        autoCompleteFP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        //COMBO BOX CONCEPTOS
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,conceptos);
        autoCompleteCon.setAdapter(adapterItems);
        autoCompleteCon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        //COMBO BOX UNIDADES
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,unidad);
        autoCompleteUni.setAdapter(adapterItems);
        autoCompleteUni.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        //COMBO BOX CANTIDAD
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,cantidad);
        autoCompleteCant.setAdapter(adapterItems);
        autoCompleteCant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        //COMBO BOX CFDI
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,cfdi);
        autoCompleteCDFI.setAdapter(adapterItems);
        autoCompleteCDFI.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });
    }
}