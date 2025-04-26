package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;

public final class ContentComponentModelEffectEndRoundTeam {
    private GeneComponentModelRate deleteAllStatus;
    private GeneComponentModelRate deleteAllStatusAlly;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        deleteAllStatus = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(deleteAllStatus.geneRate());
        deleteAllStatusAlly = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(deleteAllStatusAlly.geneRate());
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
