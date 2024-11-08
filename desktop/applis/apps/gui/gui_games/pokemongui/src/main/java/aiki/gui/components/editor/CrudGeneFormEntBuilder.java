package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public final class CrudGeneFormEntBuilder<T> {
    public CrudGeneFormEnt<T> build(AbstractProgramInfos _list, FacadeGame _facade, SubscribedTranslationList _subscriptions, EnabledMenu _menu, SubscribedTranslationMessagesFactoryCommonParam<T> _factory) {
        AbsCommonFrame frPk_ = _list.getFrameFactory().newCommonFrame();
        IdList<SubscribedTranslation> subsTm_ = new IdList<SubscribedTranslation>();
        _subscriptions.getSubscribedTranslations().addEntry(frPk_, subsTm_);
        CrudGeneFormEnt<T> crud_ = new CrudGeneFormEnt<T>(_list, _facade, _subscriptions, frPk_, _factory);
        crud_.getFrame().addWindowListener(new ReinitMenu(_menu, subsTm_));
        _menu.addActionListener(new PkEditorOpenCrudPkEvent<T>(crud_,_menu));
        return crud_;
    }
}
