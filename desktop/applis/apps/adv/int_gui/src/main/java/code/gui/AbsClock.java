package code.gui;

import code.threads.AbstractThreadFactory;

public interface AbsClock {
    AbsCustComponent getComponent();

    void setTimeText(AbstractThreadFactory _fact);
}
