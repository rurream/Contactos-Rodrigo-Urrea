package cl.creative_it_spa.contactos;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Button bt_siguiente;
    EditText tx_nombre, tx_telef, tx_mail, tx_descrip, tx_fecha_nacimiento;
    Calendar calendar;
    int year;
    int month;
    int day;
    Boolean fecha_configurada = false;
    TextInputLayout lay_nombre, lay_fecha, lay_tel, lay_mail, lay_descrip;
    Toolbar barra;
    Bundle datos_back;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tx_nombre = (EditText) findViewById(R.id.nombre);
        tx_fecha_nacimiento = (EditText) findViewById(R.id.fecha_nacimiento);
        tx_telef = (EditText) findViewById(R.id.telefono);
        tx_mail = (EditText) findViewById(R.id.mail);
        tx_descrip = (EditText) findViewById(R.id.descrip_contacto);
        lay_nombre = (TextInputLayout) findViewById(R.id.layout_nombre);
        lay_fecha = (TextInputLayout) findViewById(R.id.layout_fecha);
        lay_tel = (TextInputLayout) findViewById(R.id.layout_telefono);
        lay_mail = (TextInputLayout) findViewById(R.id.layout_mail);
        lay_descrip = (TextInputLayout) findViewById(R.id.layout_descrip);
        barra = (Toolbar) findViewById(R.id.barra_main);
        setSupportActionBar(barra);
        barra.setTitleTextColor(getResources().getColor(R.color.colorBlanco));

        tx_nombre.setInputType(InputType.TYPE_CLASS_TEXT);
        tx_telef.setInputType(InputType.TYPE_CLASS_TEXT);
        tx_mail.setInputType(InputType.TYPE_CLASS_TEXT);

        Intent intento=getIntent();
        if (intento.hasExtra("Nombre_back")) {
            datos_back = getIntent().getExtras();
            lay_nombre.setHintAnimationEnabled(false);
            tx_nombre.setText(datos_back.getString("Nombre_back"));
            lay_nombre.setHintAnimationEnabled(true);
            year = datos_back.getInt("Fecha_year_back");
            month = datos_back.getInt("Fecha_mes_back");
            day = datos_back.getInt("Fecha_dia_back");
            lay_tel.setHintAnimationEnabled(false);
            tx_telef.setText(datos_back.getString("Telefono_back"));
            lay_tel.setHintAnimationEnabled(true);
            lay_mail.setHintAnimationEnabled(false);
            tx_mail.setText(datos_back.getString("Mail_back"));
            lay_mail.setHintAnimationEnabled(true);
            lay_descrip.setHintAnimationEnabled(false);
            tx_descrip.setText(datos_back.getString("Descrip_back"));
            lay_descrip.setHintAnimationEnabled(true);
            fecha_configurada = datos_back.getBoolean("Fecha_configurada_back");
            if (fecha_configurada == true) {
                lay_fecha.setHintAnimationEnabled(false);
                tx_fecha_nacimiento.setText(day + " del " + (month + 1) + " de " + year);
                lay_fecha.setHintAnimationEnabled(true);
            }
        } else {
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }


        bt_siguiente = (Button) findViewById(R.id.bt_siguiente);
        bt_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Confirmar_datos.class);
                i.putExtra("Nombre", tx_nombre.getText().toString());
                i.putExtra("Fecha_dia", day);
                i.putExtra("Fecha_mes", month);
                i.putExtra("Fecha_year", year);
                i.putExtra("Fecha_configurada", fecha_configurada);
                i.putExtra("Telefono", tx_telef.getText().toString());
                i.putExtra("Mail", tx_mail.getText().toString());
                i.putExtra("Descrip", tx_descrip.getText().toString());

                startActivity(i);
                finish();
            }
        });

        tx_fecha_nacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogfragment = new DatePickerDialogTheme5();
                dialogfragment.show(getFragmentManager(), "Theme 5");
            }
        });

    }


    public class DatePickerDialogTheme5 extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_TRADITIONAL, this, year, month, day);
            return datepickerdialog;
        }

        public void onDateSet(DatePicker view, int year_new, int month_new, int day_new) {
            tx_fecha_nacimiento.setText(day_new + " del " + (month_new + 1) + " de " + year_new);
            day = day_new;
            month = month_new;
            year = year_new;
            fecha_configurada = true;
        }
    }
}
