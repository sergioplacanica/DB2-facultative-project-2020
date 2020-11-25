package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-25T13:19:16.500+0100")
@StaticMetamodel(Product.class)
public class Product_ {
	public static volatile SingularAttribute<Product, Integer> productID;
	public static volatile SingularAttribute<Product, String> image;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile ListAttribute<Product, Questionnaire> questionnaires;
	public static volatile ListAttribute<Product, Review> reviews;
}
