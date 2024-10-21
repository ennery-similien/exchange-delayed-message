package article.rabbitmq.exchangedelayedmessage;

import article.rabbitmq.exchangedelayedmessage.processor.MessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class ExchangeDelayedMessageApplication implements CommandLineRunner {
    private final Logger logger = Logger.getGlobal();

    private final MessageProducer producer;

    public ExchangeDelayedMessageApplication(MessageProducer producer) {
        this.producer = producer;
    }

    public static void main(String[] args) {
        SpringApplication.run(ExchangeDelayedMessageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("");

        for (int i = 1; i <= 5; i++) {
            String message = String.format("Mensagem #%s com X-DELAY de %s minuto(s)", i, i);

            producer.sendMessage(message, i);
            logger.info("ENVIADO >>> " + message);

            logger.info("");
            Thread.sleep(10000);
        }
    }
}
