package cards.president.beans;

import code.formathtml.Configuration;
import code.scripts.confs.PresidentScriptPages;

public final class ResultsPresidentLoader extends AbstractPresidentLoader {
    @Override
    public void initConf(Configuration _configuration) {
        PresidentScriptPages.initConfResults(_configuration);
    }
}
