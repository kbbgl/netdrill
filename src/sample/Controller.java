package sample;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Controller {

    public Button startBtn;
    public TextArea outputTextArea;


    public void startProcess() throws IOException {

        startTask();

    }

    private void startTask(){

        Runnable task = this::runTask;

        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();

    }

    private void runTask() {

        ProcessBuilder builder = new ProcessBuilder("speedtest-cli");
        builder.redirectErrorStream(true);
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        Process process;
        try {
            process = builder.start();

            InputStream inputStream;
            inputStream = process.getInputStream();
            BufferedReader reader = null;
            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));
            }

            String line;
            try {
                if (reader != null) {
                    while ((line = reader.readLine()) != null){

                        String output = outputTextArea.getText() + "\n" + line;

                        Platform.runLater(() -> outputTextArea.setText(output));

                    }
                }
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot run speedtest-cli", ButtonType.CLOSE);
                alert.show();
            }

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot start process", ButtonType.CLOSE);
            alert.show();
        }
    }

}
