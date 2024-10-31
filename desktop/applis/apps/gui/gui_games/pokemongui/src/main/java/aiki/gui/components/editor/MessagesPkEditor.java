package aiki.gui.components.editor;

import code.sml.util.*;

public final class MessagesPkEditor {
    public static final String PK_EDITOR = "pk_editor";
    public static final String GENDER_REP = "gender_rep";
    private MessagesPkEditor() {
    }

    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(PK_EDITOR, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(PK_EDITOR);
    }

    public static void enTr(TranslationsAppli _lgs) {
        appendMessagesEditorSelectContent(_lgs,MessagesEditorSelect.en());
    }

    public static void frTr(TranslationsAppli _lgs) {
        appendMessagesEditorSelectContent(_lgs,MessagesEditorSelect.fr());
    }

    public static void appendMessagesEditorSelectContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(GENDER_REP, _f);
    }
    public static TranslationsFile getMessagesEditorSelectContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(GENDER_REP);
    }
}
