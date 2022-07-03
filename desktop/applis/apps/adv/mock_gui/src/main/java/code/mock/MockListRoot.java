package code.mock;

import code.stream.AbstractListRoot;
import code.util.StringList;

public final class MockListRoot implements AbstractListRoot {

    private final StringList roots;
    public MockListRoot(StringList _r) {
        roots = _r;
    }
    @Override
    public int length() {
        return roots.size();
    }

    @Override
    public String path(int _i) {
        return roots.get(_i);
    }
}
