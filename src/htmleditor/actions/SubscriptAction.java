
package htmleditor.actions;

// 8.1.4. - Класс SubscriptAction, который отвечает за стиль текста "Подстрочный знак"

import java.awt.event.ActionEvent;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

public class SubscriptAction extends StyledEditorKit.StyledTextAction{

    public SubscriptAction() {
        super(StyleConstants.Subscript.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}
