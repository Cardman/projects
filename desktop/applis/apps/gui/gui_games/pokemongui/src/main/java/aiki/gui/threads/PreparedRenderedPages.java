package aiki.gui.threads;

import aiki.beans.PokemonStandards;
import code.bean.nat.AbstractNativeInit;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.NativeConfigurationLoader;
import code.bean.nat.fwd.AdvNatBlockBuilder;
import code.formathtml.Configuration;
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
        navigation = new Navigation();
        navigation.setSession(new Configuration());
        navigation.setLanguages(Constants.getAvailableLanguages());
        beanNatLgNames = stds;
//        String content_ = ResourceFiles.ressourceFichier(conf);
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(stds, init);
        Configuration session_ = new Configuration();
        NatDualConfigurationContext d_ = nat_.getDualConfigurationContext(session_);
        nat_.getForwards();
        d_.init(session_);
        navigation.setSession(session_);
        StringMap<String> files_ = NatDualConfigurationContext.files(navigation, d_, builtOther,builtMessages,relative);
        StringMap<Document> docs_ = NatDualConfigurationContext.docs(built, relative);
        navigation.setFiles(files_);
        stds.setupAll(docs_,navigation, navigation.getSession(), new AdvNatBlockBuilder(stds), d_);
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public BeanNatCommonLgNames getBeanNatLgNames() {
        return beanNatLgNames;
    }

}
