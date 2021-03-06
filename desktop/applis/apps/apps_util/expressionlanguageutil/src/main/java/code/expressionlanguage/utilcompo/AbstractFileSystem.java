package code.expressionlanguage.utilcompo;

import code.stream.core.ReadBinFiles;
import code.util.StringList;

public interface AbstractFileSystem {

    void build(String _base, ReadBinFiles _readBin);
    String contentsOfFile(String _file, RunnableContextEl _rCont);
    boolean saveTextFile(String _file, String _content, RunnableContextEl _rCont);
    byte[] loadFile(String _file, RunnableContextEl _rCont);
    boolean writeFile(String _file, byte[] _content, RunnableContextEl _rCont);
    boolean delete(String _file, RunnableContextEl _rCont);
    boolean rename(String _origin, String _dest,RunnableContextEl _rCont);
    boolean logToFile(String _file, String _content,RunnableContextEl _rCont);
    String absolutePath(String _file,RunnableContextEl _rCont);
    long length(String _file,RunnableContextEl _rCont);
    String getName(String _file,RunnableContextEl _rCont);
    String getParentPath(String _file,RunnableContextEl _rCont);
    boolean isDirectory(String _file,RunnableContextEl _rCont);
    boolean isFile(String _file,RunnableContextEl _rCont);
    boolean isAbsolute(String _file,RunnableContextEl _rCont);
    StringList getRoots(RunnableContextEl _rCont);
    boolean mkdirs(String _file,RunnableContextEl _rCont);
    long lastModified(String _file,RunnableContextEl _rCont);
    StringList getFiles(String _file, RunnableContextEl _rCont);
    StringList getFolders(String _file, RunnableContextEl _rCont);
    String currentDir(RunnableContextEl _rCont);
    void changeDir(String _file, RunnableContextEl _rCont);

}
