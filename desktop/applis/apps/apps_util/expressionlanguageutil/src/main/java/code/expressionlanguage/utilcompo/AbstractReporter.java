package code.expressionlanguage.utilcompo;

import code.stream.core.ReadFiles;

public interface AbstractReporter {
    String conf(String _fileConfOrContent);
    ReadFiles getFiles(String _archiveOrFolder);
    void coverFile(String _folder, String _fileName, String _content, RunnableContextEl _rCont);
    void errorFile(String _folder, String _fileName, String _content, RunnableContextEl _rCont);
}
