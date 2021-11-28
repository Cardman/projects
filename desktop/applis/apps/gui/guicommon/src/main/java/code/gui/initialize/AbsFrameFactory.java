package code.gui.initialize;

import code.gui.*;
import code.gui.images.AbstractImage;

public interface AbsFrameFactory {
    AbsCommonFrame newCommonFrame(String _languageKey, AbstractProgramInfos _frames, AbstractImage _imageIconFrame);
    AbsDialog newDialog(AbsCloseableDialog _closeable);
    AbsDialog newDialog();
}
