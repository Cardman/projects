package code.network;

import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.pokemon.enums.*;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import aiki.main.*;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.enums.Gender;
import aiki.sml.*;
import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
import cards.facade.*;
import cards.facade.enumerations.*;
import cards.gui.*;
import cards.gui.containers.*;
import cards.gui.dialogs.help.*;
import cards.gui.labels.*;
import cards.main.*;
import cards.president.*;
import cards.president.enumerations.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.scripts.pages.aiki.MessagesPkBean;
import code.scripts.pages.aiki.MessagesPkPokemon;
import code.gui.files.*;
import code.gui.initialize.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.netw.*;
import code.network.enums.*;
import code.scripts.messages.cards.*;
import code.scripts.pages.cards.MessagesBelotePage;
import code.scripts.pages.cards.MessagesPresidentPage;
import code.scripts.pages.cards.MessagesTarotPage;
import code.sml.util.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Assert;

public abstract class EquallableNetworkUtil {
    public static final String FR = StringUtil.FR;
    public static final String EN = StringUtil.EN;
    protected static MockSocket retrievedSocket(WindowNetWork _server, WindowNetWork _client, int _i) {
        MockSocket socket_ = prepare(_server, _client, _i);
        loopClient(_server.getSockets(),_client);
        loopServer(_server.getSockets());
        return socket_;
    }

    protected static void netPlayers(WindowNetWork _server, WindowNetWork _client) {
        loopServer(_server.getSockets());
        sendClient(_server.getSockets(), _server);
        loopClient(_server.getSockets(), _server);
        sendClient(_server.getSockets(), _client);
        loopClient(_server.getSockets(), _client);
    }

    protected static void netPlayers(WindowNetWork _server) {
        loopServer(_server.getSockets());
        sendClient(_server.getSockets(), _server);
        loopClient(_server.getSockets(), _server);
    }

    protected static MockSocket prepare(WindowNetWork _server, WindowNetWork _client, int _i) {
        MockSocket socketServer_ = (MockSocket) _server.getSockets().getSockets().getValue(_i);
        Assert.assertNotNull(socketServer_.getOutput().getList());
        MockSocket socketClient_ = (MockSocket) _client.getSocket();
        socketClient_.getInstr().addAllElts(socketServer_.getOutput());
        return socketClient_;
    }

    protected static void writeToServer(WindowNetWork _server, MockSocket _so) {
        StringList instr_ = ((MockSocket) _server.getSockets().getConnectionsServer().firstValue().getSocket()).getInstr();
        instr_.clear();
        instr_.addAllElts(_so.getOutput());
    }

    protected static void deal(WindowNetWork _server, WindowNetWork _client) {
        loopServer2(_server.getSockets());
        sendClient(_server.getSockets(), _server);
        sendClient(_server.getSockets(), _client);
        loopClient(_server.getSockets(),_server);
        loopServer2(_server.getSockets());
        loopClient(_server.getSockets(),_client);
    }
    protected static void playIa(WindowNetWork _server, WindowNetWork _client) {
        loopServer2(_server.getSockets());
        sendClient(_server.getSockets(), _server);
        sendClient(_server.getSockets(), _client);
        loopClient(_server.getSockets(),_server);
        loopServer2(_server.getSockets());
        loopClient(_server.getSockets(),_client);
    }
    protected static void playIaWithout(WindowNetWork _server, WindowNetWork _client) {
        loopServer2(_server.getSockets());
        sendClient(_server.getSockets(), _server);
        sendClient(_server.getSockets(), _client);
        loopClient(_server.getSockets(),_server);
        loopClient(_server.getSockets(),_client);
    }
    protected static void allow(WindowNetWork _server, WindowNetWork _client) {
        loopServer2(_server.getSockets());
        sendClient(_server.getSockets(), _client);
        loopClient(_server.getSockets(),_client);
    }
    protected static void self(WindowNetWork _server, WindowNetWork _client) {
        loopServer2(_server.getSockets());
        sendClient(_server.getSockets(), _client);
        loopClient(_server.getSockets(),_client);
    }
    protected static void loopClient(NetCommon _server,WindowNetWork _client) {
        MockSocket socket_ = (MockSocket) _client.getSocket();
        socket_.getOutput().clear();
        BasicClient.iterate(socket_,_client,socket_.getInstr().last());
        socket_.getInstr().clear();
        BasicServer serv_ = _server.getConnectionsServer().firstValue();
        StringList instr_ = ((MockSocket) serv_.getSocket()).getInstr();
        instr_.clear();
        instr_.addAllElts(socket_.getOutput());
        socket_.getOutput().clear();
    }

    protected static void loopServer2(NetCommon _server) {
        ((MockSocket)_server.getSockets().getValue(0)).getOutput().clear();
        ((MockSocket)_server.getSockets().getValue(1)).getOutput().clear();
        loopServer(_server);
    }

