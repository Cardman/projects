package code.network;

import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.pokemon.*;
import aiki.fight.util.*;
import aiki.instances.*;
import aiki.map.pokemon.enums.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import code.util.core.StringUtil;
import org.junit.Test;

public final class ScenePanelMultiTest extends EquallableNetworkUtil {
    public static final String FR = StringUtil.FR;
    public static final String EN = StringUtil.EN;
    public static final String LANGUAGE = EN;
    public static final String ELECTRICK = "ELECTRICK";
    @Test
    public void intro1() {
        WindowNetWork server_ = frameSingle(new MockDataBaseStreamNet());
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        introServer(server_);
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        retrievedSocket(server_, client_, 1);
        introClient(server_, client_);
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
        introServer(server_);
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        introClient(server_, client_);

        selectServer(server_, client_, socketServ_);

        selectClient(server_, client_, socketClient_);

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
        introServer(server_);
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        introClient(server_, client_);

        selectServer(server_, client_, socketServ_);

        selectClient(server_, client_, socketClient_);

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
        introServer(server_);
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        introClient(server_, client_);

        selectServer(server_, client_, socketServ_);

        selectClient(server_, client_, socketClient_);

        readyServer(server_, socketServ_);

        readyServer(server_, socketServ_);

        readyServer(server_, socketServ_);

        readyClient(server_, client_, socketClient_);

        readyClient(server_, client_, socketClient_);

        readyClient(server_, client_, socketClient_);

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
        introServer(server_);
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        introClient(server_, client_);

        selectServer(server_, client_, socketServ_);

        selectClient(server_, client_, socketClient_);

        readyServer(server_, socketServ_);

        readyServer(server_, socketServ_);

        readyServer(server_, socketServ_);

        applyTrade(server_, socketServ_);

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
        introServer(server_);
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        introClient(server_, client_);

        selectServer(server_, client_, socketServ_);

        selectClient(server_, client_, socketClient_);

        readyServer(server_, socketServ_);

        readyClient(server_, client_, socketClient_);


        applyTrade(server_, socketServ_);

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
        introServer(server_);
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
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        introServer(server_);
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        clientPk(server_,client_);
        retrievedSocket(server_, client_, 1);
        introClient(server_, client_);
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
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        serverPk(server_);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);
        introServer(server_);
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        clientPk(server_,client_);
        retrievedSocket(server_, client_, 1);
        introClient(server_, client_);

        exitTradeServer(server_, socketServ_);

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(false);
        forcedBye_.setClosing(true);
//        server_.getFrames().getCounts().put(server_.getApplicationName(),server_.getFrames().getThreadFactory().newAtomicInteger());
        assertFalse(BasicClient.iterate(socketServ_,server_,NetCommon.exportExiting(forcedBye_)));
//        client_.getFrames().getCounts().put(client_.getApplicationName(),client_.getFrames().getThreadFactory().newAtomicInteger());
//        client_.setButtonClick(client_.getCompoFactory().newPlainButton(""));
        tryClick(client_.getExit());
        GuiBaseUtil.tryToReopen(client_.getApplicationName(),client_.getFrames());
    }

    @Test
    public void quit_1() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        serverPk(server_);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);
        introServer(server_);
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet(), server_.getCompoFactory().newPlainButton(""));
        clientPk(server_,client_);
        retrievedSocket(server_, client_, 1);
        introClient(server_, client_);

        exitTradeServer(server_, socketServ_);

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(false);
        forcedBye_.setClosing(true);
//        server_.getFrames().getCounts().put(server_.getApplicationName(),server_.getFrames().getThreadFactory().newAtomicInteger());
        assertFalse(BasicClient.iterate(socketServ_,server_,NetCommon.exportExiting(forcedBye_)));
