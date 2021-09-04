package code.sys;

import code.expressionlanguage.guicompos.GuiFactroy;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.sys.impl.*;

public final class LaunchingFullSys extends LaunchingFull {
    public LaunchingFullSys() {
        super(new DefProgramInfos(), new GuiFactroy(new GraphicListGeneratorStr()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingFull.loadLaungage(_args,new LaunchingFullSys());
    }
}
