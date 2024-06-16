package cards.network.common;

public final class ClientServerIdInfos {
    private final StringBuilder id;
    private final int index;
    public ClientServerIdInfos(String _info, char _sep) {
        StringBuilder id_ = new StringBuilder();
        int index_ = 0;
        while (index_ < _info.length()) {
            char ch_ = _info.charAt(index_);
            if (ch_ == _sep) {
                index_++;
                break;
            }
            id_.append(ch_);
            index_++;
        }
        index = index_;
        id = id_;
    }

    public int getIndex() {
        return index;
    }

    public StringBuilder getId() {
        return id;
    }
}
