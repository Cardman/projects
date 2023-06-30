package code.expressionlanguage.utilcompo;

import code.stream.BytesInfo;
import code.stream.core.ReadBinFiles;
import code.util.StringList;

public interface AbstractFileSystem {

    void build(ExecutingOptions _opt, ReadBinFiles _readBin);
    String contentsOfFile(String _file, String _rCont);
    boolean saveTextFile(String _file, String _content, String _rCont);
    BytesInfo loadFile(String _file, String _rCont);
    boolean writeFile(String _file, byte[] _content, String _rCont);
    boolean delete(String _file, String _rCont);
    boolean rename(String _origin, String _dest,String _rCont);
    boolean logToFile(String _file, String _content,String _rCont);
    String absolutePath(String _file,String _rCont);
    long length(String _file,String _rCont);
    String getName(String _file,String _rCont);
    String getParentPath(String _file,String _rCont);
    boolean isDirectory(String _file,String _rCont);
    boolean isFile(String _file,String _rCont);
    boolean isAbsoluteFct(String _file);
    StringList getRoots();
    boolean mkdirs(String _file,String _rCont);
    long lastModified(String _file,String _rCont);
    StringList getFiles(String _file, String _rCont);
    StringList getFolders(String _file, String _rCont);

    String changeDir(String _file, String _rCont);

}
