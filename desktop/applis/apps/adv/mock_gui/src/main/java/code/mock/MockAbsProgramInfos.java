package code.mock;

import code.gui.initialize.*;
import code.maths.montecarlo.*;
import code.stream.AbsClipStream;
import code.stream.AbsSoundRecord;
import code.stream.AbstractFileCoreStream;
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

    protected MockAbsProgramInfos(String _h, String _t, CustomSeedGene _se, MockFileSet _set) {
        this(_h,_t,new DefaultGenerator(_se), _set);
    }

    protected MockAbsProgramInfos(String _h, String _t, CustomSeedGene _se, MockFileSet _set, MockAbsRand _abs) {
        this(_h,_t,new DefaultGenerator(_se), _set, _abs);
    }
    protected MockAbsProgramInfos(String _h, String _t, AbstractGenerator _gene, MockFileSet _set) {
        this(_h, _t, _gene, _set, new MockRand(_gene));
    }
    protected MockAbsProgramInfos(String _h, String _t, AbstractGenerator _gene, MockFileSet _set, MockAbsRand _abs) {
        super(_h,_t,_gene,
                new CompoundedInitParts(new MockThreadFactory(_abs, _set),new MockZipFact(),_set.getValidating(),new MockCompoFactory(),new MockImageFactory()));
        mockFileSet = _set;
        fileCoreStream = new MockFileCoreStream(_set);
        MockBinFact mockBinFact_ = new MockBinFact(_abs, _set);
        techStreams = new TechStreams(mockBinFact_,new MockTextFact(mockBinFact_), new MockZipFact());
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
        if (!isWav(_bytes)) {
            return null;
        }
        int offset_ = 12;
        StringBuilder ml_ = build(_bytes, offset_);
        if (ml_ == null) {
            return null;
        }
        return new MockClipStream(getGenerator(), NumberUtil.parseLongZero(ml_.toString()),true);
    }

    @Override
    public AbsClipStream openMp3(byte[] _bytes) {
        if (!isMp3(_bytes)) {
            return null;
        }
        int offset_;
        if (isMpFirst3(_bytes)) {
            offset_ = 2;
        } else {
            offset_ = 3;
        }
        StringBuilder ml_ = build(_bytes, offset_);
        if (ml_ == null) {
            return null;
        }
        return new MockClipStream(getGenerator(),NumberUtil.parseLongZero(ml_.toString()),false);
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

    private boolean isWav(byte[] _bytes) {
        if (_bytes.length < 12) {
            return false;
        }
        return _bytes[0] == 'R' && _bytes[1] == 'I' && _bytes[2] == 'F' && _bytes[3] == 'F'
                &&_bytes[8] == 'W' && _bytes[9] == 'A' && _bytes[10] == 'V' && _bytes[11] == 'E';
    }
    private boolean isMp3(byte[] _bytes) {
        if (isMpFirst3(_bytes)) {
            return true;
        }
        return _bytes.length >= 3 && _bytes[0] == 'I' && _bytes[1] == 'D' && _bytes[2] == '3';
    }
    private boolean isMpFirst3(byte[] _bytes) {
        if (_bytes.length < 2) {
            return false;
        }
        if (_bytes[0] == (byte)255 && _bytes[1] == (byte)251) {
            return true;
        }
        if (_bytes[0] == (byte)255 && _bytes[1] == (byte)243) {
            return true;
        }
        return _bytes[0] == (byte) 255 && _bytes[1] == (byte) 242;
    }

}