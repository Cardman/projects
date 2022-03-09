package code.vi.prot.impl;

import code.maths.montecarlo.AbstractGenerator;

public final class LigOtherProgramInfos extends LightProgramInfos {
    public LigOtherProgramInfos(AbstractGenerator _gene) {
        super(new AdvGraphicStringListGenerator(), new AdvGraphicComboBoxGenerator(), new AdvGraphicListGeneratorStr(),_gene);
    }
}
