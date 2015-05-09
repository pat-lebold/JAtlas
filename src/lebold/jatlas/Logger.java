/**
 * 
 */
package lebold.jatlas;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public class Logger {
    
    public static void log(String message){
	System.out.println(message);
    }
    
    public static void logException(Throwable t){
	System.out.println();
	System.out.println("-----------------------");
	System.out.println("Exception occurred: " + t.getMessage());
	t.printStackTrace();
    }
    
    public static void logDirectory(Directory directory){
	System.out.println("");
	System.out.println("Directory logged: " + directory.getPackageName());
    }
    
    public static void logClass(Class<?> classFile){
	System.out.println("Class logged: " + classFile.getName());
    }

}
