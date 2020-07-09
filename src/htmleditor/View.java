package htmleditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class View extends JFrame implements ActionListener{
    private Controller controller;
    
    public void setController(Controller controller){
        this.controller = controller;
    }
    
    public Controller getController(){
        return controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public void init(){
        
    }
    
}
