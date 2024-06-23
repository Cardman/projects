package code.network;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import aiki.main.AikiFactory;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.map.pokemon.PokemonPlayer;
import aiki.sml.IntDataBaseStream;
import cards.facade.Games;
import cards.facade.IntArtCardGames;
import cards.facade.Nicknames;
import cards.gui.dialogs.help.HelpIndexesTree;
import cards.main.CardFactories;
import cards.main.CardNatLgNamesNavigation;
import cards.network.threads.*;
import code.gui.AbsButton;
import code.gui.AbsCustComponent;
import code.gui.initialize.*;
import code.mock.*;
import code.network.enums.IpType;
import code.util.CustList;
import code.util.IdList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class NetGroupFrameTest extends EquallableNetworkUtil {
    @Test
    public void t1() {
        MockProgramInfos pr_ = build();
        pr_.setLanguage("");
        pr_.getSocketFactory().setOkServer(true);
        NetGroupFrameSampleFalse en_ = new NetGroupFrameSampleFalse("", pr_);
        AbstractServerSocket serverSocket_ = en_.getFrames().getSocketFactory().newServerSocket("", 0);
        new ConnectionToServer(serverSocket_, en_, 0).run();
        assertTrue(serverSocket_.isOk());
    }
    @Test
    public void t2() {
        MockProgramInfos pr_ = build();
        pr_.setLanguage("");
        pr_.getSocketFactory().setOkServer(true);
        NetGroupFrameSampleTrue en_ = new NetGroupFrameSampleTrue("", pr_);
        AbstractServerSocket serverSocket_ = en_.getFrames().getSocketFactory().newServerSocket("", 0);
        new ConnectionToServer(serverSocket_, en_, 0).run();
        assertTrue(serverSocket_.isOk());
    }
    @Test
    public void t3() {
        MockProgramInfos pr_ = build();
        pr_.setLanguage("");
        pr_.getSocketFactory().setOkServer(true);
        NetGroupFrameSampleTrue en_ = new NetGroupFrameSampleTrue("", pr_);
        AbstractServerSocket serverSocket_ = en_.getFrames().getSocketFactory().newServerSocket("", 0);
        serverSocket_.close();
        new ConnectionToServer(serverSocket_, en_, 0).run();
        assertEq("",pr_.getLanguage());
    }

    @Test
    public void launch() {
        MockProgramInfos api_ = updateSingle(build());
        api_.setLanguages(new StringList(EN,FR));
        Games.appendNickNames(Games.getAppliTr(api_.currentLg()), Nicknames.en());
        AikiFactory ai_ = new AikiFactory(api_, new MockBaseExecutorServiceParam<AikiNatLgNamesNavigation>(), new MockBaseExecutorServiceParam<DataBase>());
        CardFactories cf_ = new CardFactories(api_, new MockBaseExecutorServiceParam<CardNatLgNamesNavigation>(), new MockBaseExecutorServiceParam<StringMap<HelpIndexesTree>>());
        LaunchNetwork l_ = new LaunchNetwork(EN, api_,api_.getCompoFactory().newPlainButton(),api_.getCompoFactory().newMenuItem(), ai_, cf_);
        l_.run();
        assertTrue(l_.getWindow().getCommonFrame().isVisible());
        tryClick(l_.getWindow().getNetg().getLanguage());
        tryClick(l_.getWindow().getLanguageDialogButtons().getContent().getGroupe().get(l_.getWindow().getFrames().getTranslations().getMapping().indexOfEntry(EN)));
        tryClick(l_.getWindow().getLanguageDialogButtons().getContent().getGroupe().get(l_.getWindow().getFrames().getTranslations().getMapping().indexOfEntry(FR)));
//        assertTrue(wc_.getCommonFrame().isVisible());
    }
    @Test
    public void socketSend1() {
        assertFalse(NetGroupFrame.trySendString("",Net.getSocketByPlace((byte) 0, new NetCommon())));
    }

    @Test
    public void socketSend2() {
        assertFalse(NetGroupFrame.trySendString("",new MockSocket(true)));
    }

    @Test
    public void addressesNames() {
        MockAddressList addr_ = new MockAddressList();
        addr_.getHosts().add(new MockAddress("_",true,true,false));
        addr_.getHosts().add(new MockAddress("__",false,false,true));
        StringList res_ = NetCreate.addressesNames(addr_);
        assertEq(2,res_.size());
        assertEq("_",res_.get(0));
        assertEq("__",res_.get(1));
    }
    @Test
    public void hostAddresses1() {
        MockNetworkInterfaceList list_ = new MockNetworkInterfaceList();
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>()),"__",true));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>()),NetCreate.WLAN_ZERO,false));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>()),NetCreate.NET_ZERO,false));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>()),NetCreate.WLAN_ZERO,true));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>()),NetCreate.NET_ZERO,true));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>()),"",false));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>(new MockAddress("",true,true,false),new MockAddress("",true,false,true))),"__",false));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>(new MockAddress("1",false,false,false))),NetCreate.WLAN_ZERO,false));
        StringList res_ = NetCreate.hostAddresses(IpType.HOST_NAME, list_);
        assertEq(1,res_.size());
        assertEq("1",res_.get(0));
    }
    @Test
    public void hostAddresses2() {
        MockNetworkInterfaceList list_ = new MockNetworkInterfaceList();
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>()),"__",true));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>()),"",false));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>(new MockAddress("",true,true,false),new MockAddress("",true,false,true))),NetCreate.NET_ZERO,false));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>(new MockAddress("1",false,true,false),new MockAddress("2",false,false,true))),NetCreate.WLAN_ZERO,false));
        StringList res_ = NetCreate.hostAddresses(IpType.IP_V4, list_);
        assertEq(1,res_.size());
        assertEq("1",res_.get(0));
    }
    @Test
    public void hostAddresses3() {
        MockNetworkInterfaceList list_ = new MockNetworkInterfaceList();
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>()),"__",true));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>()),"",false));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>(new MockAddress("",true,true,false),new MockAddress("",true,false,true))),NetCreate.NET_ZERO,false));
        list_.getList().add(new MockNetworkInterface(init(new CustList<MockAddress>(new MockAddress("1",false,true,false),new MockAddress("2",false,false,true))),NetCreate.WLAN_ZERO,false));
        StringList res_ = NetCreate.hostAddresses(IpType.IP_V6, list_);
        assertEq(1,res_.size());
        assertEq("2",res_.get(0));
    }
    @Test
    public void tryToGetPort1() {
        assertEq(1,NetCreate.tryToGetPort(1,""));
    }
    @Test
    public void tryToGetPort2() {
        assertEq(1,NetCreate.tryToGetPort(1,""+NetCreate.MAX_PORT));
    }
    @Test
    public void tryToGetPort3() {
        assertEq(NetCreate.MAX_PORT-1,NetCreate.tryToGetPort(1,""+(NetCreate.MAX_PORT-1)));
    }
    @Test
    public void singleOrDef1() {
        assertEq("1",NetCreate.singleOrDef("1",new StringList()));
    }
    @Test
    public void singleOrDef2() {
        assertEq("2",NetCreate.singleOrDef("1",new StringList("2")));
    }
    @Test
    public void getHostAddress() {
        assertEq("1",NetCreate.getHostAddress(new MockSocketFactory(),IpType.HOST_NAME,"1"));
    }

    @Test
    public void koConnect1() {
        WindowNetWork server_ = frameSingleMenuKo(new MockDataBaseStreamNet());
        server_.getDialogServerContent().getCreateServer().getActionListeners().get(0).action();
        assertTrue(server_.getCommonFrame().isVisible());
    }

    @Test
    public void koConnect2() {
        WindowNetWork server_ = frameSingleMenuKo(new MockDataBaseStreamNet());
        server_.getDialogServerContent().getIpOrHostName().setText("_");
        server_.getDialogServerContent().getJoinServer().getActionListeners().get(0).action();
        assertTrue(server_.getCommonFrame().isVisible());
        server_.results(new MockSocket(true));
    }

    @Test
    public void match1() {
        assertFalse(NetGroupFrame.match(nullArray(), 0));
    }
    @Test
    public void match2() {
        assertFalse(NetGroupFrame.match(new byte[]{}, 2));
    }

    @Test
    public void match3() {
        assertTrue(NetGroupFrame.match(new byte[]{(byte) 1, (byte) 3}, 2));
    }

    private static byte[] nullArray() {
        return null;
    }


    public static WindowNetWork frameSingleMenuKo(IntDataBaseStream _i) {
        MockProgramInfos pr_ = updateSingle(build());
        pr_.getSocketFactory().setOkServer(false);
        AikiFactory ai_ = new AikiFactory(pr_, new MockBaseExecutorServiceParam<AikiNatLgNamesNavigation>(), new MockBaseExecutorServiceParam<DataBase>());
//        ai_.setConfPkStream(new MockConfPkStream());
        ai_.setGamePkStream(new MockGamePkStream());
//        ai_.submit(new MockCallable<DataBase>(_db));
        WindowNetWork w_ = new WindowNetWork(stream(pr_), EN, pr_, ai_, null, new IntArtCardGames());
        updateBase(pr_.currentLg());
        ai_.setPreparedPkNetTask(new AikiNatLgNamesNavigation(new PokemonStandardsSampleNet(),nav()));
        w_.setVisible(true);
        w_.pack();
        w_.setPreparedPkNetTask(ai_.getPreparedPkNetTask());
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
    private MockAddressList init(CustList<MockAddress> _h) {
        MockAddressList l_ = new MockAddressList();
        l_.getHosts().addAllElts(_h);
        return l_;
    }
}
