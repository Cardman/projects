package cards.main;

import code.bean.nat.AbstractNativeInit;
import code.bean.nat.BeanNatCommonLgNames;
import code.sml.Document;
import code.threads.IntCallable;
import code.util.StringList;
import code.util.StringMap;

public final class CallablePreparedPagesCards implements IntCallable<CardNatLgNamesNavigation> {

    private final BeanNatCommonLgNames beanNatLgNames;
    private final StringList availableLanguages;
    private final StringMap<Document> built;
    private final AbstractNativeInit init;
    private final StringMap<String> other;

    public CallablePreparedPagesCards(BeanNatCommonLgNames _stds, AbstractNativeInit _init, StringMap<Document> _built, StringMap<String> _other, StringList _lgs) {
        beanNatLgNames = _stds;
        built = _built;
        availableLanguages = _lgs;
        init = _init;
        other = _other;
    }

    @Override
    public CardNatLgNamesNavigation call() {
        return new CardNatLgNamesNavigation(beanNatLgNames,beanNatLgNames.nav(availableLanguages,"",init,built,other,other,""));
    }
}
