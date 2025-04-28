package aiki.gui.components.editor;

import aiki.db.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectSwitchPointView {
    private GeneComponentModelElt<String> pointViewChangement;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        pointViewChangement = new GeneComponentModelElt<String>(_core.getProgramInfos(), MessagesPkEditor.getMessagesEditorSelectPointViewChangementTypeTr(MessagesPkEditor.getAppliTr(_core.getProgramInfos().currentLg())).getMapping(),new EmptyDefValue());
        selected_.add(line(_core,MessagesDataEffswitchpointview.M_P_63_EFFECT,pointViewChangement.geneEnum()));
        pointViewChangement.setupValue(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_NOTHING);
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_SWITCHPOINTVIEW, _key,_input);
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
