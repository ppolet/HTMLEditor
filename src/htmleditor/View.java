package htmleditor;

import htmleditor.listeners.FrameListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

//Класс "Представления"
public class View extends JFrame implements ActionListener{
    private Controller controller;
    private JTabbedPane tabbedPane;  // панель с двумя вкладками
    private JTextPane htmlTextPane;  // компонент для визуального редактирования html. Он будет размещен на первой вкладке.
    private JEditorPane plainTextPane;  // компонент для редактирования html в виде текста, он будет отображать код html (теги и их содержимое)
    
    
    public void setController(Controller controller){
        this.controller = controller;
    }
    
    public Controller getController(){
        return controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    //4.3
    public void init(){
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);  //4.3.2
        setVisible(true);
    }
    
    //4.1 - отвечает за инициализацию меню.
    public void initMenuBar(){
        
    }
    
    //4.1 - отвечает за инициализацию панелей редактора.
    public void initEditor(){
        
    }
    
    //4.2 - инициализирует графический интерфейс.
    public void initGui(){
        initMenuBar();
        initEditor();
        pack(); // унаследовали от JFrame (Подберет оптимальный размер окна с компонентами и т.д.)
    }
    
    public void selectedTabChanged(){
        
    }
    
    //2.4
    public void exit(){
        controller.exit();
    }
    
}
