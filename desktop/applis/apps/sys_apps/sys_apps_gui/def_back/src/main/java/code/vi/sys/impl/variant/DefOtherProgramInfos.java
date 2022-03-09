package code.vi.sys.impl.variant;

import code.vi.prot.impl.variant.DefAdvGraphicComboBoxGenerator;
import code.vi.prot.impl.variant.DefAdvGraphicListGeneratorStr;
import code.vi.prot.impl.variant.DefAdvGraphicStringListGenerator;
import code.vi.sys.impl.ProgramInfos;

public final class DefOtherProgramInfos extends ProgramInfos {
    public DefOtherProgramInfos() {
        super(new DefAdvGraphicStringListGenerator(), new DefAdvGraphicComboBoxGenerator(), new DefAdvGraphicListGeneratorStr());
    }
}
