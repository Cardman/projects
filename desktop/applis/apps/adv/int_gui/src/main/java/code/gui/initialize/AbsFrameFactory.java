package code.gui.initialize;

import code.gui.*;
import code.gui.images.AbstractImage;

public interface AbsFrameFactory {
    AbsCommonFrame newCommonFrame(AbstractProgramInfos _frames, AbstractImage _imageIconFrame);

    void setCursor(AbsCustComponent _comp,int _wCurs, int _hCurs, int[] _pixels);
}
