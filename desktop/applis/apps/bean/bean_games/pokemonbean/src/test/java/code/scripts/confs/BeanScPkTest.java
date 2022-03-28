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
        d_.setNavigation(PkScriptPagesInit.initConfDetPk(configuration_));
        d_.setNavigation(PkScriptPagesInit.initConfDiff(configuration_));
        d_.setNavigation(PkScriptPagesInit.initConfProg(configuration_));
        assertNotNull(configuration_.getBeansInfos());
        PkScriptPages.initAnaData(d_);
        PkScriptPages.initAnaFight(d_);
        PkScriptPages.initAnaDetPk(d_);
        PkScriptPages.initAnaDiff(d_);
        PkScriptPages.initAnaProg(d_);
        assertNotNull(d_.getMessagesFolder());
        assertNotNull(PagesInit.build());
        assertNotNull(MessagesInit.ms());
        assertNotNull(CssInit.ms());
        assertNotNull(new BeanPageAikiSample().self());
    }
}
