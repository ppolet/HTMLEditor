
package htmleditor.listeners;

import htmleditor.View;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//3.2
public class FrameListener extends WindowAdapter{

    private View view;
    
    public FrameListener(View view){
        this.view = view;
    }

    //3.2.4
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        view.exit();
    }
    
}
