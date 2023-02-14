package code.gui.initialize;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.images.AbstractImageFactory;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.util.Translations;
import code.stream.core.AbstractZipFact;
import code.threads.AbstractThreadFactory;
import code.util.StringList;

public interface AbstractLightProgramInfos {
    Translations getTranslations();
    void setTranslations(Translations _trs);
    AbsLightFrameFactory getLightFrameFactory();
    AbstractGenerator getGenerator();
    AbstractThreadFactory getThreadFactory();
    AbstractZipFact getZipFact();
    AbstractNameValidating getValidator();
    AbstractGraphicStringListGenerator getGeneGraphicList();
    AbsCompoFactory getCompoFactory();
    AbstractImageFactory getImageFactory();
    AbstractGraphicComboBoxGenerator getGeneComboBox();
    StringList getLanguages();
    void setLanguages(StringList _lgs);
    String getLanguage();
    void setLanguage(String _lg);
}
