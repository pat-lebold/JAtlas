/**
 * 
 */
package lebold.jatlas.query;

import lebold.jatlas.file.Directory;
import lebold.jatlas.file.FileSystem;
import lebold.jatlas.query.error.InvalidDirectoryException;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public final class QueryParser {

    private QueryParser(){
	//Filler constructor to prevent instantiation of class
    }
    
    public static Directory fetchDirectory(FileSystem system, String[] packageArray) throws InvalidDirectoryException{
	Directory root = system.getRootDirectory();
	int numPackages = packageArray.length;
	int currentIndex = 0;
	while(!root.getPackageName().equals(packageToString(packageArray))){
	    if(currentIndex==numPackages)
		throw new InvalidDirectoryException("Directory Invalid: " + packageToString(packageArray));
	    String searchingFolder = packageArray[currentIndex];
	    boolean found = false;
	    for(Directory sub:root.getSubDirectories()){
		String folder = sub.getBottomFolderName();
		if(folder.equals(searchingFolder)){
		    currentIndex++;
		    root = sub;
		    found = true;
		    break;
		}
	    }
	    if(!found)
		throw new InvalidDirectoryException("Directory Invalid: " + packageToString(packageArray));
	}
	return root;
    }
    
    public static String packageToString(String[] packageSetup){
	String packageString = "";
	for(int n=0;n<packageSetup.length;n++){
	    packageString += packageSetup[n] + ".";
	}
	packageString = packageString.substring(0,packageString.length()-1);
	return packageString;
    }
    
    public static String[] splitQuery(String queryString){
	return queryString.split(" ");
    }

    public static boolean isClassFormat(String potentialClass){
	if(!Character.isJavaIdentifierStart(potentialClass.charAt(0)))
	    return false;
	for(int n=1;n<potentialClass.length();n++){
	    if(!Character.isJavaIdentifierPart(potentialClass.charAt(n)))
		return false;
	}
	if(QueryParser.isJavaKeyword(potentialClass))
	   return false;
	return true;
    }
    
    public static boolean isPackageFormat(String potentialPackage){
	if(potentialPackage.startsWith(".")||potentialPackage.endsWith("."))
	    return false;
	String[] splitPackage = potentialPackage.split("\\.");
	for(String splitPackageDirectory:splitPackage){
	    if(!QueryParser.isClassFormat(splitPackageDirectory))
		return false;
	}
	return true;
    }
    
    public static String[] splitPackage(String packageString){
	return packageString.split("\\.");
    }

    public static boolean isJavaKeyword(String word){
	String keywords[] = {
		"abstract",  "assert",       "boolean",    "break",      "byte",      "case",
		"catch",     "char",         "class",      "const",     "continue",
		"default",   "do",           "double",     "else",      "extends",
		"false",     "final",        "finally",    "float",     "for",
		"goto",      "if",           "implements", "import",    "instanceof",
		"int",       "interface",    "long",       "native",    "new",
		"null",      "package",      "private",    "protected", "public",
		"return",    "short",        "static",     "strictfp",  "super",
		"switch",    "synchronized", "this",       "throw",     "throws",
		"transient", "true",         "try",        "void",      "volatile",
		"while"
	};
	for(String keyword:keywords){
	    if(keyword.equals(word))
		return true;
	}
	return false;
    }
}
