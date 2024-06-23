package code.network;

import cards.facade.enumerations.*;
import cards.gui.containers.*;
import cards.network.threads.*;
import code.gui.*;
import code.mock.*;
import org.junit.Test;

public final class ContainerMultiContentTest extends EquallableNetworkUtil {
    @Test
    public void relative1() {
        assertEq(0,ContainerMultiContent.relative(5,5,8));
    }
    @Test
    public void relative2() {
        assertEq(3,ContainerMultiContent.relative(0,5,8));
    }
    @Test
    public void relative3() {
        assertEq(2,ContainerMultiContent.relative(7,5,8));
    }
    @Test
    public void relative4() {
        assertEq(7,ContainerMultiContent.relative(4,5,8));
    }

    @Test
    public void displayBelote() {
        WindowNetWork f_ = frameDisplay();
        tryClick(f_.getNetg().getDisplayingGames().getVal(GameEnum.BELOTE));
        assertTrue(f_.getDialogDisplayingBelote().getCardDialog().isVisible());
    }

    @Test
    public void displayPresident() {
        WindowNetWork f_ = frameDisplay();
        tryClick(f_.getNetg().getDisplayingGames().getVal(GameEnum.PRESIDENT));
        assertTrue(f_.getDialogDisplayingPresident().getCardDialog().isVisible());
    }

    @Test
    public void displayTarot() {
        WindowNetWork f_ = frameDisplay();
        tryClick(f_.getNetg().getDisplayingGames().getVal(GameEnum.TAROT));
        assertTrue(f_.getDialogDisplayingTarot().getCardDialog().isVisible());
    }
    @Test
    public void quit1() {
        MockGameTarot m_ = new MockGameTarot();
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        click(server_, socketServ_, ((ContainerMulti) server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        deal(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);
        click(server_,socketServ_,server_.getMultiStop());
        loopServer2(server_.getSockets());

        assertFalse(Net.getGames(server_.getNet()).enCoursDePartie());
    }

    @Test
    public void quit2() {
        MockGameTarot m_ = new MockGameTarot();
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        click(server_, socketServ_, ((ContainerMulti) server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        deal(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        socketServ_.getOutput().clear();
        ready(server_, server_, socketServ_);
        loopServer(server_.getSockets());
        socketServ_.getOutput().clear();
        ready(server_, server_, socketServ_);
        loopServer(server_.getSockets());

        click(server_,socketServ_,server_.getMultiStop());
        loopServer2(server_.getSockets());

        assertFalse(Net.getGames(server_.getNet()).enCoursDePartie());
    }
    @Test
    public void quit3() {
        MockGameTarot m_ = new MockGameTarot();
        WindowNetWork server_ = frameSingleTarot(m_);
        serverVersionNew(server_,3);
        retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        clientVersionNew(server_,client_);

        retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        WindowNetWork clientOk_ = frameSingleTarot(m_);
        clientVersionNew(server_,clientOk_);

        WindowNetWork clientKo_ = frameSingleTarot(m_);
        clientVersionNew(server_,clientKo_);
        MockSocket socket_ = (MockSocket) clientKo_.getSocket();
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(true);
        forcedBye_.setClosing(false);
        assertFalse(BasicClient.iterate(socket_,clientKo_,NetCommon.exportExiting(forcedBye_)));


        retrievedSocket(server_, clientOk_, 2);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), clientOk_);
        loopClient(server_.getSockets(),clientOk_);
        assertEq(3,server_.getSockets().getSockets().size());
    }

    @Test
    public void quit4() {
        MockGameTarot m_ = new MockGameTarot();
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        click(server_, socketServ_, ((ContainerMulti) server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        deal(server_, client_);


        WindowNetWork clientKo_ = frameSingleTarot(m_);
        clientVersionNew(server_,clientKo_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        socketServ_.getOutput().clear();
        ready(server_, server_, socketServ_);
        loopServer(server_.getSockets());
        socketServ_.getOutput().clear();
        ready(server_, server_, socketServ_);
        loopServer(server_.getSockets());

        click(server_,socketServ_,server_.getMultiStop());
        loopServer2(server_.getSockets());

        assertFalse(Net.getGames(server_.getNet()).enCoursDePartie());
    }
    @Test
    public void quit5() {
        MockGameTarot m_ = new MockGameTarot();
        WindowNetWork server_ = frameSingleTarot(m_);
        serverVersionNew(server_,3);
        retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        clientVersionNew(server_,client_);

        retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        WindowNetWork clientOk_ = frameSingleTarot(m_);
        clientVersionNew(server_,clientOk_);

        WindowNetWork clientKo_ = frameSingleTarot(m_);
        clientVersionNew(server_,clientKo_);
        MockSocket socket_ = (MockSocket) clientKo_.getSocket();
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(false);
        forcedBye_.setClosing(false);
        assertFalse(BasicClient.iterate(socket_,clientKo_,NetCommon.exportExiting(forcedBye_)));


        retrievedSocket(server_, clientOk_, 2);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), clientOk_);
        loopClient(server_.getSockets(),clientOk_);
        assertEq(3,server_.getSockets().getSockets().size());
    }
    @Test
    public void quit6() {
        MockGameTarot m_ = new MockGameTarot();
        WindowNetWork server_ = frameSingleTarot(m_);
        serverVersionNew(server_,3);
        retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        clientVersionNew(server_,client_);

        retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        WindowNetWork clientOk_ = frameSingleTarot(m_);
        clientVersionNew(server_,clientOk_);

        WindowNetWork clientKo_ = frameSingleTarot(m_);
        clientVersionNew(server_,clientKo_);
        MockSocket socket_ = (MockSocket) clientKo_.getSocket();
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(false);
        forcedBye_.setClosing(true);
        clientKo_.getFrames().getCounts().put(clientKo_.getApplicationName(),clientKo_.getFrames().getThreadFactory().newAtomicInteger());
        assertFalse(BasicClient.iterate(socket_,clientKo_,NetCommon.exportExiting(forcedBye_)));


        retrievedSocket(server_, clientOk_, 2);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), clientOk_);
        loopClient(server_.getSockets(),clientOk_);
        assertEq(3,server_.getSockets().getSockets().size());
    }
    @Test
    public void cancelConnect() {
        MockGameTarot m_ = new MockGameTarot();
        WindowNetWork server_ = frameSingleTarot(m_);
        cancelConnect(server_);
        assertEq(0,((MockThreadFactory)server_.getFrames().getThreadFactory()).getAllThreads().size());
    }
    private void readyPlayers(WindowNetWork _server, MockSocket _socketServ, WindowNetWork _client, MockSocket _socketClient) {
        _socketServ.getOutput().clear();
        ready(_server, _server, _socketServ);
        netPlayers(_server, _client);

        _socketClient.getOutput().clear();
        ready(_server, _client, _socketClient);
        netPlayers(_server, _client);
    }

    private void ready(WindowNetWork _server, WindowNetWork _target, MockSocket _soc) {
        click(_server, _soc, ((ContainerMulti) _target.getNetg().getContainerGame()).getContainerMultiContent().getReady());
    }

    private void click(WindowNetWork _server, MockSocket _soc, AbsButton _button) {
        _soc.getOutput().clear();
        tryClick(_button);
        writeToServer(_server, _soc);
    }

}
