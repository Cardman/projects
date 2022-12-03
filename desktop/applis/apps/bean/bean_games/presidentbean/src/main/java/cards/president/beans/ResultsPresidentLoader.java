package cards.president.beans;

import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.analyze.NatConfigurationCore;
import code.scripts.confs.PresidentScriptPages;

public final class ResultsPresidentLoader extends AbstractPresidentLoader {
    @Override
    public void initConf(NatConfigurationCore _configuration, NatDualConfigurationContext _context) {
        PresidentScriptPages.initConfResults(_configuration);
    }
}
