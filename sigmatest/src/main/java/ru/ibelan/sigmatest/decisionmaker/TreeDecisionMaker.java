package ru.ibelan.sigmatest.decisionmaker;

import ru.ibelan.sigmatest.decisiontree.DecisionNode;
import ru.ibelan.sigmatest.decisiontree.Leaf;
import ru.ibelan.sigmatest.decisiontree.Node;

public class TreeDecisionMaker implements DecisionMaker<Node> {
	private Node treeRoot;
	private Node currentNode;

	@Override
	public void initData(Node tree) {
		TreeDecisionMaker.fillParents(tree, null);
		treeRoot = tree;
		currentNode = treeRoot;
	}

	private static void fillParents(Node node, Node parent) {
		node.setParent(parent);
		if (node.getFalseNode() != null) {
			TreeDecisionMaker.fillParents(node.getFalseNode(), node);
		}
		if (node.getTrueNode() != null) {
			TreeDecisionMaker.fillParents(node.getTrueNode(), node);
		}
	}

	@Override
	public Node getData() {
		return treeRoot;
	}

	@Override
	public boolean hasDecision() {
		return currentNode.getSubject() != null;
	}

	@Override
	public String getDecision() {
		return currentNode.getSubject();
	}

	@Override
	public String getNextQuestion() {
		return currentNode.getQuestion();
	}

	@Override
	public void setAnswer(boolean answer) {
		currentNode = answer ? currentNode.getTrueNode() : currentNode.getFalseNode();
	}

	@Override
	public void addSubject(String subject, String question) {
		if (currentNode.getParent() != null) {
			// создадим новый лист
			Leaf leaf = new Leaf();
			leaf.setSubject(subject);

			// и новый узел для выбора
			DecisionNode decisionNode = new DecisionNode();
			decisionNode.setQuestion(question);
			decisionNode.setParent(currentNode.getParent());
			decisionNode.setFalseNode(currentNode);
			decisionNode.setTrueNode(leaf);

			if (currentNode == currentNode.getParent().getFalseNode()) {
				currentNode.getParent().setFalseNode(decisionNode);
			} else if (currentNode == currentNode.getParent().getTrueNode()) {
				currentNode.getParent().setTrueNode(decisionNode);
			}
			currentNode.setParent(decisionNode);
			currentNode = leaf;
		}
	}

	@Override
	public void reset() {
		currentNode = treeRoot;
	}
}