    protected static void loopServer1(NetCommon _server) {
        ((MockSocket)_server.getSockets().getValue(0)).getOutput().clear();
        loopServer(_server);
    }
    protected static void loopServer(NetCommon _server) {
        BasicServer serv_ = _server.getConnectionsServer().firstValue();
        String str_ = serv_.getSocket().read();
        serv_.loopServer(str_);
    }
    protected static void sendClient(NetCommon _server, WindowNetWork _client) {
        MockSocket socket_ = (MockSocket) _client.getSocket();
        socket_.getInstr().clear();
        int noClient_ = ((ContainerMulti) _client.getNetg().getContainerGame()).getContainerMultiContent().getNoClient();
        AbstractSocket ret_ = _server.getSockets().getVal(noClient_);
        StringList output_ = ((MockSocket) ret_).getOutput();
        socket_.getInstr().addAllElts(output_);
        output_.clear();
    }
    protected static void sendClientPk(NetCommon _server, WindowNetWork _client, int _index) {
        MockSocket socket_ = (MockSocket) _client.getSocket();
        socket_.getInstr().clear();
        AbstractSocket ret_ = _server.getSockets().getValue(_index);
        StringList output_ = ((MockSocket) ret_).getOutput();
        socket_.getInstr().addAllElts(output_);
        output_.clear();
    }
    protected static void serverVersionOld(WindowNetWork _window, int _nb) {
        server(_window, IpType.IP_V4, "__", _nb);
    }
    protected static void serverVersionNew(WindowNetWork _window, int _nb) {
        server(_window, IpType.IP_V6, "__", _nb);
    }
    protected static void serverVersion(WindowNetWork _window, int _nb) {
        server(_window, IpType.HOST_NAME, "", _nb);
    }
    protected static void server(WindowNetWork _window, IpType _i, String _host, int _nb) {
        _window.getDialogServerContent().getIpType().setSelectedItem(_i);
        _window.getDialogServerContent().getIpOrHostName().setText(_host);
        _window.getDialogServerContent().getNbPlayers().setValue(_nb);
        _window.getDialogServerContent().getCreateServer().getActionListeners().get(0).action();
        _window.getDialogServerContent().getConnection().run();
    }
    protected static void serverPk(WindowNetWork _window) {
        _window.getDialogServerContent().getIpType().setSelectedItem(IpType.IP_V6);
        _window.getDialogServerContent().getIpOrHostName().setText("__");
        _window.getDialogServerContent().getCreateServer().getActionListeners().get(0).action();
        _window.getDialogServerContent().getConnection().run();
    }
    protected static void cancelConnect(WindowNetWork _window) {
        _window.getDialogServerContent().getCancel().getActionListeners().get(0).action();
    }
    protected static void clientVersionOld(WindowNetWork _server,WindowNetWork _window) {
        client(_server,_window, IpType.IP_V4, "");
    }
    protected static void clientVersionNew(WindowNetWork _server,WindowNetWork _window) {
        client(_server,_window, IpType.IP_V6, "");
    }
    protected static void clientVersion(WindowNetWork _server,WindowNetWork _window) {
        client(_server,_window, IpType.HOST_NAME, "__");
    }
    protected static void client(WindowNetWork _server,WindowNetWork _window, IpType _i, String _host) {
        _window.getDialogServerContent().getIpType().setSelectedItem(_i);
        _window.getDialogServerContent().getIpOrHostName().setText(_host);
        _window.getDialogServerContent().getJoinServer().getActionListeners().get(0).action();
        _server.getDialogServerContent().getConnection().run();
    }
    protected static void clientPk(WindowNetWork _server,WindowNetWork _window) {
        _window.getDialogServerContent().getIpType().setSelectedItem(IpType.IP_V6);
        _window.getDialogServerContent().getIpOrHostName().setText("");
        _window.getDialogServerContent().getJoinServer().getActionListeners().get(0).action();
        _server.getDialogServerContent().getConnection().run();
    }
    protected static WindowNetWork frameSingleBelote(IntGameBelote _m) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setBelote(_m);
        MockProgramInfos pr_ = updateSingleBelote(build());
        pr_.getSocketFactory().setOkServer(true);
        WindowNetWork w_ = new WindowNetWork(streamPseudoBelote(pr_), pr_, new AikiFactory(pr_, new MockBaseExecutorServiceParam<DataBase>()), ia_, new LanguagesButtonsPair(null,null,null));
        CardFactories cf_ = new CardFactories(pr_, new MockBaseExecutorServiceParam<HelpIndexesTree>());
//        belote(cf_, w_);
//        w_.setPrepare(cf_.getTaskNav());
        w_.pack();
        w_.setVisible(true);
        tryClick(w_.getMultiModeButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) w_.getPane()).getTreeAccessible();
        assertEq(4,tr_.size());
        tryClick((AbsButton) tr_.get(0));
        return w_;
    }
    protected static WindowNetWork frameSinglePresident(IntGamePresident _m) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setPresident(_m);
        MockProgramInfos pr_ = updateSinglePresident(build());
        pr_.getSocketFactory().setOkServer(true);
        WindowNetWork w_ = new WindowNetWork(streamPseudoPresident(pr_), pr_, new AikiFactory(pr_, new MockBaseExecutorServiceParam<DataBase>()), ia_, new LanguagesButtonsPair(null,null,null));
        CardFactories cf_ = new CardFactories(pr_, new MockBaseExecutorServiceParam<HelpIndexesTree>());
