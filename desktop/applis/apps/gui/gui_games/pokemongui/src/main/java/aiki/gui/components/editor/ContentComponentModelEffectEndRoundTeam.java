package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;

public final class ContentComponentModelEffectEndRoundTeam {
    private GeneComponentModelRate deleteAllStatus;
    private GeneComponentModelRate deleteAllStatusAlly;
    private AbsPanel form;
    AbsPanel effectForm(AbstractProgramInfos _core) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        deleteAllStatus = new GeneComponentModelRate(_core);
        selected_.add(deleteAllStatus.geneRate(Rate.zero()));
        deleteAllStatusAlly = new GeneComponentModelRate(_core);
        selected_.add(deleteAllStatusAlly.geneRate(Rate.zero()));
        form =selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectEndRoundTeam _edited) {
        _edited.setDeleteAllStatus(deleteAllStatus.valueRate());
        _edited.setDeleteAllStatusAlly(deleteAllStatusAlly.valueRate());
    }
    void feedForm(EffectEndRoundTeam _edited) {
        deleteAllStatus.valueRate(_edited.getDeleteAllStatus());
        deleteAllStatusAlly.valueRate(_edited.getDeleteAllStatusAlly());
    }

    public GeneComponentModelRate getDeleteAllStatus() {
        return deleteAllStatus;
    }

    public GeneComponentModelRate getDeleteAllStatusAlly() {
        return deleteAllStatusAlly;
    }
}
