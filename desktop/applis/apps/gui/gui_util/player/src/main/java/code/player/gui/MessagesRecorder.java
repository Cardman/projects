package code.player.gui;

import code.sml.util.TranslationsFile;

public final class MessagesRecorder {
    public static final String TITLE = "0";
    public static final String RATE = "1";
    public static final String SIZE = "2";
    public static final String CHANNELS = "3";
    public static final String SIGNED = "4";
    public static final String BIG_ENDIAN = "5";
    public static final String FIVE_SAVE = "6";
    public static final String RECORD = "7";
    public static final String STOP = "8";
    public static final String PLAY = "9";
    public static final String BAD_NAME = "10";
    public static final String BAD_RECORD = "11";
    public static final String RECORDING = "12";
    private MessagesRecorder() {
    }
    public static TranslationsFile en(){
        TranslationsFile en_ = new TranslationsFile();
        en_.add(TITLE,"Recorder");
        en_.add(RATE,"rate");
        en_.add(SIZE,"size");
        en_.add(CHANNELS,"channels");
        en_.add(SIGNED,"signed");
        en_.add(BIG_ENDIAN,"big endian");
        en_.add(FIVE_SAVE,"file save");
        en_.add(RECORD,"record");
        en_.add(STOP,"stop");
        en_.add(PLAY,"play");
        en_.add(BAD_NAME,"bad file name");
        en_.add(BAD_RECORD,"error while recording");
        en_.add(RECORDING,"recorder {0} ms");
        return en_;
    }
    public static TranslationsFile fr(){
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(TITLE,"Enregistreur");
        fr_.add(RATE,"taux");
        fr_.add(SIZE,"taille");
        fr_.add(CHANNELS,"flux");
        fr_.add(SIGNED,"signé");
        fr_.add(BIG_ENDIAN,"grand bout");
        fr_.add(FIVE_SAVE,"fichier de sauvegarde");
        fr_.add(RECORD,"enregistrer");
        fr_.add(STOP,"arrêter");
        fr_.add(PLAY,"écouter");
        fr_.add(BAD_NAME,"mauvais nom de fichier");
        fr_.add(BAD_RECORD,"erreur lors de l'enregistrement");
        fr_.add(RECORDING,"enregistrement {0} ms");
        return fr_;
    }
}
