package code.sys;

import code.renders.LaunchingRenders;
import code.sys.impl.DefProgramInfos;
import code.sys.impl.GraphicComboBoxGenerator;
import code.sys.impl.GraphicStringListGenerator;
import code.sys.impl.ProgramInfos;

public class LaunchingRendersSys extends LaunchingRenders {
    public LaunchingRendersSys() {
        super(new DefProgramInfos());
    }
}
