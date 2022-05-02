package code.player.gui;

import code.gui.*;
import code.gui.events.QuittingEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.AbsSoundRecord;

public final class WindowRecorder extends GroupFrame {
    private final AbsSlider rate;
    private final AbsSlider size;
    private final AbsSlider channel;
    private final AbsCustCheckBox signed;
    private final AbsCustCheckBox bigEndian;
    private final AbsTextField fileSave;
    private final AbsPlainButton recordSong;
    private final AbsPlainButton stopSong;
    private final AbsPlainLabel status = getCompoFactory().newPlainLabel("");
    private final AbsSoundRecord soundRecord;
    public WindowRecorder(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
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
        setState();
        recordSong.addActionListener(new RecordingSongAction(this));
        stopSong.addActionListener(new StopRecordingSongAction(this));
        buttons_.add(recordSong);
        buttons_.add(stopSong);
        container_.add(group_);
        container_.add(buttons_);
        alignTopLeft(status);
        container_.add(status);
        setContentPane(container_);
        pack();
        setVisible(true);
        setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
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
        basicDispose();
    }

    @Override
    public String getApplicationName() {
        return "recorder";
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }

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
        stopSong.setEnabled(true);
        if (soundRecord.recordSongInFile(_file)) {
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
        soundRecord.stop();
        setState();
        status.setText("");
        pack();
    }
    public void setState(){
        recordSong.setEnabled(okRecord());
        stopSong.setEnabled(okStop());
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
        return soundRecord.isActive();
    }
}
