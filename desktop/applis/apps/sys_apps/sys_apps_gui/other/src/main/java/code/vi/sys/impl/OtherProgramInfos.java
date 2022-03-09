package code.vi.sys.impl;


import code.gui.initialize.AbstractProgramInfos;
import code.vi.prot.impl.AdvGraphicComboBoxGenerator;
import code.vi.prot.impl.AdvGraphicListGeneratorStr;
import code.vi.prot.impl.AdvGraphicStringListGenerator;
import code.vi.prot.impl.LigOtherProgramInfos;

public final class OtherProgramInfos extends ProgramInfos {
    public OtherProgramInfos() {
        super(new AdvGraphicStringListGenerator(), new AdvGraphicComboBoxGenerator(), new AdvGraphicListGeneratorStr());
    }

    @Override
    public AbstractProgramInfos light() {
        return new LigOtherProgramInfos(getGenerator());
    }
}
