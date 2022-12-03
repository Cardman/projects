package cards.tarot.beans;

import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.analyze.NatConfigurationCore;
import code.scripts.confs.TarotScriptPages;

public final class ResultsTarotLoader extends AbstractTarotLoader {
    @Override
    public void initConf(NatConfigurationCore _configuration, NatDualConfigurationContext _context) {
        TarotScriptPages.initConfResults(_configuration);
    }
}
