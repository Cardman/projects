package code.formathtml.nat;

import code.bean.nat.AbstractNativeInit;
import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.analyze.NatConfigurationCore;

public final class SampleNativeInit implements AbstractNativeInit {
    @Override
    public void initConf(NatConfigurationCore _configuration, NatDualConfigurationContext _context) {
        _configuration.setFirstUrl("page2.html");
        _configuration.setPrefix("c");
//        BeanInfo biOne_ = new BeanInfo();
//        cl(biOne_, "code.formathtml.classes.BeanOne");
//        biOne_.setScope("session");
        _configuration.getBeansInfos().addEntry("bean_one","code.formathtml.classes.BeanOne");
//        BeanInfo biTwo_ = new BeanInfo();
//        cl(biTwo_, "code.formathtml.classes.BeanTwo");
//        biTwo_.setScope("session");
        _configuration.getBeansInfos().addEntry("bean_two","code.formathtml.classes.BeanTwo");
    }

//    private void cl(BeanInfo _bi, String _className) {
//        _bi.setClassName(_className);
//        _bi.setResolvedClassName(_className);
//    }

    @Override
    public void initAna(NatDualConfigurationContext _d) {
        _d.setMessagesFolder("messages");
        _d.getProperties().addEntry("msg_cust","sample/file");
        _d.getAddedFiles().add("page1.html");
        _d.getAddedFiles().add("page2.html");
        _d.getRenderFiles().add("page1.html");
        _d.getRenderFiles().add("page2.html");
    }
}
