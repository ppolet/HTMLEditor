
package htmleditor.listeners;

import htmleditor.View;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Этот класс будет слушать и обрабатывать изменения состояния панели вкладок.
public class TabbedPaneChangeListener implements ChangeListener{
    private View view;
    
    public TabbedPaneChangeListener(View view){
        this.view = view;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        view.selectedTabChanged();   //5.1.2
    }
}