//        president(cf_, w_);
//        w_.setPrepare(cf_.getTaskNav());
        w_.pack();
        w_.setVisible(true);
        tryClick(w_.getMultiModeButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) w_.getPane()).getTreeAccessible();
        assertEq(4,tr_.size());
        tryClick((AbsButton) tr_.get(1));
        return w_;
    }
    protected static WindowNetWork frameSingleTarot(IntGameTarot _m) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setTarot(_m);
        MockProgramInfos pr_ = updateSingleTarot(build());
        pr_.getSocketFactory().setOkServer(true);
        WindowNetWork w_ = new WindowNetWork(streamPseudoTarot(pr_), pr_, new AikiFactory(pr_, new MockBaseExecutorServiceParam<DataBase>()), ia_, new LanguagesButtonsPair(null,null,null));
        CardFactories cf_ = new CardFactories(pr_, new MockBaseExecutorServiceParam<HelpIndexesTree>());
//        tarot(cf_, w_);
//        w_.setPrepare(cf_.getTaskNav());
        w_.pack();
        w_.setVisible(true);
        tryClick(w_.getMultiModeButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) w_.getPane()).getTreeAccessible();
        assertEq(4,tr_.size());
        tryClick((AbsButton) tr_.get(2));
        return w_;
    }
    protected static WindowNetWork frameDisplay() {
        IntArtCardGames ia_ = new IntArtCardGames();
        MockProgramInfos pr_ = updateDialogDisplay(build());
        pr_.getSocketFactory().setOkServer(true);
        WindowNetWork w_ = new WindowNetWork(stream(pr_), pr_, new AikiFactory(pr_, new MockBaseExecutorServiceParam<DataBase>()), ia_, new LanguagesButtonsPair(null,null,null));
        CardFactories cf_ = new CardFactories(pr_, new MockBaseExecutorServiceParam<HelpIndexesTree>());
//        w_.setPrepare(cf_.getTaskNav());
        w_.pack();
        w_.setVisible(true);
        return w_;
    }
    protected static WindowNetWork frameSingle(IntDataBaseStream _i) {
        MockProgramInfos pr_ = updateSingle(build());
        update(pr_.getTranslations().getMapping().getVal(EN), MessagesPkPokemon.en());
        update(pr_.getTranslations().getMapping().getVal(FR), MessagesPkPokemon.fr());
        MessagesPkGame.appendPkGameDetailContent(MessagesPkGame.getAppliTr(pr_.getTranslations().getMapping().getVal(EN)), MessagesRenderPkGameDetail.en());
        MessagesPkGame.appendPkGameDetailContent(MessagesPkGame.getAppliTr(pr_.getTranslations().getMapping().getVal(FR)), MessagesRenderPkGameDetail.fr());
        pr_.setLanguage(EN);
        MessagesCardGames.sys(MessagesCardGames.initAppliFilesTr(pr_.getTranslations()));
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(pr_.getTranslations()));
        pr_.getSocketFactory().setOkServer(true);
        AikiFactory ai_ = new AikiFactory(pr_, new MockBaseExecutorServiceParam<DataBase>());
//        ai_.setConfPkStream(new MockConfPkStream());
        ai_.setGamePkStream(new MockGamePkStream());
//        ai_.submit(new MockCallable<DataBase>(_db));
        WindowNetWork w_ = new WindowNetWork(stream(pr_), pr_, ai_, new IntArtCardGames(), new LanguagesButtonsPair(null,null,null));
        updateBase(pr_.currentLg());
//        ai_.submitNavPkNetTask(new MockCallable<AikiNatLgNamesNavigation>(new AikiNatLgNamesNavigation(new PokemonStandardsSampleNet(),nav())));
        w_.setVisible(true);
        w_.pack();
//        w_.setPreparedPkNetTask(ai_.getTaskNavPkNetTask());
        w_.getAiki().setGameCheck(new MockGameChecker());
        w_.getAiki().getAikiFactory().setDataBaseStream(_i);
        tryClick(w_.getZipLoad());
        w_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) w_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory) w_.getFrames().getThreadFactory()));
        DataBase d_ = w_.getAiki().getFacade().getData();
        Game g_ = new Game(d_);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), d_);
        ((PokemonPlayer)g_.getTeam().get(0)).setAbility("A1");
        ((PokemonPlayer)g_.getTeam().get(0)).initIv(g_.getDifficulty());
        w_.getAiki().getAikiFactory().getGamePkStream().save("", g_);
        tryClick(w_.getGameLoad());
        w_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) w_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) w_.getPane()).getTreeAccessible();
        assertEq(1,tr_.size());
        tryClick((AbsButton) tr_.get(0));
        return w_;
    }

    private static void update(TranslationsLg _lg, TranslationsFile _file) {
        TranslationsAppli ta_ = new TranslationsAppli();
        ta_.getMapping().addEntry(MessagesPkBean.POKEMON, _file);
        _lg.getMapping().addEntry(MessagesPkBean.APP_BEAN, ta_);
    }
    protected static WindowNetWork frameSingleMenu(IntDataBaseStream _i, AbsButton _mai) {
        MockProgramInfos pr_ = updateSingle(build());
        MessagesCardGames.sys(MessagesCardGames.initAppliFilesTr(pr_.getTranslations()));
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(pr_.getTranslations()));
        pr_.getSocketFactory().setOkServer(true);
        AikiFactory ai_ = new AikiFactory(pr_, new MockBaseExecutorServiceParam<DataBase>());
