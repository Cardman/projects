package code.vi.sys.impl.variant;

import applications.code.gui.WithAppFactories;
import code.gui.initialize.AbstractLightProgramInfos;
import code.vi.prot.impl.variant.LigDefProgramInfos;
import code.vi.sys.impl.deps.ProgramInfosDeps;

public final class DefProgramInfos extends ProgramInfosDeps {

    public static WithAppFactories build(String _tmp) {
        return build(new DefProgramInfos(), _tmp);
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