//        client_.getFrames().getCounts().put(client_.getApplicationName(),client_.getFrames().getThreadFactory().newAtomicInteger());
//        client_.setButtonClick(client_.getCompoFactory().newPlainButton(""));
        tryClick(client_.getExit());
        GuiBaseUtil.tryToReopen(client_.getApplicationName(),client_.getFrames());
    }
    @Test
    public void quit2() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        introServer(server_);
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        introClient(server_, client_);

        exitTradeClient(server_, client_, socketClient_);

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(true);
        forcedBye_.setClosing(false);
        assertFalse(BasicClient.iterate(socketClient_,client_,NetCommon.exportExiting(forcedBye_)));
    }

    @Test
    public void quit3() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        introServer(server_);
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        introClient(server_, client_);

        exitTradeClient(server_, client_, socketClient_);

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(false);
        forcedBye_.setClosing(false);
        assertFalse(BasicClient.iterate(socketClient_,client_,NetCommon.exportExiting(forcedBye_)));
    }

    @Test
    public void quit4() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        introServer(server_);
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        introClient(server_, client_);

        exitTradeClient(server_, client_, socketClient_);

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setTooManyPlayers(true);
        forcedBye_.setForced(true);
        forcedBye_.setClosing(false);
        assertFalse(BasicClient.iterate(socketClient_,client_,NetCommon.exportExiting(forcedBye_)));
    }

    @Test
    public void quit5() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        introServer(server_);
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        clientPk(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        WindowNetWork clientKo_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        clientPk(server_,clientKo_);
        MockSocket socket_ = (MockSocket) clientKo_.getSocket();
        introClient(server_, client_);

        exitTradeClient(server_, client_, socketClient_);

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setTooManyPlayers(true);
        forcedBye_.setForced(true);
        forcedBye_.setClosing(false);
        assertFalse(BasicClient.iterate(socket_,clientKo_,NetCommon.exportExiting(forcedBye_)));

    }

    @Test
    public void quit6() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        serverPk(server_);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);
        introServer(server_);
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        clientPk(server_,client_);
        retrievedSocket(server_, client_, 1);
        introClient(server_, client_);

        exitTradeServer(server_, socketServ_);

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(false);
        forcedBye_.setClosing(true);
        forcedBye_.setServer(true);
//        server_.getFrames().getCounts().put(server_.getApplicationName(),server_.getFrames().getThreadFactory().newAtomicInteger());
        assertFalse(BasicClient.iterate(socketServ_,server_,NetCommon.exportExiting(forcedBye_)));

//        client_.getFrames().getCounts().put(client_.getApplicationName(),client_.getFrames().getThreadFactory().newAtomicInteger());
//        client_.setButtonClick(client_.getCompoFactory().newPlainButton(""));
        tryClick(client_.getExit());
    }

    @Test
    public void quit_6() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        serverPk(server_);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);
        introServer(server_);
        WindowNetWork client_ = frameSingleMenu(new MockDataBaseStreamNet(), server_.getCompoFactory().newPlainButton(""));
        clientPk(server_,client_);
        retrievedSocket(server_, client_, 1);
        introClient(server_, client_);

        exitTradeServer(server_, socketServ_);

        assertEq(0,server_.getSockets().getSockets().size());
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(false);
        forcedBye_.setClosing(true);
        forcedBye_.setServer(true);
//        server_.getFrames().getCounts().put(server_.getApplicationName(),server_.getFrames().getThreadFactory().newAtomicInteger());
        assertFalse(BasicClient.iterate(socketServ_,server_,NetCommon.exportExiting(forcedBye_)));

