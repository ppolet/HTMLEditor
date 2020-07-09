package htmleditor;

import java.io.File;
import javax.swing.text.html.HTMLDocument;

//класс "Контроллер"
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
    
    //2.3
    public void exit(){
        System.exit(0);
    }
    
    public static void main(String[] args) {
        //2.2
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
        
    }
}
