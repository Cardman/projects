package code.vi.sys.impl.variant;

import code.gui.initialize.AbstractLightProgramInfos;
import code.gui.initialize.AbstractProgramInfos;
import code.vi.prot.impl.variant.DefAdvGraphicComboBoxGenerator;
import code.vi.prot.impl.variant.DefAdvGraphicListGeneratorStr;
import code.vi.prot.impl.variant.DefAdvGraphicStringListGenerator;
import code.vi.prot.impl.variant.LigDefOtherProgramInfos;
import code.vi.sys.impl.ProgramInfos;

public final class DefOtherProgramInfos extends ProgramInfos {
    public DefOtherProgramInfos() {
        super(new DefAdvGraphicStringListGenerator(), new DefAdvGraphicComboBoxGenerator(), new DefAdvGraphicListGeneratorStr());
    }

    @Override
    public AbstractLightProgramInfos light() {
        return new LigDefOtherProgramInfos(getGenerator());
    }
}
