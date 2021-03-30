package code.expressionlanguage.common;

public abstract class AbstractOperationIndexer {
    public int getIndex(){
        int minIndex_ = getIndex(0);
        int size_ = size();
        int i_ = 0;
        for (int i = 1; i < size_; i++) {
            int index_ = getIndex(i);
            if (index_ < minIndex_) {
                minIndex_ = index_;
                i_ = i;
            }
        }
        return i_;
    }
    public abstract int size();
    public abstract int getIndex(int _index);
}
