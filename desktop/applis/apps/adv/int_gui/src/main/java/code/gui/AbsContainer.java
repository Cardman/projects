package code.gui;

import code.util.CustList;

public interface AbsContainer {
    AbsCustComponent getParent();
    CustList<AbsCustComponent> getChildren();
}
