package htmleditor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

//класс "Контроллер"
public class Controller {
    
    //1.1 - 1.3
    private View view;
    private HTMLDocument document;
    private File currentFile;
    
    //1.4
    public Controller(View view){
        this.view = view;
    }
    
    //14.2 - геттер для модели
    public HTMLDocument getDocument(){
        return document;
    }
    
    public void init(){
        createNewDocument();  //20.2
    }
    
    //2.3
    public void exit(){
        System.exit(0);
    }
    
    //15 - который будет сбрасывать текущий документ.
    public void resetDocument(){
        if (document != null){
            document.removeUndoableEditListener(view.getUndoListener());  //15.1
        }
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();  //15.2
        document.addUndoableEditListener(view.getUndoListener());  //15.3
        view.update();  //15.4
    }
    
    //16 - будет записывать переданный текст с html тегами в документ document
    public void setPlainText(String text){
        resetDocument();
        StringReader stringReader = new StringReader(text);
        try {
            new HTMLEditorKit().read(stringReader, document, 0);   //16.3
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());  //16.4
        } catch (BadLocationException ex) {
            System.out.println("BadLocationException: " + ex.getMessage());
        }
        
    }
    
    //17 - Он должен получать текст из документа со всеми html тегами.
    public String getPlainText(){
        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());  //17.2
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());  
        } catch (BadLocationException ex) {
            System.out.println("BadLocationException: " + ex.getMessage());
        }
        return stringWriter.toString();
    }
    
    //20
    public void createNewDocument(){
        view.selectHtmlTab();  //20.1.1
        resetDocument(); //20.1.2
        view.setTitle("HTML редактор");  //20.1.3
        view.resetUndo();  //20.1.4
        currentFile = null;  //20.1.5
    }
    
    public void openDocument(){
        JFileChooser fileChoose = new JFileChooser();
        fileChoose.setFileFilter(new HTMLFileFilter());  //22.3
        if (fileChoose.showOpenDialog(view) == JFileChooser.APPROVE_OPTION){
            currentFile = fileChoose.getSelectedFile();
            resetDocument();  //23.2.2
            view.setTitle(currentFile.getName());   //23.2.3
            try (FileReader fileReader = new FileReader(currentFile)){
                new HTMLEditorKit().read(fileReader, document, 0);
                view.resetUndo();
            } catch (IOException | BadLocationException ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
            
        }
        
    }
    
    //23.1
    public void saveDocument(){
        if (currentFile != null){
            try (FileWriter filewriter = new FileWriter(currentFile)){
                new HTMLEditorKit().write(filewriter, document, 0, document.getLength());  //22.5.4
            } catch (IOException ex) {
                System.out.println("IOException: " + ex.getMessage());
            } catch (BadLocationException ex) {
                System.out.println("BadLocationException: " + ex.getMessage());
            }
        } else saveDocumentAs();
    }
    
    //22
    public void saveDocumentAs(){
        view.selectHtmlTab();  //22.1
        JFileChooser fileChoose = new JFileChooser();
        fileChoose.setFileFilter(new HTMLFileFilter());  //22.3
        if (fileChoose.showSaveDialog(view) == JFileChooser.APPROVE_OPTION){
            currentFile = fileChoose.getSelectedFile();
            view.setTitle(currentFile.getName());   //22.5.2
            try (FileWriter filewriter = new FileWriter(currentFile)){
                new HTMLEditorKit().write(filewriter, document, 0, document.getLength());  //22.5.4
            } catch (IOException ex) {
                System.out.println("IOException: " + ex.getMessage());
            } catch (BadLocationException ex) {
                System.out.println("BadLocationException: " + ex.getMessage());
            }
            
        }
    }
    
    public static void main(String[] args) {
        //2.2
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
    
}
