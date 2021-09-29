package code.scripts.confs;

import code.formathtml.Configuration;
import code.formathtml.util.DualConfigurationContext;
import code.scripts.messages.aiki.*;
import code.scripts.messages.cards.*;
import code.scripts.messages.gui.*;
import code.scripts.pages.aiki.*;
import code.scripts.pages.cards.*;
import code.scripts.imgs.cards.*;
import org.junit.Test;

public final class ScriptsTest extends EquallableScriptsUtil {
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
        assertNotNull(MessBelotePage.ms());
        assertNotNull(MessBeloteGr.ms());
        assertNotNull(MessagesBeloteBelote.ms());
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
        assertNotNull(MessPresidentPage.ms());
        assertNotNull(MessPresidentGr.ms());
        assertNotNull(MessagesPresidentPresident.ms());
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
        assertNotNull(MessTarotPage.ms());
        assertNotNull(MessTarotGr.ms());
        assertNotNull(MessagesTarotTarot.ms());
    }
    @Test
    public void cards(){
        assertNotNull(PageCards.build());
        assertNotNull(HelpCards.build());
        assertNotNull(CardsInit.ms());
        assertNotNull(MessagesCommonCommon.ms());
        assertNotNull(MessagesSymbolSymbolCards.ms());
        assertNotNull(MessagesGamesGames.ms());
        assertNotNull(MessagesCardsAll.ms());
        assertNotNull(MessagesGuiGui.ms());
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
        assertNotNull(PagesInit.build());
        assertNotNull(MessagesInit.ms());
        assertNotNull(CssInit.ms());
        assertNotNull(MessPkGr.ms());
    }
    @Test
    public void help() {
        assertNotNull(HelpScriptConfPages.infoLg());
        assertNotNull(HelpScriptPages.docs());
    }
    @Test
    public void global(){
        assertNotNull(MessCdmBaseGr.ms());
        assertNotNull(MessCdmGuiGr.ms());
        assertNotNull(MessGuiCardsGr.ms());
        assertNotNull(MessGuiPkGr.ms());
        assertNotNull(MessCdmRenderGr.ms());
        assertNotNull(MessPkVideoGr.ms());
        assertNotNull(MessGuiGr.ms());
        assertNotNull(MessCdmUnitGr.ms());
        assertNotNull(MessPlayerGr.ms());
        assertNotNull(MessCardVideoGr.ms());
        assertNotNull(MessGuiNetGr.ms());
    }
}
