package com.example.facturasrojov2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegistrarCliente extends AppCompatActivity {

    EditText nombreCliente,rfcCliente,correoCliente;
    Button registrarCL, mostrarCL, editarCL;
    TableLayout tableClientes;
    int n=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        nombreCliente = (EditText) findViewById(R.id.txtNomCliente);
        rfcCliente = (EditText) findViewById(R.id.txtRFC);
        correoCliente = (EditText) findViewById(R.id.txtCorreo);
        registrarCL=(Button)findViewById(R.id.btnReg);
        mostrarCL=(Button)findViewById(R.id.btnMstr);
        editarCL=(Button)findViewById(R.id.btnEdit);
        tableClientes = findViewById(R.id.jTableClientes);



        registrarCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar()){
                    regCliente();
                    mostrarClientes();
                }
            }
        });

        mostrarCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarClientes();
                /*n++;
                if(n == 1){
                mostrarClientes();
                }else{
                    Toast.makeText(getApplicationContext(),"MOSTRANDO CLIENTES",Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        editarCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarCliente();
                mostrarClientes();
            }
        });

    }


    public boolean validar(){
        boolean retorno=true;
        String nom,rfc,correo;
        nom=nombreCliente.getText().toString();
        rfc=rfcCliente.getText().toString();
        correo=correoCliente.getText().toString();
        if(nom.isEmpty()){
            nombreCliente.setError("Escriba el nombre del cliente");
            retorno=false;
        }
        if(rfc.isEmpty()){
            rfcCliente.setError("Escriba el RFC del cliente");
            retorno=false;
        }
        if(correo.isEmpty()){
            correoCliente.setError("Escriba el correo del cliente");
            retorno=false;
        }

        return retorno;
    }

    public void clickRegistroCliente(View view){
        resetColorReg();
        view.setBackgroundColor(Color.GRAY);
        TableRow registro = (TableRow)view;
        View controlNom = (TextView)registro.getChildAt(0);
        String nombre = controlNom.toString();

        TextView tvNomCliente = registro.findViewById(R.id.tvNomCliente);
        TextView tvRfcCliente = registro.findViewById(R.id.tvRfcCliente);
        TextView tvCorreoCliente = registro.findViewById(R.id.tvCorreoCliente);
        nombreCliente = (EditText) findViewById(R.id.txtNomCliente);
        rfcCliente = (EditText) findViewById(R.id.txtRFC);
        correoCliente = (EditText) findViewById(R.id.txtCorreo);

        nombreCliente.setText(tvNomCliente.getText());
        rfcCliente.setText(tvRfcCliente.getText());
        correoCliente.setText(tvCorreoCliente.getText());
        /*try {
            if(!nombre.isEmpty()){
                //PreparedStatement stm= conexionBD().prepareStatement("SELECT clienteNom,clienteRfc,clienteCorreo FROM Clientes Where clienteNom='" +nombre+"'");
                Statement stm=conexionBD().createStatement();
                ResultSet rs = stm.executeQuery("SELECT clienteNom,clienteRfc,clienteCorreo FROM Clientes Where clienteNom='" + nombre + "'");
                //Toast.makeText(getApplicationContext(),nombre,Toast.LENGTH_LONG).show();

                if (!rs.wasNull()){
                    nombreCliente.setText(rs.getString(1));
                    rfcCliente.setText(rs.getString(2));
                    correoCliente.setText(rs.getString(3));
                }else {
                    nombreCliente.setText("");
                    rfcCliente.setText("");
                    correoCliente.setText("");
                    Toast.makeText(getApplicationContext(),"NO SE ENCONTRO EL CLIENTE",Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }*/
    }

    public void resetColorReg(){
        for (int i = 0; i < tableClientes.getChildCount(); i++){
            View registros = tableClientes.getChildAt(i);
            registros.setBackgroundColor(Color.TRANSPARENT);
        }
    }


    public Connection conexionBD(){
        Connection conexion = null;
        try {
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.14;databaseName=FacturasRob;user=sa;password=teclaguna");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }

    public void regCliente(){
        try {
            PreparedStatement pst=conexionBD().prepareStatement("INSERT INTO Clientes VALUES(?,?,?)");
            pst.setString(1,nombreCliente.getText().toString());
            pst.setString(2,rfcCliente.getText().toString());
            pst.setString(3,correoCliente.getText().toString());
            pst.executeUpdate();

            Toast.makeText(getApplicationContext(),"CLIENTE REGISTRADO EXITOSAMENTE",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void mostrarClientes() {
        tableClientes.removeAllViews();
        try {
            Statement stm=conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("SELECT clienteNom,clienteRfc,clienteCorreo FROM Clientes");
            String datos[] = new String[3];
            while (rs.next()){
                View registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout,null,false);
                TextView tvNomCliente = registro.findViewById(R.id.tvNomCliente);
                TextView tvRfcCliente = registro.findViewById(R.id.tvRfcCliente);
                TextView tvCorreoCliente = registro.findViewById(R.id.tvCorreoCliente);
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                tvNomCliente.setText(datos[0]);
                tvRfcCliente.setText(datos[1]);
                tvCorreoCliente.setText(datos[2]);
                tableClientes.addView(registro);
            }

            //PreparedStatement pst = conexionBD().prepareStatement("SELECT clienteNom,clienteRfc,clienteCorreo FROM Clientes");

        }catch (Exception e){
        }
    }

    public void editarCliente(){
        try {
            PreparedStatement stm= conexionBD().prepareStatement("UPDATE Clientes SET clienteNom='"+nombreCliente.getText().toString()+"',clienteRfc='"+rfcCliente.getText().toString()+"', clienteCorreo='"+correoCliente.getText().toString()+"' WHERE clienteNom= '"+nombreCliente.getText().toString()+"'");
            stm.executeUpdate();
            Toast.makeText(getApplicationContext(),"CLIENTE MODIFICADO EXITOSAMENTE",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}