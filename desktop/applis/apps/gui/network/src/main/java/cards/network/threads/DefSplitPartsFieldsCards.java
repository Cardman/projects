package cards.network.threads;

public final class DefSplitPartsFieldsCards implements IntSplitPartsFieldsCards {
    @Override
    public boolean split(char _ch, int[] _nb) {
        return Net.SEP_0 == _ch;
    }
}
