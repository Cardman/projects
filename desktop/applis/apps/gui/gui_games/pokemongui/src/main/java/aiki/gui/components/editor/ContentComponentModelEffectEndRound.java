package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelEffectEndRound {
    private GeneComponentModelString failEndRound;
    private GeneComponentModelInt endRoundRank;
    private AbsPanel form;
    AbsPanel effectForm(AbstractProgramInfos _core) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        failEndRound = new GeneComponentModelString(_core,new StringList(),new DefValidateText());
        selected_.add(failEndRound.geneString());
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
        _edited.setFailEndRound(failEndRound.valueString());
        _edited.setEndRoundRank(endRoundRank.valueInt());
    }
    void feedForm(EffectEndRound _edited) {
        failEndRound.valueString(_edited.getFailEndRound());
        endRoundRank.valueInt(_edited.getEndRoundRank());
    }

    public GeneComponentModelString getFailEndRound() {
        return failEndRound;
    }

    public GeneComponentModelInt getEndRoundRank() {
        return endRoundRank;
    }
}
