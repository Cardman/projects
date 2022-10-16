package code.scripts.confs;

import code.bean.nat.NatDualConfigurationContext;
import code.formathtml.Configuration;
import code.scripts.pages.aiki.CssInit;
import code.scripts.pages.aiki.MessagesInit;
import code.scripts.pages.aiki.PagesInit;
import org.junit.Test;

public final class BeanScPkTest extends EquallablePkBeanUtil {
    @Test
    public void pk() {
        Configuration configuration_ = new Configuration();
        NatDualConfigurationContext d_ = new NatDualConfigurationContext();
        d_.setNavigation(PkScriptPagesInit.initConfData(configuration_));
        d_.setNavigation(PkScriptPagesInit.initConfFight(configuration_));
        assertNotNull(configuration_.getBeansInfos());
        PkScriptPages.initAnaData(d_);
        PkScriptPages.initAnaFight(d_);
        assertNotNull(d_.getMessagesFolder());
        assertNotNull(PagesInit.build());
        assertNotNull(PagesInit.buildFight());
        assertNotNull(MessagesInit.ms());
        assertNotNull(CssInit.ms());
        assertNotNull(new BeanPageAikiSample().self());
    }
}
