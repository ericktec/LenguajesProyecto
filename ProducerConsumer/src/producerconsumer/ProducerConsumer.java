
package producerconsumer;
import java.util.ArrayList;
import org.jatha.Jatha;

public class ProducerConsumer {
    
    private ArrayList<Producer> producers;
    private ArrayList<Consumer> consumers;
    private Buffer buffer;
    private int size;
    private int count;
    public static int tareasRealizadas;
    public static ArrayList<String> listaTareasRealizadas;
    
    
    
    public ProducerConsumer(int producers, int consumers, int timeoutProducer, int timeoutConsumer, int rangeN, int rangeM, int size){
        this.buffer = new Buffer(size);
        this.size = size;
        this.count = 0;
        tareasRealizadas = 0;
        listaTareasRealizadas = new ArrayList<String>();
        this.producers = new ArrayList<>();
        for(int i = 0; i<producers;i++){
            this.producers.add(new Producer(buffer, rangeN, rangeM, timeoutProducer, i));
            this.producers.get(i).start();
        }
        this.consumers = new ArrayList<>();
        for(int j = 0; j<consumers;j++){
            this.consumers.add(new Consumer(buffer, timeoutConsumer, j));
            this.consumers.get(j).start();
        }
        
    }
    
    public void stopProducerConsumer(){
        this.buffer= new Buffer(size);
        for(int i =0;i<this.producers.size();i++){
            this.producers.get(i).end();
        }
        for(int j =0;j<this.consumers.size();j++){
            this.consumers.get(j).end();
        }
        System.out.print("\n\n\n");
        System.out.println("Las tareas realizadas fueron: " + listaTareasRealizadas);
        System.out.println("El total de tareas realizadas fueron: " + tareasRealizadas);
        System.out.print("\n");
    }
    
}
