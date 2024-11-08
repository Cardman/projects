package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.MoveData;
import code.gui.AbsCommonFrame;
import code.gui.GeneComponentModel;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryMv extends SubscribedTranslationMessagesFactoryCommonParam<MoveData> {

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedMoves();
    }

    @Override
    public void rename(FacadeGame _facade, String _previous, String _next) {
        _facade.getData().renameMove(_previous,_next);
    }

    @Override
    public StringMap<MoveData> all(FacadeGame _facade) {
        return _facade.getData().getMoves();
    }

    @Override
    public void delete(FacadeGame _facade, String _key) {
        _facade.getData().deleteMove(_key);
    }

    @Override
    public void completeQuickMembers(FacadeGame _facade, String _key, MoveData _value) {
        _facade.getData().completeQuickMembers(_key,_value);
    }

    @Override
    public GeneComponentModel<MoveData> build(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent _facade) {
        return new GeneComponentModelMoveData(_core);
    }

    @Override
    public void removeOpenSub(CrudGeneFormSubContent _base) {
        _base.removeOpenSub();
    }

    @Override
    public IdList<SubscribedTranslation> all() {
        return new IdList<SubscribedTranslation>();
    }
}
