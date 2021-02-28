package cards.belote.beans;

import code.formathtml.Configuration;
import code.scripts.confs.BeloteScriptPages;

public final class DetailsBeloteLoader extends AbstractBeloteLoader {
    @Override
    public void initConf(Configuration _configuration) {
        BeloteScriptPages.initConfDetail(_configuration);
    }
}
