package ru.ibelan.sigmatest;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.ibelan.sigmatest.controllers.Controller;
import ru.ibelan.sigmatest.controllers.ScreenController;
import ru.ibelan.sigmatest.decisionmaker.DecisionMaker;
import ru.ibelan.sigmatest.decisionmaker.TreeDecisionMaker;
import ru.ibelan.sigmatest.decisiontree.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class SigmaTest extends Application {
	public static final String QUESTION_PANE = "question";
	public static final String GUESS_PANE = "guess";
	public static final String ADD_DATA_PANE = "addData";

	private static final String TITLE = "Sigma Test";
	private static final String INIT_JSON = "data/init.json";
	private static final String QUESTION_PANE_FILE = "fxml/questionPane.fxml";
	private static final String GUESS_PANE_FILE = "fxml/guessPane.fxml";
	private static final String ADD_DATA_PANE_FILE = "fxml/addDataPane.fxml";
	private static final String STYLES_FILE = "styles/.sass-cache/main.css";

	private static final int APP_WIDTH = 800;
	private static final int APP_HEIGHT = 400;

	@Override
	public void start(Stage stage) throws IOException {
		Scene scene = new Scene(new StackPane());
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(STYLES_FILE)).toExternalForm());
		stage.setScene(scene);

		ScreenController screenController = new ScreenController(scene);
		DecisionMaker<Node> decisionMaker = initDecisionMaker();
		loadPane(QUESTION_PANE, QUESTION_PANE_FILE, screenController, decisionMaker);
		loadPane(GUESS_PANE, GUESS_PANE_FILE, screenController, decisionMaker);
		loadPane(ADD_DATA_PANE, ADD_DATA_PANE_FILE, screenController, decisionMaker);
		screenController.activate(QUESTION_PANE);

		stage.setTitle(TITLE);
		stage.setMinWidth(APP_WIDTH);
		stage.setMaxWidth(APP_WIDTH);
		stage.setMinHeight(APP_HEIGHT);
		stage.setMaxHeight(APP_HEIGHT);
		stage.show();
	}

	private void loadPane(String name, String fileName, ScreenController screenController, DecisionMaker<Node> decisionMaker) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
		Pane pane = loader.load();
		Controller controller = loader.getController();
		controller.setScreenController(screenController); // DI не нужОн :)
		controller.setDecisionMaker(decisionMaker);
		screenController.addPane(name, pane, controller);
	}

	private DecisionMaker<Node> initDecisionMaker() {
		try (InputStream stream = getClass().getResourceAsStream(INIT_JSON);
			 InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(stream), StandardCharsets.UTF_8);
			 BufferedReader bufferedReader = new BufferedReader(reader)
		) {
			Node tree = new ObjectMapper().readerFor(Node.class).readValue(bufferedReader);
			DecisionMaker<Node> decisionMaker = new TreeDecisionMaker();
			decisionMaker.initData(tree);
			return decisionMaker;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
