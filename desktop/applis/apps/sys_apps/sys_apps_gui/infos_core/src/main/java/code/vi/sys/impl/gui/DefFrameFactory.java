package code.vi.sys.impl.gui;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsFrameFactory;
import code.gui.initialize.AbsLightFrameFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.vi.prot.impl.gui.CustComponent;

import java.awt.*;
import java.awt.image.MemoryImageSource;

public final class DefFrameFactory implements AbsFrameFactory, AbsLightFrameFactory {
    private static final String SELECT = "select";
    @Override
    public AbsCommonFrame newCommonFrame(String _languageKey, AbstractProgramInfos _frames, AbstractImage _imageIconFrame) {
        return new CommonFrame(_languageKey, _frames, _imageIconFrame);
    }

    @Override
    public AbsDialog newDialog(AbsCloseableDialog _closeable) {
        return new Dialog(_closeable);
    }

    @Override
    public AbsDialog newDialog() {
        return newDialog(null);
    }

    @Override
    public AbsOtherDialog newOtherDialog() {
        return new OtherDialog();
    }

    @Override
    public AbsOtherFrame newOtherFrame() {
        return new OtherFrame();
    }

    @Override
    public void setCursor(AbsCustComponent _comp, int _wCurs, int _hCurs, int[] _pixels) {
        Toolkit tool_ = Toolkit.getDefaultToolkit();
        Image b_ = tool_.createImage(new MemoryImageSource(_wCurs, _hCurs, _pixels, 0, _wCurs));
        ((CustComponent)_comp).getNatComponent().setCursor(tool_.createCustomCursor(b_, new Point(0, 0),SELECT));
    }
}
