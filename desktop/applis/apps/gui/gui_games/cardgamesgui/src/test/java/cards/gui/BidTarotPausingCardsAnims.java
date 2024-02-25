package cards.gui;

import cards.gui.containers.ContainerSingleImpl;
import code.util.core.NumberUtil;

public final class BidTarotPausingCardsAnims implements AbsPausingCardsAnims {
    @Override
    public int complement(ContainerSingleImpl _csi) {
        return ContainerSingleImpl.PAUSE_ALIVE;
    }

    @Override
    public int state(ContainerSingleImpl _csi) {
        return NumberUtil.compareLg(_csi.getPaused().incrementAndGet(), 2)+ContainerSingleImpl.PAUSE_STOPPED;
    }

    @Override
    public int alive(ContainerSingleImpl _csi) {
        _csi.getPaused().set(0);
        return 0;
    }
}
