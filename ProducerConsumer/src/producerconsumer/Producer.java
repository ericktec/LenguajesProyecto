
package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    boolean bandera;
    
    Producer(Buffer buffer) {
        this.buffer = buffer;
        this.bandera=true;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String products = "+-*/";
        Random r = new Random(System.currentTimeMillis());
        char product;
        
        while(this.bandera) {
            product = products.charAt(r.nextInt(4));
            this.buffer.produce(product);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer produced: " + product);
            
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
