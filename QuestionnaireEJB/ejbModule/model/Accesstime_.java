package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-25T13:19:15.973+0100")
@StaticMetamodel(Accesstime.class)
public class Accesstime_ {
	public static volatile SingularAttribute<Accesstime, Date> access_time;
	public static volatile ListAttribute<Accesstime, User> users;
}
