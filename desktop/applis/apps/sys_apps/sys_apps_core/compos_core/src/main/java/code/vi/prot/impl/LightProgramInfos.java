package code.vi.prot.impl;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.filenames.DefaultNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.*;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.core.AbstractZipFact;
import code.stream.core.DefZipFact;
import code.threads.AbstractThreadFactory;
import code.util.StringList;

public abstract class LightProgramInfos implements AbstractLightProgramInfos {

    private final AbstractGenerator generator;
    private final DefaultNameValidating validator;
    private final AbstractGraphicStringListGenerator graphicStringListGenerator;
    private final AbsCompoFactory compoFactory;
    private final AbstractImageFactory imageFactory;
    private final AbstractGraphicComboBoxGenerator graphicComboBoxGenerator;
    private final AbstractAdvGraphicListGenerator geneStrCompo;
    private final AbstractThreadFactory threadFactory;
    private final AbstractInterceptor interceptor;
    private final AbsLightFrameFactory lightFrameFactory;
    private final DefZipFact zipFact;

    protected LightProgramInfos(AbstractGraphicStringListGenerator _graphicStringListGenerator, AbstractGraphicComboBoxGenerator _graphicComboBoxGenerator,AbstractAdvGraphicListGenerator _graphicListGenerator, AbstractGenerator _gene) {
        threadFactory = new DefaultThreadFactory();
        lightFrameFactory = new DefLigFrameFactory();
        zipFact = new DefZipFact(new DefZipFactory());
        interceptor = new DefInterceptor(new DefErrGenerator());
        compoFactory = new DefCompoFactory();
        imageFactory = new DefImageFactory();
        graphicStringListGenerator = _graphicStringListGenerator;
        graphicComboBoxGenerator = _graphicComboBoxGenerator;
        geneStrCompo = _graphicListGenerator;
        generator = _gene;
        validator = new DefaultNameValidating(new StringList());
    }

    @Override
    public AbsLightFrameFactory getLightFrameFactory() {
        return lightFrameFactory;
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public AbstractThreadFactory getThreadFactory() {
        return threadFactory;
    }

    @Override
    public AbstractZipFact getZipFact() {
        return zipFact;
    }

    @Override
    public AbstractNameValidating getValidator() {
        return validator;
    }

    @Override
    public AbstractGraphicStringListGenerator getGeneGraphicList() {
        return graphicStringListGenerator;
    }

    @Override
    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    @Override
    public AbstractImageFactory getImageFactory() {
        return imageFactory;
    }

    @Override
    public AbstractGraphicComboBoxGenerator getGeneComboBox() {
        return graphicComboBoxGenerator;
    }

    @Override
    public AbstractAdvGraphicListGenerator getGeneStrCompo() {
        return geneStrCompo;
    }
    @Override
    public AbstractInterceptor getInterceptor() {
        return interceptor;
    }
}
