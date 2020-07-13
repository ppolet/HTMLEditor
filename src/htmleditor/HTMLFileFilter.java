
package htmleditor;

//21 - Для открытия или сохранения файла мы будем использовать JFileChooser из библиотеки swing. Объекты этого типа поддерживают фильтры, унаследованные от FileFilter.

import java.io.File;
import javax.swing.filechooser.FileFilter;

// Наш фильтр
public class HTMLFileFilter extends FileFilter{

    //21.2
    @Override
    public boolean accept(File f) {
        // --- расширение "htm" или "html"
        if (f.isDirectory() || f.getName().toLowerCase().endsWith(".htm") || f.getName().toLowerCase().endsWith(".html")) return true;
        return false;
    }

    //21.3
    @Override
    public String getDescription() {
        return "HTML и HTM файлы";  
    }
    
}
