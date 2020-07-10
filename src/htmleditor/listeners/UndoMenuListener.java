
package htmleditor.listeners;

// 8.2.1

import htmleditor.View;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.undo.UndoManager;

/*
Этот слушатель будет следить за меню, а если конкретнее,
то за моментом, когда меню редактирования будет выбрано пользователем. В этот момент он
будет запрашивать у представления можем ли мы сейчас отменить или вернуть какое-то
действие, и в зависимости от этого делать доступными или не доступными пункты меню
"Отменить" и "Вернуть".
*/
// 10
public class UndoMenuListener implements MenuListener{
    private View view;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;
    private UndoManager undoManager;
    
    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem){
        this.view = view;
        this.undoMenuItem = undoMenuItem;
        this.redoMenuItem = redoMenuItem;
    }

    // 10.3
    @Override
    public void menuSelected(MenuEvent e) {
        undoMenuItem.setEnabled(view.canUndo());
        redoMenuItem.setEnabled(view.canRedo());
    }

    @Override
    public void menuDeselected(MenuEvent e) {
    }

    @Override
    public void menuCanceled(MenuEvent e) {
    }
}
