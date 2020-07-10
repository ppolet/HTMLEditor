
package htmleditor.actions;

// 8.1.2. - Класс возврата действия

import htmleditor.View;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class RedoAction extends AbstractAction{
    private View view;
    
    public RedoAction(View view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
