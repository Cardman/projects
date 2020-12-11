package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.AbstractNameValidating;

public interface AbstractLogger {
    AbstractNameValidating getNameValidating();
    void logErr(String _folerName,String _fileName, String _content,RunnableContextEl _cont);
    void log(String _folerName,String _fileName, String _content,RunnableContextEl _cont);
}
