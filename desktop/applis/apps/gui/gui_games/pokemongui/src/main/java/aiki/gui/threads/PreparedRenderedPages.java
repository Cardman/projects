package aiki.gui.threads;

import aiki.beans.PokemonStandards;
import code.bean.nat.AbstractNativeInit;
import code.bean.nat.NatNavigation;
import code.sml.Document;
import code.sml.util.*;
import code.util.*;

public final class PreparedRenderedPages {
    private final AbstractNativeInit init;
    private final PokemonStandards stds;
    private final StringList availableLanguages;
    private NatNavigation navigation;
    private final String relative;
    private PokemonStandards beanNatLgNames;
    private final StringMap<Document> built;
    private final StringMap<TranslationsAppli> builtMessages;
    private final StringMap<String> builtOther;

    public PreparedRenderedPages(String _relative, AbstractNativeInit _init, StringMap<Document> _build, StringMap<TranslationsAppli> _builtMessages, StringMap<String> _builtOther, PokemonStandards _stds, StringList _lgs) {
        relative = _relative;
        init = _init;
        built = _build;
        builtMessages = _builtMessages;
        builtOther = _builtOther;
        stds = _stds;
        availableLanguages = _lgs;
    }

    public void run() {
        beanNatLgNames = stds;
        navigation = stds.nav(availableLanguages,"",init,built,builtOther,builtMessages,relative);
    }

    public NatNavigation getNavigation() {
        return navigation;
    }

    public PokemonStandards getBeanNatLgNames() {
        return beanNatLgNames;
    }

}
