package Comunicacion;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ControlCliente extends Thread {

    private Socket paquete;
    private InetAddress ip;

    private static ControlCliente controlClienteActual;
    private ConexionCliente notifiacionCliente;

    private DataInputStream entrada;
    private DataOutputStream salida;

    private Observer observador;
    private String ipg;

    private static boolean iniciado;

    static public ControlCliente getIntance() {
        if (controlClienteActual == null) {
            controlClienteActual = new ControlCliente();
        }
        return controlClienteActual;
    }

    public ControlCliente() {

    }

    public void startServer(){
        start();
    }

    public void iniciar(final String ipg) {

/*new Thread(/*new Runnable() {

    /*
    @Override
    public void run() {
        try {
            ip = InetAddress.getByName(ipg);
            paquete = new Socket(ip, 5000);

            entrada = new DataInputStream(paquete.getInputStream());
            salida = new DataOutputStream(paquete.getOutputStream());
            iniciado = true;
            start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}).start();

*/



        Log.e("Notificacion", "Intentado");
        if (iniciado == false) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (iniciado == false) {
                            ip = InetAddress.getByName(ipg);

                            if (ip != null) {
                                // Espera un cliente
                                paquete = new Socket(ip, 5000);

                                if(paquete !=null){
                                    Log.e("Notificacion", "Conexion Exitos");

                                    if(notifiacionCliente != null){
                                        notifiacionCliente.notificacionCliente(true);
                                    }
                                    entrada = new DataInputStream(paquete.getInputStream());
                                    salida = new DataOutputStream(paquete.getOutputStream());
                                    iniciado = true;
                                    startServer();

                                }else{
                                    Log.e("Notificacion", "Fallo");
                                    if(notifiacionCliente != null){
                                        notifiacionCliente.notificacionCliente(false);
                                    }
                                }



                            }else{
                                if(notifiacionCliente != null){
                                    notifiacionCliente.notificacionCliente(false);
                                }
                                Log.e("Notificacion", "Fallo");
                            }

                        }
                    } catch (IOException e) {
                        Log.e("Notificacion", "Fallo");
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void run() {

        while (iniciado){
             recibir();
        }

    }

    public void recibir() {

        try {
            // Esperando datos
            // Cuanto puedo recibir
            byte[] capacidad = new byte[50];

            entrada.read(capacidad);

            String informacion = new String(capacidad).trim();

            if (this.observador != null) {
                this.observador.mensajeRecibido(informacion);
            }

            mensajeRecibido(informacion);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void enviar(final String datos) {
        new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    salida.write(datos.getBytes());
                    salida.flush();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();

    }


    public void mensajeRecibido(String mensaje) {

    }

    public void setObservador(Observer observador) {
        this.observador = observador;
    }
    public void setNotificador(ConexionCliente notifiacionCliente) {
        this.notifiacionCliente = notifiacionCliente;
    }


    public interface ConexionCliente{
        public void notificacionCliente(boolean exito);
    }

}
