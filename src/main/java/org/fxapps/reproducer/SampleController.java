package org.fxapps.reproducer;

import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Singleton;
import javafx.application.Platform;
import org.fxapps.reproducer.events.Event2;
import org.fxapps.reproducer.events.Event1;

import io.quarkiverse.fx.RunOnFxThread;
import io.quarkiverse.fx.views.FxView;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import javafx.scene.web.WebView;

@FxView
@Singleton
public class SampleController {

    @Inject
    MyService myService;

    @FXML
    private TextField txtInput;

    @FXML
    private TextArea txtOutput;

    @FXML
    public void onInputAction() {
        if (!txtInput.getText().isBlank()) {
            String txt = txtInput.getText();
            txtInput.clear();
            myService.sendEvent1(new Event1(txt));
        }
    }

    public void onEvent2(@ObservesAsync Event2 event2) {
        System.out.println("Received event 2, updating UI");
        Platform.runLater(() -> {
            txtOutput.setText(txtOutput.getText() + "\n" + event2.getText());
        });
    }


}