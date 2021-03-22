package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.Boss;
import application.Drink;
import application.Food;
import application.History;
import application.Main;
import application.Waiter;

public class DBHandler extends Configs {
	private static Connection con;

	private static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager
					.getConnection("jdbc:sqlserver:" + dbhost + ";database=" + dbname + ";integratedSecurity=true;");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static void fillUserList() {
		try {
			getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Users");
			while (rs.next()) {
				if (rs.getString("Quality").equals("Admin")) {
					Main.bossList.add(new Boss(rs.getString("Username"), rs.getString("Password")));
				} else {
					Main.waiterList.add(new Waiter(rs.getString("Username"), rs.getString("Password")));
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void fillProductLists() {
		try {
			getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Products");
			while (rs.next()) {
				if (rs.getString("Product").equals("Food")) {
					Main.foodList.add(new Food(rs.getString("Name"), rs.getString("Price")));
				} else {
					Main.drinkList.add(new Drink(rs.getString("Name"), rs.getString("Price"), rs.getString("Brand")));
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void fillHistoryLists() {
		try {
			getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from History");
			while (rs.next()) {
				Main.historyList
						.add(new History(rs.getString("Date"), rs.getString("TableNumber"), rs.getString("Gain")));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void setDBHistory(String date, String tableName, String gain) {
		try {
			getConnection();
			PreparedStatement stmt = con
					.prepareStatement("insert into History (Date, TableNumber, Gain) values (?, ?, ?)");
			stmt.setString(1, date);
			stmt.setString(2, tableName);
			stmt.setString(3, gain);
			stmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void setDBProduct(String category, String name, String price, String brand) {
		try {
			getConnection();
			PreparedStatement stmt = con
					.prepareStatement("insert into Products (Product, Name, Price, Brand) values (?, ?, ?, ?)");
			stmt.setString(1, category);
			stmt.setString(2, name);
			stmt.setString(3, price);
			stmt.setString(4, brand);
			stmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deleteRowDBProduct(String name) {
		try {
			getConnection();
			PreparedStatement st = con.prepareStatement("DELETE FROM Products WHERE Name = ?");
			st.setString(1, name);
			st.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void setDBPUser(String quality, String username, String password) {
		try {
			getConnection();
			PreparedStatement stmt = con
					.prepareStatement("insert into Users (Quality, Username, Password) values (?, ?, ?)");
			stmt.setString(1, quality);
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deleteRowDBUser(String username) {
		try {
			getConnection();
			PreparedStatement st = con.prepareStatement("DELETE FROM Users WHERE Username = ?");
			st.setString(1, username);
			st.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}