package aiki.gui.components.editor;

import code.sml.util.*;

public final class MessagesPkEditor {
    public static final String PK_EDITOR = "pk_editor";
    public static final String GENDER_REP = "gender_rep";
    public static final String EVO = "evo";
    public static final String EFFECT = "effect";
    public static final String EXCHANGE_TYPE = "exc_type";
    public static final String MOVE_ITEM_TYPE = "move_it_type";
    public static final String SWITCH_POINT = "switch_pt";
    public static final String CONST_VALUES = "cst_values";
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
        appendMessagesEditorSelectExchangeType(_lgs,MessagesEditorSelect.enExchangeType());
        appendMessagesEditorSelectMoveItemType(_lgs,MessagesEditorSelect.enMoveItemType());
        appendMessagesEditorSelectPointViewChangementType(_lgs,MessagesEditorSelect.enPointViewChangementType());
        appendMessagesEditorSelectConstValuesType(_lgs,MessagesEditorSelect.enConstValuesType());
    }

    public static void frTr(TranslationsAppli _lgs) {
        appendMessagesEditorSelectGenderRep(_lgs,MessagesEditorSelect.frGenderRep());
        appendMessagesEditorSelectEvo(_lgs,MessagesEditorSelect.frEvo());
        appendMessagesEditorSelectEffect(_lgs,MessagesEditorSelect.frEff());
        appendMessagesEditorSelectExchangeType(_lgs,MessagesEditorSelect.frExchangeType());
        appendMessagesEditorSelectMoveItemType(_lgs,MessagesEditorSelect.frMoveItemType());
        appendMessagesEditorSelectPointViewChangementType(_lgs,MessagesEditorSelect.frPointViewChangementType());
        appendMessagesEditorSelectConstValuesType(_lgs,MessagesEditorSelect.frConstValuesType());
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

    public static void appendMessagesEditorSelectExchangeType(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(EXCHANGE_TYPE, _f);
    }
    public static void appendMessagesEditorSelectMoveItemType(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(MOVE_ITEM_TYPE, _f);
    }
    public static void appendMessagesEditorSelectPointViewChangementType(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(SWITCH_POINT, _f);
    }
    public static void appendMessagesEditorSelectConstValuesType(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(CONST_VALUES, _f);
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
    public static TranslationsFile getMessagesEditorSelectExchangeTypeTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(EXCHANGE_TYPE);
    }
    public static TranslationsFile getMessagesEditorSelectMoveItemTypeTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(MOVE_ITEM_TYPE);
    }
    public static TranslationsFile getMessagesEditorSelectPointViewChangementTypeTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(SWITCH_POINT);
    }
    public static TranslationsFile getMessagesEditorSelectConstValuesTypeTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(CONST_VALUES);
    }
}
