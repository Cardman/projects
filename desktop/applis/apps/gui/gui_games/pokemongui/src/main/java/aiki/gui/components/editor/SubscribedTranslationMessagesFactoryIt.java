package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.items.Item;
import code.gui.AbsCommonFrame;
import code.gui.EditedCrudPair;
import code.gui.GeneComponentModel;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryIt extends SubscribedTranslationMessagesFactoryCommonParam<Item> {

    private GeneComponentModelItem geneComponentModelItem;

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedItems();
    }

    @Override
    public void rename(FacadeGame _facade, String _previous, String _next) {
        _facade.getData().renameItem(_previous,_next);
    }

    @Override
    public StringMap<Item> all(FacadeGame _facade) {
        return _facade.getData().getItems();
    }

    @Override
    public void delete(FacadeGame _facade, String _key) {
        _facade.getData().deleteItem(_key);
    }

    @Override
    public void completeQuickMembers(FacadeGame _facade, String _key, Item _value) {
        _facade.getData().completeQuickMembers(_key,_value);
    }

    @Override
    public GeneComponentModel<EditedCrudPair<String,Item>> build(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent<EditedCrudPair<String,Item>> _facade) {
        geneComponentModelItem = new GeneComponentModelItem(_frame,_core, _facade.getFacadeGame(), _facade.getSubscription());
        return geneComponentModelItem;
    }

    @Override
    public void removeOpenSub(CrudGeneFormSubContent<EditedCrudPair<String,Item>> _base) {
        _base.removeOpenSub();
    }

    @Override
    public IdList<SubscribedTranslation> all() {
        return geneComponentModelItem.all();
    }

    @Override
    public StringList mids(FacadeGame _facade) {
        return new StringList();
    }
}
