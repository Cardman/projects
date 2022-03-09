package code.vi.prot.impl.variant;

import code.maths.montecarlo.AbstractGenerator;
import code.vi.prot.impl.LightProgramInfos;

public final class LigDefOtherProgramInfos extends LightProgramInfos {
    public LigDefOtherProgramInfos(AbstractGenerator _gene) {
        super(new DefAdvGraphicStringListGenerator(), new DefAdvGraphicComboBoxGenerator(), new DefAdvGraphicListGeneratorStr(),_gene);
    }
}
