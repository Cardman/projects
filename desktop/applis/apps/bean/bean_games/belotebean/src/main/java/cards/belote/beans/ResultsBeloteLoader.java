package cards.belote.beans;

import code.formathtml.Configuration;
import code.scripts.confs.BeloteScriptPages;

public final class ResultsBeloteLoader extends AbstractBeloteLoader {
    @Override
    public void initConf(Configuration _configuration) {
        BeloteScriptPages.initConfResults(_configuration);
    }
}
