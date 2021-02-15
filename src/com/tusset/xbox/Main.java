/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tusset.xbox;

import com.tusset.xbox.util.Util;
import java.net.URL;
import java.util.HashMap;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/**
 *
 * @author manue
 */
public class Main extends Application {
    
    private JSObject javascriptConnector;
    
    private JavaConnector javaConnector = new JavaConnector();
    
    @Override
    public void start(Stage stage) throws Exception {
        
        WebView webView = new WebView();
        URL url = Main.class.getResource("page/index.html");
        final WebEngine webEngine = webView.getEngine();
        
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", javaConnector);
                javascriptConnector = (JSObject) webEngine.executeScript("getJsConnector()");
            }
        });
        
        Scene scene = new Scene(webView);
        
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setMaximized(true);
        stage.setResizable(false);
        
        HashMap<String, Double> hash = Util.getDimensionScreen();
        
        stage.setWidth(hash.get("width"));
        stage.setHeight(hash.get("height"));
        stage.show();
        
        webEngine.load(url.toString());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public class JavaConnector {
        public void toLowerCase(String value) {
            javascriptConnector.call("showResult", value.toLowerCase());
        }
    }
}
