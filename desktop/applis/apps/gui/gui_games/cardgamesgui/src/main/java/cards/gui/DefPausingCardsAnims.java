package cards.gui;

import cards.gui.containers.*;

public final class DefPausingCardsAnims implements AbsPausingCardsAnims {
    @Override
    public int complement(ContainerSin _csi) {
        int next_ = ContainerSingleImpl.PAUSE_STOPPED - _csi.getPaused().get();
        _csi.getPaused().set(next_);
        return next_;
    }

    @Override
    public int state(ContainerSin _csi) {
        return _csi.getPaused().get();
    }

    @Override
    public int stateChecked(ContainerSin _csi) {
        return _csi.getPaused().get();
    }

    @Override
    public int alive(ContainerSin _csi) {
        _csi.getPaused().set(ContainerSingleImpl.PAUSE_ALIVE);
        return 0;
    }
}
