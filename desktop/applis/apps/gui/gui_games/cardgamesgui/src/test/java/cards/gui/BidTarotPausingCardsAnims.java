package cards.gui;

import cards.gui.containers.ContainerSin;
import cards.gui.containers.ContainerSingleImpl;
import code.util.core.NumberUtil;

public final class BidTarotPausingCardsAnims implements AbsPausingCardsAnims {
    @Override
    public int complement(ContainerSin _csi) {
        return ContainerSingleImpl.PAUSE_ALIVE;
    }

    @Override
    public int state(ContainerSin _csi) {
        return NumberUtil.compareLg(_csi.getPaused().incrementAndGet(), 2)+ContainerSingleImpl.PAUSE_STOPPED;
    }

    @Override
    public int stateChecked(ContainerSin _csi) {
        return NumberUtil.compareLg(_csi.getPaused().get(), 2)+ContainerSingleImpl.PAUSE_STOPPED;
    }

    @Override
    public int alive(ContainerSin _csi) {
        _csi.getPaused().set(0);
        return 0;
    }
}
