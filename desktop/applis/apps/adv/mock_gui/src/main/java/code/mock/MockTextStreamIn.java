package code.mock;

import code.stream.core.AbstractTextStreamIn;

public final class MockTextStreamIn implements AbstractTextStreamIn {
    private final String content;
    private int index;

    public MockTextStreamIn(String _c) {
        this.content = _c;
    }

    @Override
    public int close() {
        return 1;
    }

    @Override
    public int read() {
        if (content == null) {
            return -2;
        }
        if (index < content.length()) {
            char ch_ = content.charAt(index);
            index++;
            return ch_;
        }
        return -1;
    }
}
