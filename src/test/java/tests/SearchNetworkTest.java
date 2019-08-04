package tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class SearchNetworkTest {
	@Test
	public void testing() throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader("./neurotreeData.json"));
		List<Person> list = new Gson().fromJson(reader,new TypeToken<List<Person>>() {}.getType());		
		for(Person p : list) {
			System.out.println(p.toString());
		}
		// TODO Once Website is live check if people are searchable on the network	
		
	}
	
	/* Anonymous classes for parsing JSON to object */
	class Person{
		String name;
		ArrayList<String> affiliation;
		ArrayList<Parent> parents;
		ArrayList<Child> children; 
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(name + "\n");
			sb.append("Affiliations: \n");
			for(String s : affiliation) {
				sb.append("\t").append(s).append("\n");
			}
			// CHANGE TO String.format Right JUSTIFIED
			sb.append("\tParents:\n");
			for(Parent p : parents) {
				sb.append("\t\t").append(p.name);
				sb.append("\t\t").append(p.title);
				sb.append("\t\t").append(p.year);
				sb.append("\t\t").append(p.affiliation).append("\n");
			}
			
			sb.append("\tChildren:\n");
			for(Child c : children) {
				sb.append("\t\t").append(c.name);
				sb.append("\t\t").append(c.title);
				sb.append("\t\t").append(c.year);
				sb.append("\t\t").append(c.affiliation).append("\n");
			}
			
			return sb.toString();
			
		}
	}

	class Parent{
		String name;
		String title;
		String year;
		String affiliation;
	}

	class Child{
		String name;
		String title;
		String year;
		String affiliation;
	}
}



