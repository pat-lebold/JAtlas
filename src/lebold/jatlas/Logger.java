/**
 * 
 */
package lebold.jatlas;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

import lebold.jatlas.file.Directory;
import lebold.jatlas.query.IQuery;
import lebold.jatlas.query.result.IQueryResult;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public class Logger {

    public static boolean logging = true;

    public static void log(String message){
	if(logging)
	    System.out.println(message);
    }

    public static void logClassFound(Class<?> classFile){
	if(logging){
	    System.out.println("Class logged: " + classFile.getName());
	}
    }

    public static void logClassInfo(Class<?> classFile){
	if(logging){
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

    public static void logDirectory(Directory directory){
	if(logging){
	    System.out.println("");
	    System.out.println("Directory logged: " + directory.getPackageName());
	}
    }

    public static void logException(Throwable t){
	if(logging){
	    System.out.println();
	    System.out.println("-----------------------");
	    System.out.println("Exception occurred: " + t.getMessage());
	    t.printStackTrace();
	}
    }

    public static void logList(List<?> list){
	if(logging){
	    list.forEach((element) -> log("\t"+element.toString()));
	}
    }

    public static void logResult(IQueryResult<?> result){
	if(logging){
	    IQuery query = result.getQuery();
	    Object object = result.getResult();

	    log("\n"+query.toString() + ": ");

	    if(object instanceof List){
		List<?> list = (List<?>)object;
		logList(list);
	    }
	    else{
		log("\t"+object.toString());
	    }
	}
    }

}
