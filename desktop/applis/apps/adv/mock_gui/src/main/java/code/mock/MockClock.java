package code.mock;

import code.gui.AbsClock;
import code.gui.AbsCustComponent;
import code.threads.AbstractThreadFactory;

public final class MockClock implements AbsClock {
    private final MockPlainLabel plainLabel = new MockPlainLabel("");
    @Override
    public AbsCustComponent getComponent() {
        return plainLabel;
    }

    @Override
    public void setTimeText(AbstractThreadFactory _fact) {
        plainLabel.setText(Long.toString(_fact.millis()));
    }
}
