/**
 * 
 */
package lebold.jatlas;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

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

    public static void logClassFound(Class<?> classFile){
	System.out.println("Class logged: " + classFile.getName());
    }

    public static void logClassInfo(Class<?> classFile){
	System.out.println();
	System.out.println("Class Name: " + classFile.getCanonicalName());
	if(classFile.getSuperclass() == null)
	    System.out.println("Super Class: Object");
	else
	    System.out.println("Super Class: " + classFile.getSuperclass().getCanonicalName());
	System.out.println("Fields: ");
	for(Field field:classFile.getFields()){
	    System.out.println("\t" + field.getType().getCanonicalName() + " " + field.getName());
	}
	System.out.println("Methods: ");
	for(Method method:classFile.getMethods()){
	    if(method.getDeclaringClass().equals(classFile)){
		String methodString = "";
		methodString += "\t" + method.getReturnType().getCanonicalName() + " " + method.getName() + "(";
		for(Parameter parameter:method.getParameters()){
		    methodString += parameter.getType().getCanonicalName() + ", ";
		}
		if(methodString.endsWith(", "))
		    methodString = methodString.substring(0, methodString.length()-2);
		methodString += ")";
		System.out.println(methodString);
	    }
	}

    }

}