//        ai_.setConfPkStream(new MockConfPkStream());
        ai_.setGamePkStream(new MockGamePkStream());
//        ai_.submit(new MockCallable<DataBase>(_db));
        WindowNetWork w_ = new WindowNetWork(stream(pr_), pr_, ai_, new IntArtCardGames(), new LanguagesButtonsPair(null,_mai,null));
        updateBase(pr_.currentLg());
//        ai_.submitNavPkNetTask(new MockCallable<AikiNatLgNamesNavigation>(new AikiNatLgNamesNavigation(new PokemonStandardsSampleNet(),nav())));
        w_.setVisible(true);
        w_.pack();
//        w_.setPreparedPkNetTask(ai_.getTaskNavPkNetTask());
        w_.getAiki().setGameCheck(new MockGameChecker());
        w_.getAiki().getAikiFactory().setDataBaseStream(_i);
        tryClick(w_.getFolderLoad());
        w_.getFileOpenFolderFrame().getFolderOpenDialogContent().getFileName().setText("_");
        w_.getFileOpenFolderFrame().getFolderOpenDialogContent().setSelectedPath("_");

        tryClick((AbsButton) w_.getFileOpenFolderFrame().getFolderOpenDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory) w_.getFrames().getThreadFactory()));
        DataBase d_ = w_.getAiki().getFacade().getData();
        Game g_ = new Game(d_);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), d_);
        ((PokemonPlayer)g_.getTeam().get(0)).setAbility("A1");
        ((PokemonPlayer)g_.getTeam().get(0)).initIv(g_.getDifficulty());
        w_.getAiki().getAikiFactory().getGamePkStream().save("", g_);
        tryClick(w_.getGameLoad());
        w_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) w_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) w_.getPane()).getTreeAccessible();
        assertEq(1,tr_.size());
        tryClick((AbsButton) tr_.get(0));
        return w_;
    }
    protected static WindowNetWork frameSingleDiff(IntDataBaseStream _i) {
        MockProgramInfos pr_ = updateSingle(build());
        MessagesCardGames.sys(MessagesCardGames.initAppliFilesTr(pr_.getTranslations()));
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(pr_.getTranslations()));
        pr_.getSocketFactory().setOkServer(true);
        AikiFactory ai_ = new AikiFactory(pr_, new MockBaseExecutorServiceParam<DataBase>());
//        ai_.setConfPkStream(new MockConfPkStream());
        ai_.setGamePkStream(new MockGamePkStream());
//        ai_.submit(new MockCallable<DataBase>(_db));
        WindowNetWork w_ = new WindowNetWork(streamPseudoTarot(pr_), pr_, ai_, new IntArtCardGames(), new LanguagesButtonsPair(null,null,null));
        updateBase(pr_.currentLg());
//        ai_.submitNavPkNetTask(new MockCallable<AikiNatLgNamesNavigation>(new AikiNatLgNamesNavigation(new PokemonStandardsSampleNet(),nav())));
        w_.setVisible(true);
        w_.pack();
//        w_.setPreparedPkNetTask(ai_.getTaskNavPkNetTask());
        w_.getAiki().setGameCheck(new MockGameChecker());
        w_.getAiki().getAikiFactory().setDataBaseStream(_i);
        tryClick(w_.getZipLoad());
        w_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) w_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory) w_.getFrames().getThreadFactory()));
        DataBase d_ = w_.getAiki().getFacade().getData();
        Game g_ = new Game(d_);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), d_);
        ((PokemonPlayer)g_.getTeam().get(0)).setAbility("A3");
        ((PokemonPlayer)g_.getTeam().get(0)).initIv(g_.getDifficulty());
        w_.getAiki().getAikiFactory().getGamePkStream().save("", g_);
        tryClick(w_.getGameLoad());
        w_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) w_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) w_.getPane()).getTreeAccessible();
        assertEq(1,tr_.size());
        tryClick((AbsButton) tr_.get(0));
        return w_;
    }

    public static void updateBase(TranslationsLg _en) {
        StringMap<TranslationsFile> en_ = MessagesGuiFct.initAppliTr(_en).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_DIAL, MessagesFileDialog.en());
        en_.addEntry(MessagesGuiFct.CONFIRM, MessagesConfirmDialog.en());
        en_.addEntry(MessagesGuiFct.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_TAB,MessagesFileTable.en());
    }
//    private static void belote(CardFactories _cf, WindowNetWork _w) {
//        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_RULES_BELOTE,new CallablePreparedPagesCards(new BeloteStandardsSampleNet(),null,new StringMap<Document>(),new StringMap<TranslationsAppli>(),new StringMap<String>(),_w.getFrames().getLanguages()));
//        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_RESULTS_BELOTE,new CallablePreparedPagesCards(new BeloteStandardsSampleNet(),null,new StringMap<Document>(),new StringMap<TranslationsAppli>(),new StringMap<String>(),_w.getFrames().getLanguages()));
//        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,new CallablePreparedPagesCards(new BeloteStandardsSampleNet(),null,new StringMap<Document>(),new StringMap<TranslationsAppli>(),new StringMap<String>(),_w.getFrames().getLanguages()));
//    }

