package code.bean.nat;

import code.bean.nat.analyze.NatConfigurationCore;

public final class NativeConfigurationLoader {
    private final BeanNatCommonLgNames stds;
    private final AbstractNativeInit init;

    public NativeConfigurationLoader(BeanNatCommonLgNames _stds, AbstractNativeInit _init) {
        this.stds = _stds;
        init = _init;
    }

    public void getForwards() {
        stds.setupNative(stds.getNatCode());
    }

    public NatDualConfigurationContext getDualConfigurationContext(NatConfigurationCore _configuration) {
        NatDualConfigurationContext d_ = new NatDualConfigurationContext();
        specificLoadBegin(_configuration, d_);
        return d_;
    }

    public void specificLoadBegin(NatConfigurationCore _configuration, NatDualConfigurationContext _context) {
        init.initConf(_configuration,_context);
        init.initAna(_context);
    }
}
