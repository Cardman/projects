package code.gui.initialize;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.GroupFrame;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.AbsClipStream;
import code.stream.AbstractFileCoreStream;
import code.stream.core.TechStreams;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringMap;

import java.awt.image.BufferedImage;

public interface AbstractProgramInfos {
    String getHomePath();
    String getTmpUserFolder();
    CustList<GroupFrame> getFrames();
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

    AbsClipStream openClip(byte[] _file);
    BufferedImage readImg(String _file);
    AbstractSocketFactory getSocketFactory();
    boolean writeImg(String _format, String _file, BufferedImage _img);
}