//    private static void president(CardFactories _cf, WindowNetWork _w) {
//        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_RULES_PRESIDENT,new CallablePreparedPagesCards(new PresidentStandardsSampleNet(),null,new StringMap<Document>(),new StringMap<TranslationsAppli>(),new StringMap<String>(),_w.getFrames().getLanguages()));
//        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_RESULTS_PRESIDENT,new CallablePreparedPagesCards(new PresidentStandardsSampleNet(),null,new StringMap<Document>(),new StringMap<TranslationsAppli>(),new StringMap<String>(),_w.getFrames().getLanguages()));
//        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_DETAILS_RESULTS_PRESIDENT,new CallablePreparedPagesCards(new PresidentStandardsSampleNet(),null,new StringMap<Document>(),new StringMap<TranslationsAppli>(),new StringMap<String>(),_w.getFrames().getLanguages()));
//    }

//    private static void tarot(CardFactories _cf, WindowNetWork _w) {
//        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_RULES_TAROT,new CallablePreparedPagesCards(new TarotStandardsSampleNet(),null,new StringMap<Document>(),new StringMap<TranslationsAppli>(),new StringMap<String>(),_w.getFrames().getLanguages()));
//        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_RESULTS_TAROT,new CallablePreparedPagesCards(new TarotStandardsSampleNet(),null,new StringMap<Document>(),new StringMap<TranslationsAppli>(),new StringMap<String>(),_w.getFrames().getLanguages()));
//        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT,new CallablePreparedPagesCards(new TarotStandardsSampleNet(),null,new StringMap<Document>(),new StringMap<TranslationsAppli>(),new StringMap<String>(),_w.getFrames().getLanguages()));
//    }

