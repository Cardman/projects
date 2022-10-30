package aiki.beans.moves;

import aiki.beans.CommonBean;
import aiki.beans.WithFilterBean;
import aiki.beans.items.ItemsBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.util.LevelMove;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class MoveBean extends CommonBean {

    private static final String PAGE_DAMAGE = "web/html/moves/effects/effdamage.html";
    private static final String PAGE_DAMAGERATE = "web/html/moves/effects/effdamagerate.html";
    private static final String PAGE_STATIS = "web/html/moves/effects/effstatis.html";
    private static final String PAGE_STATUS = "web/html/moves/effects/effstatus.html";
    private static final String PAGE_TEAM = "web/html/moves/effects/effteam.html";
    private static final String PAGE_GLOBAL = "web/html/moves/effects/effglobal.html";
    private static final String PAGE_ENDROUND = "web/html/moves/effects/effendround.html";
    private static final String PAGE_TEAMWHILESENDINGFOE = "web/html/moves/effects/effteamwhilesendingfoe.html";
    private static final String PAGE_COPYMOVE = "web/html/moves/effects/effcopymove.html";
    private static final String PAGE_FULLHPRATE = "web/html/moves/effects/efffullhprate.html";
    private static final String PAGE_INVOKE = "web/html/moves/effects/effinvoke.html";
    private static final String PAGE_SWITCHMOVETYPES = "web/html/moves/effects/effswitchmovetypes.html";
    private static final String PAGE_COUNTERATTACK = "web/html/moves/effects/effcounterattack.html";
    private static final String PAGE_PROTECTION = "web/html/moves/effects/effprotection.html";
    private static final String PAGE_ACCURACY = "web/html/moves/effects/effaccuracy.html";
    private static final String PAGE_COPYFIGHTER = "web/html/moves/effects/effcopyfighter.html";
    private static final String PAGE_PROTECTFROMTYPES = "web/html/moves/effects/effprotectfromtypes.html";
    private static final String PAGE_UNPROTECTFROMTYPES = "web/html/moves/effects/effunprotectfromtypes.html";
    private static final String PAGE_ALLY = "web/html/moves/effects/effally.html";
    private static final String PAGE_BATONPASS = "web/html/moves/effects/effbatonpass.html";
    private static final String PAGE_CLONE = "web/html/moves/effects/effclone.html";
    private static final String PAGE_COMMONSTATISTICS = "web/html/moves/effects/effcommonstatistics.html";
    private static final String PAGE_ORDER = "web/html/moves/effects/efforder.html";
    private static final String PAGE_RESTRICTION = "web/html/moves/effects/effrestriction.html";
    private static final String PAGE_SWITCHABILITIES = "web/html/moves/effects/effswitchabilities.html";
    private static final String PAGE_SWITCHITEMS = "web/html/moves/effects/effswitchitems.html";
    private static final String PAGE_SWITCHTYPES = "web/html/moves/effects/effswitchtypes.html";
    private static final String PAGE_SWITCHPOINTVIEW = "web/html/moves/effects/effswitchpointview.html";
    private static final String PAGE_REMAINEDHPRATE = "web/html/moves/effects/effremainedhprate.html";
    private static final String PAGE_MULTUSEDMOVEPOWER = "web/html/moves/effects/effmultusedmovepower.html";
    private static final String PAGE_MULTSUFFEREDMOVEPOWER = "web/html/moves/effects/effmultsufferedmovepower.html";
    private static final String PAGE_SWITCHPOSITION = "web/html/moves/effects/effswitchposition.html";
    private static final String PAGE_VARPP = "web/html/moves/effects/effvarpp.html";
    private static final String PAGE_WINMONEY = "web/html/moves/effects/effwinmoney.html";
    private String name;
    private String displayName;
    private short pp;
    private boolean hasDefaultTypes;
    private StringList types;
    private String category;
    private StringList boostedTypes;
    private byte priority;
    private String accuracy;
    private Ints effects;
    private short nbPrepaRound;
    private boolean disappearBeforeUse;
    private DictionaryComparator<LgInt,Rate> repeatRoundLaw;
    private short rankIncrementNbRound;
    private boolean rechargeRound;
    private boolean constUserChoice;
    private boolean secEffectIfNoDamage;
    private DictionaryComparator<String, Ints> secEffectsByItem;
    private boolean ignVarAccurUserNeg;
    private boolean ignVarEvasTargetPos;
    private StringList achieveDisappearedPkUsingMove;

    private SwitchType switchType;
    private DictionaryComparator<String,String> typesByOwnedItems;
    private DictionaryComparator<String,String> typesByWeathers;
    private TargetChoice targetChoice;
    private StringList deletedStatus;
    private StringList requiredStatus;
    private StringList abilities;
    private StringList items;
    private NatStringTreeMap<String> mapVarsAccuracy;
    private boolean cannotKo;
    private StringList affectedByMoves;
    private ShortTreeMap<StringList> movesLevelLearntByPokemon;
    private StringList movesTmLearntByPokemon;
    private StringList movesHmLearntByPokemon;
    private StringList movesMtLearntByPokemon;

    @Override
    public void beforeDisplaying() {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        name = name_;
        displayName = translatedMoves_.getVal(name_);
        MoveData moveData_ = data_.getMove(name_);
        StringMap<String> translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        category = translatedCategories_.getVal(moveData_.getCategory());
        pp = moveData_.getPp();
        priority = moveData_.getPriority();
//        CustList<ItemTypeLine> typesByOwnedItem_;
//        typesByOwnedItem_ = new CustList<>();
        withDef(moveData_);
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        accuracy = data_.getFormula(moveData_.getAccuracy(),getLanguage());
//        accuracy = StringList.replace(accuracy, loc_);
//        accuracy = accuracy.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        accuracy = accuracy.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        mapVarsAccuracy = mapVarsAccuracy(moveData_);
        ignVarAccurUserNeg = moveData_.getIgnVarAccurUserNeg();
        ignVarEvasTargetPos = moveData_.getIgnVarEvasTargetPos();
        nbPrepaRound = moveData_.getNbPrepaRound();
        disappearBeforeUse = moveData_.getDisappearBeforeUse();
        targetChoice = moveData_.getTargetChoice();
        achieveDisappearedPkUsingMove = achieveDisappearedPkUsingMove(moveData_);
        abilities = abilities();
        items = items();
        if (moveData_ instanceof DamagingMoveData) {
            cannotKo = ((DamagingMoveData)moveData_).getCannotKo();
        }
        affectedByMoves = affectedByMoves(moveData_);
        Ints effects_ = new Ints();
        int nbEffects_ = moveData_.nbEffets();
        for (int i = IndexConstants.FIRST_INDEX; i < nbEffects_; i++) {
            effects_.add(i);
        }
        effects = effects_;
        DictionaryComparator<LgInt, Rate> repeatRoundLaw_;
        repeatRoundLaw_ = DictionaryComparatorUtil.buildIntRate();
        for (Rate r: moveData_.getRepeatRoundLaw().events()) {
            repeatRoundLaw_.put(r.intPart(), moveData_.getRepeatRoundLaw().normalizedRate(r));
        }
        repeatRoundLaw = repeatRoundLaw_;
        rankIncrementNbRound = moveData_.getRankIncrementNbRound();
        rechargeRound = moveData_.getRechargeRound();
        constUserChoice = moveData_.getConstUserChoice();
        switchType = moveData_.getSwitchType();
        secEffectIfNoDamage = moveData_.getSecEffectIfNoDamage();
        StringList deletedStatus_;
        deletedStatus_ = new StringList();
        for (String s: moveData_.getDeletedStatus()) {
            deletedStatus_.add(s);
        }
        deletedStatus_.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        deletedStatus = deletedStatus_;
        StringList requiredStatus_;
        requiredStatus_ = new StringList();
        for (String s: moveData_.getRequiredStatus()) {
            requiredStatus_.add(s);
        }
        requiredStatus_.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        requiredStatus = requiredStatus_;
        DictionaryComparator<String, Ints> secEffectsByItem_;
        secEffectsByItem_ = DictionaryComparatorUtil.buildItemsLs(data_,getLanguage());
        for (EntryCust<String, Ints> s: moveData_.getSecEffectsByItem().entryList()) {
            secEffectsByItem_.put(s.getKey(), getValidEffects(moveData_, s.getValue()));
        }
        secEffectsByItem = secEffectsByItem_;
        movesLevelLearntByPokemon = movesLevelLearntByPokemon();
        movesTmLearntByPokemon = movesTmLearntByPokemon();
        StringList movesHmLearntByPokemon_;
        movesHmLearntByPokemon_ = new StringList();
        Shorts hms_ = data_.getHmByMove(name_);
//        if (Map.<Short>hasString(data_.getHm(), name_))
        if (!hms_.isEmpty()) {
//            short tm_ = data_.getHm().getKeys(name_).first();
//            short tm_ = data_.getHmByMove(name_).first();
            short tm_ = hms_.first();
            for (String p: data_.getPokedex().getKeys()) {
                PokemonData pkData_ = data_.getPokemon(p);
                if (pkData_.getHiddenMoves().containsObj(tm_)) {
                    movesHmLearntByPokemon_.add(p);
                }
            }
            movesHmLearntByPokemon_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        }
        movesHmLearntByPokemon = movesHmLearntByPokemon_;
        StringList movesMtLearntByPokemon_;
        movesMtLearntByPokemon_ = new StringList();
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pkData_ = data_.getPokemon(p);
            if (StringUtil.contains(pkData_.getMoveTutors(), name_)) {
                movesMtLearntByPokemon_.add(p);
            }
        }
        movesMtLearntByPokemon_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        movesMtLearntByPokemon = movesMtLearntByPokemon_;
    }

    static Ints getValidEffects(MoveData _move, Ints _ls) {
        Ints no_ = new Ints();
        CustList<Effect> effects_ = _move.getEffects();
        int s_ = _ls.size();
        for (int i =0; i < s_; i++) {
            int v_ = _ls.get(i);
            if (effects_.isValidIndex(v_)) {
                no_.add(v_);
            }
        }
        return no_;
    }

    private StringList movesTmLearntByPokemon() {
        DataBase data_ = getDataBase();
        StringList movesTmLearntByPokemon_;
        movesTmLearntByPokemon_ = new StringList();
        Shorts tms_ = data_.getTmByMove(name);
//        if (Map.<Short>hasString(data_.getTm(), name_))
        if (!tms_.isEmpty()) {
//            short tm_ = data_.getTm().getKeys(name_).first();
//            short tm_ = data_.getTmByMove(name_).first();
            short tm_ = tms_.first();
            for (String p: data_.getPokedex().getKeys()) {
                PokemonData pkData_ = data_.getPokemon(p);
                if (pkData_.getTechnicalMoves().containsObj(tm_)) {
                    movesTmLearntByPokemon_.add(p);
                }
            }
            movesTmLearntByPokemon_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        }
        return movesTmLearntByPokemon_;
    }

    private ShortTreeMap<StringList> movesLevelLearntByPokemon() {
        DataBase data_ = getDataBase();
        ShortTreeMap<StringList> movesLevelLearntByPokemon_;
        movesLevelLearntByPokemon_ = new ShortTreeMap<StringList>();
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pkData_ = data_.getPokemon(p);
            for (LevelMove l: pkData_.getLevMoves()) {
                if (!StringUtil.quickEq(l.getMove(), name)) {
                    continue;
                }
                if (!movesLevelLearntByPokemon_.contains(l.getLevel())) {
                    movesLevelLearntByPokemon_.put(l.getLevel(), new StringList(p));
                } else {
                    movesLevelLearntByPokemon_.getVal(l.getLevel()).add(p);
                }
            }
        }
        for (StringList v: movesLevelLearntByPokemon_.values()) {
            v.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        }
        return movesLevelLearntByPokemon_;
    }

    private StringList affectedByMoves(MoveData _moveData) {
        DataBase data_ = getDataBase();
        StringList affectedByMoves_ = new StringList();
        if (_moveData.getStoppableMoveMulti()) {
            for (String move_ : data_.getMovesProtAgainstMultiTarget()) {
                affectedByMoves_.add(move_);
            }
        }
        if (_moveData.getStoppableMovePrio() && priority > 0) {
            for (String move_ : data_.getMovesProtAgainstPrio()) {
                affectedByMoves_.add(move_);
            }
        }
        if (_moveData.getStoppableMoveSolo()) {
            for (String move_ : data_.getMovesProtSingleTarget()) {
                affectedByMoves_.add(move_);
            }
        }
        if (_moveData instanceof DamagingMoveData) {
            damMove((DamagingMoveData) _moveData, affectedByMoves_);
        }
        if (_moveData instanceof StatusMoveData) {
            statMove((StatusMoveData) _moveData, affectedByMoves_);
        }
        affectedByMoves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        affectedByMoves_.removeDuplicates();
        return affectedByMoves_;
    }

    private void statMove(StatusMoveData _moveData, StringList _affectedByMoves) {
        DataBase data_ = getDataBase();
        for (String move_ : data_.getMovesProtAgainstStatusMoves()) {
            _affectedByMoves.add(move_);
        }
        if (_moveData.getThievableMove()) {
            for (String move_ : getMovesThieve()) {
                _affectedByMoves.add(move_);
            }
        }
        if (_moveData.getCounterableMove()) {
            for (String move_ : getMovesCounter()) {
                _affectedByMoves.add(move_);
            }
        }
    }

    private void damMove(DamagingMoveData _moveData, StringList _affectedByMoves) {
        DataBase data_ = getDataBase();
        for (String move_ : data_.getMovesProtAgainstDamageMoves()) {
            _affectedByMoves.add(move_);
        }
        if (_moveData.getStoppableMoveKoSingle()) {
            for (String move_ : data_.getMovesProtSingleTargetAgainstKo()) {
                _affectedByMoves.add(move_);
            }
        }
    }

    private StringList items() {
        DataBase data_ = getDataBase();
        StringList items_ = new StringList();
        for (String a: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(a);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle itBattle_ = (ItemForBattle) it_;
            if (StringUtil.contains(itBattle_.getImmuMoves(), name)) {
                items_.add(a);
            }
        }
        items_.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        return items_;
    }

    private StringList abilities() {
        DataBase data_ = getDataBase();
        StringList abilities_ = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (StringUtil.contains(ab_.getImmuMove(), name)) {
                abilities_.add(a);
            }
        }
        abilities_.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        return abilities_;
    }

    private StringList achieveDisappearedPkUsingMove(MoveData _moveData) {
        DataBase data_ = getDataBase();
        StringList achieveDisappearedPkUsingMove_ = new StringList();
        for (String m: _moveData.getAchieveDisappearedPkUsingMove()) {
            achieveDisappearedPkUsingMove_.add(m);
        }
        achieveDisappearedPkUsingMove_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return achieveDisappearedPkUsingMove_;
    }

    private NatStringTreeMap<String> mapVarsAccuracy(MoveData _moveData) {
        DataBase data_ = getDataBase();
        NatStringTreeMap<String> mapVars_ = data_.getDescriptions(_moveData.getAccuracy(),getLanguage());
        NatStringTreeMap<String> mapVarsAccuracy_ = new NatStringTreeMap<String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsAccuracy_.put(k, mapVars_.getVal(k));
        }
        return mapVarsAccuracy_;
    }

    private void withDef(MoveData _moveData) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        DictionaryComparator<String,String> typesByOwnedItems_;
        typesByOwnedItems_ =DictionaryComparatorUtil.buildItemsStr(data_,getLanguage());
        boolean hasDefault_ = false;
        for (String k: _moveData.getTypesByOwnedItem().getKeys()) {
            //ItemTypeLine line_ = new ItemTypeLine();
            if (k.isEmpty()) {
                if (!_moveData.getTypesByWeather().isEmpty()) {
                    continue;
                }
                hasDefault_ = true;
            }
//            if (translatedItems_.contains(k)) {
//                line_.setItem(translatedItems_.getVal(k));
//            } else {
//                if (!moveData_.getTypesByWeather().isEmpty()) {
//                    continue;
//                }
//                line_.setItem(messages_.getVal(OTHER_ITEM));
//                hasDefault_ = true;
//            }
            String type_ = _moveData.getTypesByOwnedItem().getVal(k);
            //line_.setType(translatedTypes_.getVal(type_));
            typesByOwnedItems_.put(k, translatedTypes_.getVal(type_));
            //typesByOwnedItem_.add(line_);
        }
        //typesByOwnedItem = typesByOwnedItem_;
        typesByOwnedItems = typesByOwnedItems_;

