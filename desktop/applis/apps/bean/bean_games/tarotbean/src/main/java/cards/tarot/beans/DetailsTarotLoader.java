package cards.tarot.beans;

import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.analyze.NatConfigurationCore;
import code.scripts.confs.TarotScriptPages;

public final class DetailsTarotLoader extends AbstractTarotLoader {
    @Override
    public void initConf(NatConfigurationCore _configuration, NatDualConfigurationContext _context) {
        TarotScriptPages.initConfDetail(_configuration);
    }
}
