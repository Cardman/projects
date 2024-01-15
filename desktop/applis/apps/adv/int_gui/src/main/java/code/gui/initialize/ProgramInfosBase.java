package code.gui.initialize;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.AbsButton;
import code.gui.AbsOpenQuit;
import code.gui.images.AbstractImageFactory;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.util.Translations;
import code.sml.util.TranslationsLg;
import code.stream.core.AbstractZipFact;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public class ProgramInfosBase {
    private Translations translations = new Translations();
    private final CustList<AbsOpenQuit> frames = new CustList<AbsOpenQuit>();
    private final StringMap<AbstractAtomicInteger> counts = new StringMap<AbstractAtomicInteger>();
    private final StringMap<AbsButton> buttons = new StringMap<AbsButton>();
    private final String homePath;
    private final String tmpUserFolder;
    private final AbstractGenerator generator;
    private final CompoundedInitParts compoundedInitParts;
    private StringList languages = new StringList();
    private StringMap<String> displayLanguages = new StringMap<String>();
    private String language = "";
    private StringMap<String> common = new StringMap<String>();

    public ProgramInfosBase(String _h, String _t, AbstractGenerator _g, CompoundedInitParts _parts) {
        this.homePath = _h;
        this.tmpUserFolder = _t;
        this.generator = _g;
        this.compoundedInitParts = _parts;
    }
    public TranslationsLg lg(String _key) {
        TranslationsLg lg_ = new TranslationsLg();
        getTranslations().getMapping().addEntry(_key, lg_);
        return lg_;
    }
    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations _trs) {
        this.translations = _trs;
    }

    public StringMap<String> getCommon() {
        return common;
    }

    public void setCommon(StringMap<String> _c) {
        this.common = _c;
    }

    public String getHomePath() {
        return homePath;
    }

    public String getTmpUserFolder() {
        return tmpUserFolder;
    }

    public CustList<AbsOpenQuit> getFrames() {
        return frames;
    }

    public StringMap<AbstractAtomicInteger> getCounts() {
        return counts;
    }

    public StringMap<AbsButton> getButtons() {
        return buttons;
    }

//    public AbstractAdvGraphicListGenerator getGeneStrCompo() {
//        return geneStrCompo;
//    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public AbstractThreadFactory getThreadFactory() {
        return compoundedInitParts.getThreadFactory();
    }

    public AbsCompoFactory getCompoFactory() {
        return compoundedInitParts.getCompoFactory();
    }

    public AbstractImageFactory getImageFactory() {
        return compoundedInitParts.getImageFactory();
    }

    public AbstractNameValidating getValidator() {
        return compoundedInitParts.getValidator();
    }

    public AbstractZipFact getZipFact() {
        return compoundedInitParts.getZipFact();
    }

    public StringList getLanguages() {
        return languages;
    }

    public void setLanguages(StringList _lgs) {
        this.languages = _lgs;
    }

    public StringMap<String> getDisplayLanguages() {
        return displayLanguages;
    }

    public void setDisplayLanguages(StringMap<String> _d) {
        this.displayLanguages = _d;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String _lg) {
        this.language = _lg;
    }
}
