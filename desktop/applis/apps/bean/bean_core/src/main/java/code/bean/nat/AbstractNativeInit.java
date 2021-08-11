package code.bean.nat;

import code.formathtml.Configuration;
import code.formathtml.util.DualConfigurationContext;

public interface AbstractNativeInit {
    void initConf(Configuration _configuration);
    void initAna(DualConfigurationContext _d);
}
