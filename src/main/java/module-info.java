module com.example.portofshipmen {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens assets.textures;
    opens com.example.portofshipmen.entities;
    opens com.example.portofshipmen to javafx.fxml;
    exports com.example.portofshipmen;
    exports com.example.portofshipmen.controllers;
    opens com.example.portofshipmen.controllers to javafx.fxml;
}