package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class SubscribedTranslationMessagesFactoryCommonParam<T> extends SubscribedTranslationMessagesFactoryCommon {
    @Override
    public boolean contains(FacadeGame _facade, String _key) {
        return all(_facade).contains(_key);
    }
    public abstract StringMap<T> all(FacadeGame _facade);
    public abstract GeneComponentModel<EditedCrudPair<String,T>> build(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent<EditedCrudPair<String,T>> _facade);

    public abstract void removeOpenSub(CrudGeneFormSubContent<EditedCrudPair<String,T>> _base);
    public abstract IdList<SubscribedTranslation> all();
    public abstract void delete(FacadeGame _facade, String _key);
    public abstract void completeQuickMembers(FacadeGame _facade, String _key, T _value);
}
