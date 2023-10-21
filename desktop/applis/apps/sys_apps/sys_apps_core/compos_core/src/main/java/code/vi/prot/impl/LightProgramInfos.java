package code.vi.prot.impl;

import code.expressionlanguage.filenames.DefaultNameValidating;
import code.gui.initialize.AbsLightFrameFactory;
import code.gui.initialize.AbstractLightProgramInfos;
import code.gui.initialize.CompoundedInitParts;
import code.gui.initialize.ProgramInfosBase;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.core.DefZipFact;
import code.util.StringList;

public abstract class LightProgramInfos extends ProgramInfosBase implements AbstractLightProgramInfos {

    private final AbsLightFrameFactory lightFrameFactory;

    protected LightProgramInfos(AbstractGenerator _gene) {
        super("","",_gene,
                new CompoundedInitParts(new DefaultThreadFactory(),new DefZipFact(new DefZipFactory()),new DefaultNameValidating(new StringList()),new DefCompoFactory(),new DefImageFactory()));
        lightFrameFactory = new DefLigFrameFactory();
    }

    @Override
    public AbsLightFrameFactory getLightFrameFactory() {
        return lightFrameFactory;
    }

}
