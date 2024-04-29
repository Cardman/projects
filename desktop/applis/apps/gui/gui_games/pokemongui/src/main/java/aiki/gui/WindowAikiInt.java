package aiki.gui;

import aiki.gui.components.walk.IntTileRender;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractProgramInfos;

public interface WindowAikiInt {
    void loadZip(boolean _f);

    void loadGame();

    void saveGame();

    AbstractProgramInfos getFrames();
    AbstractImageFactory getImageFactory();

    void afterLoadZip();
    IntTileRender getTileRender();
}
