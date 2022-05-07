package code.gui.initialize;

import code.gui.AbsGroupFrame;
import code.stream.AbsClipStream;
import code.stream.AbsSoundRecord;
import code.stream.AbstractFileCoreStream;
import code.stream.core.TechStreams;
import code.threads.AbstractAtomicInteger;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public interface AbstractProgramInfos extends AbstractLightProgramInfos {
    StringList getExcludedFolders();
    String getHomePath();
    String getTmpUserFolder();
    CustList<AbsGroupFrame> getFrames();
    AbsFrameFactory getFrameFactory();
    StringMap<AbstractAtomicInteger> getCounts();
    TechStreams getStreams();
    AbstractFileCoreStream getFileCoreStream();

    AbsClipStream openClip(byte[] _file);
    AbsClipStream openMp3(byte[] _file);
    AbstractSocketFactory getSocketFactory();
    AbstractLightProgramInfos light();

    int getScreenWidth();
    int getScreenHeight();

    AbsSoundRecord newSoundPattern();
}
