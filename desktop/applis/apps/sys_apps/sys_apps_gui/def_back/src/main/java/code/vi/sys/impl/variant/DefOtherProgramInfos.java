package code.vi.sys.impl.variant;

import code.gui.initialize.AbstractLightProgramInfos;
import code.vi.prot.impl.variant.DefAdvGraphicComboBoxGenerator;
import code.vi.prot.impl.variant.DefAdvGraphicListGeneratorStr;
import code.vi.prot.impl.variant.DefAdvGraphicStringListGenerator;
import code.vi.prot.impl.variant.LigDefOtherProgramInfos;
import code.vi.sys.impl.deps.ProgramInfosDeps;

public final class DefOtherProgramInfos extends ProgramInfosDeps {
    public DefOtherProgramInfos() {
        super(new DefAdvGraphicStringListGenerator(), new DefAdvGraphicComboBoxGenerator(), new DefAdvGraphicListGeneratorStr());
    }

    @Override
    public AbstractLightProgramInfos light() {
        return new LigDefOtherProgramInfos(getGenerator());
    }
}
