package code.vi.sys.impl.variant;

import code.gui.initialize.AbstractLightProgramInfos;
import code.vi.prot.impl.variant.GraphicComboBoxGenerator;
import code.vi.prot.impl.variant.GraphicListGeneratorStr;
import code.vi.prot.impl.variant.GraphicStringListGenerator;
import code.vi.prot.impl.variant.LigDefProgramInfos;
import code.vi.sys.impl.deps.ProgramInfosDeps;

public final class DefProgramInfos extends ProgramInfosDeps {
    public DefProgramInfos() {
        super(new GraphicStringListGenerator(), new GraphicComboBoxGenerator(), new GraphicListGeneratorStr());
    }

    @Override
    public AbstractLightProgramInfos light() {
        return new LigDefProgramInfos(getGenerator());
    }
}
