package code.expressionlanguage.utilcompo;

import code.util.StringMap;

public interface AbstractReporter {
    String conf(String _fileConfOrContent);
    StringMap<String> getFiles(String _archiveOrFolder);
    void coverFile(String _folder, String _fileName, String _content, RunnableContextEl _rCont);
    void errorFile(String _folder, String _fileName, String _content, RunnableContextEl _rCont);
}
