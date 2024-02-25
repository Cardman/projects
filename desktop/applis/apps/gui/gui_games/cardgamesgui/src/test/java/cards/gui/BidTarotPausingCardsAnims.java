package cards.gui;

import cards.gui.containers.ContainerSingle;
import cards.gui.containers.ContainerSingleImpl;
import code.util.core.NumberUtil;

public final class BidTarotPausingCardsAnims implements AbsPausingCardsAnims {
    @Override
    public int complement(ContainerSingle _csi) {
        return ContainerSingleImpl.PAUSE_ALIVE;
    }

    @Override
    public int state(ContainerSingle _csi) {
        return NumberUtil.compareLg(_csi.getPaused().incrementAndGet(), 2)+ContainerSingleImpl.PAUSE_STOPPED;
    }

    @Override
    public int stateChecked(ContainerSingle _csi) {
        return NumberUtil.compareLg(_csi.getPaused().get(), 2)+ContainerSingleImpl.PAUSE_STOPPED;
    }

    @Override
    public int alive(ContainerSingle _csi) {
        _csi.getPaused().set(0);
        return 0;
    }
}
