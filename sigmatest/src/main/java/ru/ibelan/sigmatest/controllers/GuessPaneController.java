package ru.ibelan.sigmatest.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ru.ibelan.sigmatest.SigmaTest;

public class GuessPaneController extends Controller {
	public Label guessLabel;
	public Button okButton;
	public Button notCorrectButton;

	@Override
	public void activate() {
		String decision = decisionMaker.getDecision();
		guessLabel.setText(String.format("Это \"%s\" !", decision));
		notCorrectButton.setText(String.format("Нет, это не \"%s\"", decision));
	}

	public void onClickOk() {
		decisionMaker.reset();
		screenController.activate(SigmaTest.QUESTION_PANE);
	}

	public void onClickNo() {
		screenController.activate(SigmaTest.ADD_DATA_PANE);
	}
}
