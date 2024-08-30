package code.converterimages.gui;

import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.util.StringMap;

public final class MessagesConverter {
//    public static final String FOLDER = "rts_imgs";
    public static final String APPS_CONVERTER = "converter";
    public static final String TEMP_FOLDER = MessagesConverter.APPS_CONVERTER;
    public static final String FRAME = "0";
    public static final String OK = "0";
    public static final String READ = "1";
    public static final String CONVERT = "2";

    private MessagesConverter() {
    }

    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(APPS_CONVERTER, a_);
        return a_;
    }
    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(APPS_CONVERTER);
    }

    public static TranslationsAppli initAppliFilesTr(Translations _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getFiles().addEntry(APPS_CONVERTER, a_);
        return a_;
    }

    public static TranslationsAppli getAppliFilesTr(Translations _lgs) {
        return _lgs.getFiles().getVal(APPS_CONVERTER);
    }

    public static StringMap<String> valMessages(TranslationsLg _lg) {
        return getAppliTr(_lg).getMapping().getVal(FRAME).getMapping();
    }
    public static TranslationsAppli updateEn(TranslationsAppli _a){
        _a.getMapping().addEntry(FRAME,en());
        return _a;
    }
    public static TranslationsAppli updateFr(TranslationsAppli _a){
        _a.getMapping().addEntry(FRAME,fr());
        return _a;
    }
    public static TranslationsFile en(){
        TranslationsFile en_ = new TranslationsFile();
        en_.add(OK,"ok");
        en_.add(READ,"read");
        en_.add(CONVERT,"convert images");
        return en_;
    }
    public static TranslationsFile fr(){
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(OK,"ok");
        fr_.add(READ,"lire");
        fr_.add(CONVERT,"convertir des images");
        return fr_;
    }

    public static void sys(TranslationsAppli _lgs) {
        _lgs.sys(mes());
    }
    public static TranslationsFile mes(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(Translations.TEMP_FOLDER,TEMP_FOLDER);
        return t_;
    }
}
