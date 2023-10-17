package code.vi.sys.impl.variant;

import code.gui.initialize.AbstractLightProgramInfos;
import code.vi.prot.impl.variant.DefAdvGraphicComboBoxGenerator;
import code.vi.prot.impl.variant.LigDefOtherProgramInfos;
import code.vi.sys.impl.deps.ProgramInfosDeps;

public final class DefOtherProgramInfos extends ProgramInfosDeps {
    public DefOtherProgramInfos() {
        super(new DefAdvGraphicComboBoxGenerator());
    }

    @Override
    public AbstractLightProgramInfos light() {
        LigDefOtherProgramInfos light_ = new LigDefOtherProgramInfos(getGenerator());
        light_.setLanguages(getLanguages());
        light_.setLanguage(getLanguage());
        light_.setTranslations(getTranslations());
        light_.setCommon(getCommon());
        return light_;
    }
}
