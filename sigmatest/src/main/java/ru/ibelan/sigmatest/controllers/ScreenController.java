package ru.ibelan.sigmatest.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ScreenController {
	private final Map<String, Pane> panes = new HashMap<>();
	private final Map<String, Controller> controllers = new HashMap<>();
	private final Scene scene;
	private String current;

	public void addPane(String name, Pane pane, Controller controller) {
		panes.put(name, pane);
		controllers.put(name, controller);
	}

	public void activate(String name) {
		if (current != null) {
			controllers.get(current).deactivate();
		}
		scene.setRoot(panes.get(name));
		controllers.get(name).activate();
		current = name;
	}
}
