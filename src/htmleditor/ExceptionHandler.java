
package htmleditor;

// обработчик исключительных ситуаций
public class ExceptionHandler {
    public static void log(Exception e){
        System.out.println("Exception: " + e.getMessage());
    }
}
