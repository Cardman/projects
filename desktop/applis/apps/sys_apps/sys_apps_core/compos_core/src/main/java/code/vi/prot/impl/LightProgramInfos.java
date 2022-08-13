package code.vi.prot.impl;

import code.expressionlanguage.filenames.DefaultNameValidating;
import code.gui.initialize.*;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.core.DefZipFact;
import code.threads.AbstractThreadFactory;
import code.util.StringList;

public abstract class LightProgramInfos extends ProgramInfosBase implements AbstractLightProgramInfos {

    private final AbstractThreadFactory threadFactory;
    private final AbsLightFrameFactory lightFrameFactory;

    protected LightProgramInfos(AbstractGraphicStringListGenerator _graphicStringListGenerator, AbstractGraphicComboBoxGenerator _graphicComboBoxGenerator,AbstractAdvGraphicListGenerator _graphicListGenerator, AbstractGenerator _gene) {
        super("","",_gene,_graphicStringListGenerator,_graphicComboBoxGenerator,_graphicListGenerator,
                new CompoundedInitParts(new DefZipFact(new DefZipFactory()),new DefaultNameValidating(new StringList()),new DefCompoFactory(),new DefImageFactory(),new DefInterceptor(new DefErrGenerator())));
        threadFactory = new DefaultThreadFactory();
        lightFrameFactory = new DefLigFrameFactory();
    }

    @Override
    public AbsLightFrameFactory getLightFrameFactory() {
        return lightFrameFactory;
    }


    public AbstractThreadFactory getThreadFactory() {
        return threadFactory;
    }

}
