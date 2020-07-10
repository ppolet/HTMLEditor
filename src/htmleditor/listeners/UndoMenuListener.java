
package htmleditor.listeners;

// 8.2.1

import htmleditor.View;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class UndoMenuListener implements MenuListener{
    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem){
        
    }

    @Override
    public void menuSelected(MenuEvent e) {
    }

    @Override
    public void menuDeselected(MenuEvent e) {
    }

    @Override
    public void menuCanceled(MenuEvent e) {
    }
}
