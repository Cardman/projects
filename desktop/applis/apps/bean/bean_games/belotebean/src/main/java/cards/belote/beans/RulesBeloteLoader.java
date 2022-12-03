package cards.belote.beans;

import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.analyze.NatConfigurationCore;
import code.scripts.confs.BeloteScriptPages;

public final class RulesBeloteLoader extends AbstractBeloteLoader {
    @Override
    public void initConf(NatConfigurationCore _configuration, NatDualConfigurationContext _context) {
        BeloteScriptPages.initConfRules(_configuration);
    }
}
