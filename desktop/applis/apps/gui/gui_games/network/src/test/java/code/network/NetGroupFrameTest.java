package code.network;

import code.gui.initialize.*;
import code.mock.*;
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
}
