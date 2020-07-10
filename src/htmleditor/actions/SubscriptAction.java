
package htmleditor.actions;

// 8.1.4. - Класс SubscriptAction, который отвечает за стиль текста "Подстрочный знак"

import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

public class SubscriptAction extends StyledEditorKit.StyledTextAction{

    public SubscriptAction() {
        super(StyleConstants.Subscript.toString());
    }

    //12.3.1
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JEditorPane editor = getEditor(actionEvent);
        if (editor != null) {
            MutableAttributeSet mutableAttributeSet = getStyledEditorKit(editor).getInputAttributes();
            SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
            StyleConstants.setSubscript(simpleAttributeSet, !StyleConstants.isSubscript(mutableAttributeSet));
            setCharacterAttributes(editor, simpleAttributeSet, false);
        }
    }
    
}
