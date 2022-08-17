package ru.ibelan.sigmatest.decisiontree;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type"
)
@JsonSubTypes({
		@JsonSubTypes.Type(value = DecisionNode.class, name = "question"),
		@JsonSubTypes.Type(value = Leaf.class, name = "subject")
})
@JsonIgnoreProperties(value = {"parent"})
public abstract class Node {
	@Getter
	@Setter
	public Node parent;

	public String getQuestion() {
		return null;
	}

	public String getSubject() {
		return null;
	}

	public Node getFalseNode() {
		return null;
	}

	public Node getTrueNode() {
		return null;
	}

	public void setFalseNode(Node node) {
	}

	public void setTrueNode(Node node) {
	}
}
