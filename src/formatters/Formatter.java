package formatters;

public class Formatter {
    public static String capitalizar(String string){
        return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
    }

}
