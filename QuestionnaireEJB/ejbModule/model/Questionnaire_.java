package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-25T13:19:16.521+0100")
@StaticMetamodel(Questionnaire.class)
public class Questionnaire_ {
	public static volatile SingularAttribute<Questionnaire, Integer> questionnaireID;
	public static volatile SingularAttribute<Questionnaire, Byte> age;
	public static volatile SingularAttribute<Questionnaire, String> expertise_level;
	public static volatile SingularAttribute<Questionnaire, String> sex;
	public static volatile ListAttribute<Questionnaire, Answer> answers;
	public static volatile ListAttribute<Questionnaire, Marketingquestion> marketingquestions;
	public static volatile SingularAttribute<Questionnaire, Product> product;
	public static volatile SingularAttribute<Questionnaire, User> user;
}
