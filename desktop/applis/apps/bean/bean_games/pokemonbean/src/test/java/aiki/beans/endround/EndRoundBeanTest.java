package aiki.beans.endround;

import org.junit.Test;

public final class EndRoundBeanTest extends InitDbEndRound {
    @Test
    public void evts() {
        assertSizeEq(32,dispEndRoundEvts());
    }
}
