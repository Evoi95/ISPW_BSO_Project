module ISPW_BSO_Project {
	exports boundary;
	exports controller;
	exports database;
	exports entity.users.singelton;
	exports entity.factorybook;
	exports entity;

	requires itextpdf;
	requires java.desktop;
	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires mybatis;

	
	
	opens boundary to javafx.graphics,javafx.base,javafx.controls,javafx.fxml;
	opens database to javafx.graphics,javafx.base,javafx.controls,javafx.fxml,java.sql;
}	