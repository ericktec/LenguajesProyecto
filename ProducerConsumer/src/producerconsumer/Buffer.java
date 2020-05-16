
package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private String[] buffer;
    private int index;
    
    Buffer(int size) {
        this.buffer = new String[size];
        this.index=-1;
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
        product = this.buffer[this.index];
        this.buffer[this.index] = null;
        this.index--;
        notify();
        
        return product;
    }
    
    synchronized void produce(String product) {
        while(this.index==this.buffer.length-1) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.index++;
        this.buffer[this.index] = product;
        
        
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
