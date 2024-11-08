package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.items.Item;
import code.gui.AbsCommonFrame;
import code.gui.GeneComponentModel;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryIt extends SubscribedTranslationMessagesFactoryCommonParam<Item> {

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
    public GeneComponentModel<Item> build(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent _facade) {
        return new GeneComponentModelItem(_core);
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
