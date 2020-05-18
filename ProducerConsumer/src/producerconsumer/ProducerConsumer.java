
package producerconsumer;
import java.util.ArrayList;

public class ProducerConsumer {
    
    private Producer producer;
    private Consumer consumer;
    private ArrayList<Producer> producers;
    private ArrayList<Consumer> consumers;
    
    
    
    
    public ProducerConsumer(int size, int rangeN, int rangeM, int producers, int consumers){
        Buffer buffer = new Buffer(size);
        this.producers = new ArrayList<>();
        for(int i = 0; i<producers;i++){
            this.producers.add(new Producer(buffer, rangeN, rangeM));
            this.producers.get(i).start();
        }
        this.consumers = new ArrayList<>();
        for(int j = 0; j<consumers;j++){
            this.consumers.add(new Consumer(buffer));
            this.consumers.get(j).start();
        }
        
    }
    
    public void stopProducerConsumer(){
        for(int i =0;i<this.producers.size();i++){
            this.producers.get(i).end();
        }
        for(int j =0;j<this.consumers.size();j++){
            this.consumers.get(j).end();
        }
    }
    
}
