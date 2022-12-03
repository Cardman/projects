package code.bean.nat;

import code.bean.nat.analyze.NatConfigurationCore;

public interface AbstractNativeInit {
    void initConf(NatConfigurationCore _configuration, NatDualConfigurationContext _context);
    void initAna(NatDualConfigurationContext _d);
}
