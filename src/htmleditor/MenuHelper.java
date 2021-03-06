
package htmleditor;

import htmleditor.actions.RedoAction;
import htmleditor.actions.StrikeThroughAction;
import htmleditor.actions.SubscriptAction;
import htmleditor.actions.SuperscriptAction;
import htmleditor.actions.UndoAction;
import htmleditor.listeners.TextEditMenuListener;
import htmleditor.listeners.UndoMenuListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

//7 вспомогательный класс для инициализации и настройки меню.
public class MenuHelper {
    // 7.1 parent - меню в которое мы добавляем пункт, text - текст добавляемого пункта, actionListener - слушатель действий добавляемого пункта меню.
    public static JMenuItem addMenuItem(JMenu parent, String text, ActionListener actionListener){
        JMenuItem item = new JMenuItem(text); //7.1.1
        item.addActionListener(actionListener); //7.1.2
        parent.add(item);  //7.1.3
        return item;  //7.1.4
    }
    
    // 7.2 - в качестве параметра принимает действие action, которое необходимо выполнить при выборе пункта меню.
    public static JMenuItem addMenuItem(JMenu parent, Action action){
        JMenuItem item = new JMenuItem(action);
        parent.add(item);  
        return item;  
    }
    
    // 7.3 - добавляет в parent новый пункт меню с текстом text и действием action при выборе этого метода.
    public static JMenuItem addMenuItem(JMenu parent, String text, Action action){
        JMenuItem item = addMenuItem(parent, action);
        item.setText(text);
        return item;
    }
    
    // 7.4.1 - инициализация меню помощи.
    public static void initHelpMenu(View view, JMenuBar menuBar){
        JMenu helpMenu = new JMenu("Помощь");
        menuBar.add(helpMenu);

        addMenuItem(helpMenu, "О программе", view);
    }
    
    // 7.4.2 - инициализация меню выбора
    public static void initFontMenu(View view, JMenuBar menuBar){
        JMenu fontMenu = new JMenu("Шрифт");
        menuBar.add(fontMenu);

        JMenu fontTypeMenu = new JMenu("Шрифт");
        fontMenu.add(fontTypeMenu);

        String[] fontTypes = {Font.SANS_SERIF, Font.SERIF, Font.MONOSPACED, Font.DIALOG, Font.DIALOG_INPUT};
        for (String fontType : fontTypes) {
            addMenuItem(fontTypeMenu, fontType, new StyledEditorKit.FontFamilyAction(fontType, fontType));
        }

        JMenu fontSizeMenu = new JMenu("Размер шрифта");
        fontMenu.add(fontSizeMenu);

        String[] fontSizes = {"6", "8", "10", "12", "14", "16", "20", "24", "32", "36", "48", "72"};
        for (String fontSize : fontSizes) {
            addMenuItem(fontSizeMenu, fontSize, new StyledEditorKit.FontSizeAction(fontSize, Integer.parseInt(fontSize)));
        }

        fontMenu.addMenuListener(new TextEditMenuListener(view));
    }
    
    // 7.4.3. - инициализация меню выбора цвета.
    public static void initColorMenu(View view, JMenuBar menuBar){
        JMenu colorMenu = new JMenu("Цвет");
        menuBar.add(colorMenu);

        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Красный", Color.red));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Оранжевый", Color.orange));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Желтый", Color.yellow));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Зеленый", Color.green));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Синий", Color.blue));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Голубой", Color.cyan));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Пурпурный", Color.magenta));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Черный", Color.black));

        colorMenu.addMenuListener(new TextEditMenuListener(view));
    }
    
    // 7.4.4. - инициализация меню выравнивания.
    public static void initAlignMenu(View view, JMenuBar menuBar){
        JMenu alignMenu = new JMenu("Выравнивание");
        menuBar.add(alignMenu);

        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("По левому краю", StyleConstants.ALIGN_LEFT));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("По центру", StyleConstants.ALIGN_CENTER));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("По правому краю", StyleConstants.ALIGN_RIGHT));

        alignMenu.addMenuListener(new TextEditMenuListener(view));
    }
    
    // 7.4.5. - инициализация меню выбора стиля текста.
    public static void initStyleMenu(View view, JMenuBar menuBar){
        JMenu styleMenu = new JMenu("Стиль");
        menuBar.add(styleMenu);

        addMenuItem(styleMenu, "Полужирный", new StyledEditorKit.BoldAction());
        addMenuItem(styleMenu, "Подчеркнутый", new StyledEditorKit.UnderlineAction());
        addMenuItem(styleMenu, "Курсив", new StyledEditorKit.ItalicAction());

        styleMenu.addSeparator();

        addMenuItem(styleMenu, "Подстрочный знак", new SubscriptAction());
        addMenuItem(styleMenu, "Надстрочный знак", new SuperscriptAction());
        addMenuItem(styleMenu, "Зачеркнутый", new StrikeThroughAction());

        styleMenu.addMenuListener(new TextEditMenuListener(view));
    }
    
    // 7.4.6. - инициализация меню редактирования текста.
    public static void initEditMenu(View view, JMenuBar menuBar){
        JMenu editMenu = new JMenu("Редактировать");
        menuBar.add(editMenu);

        JMenuItem undoItem = addMenuItem(editMenu, "Отменить", new UndoAction(view));
        JMenuItem redoItem = addMenuItem(editMenu, "Вернуть", new RedoAction(view));
        addMenuItem(editMenu, "Вырезать", new DefaultEditorKit.CutAction());
        addMenuItem(editMenu, "Копировать", new DefaultEditorKit.CopyAction());
        addMenuItem(editMenu, "Вставить", new DefaultEditorKit.PasteAction());

        editMenu.addMenuListener(new UndoMenuListener(view, undoItem, redoItem));
    }
    
    // 7.4.7. - инициализация меню Файл.
    public static void initFileMenu(View view, JMenuBar menuBar){
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);

        addMenuItem(fileMenu, "Новый", view);
        addMenuItem(fileMenu, "Открыть", view);
        addMenuItem(fileMenu, "Сохранить", view);
        addMenuItem(fileMenu, "Сохранить как...", view);
        fileMenu.addSeparator();
        addMenuItem(fileMenu, "Выход", view);
    }
    
}
