package code.network;

import cards.belote.enumerations.*;
import cards.facade.*;
import cards.gui.containers.*;
import cards.gui.dialogs.*;
import cards.network.threads.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class ContainerMultiBeloteTest extends EquallableNetworkUtil {
    @Test
    public void intro1() {
        MockGameBelote m_ = new MockGameBelote();
        WindowNetWork server_ = frameSingleBelote(m_);
        serverVersionOld(server_,4);
        retrievedSocket(server_, server_, 0);
        WindowNetWork client_ = frameSingleBelote(m_);
        clientVersionOld(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);
        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(22, serverCompo_.size());
        checkRules((ContainerMultiBelote) server_.getNetg().getContainerGame(),serverCompo_);
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));

        tryClick(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketClient_);
        netPlayers(server_, client_);
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo2_.size());
        assertTrue(clientCompo2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        tryClick(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketClient_);
        netPlayers(server_, client_);
        IdList<AbsCustComponent> clientCompo3_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo3_.size());
        assertTrue(clientCompo3_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo3_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        assertFalse(Net.isProgressingGame(server_.getNet()));
    }
    @Test
    public void intro2() {
        MockGameBelote m_ = new MockGameBelote();
        WindowNetWork server_ = frameSingleBelote(m_);
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketServ_);
        netPlayers(server_);

        WindowNetWork client_ = frameSingleBelote(m_);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketServ_);
        netPlayers(server_);


        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(22, serverCompo_.size());
        checkRules((ContainerMultiBelote) server_.getNetg().getContainerGame(),serverCompo_);
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));

        eventsCombo(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame(),2);
        writeToServer(server_, socketServ_);
        netPlayers(server_, client_);

        eventsCombo(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame(),2);
        writeToServer(server_, socketClient_);
        netPlayers(server_, client_);

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketServ_);
        netPlayers(server_, client_);

        tryClick(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketClient_);
        netPlayers(server_, client_);

        IdList<AbsCustComponent> serverCompo2_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(21, serverCompo2_.size());
        checkRules((ContainerMultiBelote) server_.getNetg().getContainerGame(),serverCompo2_);
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo2_.size());
        assertTrue(clientCompo2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        assertFalse(Net.isProgressingGame(server_.getNet()));
    }

    private static void checkRules(ContainerMultiBelote _cont, IdList<AbsCustComponent> _tr) {
        DialogBeloteContent d_ = _cont.getDialogBeloteContent();
        assertTrue(_tr.containsObj(d_.getListeChoixFour().self()));
        assertTrue(_tr.containsObj(d_.getClassic()));
        assertTrue(_tr.containsObj(d_.getUnderTrumpingFoe()));
        assertTrue(_tr.containsObj(d_.getListeChoix().self()));
        assertTrue(_tr.containsObj(d_.getListChoiceTwo().self()));
        assertTrue(_tr.containsObj(d_.getBids().getVal(BidBelote.NO_TRUMP)));
        assertTrue(_tr.containsObj(d_.getBids().getVal(BidBelote.ALL_TRUMP)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.THIRTY)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FIFTY)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.HUNDRED)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_1)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_7)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_8)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_9)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_10)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_JACK)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_QUEEN)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_KING)));
        assertTrue(_tr.containsObj(_cont.getSelectRules()));
    }
}
