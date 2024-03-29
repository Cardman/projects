package cards.gui.dialogs.help;

import code.util.CustList;
import code.util.Ints;

public final class HelpIndexes {

    private final Ints indexes;

    public HelpIndexes() {
        indexes = new Ints();
    }

    public HelpIndexes(HelpIndexes _cheminNumCourant) {
        indexes = new Ints(_cheminNumCourant.indexes);
    }

    public boolean eq(HelpIndexes _g) {
        int len_ = size();
        if (_g.size() != len_) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            int e_ = get(i);
            int f_ = _g.get(i);
            if (e_ != f_) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return indexes.size();
    }

    public int get(int _i) {
        return indexes.get(_i);
    }

    public void add(int _i) {
        indexes.add(_i);
    }

    public int getLastIndex() {
        return indexes.getLastIndex();
    }

    public CustList<Integer> left(int _lastIndex) {
        return indexes.left(_lastIndex);
    }

}
