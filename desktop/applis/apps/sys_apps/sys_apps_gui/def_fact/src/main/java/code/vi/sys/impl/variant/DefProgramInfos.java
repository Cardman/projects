package code.vi.sys.impl.variant;

import code.gui.initialize.AbstractLightProgramInfos;
import code.vi.prot.impl.variant.GraphicComboBoxGenerator;
import code.vi.prot.impl.variant.GraphicStringListGenerator;
import code.vi.prot.impl.variant.LigDefProgramInfos;
import code.vi.sys.impl.deps.ProgramInfosDeps;

public final class DefProgramInfos extends ProgramInfosDeps {
    public DefProgramInfos() {
        super(new GraphicStringListGenerator(), new GraphicComboBoxGenerator());
    }

    @Override
    public AbstractLightProgramInfos light() {
        LigDefProgramInfos light_ = new LigDefProgramInfos(getGenerator());
        light_.setLanguages(getLanguages());
        light_.setLanguage(getLanguage());
        light_.setTranslations(getTranslations());
        return light_;
    }
}
