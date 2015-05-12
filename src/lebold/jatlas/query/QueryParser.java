/**
 * 
 */
package lebold.jatlas.query;

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
