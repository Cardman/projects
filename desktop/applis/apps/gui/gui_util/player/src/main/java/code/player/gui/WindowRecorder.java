package code.player.gui;

import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.AbsPlayBack;
import code.stream.AbsSoundRecord;
import code.stream.StreamBinaryFile;

public final class WindowRecorder extends GroupFrame implements AbsOpenQuit {
    private final AbsSlider rate;
    private final AbsSlider size;
    private final AbsSlider channel;
    private final AbsCustCheckBox signed;
    private final AbsCustCheckBox bigEndian;
    private final AbsTextField fileSave;
    private final AbsButton recordSong;
    private final AbsButton stopSong;
    private final AbsButton playSong;
    private final AbsPlainLabel status = getCompoFactory().newPlainLabel("");
    private final AbsSoundRecord soundRecord;
    private AbsPlayBack playBack;
    private boolean built;

    public WindowRecorder(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
        GuiBaseUtil.choose(_lg, this, _list.getCommon());
        setTitle("recorder");
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
        alignAddedTopLeft(labels_,_list.getCompoFactory().newPlainLabel("rate"));
        rate = _list.getCompoFactory().newAbsSlider(8000,128000,44100);
        rate.addChangeListener(new StatusChangeSlider(this));
        alignTopLeft(rate);
        inputs_.add(rate);
        alignAddedTopLeft(labels_,_list.getCompoFactory().newPlainLabel("size"));
        size = _list.getCompoFactory().newAbsSlider(1,32,16);
        size.addChangeListener(new StatusChangeSlider(this));
        alignTopLeft(size);
        inputs_.add(size);
        alignAddedTopLeft(labels_,_list.getCompoFactory().newPlainLabel("channels"));
        channel = _list.getCompoFactory().newAbsSlider(1,8,2);
        channel.addChangeListener(new StatusChangeSlider(this));
        alignTopLeft(channel);
        inputs_.add(channel);
        alignAddedTopLeft(labels_,_list.getCompoFactory().newPlainLabel("signed"));
        signed = _list.getCompoFactory().newCustCheckBox();
        signed.setSelected(true);
        signed.addActionListener(new StatusChangeCheck(this));
        alignTopLeft(signed);
        inputs_.add(signed);
        alignAddedTopLeft(labels_,_list.getCompoFactory().newPlainLabel("big endian"));
        bigEndian = _list.getCompoFactory().newCustCheckBox();
        bigEndian.setSelected(true);
        bigEndian.addActionListener(new StatusChangeCheck(this));
        alignTopLeft(bigEndian);
        inputs_.add(bigEndian);
        alignAddedTopLeft(labels_,_list.getCompoFactory().newPlainLabel("file save"));
        fileSave = _list.getCompoFactory().newTextField();
        alignTopLeft(fileSave);
        inputs_.add(fileSave);
        AbsPanel buttons_ = _list.getCompoFactory().newLineBox();
        alignTopLeft(buttons_);
        recordSong = _list.getCompoFactory().newPlainButton("record");
        stopSong = _list.getCompoFactory().newPlainButton("stop");
        playSong = _list.getCompoFactory().newPlainButton("play");
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
        setLanguageKey(_language);
    }
    public void eventRecord() {
        String fileTxt_ = fileSave.getText().trim();
        if (fileTxt_.isEmpty()){
            status.setText("Error");
            pack();
            return;
        }
        status.setText("");
        pack();
        getFrames().getThreadFactory().newStartedThread(new RecordingSong(fileTxt_,this));
    }
    public void tryRecord(String _file) {
        recordSong.setEnabled(false);
        playSong.setEnabled(false);
        stopSong.setEnabled(true);
        byte[] bytes_ = soundRecord.recordSong();
        setTitle("recorder "+soundRecord.millis()+" ms");
        if (bytes_.length > 0) {
            StreamBinaryFile.writeFile(_file,bytes_,getStreams());
            return;
        }
        error();
    }
    public void error() {
        setState();
        status.setText("Error");
        pack();
    }
    public void stop() {
        if (playBack != null) {
            playBack.getState().set(false);
        } else {
            soundRecord.getState().set(false);
            soundRecord.stop();
            built = true;
        }
        setState();
        playBack = null;
        status.setText("");
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
        playBack = soundRecord.build();
        GuiBaseUtil.launch(playBack);
        playBack = null;
        setState();
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
    public boolean okStop(){
        return playBack != null||soundRecord.isActive();
    }
    public boolean okPlay(){
        return built&&playBack == null;
    }
}
