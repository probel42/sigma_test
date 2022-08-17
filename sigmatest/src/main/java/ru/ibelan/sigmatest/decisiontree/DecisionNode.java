package ru.ibelan.sigmatest.decisiontree;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DecisionNode extends Node {
	private String question;
	@JsonProperty("false")
	private Node falseNode;
	@JsonProperty("true")
	private Node trueNode;
}
