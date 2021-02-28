package cards.belote.beans;

import code.formathtml.Configuration;
import code.scripts.confs.BeloteScriptPages;

public final class RulesBeloteLoader extends AbstractBeloteLoader {
    @Override
    public void initConf(Configuration _configuration) {
        BeloteScriptPages.initConfRules(_configuration);
    }
}
