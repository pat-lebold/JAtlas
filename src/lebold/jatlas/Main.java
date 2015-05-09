package lebold.jatlas;

import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		Logger.log("Enter a file path!");
		String filePath = scanner.nextLine();
		File file = new File(filePath);
		FileSystem system = new FileSystem(file);
		scanner.close();
	}
}
