package code.sys;

import code.expressionlanguage.guicompos.GuiFactroy;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.sys.impl.*;

public class LaunchingFullSys extends LaunchingFull {
    public LaunchingFullSys() {
        super(new DefProgramInfos(), new GuiFactroy(new GraphicListGeneratorStr()));
    }
}
