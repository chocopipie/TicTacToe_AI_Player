module com.networkdemo.ai_player {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.networkdemo to javafx.fxml;
    exports com.networkdemo;
}