package code.scripts.confs;

import code.scripts.messages.aiki.*;
import code.scripts.messages.cards.*;
import code.scripts.messages.gui.*;
import code.scripts.imgs.cards.*;
import org.junit.Test;

public final class ScriptsTest extends EquallableScriptsUtil {
    @Test
    public void belote(){
//        assertNotNull(BeloteScriptPages.infos());
        assertNotNull(MessBeloteGr.ms());
        assertNotNull(MessagesBeloteBelote.ms());
    }

    @Test
    public void president(){
//        assertNotNull(PresidentScriptPages.infos());
        assertNotNull(MessPresidentGr.ms());
        assertNotNull(MessagesPresidentPresident.ms());
    }
    @Test
    public void tarot(){
//        assertNotNull(TarotScriptPages.infos());
        assertNotNull(MessTarotGr.ms());
        assertNotNull(MessagesTarotTarot.ms());
    }
    @Test
    public void cards(){
        assertNotNullStr(CardsInit.ms());
        assertNotNull(MessagesCommonCommon.ms());
        assertNotNull(MessagesSymbolSymbolCards.ms());
        assertNotNull(MessagesGamesGames.ms());
        assertNotNull(MessagesCardsAll.ms());
        assertNotNull(MessagesGuiGui.ms());
    }
    @Test
    public void pk() {
        assertNotNull(MessPkGr.ms());
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
