package com.tests;

import java.util.ArrayList;

import org.h2.command.dml.Delete;
import org.hibernate.Query;
import org.hibernate.Session;

import com.entities.Adress;
import com.entities.Event;
import com.entities.User;

public class Main {

	public static void main(String[] args) {

		// Lancement des tests successifs
		try {
			testCreate();
			testDelete();
			printAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void test() throws Exception{
		// Creation des données
		System.out.println("Creation des données. Attendu : un event et un user");
		testCreate();
		
		
	
		
	}

	private static void testCreate() throws Exception {
		// Création des objets à rendre persistants
		Event e = new Event("Titre de l'évènement", "description", true);
		Adress a = new Adress("Nom de l'adresse", "24 rue des cerisiers",
				"75001", "Paris");

		User u = new User("mistra", "mistra(a)mistra.fr", "formation");
		e.setUser(u);
		
		
		// Liste des deux Event de l’User u
		ArrayList<Event> eventList = new ArrayList<Event>();
		eventList.add(e);
	
		u.setEventList(eventList);

		// Enregistrements
		new TransactionOperation() {

			@Override
			protected void operationBrut(Session session, Object... object) {
	
				session.persist((Event) object[0]);
			}
		}.operation(e);

	}

	// Affiche le contenu de la table EVENTS
	private static void printEvents() throws Exception {

		System.out.println("[Events]");

		new TransactionOperation() {

			@Override
			protected void operationBrut(Session session, Object... object) {
				// Création de la requête
				Query q = session.createQuery("from Event");
				ArrayList<Event> list = (ArrayList<Event>) q.list();
				// Affichage de la liste de résultats
				for (Event e : list) {
					System.out.println(e.toString());
				}
			}
		}.operation();

	}

	// Affiche le contenu de la table ADDRESSES
	private static void printAddresses() throws Exception {
		System.out.println("[Addresses]");

		new TransactionOperation() {

			@Override
			protected void operationBrut(Session session, Object... object) {
				// Création de la requête
				Query q = session.createQuery("from Adress");
				ArrayList<Adress> list = (ArrayList<Adress>) q.list();

				// Affichage de la liste de résultats
				for (Adress a : list) {
					System.out.println(a);
				}
			}
		}.operation();

	}
	
	private static void testDelete()throws Exception{
		new TransactionOperation() {
			
			@Override
			protected void operationBrut(Session session, Object... object)
					throws Exception {
				Query q = session.createQuery("delete User");
				q.executeUpdate();
//				User u = (User)q.uniqueResult();
//				session.delete(u);
				
			}
		}.operation();
	}

	public static void clean() throws Exception {
		new TransactionOperation() {

			@Override
			protected void operationBrut(Session session, Object... object) {
				System.out
				.println("--------- Vide la table event : ---------");
				Query q = session.createQuery("delete Event");
				q.executeUpdate();
				
				
				System.out
						.println("--------- Vide la table user : ---------");
				q = session.createQuery("delete User");
				
				q.executeUpdate();
				
				System.out
				.println("--------- Vide la table adress : ---------");
				q = session.createQuery("delete Adress");
				q.executeUpdate();
				
			}
		}.operation();
	}

	public static void printUsers() throws Exception {
		System.out.println("[Users]");
		new TransactionOperation() {

			@Override
			protected void operationBrut(Session session, Object... object) {
				Query q = session.createQuery("from User");
				ArrayList<User> users = (ArrayList<User>) q.list();

				for (User user : users) {
					System.out.println(user);
				}
			}
		}.operation();
	}
	
	public static void printAll() throws Exception{
		
		new TransactionOperation() {
			@Override
			protected void operationBrut(Session session, Object... object) throws Exception {
				printUsers();
				printEvents();
			}
		}.operation();
	}

}