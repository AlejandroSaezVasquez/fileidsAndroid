package com.example.proyectoadbj;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class errorHandler {

    // Clase para manejar todos los errores de la aplicacion.

    public static String Toaster(enumMensajes err, Context context) {

        // Crear toast
        Toast myToast = new Toast(context);
        myToast.setGravity(Gravity.TOP, 0, 0);

        switch (err) {
            case loginError:
                // Mostrar mensaje de error
                myToast.makeText(context, "Usuario o contraseña inválidos", Toast.LENGTH_SHORT).show();
                break;
            case camposRequeridos:
                myToast.makeText(context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                break;
            case dataError:
                // Mostrar mensaje de error
                myToast.makeText(context, "Datos inválidos", Toast.LENGTH_SHORT).show();
                break;
            case registroExiste:
                // Mostrar mensaje de error
                myToast.makeText(context, "Este registro ya existe", Toast.LENGTH_SHORT).show();
                break;
            case registroNoExiste:
                // Mostrar mensaje de error
                myToast.makeText(context, "Este registro no existe", Toast.LENGTH_SHORT).show();
                break;

            case errorSQL:
                // Mostrar mensaje de error
                myToast.makeText(context, "Error en consulta SQL", Toast.LENGTH_SHORT).show();
                break;

            case formularioIncompleto:
                // Mostrar mensaje de error
                myToast.makeText(context, "Formulario incompleto o con campos erroneos", Toast.LENGTH_SHORT).show();
                break;

            case registroExitoso:
                // Mostrar mensaje de error
                myToast.makeText(context, "Registro añadido a la base de datos", Toast.LENGTH_SHORT).show();
                break;
            case registroFallido:
                // Mostrar mensaje de error
                myToast.makeText(context, "Error al registrar, ya existe", Toast.LENGTH_SHORT).show();
                break;
            case registroModificado:
                // Mostrar mensaje de error
                myToast.makeText(context, "Registro modificado con éxito", Toast.LENGTH_SHORT).show();
                break;
            case registroEliminado:
                // Mostrar mensaje de error
                myToast.makeText(context, "Registro eliminado con éxito", Toast.LENGTH_SHORT).show();
                break;
            case errorSubirArchivo:
                // Mostrar mensaje de error
                myToast.makeText(context, "Error al subir el archivo", Toast.LENGTH_SHORT).show();
                break;
            case errorBorrarArchivo:
                // Mostrar mensaje de error
                myToast.makeText(context, "El campo no pudo ser eliminado", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show();
        }
        return "";
    }


}
