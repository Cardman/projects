package code.vi.sys.impl.variant;

import code.vi.sys.impl.ProgramInfos;

public final class DefOtherProgramInfos extends ProgramInfos {
    public DefOtherProgramInfos() {
        super(new DefAdvGraphicStringListGenerator(), new DefAdvGraphicComboBoxGenerator());
    }
}
