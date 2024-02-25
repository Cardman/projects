package cards.gui;

import cards.gui.containers.*;

public final class DefPausingCardsAnims implements AbsPausingCardsAnims {
    @Override
    public int complement(ContainerSingle _csi) {
        int next_ = ContainerSingleImpl.PAUSE_STOPPED - _csi.getPaused().get();
        _csi.getPaused().set(next_);
        return next_;
    }

    @Override
    public int state(ContainerSingle _csi) {
        return _csi.getPaused().get();
    }

    @Override
    public int stateChecked(ContainerSingle _csi) {
        return _csi.getPaused().get();
    }

    @Override
    public int alive(ContainerSingle _csi) {
        _csi.getPaused().set(ContainerSingleImpl.PAUSE_ALIVE);
        return 0;
    }
}
