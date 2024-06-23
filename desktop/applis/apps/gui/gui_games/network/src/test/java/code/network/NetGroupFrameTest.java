package code.network;

import cards.network.threads.*;
import code.gui.initialize.*;
import code.mock.*;
import code.network.enums.IpType;
import code.util.CustList;
import code.util.StringList;
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
    private MockAddressList init(CustList<MockAddress> _h) {
        MockAddressList l_ = new MockAddressList();
        l_.getHosts().addAllElts(_h);
        return l_;
    }
}
