package org.example;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class chatController {
    private final VBox chatBox = new VBox(5);
    private TextArea textArea = new TextArea();
    private List<Label> messages = new ArrayList<>();
    private ScrollPane container = new ScrollPane();

    public void test() {
        container.setContent(chatBox);


        // when button send
            Label label = new Label();
            label.setText(textArea.getText());
            messages.add(label);
        //

        for (int i = 0; i < messages.size(); i++) {
            chatBox.getChildren().add(messages.get(i));
        }
    }
}
