package code.gui.initialize;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.images.AbstractImageFactory;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.util.Translations;
import code.stream.core.AbstractZipFact;
import code.threads.AbstractThreadFactory;
import code.util.StringList;
import code.util.StringMap;

public interface AbstractLightProgramInfos {
    Translations getTranslations();
    void setTranslations(Translations _trs);
    StringMap<String> getCommon();
    void setCommon(StringMap<String> _c);
    AbsLightFrameFactory getLightFrameFactory();
    AbstractGenerator getGenerator();
    AbstractThreadFactory getThreadFactory();
    AbstractZipFact getZipFact();
    AbstractNameValidating getValidator();

    AbsCompoFactory getCompoFactory();
    AbstractImageFactory getImageFactory();
    StringList getLanguages();
    void setLanguages(StringList _lgs);
    StringMap<String> getDisplayLanguages();
    void setDisplayLanguages(StringMap<String> _d);
    String getLanguage();
    void setLanguage(String _lg);
}
