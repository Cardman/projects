package code.vi.sys;

import code.expressionlanguage.guicompos.GuiFactroy;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.vi.sys.impl.variant.DefProgramInfos;
import code.vi.prot.impl.variant.GraphicListGeneratorStr;

public final class LaunchingFullSys extends LaunchingFull {
    public LaunchingFullSys() {
        super(new DefProgramInfos(), new GuiFactroy(new GraphicListGeneratorStr()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingFull.loadLaungage(_args,new LaunchingFullSys());
    }
}
