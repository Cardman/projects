package code.bean.nat;

import code.expressionlanguage.fwd.Forwards;
import code.formathtml.Configuration;

public final class NativeConfigurationLoader {
    private final BeanNatCommonLgNames stds;
    private final AbstractNativeInit init;

    public NativeConfigurationLoader(BeanNatCommonLgNames _stds, AbstractNativeInit _init) {
        this.stds = _stds;
        init = _init;
    }

    public Forwards getForwards() {
        return stds.setupNative(stds.getNatCode());
    }

    public NatDualConfigurationContext getDualConfigurationContext(Configuration _configuration) {
        NatDualConfigurationContext d_ = new NatDualConfigurationContext();
        specificLoadBegin(_configuration, d_);
        return d_;
    }

    public void specificLoadBegin(Configuration _configuration, NatDualConfigurationContext _context) {
        init.initConf(_configuration,_context);
        init.initAna(_context);
    }
}
