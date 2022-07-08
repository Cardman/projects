package code.mock;

import code.gui.initialize.*;
import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.CustList;
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
}
