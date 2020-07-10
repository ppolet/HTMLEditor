
package htmleditor.actions;

//8.1.1 - Класс отмены действия

import htmleditor.View;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class UndoAction extends AbstractAction{
    private View view;
    
    public UndoAction(View view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.undo();  //12.2
    }
}
