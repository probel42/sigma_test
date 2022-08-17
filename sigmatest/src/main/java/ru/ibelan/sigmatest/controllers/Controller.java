package ru.ibelan.sigmatest.controllers;

import javafx.fxml.Initializable;
import lombok.Setter;
import ru.ibelan.sigmatest.decisionmaker.DecisionMaker;
import ru.ibelan.sigmatest.decisiontree.Node;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * JavaFX Controller
 */
@Setter
public abstract class Controller implements Initializable {
	protected ScreenController screenController;
	protected DecisionMaker<Node> decisionMaker;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void activate() {
	}

	public void deactivate() {
	}
}
