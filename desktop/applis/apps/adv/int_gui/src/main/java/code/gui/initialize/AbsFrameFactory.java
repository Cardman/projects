package code.gui.initialize;

import code.gui.*;

public interface AbsFrameFactory {
    AbsCommonFrame newCommonFrame();

    void setCursor(AbsCustComponent _comp,int _wCurs, int _hCurs, int[] _pixels);
}
