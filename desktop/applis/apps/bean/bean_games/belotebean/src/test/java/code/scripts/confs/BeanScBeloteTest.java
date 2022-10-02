package code.scripts.confs;

import code.bean.nat.NatDualConfigurationContext;
import code.formathtml.Configuration;
import code.scripts.pages.cards.MessBelotePage;
import code.scripts.pages.cards.PagesBelotes;
import org.junit.Test;

public final class BeanScBeloteTest extends EquallableBeloteBeanUtil {
    @Test
    public void cards(){
        assertNotNull(PagesBelotes.build());
        assertNotNull(PagesBelotes.buildDetails());
    }
    @Test
    public void belote(){
//        assertNotNull(BeloteScriptPages.infos());
        Configuration configuration_ = new Configuration();
        BeloteScriptPages.initConfDetail(configuration_);
        BeloteScriptPages.initConfResults(configuration_);
        BeloteScriptPages.initConfRules(configuration_);
        assertNotNull(configuration_.getBeansInfos());
        NatDualConfigurationContext d_ = new NatDualConfigurationContext();
        BeloteScriptPages.initAna(d_);
        assertNotNull(d_.getMessagesFolder());
        assertNotNull(MessBelotePage.ms());
    }
}
