package code.formathtml.nat;

import code.bean.nat.AbstractNativeInit;
import code.formathtml.Configuration;
import code.formathtml.structs.BeanInfo;
import code.formathtml.util.DualConfigurationContext;

public final class SampleNativeInit implements AbstractNativeInit {
    @Override
    public void initConf(Configuration _configuration) {
        _configuration.setFirstUrl("page2.html");
        _configuration.setPrefix("c");
        BeanInfo biOne_ = new BeanInfo();
        biOne_.setClassName("code.formathtml.classes.BeanOne");
        biOne_.setScope("session");
        _configuration.getBeansInfos().addEntry("bean_one",biOne_);
        BeanInfo biTwo_ = new BeanInfo();
        biTwo_.setClassName("code.formathtml.classes.BeanTwo");
        biTwo_.setScope("session");
        _configuration.getBeansInfos().addEntry("bean_two",biTwo_);
    }

    @Override
    public void initAna(DualConfigurationContext _d) {
        _d.setMessagesFolder("messages");
        _d.getProperties().addEntry("msg_cust","sample/file");
        _d.getAddedFiles().add("page1.html");
        _d.getAddedFiles().add("page2.html");
        _d.getRenderFiles().add("page1.html");
        _d.getRenderFiles().add("page2.html");
    }
}
