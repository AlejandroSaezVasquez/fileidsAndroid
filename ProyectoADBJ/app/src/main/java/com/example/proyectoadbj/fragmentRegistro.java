package com.example.proyectoadbj;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class fragmentRegistro extends AppCompatActivity {

    private EditText txId,txDescriptores,txDescriptoren,txOemsku,txdescriptorExtra,txnomArchivo,txnomEntregable;
    private Spinner spExtensionWin,spTipoDocumentos,spProyecto;
    private Button btLimpiar,btModificar,btNuevo,btAceptar,btCancelar;

    public fragmentRegistro() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_registro);

        // Instanciar botones y controles
        txId= findViewById(R.id.txID);
        txDescriptores = findViewById(R.id.txDescriptores);
        txDescriptoren = findViewById(R.id.txDescriptoren);
        txOemsku = findViewById(R.id.txOemsku);
        txdescriptorExtra = findViewById(R.id.txdescriptorExtra);
        txnomArchivo = findViewById(R.id.txnomArchivo);
        txnomEntregable = findViewById(R.id.txnomEntregable);
        spExtensionWin = findViewById(R.id.spExtesionWin);
        spTipoDocumentos = findViewById(R.id.spTipoDocumentos);
        spProyecto = findViewById(R.id.spProyecto);
        btLimpiar = findViewById(R.id.btLimpiar);
        btModificar = findViewById(R.id.btModificar);
        btNuevo = findViewById(R.id.btNuevo);
        btAceptar = findViewById(R.id.btAceptar);
        btCancelar = findViewById(R.id.btCancelar);

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
                        errorHandler.Toaster(enumMensajes.registroModificado,fragmentRegistro.this);
                    }
                    else
                    {
                        //Error en la transaccion
                        errorHandler.Toaster(enumMensajes.errorSQL,fragmentRegistro.this);
                    }
                }
                else
                {
                    if (addRecord())
                    {
                        errorHandler.Toaster(enumMensajes.registroExitoso,fragmentRegistro.this);
                    }
                    else
                    {
                        //Error en la transaccion
                        errorHandler.Toaster(enumMensajes.errorSQL,fragmentRegistro.this);
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

