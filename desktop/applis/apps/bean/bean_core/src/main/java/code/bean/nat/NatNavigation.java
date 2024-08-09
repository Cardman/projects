package code.bean.nat;

import code.bean.nat.analyze.NatConfigurationCore;
import code.sml.Document;
import code.sml.NavigationCore;
import code.sml.util.TranslationsAppli;
import code.util.StringList;
import code.util.StringMap;

public final class NatNavigation {

    private NatConfigurationCore session;
    private final NavigationCore bean = new NavigationCore();
    private final StringMap<TranslationsAppli> applis = new StringMap<TranslationsAppli>();

    public StringMap<TranslationsAppli> getApplis() {
        return applis;
    }

    public void setLanguage(String _language) {
        bean.setLanguage(_language);
        session.setCurrentLanguage(_language);
    }

    public boolean setupText(String _text, Document _document) {
        return getBean().setupText(_text,_document,session.getRendKeyWords().getKeyWordsTags().getKeyWordHead(),session.getRendKeyWords().getKeyWordsAttrs().getAttrTitle());
    }

    public NavigationCore getBean() {
        return bean;
    }

    public NatConfigurationCore getSession() {
        return session;
    }

    public void setSession(NatConfigurationCore _session) {
        session = _session;
    }

    public void setFiles(StringMap<String> _files) {
        bean.setFiles(_files);
    }

    public String getLanguage() {
        return bean.getLanguage();
    }

    public String getTitle() {
        return bean.getTitle();
    }

    public StringList getLanguages() {
        return bean.getLanguages();
    }

    public void setLanguages(StringList _languages) {
        bean.setLanguages(_languages);
    }

    public String getCurrentUrl() {
        return bean.getCurrentUrl();
    }

    public void setCurrentUrl(String _v) {
        this.bean.setCurrentUrl(_v);
    }

    public String getCurrentBeanName() {
        return bean.getCurrentBeanName();
    }

    public void setCurrentBeanName(String _v) {
        this.bean.setCurrentBeanName(_v);
    }
    public String getHtmlText() {
        return bean.getHtmlText();
    }

    public Document getDocument() {
        return bean.getDocument();
    }

    public String getReferenceScroll() {
        return bean.getReferenceScroll();
    }

    public StringMap<String> getFiles() {
        return bean.getFiles();
    }
}
