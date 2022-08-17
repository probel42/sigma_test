package ru.ibelan.sigmatest.decisionmaker;

/**
 * Класс принимающий решения о следующем вопросе
 *
 * @param <T> инициализирующие данные
 */
public interface DecisionMaker<T> {
	void initData(T init);

	T getData();

	boolean hasDecision();

	String getDecision();

	String getNextQuestion();

	void setAnswer(boolean answer);

	/**
	 * Добавить субъект (животное)
	 *
	 * @param subject  субъект
	 * @param question вопрос, который отличает его от субъекта в текущем узле
	 */
	void addSubject(String subject, String question);

	void reset();
}
