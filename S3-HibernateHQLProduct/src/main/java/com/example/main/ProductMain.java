package com.example.main;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.example.entity.Product;
import com.example.util.HibernateUtil;
public class ProductMain {
 public static void main(String[] args) {// Open Session
	 Session session = HibernateUtil.getSessionFactory().openSession();
	 Transaction tx = session.beginTransaction();
	 // Insert Products (run once, then you can comment this)
	 session.save(new Product("Laptop", "Electronics", 60000, 10));
	 session.save(new Product("Mouse", "Electronics", 500, 50));
	 session.save(new Product("Keyboard", "Electronics", 1500, 20));
	 session.save(new Product("Chair", "Furniture", 3500, 15));
	 session.save(new Product("Table", "Furniture", 8000, 5));
	 tx.commit();
	 // ===============================
	 // SORT BY PRICE (ASC)
	 // ===============================
	 Query<Product> q1 =
	 session.createQuery("FROM Product p ORDER BY p.price ASC",
	Product.class);
	 List<Product> list1 = q1.list();
	 System.out.println("\n--- Price Ascending ---");
	 list1.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));// ===============================
	 // SORT BY PRICE (DESC)
	 // ===============================
	 Query<Product> q2 =
	 session.createQuery("FROM Product p ORDER BY p.price DESC",
	Product.class);
	 System.out.println("\n--- Price Descending ---");
	 q2.list().forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
	 // ===============================
	 // SORT BY QUANTITY (DESC)
	 // ===============================
	 Query<Product> q3 =
	 session.createQuery("FROM Product p ORDER BY p.quantity DESC",
	Product.class);
	 System.out.println("\n--- Quantity High to Low ---");
	 q3.list().forEach(p -> System.out.println(p.getName() + " " + p.getQuantity()));
	 // ===============================
	 // PAGINATION
	 // ===============================
	 Query<Product> q4 =
	 session.createQuery("FROM Product", Product.class);
	 q4.setFirstResult(0);
	 q4.setMaxResults(3);
	 System.out.println("\n--- First 3 Products ---");
	 q4.list().forEach(p -> System.out.println(p.getName()));
	 Query<Product> q5 =
	 session.createQuery("FROM Product", Product.class);
	 q5.setFirstResult(3);
	 q5.setMaxResults(3);
	 System.out.println("\n--- Next 3 Products ---");
	 q5.list().forEach(p -> System.out.println(p.getName()));// ===============================
	 // AGGREGATE FUNCTIONS
	 // ===============================
	 Long total =
	 session.createQuery("SELECT COUNT(p) FROM Product p", Long.class)
	 .uniqueResult();
	 System.out.println("\nTotal Products: " + total);
	 Object[] minMax =
	 session.createQuery(
	 "SELECT MIN(p.price), MAX(p.price) FROM Product p",
	 Object[].class).uniqueResult();
	 System.out.println("Min Price: " + minMax[0] + ", Max Price: " + minMax[1]);// ===============================
	 // GROUP BY DESCRIPTION
	 // ===============================
	 List<Object[]> groupList =
	 session.createQuery(
	 "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description",
	 Object[].class).list();
	 System.out.println("\n--- Group By Description ---");
	 for (Object[] row : groupList) {
	 System.out.println(row[0] + " -> " + row[1]);
	 }
	 // ===============================
	 // WHERE + LIKE
	 // ===============================
	 List<Product> likeList =
	 session.createQuery(
	 "FROM Product p WHERE p.name LIKE 'L%'", Product.class).list();
	 System.out.println("\n--- Names starting with L ---");
	 likeList.forEach(p -> System.out.println(p.getName()));

	 // Names ending with p
	 List<Product> endWithList =
	 session.createQuery(
	 "FROM Product p WHERE p.name LIKE '%p'", Product.class)
	 .list();
	 System.out.println("\n--- Names ending with 'p' ---");
	 endWithList.forEach(p -> System.out.println(p.getName()));
	 // Names containing 'top'
	 List<Product> containList =
	 session.createQuery(
	 "FROM Product p WHERE p.name LIKE '%top%'", Product.class)
	 .list();
	 System.out.println("\n--- Names containing 'top' ---");containList.forEach(p -> System.out.println(p.getName()));
	 // Names with exact length = 5
	 List<Product> lengthList =
	 session.createQuery(
	 "FROM Product p WHERE LENGTH(p.name) = 5", Product.class)
	 .list();
	 System.out.println("\n--- Names with name length = 5 ---");
	 lengthList.forEach(p -> System.out.println(p.getName()));
	 // Close session
	 session.close();
	 System.out.println("\nSUCCESS");
	 }
	}