//    public static NatNavigation nav() {
//        NatNavigation nav_ = new NatNavigation();
////        nav_.setSession(new NatConfigurationCore());
//        return nav_;
//    }

    public static MockProgramInfos build() {
        return build("", "",dbs(0.75));
    }
    public static MockProgramInfos build(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = MockProgramInfos.inst(_h, _t, new CustomSeedGene(_dbs), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.setLanguage(EN);
        return pr_;
    }

    public static MockProgramInfos updateSingleBelote(MockProgramInfos _pr) {
        MessagesCardGames.sys(MessagesCardGames.initAppliFilesTr(_pr.getTranslations()));
        appendMainGame(appendCards(appendCommon(appendMix(appendBelote(baseCardsEn(_pr),MessagesBelote.en(),MessagesBelote.enGame(),MessagesDialogBelote.en()) ,MessagesCommonMix.en()),MessagesCommonFile.en()),MessagesCommonCards.en()),MessagesGuiCards.enGame());
        appendMainGame(appendCards(appendCommon(appendMix(appendBelote(baseCardsFr(_pr),MessagesBelote.fr(),MessagesBelote.frGame(),MessagesDialogBelote.fr()),MessagesCommonMix.en()),MessagesCommonFile.fr()),MessagesCommonCards.fr()),MessagesGuiCards.frGame());
        maxiImgs(_pr);
        TranslationsAppli ta_ = new TranslationsAppli();
        ta_.getMapping().addEntry("",new TranslationsFile());
        _pr.getTranslations().getMapping().getVal(EN).getMapping().addEntry(MessagesBelotePage.APP_BEAN, ta_);
        _pr.getTranslations().getMapping().getVal(FR).getMapping().addEntry(MessagesBelotePage.APP_BEAN, ta_);
        return _pr;
    }
    public static MockProgramInfos updateSinglePresident(MockProgramInfos _pr) {
        MessagesCardGames.sys(MessagesCardGames.initAppliFilesTr(_pr.getTranslations()));
        appendMainGame(appendCards(appendCommon(appendMix(appendPresident(baseCardsEn(_pr),MessagesPresident.en(),MessagesPresident.enGame(),MessagesDialogPresident.en()) ,MessagesCommonMix.en()),MessagesCommonFile.en()),MessagesCommonCards.en()),MessagesGuiCards.enGame());
        appendMainGame(appendCards(appendCommon(appendMix(appendPresident(baseCardsFr(_pr),MessagesPresident.fr(),MessagesPresident.frGame(),MessagesDialogPresident.fr()),MessagesCommonMix.en()),MessagesCommonFile.fr()),MessagesCommonCards.fr()),MessagesGuiCards.frGame());
        maxiImgs(_pr);
        TranslationsAppli ta_ = new TranslationsAppli();
        ta_.getMapping().addEntry("",new TranslationsFile());
        _pr.getTranslations().getMapping().getVal(EN).getMapping().addEntry(MessagesPresidentPage.APP_BEAN, ta_);
        _pr.getTranslations().getMapping().getVal(FR).getMapping().addEntry(MessagesPresidentPage.APP_BEAN, ta_);
        return _pr;
    }
    public static MockProgramInfos updateSingleTarot(MockProgramInfos _pr) {
        MessagesCardGames.sys(MessagesCardGames.initAppliFilesTr(_pr.getTranslations()));
        appendMainGame(appendCards(appendCommon(appendMix(appendTarot(baseCardsEn(_pr),MessagesTarot.en(),MessagesTarot.enGame(),MessagesDialogTarot.en()) ,MessagesCommonMix.en()),MessagesCommonFile.en()),MessagesCommonCards.en()),MessagesGuiCards.enGame());
        appendMainGame(appendCards(appendCommon(appendMix(appendTarot(baseCardsFr(_pr),MessagesTarot.fr(),MessagesTarot.frGame(),MessagesDialogTarot.fr()),MessagesCommonMix.en()),MessagesCommonFile.fr()),MessagesCommonCards.fr()),MessagesGuiCards.frGame());
        maxiImgs(_pr);
        TranslationsAppli ta_ = new TranslationsAppli();
        ta_.getMapping().addEntry("",new TranslationsFile());
        _pr.getTranslations().getMapping().getVal(EN).getMapping().addEntry(MessagesTarotPage.APP_BEAN, ta_);
        _pr.getTranslations().getMapping().getVal(FR).getMapping().addEntry(MessagesTarotPage.APP_BEAN, ta_);
        return _pr;
    }
    public static MockProgramInfos updateDialogDisplay(MockProgramInfos _pr) {
        MessagesCardGames.sys(MessagesCardGames.initAppliFilesTr(_pr.getTranslations()));
        MessagesCardGames.appendCommonFile(appendDialogDisplay(baseCardsEn(_pr),MessagesGuiCards.enDisplay()),MessagesCommonFile.en());
        MessagesCardGames.appendCommonFile(appendDialogDisplay(baseCardsFr(_pr),MessagesGuiCards.frDisplay()),MessagesCommonFile.fr());
        return _pr;
    }

    public static MockProgramInfos updateSingle(MockProgramInfos _pr) {
        baseCardsEnPk(_pr);
        baseCardsFrPk(_pr);
        return _pr;
    }
    private static TranslationsAppli baseCardsFr(MockProgramInfos _pr) {
        TranslationsLg lg_ = lg(_pr, FR);
        TranslationsAppli appNet_ = MessagesNetWork.initAppliTr(lg_);
        MessagesNetWork.frTr(appNet_);
        TranslationsAppli appPk_ = MessagesPkGame.initAppliTr(lg_);
        MessagesPkGame.appendWindowPkContent(appPk_, MessagesRenderWindowPk.fr());
        return appendMulti(appendDialogNicknames(appendMenus(appendGamesNames(MessagesCardGames.initAppliTr(lg_), MessagesGamesGames.fr()), MessagesGuiCards.frMenu()),MessagesGuiCards.frNickname()),MessagesGuiCards.frMulti());
    }
    private static TranslationsAppli baseCardsEn(MockProgramInfos _pr) {
        TranslationsLg lg_ = lg(_pr, EN);
        TranslationsAppli appNet_ = MessagesNetWork.initAppliTr(lg_);
        MessagesNetWork.enTr(appNet_);
        TranslationsAppli appPk_ = MessagesPkGame.initAppliTr(lg_);
        MessagesPkGame.appendWindowPkContent(appPk_, MessagesRenderWindowPk.en());
        return appendMulti(appendDialogNicknames(appendMenus(appendGamesNames(MessagesCardGames.initAppliTr(lg_), MessagesGamesGames.en()),MessagesGuiCards.enMenu()),MessagesGuiCards.enNickname()),MessagesGuiCards.enMulti());
    }
    private static TranslationsAppli baseCardsFrPk(MockProgramInfos _pr) {
        TranslationsLg lg_ = lg(_pr, FR);
        TranslationsAppli appNet_ = MessagesNetWork.initAppliTr(lg_);
        MessagesNetWork.frTr(appNet_);
        TranslationsAppli appPk_ = MessagesPkGame.initAppliTr(lg_);
        MessagesPkGame.appendWindowPkContent(appPk_, MessagesRenderWindowPk.fr());
        MessagesPkGame.appendScenePanelContent(appPk_, MessagesRenderScenePanel.fr());
        return appendMenus(appendGamesNames(MessagesCardGames.initAppliTr(lg_), MessagesGamesGames.fr()), MessagesGuiCards.frMenu());
    }
    private static TranslationsAppli baseCardsEnPk(MockProgramInfos _pr) {
        TranslationsLg lg_ = lg(_pr, EN);
        TranslationsAppli appNet_ = MessagesNetWork.initAppliTr(lg_);
        MessagesNetWork.enTr(appNet_);
        TranslationsAppli appPk_ = MessagesPkGame.initAppliTr(lg_);
        MessagesPkGame.appendWindowPkContent(appPk_, MessagesRenderWindowPk.en());
        MessagesPkGame.appendScenePanelContent(appPk_, MessagesRenderScenePanel.en());
        return appendMenus(appendGamesNames(MessagesCardGames.initAppliTr(lg_), MessagesGamesGames.en()),MessagesGuiCards.enMenu());
    }
    public static TranslationsAppli appendMulti(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(MessagesCardGames.NETWORK,_f);
        return _app;
    }
    public static TranslationsAppli appendDialogNicknames(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(MessagesCardGames.DIALOG_NICKNAME,_f);
        return _app;
    }
    private static TranslationsAppli baseFr(MockProgramInfos _pr) {
        TranslationsLg lg_ = lg(_pr, FR);
        TranslationsAppli appNet_ = MessagesNetWork.initAppliTr(lg_);
        MessagesNetWork.frTr(appNet_);
        return appendMenus(appendGamesNames(MessagesCardGames.initAppliTr(lg_), MessagesGamesGames.fr()), MessagesGuiCards.frMenu());
    }
    private static TranslationsAppli baseEn(MockProgramInfos _pr) {
        TranslationsLg lg_ = lg(_pr, EN);
        TranslationsAppli appNet_ = MessagesNetWork.initAppliTr(lg_);
        MessagesNetWork.enTr(appNet_);
        return appendMenus(appendGamesNames(MessagesCardGames.initAppliTr(lg_), MessagesGamesGames.en()),MessagesGuiCards.enMenu());
    }
    public static TranslationsAppli appendBelote(TranslationsAppli _app, TranslationsFile _f, TranslationsFile _g, TranslationsFile _h) {
        _app.getMapping().addEntry(MessagesCardGames.COMMON_BELOTE,_f);
        _app.getMapping().addEntry(MessagesCardGames.GAME_BELOTE,_g);
        _app.getMapping().addEntry(MessagesCardGames.DIALOG_BELOTE,_h);
        return _app;
    }
    public static TranslationsAppli appendPresident(TranslationsAppli _app, TranslationsFile _f, TranslationsFile _g, TranslationsFile _h) {
        _app.getMapping().addEntry(MessagesCardGames.COMMON_PRESIDENT,_f);
        _app.getMapping().addEntry(MessagesCardGames.GAME_PRESIDENT,_g);
        _app.getMapping().addEntry(MessagesCardGames.DIALOG_PRESIDENT,_h);
        return _app;
    }
    public static TranslationsAppli appendTarot(TranslationsAppli _app, TranslationsFile _f, TranslationsFile _g, TranslationsFile _h) {
        _app.getMapping().addEntry(MessagesCardGames.COMMON_TAROT,_f);
        _app.getMapping().addEntry(MessagesCardGames.GAME_TAROT,_g);
        _app.getMapping().addEntry(MessagesCardGames.DIALOG_TAROT,_h);
        return _app;
    }
    public static TranslationsAppli appendGamesNames(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(MessagesCardGames.GAMES_NAMES,_f);
        return _app;
    }

    public static TranslationsAppli appendMenus(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(MessagesCardGames.MENUS,_f);
        return _app;
    }
    public static TranslationsAppli appendMix(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(MessagesCardGames.COMMON_MIX,_f);
        return _app;
    }

    public static TranslationsAppli appendCommon(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(MessagesCardGames.COMMON_FILE,_f);
        return _app;
    }

    public static TranslationsAppli appendCards(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(MessagesCardGames.COMMON_CARDS,_f);
        return _app;
    }

    public static TranslationsAppli appendMainGame(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(MessagesCardGames.MAIN_GAME,_f);
        return _app;
    }

    public static TranslationsAppli appendDialogDisplay(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(MessagesCardGames.DIALOG_DISPLAY,_f);
        return _app;
    }

    private static void maxiImgs(MockProgramInfos _pr) {
        StringMap<int[][]> mini_ = MiniCardsSampleNetGene.def();
        _pr.getTranslations().getMapping().getVal(EN).getMaxiCards().addAllEntries(mini_);
        _pr.getTranslations().getMapping().getVal(EN).getMiniCardsDef().addAllEntries(mini_);
        _pr.getTranslations().getMapping().getVal(FR).getMaxiCards().addAllEntries(mini_);
        _pr.getTranslations().getMapping().getVal(FR).getMiniCardsDef().addAllEntries(mini_);
    }

    private static CardGamesStream streamPseudoBelote(AbstractProgramInfos _pr) {
        CardGamesStream str_ = stream(_pr);
        Nicknames n_ = new Nicknames();
        nicknamesBelote(n_);
        str_.getNicknamesCrud().value(n_);
        return str_;
    }

    private static void nicknamesBelote(Nicknames _b) {
        _b.getPseudosBelote().add("0");
        _b.getPseudosBelote().add("1");
        _b.getPseudosBelote().add("2");
    }

    private static CardGamesStream streamPseudoPresident(AbstractProgramInfos _pr) {
        CardGamesStream str_ = stream(_pr);
        Nicknames n_ = new Nicknames();
        nicknamesPresident(n_);
        str_.getNicknamesCrud().value(n_);
        return str_;
    }

    private static void nicknamesPresident(Nicknames _n) {
        _n.getPseudosPresident().add("0");
        _n.getPseudosPresident().add("1");
        _n.getPseudosPresident().add("2");
        _n.getPseudosPresident().add("3");
        _n.getPseudosPresident().add("4");
        _n.getPseudosPresident().add("5");
        _n.getPseudosPresident().add("6");
        _n.getPseudosPresident().add("7");
        _n.getPseudosPresident().add("8");
        _n.getPseudosPresident().add("9");
        _n.getPseudosPresident().add("10");
        _n.getPseudosPresident().add("11");
        _n.getPseudosPresident().add("12");
        _n.getPseudosPresident().add("13");
        _n.getPseudosPresident().add("14");
        _n.getPseudosPresident().add("15");
        _n.getPseudosPresident().add("16");
        _n.getPseudosPresident().add("17");
        _n.getPseudosPresident().add("18");
        _n.getPseudosPresident().add("19");
        _n.getPseudosPresident().add("20");
        _n.getPseudosPresident().add("21");
        _n.getPseudosPresident().add("22");
        _n.getPseudosPresident().add("23");
        _n.getPseudosPresident().add("24");
        _n.getPseudosPresident().add("25");
        _n.getPseudosPresident().add("26");
        _n.getPseudosPresident().add("27");
        _n.getPseudosPresident().add("28");
        _n.getPseudosPresident().add("29");
        _n.getPseudosPresident().add("30");
    }

    private static CardGamesStream streamPseudoTarot(AbstractProgramInfos _pr) {
        CardGamesStream str_ = stream(_pr);
        Nicknames n_ = new Nicknames();
        nicknamesTarot(n_);
        str_.getNicknamesCrud().value(n_);
        return str_;
    }

    private static void nicknamesTarot(Nicknames _n) {
        _n.getPseudosTarot().add("0");
        _n.getPseudosTarot().add("1");
        _n.getPseudosTarot().add("2");
        _n.getPseudosTarot().add("3");
        _n.getPseudosTarot().add("4");
    }
    protected static CardGamesStream stream(AbstractProgramInfos _pr) {
        CardGamesStream cs_ = new CardGamesStream(_pr,"_/");
        cs_.setNicknamesCrud(new SampleNicknamesNetCrud(_pr));
        cs_.setCardGamesCrud(new SampleCardGamesNetCrud(_pr));
        return cs_;
    }
    public static double[] dbs(double... _args) {
        return _args;
    }
    public static void tryClick(AbsButton _m) {
        assertTrue(_m.isVisible());
        assertTrue(_m.isEnabled());
        _m.getActionListeners().get(0).action();
    }

    public AbstractThread tryAnimate(ContainerSingleImpl _cont) {
        return tryAn(facto(_cont.getOwner()));
    }

    public MockThreadFactory facto(WindowCardsInt _owner) {
        return (MockThreadFactory) _owner.getFrames().getThreadFactory();
    }

    public CustList<AbstractThread> factory(WindowCardsInt _owner) {
        return facto(_owner).getAllThreads();
    }


    public CustList<AbstractThread> factory(ContainerSingleImpl _owner) {
        return factory(_owner.getOwner());
    }

    public static AbstractThread tryAn(MockThreadFactory _g) {
        assertEq(1, _g.getAllThreads().size());
        AbstractThread th_ = _g.getAllThreads().get(0);
        _g.getAllThreads().remove(0);
        th_.join();
        checkNoAnim(_g);
        return th_;
    }

    public static void checkNoAnim(MockThreadFactory _thFact) {
        assertEq(0, _thFact.getAllThreads().size());
    }
    public static void tryClickHandful(HandfulLabel _m) {
        tryClick(_m.getButton());
    }
    public static void tryClickCard(AbsCustComponent _compo) {
        assertEq(1,_compo.getMouseListenersRel().size());
        _compo.getMouseListenersRel().get(0).mouseReleased(null,null,null);
    }

    public static void tryCheck(AbsCustCheckBox _ch, boolean _v) {
        assertTrue(((MockCustComponent) _ch).isDeepAccessible());
        _ch.setSelected(_v);
    }

    public static void tryToggle(AbsCustCheckBox _ch) {
        assertTrue(((MockCustComponent) _ch).isDeepAccessible());
        ((MockCustCheckBox)_ch).toggle();
    }
    public void eventsCombo(ScrollCustomCombo _combo, int _i) {
        _combo.select(_i);
        _combo.events(null);
    }
    public void eventsCombo(NumComboBox _combo, int _i) {
        _combo.selectItem(_i);
        _combo.getCombo().events(null);
    }
    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
        return _pr.lg(_key);
    }
    public static void assertNull(AbsCustComponent _compo) {
        Assert.assertNull(_compo);
    }
    public static void assertNotNull(AbsCustComponent _compo) {
        Assert.assertNotNull(_compo);
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(DealingTarot _expected, DealingTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(DealingBelote _expected, DealingBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Suit _expected, Suit _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(GameType _expected, GameType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(CardPresident _expected, CardPresident _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(EqualtyPlaying _expected, EqualtyPlaying _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(CardBelote _expected, CardBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(CardTarot _expected, CardTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Miseres _expected, Miseres _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Handfuls _expected, Handfuls _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(MixCardsChoice _expected, MixCardsChoice _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BeloteTrumpPartner _expected, BeloteTrumpPartner _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(DeclaresBelote _expected, DeclaresBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(EndDealTarot _expected, EndDealTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(ModeTarot _expected, ModeTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BidTarot _expected, BidTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BidBelote _expected, BidBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Playing _expected, Playing _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(GameEnum _expected, GameEnum _result) {
        Assert.assertSame(_expected,_result);
    }
    public static void assertEq(GenderRepartition _expected, GenderRepartition _result) {
        Assert.assertSame(_expected,_result);
    }
    public static void assertEq(Statistic _expected, Statistic _result) {
        Assert.assertSame(_expected,_result);
    }
    public static void assertEq(Gender _expected, Gender _result) {
        Assert.assertSame(_expected,_result);
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }

    public static ImageArrayBaseSixtyFour instance(int[][] _img) {
        return ImageArrayBaseSixtyFour.instance(_img,"");
    }
}
