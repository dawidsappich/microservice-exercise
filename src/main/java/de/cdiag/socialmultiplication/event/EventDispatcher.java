package de.cdiag.socialmultiplication.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

    private RabbitTemplate template;

    // The exchange to use to send anything related to Multiplication
    private String multiplicationExchange;

    // The routing key to use to send this particular event
    private String multiplicationSolvedRoutingKey;

    @Autowired
    public EventDispatcher(final RabbitTemplate template,
                           @Value("${multiplication.exchange}") final String multiplicationExchange,
                           @Value("${multiplication.solved.key}") final String multiplicationSolvedRoutingKey) {
        this.template = template;
        this.multiplicationExchange = multiplicationExchange;
        this.multiplicationSolvedRoutingKey = multiplicationSolvedRoutingKey;
    }
}
