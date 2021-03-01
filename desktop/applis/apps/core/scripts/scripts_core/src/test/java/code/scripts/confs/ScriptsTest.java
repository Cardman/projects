package code.scripts.confs;

import code.formathtml.Configuration;
import code.formathtml.util.DualConfigurationContext;
import org.junit.Test;

public final class ScriptsTest extends EquallableExUtil {
    @Test
    public void belote(){
//        assertNotNull(BeloteScriptPages.infos());
        Configuration configuration_ = new Configuration();
        BeloteScriptPages.initConfDetail(configuration_);
        BeloteScriptPages.initConfResults(configuration_);
        BeloteScriptPages.initConfRules(configuration_);
        assertNotNull(configuration_.getBeansInfos());
        DualConfigurationContext d_ = new DualConfigurationContext();
        BeloteScriptPages.initAna(d_);
        assertNotNull(d_.getMessagesFolder());
    }

    @Test
    public void president(){
//        assertNotNull(PresidentScriptPages.infos());
        Configuration configuration_ = new Configuration();
        PresidentScriptPages.initConfResults(configuration_);
        PresidentScriptPages.initConfRules(configuration_);
        assertNotNull(configuration_.getBeansInfos());
        DualConfigurationContext d_ = new DualConfigurationContext();
        PresidentScriptPages.initAna(d_);
        assertNotNull(d_.getMessagesFolder());
    }
    @Test
    public void tarot(){
//        assertNotNull(TarotScriptPages.infos());
        Configuration configuration_ = new Configuration();
        TarotScriptPages.initConfDetail(configuration_);
        TarotScriptPages.initConfResults(configuration_);
        TarotScriptPages.initConfRules(configuration_);
        assertNotNull(configuration_.getBeansInfos());
        DualConfigurationContext d_ = new DualConfigurationContext();
        TarotScriptPages.initAna(d_);
        assertNotNull(d_.getMessagesFolder());
    }
    @Test
    public void pk() {
        Configuration configuration_ = new Configuration();
        PkScriptPagesInit.initConfData(configuration_);
        PkScriptPagesInit.initConfFight(configuration_);
        PkScriptPagesInit.initConfDetPk(configuration_);
        PkScriptPagesInit.initConfDiff(configuration_);
        PkScriptPagesInit.initConfProg(configuration_);
        assertNotNull(configuration_.getBeansInfos());
        DualConfigurationContext d_ = new DualConfigurationContext();
        PkScriptPages.initAnaData(d_);
        PkScriptPages.initAnaFight(d_);
        PkScriptPages.initAnaDetPk(d_);
        PkScriptPages.initAnaDiff(d_);
        PkScriptPages.initAnaProg(d_);
        assertNotNull(d_.getMessagesFolder());
    }
    @Test
    public void help() {
        assertNotNull(HelpScriptConfPages.infoLg());
        assertNotNull(HelpScriptPages.docs());
    }
}
