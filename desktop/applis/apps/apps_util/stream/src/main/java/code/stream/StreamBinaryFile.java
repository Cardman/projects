package code.stream;

import code.stream.core.TechStreams;

public final class StreamBinaryFile {

    private StreamBinaryFile() {
    }

    public static byte[] loadFile(String _file, TechStreams _str) {
        return _str.getBinFact().loadFile(_file);
    }

    public static boolean writeFile(String _file, byte[] _content, TechStreams _str) {
        return _str.getBinFact().writeFile(_file,_content);
    }

}
