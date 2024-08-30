package applications.gui;

import code.sml.util.*;

public final class MessagesApplications {
    public static final String APP = "";
    public static final String MAIN_GAME = "main_game";
    public static final String MAIN_TITLE = "0";
    public static final String COORDS = "1";
    private MessagesApplications() {
    }
    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(APP, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(APP);
    }

    public static TranslationsAppli initAppliFilesTr(Translations _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getFiles().addEntry(APP, a_);
        return a_;
    }

    public static TranslationsAppli getAppliFilesTr(Translations _lgs) {
        return _lgs.getFiles().getVal(APP);
    }

    public static void tr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(MAIN_GAME,mesApp());
    }

    public static void sys(TranslationsAppli _lgs) {
        _lgs.sys(mes());
    }
    public static TranslationsFile mesApp(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(MAIN_TITLE,"Applications");
        return t_;
    }
    public static TranslationsFile mes(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(Translations.TEMP_FOLDER,"applications");
        t_.add(COORDS,"applications.coords");
        return t_;
    }
}
