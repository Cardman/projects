package aiki.gui.threads;

import aiki.beans.PokemonStandards;
import code.bean.nat.NativeConfigurationLoader;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.bean.nat.BeanNatLgNames;
import code.formathtml.util.DualAnalyzedContext;
import code.resources.ResourceFiles;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public final class PreparedRenderedPages implements Runnable {
    private Navigation navigation;
    private String relative;
    private String conf;
    private BeanNatLgNames beanNatLgNames;
    private ContextEl context;

    public PreparedRenderedPages(String _relative, String _conf) {
        relative = _relative;
        conf = _conf;
    }

    @Override
    public void run() {
        navigation = new Navigation();
        navigation.setSession(new Configuration());
        navigation.setLanguages(Constants.getAvailableLanguages());
        PokemonStandards stds_ = new PokemonStandards();
        beanNatLgNames = stds_;
        String content_ = ResourceFiles.ressourceFichier(conf);
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(stds_);
        DualAnalyzedContext du_ = navigation.loadConfiguration(content_, "", stds_, DefaultFileBuilder.newInstance(stds_.getContent()), nat_);
        context = du_.getContext().getContext();
        StringMap<String> files_ = new StringMap<String>();
        Configuration session_ = navigation.getSession();
        for (String a: du_.getContext().getAddedFiles()) {
            String name_ = StringList.concat(relative, a);
            files_.put(a,ResourceFiles.ressourceFichier(name_));
        }
        for (String l: navigation.getLanguages()) {
            for (String a: du_.getContext().getProperties().values()) {
                String folder_ = du_.getContext().getMessagesFolder();
                String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_,l,a);
                files_.put(fileName_,ResourceFiles.ressourceFichier(StringList.concat(relative,fileName_)));
            }
        }
        String realFilePath_ = session_.getFirstUrl();
        String rel_ = StringList.concat(relative,realFilePath_);
        files_.put(realFilePath_,ResourceFiles.ressourceFichier(rel_));
        navigation.setFiles(files_);
        stds_.setupAll(navigation, navigation.getSession(), navigation.getFiles(), du_);
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public ContextEl getContext() {
        return context;
    }

    public BeanNatLgNames getBeanNatLgNames() {
        return beanNatLgNames;
    }

}
