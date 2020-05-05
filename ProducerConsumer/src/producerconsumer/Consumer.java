
package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    boolean bandera;
    
    Consumer(Buffer buffer) {
        this.buffer = buffer;
        this.bandera=true;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        char product;
        
        while(this.bandera) {
            product = this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
            Buffer.print("Consumer consumed: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    void terminar(){
        this.bandera=false;
    }
}
