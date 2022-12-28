package code.stream.core;

import code.stream.BytesInfo;
import code.util.StringMap;

public interface AbstractZipFact {
    StringMap<ContentTime> zippedBinaryFiles(BytesInfo _bytes);
    byte[] zipBinFiles(StringMap<ContentTime> _files);
}
