package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.User;


public class InsertUser {
	public static void main(String[] args)	{
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
	      
	      EntityManager entitymanager = emfactory.createEntityManager( );
	      entitymanager.getTransaction( ).begin( );

	      User user = new User( ); 
	      user.setAdmin(1);
	      user.setBlocked(0);
	      user.setEmail("lorenzo.amata@mail.polimi.it");
	      user.setPassword("ciaociao");
	      user.setUsername("username");
	      
	      entitymanager.persist( user );
	      entitymanager.getTransaction( ).commit( );

	      entitymanager.close( );
	      emfactory.close( );
	   }
	}
