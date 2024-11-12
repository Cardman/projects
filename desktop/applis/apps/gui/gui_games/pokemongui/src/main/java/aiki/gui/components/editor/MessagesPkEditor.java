package aiki.gui.components.editor;

import code.sml.util.*;

public final class MessagesPkEditor {
    public static final String PK_EDITOR = "pk_editor";
    public static final String GENDER_REP = "gender_rep";
    public static final String EVO = "evo";
    public static final String EFFECT = "effect";
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
        appendMessagesEditorSelectGenderRep(_lgs,MessagesEditorSelect.enGenderRep());
        appendMessagesEditorSelectEvo(_lgs,MessagesEditorSelect.enEvo());
        appendMessagesEditorSelectEffect(_lgs,MessagesEditorSelect.enEff());
    }

    public static void frTr(TranslationsAppli _lgs) {
        appendMessagesEditorSelectGenderRep(_lgs,MessagesEditorSelect.frGenderRep());
        appendMessagesEditorSelectEvo(_lgs,MessagesEditorSelect.frEvo());
        appendMessagesEditorSelectEffect(_lgs,MessagesEditorSelect.frEff());
    }

    public static void appendMessagesEditorSelectGenderRep(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(GENDER_REP, _f);
    }

    public static void appendMessagesEditorSelectEvo(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(EVO, _f);
    }

    public static void appendMessagesEditorSelectEffect(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(EFFECT, _f);
    }
    public static TranslationsFile getMessagesEditorSelectGenderRepTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(GENDER_REP);
    }
    public static TranslationsFile getMessagesEditorSelectEvoTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(EVO);
    }
    public static TranslationsFile getMessagesEditorSelectEffectTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(EFFECT);
    }
}
