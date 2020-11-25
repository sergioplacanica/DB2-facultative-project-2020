package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-25T13:19:16.462+0100")
@StaticMetamodel(Answer.class)
public class Answer_ {
	public static volatile SingularAttribute<Answer, Integer> answer_ID;
	public static volatile SingularAttribute<Answer, Marketingquestion> marketingquestion;
	public static volatile SingularAttribute<Answer, Questionnaire> questionnaire;
	public static volatile SingularAttribute<Answer, User> user;
}