//        CustList<WeatherTypeLine> typesByWeather_;
//        typesByWeather_ = new CustList<>();
        DictionaryComparator<String,String> typesByWeathers_;
        typesByWeathers_ = DictionaryComparatorUtil.buildMovesStr(data_,getLanguage());
        for (String k: _moveData.getTypesByWeather().getKeys()) {
            //WeatherTypeLine line_ = new WeatherTypeLine();
//            if (translatedMoves_.contains(k)) {
//                line_.setWeather(translatedMoves_.getVal(k));
//            } else {
//                line_.setWeather(messages_.getVal(OTHER_WEATHER));
//                hasDefault_ = true;
//            }
            if (k.isEmpty()) {
                //line_.setWeather(translatedMoves_.getVal(k));
                hasDefault_ = true;
            }
//            else {
//                //line_.setWeather(messages_.getVal(OTHER_WEATHER));
//                //hasDefault_ = true;
//            }
            String type_ = _moveData.getTypesByWeather().getVal(k);
            //line_.setType(translatedTypes_.getVal(type_));
            //typesByWeather_.add(line_);
            typesByWeathers_.put(k, translatedTypes_.getVal(type_));
        }
        //typesByWeather = typesByWeather_;
        typesByWeathers = typesByWeathers_;
        if (hasDefault_) {
            types = new StringList();
        } else {
            StringList types_ = new StringList();
            for (String t: _moveData.getTypes()) {
                types_.add(translatedTypes_.getVal(t));
            }
            types_.sort();
            types = types_;
        }
        StringList boostedTypes_ = new StringList();
        for (String t: _moveData.getBoostedTypes()) {
            boostedTypes_.add(translatedTypes_.getVal(t));
        }
        boostedTypes_.sort();
        boostedTypes = boostedTypes_;
        hasDefaultTypes = hasDefault_;
    }

    public String clickMoves() {
        getForms().safeMoves(CST_MOVES_SET);
        getForms().safeMoves(CST_LEARNT_MOVES);
        return CST_MOVES;
    }

    private StringList getMovesThieve() {
        return movesSwitchPointView(PointViewChangementType.THIEF_BONUSES);
    }

    private StringList getMovesCounter() {
        return movesSwitchPointView(PointViewChangementType.MIRROR_AGAINST_THROWER);
    }

    private StringList movesSwitchPointView(PointViewChangementType _pt) {
        DataBase data_ = getDataBase();
        StringList moves_ = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData fAttCible_ = data_.getMove(m);
            int nbEffetsCible_=fAttCible_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffetsCible_; i++){
                Effect effetLoc_=fAttCible_.getEffet(i);
                if(!(effetLoc_ instanceof EffectSwitchPointView)){
                    continue;
                }
                EffectSwitchPointView effetChgtPointVueAttLoc_=(EffectSwitchPointView)effetLoc_;
                if (effetChgtPointVueAttLoc_.getPointViewChangement() == _pt) {
                    moves_.add(m);
                }
            }
        }
        return moves_;
    }
    public boolean typesDependOnWeatherAndItem() {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return !moveData_.getTypesByOwnedItem().isEmpty() && !moveData_.getTypesByWeather().isEmpty();
    }
    public boolean typesDependOnlyOnItem() {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return !moveData_.getTypesByOwnedItem().isEmpty() && moveData_.getTypesByWeather().isEmpty();
    }
    public boolean typesDependOnlyOnWeather() {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return moveData_.getTypesByOwnedItem().isEmpty() && !moveData_.getTypesByWeather().isEmpty();
    }
    public boolean isDamagingMove() {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return moveData_ instanceof DamagingMoveData;
    }
    public boolean isDamagingDirectMove() {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return WithFilterBean.direct(moveData_);
    }
    public boolean isZeroPriority() {
        return priority == 0;
    }
    public boolean isConstAccuracy() {
        return Rate.isValid(accuracy);
    }
    public boolean isZeroPrepaRound() {
        return nbPrepaRound == 0;
    }
    public boolean isBeforePrimaryEffect(int _long) {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return _long < moveData_.indexOfPrimaryEffect();
    }
    public boolean isPrimaryEffect(int _long) {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return _long == moveData_.indexOfPrimaryEffect();
    }
    public boolean isAfterPrimaryEffect(int _long) {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return _long > moveData_.indexOfPrimaryEffect();
    }
    public boolean isEndRoundEffect(int _long) {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return moveData_.getEffet(_long) instanceof EffectEndRound;
    }

    public boolean effPrimOrBeforeNotEndRound(int _index) {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        int indPr_ = moveData_.indexOfPrimaryEffect();
        return _index <= indPr_ && !(moveData_.getEffet(_index) instanceof EffectEndRound);
    }

    public boolean effSecNotEndRound(int _index) {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        int indPr_ = moveData_.indexOfPrimaryEffect();
        return _index > indPr_ && !(moveData_.getEffet(_index) instanceof EffectEndRound);
    }

    public boolean isRepeatedRound() {
        return !repeatRoundLaw.isEmpty();
    }
    public boolean switchAfterUsingMove() {
        return switchType == SwitchType.LANCEUR;
    }
    public String getTrAchieveDisappearedPkUsingMove(int _index) {
        String move_ = achieveDisappearedPkUsingMove.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
    public String clickRequiredStatus(int _index) {
        String key_ = getRequiredStatusKey(_index);
        getForms().put(CST_STATUS, key_);
        return CST_STATUS;
    }
    public String getRequiredStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String key_ = getRequiredStatusKey(_index);
        return translatedStatus_.getVal(key_);
    }

    private String getRequiredStatusKey(int _index) {
        return requiredStatus.get(_index);
    }
    public String clickDeletedStatus(int _index) {
        String key_ = getDeletedStatusKey(_index);
        getForms().put(CST_STATUS, key_);
        return CST_STATUS;
    }
    public String getDeletedStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String key_ = getDeletedStatusKey(_index);
        return translatedStatus_.getVal(key_);
    }

    private String getDeletedStatusKey(int _index) {
        return deletedStatus.get(_index);
    }
    public boolean isItem(int _index) {
        return !typesByOwnedItems.getKey(_index).isEmpty();
    }
    public String clickTypesByOwnedItems(int _index) {
        String item_ = typesByOwnedItems.getKey(_index);
        DataBase data_ = getDataBase();
        getForms().put(CST_ITEM, item_);
        Item it_ = data_.getItem(item_);
        return ItemsBean.switchItem(it_);
//        if (it_ instanceof Ball) {
//            return CST_BALL;
//        }
//        if (it_ instanceof Berry) {
//            return CST_BERRY;
//        }
//        if (it_ instanceof Boost) {
//            return CST_BOOST;
//        }
//        if (it_ instanceof EvolvingItem) {
//            return CST_EVOLVINGITEM;
//        }
//        if (it_ instanceof EvolvingStone) {
//            return CST_EVOLVINGSTONE;
//        }
//        if (it_ instanceof Fossil) {
//            return CST_FOSSIL;
//        }
//        if (it_ instanceof HealingHpStatus) {
//            return CST_HEALINGHPSTATUS;
//        }
//        if (it_ instanceof HealingStatus) {
//            return CST_HEALINGSTATUS;
//        }
//        if (it_ instanceof HealingHp) {
//            return CST_HEALINGHP;
//        }
//        if (it_ instanceof HealingPp) {
//            return CST_HEALINGPP;
//        }
//        if (it_ instanceof HealingItem) {
//            return CST_HEALINGITEM;
//        }
//        if (it_ instanceof ItemForBattle) {
//            return CST_ITEMFORBATTLE;
//        }
//        if (it_ instanceof Repel) {
//            return CST_REPEL;
//        }
//        if (it_ instanceof SellingItem) {
//            return CST_SELLINGITEM;
//        }
//        return CST_ITEM;
    }
    public String getTrTypesByOwnedItems(int _index) {
        String item_ = typesByOwnedItems.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(item_);
    }
    public boolean isWeather(int _index) {
        return !typesByWeathers.getKey(_index).isEmpty();
    }
    public String clickTypesByWeathers(int _index) {
        String item_ = typesByWeathers.getKey(_index);
        getForms().put(CST_MOVE, item_);
        return CST_MOVE;
    }
    public String getTrTypesByWeathers(int _index) {
        String item_ = typesByWeathers.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(item_);
    }
    public String clickItemSecEffect(int _index) {
        String item_ = getItemSecEffect(_index);
        DataBase data_ = getDataBase();
        getForms().put(CST_ITEM, item_);
        Item it_ = data_.getItem(item_);
        return ItemsBean.switchItem(it_);
//        if (it_ instanceof Ball) {
//            return CST_BALL;
//        }
//        if (it_ instanceof Berry) {
//            return CST_BERRY;
//        }
//        if (it_ instanceof Boost) {
//            return CST_BOOST;
//        }
//        if (it_ instanceof EvolvingItem) {
//            return CST_EVOLVINGITEM;
//        }
//        if (it_ instanceof EvolvingStone) {
//            return CST_EVOLVINGSTONE;
//        }
//        if (it_ instanceof Fossil) {
//            return CST_FOSSIL;
//        }
//        if (it_ instanceof HealingHpStatus) {
//            return CST_HEALINGHPSTATUS;
//        }
//        if (it_ instanceof HealingStatus) {
//            return CST_HEALINGSTATUS;
//        }
//        if (it_ instanceof HealingHp) {
//            return CST_HEALINGHP;
//        }
//        if (it_ instanceof HealingPp) {
//            return CST_HEALINGPP;
//        }
//        if (it_ instanceof HealingItem) {
//            return CST_HEALINGITEM;
//        }
//        if (it_ instanceof ItemForBattle) {
//            return CST_ITEMFORBATTLE;
//        }
//        if (it_ instanceof Repel) {
//            return CST_REPEL;
//        }
//        if (it_ instanceof SellingItem) {
//            return CST_SELLINGITEM;
//        }
//        return CST_ITEM;
    }
    public String translateItemSecEffect(int _index) {
        String it_ = getItemSecEffect(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(it_);
    }

    private String getItemSecEffect(int _index) {
        return secEffectsByItem.getKey(_index);
    }
    public String clickAbility(int _index) {
        String key_ = abilities.get(_index);
        getForms().put(CST_ABILITY, key_);
        return CST_ABILITY;
    }
    public String getTrAbility(int _index) {
        String ab_ = abilities.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(ab_);
    }
    public String clickItem(int _index) {
        String key_ = items.get(_index);
        DataBase data_ = getDataBase();
        getForms().put(CST_ITEM, key_);
        Item it_ = data_.getItem(key_);
        return ItemsBean.switchItem(it_);
//        if (it_ instanceof Ball) {
//            return CST_BALL;
//        }
//        if (it_ instanceof Berry) {
//            return CST_BERRY;
//        }
//        if (it_ instanceof Boost) {
//            return CST_BOOST;
//        }
//        if (it_ instanceof EvolvingItem) {
//            return CST_EVOLVINGITEM;
//        }
//        if (it_ instanceof EvolvingStone) {
//            return CST_EVOLVINGSTONE;
//        }
//        if (it_ instanceof Fossil) {
//            return CST_FOSSIL;
//        }
//        if (it_ instanceof HealingHpStatus) {
//            return CST_HEALINGHPSTATUS;
//        }
//        if (it_ instanceof HealingStatus) {
//            return CST_HEALINGSTATUS;
//        }
//        if (it_ instanceof HealingHp) {
//            return CST_HEALINGHP;
//        }
//        if (it_ instanceof HealingPp) {
//            return CST_HEALINGPP;
//        }
//        if (it_ instanceof HealingItem) {
//            return CST_HEALINGITEM;
//        }
//        if (it_ instanceof ItemForBattle) {
//            return CST_ITEMFORBATTLE;
//        }
//        if (it_ instanceof Repel) {
//            return CST_REPEL;
//        }
//        if (it_ instanceof SellingItem) {
//            return CST_SELLINGITEM;
//        }
//        return CST_ITEM;
    }
    public String getTrItem(int _index) {
        String ab_ = items.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedAbilities_.getVal(ab_);
    }
    public String clickMove(int _index) {
        String key_ = affectedByMoves.get(_index);
        getForms().put(CST_MOVE, key_);
        return CST_MOVE;
    }
    public String getTrMove(int _index) {
        String ab_ = affectedByMoves.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedAbilities_.getVal(ab_);
    }
    public String clickPokemon(int _indexLevel, int _indexPk) {
        StringList pks_ = movesLevelLearntByPokemon.getValue(_indexLevel);
        String pk_ = pks_.get(_indexPk);
        getForms().put(CST_PK, pk_);
        return CST_POKEMON;
    }
    public String getTrPokemon(int _indexLevel, int _indexPk) {
        StringList pks_ = movesLevelLearntByPokemon.getValue(_indexLevel);
        DataBase data_ = getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        String pk_ = pks_.get(_indexPk);
        return translatedPokemon_.getVal(pk_);
    }
    public String clickPokemonTm(int _indexPk) {
        String pk_ = movesTmLearntByPokemon.get(_indexPk);
        getForms().put(CST_PK, pk_);
        return CST_POKEMON;
    }
    public String getTrPokemonTm(int _indexPk) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        String pk_ = movesTmLearntByPokemon.get(_indexPk);
        return translatedPokemon_.getVal(pk_);
    }
    public String clickPokemonHm(int _indexPk) {
        String pk_ = movesHmLearntByPokemon.get(_indexPk);
        getForms().put(CST_PK, pk_);
        return CST_POKEMON;
    }
    public String getTrPokemonHm(int _indexPk) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        String pk_ = movesHmLearntByPokemon.get(_indexPk);
        return translatedPokemon_.getVal(pk_);
    }
    public String clickPokemonMt(int _indexPk) {
        String pk_ = movesMtLearntByPokemon.get(_indexPk);
        getForms().put(CST_PK, pk_);
        return CST_POKEMON;
    }
    public String getTrPokemonMt(int _indexPk) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        String pk_ = movesMtLearntByPokemon.get(_indexPk);
        return translatedPokemon_.getVal(pk_);
    }
    public boolean canBeLearnt() {
        if (!movesLevelLearntByPokemon.isEmpty()) {
            return true;
        }
        if (!movesTmLearntByPokemon.isEmpty()) {
            return true;
        }
        if (!movesHmLearntByPokemon.isEmpty()) {
            return true;
        }
        return !movesMtLearntByPokemon.isEmpty();
    }
    public boolean isAdjAdv() {
        return targetChoice == TargetChoice.ADJ_ADV;
    }
    public boolean isAdjMult() {
        return targetChoice == TargetChoice.ADJ_MULT;
    }
    public boolean isAdjUniq() {
        return targetChoice == TargetChoice.ADJ_UNIQ;
    }
    public boolean isAllie() {
        return targetChoice == TargetChoice.ALLIE;
    }
    public boolean isAllies() {
        return targetChoice == TargetChoice.ALLIES;
    }
    public boolean isAnyFoe() {
        return targetChoice == TargetChoice.ANY_FOE;
    }
    public boolean isAutreUniq() {
        return targetChoice == TargetChoice.AUTRE_UNIQ;
    }
    public boolean isGlobale() {
        return targetChoice == TargetChoice.GLOBALE;
    }
    public boolean isLanceur() {
        return targetChoice == TargetChoice.LANCEUR;
    }
    public boolean isPseudoGlobale() {
        return targetChoice == TargetChoice.PSEUDO_GLOBALE;
    }
    public boolean isTousAdv() {
        return targetChoice == TargetChoice.TOUS_ADV;
    }
    public boolean isUniqueImporte() {
        return targetChoice == TargetChoice.UNIQUE_IMPORTE;
    }
    public boolean isNothing() {
        return targetChoice == TargetChoice.NOTHING;
    }
    public String getPage(int _long) {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        Effect eff_ = moveData_.getEffet(_long);
        if (eff_ instanceof EffectDamage) {
            return PAGE_DAMAGE;
        }
        if (eff_ instanceof EffectDamageRate) {
            return PAGE_DAMAGERATE;
        }
        if (eff_ instanceof EffectStatistic) {
            return PAGE_STATIS;
        }
        if (eff_ instanceof EffectStatus) {
            return PAGE_STATUS;
        }
        if (eff_ instanceof EffectTeam) {
            return PAGE_TEAM;
        }
        if (eff_ instanceof EffectGlobal) {
            return PAGE_GLOBAL;
        }
        if (eff_ instanceof EffectEndRound) {
            return PAGE_ENDROUND;
        }
        if (eff_ instanceof EffectTeamWhileSendFoe) {
            return PAGE_TEAMWHILESENDINGFOE;
        }
        if (eff_ instanceof EffectCopyMove) {
            return PAGE_COPYMOVE;
        }
        if (eff_ instanceof EffectFullHpRate) {
            return PAGE_FULLHPRATE;
        }
        if (eff_ instanceof EffectInvoke) {
            return PAGE_INVOKE;
        }
        if (eff_ instanceof EffectSwitchMoveTypes) {
            return PAGE_SWITCHMOVETYPES;
        }
        return red(eff_);
    }

    private String red(Effect _eff) {
        if (_eff instanceof EffectCounterAttack) {
            return PAGE_COUNTERATTACK;
        }
        if (_eff instanceof EffectProtection) {
            return PAGE_PROTECTION;
        }
        if (_eff instanceof EffectAccuracy) {
            return PAGE_ACCURACY;
        }
        if (_eff instanceof EffectCopyFighter) {
            return PAGE_COPYFIGHTER;
        }
        if (_eff instanceof EffectProtectFromTypes) {
            return PAGE_PROTECTFROMTYPES;
        }
        if (_eff instanceof EffectUnprotectFromTypes) {
            return PAGE_UNPROTECTFROMTYPES;
        }
        if (_eff instanceof EffectAlly) {
            return PAGE_ALLY;
        }
        if (_eff instanceof EffectBatonPass) {
            return PAGE_BATONPASS;
        }
        if (_eff instanceof EffectClone) {
            return PAGE_CLONE;
        }
        if (_eff instanceof EffectCommonStatistics) {
            return PAGE_COMMONSTATISTICS;
        }
        return redir(_eff);
    }

    private String redir(Effect _eff) {
        if (_eff instanceof EffectOrder) {
            return PAGE_ORDER;
        }
        if (_eff instanceof EffectRestriction) {
            return PAGE_RESTRICTION;
        }
        if (_eff instanceof EffectSwitchAbilities) {
            return PAGE_SWITCHABILITIES;
        }
        if (_eff instanceof EffectSwitchItems) {
            return PAGE_SWITCHITEMS;
        }
        if (_eff instanceof EffectSwitchTypes) {
            return PAGE_SWITCHTYPES;
        }
        if (_eff instanceof EffectSwitchPointView) {
            return PAGE_SWITCHPOINTVIEW;
        }
        if (_eff instanceof EffectRemainedHpRate) {
            return PAGE_REMAINEDHPRATE;
        }
        if (_eff instanceof EffectMultUsedMovePower) {
            return PAGE_MULTUSEDMOVEPOWER;
        }
        if (_eff instanceof EffectMultSufferedMovePower) {
            return PAGE_MULTSUFFEREDMOVEPOWER;
        }
        if (_eff instanceof EffectSwitchPosition) {
            return PAGE_SWITCHPOSITION;
        }
        if (_eff instanceof EffectVarPP) {
            return PAGE_VARPP;
        }
        if (_eff instanceof EffectWinMoney) {
            return PAGE_WINMONEY;
        }
        return DataBase.EMPTY_STRING;
    }

    public String getDisplayName() {
        return displayName;
    }

    public StringList getBoostedTypes() {
        return boostedTypes;
    }

    public boolean getHasDefaultTypes() {
        return hasDefaultTypes;
    }

    public StringList getTypes() {
        return types;
    }

    public DictionaryComparator<String,String> getTypesByOwnedItems() {
        return typesByOwnedItems;
    }

    public DictionaryComparator<String,String> getTypesByWeathers() {
        return typesByWeathers;
    }

    public String getCategory() {
        return category;
    }

    public short getPp() {
        return pp;
    }

    public byte getPriority() {
        return priority;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public NatStringTreeMap<String> getMapVarsAccuracy() {
        return mapVarsAccuracy;
    }

    public boolean getIgnVarAccurUserNeg() {
        return ignVarAccurUserNeg;
    }

    public boolean getIgnVarEvasTargetPos() {
        return ignVarEvasTargetPos;
    }

    public short getNbPrepaRound() {
        return nbPrepaRound;
    }

    public boolean getDisappearBeforeUse() {
        return disappearBeforeUse;
    }

    public StringList getDeletedStatus() {
        return deletedStatus;
    }

    public StringList getRequiredStatus() {
        return requiredStatus;
    }

    public StringList getAchieveDisappearedPkUsingMove() {
        return achieveDisappearedPkUsingMove;
    }

    public StringList getAbilities() {
        return abilities;
    }

    public StringList getItems() {
        return items;
    }

    public boolean getCannotKo() {
        return cannotKo;
    }

    public StringList getAffectedByMoves() {
        return affectedByMoves;
    }

    public DictionaryComparator<String,Ints> getSecEffectsByItem() {
        return secEffectsByItem;
    }

    public Ints getEffects() {
        return effects;
    }

    public boolean getSecEffectIfNoDamage() {
        return secEffectIfNoDamage;
    }

    public String getName() {
        return name;
    }

    public boolean getRechargeRound() {
        return rechargeRound;
    }

    public boolean getConstUserChoice() {
        return constUserChoice;
    }

    public short getRankIncrementNbRound() {
        return rankIncrementNbRound;
    }

    public DictionaryComparator<LgInt,Rate> getRepeatRoundLaw() {
        return repeatRoundLaw;
    }

    public ShortTreeMap<StringList> getMovesLevelLearntByPokemon() {
        return movesLevelLearntByPokemon;
    }

    public StringList getMovesTmLearntByPokemon() {
        return movesTmLearntByPokemon;
    }

    public StringList getMovesHmLearntByPokemon() {
        return movesHmLearntByPokemon;
    }

    public StringList getMovesMtLearntByPokemon() {
        return movesMtLearntByPokemon;
    }
}