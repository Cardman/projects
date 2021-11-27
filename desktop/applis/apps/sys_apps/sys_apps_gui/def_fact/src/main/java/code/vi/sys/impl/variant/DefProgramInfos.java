package code.vi.sys.impl.variant;

import code.vi.prot.impl.variant.GraphicComboBoxGenerator;
import code.vi.prot.impl.variant.GraphicStringListGenerator;
import code.vi.sys.impl.ProgramInfos;

public final class DefProgramInfos extends ProgramInfos {
    public DefProgramInfos() {
        super(new GraphicStringListGenerator(), new GraphicComboBoxGenerator());
    }
}
