/**
 * 
 */
package lebold.jatlas;

import java.io.File;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public class FileSystem {

    private Directory directory;
    
    public FileSystem(File file){
	this.initDirectory(file);
    }
    
    private void initDirectory(File file){
	this.directory = new Directory(file,"");
	this.directory.init(file);
    }
    
    public void reinitialize(){
	this.directory.init(this.directory.getPath());
    }
}
