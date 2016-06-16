import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextFileReader {
	static ArrayList princess = new ArrayList();
	public static void main (String[] args) throws IOException {
		doIt();
	}
	
	public static void doIt() throws IOException {
		readTextFile("src/Princesses.txt", "Kobold");
	}
	
	public static void readTextFile (String location, String princessName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(location));
		String line = "";
		while ((line = reader.readLine()) != null) {
			if (line.equals("#" + princessName)) {
				princess.add(princessName + " Princess");
				while(!line.equals("") && (line = reader.readLine()) != null) {
					princess.add(line);
				}
			}
		}
		
		outPut(princess);
	}
	
	public static void outPut (ArrayList princessObject) {
		//This should be sending to the princess builder instead
		for(int i=0; i<princess.size(); i++) {
			System.out.println(princess.get(i));
		}
	}
}
