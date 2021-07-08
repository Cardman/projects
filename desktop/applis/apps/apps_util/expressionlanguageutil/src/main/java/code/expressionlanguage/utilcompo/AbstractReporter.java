package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.stream.core.ReadBinFiles;
import code.stream.core.ReadFiles;
import code.util.StringMap;

public interface AbstractReporter {
    AbstractNameValidating getNameValidating();
    StringMap<String> getSrc(String _archive, ExecutingOptions _exec, ReadFiles _results);
    String getFolderPath(String _folderPath, ExecutingOptions _exec,ReadFiles _results);
    boolean koPaths(String _folderPath, ExecutingOptions _exec);

    String conf(String _fileConfOrContent);
    String confTxt(String _fileConfOrContent);
    ReadBinFiles getBinFiles(String _archiveOrFolder);
    ReadFiles getFiles(String _archiveOrFolder);
    void coverFile(ExecutingOptions _ex, String _fileName, String _content);
    void errorFile(ExecutingOptions _ex, String _fileName, String _content);
    byte[] exportErrs(ExecutingOptions _ex, AbstractLogger _log);
    byte[] export(ExecutingOptions _ex,AbstractFileSystem _sys,AbstractLogger _log);
}
