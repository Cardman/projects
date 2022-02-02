package code.vi.prot.impl;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.filenames.DefaultNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.AbsGroupFrame;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.*;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.AbsClipStream;
import code.stream.AbstractFileCoreStream;
import code.stream.core.DefZipFact;
import code.stream.core.TechStreams;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class LightProgramInfos implements AbstractProgramInfos {

    private final CustList<AbsGroupFrame> frames = new CustList<AbsGroupFrame>();
    private final StringMap<AbstractAtomicInteger> counts = new StringMap<AbstractAtomicInteger>();
    private final AbstractGenerator generator;
    private final String tmpUserFolder;
    private final String homePath;
    private final DefaultNameValidating validator;
    private final AbstractGraphicStringListGenerator graphicStringListGenerator;
    private final AbsCompoFactory compoFactory;
    private final AbstractImageFactory imageFactory;
    private final AbstractGraphicComboBoxGenerator graphicComboBoxGenerator;
    private final AbstractThreadFactory threadFactory;
    private final AbstractFileCoreStream fileCoreStream;
    private final TechStreams streams;
    private final AbstractInterceptor interceptor;
    private final AbstractSocketFactory socketFactory;
    private final AbsLightFrameFactory lightFrameFactory;

    protected LightProgramInfos(AbstractGraphicStringListGenerator _graphicStringListGenerator, AbstractGraphicComboBoxGenerator _graphicComboBoxGenerator, AbstractGenerator _gene) {
        threadFactory = new DefaultThreadFactory();
        fileCoreStream = null;
        lightFrameFactory = new DefLigFrameFactory();
        streams = new TechStreams(null,null,new DefZipFact(new DefZipFactory()));
        interceptor = new DefInterceptor(new DefErrGenerator());
        socketFactory = null;
        compoFactory = new DefCompoFactory();
        imageFactory = new DefImageFactory();
        graphicStringListGenerator = _graphicStringListGenerator;
        graphicComboBoxGenerator = _graphicComboBoxGenerator;
        homePath = "";
        tmpUserFolder = "";
        generator = _gene;
        validator = new DefaultNameValidating(new StringList());
    }

    public StringList getExcludedFolders() {
        return new StringList();
    }

    @Override
    public AbsFrameFactory getFrameFactory() {
        return null;
    }

    @Override
    public AbsLightFrameFactory getLightFrameFactory() {
        return lightFrameFactory;
    }

    public String getHomePath() {
        return homePath;
    }

    public String getTmpUserFolder() {
        return tmpUserFolder;
    }

    public CustList<AbsGroupFrame> getFrames() {
        return frames;
    }

    public StringMap<AbstractAtomicInteger> getCounts() {
        return counts;
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public AbstractThreadFactory getThreadFactory() {
        return threadFactory;
    }

    @Override
    public AbstractFileCoreStream getFileCoreStream() {
        return fileCoreStream;
    }

    public TechStreams getStreams() {
        return streams;
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
    public AbsClipStream openClip(byte[] _file) {
        return null;
    }

    @Override
    public AbstractImage readImg(String _file) {
        return null;
    }

    @Override
    public boolean writeImg(String _format, String _file, AbstractImage _img) {
        return false;
    }

    @Override
    public AbstractInterceptor getInterceptor() {
        return interceptor;
    }

    @Override
    public AbstractSocketFactory getSocketFactory() {
        return socketFactory;
    }

    @Override
    public int getScreenHeight() {
        return 0;
    }

    @Override
    public int getScreenWidth() {
        return 0;
    }
}
