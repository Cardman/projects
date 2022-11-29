package aiki.beans;

import code.bean.nat.AbstractNativeInit;
import code.bean.nat.NatDualConfigurationContext;
import code.formathtml.Configuration;
import code.scripts.confs.PkScriptPages;
import code.scripts.confs.PkScriptPagesInit;

public final class DetPkGameInit implements AbstractNativeInit {
    @Override
    public void initConf(Configuration _configuration, NatDualConfigurationContext _context) {
        PkScriptPagesInit.initConfDetPk(_configuration);
    }

    @Override
    public void initAna(NatDualConfigurationContext _d) {
        PkScriptPages.initAnaDetPk(_d);
    }
}
