package aiki.gui.threads;

import aiki.beans.PokemonStandards;
import code.bean.nat.AbstractNativeInit;
import code.bean.nat.BeanNatCommonLgNames;
import code.formathtml.Navigation;
import code.gui.document.PreparedAnalyzed;
import code.sml.Document;
import code.util.StringMap;
import code.util.consts.Constants;

public final class PreparedRenderedPages implements PreparedAnalyzed {
    private final AbstractNativeInit init;
    private final PokemonStandards stds;
    private Navigation navigation;
    private final String relative;
    private BeanNatCommonLgNames beanNatLgNames;
    private final StringMap<Document> built;
    private final StringMap<String> builtMessages;
    private final StringMap<String> builtOther;

    public PreparedRenderedPages(String _relative, AbstractNativeInit _init, StringMap<Document> _build, StringMap<String> _builtMessages, StringMap<String> _builtOther, PokemonStandards _stds) {
        relative = _relative;
        init = _init;
        built = _build;
        builtMessages = _builtMessages;
        builtOther = _builtOther;
        stds = _stds;
    }

    @Override
    public void run() {
        beanNatLgNames = stds;
        navigation = stds.nav(Constants.getAvailableLanguages(),"",init,built,builtOther,builtMessages,relative);
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public BeanNatCommonLgNames getBeanNatLgNames() {
        return beanNatLgNames;
    }

}
