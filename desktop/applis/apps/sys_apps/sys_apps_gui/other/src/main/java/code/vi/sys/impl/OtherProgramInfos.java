package code.vi.sys.impl;


import code.gui.initialize.AbstractLightProgramInfos;
import code.vi.prot.impl.AdvGraphicComboBoxGenerator;
import code.vi.prot.impl.AdvGraphicListGeneratorStr;
import code.vi.prot.impl.AdvGraphicStringListGenerator;
import code.vi.prot.impl.LigOtherProgramInfos;
import code.vi.sys.impl.deps.ProgramInfosDeps;

public final class OtherProgramInfos extends ProgramInfosDeps {
    public OtherProgramInfos() {
        super(new AdvGraphicStringListGenerator(), new AdvGraphicComboBoxGenerator(), new AdvGraphicListGeneratorStr());
    }

    @Override
    public AbstractLightProgramInfos light() {
        return new LigOtherProgramInfos(getGenerator());
    }
}
