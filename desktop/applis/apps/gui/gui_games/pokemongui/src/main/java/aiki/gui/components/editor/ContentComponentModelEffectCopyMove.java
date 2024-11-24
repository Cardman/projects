package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelEffectCopyMove {

    private GeneComponentModelInt copyingMoveForUser;
    private GeneComponentModelLsStrSub<String,StringList> movesNotToBeCopied;
    private AbsCustCheckBox copyingMoveForUserDef;
    private AbsPanel form;

    AbsPanel effectForm(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        copyingMoveForUser = new GeneComponentModelInt(_core);
        selected_.add(copyingMoveForUser.geneInt());
        movesNotToBeCopied = ConverterCommonMapUtil.buildMoveList(_core,_fac,_fact);
        selected_.add(movesNotToBeCopied.geneEnum());
        copyingMoveForUserDef = _core.getCompoFactory().newCustCheckBox();
        selected_.add(copyingMoveForUserDef);
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }

    void buildEntity(EffectCopyMove _edited) {
        _edited.setCopyingMoveForUser((short) copyingMoveForUser.valueInt());
        _edited.setMovesNotToBeCopied(movesNotToBeCopied.tryRet());
        _edited.setCopyingMoveForUserDef(copyingMoveForUserDef.isSelected());
    }
    void feedForm(EffectCopyMove _edited) {
        copyingMoveForUser.valueInt(_edited.getCopyingMoveForUser());
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
