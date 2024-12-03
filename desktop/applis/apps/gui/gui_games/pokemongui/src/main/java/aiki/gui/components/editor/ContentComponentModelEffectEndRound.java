package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;

public final class ContentComponentModelEffectEndRound {
    private GeneComponentModelSubscribeString failEndRound;
    private GeneComponentModelInt endRoundRank;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        failEndRound = new GeneComponentModelSubscribeString(_core.getProgramInfos(),_core.getFacadeGame());
        selected_.add(failEndRound.geneEnum());
        failEndRound.addComplete();
        endRoundRank = new GeneComponentModelInt(_core.getProgramInfos());
        selected_.add(endRoundRank.geneInt());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectEndRound _edited) {
        _edited.setFailEndRound(failEndRound.tryRet());
        _edited.setEndRoundRank(endRoundRank.valueInt());
    }
    void feedForm(EffectEndRound _edited) {
        failEndRound.setupValue(_edited.getFailEndRound());
        endRoundRank.valueInt(_edited.getEndRoundRank());
    }

    public GeneComponentModelSubscribeString getFailEndRound() {
        return failEndRound;
    }

    public GeneComponentModelInt getEndRoundRank() {
        return endRoundRank;
    }
}
