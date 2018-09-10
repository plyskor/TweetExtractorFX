/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class FilterCard extends StackPane {

    private final Label label;

    public FilterCard(){
        label = new Label();
        getChildren().add(label);
    }

    public void setText(String text) {
        label.setText(text);
    }
}