package code.mock;

import code.gui.initialize.*;
import code.threads.*;
import code.util.CustList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class MockNetworkTest extends EquallableMockGuiUtil {
    @Test
    public void n1() {
        AbstractSocketFactory s_ = init().getSocketFactory();
        AbstractServerSocket server_ = s_.newServerSocket("", 0);
        server_.setOk(false);
        assertFalse(server_.isOk());
        assertFalse(s_.setOkServer(false));
        assertFalse(s_.setKoSocket(false));
    }
    @Test
    public void n2() {
        AbstractSocketFactory s_ = init().getSocketFactory();
        AbstractServerSocket server_ = s_.newServerSocket("", 0);
        server_.setOk(true);
        assertTrue(server_.isOk());
        AbstractSocket so_ = server_.accept();
        assertFalse(so_.isKo());
        assertEq("_\n",so_.println("_"));
        assertNull(so_.getInput().readLine());
        so_.close();
        assertEq("",so_.println("_"));
        assertFalse(server_.isClosed());
        assertTrue(server_.close());
        assertTrue(server_.isClosed());
        assertTrue(server_.accept().isKo());
    }
    @Test
    public void n3() {
        AbstractScheduledExecutorService s_ = init().getThreadFactory().newScheduledExecutorService();
        AbstractFuture f1_ = s_.scheduleAtFixedRateNanos(new MockRunnable(), 0, 1);
        assertTrue(f1_.cancel(true));
        AbstractFuture f2_ = s_.scheduleAtFixedRate(new MockRunnable(), 0, 1);
        assertTrue(f2_.cancel(true));
    }
    @Test
    public void n4() {
        AbstractScheduledExecutorService s_ = init().getThreadFactory().newScheduledExecutorService();
        s_.shutdown();
        AbstractFuture f1_ = s_.scheduleAtFixedRateNanos(new MockRunnable(), 0, 1);
        assertTrue(f1_.cancel(true));
        AbstractFuture f2_ = s_.scheduleAtFixedRate(new MockRunnable(), 0, 1);
        assertTrue(f2_.cancel(true));
    }
    @Test
    public void n5() {
        AbstractScheduledExecutorService s_ = init().getThreadFactory().newScheduledExecutorService();
        s_.shutdown();
        AbstractFuture f1_ = s_.scheduleAtFixedRateNanos(new MockRunnable(), 0, 1);
        assertTrue(f1_.cancel(false));
        AbstractFuture f2_ = s_.scheduleAtFixedRate(new MockRunnable(), 0, 1);
        assertTrue(f2_.cancel(false));
    }
    @Test
    public void n6() {
        AbstractScheduledExecutorService s_ = init().getThreadFactory().newScheduledExecutorService();
        AbstractFuture f1_ = s_.scheduleAtFixedRateNanos(new MockRunnable(), 0, 1);
        assertFalse(f1_.cancel(false));
        AbstractFuture f2_ = s_.scheduleAtFixedRate(new MockRunnable(), 0, 1);
        assertFalse(f2_.cancel(false));
    }
    @Test
    public void n7() {
        AbstractBaseExecutorService s_ = init().getThreadFactory().newExecutorService();
        MockRunnable r_ = new MockRunnable();
        s_.execute(r_);
        assertTrue(r_.isStarted());
    }
    @Test
    public void n8() {
        AbstractBaseExecutorService s_ = init().getThreadFactory().newExecutorService();
        MockRunnable r_ = new MockRunnable();
        s_.execute(null);
        assertFalse(r_.isStarted());
    }
    @Test
    public void n9() {
        AbstractBaseExecutorService s_ = init().getThreadFactory().newExecutorService();
        s_.shutdown();
        MockRunnable r_ = new MockRunnable();
        s_.execute(r_);
        assertFalse(r_.isStarted());
    }
    @Test
    public void n10() {
        AbstractSocketFactory s_ = init().getSocketFactory();
        assertFalse(s_.newSocket(0,"").isKo());
    }
    @Test
    public void n11() {
        AbstractSocketFactory s_ = init().getSocketFactory();
        AbstractNetworkInterfaceList l_ = s_.newList();
        assertEq(0, l_.size());
        CustList<MockNetworkInterface> ls_ = ((MockNetworkInterfaceList) l_).getList();
        assertEq(0, ls_.size());
        ls_.add(new MockNetworkInterface(new MockAddressList(),"",true));
        assertEq("",l_.getName(0));
        assertTrue(l_.isVirtual(0));
        assertEq(0,l_.size(0));
        assertEq(0,l_.list(0).size());
    }
    @Test
    public void n12() {
        AbstractSocketFactory s_ = init().getSocketFactory();
        AbstractAddressList a_ = s_.newAddr("");
        assertEq(0, a_.size());
        CustList<MockAddress> h_ = ((MockAddressList) a_).getHosts();
        assertEq(0, h_.size());
        h_.add(new MockAddress("",true,true,true));
        assertEq("",a_.getHost(0));
        assertTrue(a_.isIpFour(0));
        assertTrue(a_.isIpSix(0));
        assertTrue(a_.isLoopbackAddress(0));
    }
    @Test
    public void n13() {
        AbstractBaseExecutorService s_ = init().getThreadFactory().newExecutorService();
        MockRunnable r_ = new MockRunnable();
        AbstractFuture f_ = s_.submitLater(r_);
        assertFalse(r_.isStarted());
        assertFalse(f_.attendre());
        assertTrue(r_.isStarted());
        assertFalse(f_.cancel(false));
        assertFalse(f_.cancel(true));
    }
    @Test
    public void n14() {
        AbstractBaseExecutorService s_ = init().getThreadFactory().newExecutorService();
        s_.shutdown();
        MockRunnable r_ = new MockRunnable();
        AbstractFuture f_ = s_.submitLater(r_);
        assertFalse(r_.isStarted());
        assertTrue(f_.attendre());
        assertFalse(r_.isStarted());
        assertTrue(f_.cancel(false));
        assertTrue(f_.cancel(true));
    }
    @Test
    public void n15() {
        MockCallable<String> r_ = new MockCallable<String>("RES");
        assertFalse(r_.isStarted());
    }
    @Test
    public void n16() {
        AbstractBaseExecutorServiceParam<String> s_ = new MockBaseExecutorServiceParam<String>();
        MockCallable<String> r_ = new MockCallable<String>("RES");
        AbstractFutureParam<String> f_ = s_.submitCallable(r_);
        assertTrue(r_.isStarted());
        assertEq("RES",f_.attendreResultat());
    }
    @Test
    public void n17() {
        AbstractBaseExecutorServiceParam<String> s_ = new MockBaseExecutorServiceParam<String>();
        MockCallable<String> r_ = new MockCallable<String>("RES");
        AbstractFutureParam<String> f_ = s_.submitLater(r_);
        assertFalse(r_.isStarted());
        assertEq("RES",f_.attendreResultat());
        assertTrue(r_.isStarted());
    }
    @Test
    public void n18() {
        AbstractBaseExecutorServiceParam<String> s_ = new MockBaseExecutorServiceParam<String>();
        MockCallable<String> r_ = new MockCallable<String>("RES");
        s_.shutdown();
        AbstractFutureParam<String> f_ = s_.submitLater(r_);
        assertFalse(r_.isStarted());
        assertEq("", StringUtil.nullToEmpty(f_.attendreResultat()));
        assertFalse(r_.isStarted());
    }
    @Test
    public void n19() {
        AbstractBaseExecutorServiceParam<String> s_ = new MockBaseExecutorServiceParam<String>();
        MockCallable<String> r_ = new MockCallable<String>("RES");
        s_.shutdown();
        AbstractFutureParam<String> f_ = s_.submitCallable(r_);
        assertFalse(r_.isStarted());
        assertEq("", StringUtil.nullToEmpty(f_.attendreResultat()));
        assertFalse(r_.isStarted());
    }
    @Test
    public void n20() {
        AbstractBaseExecutorServiceParam<String> s_ = new MockBaseExecutorServiceParam<String>();
        MockCallable<String> r_ = new MockCallable<String>("RES");
        AbstractFutureParam<String> f_ = s_.submitCallable(r_);
        assertTrue(r_.isStarted());
        assertFalse(f_.attendre());
    }
    @Test
    public void n21() {
        AbstractBaseExecutorServiceParam<String> s_ = new MockBaseExecutorServiceParam<String>();
        MockCallable<String> r_ = new MockCallable<String>("RES");
        s_.shutdown();
        AbstractFutureParam<String> f_ = s_.submitCallable(r_);
        assertFalse(r_.isStarted());
        assertTrue(f_.attendre());
    }
    @Test
    public void n22() {
        AbstractBaseExecutorServiceParam<String> s_ = new MockBaseExecutorServiceParam<String>();
        AbstractFutureParam<String> f_ = s_.submitCallable(null);
        assertEq("", StringUtil.nullToEmpty(f_.attendreResultat()));
    }
    @Test
    public void n23() {
        AbstractBaseExecutorServiceParam<String> s_ = new MockBaseExecutorServiceParam<String>();
        MockCallable<String> r_ = new MockCallable<String>("RES");
        AbstractFutureParam<String> f_ = s_.submitLater(r_);
        assertFalse(r_.isStarted());
        assertFalse(f_.attendre());
        assertTrue(r_.isStarted());
        assertFalse(f_.cancel(true));
    }
    @Test
    public void n24() {
        AbstractBaseExecutorServiceParam<String> s_ = new MockBaseExecutorServiceParam<String>();
        MockCallable<String> r_ = new MockCallable<String>("RES");
        s_.shutdown();
        AbstractFutureParam<String> f_ = s_.submitLater(r_);
        assertFalse(r_.isStarted());
        assertTrue(f_.attendre());
        assertFalse(r_.isStarted());
        assertTrue(f_.cancel(true));
    }
}
