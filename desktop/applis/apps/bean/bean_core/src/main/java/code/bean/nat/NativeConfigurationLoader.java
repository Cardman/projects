package code.bean.nat;

import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.Configuration;
import code.formathtml.util.DualConfigurationContext;

public final class NativeConfigurationLoader {
    private final BeanNatLgNames stds;
    private final AbstractNativeInit init;

    public NativeConfigurationLoader(BeanNatLgNames _stds, AbstractNativeInit _init) {
        this.stds = _stds;
        init = _init;
    }

    public Forwards getForwards(DualConfigurationContext d_) {
        return stds.setupNative(stds.getNatCode(), d_);
    }

    public DualConfigurationContext getDualConfigurationContext(Configuration _configuration, AbstractFileBuilder _fileBuilder) {
        DualConfigurationContext d_ = new DualConfigurationContext();
        d_.setFileBuilder(_fileBuilder);
        specificLoadBegin(_configuration, d_);
        return d_;
    }

    public void specificLoadBegin(Configuration _configuration, DualConfigurationContext _context) {
        init.initConf(_configuration);
        init.initAna(_context);
    }
}
