/**
 * 
 */
package lebold.jatlas.file;

import java.io.File;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public class FileSystem {

    private Directory rootDirectory;
    
    public FileSystem(File file){
	this.initDirectory(file);
    }
    
    private void initDirectory(File file){
	this.rootDirectory = new Directory(file,"");
	this.rootDirectory.init(file);
    }
    
    public void reinitialize(){
	this.rootDirectory.init(this.rootDirectory.getPath());
    }
    
    public Directory getRootDirectory(){
	return this.rootDirectory;
    }
}
