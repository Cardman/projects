package code.vi.sys.impl.variant;

import applications.code.gui.WithAppFactories;
import code.gui.initialize.AbstractLightProgramInfos;
import code.vi.prot.impl.variant.LigDefProgramInfos;
import code.vi.sys.impl.deps.ProgramInfosDeps;

public final class DefProgramInfos extends ProgramInfosDeps {

    public static WithAppFactories build() {
        return build(new DefProgramInfos());
    }
    @Override
    public AbstractLightProgramInfos light() {
        LigDefProgramInfos light_ = new LigDefProgramInfos(getGenerator());
        light_.setLanguages(getLanguages());
        light_.setLanguage(getLanguage());
        light_.setTranslations(getTranslations());
        light_.setCommon(getCommon());
        return light_;
    }
}
