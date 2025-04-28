package aiki.gui.components.editor;

import aiki.db.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectSwitchItems {
    private GeneComponentModelElt<String> moveObject;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        moveObject = new GeneComponentModelElt<String>(_core.getProgramInfos(), MessagesPkEditor.getMessagesEditorSelectMoveItemTypeTr(MessagesPkEditor.getAppliTr(_core.getProgramInfos().currentLg())).getMapping(),new EmptyDefValue());
        selected_.add(line(_core,MessagesDataEffswitchitems.M_P_61_EFFECT,moveObject.geneEnum()));
        moveObject.setupValue(DataBase.DEF_MOVE_ITEM_TYPE_REUSE_LAST_OBJECT);
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_SWITCHITEMS, _key,_input);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectSwitchItems _edited) {
        _edited.setMoveObject(MoveItemType.getMoveItemTypeTypeByName(moveObject.tryRet()));
    }
    void feedForm(EffectSwitchItems _edited) {
        moveObject.setupValue(_edited.getMoveObject().getItType());
    }

    public GeneComponentModelElt<String> getMoveObject() {
        return moveObject;
    }
}
