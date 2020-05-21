
package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    boolean bandera;
    private int numberN;
    private int numberM;
    private int timeout;
    private int id;
    
    
    
    Producer(Buffer buffer, int numberN, int numberM, int timeout, int id) {
        this.buffer = buffer;
        this.bandera=true;
        this.numberN = numberN;
        this.numberM = numberM;
        this.timeout = timeout;
        this.id=id;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String products = "+-*/";
        
        Random r = new Random(System.currentTimeMillis());
        Random random = new Random(System.currentTimeMillis());
        int firstValue;
        int secondValue;
        
        
        char operation;
        String schemeProcess;
        
        while(this.bandera) {
            operation = products.charAt(r.nextInt(4));
            firstValue = random.nextInt(this.numberM - this.numberN + 1) + this.numberN;
            secondValue = random.nextInt(this.numberM - this.numberN + 1) + this.numberN;
            
            if(divisionExcpetion(operation, secondValue)){
                secondValue++;
            }
            schemeProcess = "("+ operation +" "+firstValue+ " "+secondValue+")";
            int bufferPos = this.buffer.produce(schemeProcess);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer number "+this.id+" produced: " + schemeProcess);
            System.out.println("Total de tareas producidas: " + ++ProducerConsumer.tareasProducidas);
            
            
            GUIFrame.addPendientes(bufferPos, schemeProcess);
            
            
            
           
            
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
    
    boolean divisionExcpetion(char operator, int value){
        Character op = new Character(operator);
        if (value == 0 && op.equals('/')){
            return true;
        }
        else{return false;}
        
    }
    
}
