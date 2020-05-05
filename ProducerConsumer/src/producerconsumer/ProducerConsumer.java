
package producerconsumer;

public class ProducerConsumer {
    
    
    
    public ProducerConsumer(int size){
        Buffer buffer = new Buffer(size);
        Producer producer = new Producer(buffer);
        producer.start();
        Consumer consumer = new Consumer(buffer);
        consumer.start();
        
    }
    
}
