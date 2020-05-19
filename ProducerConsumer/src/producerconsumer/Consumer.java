
package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    boolean bandera;
    private int timeout;
    private int id;
    
    Consumer(Buffer buffer, int timeout, int id) {
        this.buffer = buffer;
        this.bandera=true;
        this.timeout = timeout;
        this.id = id;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        String product;
        
        while(this.bandera) {
            product = this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
            Buffer.print("Consumer consumed: " + product);
            String tareaRealizada = "{operacion:" + product + ", id: "+this.id+"}";
            ProducerConsumer.listaTareasRealizadas.add(tareaRealizada);
            GUIFrame.addTasksCompleted(product, id);
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
