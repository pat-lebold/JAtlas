/**
 * 
 */
package lebold.jatlas;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public class Directory {

    private File path;
    private String packageName;
    private List<Class<?>> classes;
    private List<Directory> subDirectories;

    public Directory(File path, String packageName){
	this.path = path;
	this.packageName = packageName;
	this.classes = new ArrayList<Class<?>>();
	this.subDirectories = new ArrayList<Directory>();
    }

    public void init(File baseFile){
	Logger.logDirectory(this);
	this.loadClasses(baseFile);
	this.loadSubDirectories(baseFile);
    }

    public File getPath(){
	return this.path;
    }

    public String getPackageName(){
	return this.packageName;
    }

    @SuppressWarnings("deprecation")
    private void loadClasses(File baseFile){
	try {
	    URL url = baseFile.toURL();
	    URL[] urls = new URL[]{url};
	    URLClassLoader loader = new URLClassLoader(urls);
	    for(File file:this.path.listFiles()){
		try{
		    String name = file.getName();
		    if(name.contains("$"))
			continue;
		    if(name.endsWith(".class")){
			String classPath = this.packageName + "." + name.substring(0,name.length()-6);
			Class<?> classFile = loader.loadClass(classPath);
			this.classes.add(classFile);
			Logger.logClass(classFile);
		    }
		}
		catch(NoClassDefFoundError e){
		    Logger.log("----- Class log error: " + e.getMessage());
		}
	    }
	    loader.close();
	}
	catch(MalformedURLException e){
	    Logger.logException(e);
	}
	catch(ClassNotFoundException e){
	    Logger.logException(e);
	}
	catch (IOException e) {
	    Logger.logException(e);
	}
    }

    private void loadSubDirectories(File baseFile){
	for(File file:this.path.listFiles()){
	    if(file.isDirectory()){
		String newPackageName = "";
		if(this.packageName.equals(""))
		    newPackageName = file.getName();
		else
		    newPackageName = this.packageName + "." + file.getName();
		Directory subDirectory = new Directory(file,newPackageName);
		this.subDirectories.add(subDirectory);
		subDirectory.init(baseFile);
	    }
	}
    }
}
