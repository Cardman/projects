package cards.network.threads;

public final class NicknameSplitPartsNewFieldsCards implements IntSplitPartsFieldsCards {
    @Override
    public boolean split(char _ch, int[] _nb) {
        if (Net.SEP_0 == _ch) {
            _nb[0]++;
        }
        return Net.SEP_0 == _ch && _nb[0] <= 1;
    }
}
