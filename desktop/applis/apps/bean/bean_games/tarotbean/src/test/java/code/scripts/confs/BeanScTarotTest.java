package code.scripts.confs;

import code.bean.nat.NatDualConfigurationContext;
import code.formathtml.Configuration;
import code.scripts.pages.cards.MessTarotPage;
import code.scripts.pages.cards.PagesTarots;
import org.junit.Test;

public final class BeanScTarotTest extends EquallableTarotBeanUtil {
    @Test
    public void cards(){
        assertNotNull(PagesTarots.build());
        assertNotNull(PagesTarots.buildRules());
        assertNotNull(PagesTarots.buildDetails());
    }

    @Test
    public void tarot(){
//        assertNotNull(TarotScriptPages.infos());
        Configuration configuration_ = new Configuration();
        TarotScriptPages.initConfDetail(configuration_);
        TarotScriptPages.initConfResults(configuration_);
        TarotScriptPages.initConfRules(configuration_);
        assertNotNull(configuration_.getBeansInfos());
        NatDualConfigurationContext d_ = new NatDualConfigurationContext();
        TarotScriptPages.initAna(d_);
        assertNotNull(d_.getMessagesFolder());
        assertNotNull(MessTarotPage.ms());
    }

}
