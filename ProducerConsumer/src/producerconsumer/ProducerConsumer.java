
package producerconsumer;

public class ProducerConsumer {
    
    private Producer producer;
    private Consumer consumer;
    
    
    
    public ProducerConsumer(int size, int rangeN, int rangeM){
        Buffer buffer = new Buffer(size);
        producer = new Producer(buffer, rangeN, rangeM);
        producer.start();
        consumer = new Consumer(buffer);
        consumer.start();
        
    }
    
    public void stopProducerConsumer(){
        producer.end();
        consumer.end();
    }
    
}
