package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public interface SubscribedTranslationMessagesFactoryCommonInt<T> extends SubscribedTranslationMessagesFactoryCoreMessages<String> {
    StringMap<T> all(FacadeGame _facade);
    GeneComponentModel<EditedCrudPair<String,T>> build(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent<EditedCrudPair<String,T>> _facade);

    void removeOpenSub(CrudGeneFormSubContent<EditedCrudPair<String,T>> _base);
    IdList<SubscribedTranslation> all();
    void delete(FacadeGame _facade, String _key);
}
