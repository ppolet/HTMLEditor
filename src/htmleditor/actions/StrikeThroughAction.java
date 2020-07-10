
package htmleditor.actions;

// 8.1.3. - Класс StrikeThroughAction, который отвечает за стиль текста "Зачеркнутый"

import java.awt.event.ActionEvent;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

public class StrikeThroughAction extends StyledEditorKit.StyledTextAction{

    public StrikeThroughAction() {
        super(StyleConstants.StrikeThrough.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}
