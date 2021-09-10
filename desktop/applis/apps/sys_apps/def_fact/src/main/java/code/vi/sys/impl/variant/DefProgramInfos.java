package code.vi.sys.impl.variant;

import code.vi.sys.impl.ProgramInfos;

public final class DefProgramInfos extends ProgramInfos {
    public DefProgramInfos() {
        super(new GraphicStringListGenerator(), new GraphicComboBoxGenerator());
    }
}
