package cards.president.beans;

import code.bean.nat.AbstractNativeInit;
import code.bean.nat.NatDualConfigurationContext;
import code.formathtml.util.DualConfigurationContext;
import code.scripts.confs.PresidentScriptPages;

public abstract class AbstractPresidentLoader implements AbstractNativeInit {
    @Override
    public void initAna(NatDualConfigurationContext _d) {
        PresidentScriptPages.initAna(_d);
    }
}
