package code.mock;

import code.gui.initialize.*;
import code.maths.montecarlo.*;
import code.stream.AbsClipStream;
import code.stream.AbsSoundRecord;
import code.stream.AbstractFileCoreStream;
import code.stream.FileListInfo;
import code.stream.core.TechStreams;
import code.util.StringList;
import code.util.core.NumberUtil;

public abstract class MockAbsProgramInfos extends ProgramInfosBase implements AbstractProgramInfos {
    private final MockFileSet mockFileSet;
    private final TechStreams techStreams;
    private final AbstractFileCoreStream fileCoreStream;
    private final AbstractSocketFactory socketFactory = new MockSocketFactory();
    private int screenWidth;
    private int screenHeight;

    protected MockAbsProgramInfos(String _h, String _t, CustomSeedGene _se, MockFileSet _set, MockAbsRand _abs) {
        this(_h,_t,new DefaultGenerator(_se), _set, _abs);
    }
    protected MockAbsProgramInfos(String _h, String _t, AbstractGenerator _gene, MockFileSet _set, MockAbsRand _abs) {
        super(_h,_t,_gene,
                new CompoundedInitParts(new MockThreadFactory(_abs, _set),new MockZipFact(),_set.getValidating(),new MockCompoFactory(),new MockImageFactory()));
        mockFileSet = _set;
        fileCoreStream = new MockFileCoreStream(_set);
        MockBinFact mockBinFact_ = new MockBinFact(_abs, _set);
        techStreams = new TechStreams(mockBinFact_, new MockZipFact());
    }
    @Override
    public StringList getExcludedFolders() {
        return new StringList();
    }

    @Override
    public AbsFrameFactory getFrameFactory() {
        return getMockFrameFactory();
    }
    @Override
    public TechStreams getStreams() {
        return techStreams;
    }

    @Override
    public AbstractFileCoreStream getFileCoreStream() {
        return fileCoreStream;
    }

    @Override
    public AbsClipStream openClip(byte[] _bytes) {
        if (!FileListInfo.isWav(_bytes)) {
            return null;
        }
        int offset_ = 12;
        StringBuilder ml_ = build(_bytes, offset_);
        if (ml_ == null) {
            return null;
        }
        return new MockClipStream(new MockTrueRand(), NumberUtil.parseLongZero(ml_.toString()),true);
    }

    @Override
    public AbsClipStream openMp3(byte[] _bytes) {
        if (!FileListInfo.isMp3(_bytes)) {
            return null;
        }
        int offset_;
        if (FileListInfo.isMpFirst3(_bytes)) {
            offset_ = 2;
        } else {
            offset_ = 3;
        }
        StringBuilder ml_ = build(_bytes, offset_);
        if (ml_ == null) {
            return null;
        }
        return new MockClipStream(new MockTrueRand(),NumberUtil.parseLongZero(ml_.toString()),false);
    }

    @Override
    public AbstractSocketFactory getSocketFactory() {
        return socketFactory;
    }

    @Override
    public AbstractLightProgramInfos light() {
        return this;
    }

    @Override
    public int getScreenWidth() {
        return screenWidth;
    }

    @Override
    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int _s) {
        this.screenHeight = _s;
    }

    public void setScreenWidth(int _s) {
        this.screenWidth = _s;
    }

    @Override
    public AbsSoundRecord newSoundPattern() {
        return new MockSoundRecord();
    }

    @Override
    public AbsLightFrameFactory getLightFrameFactory() {
        return getMockFrameFactory();
    }

    public abstract MockAbsFrameFactory getMockFrameFactory();

    public MockFileSet getMockFileSet() {
        return mockFileSet;
    }

    public String getCurrentPath(){
        return mockFileSet.getCurrentPath();
    }
    public void setCurrentPath(String _path) {
        mockFileSet.setCurrentPath(_path);
    }
    private StringBuilder build(byte[] _bytes, int _offset) {
        int len_ = _bytes.length;
        StringBuilder ml_ = new StringBuilder();
        for (int i = _offset; i < len_; i++) {
            if (_bytes[i] < 0 || _bytes[i] > 9) {
                return null;
            }
            ml_.append(_bytes[i]);
        }
        if (ml_.length() == 0) {
            return null;
        }
        return ml_;
    }

}