package cards.gui.animations;

import cards.gui.dialogs.PreparedAnalyzedCards;
import code.bean.nat.AbstractNativeInit;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatNavigation;
import code.sml.Document;
import code.util.StringMap;
import code.util.consts.Constants;

public abstract class AbstractPreparedPagesCards implements PreparedAnalyzedCards {
    private final String lg;
    private final BeanNatCommonLgNames beanNatLgNames;
    private NatNavigation navigation;
    private final StringMap<Document> built;

    protected AbstractPreparedPagesCards(String _lg, BeanNatCommonLgNames _stds, StringMap<Document> _built) {
        lg = _lg;
        beanNatLgNames = _stds;
        built = _built;
    }

    public void prepareDoc(AbstractNativeInit _init, StringMap<String> _other) {
        navigation = beanNatLgNames.nav(Constants.getAvailableLanguages(),lg,_init,built,_other,_other,"");
    }

    @Override
    public NatNavigation getNavigation() {
        return navigation;
    }

    protected void setNavigation(NatNavigation _navigation) {
        navigation = _navigation;
    }

    protected String getLg() {
        return lg;
    }

    @Override
    public BeanNatCommonLgNames getBeanNatLgNames() {
        return beanNatLgNames;
    }
}
