package code.sys;

import code.expressionlanguage.guicompos.GuiFactroy;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.expressionlanguage.structs.Struct;
import code.sys.impl.GraphicListGenerator;
import code.sys.impl.ProgramInfos;

public class LaunchingFullSys extends LaunchingFull {
    public LaunchingFullSys() {
        super(new ProgramInfos(), new GuiFactroy(new GraphicListGenerator<Struct>()));
    }
}
