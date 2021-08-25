package code.gui;

import code.gui.events.AbsMouseListener;

public interface AbsImgButton extends AbsCustComponent, AbsEnabled {
    void addMouseList(AbsMouseListener _l);
}
