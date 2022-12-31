package code.expressionlanguage.utilcompo;

public interface AbstractLogger {
    AbstractIssuer getIssuer();
    void logErr(String _folerName, String _fileName, String _content);
    void log(String _folerName,String _fileName, String _content,RunnableContextEl _cont);
}
