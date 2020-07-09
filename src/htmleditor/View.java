package htmleditor;

import htmleditor.listeners.FrameListener;
import htmleditor.listeners.TabbedPaneChangeListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

//Класс "Представления"
public class View extends JFrame implements ActionListener{
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();  // панель с двумя вкладками
    private JTextPane htmlTextPane = new JTextPane();  // компонент для визуального редактирования html. Он будет размещен на первой вкладке.
    private JEditorPane plainTextPane = new JEditorPane();  // компонент для редактирования html в виде текста, он будет отображать код html (теги и их содержимое)
    
    
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
        htmlTextPane.setContentType("text/html"); //6.1
        JScrollPane jScrollPaneHtml = new JScrollPane(htmlTextPane); //6.2
        tabbedPane.addTab("HTML", jScrollPaneHtml);  //6.3
        
        JScrollPane jScrollPanePlain = new JScrollPane(plainTextPane);  //6.4
        tabbedPane.addTab("Текст", jScrollPanePlain);  //6.5
        Dimension dimension = new Dimension();
        dimension.setSize(640, 480);
        tabbedPane.setPreferredSize(dimension);  //6.6
        
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);  //6.7
        getContentPane().add(tabbedPane, BorderLayout.CENTER);  //6.8
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
