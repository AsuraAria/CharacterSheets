package spinnerdemo;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Defines the Spinner Demo application for JavaFX.
 * @author Christoph Nahr
 * @version 1.0.2
 */
public class SpinnerDemo extends Application {
    /**
     * Starts the {@link SpinnerDemo} application.
     * @param primaryStage the primary {@link Stage} for the application
     */
    @Override
    public void start(Stage primaryStage) {
        int row = -1;

        final Label intCustom = new Label("Custom Converter");
        GridPane.setRowIndex(intCustom, ++row);
        GridPane.setColumnIndex(intCustom, 0);
        
        final Spinner<Integer> intCustomSpinner = new Spinner<>(0, 100, 0, 1);
        initSpinner(intCustomSpinner);
        IntegerStringConverter.createFor(intCustomSpinner);
        GridPane.setRowIndex(intCustomSpinner, row);
        GridPane.setColumnIndex(intCustomSpinner, 1);

        final Label exception = new Label();
        exception.setMinHeight(36); // reserve space for two lines
        exception.setTextFill(Color.RED);
        exception.setWrapText(true);
        GridPane.setRowIndex(exception, ++row);
        GridPane.setColumnIndex(exception, 0);
        GridPane.setColumnSpan(exception, 3);
        
        final GridPane root = new GridPane();
        root.setHgap(8);
        root.setVgap(8);
        root.setPadding(new Insets(8));
        root.getChildren().addAll(
                intCustom, intCustomSpinner,exception);

        Thread.currentThread().setUncaughtExceptionHandler((t, e) -> {
            exception.setText(e.toString());
            e.printStackTrace();
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * Launches the {@link SpinnerDemo} application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static void initSpinner(Spinner<?> spinner) {
        spinner.getEditor().setAlignment(Pos.CENTER_RIGHT);
        spinner.setEditable(true);
        spinner.setPrefWidth(80);
    }

    private static void initOutput(Label output) {
        output.setAlignment(Pos.CENTER);
        output.setPrefWidth(80);
    }
}
