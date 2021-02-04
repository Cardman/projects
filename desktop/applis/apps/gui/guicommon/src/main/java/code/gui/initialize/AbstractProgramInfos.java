package code.gui.initialize;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.GroupFrame;
import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;
import code.util.StringMap;

import java.util.concurrent.atomic.AtomicInteger;

public interface AbstractProgramInfos {
    String getHomePath();
    String getTmpUserFolder();
    CustList<GroupFrame> getFrames();
    StringMap<AtomicInteger> getCounts();
    AbstractGenerator getGenerator();
    AbstractNameValidating getValidator();
    AbstractGraphicStringListGenerator getGeneGraphicList();
    AbstractGraphicComboBoxGenerator getGeneComboBox();
}
