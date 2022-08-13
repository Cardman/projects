package code.gui.initialize;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.AbsGroupFrame;
import code.gui.images.AbstractImageFactory;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.core.AbstractZipFact;
import code.threads.AbstractAtomicInteger;
import code.util.CustList;
import code.util.StringMap;

public class ProgramInfosBase {
    private final CustList<AbsGroupFrame> frames = new CustList<AbsGroupFrame>();
    private final StringMap<AbstractAtomicInteger> counts = new StringMap<AbstractAtomicInteger>();
    private final String homePath;
    private final String tmpUserFolder;
    private final AbstractGenerator generator;
    private final AbstractGraphicStringListGenerator geneGraphicList;
    private final AbstractGraphicComboBoxGenerator geneComboBox;
    private final AbstractAdvGraphicListGenerator geneStrCompo;
    private final CompoundedInitParts compoundedInitParts;

    public ProgramInfosBase(String _h,String _t,AbstractGenerator _g, AbstractGraphicStringListGenerator _l, AbstractGraphicComboBoxGenerator _c, AbstractAdvGraphicListGenerator _s, CompoundedInitParts _parts) {
        this.homePath = _h;
        this.tmpUserFolder = _t;
        this.generator = _g;
        this.geneGraphicList = _l;
        this.geneComboBox = _c;
        this.geneStrCompo = _s;
        this.compoundedInitParts = _parts;
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


    public AbstractAdvGraphicListGenerator getGeneStrCompo() {
        return geneStrCompo;
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public AbstractGraphicComboBoxGenerator getGeneComboBox() {
        return geneComboBox;
    }

    public AbstractGraphicStringListGenerator getGeneGraphicList() {
        return geneGraphicList;
    }

    public AbsCompoFactory getCompoFactory() {
        return compoundedInitParts.getCompoFactory();
    }

    public AbstractImageFactory getImageFactory() {
        return compoundedInitParts.getImageFactory();
    }

    public AbstractInterceptor getInterceptor() {
        return compoundedInitParts.getInterceptor();
    }

    public AbstractNameValidating getValidator() {
        return compoundedInitParts.getValidator();
    }

    public AbstractZipFact getZipFact() {
        return compoundedInitParts.getZipFact();
    }
}
