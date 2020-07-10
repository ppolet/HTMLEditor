
package htmleditor.actions;

// 8.1.5. - Класс SuperscriptAction. Он будет отвечать за стиль "Надстрочный знак"

import java.awt.event.ActionEvent;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

public class SuperscriptAction extends StyledEditorKit.StyledTextAction{

    public SuperscriptAction() {
        super(StyleConstants.Superscript.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}
