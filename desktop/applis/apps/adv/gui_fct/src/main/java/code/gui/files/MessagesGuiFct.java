package code.gui.files;

import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsLg;

public final class MessagesGuiFct {
    public static final String GUI = "gui";
    public static final String CONFIRM = "confirm";
    public static final String FILE_DIAL = "file_dialog";
    public static final String FILE_OPEN_DIAL = "file_open";
    public static final String FOLDER_OPEN_DIAL = "folder_open";
    public static final String FILE_SAVE_DIAL = "file_save";
    public static final String FILE_TAB = "file_table";
    public static final String DATE_FORMAT = "dd-MM-yyyy HH-mm-ss";

    private MessagesGuiFct() {
    }

    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(GUI, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(GUI);
    }

    public static void enTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(FILE_DIAL,MessagesFileDialog.en());
        _lgs.getMapping().addEntry(FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.en());
        _lgs.getMapping().addEntry(FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        _lgs.getMapping().addEntry(FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        _lgs.getMapping().addEntry(FILE_TAB,MessagesFileTable.en());
        _lgs.getMapping().addEntry(CONFIRM,MessagesConfirmDialog.en());
    }

    public static void frTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(FILE_DIAL,MessagesFileDialog.fr());
        _lgs.getMapping().addEntry(FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.fr());
        _lgs.getMapping().addEntry(FILE_OPEN_DIAL,MessagesFileOpenDialog.fr());
        _lgs.getMapping().addEntry(FILE_SAVE_DIAL,MessagesFileSaveDialog.fr());
        _lgs.getMapping().addEntry(FILE_TAB,MessagesFileTable.fr());
        _lgs.getMapping().addEntry(CONFIRM,MessagesConfirmDialog.fr());
    }

    public static String date(String _separatorDate) {
        return "yyyy" + _separatorDate + "MM" + _separatorDate + "dd";
    }

    public static String time(String _separator) {
        return "HH" + _separator + "mm" + _separator + "ss";
    }
}
