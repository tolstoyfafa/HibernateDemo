package com.fafa.company.control;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.fafa.company.Player;

public class HibernateMain {

	public static void main(String[] args) {
		
		try {
			// hibernate session configuration 
			Configuration configuration = new Configuration().configure();
			configuration.addAnnotatedClass(Player.class);
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties());
			SessionFactory factory = configuration.buildSessionFactory(builder.build());
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			// create action 
			Player player = new Player(1, "toto", "tata", 0);
			Player player1 = new Player(2, "mama", "paris", 0);
			session.save(player);
			session.save(player1);
			// recover action 
			Player playerGet = session.get(Player.class, 1);
			System.out.println(playerGet);
			// update action 
			playerGet.setAge(555);
			session.save(playerGet);
			// delete action 
			session.delete(player1);
			tx.commit();
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
