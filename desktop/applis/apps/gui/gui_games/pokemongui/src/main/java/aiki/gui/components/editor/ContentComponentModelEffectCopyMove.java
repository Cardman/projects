package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectCopyMove {

    private GeneComponentModelLong copyingMoveForUser;
    private GeneComponentModelLsStrSub<String,StringList> movesNotToBeCopied;
    private AbsCustCheckBox copyingMoveForUserDef;
    private AbsPanel form;

    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        copyingMoveForUser = new GeneComponentModelLong(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffcopymove.M_P_43_COPY_TMP_MOVE_INTRO,copyingMoveForUser.geneLong()));
        movesNotToBeCopied = ConverterCommonMapUtil.buildMoveList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffcopymove.M_P_43_MOVES_NOT_COPIED,movesNotToBeCopied.geneEnum()));
        copyingMoveForUserDef = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffcopymove.M_P_43_COPY_DEF_MOVE_INTRO,copyingMoveForUserDef));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_COPYMOVE, _key,_input);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }

    void buildEntity(EffectCopyMove _edited) {
        _edited.setCopyingMoveForUser(copyingMoveForUser.valueLong());
        _edited.setMovesNotToBeCopied(movesNotToBeCopied.tryRet());
        _edited.setCopyingMoveForUserDef(copyingMoveForUserDef.isSelected());
    }
    void feedForm(EffectCopyMove _edited) {
        copyingMoveForUser.valueLong(_edited.getCopyingMoveForUser());
        movesNotToBeCopied.setupValue(_edited.getMovesNotToBeCopied());
        copyingMoveForUserDef.setSelected(_edited.getCopyingMoveForUserDef());
    }

    public AbsCustCheckBox getCopyingMoveForUserDef() {
        return copyingMoveForUserDef;
    }

    public GeneComponentModelLsStrSub<String,StringList> getMovesNotToBeCopied() {
        return movesNotToBeCopied;
    }
}
