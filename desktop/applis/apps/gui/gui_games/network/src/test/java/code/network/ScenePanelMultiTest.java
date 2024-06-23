package code.network;

import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.pokemon.*;
import aiki.fight.util.*;
import aiki.instances.*;
import aiki.map.pokemon.enums.*;
import aiki.network.NetAiki;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class ScenePanelMultiTest extends EquallableNetworkUtil {
    public static final String FR = "fr";
    public static final String EN = "en";
    public static final String LANGUAGE = EN;
    public static final String ELECTRICK = "ELECTRICK";
    @Test
    public void intro1() {
        WindowNetWork server_ = frameSingle(new MockDataBaseStreamNet());
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(3, tr_.size());
        assertTrue(tr_.containsObj(server_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getExitTrade()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getApplyTrade()));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, tr2_.size());
        assertTrue(tr2_.containsObj(client_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr2_.containsObj(client_.getScenePanel().getExitTrade()));
    }
    @Test
    public void intro2() {
        WindowNetWork server_ = frameSingle(new MockDataBaseStreamNet());
        serverPk(server_);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        server_.getScenePanel().getTeamPan().getListe().select(0);
        server_.getScenePanel().getTeamPan().getListe().events();
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        client_.getScenePanel().getTeamPan().getListe().select(0);
        client_.getScenePanel().getTeamPan().getListe().events();
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);

        IdList<AbsCustComponent> tr_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertTrue(tr_.containsObj(server_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getExitTrade()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getReadyCheck()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getApplyTrade()));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(3, tr2_.size());
        assertTrue(tr2_.containsObj(client_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr2_.containsObj(client_.getScenePanel().getReadyCheck()));
        assertTrue(tr2_.containsObj(client_.getScenePanel().getExitTrade()));
    }
    @Test
    public void intro3() {
        WindowNetWork server_ = frameSingle(new MockDataBaseStreamNet());
        serverPk(server_);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        server_.getScenePanel().getTeamPan().getListe().select(0);
        server_.getScenePanel().getTeamPan().getListe().events();
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        client_.getScenePanel().getTeamPan().getListe().select(0);
        client_.getScenePanel().getTeamPan().getListe().events();
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);

        server_.getScenePanel().getTeamPan().getListe().select(-1);
        server_.getScenePanel().getTeamPan().getListe().events();

        client_.getScenePanel().getTeamPan().getListe().select(-1);
        client_.getScenePanel().getTeamPan().getListe().events();

        IdList<AbsCustComponent> tr_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(3, tr_.size());
        assertTrue(tr_.containsObj(server_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getExitTrade()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getApplyTrade()));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, tr2_.size());
        assertTrue(tr2_.containsObj(client_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr2_.containsObj(client_.getScenePanel().getExitTrade()));
    }
    @Test
    public void intro4() {
        WindowNetWork server_ = frameSingle(new MockDataBaseStreamNet());
        serverPk(server_);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        server_.getScenePanel().getTeamPan().getListe().select(0);
        server_.getScenePanel().getTeamPan().getListe().events();
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        client_.getScenePanel().getTeamPan().getListe().select(0);
        client_.getScenePanel().getTeamPan().getListe().events();
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);

        tryClick(server_.getScenePanel().getReadyCheck());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());

        tryClick(server_.getScenePanel().getReadyCheck());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());

        tryClick(server_.getScenePanel().getReadyCheck());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());

        tryClick(client_.getScenePanel().getReadyCheck());
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());

        tryClick(client_.getScenePanel().getReadyCheck());
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());

        tryClick(client_.getScenePanel().getReadyCheck());
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());

        IdList<AbsCustComponent> tr_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(3, tr_.size());
        assertTrue(tr_.containsObj(server_.getScenePanel().getExitTrade()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getReadyCheck()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getApplyTrade()));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, tr2_.size());
        assertTrue(tr2_.containsObj(client_.getScenePanel().getReadyCheck()));
        assertTrue(tr2_.containsObj(client_.getScenePanel().getExitTrade()));
    }
    @Test
    public void intro5() {
        WindowNetWork server_ = frameSingle(new MockDataBaseStreamNet());
        serverPk(server_);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        server_.getScenePanel().getTeamPan().getListe().select(0);
        server_.getScenePanel().getTeamPan().getListe().events();
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        client_.getScenePanel().getTeamPan().getListe().select(0);
        client_.getScenePanel().getTeamPan().getListe().events();
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);

        tryClick(server_.getScenePanel().getReadyCheck());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());

        tryClick(server_.getScenePanel().getReadyCheck());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());

        tryClick(server_.getScenePanel().getReadyCheck());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());

        socketServ_.getOutput().clear();
        tryClick(server_.getScenePanel().getApplyTrade());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());

        IdList<AbsCustComponent> tr_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(3, tr_.size());
        assertTrue(tr_.containsObj(server_.getScenePanel().getExitTrade()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getReadyCheck()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getApplyTrade()));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(3, tr2_.size());
        assertTrue(tr2_.containsObj(client_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr2_.containsObj(client_.getScenePanel().getReadyCheck()));
        assertTrue(tr2_.containsObj(client_.getScenePanel().getExitTrade()));
    }
    @Test
    public void intro6() {
        WindowNetWork server_ = frameSingle(new MockDataBaseStreamNet());
        serverPk(server_);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        server_.getScenePanel().getTeamPan().getListe().select(0);
        server_.getScenePanel().getTeamPan().getListe().events();
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        client_.getScenePanel().getTeamPan().getListe().select(0);
        client_.getScenePanel().getTeamPan().getListe().events();
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);

        socketServ_.getOutput().clear();
        tryClick(server_.getScenePanel().getReadyCheck());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());

        socketClient_.getOutput().clear();
        tryClick(client_.getScenePanel().getReadyCheck());
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());


        socketServ_.getOutput().clear();
        tryClick(server_.getScenePanel().getApplyTrade());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());

        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);

        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        IdList<AbsCustComponent> tr_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(server_.getScenePanel().getExitTrade()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getApplyTrade()));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        assertTrue(tr2_.containsObj(client_.getScenePanel().getExitTrade()));
        tryClick(server_.getAiki().getGameSave());
        tryClick(client_.getAiki().getGameSave());

        server_.getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) server_.getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0));
        assertTrue(server_.getCommonFrame().isVisible());
        client_.getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) client_.getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0));
        assertTrue(client_.getCommonFrame().isVisible());
    }
    @Test
    public void intro7() {
        WindowNetWork server_ = frameSingle(new MockDataBaseStreamNet());
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingleDiff(new MockDataBaseStreamSecNet());
        clientPk(server_,client_);
        retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        assertEq(1,server_.getSockets().getSockets().size());
    }
    @Test
    public void intro8() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet());
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(3, tr_.size());
        assertTrue(tr_.containsObj(server_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getExitTrade()));
        assertTrue(tr_.containsObj(server_.getScenePanel().getApplyTrade()));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, tr2_.size());
        assertTrue(tr2_.containsObj(client_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr2_.containsObj(client_.getScenePanel().getExitTrade()));
    }

    @Test
    public void quit1() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet());
        serverPk(server_);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        socketServ_.getOutput().clear();
        tryClick(server_.getScenePanel().getExitTrade());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(false);
        forcedBye_.setClosing(true);
        server_.getFrames().getCounts().put(server_.getApplicationName(),server_.getFrames().getThreadFactory().newAtomicInteger());
        assertFalse(BasicClient.iterate(socketServ_,server_,NetCommon.exportExiting(forcedBye_)));

        client_.getFrames().getCounts().put(client_.getApplicationName(),client_.getFrames().getThreadFactory().newAtomicInteger());
        client_.setButtonClick(client_.getCompoFactory().newPlainButton(""));
        tryClick(client_.getExit());
    }

    @Test
    public void quit2() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet());
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        socketClient_.getOutput().clear();
        tryClick(client_.getScenePanel().getExitTrade());
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(true);
        forcedBye_.setClosing(false);
        assertFalse(BasicClient.iterate(socketClient_,client_,NetCommon.exportExiting(forcedBye_)));
    }

    @Test
    public void quit3() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet());
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        socketClient_.getOutput().clear();
        tryClick(client_.getScenePanel().getExitTrade());
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(false);
        forcedBye_.setClosing(false);
        assertFalse(BasicClient.iterate(socketClient_,client_,NetCommon.exportExiting(forcedBye_)));
    }

    @Test
    public void quit4() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet());
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        socketClient_.getOutput().clear();
        tryClick(client_.getScenePanel().getExitTrade());
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setTooManyPlayers(true);
        forcedBye_.setForced(true);
        forcedBye_.setClosing(false);
        assertFalse(BasicClient.iterate(socketClient_,client_,NetCommon.exportExiting(forcedBye_)));
    }

    @Test
    public void quit5() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet());
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        WindowNetWork clientKo_ = frameSingleMenu(new MockDataBaseStreamNet());
        clientPk(server_,clientKo_);
        MockSocket socket_ = (MockSocket) clientKo_.getSocket();
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        socketClient_.getOutput().clear();
        tryClick(client_.getScenePanel().getExitTrade());
        writeToServer(server_,socketClient_);
        loopServer2(server_.getSockets());

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setTooManyPlayers(true);
        forcedBye_.setForced(true);
        forcedBye_.setClosing(false);
        assertFalse(BasicClient.iterate(socket_,clientKo_,NetCommon.exportExiting(forcedBye_)));

    }

    @Test
    public void quit6() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet());
        serverPk(server_);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);

        socketServ_.getOutput().clear();
        tryClick(server_.getScenePanel().getExitTrade());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(false);
        forcedBye_.setClosing(true);
        forcedBye_.setServer(true);
        server_.getFrames().getCounts().put(server_.getApplicationName(),server_.getFrames().getThreadFactory().newAtomicInteger());
        assertFalse(BasicClient.iterate(socketServ_,server_,NetCommon.exportExiting(forcedBye_)));

        client_.getFrames().getCounts().put(client_.getApplicationName(),client_.getFrames().getThreadFactory().newAtomicInteger());
        client_.setButtonClick(client_.getCompoFactory().newPlainButton(""));
        tryClick(client_.getExit());
    }
    @Test
    public void cancelConnect() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet());
        cancelConnect(server_);
        assertEq(0,((MockThreadFactory)server_.getFrames().getThreadFactory()).getAllThreads().size());
        new BasicClient(server_.getSocket(),server_).iterate(server_.getSocket(), NetCommon.exportExiting(new Exiting()));
    }
    public static DataBase sample() {
        DataBase db_ = init();
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        db_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        db_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        db_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        db_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        db_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        db_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        db_.getTranslatedGenders().addEntry(LANGUAGE,new IdMap<Gender, String>());
        trsTypes_.put(ELECTRICK,"elec");
        db_ = withPk(withPk(db_,"P1",trsPk_),"P2",trsPk_);
        db_ = withMv(withMv(db_,"M1",trsMv_),"M2",trsMv_);
        db_ = withAb(withAb(db_,"A1",trsAb_),"A2",trsAb_);
        db_ = withIt(withIt(db_,"I1",trsIt_,trsDesc_),"I2",trsIt_,trsDesc_);
        db_.getMap().getFirstPokemon().setName("P1");
        return db_;
    }
    public static DataBase sampleSec() {
        DataBase db_ = init();
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        db_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        db_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        db_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        db_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        db_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        db_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        db_.getTranslatedGenders().addEntry(LANGUAGE,new IdMap<Gender, String>());
        trsTypes_.put(ELECTRICK,"elec");
        db_ = withPk(withPk(db_,"P1",trsPk_),"P2",trsPk_);
        db_ = withMv(withMv(db_,"M1",trsMv_),"M2",trsMv_);
        db_ = withAb(withAb(db_,"A3",trsAb_),"A4",trsAb_);
        db_ = withIt(withIt(db_,"I1",trsIt_,trsDesc_),"I2",trsIt_,trsDesc_);
        db_.getMap().getFirstPokemon().setName("P1");
        return db_;
    }
    public static DataBase init() {
        DataBase data_ = new DataBase(DefaultGenerator.oneElt());
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initTranslations();
        data_.initializeMembers();
        data_.setMap(Instances.newDataMap());
        data_.setCombos(Instances.newCombos());
        data_.getMap().setSideLength(1);
        return data_;
    }
    public static DataBase withPk(DataBase _data, String _key, StringMap<String> _trs) {
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getStatistics().addEntry(Statistic.ATTACK, new StatBaseEv((short) 1,(short)1));
        pk_.getStatistics().addEntry(Statistic.DEFENSE, new StatBaseEv((short) 1,(short)1));
        pk_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK, new StatBaseEv((short) 1,(short)1));
        pk_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE, new StatBaseEv((short) 1,(short)1));
        pk_.getStatistics().addEntry(Statistic.HP, new StatBaseEv((short) 1,(short)1));
        _data.completeQuickMembers(_key, pk_);
        _data.getMiniPk().addEntry(_key,new int[][]{new int[1]});
        _data.getMaxiPkBack().addEntry(_key,new int[][]{new int[1]});
        _data.getMaxiPkFront().addEntry(_key,new int[][]{new int[1]});
        _trs.addEntry(_key,_key);
        return _data;
    }
    public static DataBase withMv(DataBase _data, String _key, StringMap<String> _trs) {
        _data.completeQuickMembers(_key,Instances.newDamagingMoveData());
        _trs.addEntry(_key,_key);
        return _data;
    }

    public static DataBase withAb(DataBase _data, String _key, StringMap<String> _trs) {
        _data.completeQuickMembers(_key,Instances.newAbilityData());
        _trs.addEntry(_key,_key);
        return _data;
    }

    public static DataBase withIt(DataBase _data, String _key, StringMap<String> _trs) {
        Ball ball_ = Instances.newBall();
        ball_.setCatchingRate("1");
        return withIt(_data, _key, _trs, ball_);
    }

    public static DataBase withIt(DataBase _data, String _key, StringMap<String> _trs, Item _it) {
        _data.completeQuickMembers(_key, _it);
        _trs.addEntry(_key,_key);
        _data.getMiniItems().addEntry(_key,new int[][]{new int[1]});
        return _data;
    }

    public static DataBase withIt(DataBase _data, String _key, StringMap<String> _trs, StringMap<String> _trsDesc) {
        Ball ball_ = Instances.newBall();
        ball_.setCatchingRate("1");
        _data.completeQuickMembers(_key, ball_);
        _trs.addEntry(_key,_key);
        _data.getMiniItems().addEntry(_key,new int[][]{new int[1]});
        _trsDesc.addEntry(ball_.getItemType(),ball_.getItemType());
        return _data;
    }
}
