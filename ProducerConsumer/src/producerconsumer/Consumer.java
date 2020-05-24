
package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jatha.Jatha;
import org.jatha.dynatype.LispValue;

public class Consumer extends Thread {
    Buffer buffer;
    boolean bandera;
    private int timeout;
    private int id;
    Jatha myLisp;
    
    Consumer(Buffer buffer, int timeout, int id) {
        this.buffer = buffer;
        this.bandera=true;
        this.timeout = timeout;
        this.id = id;
        //se inicializa el objeto que realiza operaciones en lisp
        this.myLisp  = new Jatha(false, false);
        //funcion constructora para iniciarlizar el objecto
        myLisp.init();
        myLisp.start();
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        String product;
        
        while(this.bandera) {
            product = this.buffer.consume();
            Buffer.print("Consumer consumed: " + product);
            String tareaRealizada = "{operacion:" + product + ", id: "+this.id+"}";
            System.out.println(tareaRealizada);
            ProducerConsumer.listaTareasRealizadas.add(tareaRealizada);
            /*
            LIBRERIA JATHA:
            Al principio propusimos investigar una manera de procesar la operación con Scheme, 
            pero todas las librerias que existian o eran muy viejas o funcionaban a la inversa
            (correr java en scheme), asi que el siguiente paso fue investigar como correr lisp
            en Java. Encontramos esta librería que simula casi todas las operaciones de
            MyLittleLisp. Se importa el archivo .jar, luego se importa en la clase que se quiera
            usar.
            */
            LispValue result = myLisp.eval(product); //correr la operacion en el environment de lisp
            //Se crea un objeto LispValue, el cual se puede imprimir.
            GUIFrame.addTasksCompleted(product, this.id, result);
            System.out.println("Total de tareas realizadas: " + ++ProducerConsumer.tareasRealizadas);
            
            try {
                Thread.sleep(this.timeout);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    void end(){
        this.bandera=false;
    }
}
