package aiki.gui.components.editor;

import code.gui.*;
import code.util.*;

public final class CrudGeneFormEntBuilder<T> {
    public CrudGeneFormEnt<T> build(CustList<AbsCrudGeneFormTrCstOpen> _allWindows, SubscribedTranslationList _subscriptions, EnabledMenu _menu, SubscribedTranslationMessagesFactoryCommonParam<T> _factory) {
        AbsCommonFrame frPk_ = _subscriptions.getProgramInfos().getFrameFactory().newCommonFrame();
        IdList<SubscribedTranslation> subsTm_ = new IdList<SubscribedTranslation>();
        _subscriptions.getSubscribedTranslations().addEntry(frPk_, subsTm_);
        CrudGeneFormEnt<T> crud_ = new CrudGeneFormEnt<T>(_subscriptions.getProgramInfos(), _subscriptions.getFacadeGame(), _subscriptions, frPk_, _factory);
        crud_.getFrame().addWindowListener(new ReinitMenu(_menu, subsTm_));
        _menu.addActionListener(new PkEditorOpenCrudTrCstEvent(crud_,_menu));
        _allWindows.add(crud_);
        return crud_;
    }
}
