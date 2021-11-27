package code.vi.sys.impl;


import code.vi.prot.impl.AdvGraphicComboBoxGenerator;
import code.vi.prot.impl.AdvGraphicStringListGenerator;

public final class OtherProgramInfos extends ProgramInfos {
    public OtherProgramInfos() {
        super(new AdvGraphicStringListGenerator(), new AdvGraphicComboBoxGenerator());
    }
}
