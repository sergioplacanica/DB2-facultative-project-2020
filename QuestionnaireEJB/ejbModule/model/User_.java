package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-25T13:19:16.551+0100")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> userID;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> username;
	public static volatile ListAttribute<User, Accesstime> accesstimes;
	public static volatile ListAttribute<User, Answer> answers;
	public static volatile ListAttribute<User, Questionnaire> questionnaires;
	public static volatile ListAttribute<User, Review> reviews;
}
