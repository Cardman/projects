package aiki.gui;

import aiki.main.AikiFactory;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractProgramInfos;

public interface WindowAikiInt {
    void loadZip(boolean _f);

    void loadGame();

    void saveGame();
    AikiFactory getAikiFactory();

    AbstractProgramInfos getFrames();
    AbstractImageFactory getImageFactory();

    void afterLoadZip();
}
