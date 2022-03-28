package code.bean.nat;

import code.formathtml.Configuration;

public interface AbstractNativeInit {
    void initConf(Configuration _configuration, NatDualConfigurationContext _context);
    void initAna(NatDualConfigurationContext _d);
}
