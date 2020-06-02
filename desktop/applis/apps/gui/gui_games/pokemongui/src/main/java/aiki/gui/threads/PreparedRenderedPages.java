package aiki.gui.threads;

import aiki.beans.PokemonStandards;
import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.bean.nat.BeanNatLgNames;
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
        navigation.loadConfiguration(content_,"", stds_);
        StringMap<String> files_ = new StringMap<String>();
        Configuration session_ = navigation.getSession();
        for (String a: session_.getAddedFiles()) {
            String name_ = StringList.concat(relative, a);
            files_.put(a,ResourceFiles.ressourceFichier(name_));
        }
        for (String l: navigation.getLanguages()) {
            for (String a: session_.getProperties().values()) {
                String folder_ = session_.getMessagesFolder();
                String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_,l,a);
                files_.put(fileName_,ResourceFiles.ressourceFichier(StringList.concat(relative,fileName_)));
            }
        }
        String realFilePath_ = session_.getFirstUrl();
        String rel_ = StringList.concat(relative,realFilePath_);
        files_.put(realFilePath_,ResourceFiles.ressourceFichier(rel_));
        navigation.setFiles(files_);
        navigation.initInstancesPattern();
        navigation.setupRenders();
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public BeanNatLgNames getBeanNatLgNames() {
        return beanNatLgNames;
    }
}
