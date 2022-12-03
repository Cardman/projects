package code.bean.nat.analyze;

import code.sml.ConfigurationCore;
import code.sml.RendKeyWordsGroup;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatConfigurationCore {

    private static final String SEP = ":";
    private final ConfigurationCore nat = new ConfigurationCore();
    private final RendKeyWordsGroup rendKeyWords = new RendKeyWordsGroup();

    private StringMap<String> beansInfos;
    public NatConfigurationCore() {
        setBeansInfos(new StringMap<String>());
    }

    public void updatePref() {
        nat.setPrefix(StringUtil.concat(nat.getPrefix(), SEP));
    }

    public String getFirstUrl() {
        return nat.getFirstUrl();
    }

    public void setFirstUrl(String _firstUrl) {
        nat.setFirstUrl(_firstUrl);
    }

    public StringMap<String> getBeansInfos() {
        return beansInfos;
    }

    public void setBeansInfos(StringMap<String> _beansInfos) {
        beansInfos = _beansInfos;
    }

    public int getTabWidth() {
        return nat.getTabWidth();
    }


    public String getPrefix() {
        return nat.getPrefix();
    }

    public void setPrefix(String _prefix) {
        nat.setPrefix(_prefix);
    }

    public RendKeyWordsGroup getRendKeyWords() {
        return rendKeyWords;
    }

    public String getCurrentLanguage() {
        return nat.getCurrentLanguage();
    }

    public void setCurrentLanguage(String _currentLanguage) {
        nat.setCurrentLanguage(_currentLanguage);
    }


    public void setFiles(StringMap<String> _files) {
        nat.setFiles(_files);
    }

    public StringMap<String> getFiles() {
        return nat.getFiles();
    }

    public ConfigurationCore getNat() {
        return nat;
    }
}
