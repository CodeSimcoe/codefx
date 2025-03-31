package org.fxapps.reproducer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import org.fxapps.reproducer.events.Event1;

@ApplicationScoped
public class MyService {

  @Inject
  Event<Event1> event1;

  public void sendEvent1(final Event1 e1) {
    event1.fireAsync(e1);
  }
}
