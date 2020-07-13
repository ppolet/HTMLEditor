package htmleditor;

import htmleditor.listeners.FrameListener;
import htmleditor.listeners.TabbedPaneChangeListener;
import htmleditor.listeners.UndoListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

//Класс "Представления"
public class View extends JFrame implements ActionListener{
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();  // панель с двумя вкладками
    private JTextPane htmlTextPane = new JTextPane();  // компонент для визуального редактирования html. Он будет размещен на первой вкладке.
    private JEditorPane plainTextPane = new JEditorPane();  // компонент для редактирования html в виде текста, он будет отображать код html (теги и их содержимое)
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);  //11.4
    
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

    //11.5.4
    public UndoListener getUndoListener(){
        return undoListener;
    }
    
    //19 - будет вызваться при выборе пунктов меню, у которых наше представление указано в виде слушателя событий.
    @Override
    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();
        switch (act) {
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
                break;
        }
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
    
    //2.4
    public void exit(){
        controller.exit();
    }
    
    public boolean canUndo(){
        return undoManager.canUndo();
    }
    
    public boolean canRedo(){
        return undoManager.canRedo();
    }
    
    //11.5.1 - отменяет последнее действие.
    public void undo(){
        try{
            undoManager.undo();
        } catch (CannotUndoException ex) {
            ExceptionHandler.log(ex);
        }
    }
    
    //11.5.2 - возвращает ранее отмененное действие.
    public void redo(){
        try{
            undoManager.redo();
        } catch (Exception ex) {
            ExceptionHandler.log(ex);
        }
    }
    
    //11.5.5 - который должен сбрасывать все правки в менеджере undoManager
    public void resetUndo(){
        undoManager.discardAllEdits();
    }
    
    //13.1
    public boolean isHtmlTabSelected(){
        return tabbedPane.getSelectedIndex() == 0;
    }
    
    //14
    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);  // 14.1.1. - Выбираем html вкладку (переключаемся на нее).
        resetUndo();  // 14.1.2. - Сбрасываем все правки
    }
    
    //14.3 - который должен получать документ у контроллера и устанавливать его в панель редактирования htmlTextPane.
    public void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }
    
    //14.4
    public void showAbout(){
        JOptionPane.showMessageDialog(getContentPane(),"HTML Editor", "From JavaRush", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //18 - метод вызывается, когда произошла смена выбранной вкладки.
    public void selectedTabChanged(){
        if (isHtmlTabSelected()){
            controller.setPlainText(plainTextPane.getText());
        }
        if (tabbedPane.getSelectedIndex() == 1){
            plainTextPane.setText(controller.getPlainText());
        }
        resetUndo();  //18.4
    }
    
}
