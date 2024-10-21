package article.rabbitmq.exchangedelayedmessage.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.GenericMessage;

import java.nio.charset.StandardCharsets;

@Configuration
public class MessageConveterConfig {

    @Bean
    public MessageConverter messageConverter() {
        return new MessageConverter() {
            @Override
            public Message toMessage(Object message, MessageProperties messageProperties) throws MessageConversionException {
                return new Message(message.toString().getBytes(StandardCharsets.UTF_8), messageProperties);
            }

            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                return new GenericMessage<Object>(new String(message.getBody(), StandardCharsets.UTF_8), message.getMessageProperties().getHeaders());
            }
        };
    }

}
