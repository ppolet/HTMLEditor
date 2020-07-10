
package htmleditor.listeners;

// 11.2. - Этот класс будет следить за правками, которые можно отменить или вернуть.

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class UndoListener implements UndoableEditListener{
    private UndoManager undoManager;
    
    public UndoListener(UndoManager undoManager){
        this.undoManager = undoManager;
    }
    
    @Override
    public void undoableEditHappened(UndoableEditEvent e) {
        undoManager.addEdit(e.getEdit()); //11.3.3
    }
    
}
