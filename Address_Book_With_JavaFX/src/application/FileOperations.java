package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperations {
	public static void readFromTxt(ArrayList<Person> people) {
		try {
			File myObj = new File("AddressDatas.txt");
			Scanner myReader = new Scanner(myObj);
			myReader.nextLine();
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				createObjects(people, data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	private static void createObjects(ArrayList<Person> people, String strPerson) {
		String[] dividedInformation = strPerson.split("/");
		people.add(new Person(Integer.parseInt(dividedInformation[0]), dividedInformation[1], dividedInformation[2],
				dividedInformation[3], dividedInformation[4], dividedInformation[5]));
	}
	
	public static void writeToFile(ArrayList<Person> people) {
		try {
			FileWriter myWriter = new FileWriter("AddressDatas.txt", true);
			int i = people.size() - 1;
			myWriter.append(
					"\n" + people.get(i).getId() + "/" + people.get(i).getName() + "/" + people.get(i).getStreet() + "/"
							+ people.get(i).getCity() + "/" + people.get(i).getGender() + "/" + people.get(i).getZip());
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static void updateToFile(ArrayList<Person> people) {
		try {
			FileWriter myWriter = new FileWriter("AddressDatas.txt");
			myWriter.append("Id/Name/Street/City/Gender/Zip");
			for (int i = 0; i < people.size(); i++) {
				myWriter.append("\n" + people.get(i).getId() + "/" + people.get(i).getName() + "/"
						+ people.get(i).getStreet() + "/" + people.get(i).getCity() + "/" + people.get(i).getGender()
						+ "/" + people.get(i).getZip());
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
