package code.player.gui;

import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.AbsPlayBack;
import code.stream.AbsSoundRecord;
import code.stream.StreamBinaryFile;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class WindowRecorder extends GroupFrame implements AbsOpenQuit {
    public static final String EMPTY = "";
    private final AbsSlider rate;
    private final AbsSlider size;
    private final AbsSlider channel;
    private final AbsCustCheckBox signed;
    private final AbsCustCheckBox bigEndian;
    private final AbsTextField fileSave;
    private final AbsButton recordSong;
    private final AbsButton stopSong;
    private final AbsButton playSong;
    private final AbsPlainLabel status = getCompoFactory().newPlainLabel(EMPTY);
    private final AbsSoundRecord soundRecord;
    private AbsPlayBack playBack;
    private boolean built;
    private final AbsButton mainButton;

    public WindowRecorder(AbstractProgramInfos _list, LanguagesButtonsPair _pair) {
        super(_list);
        mainButton = _pair.getMainButton();
        GuiBaseUtil.choose(this);
        StringMap<String> mes_ = SongRenderer.valRecorderMessages(_list.currentLg());
        setTitle(mes_.getVal(MessagesRecorder.TITLE));
        soundRecord = _list.newSoundPattern();
        AbsPanel container_ = _list.getCompoFactory().newPageBox();
        AbsPanel group_ = _list.getCompoFactory().newLineBox();
        alignTopLeft(group_);
        AbsPanel labels_ = _list.getCompoFactory().newPageBox();
        alignTopLeft(labels_);
        AbsPanel inputs_ = _list.getCompoFactory().newPageBox();
        alignTopLeft(inputs_);
        group_.add(labels_);
        group_.add(inputs_);
        alignAddedTopLeft(labels_,_list.getCompoFactory().newPlainLabel(mes_.getVal(MessagesRecorder.RATE)));
        rate = _list.getCompoFactory().newAbsSlider(8000,128000,44100);
        rate.addChangeListener(new StatusChangeSlider(this));
        alignTopLeft(rate);
        inputs_.add(rate);
        alignAddedTopLeft(labels_,_list.getCompoFactory().newPlainLabel(mes_.getVal(MessagesRecorder.SIZE)));
        size = _list.getCompoFactory().newAbsSlider(1,32,16);
        size.addChangeListener(new StatusChangeSlider(this));
        alignTopLeft(size);
        inputs_.add(size);
        alignAddedTopLeft(labels_,_list.getCompoFactory().newPlainLabel(mes_.getVal(MessagesRecorder.CHANNELS)));
        channel = _list.getCompoFactory().newAbsSlider(1,8,2);
        channel.addChangeListener(new StatusChangeSlider(this));
        alignTopLeft(channel);
        inputs_.add(channel);
        alignAddedTopLeft(labels_,_list.getCompoFactory().newPlainLabel(mes_.getVal(MessagesRecorder.SIGNED)));
        signed = _list.getCompoFactory().newCustCheckBox();
        signed.setSelected(true);
        signed.addActionListener(new StatusChangeCheck(this));
        alignTopLeft(signed);
        inputs_.add(signed);
        alignAddedTopLeft(labels_,_list.getCompoFactory().newPlainLabel(mes_.getVal(MessagesRecorder.BIG_ENDIAN)));
        bigEndian = _list.getCompoFactory().newCustCheckBox();
        bigEndian.setSelected(true);
        bigEndian.addActionListener(new StatusChangeCheck(this));
        alignTopLeft(bigEndian);
        inputs_.add(bigEndian);
        alignAddedTopLeft(labels_,_list.getCompoFactory().newPlainLabel(mes_.getVal(MessagesRecorder.FIVE_SAVE)));
        fileSave = _list.getCompoFactory().newTextField();
        alignTopLeft(fileSave);
        inputs_.add(fileSave);
        AbsPanel buttons_ = _list.getCompoFactory().newLineBox();
        alignTopLeft(buttons_);
        recordSong = _list.getCompoFactory().newPlainButton(mes_.getVal(MessagesRecorder.RECORD));
        stopSong = _list.getCompoFactory().newPlainButton(mes_.getVal(MessagesRecorder.STOP));
        playSong = _list.getCompoFactory().newPlainButton(mes_.getVal(MessagesRecorder.PLAY));
        setState();
        recordSong.addActionListener(new RecordingSongAction(this));
        stopSong.addActionListener(new StopRecordingSongAction(this));
        buttons_.add(recordSong);
        buttons_.add(stopSong);
        container_.add(group_);
        container_.add(buttons_);
        alignTopLeft(status);
        container_.add(status);
        playSong.addActionListener(new PlayingSongAction(this));
        container_.add(playSong);
        setContentPane(container_);
        pack();
        setVisible(true);
//        exitMode(_list);
//        setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }

    static void alignAddedTopLeft(AbsPanel _par,AbsCustComponent _compo){
        alignTopLeft(_compo);
        _par.add(_compo);
    }
    static void alignTopLeft(AbsCustComponent _compo){
        _compo.left();
        _compo.top();
    }
    @Override
    public void quit() {
        getCommonFrame().setVisible(false);
        LanguageDialogButtons.enable(mainButton,true);
        GuiBaseUtil.trEx(this);
    }

    @Override
    public String getApplicationName() {
        return "recorder";
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return false;
//    }

    @Override
    public void changeLanguage(String _language) {
        getFrames().setLanguage(_language);
    }
    public void eventRecord() {
        String fileTxt_ = fileSave.getText().trim();
        StringMap<String> mes_ = SongRenderer.valRecorderMessages(getFrames().currentLg());
        if (fileTxt_.isEmpty()){
            status.setText(mes_.getVal(MessagesRecorder.BAD_NAME));
            pack();
            return;
        }
        status.setText(EMPTY);
        pack();
        getFrames().getThreadFactory().newStartedThread(new RecordingSong(fileTxt_,this));
    }
    public void tryRecord(String _file) {
        recordSong.setEnabled(false);
        playSong.setEnabled(false);
        stopSong.setEnabled(true);
        byte[] bytes_ = soundRecord.recordSong();
        StringMap<String> mes_ = SongRenderer.valRecorderMessages(getFrames().currentLg());
        setTitle(StringUtil.simpleStringsFormat(mes_.getVal(MessagesRecorder.RECORDING),Long.toString(soundRecord.millis())));
        if (bytes_.length > 0) {
            StreamBinaryFile.writeFile(_file,bytes_,getStreams());
            return;
        }
        error();
    }
    public void error() {
        setState();
        StringMap<String> mes_ = SongRenderer.valRecorderMessages(getFrames().currentLg());
        status.setText(mes_.getVal(MessagesRecorder.BAD_RECORD));
        pack();
    }
    public void stop() {
        if (playBack != null) {
            playBack.getState().set(false);
        } else {
            soundRecord.getState().set(false);
            built = true;
        }
        setState();
        playBack = null;
        status.setText(EMPTY);
        pack();
    }
    public void setState(){
        recordSong.setEnabled(okRecord());
        stopSong.setEnabled(okStop());
        playSong.setEnabled(okPlay());
    }
    public void play() {
        playSong.setEnabled(false);
        recordSong.setEnabled(false);
        stopSong.setEnabled(true);
        pack();
        playBack();
        GuiBaseUtil.launch(playBack);
        playBack = null;
        setState();
    }

    public void playBack() {
        playBack = soundRecord.build();
    }

    public boolean okRecord(){
        boolean supported_ = soundRecord.supported(rate.getValue(), size.getValue(),
                channel.getValue(), signed.isSelected(), bigEndian.isSelected());
        if (!supported_) {
            soundRecord.restore();
            return false;
        }
        if (soundRecord.isActive()) {
            soundRecord.restore();
            return false;
        }
        soundRecord.update();
        return true;
    }

    public AbsSoundRecord getSoundRecord() {
        return soundRecord;
    }

    public AbsSlider getRate() {
        return rate;
    }

    public AbsSlider getSize() {
        return size;
    }

    public AbsSlider getChannel() {
        return channel;
    }

    public AbsCustCheckBox getSigned() {
        return signed;
    }

    public AbsCustCheckBox getBigEndian() {
        return bigEndian;
    }

    public AbsTextField getFileSave() {
        return fileSave;
    }

    public AbsButton getRecordSong() {
        return recordSong;
    }

    public AbsButton getStopSong() {
        return stopSong;
    }

    public AbsButton getPlaySong() {
        return playSong;
    }

    public AbsPlayBack getPlayBack() {
        return playBack;
    }

    public boolean okStop(){
        return getPlayBack() != null||soundRecord.isActive();
    }
    public boolean okPlay(){
        return built;
    }
//    public boolean okPlay(){
//        return built&&playBack == null;
//    }
}
