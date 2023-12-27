package aiki.gui.threads;

import aiki.beans.PokemonStandards;
import code.bean.nat.AbstractNativeInit;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatNavigation;
import code.gui.document.PreparedAnalyzed;
import code.sml.Document;
import code.util.*;

public final class PreparedRenderedPages implements PreparedAnalyzed {
    private final AbstractNativeInit init;
    private final PokemonStandards stds;
    private final StringList availableLanguages;
    private NatNavigation navigation;
    private final String relative;
    private BeanNatCommonLgNames beanNatLgNames;
    private final StringMap<Document> built;
    private final StringMap<String> builtMessages;
    private final StringMap<String> builtOther;

    public PreparedRenderedPages(String _relative, AbstractNativeInit _init, StringMap<Document> _build, StringMap<String> _builtMessages, StringMap<String> _builtOther, PokemonStandards _stds, StringList _lgs) {
        relative = _relative;
        init = _init;
        built = _build;
        builtMessages = _builtMessages;
        builtOther = _builtOther;
        stds = _stds;
        availableLanguages = _lgs;
    }

    @Override
    public void run() {
        beanNatLgNames = stds;
        navigation = stds.nav(availableLanguages,"",init,built,builtOther,builtMessages,relative);
    }

    public NatNavigation getNavigation() {
        return navigation;
    }

    public BeanNatCommonLgNames getBeanNatLgNames() {
        return beanNatLgNames;
    }

}
