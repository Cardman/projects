package code.network;

import cards.gui.containers.ContainerMulti;
import cards.gui.containers.ContainerMultiContent;
import cards.network.threads.Net;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.AbsButton;
import code.mock.MockGameTarot;
import code.mock.MockSocket;
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
