package aiki.gui.components.editor;

import code.sml.util.*;

public final class MessagesPkEditor {
    public static final String PK_EDITOR = "pk_editor";
    public static final String GENDER_REP = "gender_rep";
    public static final String EXP_TYPE = "exp_type";
    public static final String EVO = "evo";
    public static final String EFFECT = "effect";
    public static final String STATUS = "status";
    public static final String EXCHANGE_TYPE = "exc_type";
    public static final String MOVE_ITEM_TYPE = "move_it_type";
    public static final String SWITCH_POINT = "switch_pt";
    public static final String CONST_VALUES = "cst_values";
    public static final String RESTRICT = "restrict";
    public static final String HEROS_SEX = "heros";
    public static final String PLACE = "place";
    public static final String TILE = "tile";
    public static final String GYM_TILE = "gym_tile";
    public static final String PC_SELL_TYPE = "pc_sell";
    public static final String PC_GERANT_TYPE = "pc_gerant";
    public static final String PC_TILE = "pc_tile";
    public static final String LEAGUE_TILE = "league_tile";
    public static final String DATA_MAP_GENE = "data_map_gene";
    public static final String DATA_MAP_LEV = "data_map_lev";
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
        appendMessagesEditorSelectExpType(_lgs,MessagesEditorSelect.enExpType());
        appendMessagesEditorSelectEvo(_lgs,MessagesEditorSelect.enEvo());
        appendMessagesEditorSelectEffect(_lgs,MessagesEditorSelect.enEff());
        appendMessagesEditorSelectStatus(_lgs,MessagesEditorSelect.enStatus());
        appendMessagesEditorSelectExchangeType(_lgs,MessagesEditorSelect.enExchangeType());
        appendMessagesEditorSelectMoveItemType(_lgs,MessagesEditorSelect.enMoveItemType());
        appendMessagesEditorSelectPointViewChangementType(_lgs,MessagesEditorSelect.enPointViewChangementType());
        appendMessagesEditorSelectConstValuesType(_lgs,MessagesEditorSelect.enConstValuesType());
        appendMessagesEditorSelectMoveChoiceRestrictionType(_lgs,MessagesEditorSelect.enMoveChoiceRestrictionType());
        appendMessagesEditorSelectHerosSex(_lgs,MessagesEditorSelect.enHerosSex());
        appendMessagesEditorSelectPlace(_lgs,MessagesEditorSelect.enPlace());
        appendMessagesEditorSelectTileKindWild(_lgs,MessagesEditorSelect.enTileKindWild());
        appendMessagesEditorSelectTileKindGym(_lgs,MessagesEditorSelect.enGymTileKindWild());
        appendMessagesEditorSelectTileSellType(_lgs,MessagesEditorSelect.enSellType());
        appendMessagesEditorSelectTileGerantType(_lgs,MessagesEditorSelect.enGeranceType());
        appendMessagesEditorSelectTileKindPc(_lgs,MessagesEditorSelect.enPcTileKindWild());
        appendMessagesEditorSelectTileKindLeague(_lgs,MessagesEditorSelect.enLeagueTile());
        appendMessagesEditorSelectDataMapGene(_lgs,MessagesEditorSelect.enDataMapGene());
        appendMessagesEditorSelectDataMapLev(_lgs,MessagesEditorSelect.enDataMapLev());
    }

    public static void frTr(TranslationsAppli _lgs) {
        appendMessagesEditorSelectGenderRep(_lgs,MessagesEditorSelect.frGenderRep());
        appendMessagesEditorSelectExpType(_lgs,MessagesEditorSelect.frExpType());
        appendMessagesEditorSelectEvo(_lgs,MessagesEditorSelect.frEvo());
        appendMessagesEditorSelectEffect(_lgs,MessagesEditorSelect.frEff());
        appendMessagesEditorSelectStatus(_lgs,MessagesEditorSelect.frStatus());
        appendMessagesEditorSelectExchangeType(_lgs,MessagesEditorSelect.frExchangeType());
        appendMessagesEditorSelectMoveItemType(_lgs,MessagesEditorSelect.frMoveItemType());
        appendMessagesEditorSelectPointViewChangementType(_lgs,MessagesEditorSelect.frPointViewChangementType());
        appendMessagesEditorSelectConstValuesType(_lgs,MessagesEditorSelect.frConstValuesType());
        appendMessagesEditorSelectMoveChoiceRestrictionType(_lgs,MessagesEditorSelect.frMoveChoiceRestrictionType());
        appendMessagesEditorSelectHerosSex(_lgs,MessagesEditorSelect.frHerosSex());
        appendMessagesEditorSelectPlace(_lgs,MessagesEditorSelect.frPlace());
        appendMessagesEditorSelectTileKindWild(_lgs,MessagesEditorSelect.frTileKindWild());
        appendMessagesEditorSelectTileKindGym(_lgs,MessagesEditorSelect.frGymTileKindWild());
        appendMessagesEditorSelectTileSellType(_lgs,MessagesEditorSelect.frSellType());
        appendMessagesEditorSelectTileGerantType(_lgs,MessagesEditorSelect.frGeranceType());
        appendMessagesEditorSelectTileKindPc(_lgs,MessagesEditorSelect.frPcTileKindWild());
        appendMessagesEditorSelectTileKindLeague(_lgs,MessagesEditorSelect.frLeagueTile());
        appendMessagesEditorSelectDataMapGene(_lgs,MessagesEditorSelect.frDataMapGene());
        appendMessagesEditorSelectDataMapLev(_lgs,MessagesEditorSelect.frDataMapLev());
    }

    public static void appendMessagesEditorSelectGenderRep(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(GENDER_REP, _f);
    }

    public static void appendMessagesEditorSelectExpType(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(EXP_TYPE, _f);
    }

    public static void appendMessagesEditorSelectEvo(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(EVO, _f);
    }

    public static void appendMessagesEditorSelectEffect(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(EFFECT, _f);
    }

    public static void appendMessagesEditorSelectStatus(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(STATUS, _f);
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
    public static void appendMessagesEditorSelectMoveChoiceRestrictionType(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(RESTRICT, _f);
    }
    public static void appendMessagesEditorSelectHerosSex(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(HEROS_SEX, _f);
    }
    public static void appendMessagesEditorSelectPlace(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PLACE, _f);
    }
    public static void appendMessagesEditorSelectTileKindWild(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(TILE, _f);
    }
    public static void appendMessagesEditorSelectTileKindGym(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(GYM_TILE, _f);
    }
    public static void appendMessagesEditorSelectTileSellType(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PC_SELL_TYPE, _f);
    }
    public static void appendMessagesEditorSelectTileGerantType(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PC_GERANT_TYPE, _f);
    }
    public static void appendMessagesEditorSelectTileKindPc(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PC_TILE, _f);
    }
    public static void appendMessagesEditorSelectTileKindLeague(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(LEAGUE_TILE, _f);
    }
    public static void appendMessagesEditorSelectDataMapGene(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(DATA_MAP_GENE, _f);
    }
    public static void appendMessagesEditorSelectDataMapLev(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(DATA_MAP_LEV, _f);
    }
    public static TranslationsFile getMessagesEditorSelectGenderRepTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(GENDER_REP);
    }
    public static TranslationsFile getMessagesEditorExpTypeTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(EXP_TYPE);
    }
    public static TranslationsFile getMessagesEditorSelectEvoTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(EVO);
    }
    public static TranslationsFile getMessagesEditorSelectEffectTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(EFFECT);
    }
    public static TranslationsFile getMessagesEditorSelectStatusTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(STATUS);
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
    public static TranslationsFile getMessagesEditorSelectMoveChoiceRestrictionTypeTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(RESTRICT);
    }
    public static TranslationsFile getMessagesEditorSelectHerosSexTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(HEROS_SEX);
    }
    public static TranslationsFile getMessagesEditorSelectPlaceTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PLACE);
    }
    public static TranslationsFile getMessagesEditorSelectTileKindWildTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(TILE);
    }
    public static TranslationsFile getMessagesEditorSelectTileKindGymTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(GYM_TILE);
    }
    public static TranslationsFile getMessagesEditorSelectTileKindSellTypeTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PC_SELL_TYPE);
    }
    public static TranslationsFile getMessagesEditorSelectTileKindGerantTypeTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PC_GERANT_TYPE);
    }
    public static TranslationsFile getMessagesEditorSelectTileKindPcTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PC_TILE);
    }
    public static TranslationsFile getMessagesEditorSelectTileKindLeagueTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(LEAGUE_TILE);
    }
    public static TranslationsFile getMessagesEditorSelectDataMapGeneTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(DATA_MAP_GENE);
    }
    public static TranslationsFile getMessagesEditorSelectDataMapLevTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(DATA_MAP_LEV);
    }
}
