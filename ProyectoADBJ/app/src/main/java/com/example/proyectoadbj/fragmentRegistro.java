package com.example.proyectoadbj;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentRegistro extends Fragment {

   EditText txId,txDescriptores,txDescriptoren,txOemsku,txdescriptorExtra,txnomArchivo,txnomEntregable;
   Spinner spExtensionWin,spTipoDocumentos,spProyecto;
   Button btLimpiar,btModificar,btNuevo,btAceptar,btCancelar;
   ImageButton Imgbtbuscar;

    public fragmentRegistro() {
        // Required empty public constructor
    }

    // Parametros
    private String activeRegistro;


    // Constructor para crear instancias de este fragmento
    public static fragmentRegistro crearFragmentRegistro(String activeRegistro){
        fragmentRegistro frag=new fragmentRegistro();
        // Crear un bundle de argumentos para inyectar en el fragmento
        Bundle argumentos=new Bundle();
        // Acá se pueden insertar tantos argumentos como sea necesario en pares key-value
        argumentos.putString("activeRegistro",activeRegistro);
        // Registrar como argumentos.
        frag.setArguments(argumentos);
        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Desempacar bundle y extraer nombre de usuario activo.
        activeRegistro=getArguments().getString("activeRegistro");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar fragmento
        View view = inflater.inflate(R.layout.fragment_tab_registro, container, false);
        // Inicializar fragmento
        initRegistroFragment(container,view);

        return view;
    }

    private void initRegistroFragment(ViewGroup container, View view){

        // Instanciar botones y controles

        TextView txId=view.findViewById(R.id.txID);
        TextView txDescriptores=view.findViewById(R.id.txDescriptores);
        TextView txDescriptoren=view.findViewById(R.id.txDescriptoren);
        TextView txOemsku=view.findViewById(R.id.txOemsku);
        TextView txdescriptorExtra=view.findViewById(R.id.txdescriptorExtra);
        TextView txnomArchivo=view.findViewById(R.id.txnomArchivo);
        TextView txnomEntregable=view.findViewById(R.id. txnomEntregable);
        Spinner spExtensionWin=view.findViewById(R.id.spExtesionWin);
        Spinner spTipoDocumentos=view.findViewById(R.id.spTipoDocumentos);
        Spinner spProyecto=view.findViewById(R.id.spProyecto);
        btLimpiar= (Button) view.findViewById(R.id.btLimpiar);
        btModificar= (Button) view.findViewById(R.id.btModificar);
        btNuevo= (Button) view.findViewById(R.id.btNuevo);
        btAceptar= (Button) view.findViewById(R.id.btAceptar);
        btCancelar = (Button) view.findViewById(R.id.btCancelar);
        Imgbtbuscar = (ImageButton) view.findViewById(R.id.Imgbtbuscar);


        Imgbtbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volcar el propertybundle que corresponde al id existente.
                volcarPbEnForm();
            }
        });


        btNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Limpiar forma
                limpiarForm();
                //Activar edicion
                activarModoEdicion(true);
            }
        });

        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volcar el propertybundle que corresponde al id existente.
                volcarPbEnForm();
                //Permitir edicion
                activarModoEdicion(true);
            }
        });

        btLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarForm();
            }
        });

        btLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarForm();
            }
        });
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarForm();
                activarModoEdicion(false);
            }
        });

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Construir un propertybundle y registrarlo en la DB

                if (dao.checkExistenceOnDb(txId.getText().toString().trim()))
                {
                    if (updateRecord())
                    {
                        // Registro modificado exitosamente
                       // errorHandler.Toaster(enumMensajes.registroModificado,fragmentRegistro.this);
                    }
                    else
                    {
                        //Error en la transaccion
                       // errorHandler.Toaster(enumMensajes.errorSQL,fragmentRegistro.this);
                    }
                }
                else
                {
                    if (addRecord())
                    {
                        //errorHandler.Toaster(enumMensajes.registroExitoso,fragmentRegistro.this);
                    }
                    else
                    {
                        //Error en la transaccion
                       // errorHandler.Toaster(enumMensajes.errorSQL,fragmentRegistro.this);
                    }
                }
                //Destruir pb
                pb = null;
            }
        });

    }

    DAO dao = new DAO(fragmentRegistro.this);
    queryDump q = new queryDump();
    propertyBundle pb=new propertyBundle();



    //region EventHandlers






    //endregion

    //region UI_HANDLERS

    //Modo edicion
    private void activarModoEdicion(boolean modo){

        //Casillas de texto
        txId.setEnabled(false);
        txDescriptoren.setEnabled(!modo);
        txDescriptores.setEnabled(!modo);
        txdescriptorExtra.setEnabled(!modo);
        txOemsku.setEnabled(!modo);

        //Spinners
        spExtensionWin.setEnabled(modo);
        spProyecto.setEnabled(modo);
        spTipoDocumentos.setEnabled(modo);

        //Botones
        btAceptar.setEnabled(modo);
        btCancelar.setEnabled(modo);
        btModificar.setEnabled(!modo);
        btNuevo.setEnabled(!modo);
        Imgbtbuscar.setEnabled(false);
    }

    private void actualizarForm() {

        //Actualizar valores de la interfaz

        txId.setText(pb.getId());
        txDescriptores.setText(pb.getDescriptorEs());
        txDescriptoren.setText(pb.getDescriptorEn());
        txdescriptorExtra.setText(pb.getDescriptorExtra());
        txOemsku.setText(pb.getOemsku());

        //Spinners
        spExtensionWin.setSelection(pb.getIdExtension()-1);
        spProyecto.setSelection(pb.getIdProyecto()-1);
        spTipoDocumentos.setSelection(pb.getIdTipoEntregable()-1);

        //Nombres de archivo. Estas casillas siempre son de solo lectura.
        txnomArchivo.setText(pb.getNombreArchivo());
        txnomEntregable.setText(pb.getNombreEntregable());

    }

    private void limpiarForm() {

        //Actualizar valores de la interfaz

        txId.setText("");
        txDescriptores.setText("");
        txDescriptoren.setText("");
        txdescriptorExtra.setText("");
        txOemsku.setText("");

        //Spinners
        spExtensionWin.setSelection(0);
        spProyecto.setSelection(0);
        spTipoDocumentos.setSelection(0);

        //Nombres de archivo. Estas casillas siempre son de solo lectura.
        txnomArchivo.setText("");
        txnomEntregable.setText("");

    }
    //endregion

    //region Comunicacion Con MODEL
    private propertyBundle crearPbDesdeUi()
    {
        // Construir un propertybundle, un objeto que tiene todos los atributos del archivo registrado.
        pb = new propertyBundle();
        String txid=txId.getText().toString().trim();

        if (txid!=null && !txid.isEmpty())
        {
            pb.setId(txid);
        }

        pb.setDescriptorEs(txDescriptores.getText().toString());
        pb.setDescriptorEn(txDescriptoren.getText().toString());
        pb.setDescriptorExtra(txdescriptorExtra.getText().toString());
        pb.setOemsku(txOemsku.getText().toString());

        pb.setIdExtension(spExtensionWin.getSelectedItemPosition()+1);
        pb.setIdProyecto(spProyecto.getSelectedItemPosition()+1);
        pb.setIdTipoEntregable(spTipoDocumentos.getSelectedItemPosition()+1);

        return pb;
    }

    private boolean updateRecord(){
        boolean success=dao.updateRecord(crearPbDesdeUi());
        //Actualizar interfaz con el id
        actualizarForm();
        //Congelar interfaz
        activarModoEdicion(false);
        //Subir archivos si los hay
        return success;

    }

    private boolean addRecord(){

        pb = dao.addRecord(crearPbDesdeUi());

        if (pb != null)
        {
            //Actualizar el form con los nuevos datos.
            actualizarForm();
            //Congelar interfaz
            activarModoEdicion(false);
            //Operacion exitosa
            return true;
        }
        else
        {
            //Operacion fallida
            return false;
        }

    }

    private void volcarPbEnForm()
    {
        // Validar si el valor en txid es correcto

        if (dao.checkExistenceOnDb(txId.getText().toString().trim()))
        {
            //Construir pb con id
            pb.setId(txId.getText().toString().trim());
            //Actualizar desde la db
            pb = dao.getPropertyBundleFromDB(pb);
            // Volcar en la pantalla.
            actualizarForm();

        }
        else
        {
            errorHandler.Toaster(enumMensajes.registroNoExiste,fragmentRegistro.this);
            limpiarForm();
        }
    }

    //endregion

}

