package cards.tarot.beans;

import code.bean.nat.NatDualConfigurationContext;
import code.formathtml.Configuration;
import code.scripts.confs.TarotScriptPages;

public final class DetailsTarotLoader extends AbstractTarotLoader {
    @Override
    public void initConf(Configuration _configuration, NatDualConfigurationContext _context) {
        TarotScriptPages.initConfDetail(_configuration);
    }
}
