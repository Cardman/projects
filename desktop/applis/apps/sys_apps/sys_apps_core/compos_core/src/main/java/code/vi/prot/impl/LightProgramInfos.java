package code.vi.prot.impl;

import code.expressionlanguage.filenames.DefaultNameValidating;
import code.gui.initialize.*;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.core.DefZipFact;
import code.util.StringList;

public abstract class LightProgramInfos extends ProgramInfosBase implements AbstractLightProgramInfos {

    private final AbsLightFrameFactory lightFrameFactory;

    protected LightProgramInfos(AbstractGraphicComboBoxGenerator _graphicComboBoxGenerator, AbstractGenerator _gene) {
        super("","",_gene, _graphicComboBoxGenerator,
                new CompoundedInitParts(new DefaultThreadFactory(),new DefZipFact(new DefZipFactory()),new DefaultNameValidating(new StringList()),new DefCompoFactory(),new DefImageFactory()));
        lightFrameFactory = new DefLigFrameFactory();
    }

    @Override
    public AbsLightFrameFactory getLightFrameFactory() {
        return lightFrameFactory;
    }

}
