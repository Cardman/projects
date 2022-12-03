package aiki.beans;

import code.bean.nat.AbstractNativeInit;
import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.analyze.NatConfigurationCore;
import code.scripts.confs.PkScriptPages;
import code.scripts.confs.PkScriptPagesInit;

public final class DataGameInit implements AbstractNativeInit {
    @Override
    public void initConf(NatConfigurationCore _configuration, NatDualConfigurationContext _context) {
        PkScriptPagesInit.initConfData(_configuration);
    }

    @Override
    public void initAna(NatDualConfigurationContext _d) {
        PkScriptPages.initAnaData(_d);
    }
}
