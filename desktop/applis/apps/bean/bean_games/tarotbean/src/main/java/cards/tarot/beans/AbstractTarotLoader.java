package cards.tarot.beans;

import code.bean.nat.AbstractNativeInit;
import code.formathtml.util.DualConfigurationContext;
import code.scripts.confs.TarotScriptPages;

public abstract class AbstractTarotLoader implements AbstractNativeInit {
    @Override
    public void initAna(DualConfigurationContext _d) {
        TarotScriptPages.initAna(_d);
    }
}
