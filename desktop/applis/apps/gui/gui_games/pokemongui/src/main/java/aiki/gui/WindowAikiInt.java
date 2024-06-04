package aiki.gui;

import aiki.facade.FacadeGame;
import aiki.gui.components.walk.IntTileRender;
import aiki.gui.dialogs.ProgressingDialog;
import code.gui.AbsCommonFrame;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBooleanCore;

public interface WindowAikiInt {
    boolean updateConf();
    void loadZip(boolean _f);

    void loadGame();

    void save(String _fileName);
    void saveGame();

    AbstractProgramInfos getFrames();
    AbstractImageFactory getImageFactory();

    FacadeGame facade();
    WindowAikiCore common();
    void reset();
    void iniGui(String _fileName);
    void afterLoadZip();
    IntTileRender getTileRender();
    ProgressingDialog progressDial();
    AbstractAtomicBooleanCore getLoadFlag();
    AbstractAtomicBooleanCore getModal();

    AbsCommonFrame getCommonFrame();
    boolean showErrorMessageDialog(String _fileName);
    void showSuccessfulMessageDialogThenLoadHelp(String _fileName);
}
