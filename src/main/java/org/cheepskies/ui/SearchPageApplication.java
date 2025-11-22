package org.cheepskies.ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchPageApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SearchPageApplication.class.getResource("/org/gui/cheepskies/search-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("searchPage");
        stage.setScene(scene);
        stage.show();
    }
}

