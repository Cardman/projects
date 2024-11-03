package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.AbsCommonFrame;
import code.gui.AbsCrudGeneForm;
import code.util.*;

public final class CrudGeneFormSubContent {
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscription;
    private final IdList<SubscribedTranslation> subscribedTranslations;
    private final IdList<SubscribedTranslation> subscribedTranslationsForm;
    private final CrudGeneFormSubUp visited;

    public CrudGeneFormSubContent(FacadeGame _facade, SubscribedTranslationList _sub, CrudGeneFormSubUp _current, AbsCommonFrame _fr) {
        facadeGame = _facade;
        subscription = _sub;
        subscribedTranslations = _sub.getSubscribedTranslations().getVal(_fr);
        subscribedTranslationsForm = new IdList<SubscribedTranslation>();
        visited = _current;
    }

    public void clear() {
        subscribedTranslationsForm.clear();
    }

    public SubscribedTranslationList getSubscription() {
        return subscription;
    }

    public FacadeGame getFacadeGame() {
        return facadeGame;
    }

    public void selectOrAdd(CustList<SubscribedTranslation> _subs) {
        for (SubscribedTranslation s: _subs) {
            subscribedTranslations.add(s);
            subscribedTranslationsForm.add(s);
        }
    }

    public void removeOpenSub() {
        subscribedTranslations.removeAllElements(subscribedTranslationsForm);
    }

    public void subscribeAll() {
        visited.subscribeAll(visited.subscribe());
    }
    public void afterChange(AbsCrudGeneForm _c) {
        removeOpenSub();
        visited.subscribeAll(visited.subscribe());
        _c.afterModif();
    }
    public void clearSub() {
        subscribedTranslationsForm.clear();
    }
    public void addAllSub(IdList<SubscribedTranslation> _sub) {
        for (SubscribedTranslation s:_sub) {
            addSub(s);
        }
    }
    public void addSub(SubscribedTranslation _sub) {
        subscribedTranslations.add(_sub);
        subscribedTranslationsForm.add(_sub);
    }
    public void addSubRoot(SubscribedTranslation _sub) {
        subscribedTranslations.add(_sub);
    }
}
