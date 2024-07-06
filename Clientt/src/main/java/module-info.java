module Lind.A {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires javafx.swing;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires commons.validator;
    requires json;
    requires gson;
    requires sqlite.jdbc;
    requires java.sql;
    requires jdk.httpserver;
    requires mysql.connector.java;

    opens org.example to javafx.fxml , com.fasterxml.jackson.databind;
    exports org.example;
    //exports com.bouncy_mehdich.Client.controller;
    //opens com.bouncy_mehdich.Client.controller;

}