package cards.gui.animations;

import code.bean.nat.AbstractNativeInit;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.NativeConfigurationLoader;
import code.bean.nat.fwd.DefNatBlockBuilder;
import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.gui.document.PreparedAnalyzed;
import code.sml.Document;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.StringUtil;

public abstract class AbstractPreparedPagesCards implements PreparedAnalyzed {
    private static final String SEPARATOR_PATH = "/";
    private static final String IMPLICIT_LANGUAGE = "//";
    private final String lg;
    private final BeanNatCommonLgNames beanNatLgNames;
    private Navigation navigation;
    private final StringMap<Document> built;

    protected AbstractPreparedPagesCards(String _lg, BeanNatCommonLgNames _stds, StringMap<Document> _built) {
        lg = _lg;
        beanNatLgNames = _stds;
        built = _built;
    }

    public void prepareDoc(AbstractNativeInit _init, StringMap<String> _other) {
        navigation = new Navigation();
        navigation.setSession(new Configuration());
        navigation.setLanguage(lg);
        navigation.setLanguages(Constants.getAvailableLanguages());
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(beanNatLgNames, _init);
        Configuration session_ = new Configuration();
        NatDualConfigurationContext d_ = nat_.getDualConfigurationContext(session_);
        nat_.getForwards();
        d_.init(session_);
        navigation.setSession(session_);
        StringMap<String> files_ = NatDualConfigurationContext.files(navigation,d_,_other,_other,"");
        String realFilePath_ = session_.getFirstUrl();
        StringMap<Document> docs_ = NatDualConfigurationContext.docs(built,"");
        session_.setFirstUrl(realFilePath_);
        navigation.setFiles(files_);
        beanNatLgNames.setupAll(docs_,navigation, navigation.getSession(), new DefNatBlockBuilder(), d_);
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
    public BeanNatCommonLgNames getBeanNatLgNames() {
        return beanNatLgNames;
    }
}
