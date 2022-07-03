package code.stream.core;

import code.util.StringMap;

public interface AbstractZipFact {
    StringMap<ContentTime> zippedBinaryFiles(byte[] _bytes);
    byte[] zipBinFiles(StringMap<ContentTime> _files);
}
