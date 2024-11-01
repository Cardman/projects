package aiki.gui.components.editor;

import aiki.facade.*;
import code.util.*;

public final class CrudGeneFormSubContent {
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscription;
    private final IdList<SubscribedTranslation> subscribedTranslations;
    private final IdList<SubscribedTranslation> subscribedTranslationsForm;

    public CrudGeneFormSubContent(FacadeGame _facade, SubscribedTranslationList _sub) {
        facadeGame = _facade;
        subscription = _sub;
        subscribedTranslations = _sub.getSubscribedTranslations();
        subscribedTranslationsForm = new IdList<SubscribedTranslation>();
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

}
