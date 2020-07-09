package htmleditor;

import java.io.File;
import javax.swing.text.html.HTMLDocument;

public class Controller {
    
    //1.1 - 1.3
    private View view;
    private HTMLDocument document;
    private File currentFile;
    
    //1.4
    public Controller(View view){
        this.view = view;
    }
    
    public void init(){
        
    }
    
    public static void main(String[] args) {
        
    }
}
