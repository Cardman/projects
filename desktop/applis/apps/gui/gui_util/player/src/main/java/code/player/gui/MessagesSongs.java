package code.player.gui;

import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.util.StringMap;

public final class MessagesSongs {
    public static final String SONGS_APP = "songs";
    public static final String PLAYER_FILE = "player";
    public static final String RECORDER_FILE = "recorder";
    public static final String LOC_REC = "recorder";
    public static final String APPS_MUSICPLAYER = "musicplayer";
    public static final String START = "Start";
    public static final String STOP = "Stop";

    private MessagesSongs() {
    }

    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(SONGS_APP, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(SONGS_APP);
    }

    public static TranslationsAppli initAppliFilesTr(Translations _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getFiles().addEntry(SONGS_APP, a_);
        return a_;
    }

    public static TranslationsAppli getAppliFilesTr(Translations _lgs) {
        return _lgs.getFiles().getVal(SONGS_APP);
    }


    public static StringMap<String> valPlayerMessages(TranslationsLg _lg) {
        return getAppliTr(_lg).getMapping().getVal(PLAYER_FILE).getMapping();
    }

    public static StringMap<String> valRecorderMessages(TranslationsLg _lg) {
        return getAppliTr(_lg).getMapping().getVal(RECORDER_FILE).getMapping();
    }

    public static TranslationsAppli updateEn(TranslationsAppli _a){
        _a.getMapping().addEntry(PLAYER_FILE,MessagesPlayer.en());
        _a.getMapping().addEntry(RECORDER_FILE,MessagesRecorder.en());
        return _a;
    }

    public static TranslationsAppli updateFr(TranslationsAppli _a){
        _a.getMapping().addEntry(PLAYER_FILE,MessagesPlayer.fr());
        _a.getMapping().addEntry(RECORDER_FILE,MessagesRecorder.fr());
        return _a;
    }

    public static void sys(TranslationsAppli _lgs) {
        _lgs.sys(mes());
    }
    public static TranslationsFile mes(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(Translations.TEMP_FOLDER,"playersongs");
        return t_;
    }
}
