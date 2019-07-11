package ar.edu.unlam.tallerweb1.Helpers;

public class RegexHelper {
    public static final String REGEXLETRASYNUMEROS = "[0-9a-zA-Z]+$";
    public static final String REGEXEMAIL = "[a-zA-Z0-9_\\.\\-]+@[a-zA-Z0-9\\-]+\\.[a-zA-Z0-9\\-\\.]+$";
    public static final String REGEXSOLOLETRAS = "[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$";
    public static final String REGEXLETRASYESPACIO = "[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$";
    public static final String REGEXLETRASNUMEROSYESPACIO = "[0-9a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[0-9a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[0-9a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$";
    public static final String REGEXSOLONUMEROS = "[0-9]+$";
}
