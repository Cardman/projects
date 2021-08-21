package code.gui.initialize;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.AbsGroupFrame;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.AbsClipStream;
import code.stream.AbstractFileCoreStream;
import code.stream.core.TechStreams;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringMap;

public interface AbstractProgramInfos {
    String getHomePath();
    String getTmpUserFolder();
    CustList<AbsGroupFrame> getFrames();
    AbsFrameFactory getFrameFactory();
    StringMap<AbstractAtomicInteger> getCounts();
    AbstractGenerator getGenerator();
    AbstractThreadFactory getThreadFactory();
    TechStreams getStreams();
    AbstractFileCoreStream getFileCoreStream();
    AbstractInterceptor getInterceptor();

    AbstractNameValidating getValidator();
    AbstractGraphicStringListGenerator getGeneGraphicList();
    AbstractGraphicComboBoxGenerator getGeneComboBox();
    AbsCompoFactory getCompoFactory();
    AbstractImageFactory getImageFactory();

    AbsClipStream openClip(byte[] _file);
    AbstractImage readImg(String _file);
    AbstractSocketFactory getSocketFactory();
    boolean writeImg(String _format, String _file, AbstractImage _img);
}
