package code.vi.sys.impl;


import code.gui.initialize.AbstractLightProgramInfos;
import code.vi.prot.impl.AdvGraphicComboBoxGenerator;
import code.vi.prot.impl.LigOtherProgramInfos;
import code.vi.sys.impl.deps.ProgramInfosDeps;

public final class OtherProgramInfos extends ProgramInfosDeps {
    public OtherProgramInfos() {
        super(new AdvGraphicComboBoxGenerator());
    }

    @Override
    public AbstractLightProgramInfos light() {
        LigOtherProgramInfos light_ = new LigOtherProgramInfos(getGenerator());
        light_.setLanguages(getLanguages());
        light_.setLanguage(getLanguage());
        light_.setTranslations(getTranslations());
        light_.setCommon(getCommon());
        return light_;
    }
}
