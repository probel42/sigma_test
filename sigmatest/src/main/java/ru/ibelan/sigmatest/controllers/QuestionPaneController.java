package ru.ibelan.sigmatest.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.commons.lang3.StringUtils;
import ru.ibelan.sigmatest.SigmaTest;

import java.util.function.Function;

public class QuestionPaneController extends Controller {
	public Label questionLabel;
	public Button yesButton;
	public Button noButton;

	private final Function<String, String> questionTransform = s -> StringUtils.capitalize(s) + " ?";

	@Override
	public void activate() {
		updateQuestion();
		yesButton.setDisable(false);
		noButton.setDisable(false);
	}

	public void onClickYes() {
		decisionMaker.setAnswer(true);
		checkDecision();
	}

	public void onClickNo() {
		decisionMaker.setAnswer(false);
		checkDecision();
	}

	private void checkDecision() {
		if (decisionMaker.hasDecision()) {
			screenController.activate(SigmaTest.GUESS_PANE);
		} else {
			updateQuestion();
		}
	}

	private void updateQuestion() {
		questionLabel.setText(questionTransform.apply(decisionMaker.getNextQuestion()));
	}
}
