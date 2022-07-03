package code.mock;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.filenames.DefaultNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.*;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.*;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.AbsClipStream;
import code.stream.AbsSoundRecord;
import code.stream.AbstractFileCoreStream;
import code.stream.core.AbstractZipFact;
import code.stream.core.TechStreams;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.NumberUtil;

public abstract class MockAbsProgramInfos implements AbstractProgramInfos {
    private final CustList<AbsGroupFrame> frames = new CustList<AbsGroupFrame>();
    private final StringMap<AbstractAtomicInteger> counts = new StringMap<AbstractAtomicInteger>();
    private final AbstractGraphicStringListGenerator graphicStringListGenerator;
    private final AbstractGraphicComboBoxGenerator graphicComboBoxGenerator;
    private final AbstractAdvGraphicListGenerator geneStrCompo;
    private final DefaultNameValidating validator;
    private final String homePath;
    private final String tmpUserFolder;
    private final MockFileSet mockFileSet;
    private final AbstractInterceptor mockInterceptor = new MockInterceptor();
    private final AbstractGenerator generator;
    private final AbstractImageFactory imageFactory = new MockImageFactory();
    private final TechStreams techStreams;
    private final AbstractThreadFactory threadFactory;
    private final AbstractFileCoreStream fileCoreStream;
    private final AbsCompoFactory compoFactory = new MockCompoFactory();
    private final AbstractSocketFactory socketFactory = new MockSocketFactory();
    private final MockFileFolerDialog mockFileFolerDialog;
    private final MessageDialogAbs messageDialogAbs = new MockMessageDialogAbs();
    private final MockConfirmDialogTextAbs mockConfirmDialogTextAbs;
    private final MockConfirmDialogAnsAbs mockConfirmDialogAnsAbs;
    private int screenWidth;
    private int screenHeight;

    protected MockAbsProgramInfos(String _h, String _t, MockEventListIncr _se, boolean _cust, MockFileSet _set) {
        this.homePath = _h;
        tmpUserFolder = _t;
        generator = new MockGenerator(_se.getSe());
        graphicStringListGenerator = new MockGraphicStringListGenerator();
        graphicComboBoxGenerator = new MockGraphicComboBoxGenerator();
        geneStrCompo = new MockAdvGraphicListGenerator(_cust);
        mockFileSet = _set;
        validator = mockFileSet.getValidating();
        fileCoreStream = new MockFileCoreStream(mockFileSet);
        threadFactory = new MockThreadFactory(generator, mockFileSet);
        MockBinFact mockBinFact_ = new MockBinFact(generator, mockFileSet);
        techStreams = new TechStreams(mockBinFact_,new MockTextFact(mockBinFact_), new MockZipFact());
        mockFileFolerDialog = new MockFileFolerDialog(_se.getFiles());
        mockConfirmDialogTextAbs = new MockConfirmDialogTextAbs(_se.getText());
        mockConfirmDialogAnsAbs = new MockConfirmDialogAnsAbs(_se.getAns());
    }
    @Override
    public StringList getExcludedFolders() {
        return new StringList();
    }

    @Override
    public String getHomePath() {
        return homePath;
    }

    @Override
    public String getTmpUserFolder() {
        return tmpUserFolder;
    }

    @Override
    public CustList<AbsGroupFrame> getFrames() {
        return frames;
    }

    @Override
    public AbsFrameFactory getFrameFactory() {
        return getMockFrameFactory();
    }

    @Override
    public StringMap<AbstractAtomicInteger> getCounts() {
        return counts;
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
        return new MockClipStream(generator, NumberUtil.parseLongZero(ml_.toString()),true);
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
        return new MockClipStream(generator,NumberUtil.parseLongZero(ml_.toString()),false);
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

    @Override
    public AbstractGenerator getGenerator() {
        return generator;
    }

    @Override
    public AbstractThreadFactory getThreadFactory() {
        return threadFactory;
    }

    @Override
    public AbstractZipFact getZipFact() {
        return getStreams().getZipFact();
    }

    @Override
    public AbstractNameValidating getValidator() {
        return validator;
    }

    @Override
    public AbstractGraphicStringListGenerator getGeneGraphicList() {
        return graphicStringListGenerator;
    }

    @Override
    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    @Override
    public AbstractImageFactory getImageFactory() {
        return imageFactory;
    }

    @Override
    public AbstractGraphicComboBoxGenerator getGeneComboBox() {
        return graphicComboBoxGenerator;
    }

    @Override
    public AbstractAdvGraphicListGenerator getGeneStrCompo() {
        return geneStrCompo;
    }

    @Override
    public AbstractInterceptor getInterceptor() {
        return mockInterceptor;
    }

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

    @Override
    public FolderOpenDialogAbs getFolderOpenDialogInt() {
        return getMockFileFolerDialog();
    }

    @Override
    public FileSaveDialogAbs getFileSaveDialogInt() {
        return getMockFileFolerDialog();
    }

    @Override
    public FileOpenDialogAbs getFileOpenDialogInt() {
        return getMockFileFolerDialog();
    }

    public MockFileFolerDialog getMockFileFolerDialog() {
        return mockFileFolerDialog;
    }

    @Override
    public ConfirmDialogTextAbs getConfirmDialogText() {
        return mockConfirmDialogTextAbs;
    }

    @Override
    public ConfirmDialogAnsAbs getConfirmDialogAns() {
        return mockConfirmDialogAnsAbs;
    }

    @Override
    public MessageDialogAbs getMessageDialogAbs() {
        return messageDialogAbs;
    }
}