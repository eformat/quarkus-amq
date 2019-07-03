package org.acme.messaging;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.annotations.Merge;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.reactivestreams.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RegisterForReflection(methods = true, fields = true)
@ApplicationScoped
public class Consumer {

    private Jsonb jsonb = JsonbBuilder.create();

    private static String[] coffees = {"log black", "cappacunio", "latte", "flat white", "mocha", "frappa", "expresso", "iced latte"};
    private static String[] people = {"mike", "tom", "jane", "wilma", "brian", "thomas", "sharon"};

    @Merge
    @Incoming("orders")
    public void processOrder(String message) {
        Order order = jsonb.fromJson(message, Order.class);
        order.setOrderId(UUID.randomUUID().toString());
        System.out.println("Received Order:\n" + order);
    }

    @Outgoing("orders")
    public Publisher<String> generateOrder() {
        return Flowable.interval(2, TimeUnit.SECONDS)
                .map(x -> jsonb.toJson(new Order(people[new Random().nextInt(people.length)],
                        coffees[new Random().nextInt(coffees.length)])));
    }

}
