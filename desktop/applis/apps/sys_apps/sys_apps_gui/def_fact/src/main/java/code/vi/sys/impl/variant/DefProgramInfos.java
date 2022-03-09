package code.vi.sys.impl.variant;

import code.gui.initialize.AbstractLightProgramInfos;
import code.gui.initialize.AbstractProgramInfos;
import code.vi.prot.impl.variant.GraphicComboBoxGenerator;
import code.vi.prot.impl.variant.GraphicListGeneratorStr;
import code.vi.prot.impl.variant.GraphicStringListGenerator;
import code.vi.prot.impl.variant.LigDefProgramInfos;
import code.vi.sys.impl.ProgramInfos;

public final class DefProgramInfos extends ProgramInfos {
    public DefProgramInfos() {
        super(new GraphicStringListGenerator(), new GraphicComboBoxGenerator(), new GraphicListGeneratorStr());
    }

    @Override
    public AbstractLightProgramInfos light() {
        return new LigDefProgramInfos(getGenerator());
    }
}
