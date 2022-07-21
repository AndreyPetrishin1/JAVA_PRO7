package jpa1;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPAFlat");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add Float");
                    System.out.println("2: delete Float");
                    System.out.println("3: change Float");
                    System.out.println("4: view Float all");
                    System.out.println("5: view Float area");
                    System.out.println("6: view Float square");
                    System.out.println("7: view Float quantityRoom");
                    System.out.println("8: view Float price");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addFloat(sc);
                            break;
                        case "2":
                            deleteFlat(sc);
                            break;
                        case "3":
                            changeFlat(sc);
                            break;
                        case "4":
                            viewFlat();
                            break;
                        case "5":
                            viewFlat1(sc);
                            break;
                        case "6":
                            viewFlat2(sc);
                            break;
                        case "7":
                            viewFlat3(sc);
                            break;
                        case "8":
                            viewFlat4(sc);
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void addFloat(Scanner sc) {

        System.out.print("Enter area: ");
        String area = sc.nextLine();

        System.out.print("Enter square: ");
        String square = sc.nextLine();

        System.out.print("Enter quantityRoom: ");
        String quantityRoomS = sc.nextLine();
        int quantityRoom = Integer.parseInt(quantityRoomS);

        System.out.print("Enter price: ");
        String priceS = sc.nextLine();
        long price = Long.parseLong(priceS);


        em.getTransaction().begin();
        try {
            Flat f = new Flat(area, square, quantityRoom, price);
            em.persist(f);
            em.getTransaction().commit();

            System.out.println(f.getId());
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void deleteFlat(Scanner sc) {
        System.out.print("Enter client id: ");
        String idS = sc.nextLine();
        long id = Long.parseLong(idS);


        Flat f = em.getReference(Flat.class, id);
        if (f == null) {
            System.out.println("Client not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(f);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeFlat(Scanner sc) {
        System.out.print("Enter area: ");
        String area = sc.nextLine();

        System.out.print("Enter square: ");
        String square = sc.nextLine();

        System.out.print("Enter quantityRoom: ");
        String quantityRoomS = sc.nextLine();
        int quantityRoom = Integer.parseInt(quantityRoomS);


        System.out.print("Enter price: ");
        String priceS = sc.nextLine();
        long price = Long.parseLong(priceS);

        Flat f = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.area = :area", Flat.class);
            query.setParameter("area", area);
            f = (Flat) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("float not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        ///........

        em.getTransaction().begin();
        try {
            f.setPrice(price);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void viewFlat() {
        Query query = em.createQuery(
                "SELECT f FROM Flat f", Flat.class);
        List<Flat> list = (List<Flat>) query.getResultList();

        for (Flat f : list)
            System.out.println(f);
    }
    private static void viewFlat1(Scanner sc) {


        System.out.print("Enter area: ");
        String area = sc.nextLine();
        Query query = em.createQuery(
                "SELECT f FROM Flat f WHERE f.area = :area", Flat.class);

        query.setParameter("area", area);

        List<Flat> list = (List<Flat>) query.getResultList();

        for (Flat f : list)
            System.out.println(f);
    }

    private static void viewFlat2(Scanner sc) {
        System.out.print("Enter square: ");
        String square = sc.nextLine();

        Query query = em.createQuery(
                "SELECT f FROM Flat f WHERE f.square = :square", Flat.class);

        query.setParameter("square", square);

        List<Flat> list = (List<Flat>) query.getResultList();

        for (Flat f : list)
            System.out.println(f);
    }

    private static void viewFlat3(Scanner sc) {
        System.out.print("Enter quantityRoom: ");
        String quantityRoomS = sc.nextLine();
        int quantityRoom = Integer.parseInt(quantityRoomS);

        Query query = em.createQuery(
                "SELECT f FROM Flat f WHERE f.quantityRoom = :quantityRoom", Flat.class);

        query.setParameter("quantityRoom", quantityRoom);

        List<Flat> list = (List<Flat>) query.getResultList();

        for (Flat f : list)
            System.out.println(f);
    }

    private static void viewFlat4(Scanner sc) {
        System.out.print("Enter price: ");
        String priceS = sc.nextLine();
        long price = Long.parseLong(priceS);

        Query query = em.createQuery(
                "SELECT f FROM Flat f WHERE f.price = :price", Flat.class);

        query.setParameter("price", price);

        List<Flat> list = (List<Flat>) query.getResultList();

        for (Flat f : list)
            System.out.println(f);
    }

}


