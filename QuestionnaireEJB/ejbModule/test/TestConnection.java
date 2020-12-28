package test;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import model.Marketingquestion;
import model.Offensiveword;
import model.User;
import services.UserService;

public class TestConnection {
    public static void main(String[] args) throws ParseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuestionnaireEJB");
        EntityManager em = emf.createEntityManager();
        UserService usrService = new UserService();
        TestService testService = new TestService(em);

        //find an User by is id
        em.getTransaction().begin();
        
        User user = testService.findUser(1);
        System.out.println("found User:" + user);
        Offensiveword offensiveWord=new Offensiveword();
        offensiveWord.setWord("culo");
        List<User> questions=em.createQuery("SELECT u FROM User u", User.class).getResultList();
        for (User q : questions) {
            System.out.println(q.getPassword());
        } 
        /*User user2 = usrService.checkCredentials("ciccio", "pass");
        System.out.println("tutto fatto"+user2);*/
        //em.persist(user);
        //em.getTransaction().commit();
        /*try {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		    Date today = (Date) Calendar.getInstance().getTime(); 
		    String reportDate = df.format(today);
			Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(reportDate);
			System.out.println(date1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
        /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String today = dtf.format(now);
		Date startDate = (Date) dtf.parse(today);
		System.out.println(startDate);*/
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //LocalDateTime now = LocalDateTime.now();
        //String current_date = sdf.format(now);
        //System.out.println(current_date);
		//Date startDate = (Date) sdf.parse(current_date);
		//System.out.println(startDate);
        /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String current_date = (String) dtf.format(now);
        System.out.println(current_date);
        Date startDate = (Date) dtf.parse(current_date);
        System.out.println(startDate);
       
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String current_date = (String) dtf.format(now);
        Date startDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(current_date).getTime());
        System.out.println(startDate);
*/
        List <Offensiveword> offensive = new ArrayList<Offensiveword>();
		List <String> off = new ArrayList<String>();
		List <String> list = new ArrayList<String>();
		String [] splitted_answ = null;
		List <String> answers = new ArrayList<String>();
		answers.add("Ciao questa è la prima cazzo");
		answers.add("Ciao questa è la seconda risposta");
		answers.add("Ciao questa è la terza risposta");

		
		offensive = em.createQuery("SELECT u FROM Offensiveword u",Offensiveword.class).getResultList();
		//creazione List<String> di offensive word
		for (Offensiveword x: offensive) {
			off.add(x.getWord());
		}
		
		for(String x: off) {
			System.out.println(x);
		}
		
		for (String x: answers) {
			splitted_answ = x.split(" ");
			System.out.println(splitted_answ);
			for(String i: splitted_answ) {
				list.add(i);
			}				
		}

		for(String x: list) {
			System.out.println(x);
		}
		
		for(String x: off) {
			if(list.contains(x))
				System.out.println("Conteneva almeno una parolaccia");
		}

        
     
        
        
    }
}