package cards.belote.beans;

import code.bean.nat.NatDualConfigurationContext;
import code.formathtml.Configuration;
import code.scripts.confs.BeloteScriptPages;

public final class ResultsBeloteLoader extends AbstractBeloteLoader {
    @Override
    public void initConf(Configuration _configuration, NatDualConfigurationContext _context) {
        BeloteScriptPages.initConfResults(_configuration);
    }
}
