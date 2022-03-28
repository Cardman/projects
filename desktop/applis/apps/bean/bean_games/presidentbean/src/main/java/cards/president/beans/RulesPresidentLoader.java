package cards.president.beans;

import code.bean.nat.NatDualConfigurationContext;
import code.formathtml.Configuration;
import code.scripts.confs.PresidentScriptPages;

public final class RulesPresidentLoader extends AbstractPresidentLoader {
    @Override
    public void initConf(Configuration _configuration, NatDualConfigurationContext _context) {
        PresidentScriptPages.initConfRules(_configuration);
    }
}
