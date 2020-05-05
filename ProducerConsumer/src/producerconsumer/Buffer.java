
package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private char[] buffer;
    private int index;
    
    Buffer(int size) {
        this.buffer = new char[size];
        this.index=-1;
    }
    
    synchronized char consume() {
        char product = 0;
        
        while(this.index == -1) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer[this.index];
        this.buffer[this.index] = 0;
        this.index--;
        notify();
        
        return product;
    }
    
    synchronized void produce(char product) {
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
