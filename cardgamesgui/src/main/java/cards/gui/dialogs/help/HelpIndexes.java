package cards.gui.dialogs.help;

import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Equallable;

public final class HelpIndexes implements Equallable<HelpIndexes> {

    private final Numbers<Integer> indexes;

    public HelpIndexes() {
        indexes = new Numbers<Integer>();
    }

    public HelpIndexes(HelpIndexes _cheminNumCourant) {
        indexes = new Numbers<Integer>(_cheminNumCourant.indexes);
    }

    @Override
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

    public CustList<Integer> mid(byte _firstIndex, int _lastIndex) {
        return indexes.mid(_firstIndex, _lastIndex);
    }

}
