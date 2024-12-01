package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;

public final class ContentComponentModelEffectEndRound {
    private GeneComponentModelSubscribeString failEndRound;
    private GeneComponentModelInt endRoundRank;
    private AbsPanel form;
    AbsPanel effectForm(AbstractProgramInfos _core) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        failEndRound = new GeneComponentModelSubscribeString(_core);
        selected_.add(failEndRound.geneEnum());
        endRoundRank = new GeneComponentModelInt(_core);
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
