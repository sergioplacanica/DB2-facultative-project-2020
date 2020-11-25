package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-25T13:19:15.976+0100")
@StaticMetamodel(Marketingquestion.class)
public class Marketingquestion_ {
	public static volatile SingularAttribute<Marketingquestion, Integer> questionID;
	public static volatile SingularAttribute<Marketingquestion, String> description;
	public static volatile ListAttribute<Marketingquestion, Answer> answers;
	public static volatile ListAttribute<Marketingquestion, Questionnaire> questionnaires;
}