//        client_.getFrames().getCounts().put(client_.getApplicationName(),client_.getFrames().getThreadFactory().newAtomicInteger());
//        client_.setButtonClick(client_.getCompoFactory().newPlainButton(""));
        tryClick(client_.getExit());
    }
    @Test
    public void cancelConnect() {
        WindowNetWork server_ = frameSingleMenu(new MockDataBaseStreamNet(), null);
        cancelConnect(server_);
        assertEq(0,((MockThreadFactory)server_.getFrames().getThreadFactory()).getAllThreads().size());
        new BasicClient(server_.getSocket(),server_).iterate(server_.getSocket(), NetCommon.exportExiting(new Exiting()));
    }

    private void exitTradeClient(WindowNetWork _server, WindowNetWork _client, MockSocket _socketClient) {
        _socketClient.getOutput().clear();
        tryClick(_client.getScenePanel().getExitTrade());
        writeToServer(_server, _socketClient);
        loopServer2(_server.getSockets());
    }

    private void exitTradeServer(WindowNetWork _server, MockSocket _socketServ) {
        exitTradeClient(_server, _server, _socketServ);
    }

    private void applyTrade(WindowNetWork _server, MockSocket _socketServ) {
        _socketServ.getOutput().clear();
        tryClick(_server.getScenePanel().getApplyTrade());
        writeToServer(_server, _socketServ);
        loopServer2(_server.getSockets());
    }

    private void readyClient(WindowNetWork _server, WindowNetWork _client, MockSocket _socketClient) {
        _socketClient.getOutput().clear();
        tryClick(_client.getScenePanel().getReadyCheck());
        writeToServer(_server, _socketClient);
        loopServer2(_server.getSockets());
    }

    private void readyServer(WindowNetWork _server, MockSocket _socketServ) {
        _socketServ.getOutput().clear();
        tryClick(_server.getScenePanel().getReadyCheck());
        writeToServer(_server, _socketServ);
        loopServer2(_server.getSockets());
    }


    private void selectServer(WindowNetWork _server, WindowNetWork _client, MockSocket _socketServ) {
        _server.getScenePanel().getTeamPan().getListe().select(0);
        _server.getScenePanel().getTeamPan().getListe().events();
        writeToServer(_server, _socketServ);
        loopServer2(_server.getSockets());
        sendClientPk(_server.getSockets(), _client,1);
        loopClient(_server.getSockets(), _client);
    }


    private void selectClient(WindowNetWork _server, WindowNetWork _client, MockSocket _socketClient) {
        _client.getScenePanel().getTeamPan().getListe().select(0);
        _client.getScenePanel().getTeamPan().getListe().events();
        writeToServer(_server, _socketClient);
        loopServer2(_server.getSockets());
        sendClientPk(_server.getSockets(), _server,0);
        loopClient(_server.getSockets(), _server);
    }

    private void introClient(WindowNetWork _server, WindowNetWork _client) {
        sendClientPk(_server.getSockets(), _client,1);
        loopClient(_server.getSockets(), _client);
        loopServer2(_server.getSockets());
        sendClientPk(_server.getSockets(), _server,0);
        loopClient(_server.getSockets(), _server);
        sendClientPk(_server.getSockets(), _client,1);
        loopClient(_server.getSockets(), _client);
    }

    private void introServer(WindowNetWork _server) {
        sendClientPk(_server.getSockets(), _server,0);
        loopClient(_server.getSockets(), _server);
        loopServer1(_server.getSockets());
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
        updateLg(data_);
        data_.initTranslations();
        data_.initializeMembers();
        data_.setMap(Instances.newDataMap());
        data_.setCombos(Instances.newCombos());
        data_.getMap().setSideLength(1);
        return data_;
    }
    public static void updateLg(DataBase _db) {
        _db.setLanguage(LANGUAGE);
        _db.setLanguages(new StringList(LANGUAGE));
    }
    public static DataBase withPk(DataBase _data, String _key, StringMap<String> _trs) {
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getStatistics().addEntry(Statistic.ATTACK, new StatBaseEv(1,1));
        pk_.getStatistics().addEntry(Statistic.DEFENSE, new StatBaseEv(1,1));
        pk_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK, new StatBaseEv(1,1));
        pk_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE, new StatBaseEv(1,1));
        pk_.getStatistics().addEntry(Statistic.HP, new StatBaseEv(1,1));
        _data.completeQuickMembers(_key, pk_);
        _data.getMiniPk().addEntry(_key,instance(new int[][]{new int[1]}));
        _data.getMaxiPkBack().addEntry(_key,instance(new int[][]{new int[1]}));
        _data.getMaxiPkFront().addEntry(_key,instance(new int[][]{new int[1]}));
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
        _data.getMiniItems().addEntry(_key,instance(new int[][]{new int[1]}));
        return _data;
    }

    public static DataBase withIt(DataBase _data, String _key, StringMap<String> _trs, StringMap<String> _trsDesc) {
        Ball ball_ = Instances.newBall();
        ball_.setCatchingRate("1");
        _data.completeQuickMembers(_key, ball_);
        _trs.addEntry(_key,_key);
        _data.getMiniItems().addEntry(_key,instance(new int[][]{new int[1]}));
        _trsDesc.addEntry(ball_.getItemType(),ball_.getItemType());
        return _data;
    }
}
