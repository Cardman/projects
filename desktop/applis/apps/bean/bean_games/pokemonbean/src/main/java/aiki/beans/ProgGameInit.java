package aiki.beans;

import code.bean.nat.AbstractNativeInit;
import code.formathtml.Configuration;
import code.formathtml.util.DualConfigurationContext;
import code.scripts.confs.PkScriptPages;
import code.scripts.confs.PkScriptPagesInit;

public final class ProgGameInit implements AbstractNativeInit {
    @Override
    public void initConf(Configuration _configuration) {
        PkScriptPagesInit.initConfProg(_configuration);
    }

    @Override
    public void initAna(DualConfigurationContext _d) {
        PkScriptPages.initAnaProg(_d);
    }
}
