package cards.tarot.beans;

import code.bean.nat.AbstractNativeInit;
import code.bean.nat.NatDualConfigurationContext;
import code.formathtml.util.DualConfigurationContext;
import code.scripts.confs.TarotScriptPages;

public abstract class AbstractTarotLoader implements AbstractNativeInit {
    @Override
    public void initAna(NatDualConfigurationContext _d) {
        TarotScriptPages.initAna(_d);
    }
}
