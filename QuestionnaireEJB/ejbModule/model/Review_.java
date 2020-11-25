package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-25T13:19:16.537+0100")
@StaticMetamodel(Review.class)
public class Review_ {
	public static volatile SingularAttribute<Review, Integer> reviewID;
	public static volatile SingularAttribute<Review, Product> product;
	public static volatile SingularAttribute<Review, User> user;
}
