package code.mock;

import code.util.core.StringUtil;

public final class MockNameFile {
    private final String name;
    private final byte[] content;
    private long millis;

    public MockNameFile(String _n, String _c) {
        this(_n, _c,0);
    }
    public MockNameFile(String _n, String _c, long _millis) {
        this(_n, StringUtil.encode(_c),_millis);
    }
    public MockNameFile(String _n, byte[] _c) {
        this(_n,_c,0);
    }
    public MockNameFile(String _n, byte[] _c, long _m) {
        this.name = _n;
        this.content = _c;
        millis = _m;
    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long _m) {
        this.millis = _m;
    }
}
