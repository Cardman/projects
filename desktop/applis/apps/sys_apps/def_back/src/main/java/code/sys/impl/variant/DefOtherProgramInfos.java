package code.sys.impl.variant;

import code.sys.impl.ProgramInfos;

public final class DefOtherProgramInfos extends ProgramInfos {
    public DefOtherProgramInfos() {
        super(new DefAdvGraphicStringListGenerator(), new DefAdvGraphicComboBoxGenerator());
    }
}
