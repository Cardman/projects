package code.vi.prot.impl.variant;

import code.maths.montecarlo.AbstractGenerator;
import code.vi.prot.impl.LightProgramInfos;

public final class LigDefProgramInfos extends LightProgramInfos {
    public LigDefProgramInfos(AbstractGenerator _gene) {
        super(new GraphicStringListGenerator(), new GraphicComboBoxGenerator(),_gene);
    }
}
