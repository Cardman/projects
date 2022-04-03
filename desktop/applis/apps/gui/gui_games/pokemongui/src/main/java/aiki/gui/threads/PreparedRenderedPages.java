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
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.StringUtil;

public final class PreparedRenderedPages implements PreparedAnalyzed {
    private final AbstractNativeInit init;
    private Navigation navigation;
    private final String relative;
    private BeanNatCommonLgNames beanNatLgNames;
    private final StringMap<Document> built;
    private final StringMap<String> builtMessages;
    private final StringMap<String> builtOther;

    public PreparedRenderedPages(String _relative, AbstractNativeInit _init, StringMap<Document> _build, StringMap<String> _builtMessages, StringMap<String> _builtOther) {
        relative = _relative;
        init = _init;
        built = _build;
        builtMessages = _builtMessages;
        builtOther = _builtOther;
    }

    @Override
    public void run() {
        navigation = new Navigation();
        navigation.setSession(new Configuration());
        navigation.setLanguages(Constants.getAvailableLanguages());
        PokemonStandards stds_ = new PokemonStandards();
        beanNatLgNames = stds_;
//        String content_ = ResourceFiles.ressourceFichier(conf);
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(stds_, init);
        Configuration session_ = new Configuration();
        NatDualConfigurationContext d_ = nat_.getDualConfigurationContext(session_);
        nat_.getForwards();
        d_.init(session_);
        navigation.setSession(session_);
        //        DualAnalyzedContext du_ = navigation.loadConfiguration(content_, "", stds_, DefaultFileBuilder.newInstance(stds_.getContent()), nat_);
        StringMap<String> files_ = new StringMap<String>();
        StringMap<Document> docs_ = new StringMap<Document>();
        for (String a: d_.getAddedFiles()) {
            String name_ = StringUtil.concat(relative, a);
            Document doc_ = built.getVal(name_);
            if (doc_ != null) {
                docs_.addEntry(a,doc_);
            } else {
                files_.put(a, builtOther.getVal(name_));
//                files_.put(a, ResourceFiles.ressourceFichier(name_));
            }
        }
        for (String l: navigation.getLanguages()) {
            for (String a: d_.getProperties().values()) {
                String folder_ = d_.getMessagesFolder();
                String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_,l,a);
                files_.put(fileName_,builtMessages.getVal(StringUtil.concat(relative,fileName_)));
//                files_.put(fileName_,ResourceFiles.ressourceFichier(StringUtil.concat(relative,fileName_)));
            }
        }
        String realFilePath_ = session_.getFirstUrl();
        String rel_ = StringUtil.concat(relative,realFilePath_);
        docs_.addEntry(realFilePath_,built.getVal(rel_));
//        files_.put(realFilePath_,ResourceFiles.ressourceFichier(rel_));
        navigation.setFiles(files_);
        stds_.setupAll(docs_,navigation, navigation.getSession(), new AdvNatBlockBuilder(stds_), d_);
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public BeanNatCommonLgNames getBeanNatLgNames() {
        return beanNatLgNames;
    }

}
