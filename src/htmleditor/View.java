package htmleditor;

import htmleditor.listeners.FrameListener;
import htmleditor.listeners.TabbedPaneChangeListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;

//Класс "Представления"
public class View extends JFrame implements ActionListener{
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();  // панель с двумя вкладками
    private JTextPane htmlTextPane = new JTextPane();  // компонент для визуального редактирования html. Он будет размещен на первой вкладке.
    private JEditorPane plainTextPane = new JEditorPane();  // компонент для редактирования html в виде текста, он будет отображать код html (теги и их содержимое)
    
    // 9.2 
    public View(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ExceptionHandler.log(ex);
        }
    }
    
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
    
    //4.1, 9.1 - отвечает за инициализацию меню.
    public void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        
        //9.1.2. - С помощью MenuHelper инициализировать меню в следующем порядке: Файл, Редактировать, Стиль, Выравнивание, Цвет, Шрифт и Помощь.
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        
        //9.1.3. - Добавлять в верхнюю часть панели контента текущего фрейма нашу панель меню, аналогично тому, как это мы делали с панелью вкладок.
        getContentPane().add(menuBar, BorderLayout.NORTH);
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
