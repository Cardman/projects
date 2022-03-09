package code.gui.initialize;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.AbsGroupFrame;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.AbsClipStream;
import code.stream.AbstractFileCoreStream;
import code.stream.core.AbstractZipFact;
import code.stream.core.TechStreams;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public interface AbstractProgramInfos {
    StringList getExcludedFolders();
    String getHomePath();
    String getTmpUserFolder();
    CustList<AbsGroupFrame> getFrames();
    AbsFrameFactory getFrameFactory();
    AbsLightFrameFactory getLightFrameFactory();
    StringMap<AbstractAtomicInteger> getCounts();
    AbstractGenerator getGenerator();
    AbstractThreadFactory getThreadFactory();
    AbstractZipFact getZipFact();
    TechStreams getStreams();
    AbstractFileCoreStream getFileCoreStream();
    AbstractInterceptor getInterceptor();

    AbstractNameValidating getValidator();
    AbstractGraphicStringListGenerator getGeneGraphicList();
    AbstractGraphicComboBoxGenerator getGeneComboBox();
    AbstractAdvGraphicListGenerator getGeneStrCompo();
    AbsCompoFactory getCompoFactory();
    AbstractImageFactory getImageFactory();

    AbsClipStream openClip(byte[] _file);
    AbstractImage readImg(String _file);
    AbstractSocketFactory getSocketFactory();
    AbstractProgramInfos light();
    boolean writeImg(String _format, String _file, AbstractImage _img);
    int getScreenWidth();
    int getScreenHeight();
}
