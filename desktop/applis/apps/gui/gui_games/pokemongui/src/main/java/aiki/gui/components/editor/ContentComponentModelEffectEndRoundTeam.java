package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectEndRoundTeam {
    private GeneComponentModelRate deleteAllStatus;
    private GeneComponentModelRate deleteAllStatusAlly;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        deleteAllStatus = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEndroundTeam.M_P_13_OWNER_INTRO,deleteAllStatus.geneRate()));
        deleteAllStatusAlly = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEndroundTeam.M_P_13_TEAM_INTRO,deleteAllStatusAlly.geneRate()));
        form =selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.ENDROUND_TEAM, _key,_input);
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
