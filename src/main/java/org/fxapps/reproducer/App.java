package org.fxapps.reproducer;

import jakarta.enterprise.event.ObservesAsync;
import org.fxapps.reproducer.events.Event2;
import org.fxapps.reproducer.events.Event1;

import io.quarkiverse.fx.FxPostStartupEvent;
import io.quarkiverse.fx.RunOnFxThread;
import io.quarkiverse.fx.views.FxViewRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import javafx.scene.Parent;
import javafx.scene.Scene;

@ApplicationScoped
public class App {

    @Inject
    Event<Event2> event2;

    @Inject
    FxViewRepository viewRepo;

    void onPostStartup(@Observes final FxPostStartupEvent event) throws Exception {
        final var sampleViewData = viewRepo.getViewData("Sample");
        final var rootNode = (Parent) sampleViewData.getRootNode();
        final var scene = new Scene(rootNode);
        final var stage = event.getPrimaryStage();
        stage.setScene(scene);
        stage.setTitle("MCP FX: A Client for Testing MCP Servers");
        stage.show();
    }

    public void onEvent2(@ObservesAsync Event1 event1) {
        System.out.println("Received event 1, firing event 2");
        event2.fireAsync(new Event2(event1.getText() + "||"));
    }
}