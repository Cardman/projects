package code.scripts.confs;

import code.bean.nat.NatDualConfigurationContext;
import code.formathtml.Configuration;
import code.scripts.pages.cards.MessPresidentPage;
import code.scripts.pages.cards.PagesPresidents;
import org.junit.Test;

public final class BeanScPresidentTest extends EquallablePresidentBeanUtil {
    @Test
    public void cards(){
        assertNotNull(PagesPresidents.build());
    }

    @Test
    public void president(){
//        assertNotNull(PresidentScriptPages.infos());
        Configuration configuration_ = new Configuration();
        PresidentScriptPages.initConfResults(configuration_);
        PresidentScriptPages.initConfRules(configuration_);
        assertNotNull(configuration_.getBeansInfos());
        NatDualConfigurationContext d_ = new NatDualConfigurationContext();
        PresidentScriptPages.initAna(d_);
        assertNotNull(d_.getMessagesFolder());
        assertNotNull(MessPresidentPage.ms());
    }
}
