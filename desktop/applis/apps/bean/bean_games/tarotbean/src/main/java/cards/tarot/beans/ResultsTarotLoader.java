package cards.tarot.beans;

import code.formathtml.Configuration;
import code.scripts.confs.TarotScriptPages;

public final class ResultsTarotLoader extends AbstractTarotLoader {
    @Override
    public void initConf(Configuration _configuration) {
        TarotScriptPages.initConfResults(_configuration);
    }
}
