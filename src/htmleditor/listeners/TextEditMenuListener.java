
package htmleditor.listeners;

// 8.2.2, 13
/*
Этот класс будет работать аналогично
классу UndoMenuListener только для других пунктов меню. Пункты меню, отвечающие за
стиль, шрифт, цвет и т.д. должны быть доступны только тогда, когда в нашем редакторе
выбрана первая вкладка.
*/

import htmleditor.View;
import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class TextEditMenuListener implements MenuListener{
    private View view;
    
    public TextEditMenuListener(View view){
        this.view = view;
    }

    //13.3
    @Override
    public void menuSelected(MenuEvent e) {
        JMenu menu = (JMenu)e.getSource();  //13.3.1
        
        for(Component component: menu.getMenuComponents()){
            component.setEnabled(view.isHtmlTabSelected());
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {
    }

    @Override
    public void menuCanceled(MenuEvent e) {
    }
}
