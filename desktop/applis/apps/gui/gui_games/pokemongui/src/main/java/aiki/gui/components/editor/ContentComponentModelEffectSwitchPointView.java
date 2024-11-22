package aiki.gui.components.editor;

import aiki.db.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import code.gui.*;

public final class ContentComponentModelEffectSwitchPointView {
    private GeneComponentModelElt<String> pointViewChangement;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        pointViewChangement = new GeneComponentModelElt<String>(_core.getProgramInfos(), MessagesPkEditor.getMessagesEditorSelectPointViewChangementTypeTr(MessagesPkEditor.getAppliTr(_core.getProgramInfos().currentLg())).getMapping(),new EmptyDefValue());
        selected_.add(pointViewChangement.geneEnum());
        pointViewChangement.setupValue(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_NOTHING);
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectSwitchPointView _edited) {
        _edited.setPointViewChangement(PointViewChangementType.getPointViewChangementTypeByName(pointViewChangement.tryRet()));
    }
    void feedForm(EffectSwitchPointView _edited) {
        pointViewChangement.setupValue(_edited.getPointViewChangement().getPtView());
    }

    public GeneComponentModelElt<String> getPointViewChangement() {
        return pointViewChangement;
    }
}
