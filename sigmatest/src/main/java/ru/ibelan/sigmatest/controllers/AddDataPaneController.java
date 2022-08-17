package ru.ibelan.sigmatest.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import ru.ibelan.sigmatest.SigmaTest;

public class AddDataPaneController extends Controller {
	public Label topLabel;
	public TextField answerField;
	public Button submitButton;

	private String subject;
	private String question;

	@Override
	public void activate() {
		topLabel.setText("Хмм.. а кто это?");
	}

	public void onClickSubmit() {
		if (StringUtils.isNotEmpty(answerField.getText())) {
			if (subject == null) {
				subject = answerField.getText();
				topLabel.setText(String.format("Чем \"%s\" отличается от \"%s\"", subject, decisionMaker.getDecision()));
				answerField.clear();
				answerField.requestFocus();
			} else {
				question = answerField.getText();
				decisionMaker.addSubject(subject, question);
				subject = null;
				question = null;
				answerField.clear();
				decisionMaker.reset();
				screenController.activate(SigmaTest.QUESTION_PANE);
			}
		}
	}
}
