package cl.creative_it_spa.contactos;


import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Confirmar_datos extends AppCompatActivity {

    String nombre, telefono, mail, descrip;
    int fecha_dia, fecha_mes, fecha_year;
    EditText t_nombre, t_fecha, t_telef, t_mail, t_descrip;
    Button bt_editar;
    Boolean fecha_configurada=false;
    TextInputLayout lay_nombre, lay_fecha, lay_tel, lay_mail, lay_descrip;
    Toolbar barra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        Bundle datos= getIntent().getExtras();
        nombre=datos.getString("Nombre");
        fecha_dia=datos.getInt("Fecha_dia");
        fecha_mes=datos.getInt("Fecha_mes");
        fecha_year=datos.getInt("Fecha_year");
        telefono=datos.getString("Telefono");
        mail=datos.getString("Mail");
        descrip=datos.getString("Descrip");
        fecha_configurada=datos.getBoolean("Fecha_configurada");

        t_fecha=(EditText) findViewById(R.id.edt_fecha);
        t_nombre=(EditText) findViewById(R.id.edt_nombre);
        t_telef=(EditText) findViewById(R.id.edt_telefono);
        t_mail=(EditText) findViewById(R.id.edt_mail);
        t_descrip=(EditText) findViewById(R.id.edt_descrip);
        lay_nombre=(TextInputLayout) findViewById(R.id.l_nombre);
        lay_fecha=(TextInputLayout) findViewById(R.id.l_fecha);
        lay_tel=(TextInputLayout) findViewById(R.id.l_tel);
        lay_mail=(TextInputLayout) findViewById(R.id.l_mail);
        lay_descrip=(TextInputLayout) findViewById(R.id.l_descrip);
        barra=(Toolbar)findViewById(R.id.barra_confirmar_datos);
        setSupportActionBar(barra);
        barra.setTitleTextColor(getResources().getColor(R.color.colorBlanco));

        lay_nombre.setHintAnimationEnabled(false);
        t_nombre.setText(nombre);
        lay_nombre.setHintAnimationEnabled(true);
        if(fecha_configurada==true){
            lay_fecha.setHintAnimationEnabled(false);
            t_fecha.setText(fecha_dia + " del " + (fecha_mes+1) + " de " + fecha_year);
            lay_fecha.setHintAnimationEnabled(true);
        }
        lay_tel.setHintAnimationEnabled(false);
        t_telef.setText(telefono);
        lay_tel.setHintAnimationEnabled(true);
        lay_mail.setHintAnimationEnabled(false);
        t_mail.setText(mail);
        lay_mail.setHintAnimationEnabled(true);
        lay_descrip.setHintAnimationEnabled(false);
        t_descrip.setText(descrip);
        lay_descrip.setHintAnimationEnabled(true);

        bt_editar=(Button) findViewById(R.id.bt_aditar);
        bt_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });
    }




    public boolean onKeyDown(int KeyCode, KeyEvent event){
        if (KeyCode==KeyEvent.KEYCODE_BACK){
            volver();
        }
        return super.onKeyDown(KeyCode, event);
    }

    public void volver(){
        Intent i_back = new Intent(Confirmar_datos.this, MainActivity.class);
        i_back.putExtra("Nombre_back", nombre);
        i_back.putExtra("Fecha_dia_back", fecha_dia);
        i_back.putExtra("Fecha_mes_back", fecha_mes);
        i_back.putExtra("Fecha_year_back", fecha_year);
        i_back.putExtra("Telefono_back", telefono);
        i_back.putExtra("Mail_back", mail);
        i_back.putExtra("Descrip_back", descrip);
        i_back.putExtra("Fecha_configurada_back", fecha_configurada);
        startActivity(i_back);
        finish();
    }
}
