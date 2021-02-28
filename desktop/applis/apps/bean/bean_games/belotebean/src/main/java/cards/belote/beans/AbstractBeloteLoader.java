package cards.belote.beans;

import code.bean.nat.AbstractNativeInit;
import code.formathtml.util.DualConfigurationContext;
import code.scripts.confs.BeloteScriptPages;

public abstract class AbstractBeloteLoader implements AbstractNativeInit {
    @Override
    public void initAna(DualConfigurationContext _d) {
        BeloteScriptPages.initAna(_d);
    }
}
