package code.network;

import cards.gui.containers.*;
import cards.gui.dialogs.*;
import cards.network.threads.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class ContainerMultiPresidentTest extends EquallableNetworkUtil {
    @Test
    public void intro1() {
        MockGamePresident m_ = new MockGamePresident();
        WindowNetWork server_ = frameSinglePresident(m_);
        serverVersionOld(server_,6);
        retrievedSocket(server_, server_, 0);
        WindowNetWork client_ = frameSinglePresident(m_);
        clientVersionOld(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);
        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(12, serverCompo_.size());
        checkRules((ContainerMultiPresident) server_.getNetg().getContainerGame(),serverCompo_);
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));

        ready(server_, client_, socketClient_);
        netPlayers(server_, client_);
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo2_.size());
        assertTrue(clientCompo2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        ready(server_, client_, socketClient_);
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
        MockGamePresident m_ = new MockGamePresident();
        WindowNetWork server_ = frameSinglePresident(m_);
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        ready(server_, server_, socketServ_);
        netPlayers(server_);

        WindowNetWork client_ = frameSinglePresident(m_);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        ready(server_, server_, socketServ_);
        netPlayers(server_);


        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(12, serverCompo_.size());
        checkRules((ContainerMultiPresident) server_.getNetg().getContainerGame(),serverCompo_);
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));

        choicePosition(server_, client_, server_, socketServ_, 2);

        choicePosition(server_, client_, client_, socketClient_, 2);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        IdList<AbsCustComponent> serverCompo2_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(11, serverCompo2_.size());
        checkRules((ContainerMultiPresident) server_.getNetg().getContainerGame(),serverCompo2_);
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo2_.size());
        assertTrue(clientCompo2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        assertFalse(Net.isProgressingGame(server_.getNet()));
    }
    private void choicePosition(WindowNetWork _server, WindowNetWork _client, WindowNetWork _dest, MockSocket _socket, int _i) {
        _socket.getOutput().clear();
        eventsCombo(((ContainerMulti) _dest.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame(), _i);
        writeToServer(_server, _socket);
        netPlayers(_server, _client);
    }
    private void rules(WindowNetWork _server, MockSocket _soc) {
        _soc.getOutput().clear();
        tryClick(((ContainerMultiPresident) _server.getNetg().getContainerGame()).getSelectRules());
        writeToServer(_server, _soc);
    }

    private void ready(WindowNetWork _server, WindowNetWork _target, MockSocket _soc) {
        _soc.getOutput().clear();
        tryClick(((ContainerMulti) _target.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(_server, _soc);
    }
    private void readyPlayers(WindowNetWork _server, MockSocket _socketServ, WindowNetWork _client, MockSocket _socketClient) {
        _socketServ.getOutput().clear();
        ready(_server, _server, _socketServ);
        netPlayers(_server, _client);

        _socketClient.getOutput().clear();
        ready(_server, _client, _socketClient);
        netPlayers(_server, _client);
    }
    private static void checkRules(ContainerMultiPresident _cont, IdList<AbsCustComponent> _tr) {
        DialogPresidentContent d_ = _cont.getDialogPresidentContent();
        assertTrue(_tr.containsObj(d_.getNbStacks()));
        assertTrue(_tr.containsObj(d_.getCanPass()));
        assertTrue(_tr.containsObj(d_.getLooseFinishBestCards()));
        assertTrue(_tr.containsObj(d_.getLooserStartsFirst()));
        assertTrue(_tr.containsObj(d_.getPossibleReversing()));
        assertTrue(_tr.containsObj(d_.getSwitchCards()));
        assertTrue(_tr.containsObj(d_.getListeChoix().self()));
        assertTrue(_tr.containsObj(d_.getEquality().self()));
        assertTrue(_tr.containsObj(_cont.getSelectRules()));
    }
}
