package article.rabbitmq.exchangedelayedmessage.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitmqConfig {

    public static final String EXCHANGE_NAME = "ARTICLE_EXCHANGE";
    public static final String QUEUE_NAME = "ARTICLE_QUEUE";
    public static final String ROUTING_KEY = "ARTICLE_ROUTING_KEY";


    @Bean
    public CustomExchange exchange() {
        return new CustomExchange(EXCHANGE_NAME, "x-delayed-message", Boolean.TRUE, Boolean.FALSE, Map.of("x-delayed-type", "direct"));
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, Boolean.TRUE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY).noargs();
    }
}
