package code.gui.initialize;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.images.AbstractImageFactory;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.core.AbstractZipFact;
import code.threads.AbstractThreadFactory;
import code.util.StringList;

public interface AbstractLightProgramInfos {
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
}
