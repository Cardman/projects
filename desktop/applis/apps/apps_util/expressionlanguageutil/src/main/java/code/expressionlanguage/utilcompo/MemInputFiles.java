package code.expressionlanguage.utilcompo;

public final class MemInputFiles {
    private final byte[] conf;
    private final byte[] src;
    private final byte[] files;

    public MemInputFiles(byte[] _conf, byte[] _src, byte[] _files) {
        this.conf = _conf;
        this.src = _src;
        this.files = _files;
    }

    public byte[] getConf() {
        return conf;
    }

    public byte[] getFiles() {
        return files;
    }

    public byte[] getSrc() {
        return src;
    }
}
