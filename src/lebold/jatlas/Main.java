package lebold.jatlas;

import java.io.File;
import java.util.Scanner;

import lebold.jatlas.file.FileSystem;
import lebold.jatlas.query.QueryEngine;
import lebold.jatlas.query.error.NoResultsFoundException;
import lebold.jatlas.query.result.IQueryResult;

public class Main {

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		Logger.log("Enter a file path!");
		Logger.logging = false;
		String filePath = scanner.nextLine();
		File file = new File(filePath);
		FileSystem system = new FileSystem(file);
		Workspace workspace = new Workspace(system);
		Logger.logging = true;
		Logger.log("Enter a Query!");
		String query = scanner.nextLine();
		QueryEngine engine = workspace.getQueryEngine();
		if(engine.isQueryValid(query)){
		    IQueryResult<?> result;
		    try {
			result = engine.executeQuery(query);
			    Logger.logResult(result);
		    }
		    catch (NoResultsFoundException e) {
			Logger.logException(e);
		    }
		}
		else{
		    Logger.log("Query not valid");
		}
		scanner.close();
	}
}
