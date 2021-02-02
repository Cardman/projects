package cards.gui.animations;

import code.bean.nat.BeanNatLgNames;
import code.bean.nat.NativeConfigurationLoader;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.formathtml.util.DualAnalyzedContext;
import code.gui.document.PreparedAnalyzed;
import code.resources.ResourceFiles;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.StringUtil;

public abstract class AbstractPreparedPagesCards implements PreparedAnalyzed {
    private static final String SEPARATOR_PATH = "/";
    private static final String IMPLICIT_LANGUAGE = "//";
    private final String conf;
    private final String lg;
    private final BeanNatLgNames beanNatLgNames;
    private ContextEl context;
    private Navigation navigation;

    public AbstractPreparedPagesCards(String _conf, String _lg, BeanNatLgNames _stds) {
        conf = _conf;
        lg = _lg;
        beanNatLgNames = _stds;
    }

    public BeanNatLgNames prepare() {
        navigation = new Navigation();
        navigation.setSession(new Configuration());
        navigation.setLanguage(lg);
        navigation.setLanguages(Constants.getAvailableLanguages());
        String content_ = ResourceFiles.ressourceFichier(conf);
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(beanNatLgNames);
        DualAnalyzedContext du_ = navigation.loadConfiguration(content_, "", beanNatLgNames, DefaultFileBuilder.newInstance(beanNatLgNames.getContent()), nat_);
        context = du_.getContext().getContext();
        StringMap<String> files_ = new StringMap<String>();
        Configuration session_ = navigation.getSession();
        for (String a: du_.getContext().getAddedFiles()) {
            files_.put(a,ResourceFiles.ressourceFichier(a));
        }
        for (String l: navigation.getLanguages()) {
            for (String a: du_.getContext().getProperties().values()) {
                String folder_ = du_.getContext().getMessagesFolder();
                String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_,l,a);
                files_.put(fileName_,ResourceFiles.ressourceFichier(fileName_));
            }
        }
        String realFilePath_ = getRealFilePath(lg, session_.getFirstUrl());
        files_.put(realFilePath_,ResourceFiles.ressourceFichier(realFilePath_));
        session_.setFirstUrl(realFilePath_);
        navigation.setFiles(files_);
        beanNatLgNames.setupAll(navigation, navigation.getSession(), navigation.getFiles(), du_);
        return beanNatLgNames;
    }

    private static String getRealFilePath(String _lg, String _link) {
        return StringUtil.replace(_link, IMPLICIT_LANGUAGE, StringUtil.concat(SEPARATOR_PATH,_lg,SEPARATOR_PATH));
    }
    @Override
    public Navigation getNavigation() {
        return navigation;
    }

    @Override
    public ContextEl getContext() {
        return context;
    }

    @Override
    public BeanNatLgNames getBeanNatLgNames() {
        return beanNatLgNames;
    }
}
