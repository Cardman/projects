package code.stream;

import code.stream.core.TechStreams;

public final class StreamBinaryFile {

    private StreamBinaryFile() {
    }

    public static byte[] loadFile(String _file, AbstractFileCoreStream _fact, TechStreams _str) {
        AbstractFile file_ = _fact.newFile(_file);
        return _str.getBinFact().loadFile(_file, file_.length());
    }

    public static boolean writeFile(String _file, byte[] _content, TechStreams _str) {
        return _str.getBinFact().writeFile(_file,_content);
    }

}
