package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryMv extends SubscribedTranslationMessagesFactoryCommonParam<MoveData> {

    private GeneComponentModelMoveData geneComponentModelMoveData;

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
    public GeneComponentModel<EditedCrudPair<String,MoveData>> build(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent<EditedCrudPair<String,MoveData>> _facade) {
        geneComponentModelMoveData = new GeneComponentModelMoveData(_frame,_core, _facade.getFacadeGame(), _facade.getSubscription());
        return geneComponentModelMoveData;
    }

    @Override
    public void removeOpenSub(CrudGeneFormSubContent<EditedCrudPair<String,MoveData>> _base) {
        geneComponentModelMoveData.getTypesByOwnedItem().getCrudGeneFormSubContent().removeOpenSub();
        geneComponentModelMoveData.getTypesByWeather().getCrudGeneFormSubContent().removeOpenSub();
        geneComponentModelMoveData.getSecEffectsByItem().getCrudGeneFormSubContent().removeOpenSub();
        geneComponentModelMoveData.getEffects().getCrudGeneFormSubContent().removeOpenSub();
        _base.removeOpenSub();
    }

    @Override
    public IdList<SubscribedTranslation> all() {
        return geneComponentModelMoveData.all();
    }

    @Override
    public StringList mids(FacadeGame _facade) {
        return _facade.getData().movesPart();
    }
}
