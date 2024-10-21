package article.rabbitmq.exchangedelayedmessage.processor;


import article.rabbitmq.exchangedelayedmessage.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class MessageProducerImpl implements MessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public MessageProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void sendMessage(Object message, int delayInMinutes) {
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, RabbitmqConfig.ROUTING_KEY, new GenericMessage<Object>(message), msg -> {
            msg.getMessageProperties().setHeader("x-delay", delayInMinutes * 60 * 1000); //Aplicação do delay em milisegundos
            msg.getMessageProperties().setHeader("ORIGIN", "sendMessage(Object message, int delay)");
            msg.getMessageProperties().setContentType("application/json");
            return msg;
        });
    }

    @Override
    public void sendMessage(Object message) {
        sendMessage(message, 0);
    }
}
