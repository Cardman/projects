package cards.gui.animations;

import code.bean.nat.AbstractNativeInit;
import code.bean.nat.BeanNatLgNames;
import code.bean.nat.NativeConfigurationLoader;
import code.bean.nat.fwd.DefNatBlockBuilder;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.formathtml.util.AbstractConfigurationLoader;
import code.formathtml.util.DualAnalyzedContext;
import code.gui.document.PreparedAnalyzed;
import code.sml.Document;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.StringUtil;

public abstract class AbstractPreparedPagesCards implements PreparedAnalyzed {
    private static final String SEPARATOR_PATH = "/";
    private static final String IMPLICIT_LANGUAGE = "//";
    private final String lg;
    private final BeanNatLgNames beanNatLgNames;
    private ContextEl context;
    private Navigation navigation;
    private final StringMap<Document> built;

    public AbstractPreparedPagesCards(String _lg, BeanNatLgNames _stds, StringMap<Document> _built) {
        lg = _lg;
        beanNatLgNames = _stds;
        built = _built;
    }

    public void prepareDoc(Document _doc, AbstractNativeInit _init, StringMap<String> _other) {
        navigation = new Navigation();
        navigation.setSession(new Configuration());
        navigation.setLanguage(lg);
        navigation.setLanguages(Constants.getAvailableLanguages());
        AbstractConfigurationLoader nat_ = new NativeConfigurationLoader(beanNatLgNames, _init);
        DualAnalyzedContext du_ = navigation.innerLoad("", beanNatLgNames, DefaultFileBuilder.newInstance(beanNatLgNames.getContent()), nat_,_doc);
        StringMap<String> files_ = new StringMap<String>();
        Configuration session_ = navigation.getSession();
        for (String a : du_.getContext().getAddedFiles()) {
            files_.put(a, _other.getVal(a));
//            files_.put(a, ResourceFiles.ressourceFichier(a));
        }
        for (String l : navigation.getLanguages()) {
            for (String a : du_.getContext().getProperties().values()) {
                String folder_ = du_.getContext().getMessagesFolder();
                String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_, l, a);
//                files_.put(fileName_, ResourceFiles.ressourceFichier(fileName_));
                files_.put(fileName_, _other.getVal(fileName_));
            }
        }
        String realFilePath_ = session_.getFirstUrl();
        StringMap<Document> docs_ = new StringMap<Document>();
        Document doc_ = built.getVal(realFilePath_);
        docs_.addEntry(realFilePath_,doc_);
        session_.setFirstUrl(realFilePath_);
        navigation.setFiles(files_);
        beanNatLgNames.setupAll(docs_,navigation, navigation.getSession(), du_, new DefNatBlockBuilder());
        context = du_.getForwards().generate(du_.getContext().getOptions());
    }

    static String getRealFilePath(String _lg, String _link) {
        return StringUtil.replace(_link, IMPLICIT_LANGUAGE, StringUtil.concat(SEPARATOR_PATH,_lg,SEPARATOR_PATH));
    }
    @Override
    public Navigation getNavigation() {
        return navigation;
    }

    protected void setNavigation(Navigation _navigation) {
        navigation = _navigation;
    }

    protected String getLg() {
        return lg;
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
