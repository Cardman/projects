package cards.gui.animations;

import cards.gui.dialogs.PreparedAnalyzedCards;
import code.bean.nat.AbstractNativeInit;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatNavigation;
import code.sml.Document;
import code.util.StringList;
import code.util.StringMap;

public abstract class AbstractPreparedPagesCards implements PreparedAnalyzedCards {
    private final BeanNatCommonLgNames beanNatLgNames;
    private final StringList availableLanguages;
    private NatNavigation navigation;
    private final StringMap<Document> built;

    protected AbstractPreparedPagesCards(BeanNatCommonLgNames _stds, StringMap<Document> _built, StringList _lgs) {
        beanNatLgNames = _stds;
        built = _built;
        availableLanguages = _lgs;
    }

    public void prepareDoc(AbstractNativeInit _init, StringMap<String> _other) {
        navigation = beanNatLgNames.nav(availableLanguages,"",_init,built,_other,_other,"");
    }

    @Override
    public NatNavigation getNavigation() {
        return navigation;
    }

    protected void setNavigation(NatNavigation _navigation) {
        navigation = _navigation;
    }

    @Override
    public BeanNatCommonLgNames getBeanNatLgNames() {
        return beanNatLgNames;
    }
}
