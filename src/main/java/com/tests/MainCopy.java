package com.tests;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import com.entities.Adress;
import com.entities.Event;
import com.entities.User;

public class MainCopy {

	public static void main(String[] args) {

		// Lancement des tests successifs
		try {
			clean();
			testCreate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void testCreate() throws Exception {
		// Création des objets à rendre persistants
		Event e = new Event("Titre de l'évènement", "description", true);
		Adress a = new Adress("Nom de l'adresse", "24 rue des cerisiers",
				"75001", "Paris");

		User u = new User("mistra", "mistra(a)mistra.fr", "formation");
		User u2 = new User("John Doe", "john.doe(a)mistra.fr", "password");

		Event e2 = new Event("Event sans adresse", "", false);

		// Liens d'associations pour l’Event e
		e.setAdress(a);
		e.setUser(u);
		// Liens d'associations pour l'Event e2
		e2.setUser(u);
		// Liste des deux Event de l’User u
		ArrayList<Event> eventList = new ArrayList<Event>();
		eventList.add(e);
		eventList.add(e2);
		u.setEventList(eventList);

		// Enregistrements
		new TransactionOperation() {

			@Override
			protected void operationBrut(Session session, Object... object) {
				session.persist((Event) object[0]);
				session.persist((Event) object[1]);
				session.persist((User) object[2]);
			}
		}.operation(e, e2, u2);

		// Affichage du contenu des tables
		printEvents();
		printAddresses();
		printUsers();
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

	public static void clean() throws Exception {
		new TransactionOperation() {

			@Override
			protected void operationBrut(Session session, Object... object) {
				System.out
						.println("--------- Vide la table events : ---------");
				Query q = session.createQuery("delete Event");

				q.executeUpdate();
				q = session.createQuery("delete Adress");
				q.executeUpdate();
				q = session.createQuery("delete User");
				q.executeUpdate();
			}
		}.operation();
	}

	public static void printUsers() throws Exception {
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

}