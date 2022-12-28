package code.expressionlanguage.utilcompo;

import code.stream.BytesInfo;

public final class MemInputFiles {
    private final byte[] conf;
    private final BytesInfo src;
    private final BytesInfo files;

    public MemInputFiles(byte[] _conf, BytesInfo _src, BytesInfo _files) {
        this.conf = _conf;
        this.src = _src;
        this.files = _files;
    }

    public byte[] getConf() {
        return conf;
    }

    public BytesInfo getFiles() {
        return files;
    }

    public BytesInfo getSrc() {
        return src;
    }
}
