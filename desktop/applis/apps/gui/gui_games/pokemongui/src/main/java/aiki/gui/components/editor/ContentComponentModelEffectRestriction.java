package aiki.gui.components.editor;

import aiki.db.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import code.gui.*;

public final class ContentComponentModelEffectRestriction {

    private GeneComponentModelElt<String> choiceRestriction;
    private AbsCustCheckBox forbidTargetUsingItem;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        choiceRestriction = new GeneComponentModelElt<String>(_core.getProgramInfos(), MessagesPkEditor.getMessagesEditorSelectMoveChoiceRestrictionTypeTr(MessagesPkEditor.getAppliTr(_core.getProgramInfos().currentLg())).getMapping(),new EmptyDefValue());
        selected_.add(choiceRestriction.geneEnum());
        choiceRestriction.setupValue(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_NOTHING);
        forbidTargetUsingItem = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(forbidTargetUsingItem);
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectRestriction _edited) {
        _edited.setChoiceRestriction(MoveChoiceRestrictionType.getMoveChoiceRestrictionTypeByName(choiceRestriction.tryRet()));
        _edited.setForbidTargetUsingItem(forbidTargetUsingItem.isSelected());
    }
    void feedForm(EffectRestriction _edited) {
        choiceRestriction.setupValue(_edited.getChoiceRestriction().getResType());
        forbidTargetUsingItem.setSelected(_edited.getForbidTargetUsingItem());
    }

    public AbsCustCheckBox getForbidTargetUsingItem() {
        return forbidTargetUsingItem;
    }
}
