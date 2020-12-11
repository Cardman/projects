package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.stream.core.ReadBinFiles;
import code.stream.core.ReadFiles;
import code.util.StringList;

public interface AbstractReporter {
    AbstractNameValidating getNameValidating();
    boolean koOutZip(String _folderPath,StringList _list, ExecutingOptions _exec);
    boolean isMemory();
    String conf(String _fileConfOrContent);
    ReadBinFiles getBinFiles(String _archiveOrFolder);
    ReadFiles getFiles(String _archiveOrFolder);
    void coverFile(String _folder, String _fileName, String _content, RunnableContextEl _rCont);
    void errorFile(String _folder, String _fileName, String _content, RunnableContextEl _rCont);
}
