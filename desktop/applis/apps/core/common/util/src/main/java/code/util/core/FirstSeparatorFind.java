package code.util.core;

public final class FirstSeparatorFind {
    private final StringBuilder id;
    private final boolean found;
    private final int index;
    private final char used;
    public FirstSeparatorFind(String _info, char _sep) {
        StringBuilder id_ = new StringBuilder();
        int index_ = 0;
        boolean f_ = false;
        while (index_ < _info.length()) {
            char ch_ = _info.charAt(index_);
            if (ch_ == _sep) {
                f_ = true;
                index_++;
                break;
            }
            id_.append(ch_);
            index_++;
        }
        found = f_;
        index = index_;
        id = id_;
        used = _sep;
    }

    public int getIndex() {
        return index;
    }

    public boolean isFound() {
        return found;
    }

    public char getUsed() {
        return used;
    }

    public StringBuilder getId() {
        return id;
    }
}
