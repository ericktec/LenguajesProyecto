
package producerconsumer;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private String[] buffer;
    private int index;
    static int count;
    
    Buffer(int size) {
        this.buffer = new String[size];
        this.index=-1;
        count = 1;
    }
    
    public String[] getBuffer(){
        return this.buffer;
    }
    
    synchronized String consume() {
        String product = null;
        
        while(this.index == -1) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Run
        product = turn(false, "", 0);
        notify();
        return product;
    }
    
    synchronized void produce(String product, int idProducer) {
        while(this.index==this.buffer.length-1) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Run
        turn(true, product, idProducer);
        notify();
    }
    synchronized String turn(boolean isProducer, String product, int idProducer){
        if (isProducer){
            this.index++;
            this.buffer[this.index] = product;
            GUIFrame.addTasksPending(product, idProducer);
            double percentage =((this.index+1)/((double) this.buffer.length))*100;
            System.out.println("porcentaje: %"+percentage);
            GUIFrame.setProgressBar((int)percentage);
        } else { // Consumer
            product = this.buffer[this.index];
            this.buffer[this.index] = null;
            this.index--;
            double percentage =((this.index+1)/((double) this.buffer.length))*100;
            System.out.println("porcentaje: %"+percentage);
            GUIFrame.setProgressBar((int)percentage);
            GUIFrame.removeTasksPending(product, this.index);
        }
        return product;
    }
    
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
