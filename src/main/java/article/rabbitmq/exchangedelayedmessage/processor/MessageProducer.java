package article.rabbitmq.exchangedelayedmessage.processor;

public interface MessageProducer {
    void sendMessage(Object message, int delay);
    void sendMessage(Object message);
}
