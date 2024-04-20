package code.gui.document;

import code.gui.AbsCommonFrame;
import code.gui.Iconifiable;
import code.gui.ProgressDialog;
import code.gui.images.AbstractImage;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public interface ProgressDialogAdv extends ProgressDialog {
    void init(AbstractThreadFactory _th, ProcessingSession _r, Iconifiable _f, CustList<AbstractImage> _p);

    void startAnimation(AbstractThreadFactory _th);

    void stopAnimation();

    AbsCommonFrame getAbsDialog();
}
