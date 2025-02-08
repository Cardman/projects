package aiki.beans.moves;

import aiki.beans.*;
import aiki.beans.WithFilterBean;
import aiki.beans.moves.effects.*;
import aiki.comparators.ComparingTranslatedKey;
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
import code.scripts.confs.PkScriptPages;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class MoveBean extends CommonBean {

    private String name;
    private String displayName;
    private long pp;
    private boolean hasDefaultTypes;
    private StringList types;
    private String category;
    private StringList boostedTypes;
    private long priority;
    private String accuracy;
    private Ints effects;
    private long nbPrepaRound;
    private boolean disappearBeforeUse;
    private DictionaryComparator<LgInt,Rate> repeatRoundLaw;
    private long rankIncrementNbRound;
    private boolean rechargeRound;
    private boolean constUserChoice;
    private boolean secEffectIfNoDamage;
    private DictionaryComparator<TranslatedKey, Ints> secEffectsByItem;
    private boolean ignVarAccurUserNeg;
    private boolean ignVarEvasTargetPos;
    private CustList<TranslatedKey> achieveDisappearedPkUsingMove;

    private SwitchType switchType;
    private DictionaryComparator<TranslatedKey,TranslatedKey> typesByOwnedItems;
    private DictionaryComparator<TranslatedKey,TranslatedKey> typesByWeathers;
    private TargetChoice targetChoice;
    private CustList<TranslatedKey> deletedStatus;
    private CustList<TranslatedKey> requiredStatus;
    private CustList<TranslatedKey> abilities;
    private CustList<TranslatedKey> items;
    private NatStringTreeMap<String> mapVarsAccuracy;
    private boolean cannotKo;
    private CustList<TranslatedKey> affectedByMoves;
    private LongTreeMap<CustList<TranslatedKey>> movesLevelLearntByPokemon;
    private CustList<TranslatedKey> movesTmLearntByPokemon;
    private CustList<TranslatedKey> movesHmLearntByPokemon;
    private CustList<TranslatedKey> movesMtLearntByPokemon;

    @Override
    public void beforeDisplaying() {
        String name_ = getForms().getValStr(CST_MOVE);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        name = name_;
        displayName = translatedMoves_.getVal(name_);
        MoveData moveData_ = data_.getMove(name_);
        StringMap<String> translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        category = translatedCategories_.getVal(data_.getCategory(moveData_));
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
        achieveDisappearedPkUsingMove = listTrStringsMv(moveData_.getAchieveDisappearedPkUsingMove(),data_,getLanguage());
        abilities = abilities();
        items = items();
        if (moveData_ instanceof DamagingMoveData) {
            cannotKo = ((DamagingMoveData)moveData_).getCannotKo();
        }
        affectedByMoves = listTrStringsMv(affectedByMoves(moveData_),data_,getLanguage());
        Ints effects_ = new Ints();
        int nbEffects_ = moveData_.nbEffets();
        for (int i = IndexConstants.FIRST_INDEX; i < nbEffects_; i++) {
            effects_.add(i);
        }
        effects = effects_;
        repeatRoundLaw = DictionaryComparatorUtil.buildIntRate(moveData_.getRepeatRoundLaw());
        rankIncrementNbRound = moveData_.getRankIncrementNbRound();
        rechargeRound = moveData_.getRechargeRound();
        constUserChoice = moveData_.getConstUserChoice();
        switchType = moveData_.getSwitchType();
        secEffectIfNoDamage = moveData_.getSecEffectIfNoDamage();
        deletedStatus = listTrStringsSt(moveData_.getDeletedStatus(),data_,getLanguage());
        requiredStatus = listTrStringsSt(moveData_.getRequiredStatus(),data_,getLanguage());
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        DictionaryComparator<TranslatedKey, Ints> secEffectsByItem_;
        secEffectsByItem_ = DictionaryComparatorUtil.buildItemsLs();
        for (EntryCust<String, Ints> s: moveData_.getSecEffectsByItem().entryList()) {
            secEffectsByItem_.put(buildIt(getDataBase(),translatedItems_, s.getKey()), getValidEffects(moveData_, s.getValue()));
        }
        secEffectsByItem = secEffectsByItem_;
        movesLevelLearntByPokemon = movesLevelLearntByPokemon();
        movesTmLearntByPokemon = movesTmLearntByPokemon();
        CustList<TranslatedKey> movesHmLearntByPokemon_;
        movesHmLearntByPokemon_ = new CustList<TranslatedKey>();
        Ints hms_ = data_.getHmByMove(name_);
//        if (Map.<Short>hasString(data_.getHm(), name_))
        if (!hms_.isEmpty()) {
//            short tm_ = data_.getHm().getKeys(name_).first();
//            short tm_ = data_.getHmByMove(name_).first();
            int tm_ = hms_.first();
            for (String p: data_.getPokedex().getKeys()) {
                PokemonData pkData_ = data_.getPokemon(p);
                if (pkData_.getHiddenMoves().containsObj(tm_)) {
                    movesHmLearntByPokemon_.add(buildPk(data_.getTranslatedPokemon().getVal(getLanguage()),p));
                }
            }
            movesHmLearntByPokemon_.sortElts(new ComparingTranslatedKey());
        }
        movesHmLearntByPokemon = movesHmLearntByPokemon_;
        CustList<TranslatedKey> movesMtLearntByPokemon_;
        movesMtLearntByPokemon_ = new CustList<TranslatedKey>();
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pkData_ = data_.getPokemon(p);
            if (StringUtil.contains(pkData_.getMoveTutors(), name_)) {
                movesMtLearntByPokemon_.add(buildPk(data_.getTranslatedPokemon().getVal(getLanguage()),p));
            }
        }
        movesMtLearntByPokemon_.sortElts(new ComparingTranslatedKey());
        movesMtLearntByPokemon = movesMtLearntByPokemon_;
        getForms().getCurrent().clear();
        getForms().getCurrent().addAllElts(moveData_.getEffects());
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

    private CustList<TranslatedKey> movesTmLearntByPokemon() {
        DataBase data_ = getDataBase();
        CustList<TranslatedKey> movesTmLearntByPokemon_;
        movesTmLearntByPokemon_ = new CustList<TranslatedKey>();
        Ints tms_ = data_.getTmByMove(name);
//        if (Map.<Short>hasString(data_.getTm(), name_))
        if (!tms_.isEmpty()) {
//            short tm_ = data_.getTm().getKeys(name_).first();
//            short tm_ = data_.getTmByMove(name_).first();
            int tm_ = tms_.first();
            for (String p: data_.getPokedex().getKeys()) {
                PokemonData pkData_ = data_.getPokemon(p);
                if (pkData_.getTechnicalMoves().containsObj(tm_)) {
                    movesTmLearntByPokemon_.add(buildPk(data_.getTranslatedPokemon().getVal(getLanguage()),p));
                }
            }
            movesTmLearntByPokemon_.sortElts(new ComparingTranslatedKey());
        }
        return movesTmLearntByPokemon_;
    }

    private LongTreeMap<CustList<TranslatedKey>> movesLevelLearntByPokemon() {
        DataBase data_ = getDataBase();
        LongTreeMap<CustList<TranslatedKey>> movesLevelLearntByPokemon_;
        movesLevelLearntByPokemon_ = new LongTreeMap<CustList<TranslatedKey>>();
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pkData_ = data_.getPokemon(p);
            for (LevelMove l: pkData_.getLevMoves()) {
                if (!StringUtil.quickEq(l.getMove(), name)) {
                    continue;
                }
                if (!movesLevelLearntByPokemon_.contains(l.getLevel())) {
                    movesLevelLearntByPokemon_.put(l.getLevel(), new CustList<TranslatedKey>(buildPk(data_.getTranslatedPokemon().getVal(getLanguage()),p)));
                } else {
                    movesLevelLearntByPokemon_.getVal(l.getLevel()).add(buildPk(data_.getTranslatedPokemon().getVal(getLanguage()),p));
                }
            }
        }
        for (CustList<TranslatedKey> v: movesLevelLearntByPokemon_.values()) {
            v.sortElts(new ComparingTranslatedKey());
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

    private CustList<TranslatedKey> items() {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        CustList<TranslatedKey> items_ = new CustList<TranslatedKey>();
        for (String a: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(a);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle itBattle_ = (ItemForBattle) it_;
            if (StringUtil.contains(itBattle_.getImmuMoves(), name)) {
                items_.add(buildIt(getDataBase(),translatedItems_,a));
            }
        }
        items_.sortElts(DictionaryComparatorUtil.cmpItems());
        return items_;
    }

    private CustList<TranslatedKey> abilities() {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        CustList<TranslatedKey> abilities_ = new CustList<TranslatedKey>();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (StringUtil.contains(ab_.getImmuMove(), name)) {
                abilities_.add(buildAb(translatedAbilities_,a));
            }
        }
        abilities_.sortElts(DictionaryComparatorUtil.cmpAbilities());
        return abilities_;
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
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        DictionaryComparator<TranslatedKey, TranslatedKey> typesByOwnedItems_;
        typesByOwnedItems_ =DictionaryComparatorUtil.buildItemsStr();
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
            typesByOwnedItems_.put(buildIt(getDataBase(),translatedItems_, k), build(translatedTypes_, type_));
            //typesByOwnedItem_.add(line_);
        }
        //typesByOwnedItem = typesByOwnedItem_;
        typesByOwnedItems = typesByOwnedItems_;

//        CustList<WeatherTypeLine> typesByWeather_;
//        typesByWeather_ = new CustList<>();
        DictionaryComparator<TranslatedKey, TranslatedKey> typesByWeathers_;
        typesByWeathers_ = DictionaryComparatorUtil.buildMovesStr();
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
            typesByWeathers_.put(buildMv(translatedMoves_, k), build(translatedTypes_, type_));
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
        return PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML;
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
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
        return !moveData_.getTypesByOwnedItem().isEmpty() && !moveData_.getTypesByWeather().isEmpty();
    }
    public boolean typesDependOnlyOnItem() {
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
        return !moveData_.getTypesByOwnedItem().isEmpty() && moveData_.getTypesByWeather().isEmpty();
    }
    public boolean typesDependOnlyOnWeather() {
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
        return moveData_.getTypesByOwnedItem().isEmpty() && !moveData_.getTypesByWeather().isEmpty();
    }
    public boolean isDamagingMove() {
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
        return moveData_ instanceof DamagingMoveData;
    }
    public boolean isDamagingDirectMove() {
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
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
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
        return _long < moveData_.indexOfPrimaryEffect();
    }
    public boolean isPrimaryEffect(int _long) {
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
        return _long == moveData_.indexOfPrimaryEffect();
    }
    public boolean isAfterPrimaryEffect(int _long) {
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
        return _long > moveData_.indexOfPrimaryEffect();
    }
    public boolean isEndRoundEffect(int _long) {
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
        return moveData_.getEffet(_long) instanceof EffectEndRound;
    }

    public boolean effPrimOrBeforeNotEndRound(int _index) {
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
        int indPr_ = moveData_.indexOfPrimaryEffect();
        return _index <= indPr_ && !(moveData_.getEffet(_index) instanceof EffectEndRound);
    }

    public boolean effSecNotEndRound(int _index) {
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
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
        return achieveDisappearedPkUsingMove.get(_index).getTranslation();
//        String move_ = achieveDisappearedPkUsingMove.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(move_);
    }
    public String clickAchieveDisappearedPkUsingMove(int _index) {
        return tryRedirect(achieveDisappearedPkUsingMove.get(_index));
    }
    public String clickRequiredStatus(int _index) {
        return tryRedirect(requiredStatus.get(_index));
    }
    public String getRequiredStatus(int _index) {
        return requiredStatus.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        String key_ = getRequiredStatusKey(_index);
//        return translatedStatus_.getVal(key_);
    }
//
//    private String getRequiredStatusKey(int _index) {
//        return requiredStatus.get(_index);
//    }
    public String clickDeletedStatus(int _index) {
        return tryRedirect(deletedStatus.get(_index));
    }
    public String getDeletedStatus(int _index) {
        return deletedStatus.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        String key_ = getDeletedStatusKey(_index);
//        return translatedStatus_.getVal(key_);
    }

//    private String getDeletedStatusKey(int _index) {
//        return deletedStatus.get(_index);
//    }
    public boolean isItem(int _index) {
        return !typesByOwnedItems.getKey(_index).getKey().isEmpty();
    }
    public String clickTypesByOwnedItems(int _index) {
        return tryRedirect(typesByOwnedItems.getKey(_index));
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
        return typesByOwnedItems.getKey(_index).getTranslation();
//        String item_ = typesByOwnedItems.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
//        return translatedItems_.getVal(item_);
    }
    public boolean isWeather(int _index) {
        return !typesByWeathers.getKey(_index).getKey().isEmpty();
    }
    public String clickTypesByWeathers(int _index) {
        return tryRedirect(typesByWeathers.getKey(_index));
    }
    public String getTrTypesByWeathers(int _index) {
        return typesByWeathers.getKey(_index).getTranslation();
//        String item_ = typesByWeathers.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(item_);
    }
    public String clickItemSecEffect(int _index) {
//        String item_ = getItemSecEffect(_index).getKey();
        return tryRedirect(getItemSecEffect(_index));
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
        return getItemSecEffect(_index).getTranslation();
    }

    private TranslatedKey getItemSecEffect(int _index) {
        return secEffectsByItem.getKey(_index);
    }
    public String clickAbility(int _index) {
        return tryRedirect(abilities.get(_index));
    }
    public String getTrAbility(int _index) {
        return abilities.get(_index).getTranslation();
//        String ab_ = abilities.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        return translatedAbilities_.getVal(ab_);
    }
    public String clickItem(int _index) {
//        String key_ = items.get(_index);
        return tryRedirect(items.get(_index));
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
        return items.get(_index).getTranslation();
//        String ab_ = items.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedAbilities_ = data_.getTranslatedItems().getVal(getLanguage());
//        return translatedAbilities_.getVal(ab_);
    }
    public String clickMove(int _index) {
        return tryRedirect(affectedByMoves.get(_index));
    }
    public String getTrMove(int _index) {
        return affectedByMoves.get(_index).getTranslation();
//        String ab_ = affectedByMoves.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedAbilities_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedAbilities_.getVal(ab_);
    }
    public String clickPokemon(int _indexLevel, int _indexPk) {
        return tryRedirect(movesLevelLearntByPokemon.getValue(_indexLevel).get(_indexPk));
    }
    public String getTrPokemon(int _indexLevel, int _indexPk) {
        return movesLevelLearntByPokemon.getValue(_indexLevel).get(_indexPk).getTranslation();
//        StringList pks_ = ;
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        String pk_ = pks_.get(_indexPk);
//        return translatedPokemon_.getVal(pk_);
    }
    public String clickPokemonTm(int _indexPk) {
        return tryRedirect(movesTmLearntByPokemon.get(_indexPk));
    }
    public String getTrPokemonTm(int _indexPk) {
        return movesTmLearntByPokemon.get(_indexPk).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        String pk_ = movesTmLearntByPokemon.get(_indexPk);
//        return translatedPokemon_.getVal(pk_);
    }
    public String clickPokemonHm(int _indexPk) {
        return tryRedirect(movesHmLearntByPokemon.get(_indexPk));
    }
    public String getTrPokemonHm(int _indexPk) {
        return movesHmLearntByPokemon.get(_indexPk).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        String pk_ = movesHmLearntByPokemon.get(_indexPk);
//        return translatedPokemon_.getVal(pk_);
    }
    public String clickPokemonMt(int _indexPk) {
        return tryRedirect(movesMtLearntByPokemon.get(_indexPk));
    }
    public String getTrPokemonMt(int _indexPk) {
        return movesMtLearntByPokemon.get(_indexPk).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        String pk_ = movesMtLearntByPokemon.get(_indexPk);
//        return translatedPokemon_.getVal(pk_);
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
    public String getPage(int _long) {
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
        Effect eff_ = moveData_.getEffet(_long);
        if (eff_ instanceof EffectDamage) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFDAMAGE_HTML;
        }
        if (eff_ instanceof EffectDamageRate) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFDAMAGERATE_HTML;
        }
        if (eff_ instanceof EffectStatistic) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSTATIS_HTML;
        }
        if (eff_ instanceof EffectStatus) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSTATUS_HTML;
        }
        if (eff_ instanceof EffectTeam) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFTEAM_HTML;
        }
        if (eff_ instanceof EffectGlobal) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFGLOBAL_HTML;
        }
        if (eff_ instanceof EffectEndRound) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFENDROUND_HTML;
        }
        if (eff_ instanceof EffectTeamWhileSendFoe) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFTEAMWHILESENDINGFOE_HTML;
        }
        if (eff_ instanceof EffectCopyMove) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOPYMOVE_HTML;
        }
        if (eff_ instanceof EffectFullHpRate) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFFULLHPRATE_HTML;
        }
        if (eff_ instanceof EffectInvoke) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFINVOKE_HTML;
        }
        if (eff_ instanceof EffectSwitchTypes) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHTYPES_HTML;
        }
        if (eff_ instanceof EffectSwitchMoveTypes) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHMOVETYPES_HTML;
        }
        return red(eff_);
    }

    private String red(Effect _eff) {
        if (_eff instanceof EffectCounterAttack) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOUNTERATTACK_HTML;
        }
        if (_eff instanceof EffectProtection) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFPROTECTION_HTML;
        }
        if (_eff instanceof EffectAccuracy) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFACCURACY_HTML;
        }
        if (_eff instanceof EffectCopyFighter) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOPYFIGHTER_HTML;
        }
        if (_eff instanceof EffectProtectFromTypes) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFPROTECTFROMTYPES_HTML;
        }
        if (_eff instanceof EffectUnprotectFromTypes) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFUNPROTECTFROMTYPES_HTML;
        }
        if (_eff instanceof EffectAlly) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFALLY_HTML;
        }
        if (_eff instanceof EffectBatonPass) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFBATONPASS_HTML;
        }
        if (_eff instanceof EffectClone) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCLONE_HTML;
        }
        if (_eff instanceof EffectCommonStatistics) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOMMONSTATISTICS_HTML;
        }
        return redir(_eff);
    }

    private String redir(Effect _eff) {
        if (_eff instanceof EffectOrder) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFORDER_HTML;
        }
        if (_eff instanceof EffectRestriction) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFRESTRICTION_HTML;
        }
        if (_eff instanceof EffectSwitchAbilities) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHABILITIES_HTML;
        }
        if (_eff instanceof EffectSwitchItems) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHITEMS_HTML;
        }
        if (_eff instanceof EffectSwitchPointView) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHPOINTVIEW_HTML;
        }
        if (_eff instanceof EffectRemainedHpRate) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFREMAINEDHPRATE_HTML;
        }
        if (_eff instanceof EffectMultUsedMovePower) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFMULTUSEDMOVEPOWER_HTML;
        }
        if (_eff instanceof EffectMultSufferedMovePower) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFMULTSUFFEREDMOVEPOWER_HTML;
        }
        if (_eff instanceof EffectSwitchPosition) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHPOSITION_HTML;
        }
        if (_eff instanceof EffectVarPP) {
            return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFVARPP_HTML;
        }
        return PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFWINMONEY_HTML;
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

    public DictionaryComparator<TranslatedKey,TranslatedKey> getTypesByOwnedItems() {
        return typesByOwnedItems;
    }

    public DictionaryComparator<TranslatedKey,TranslatedKey> getTypesByWeathers() {
        return typesByWeathers;
    }

    public String getCategory() {
        return category;
    }

    public long getPp() {
        return pp;
    }

    public long getPriority() {
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

    public long getNbPrepaRound() {
        return nbPrepaRound;
    }

    public boolean getDisappearBeforeUse() {
        return disappearBeforeUse;
    }

    public CustList<TranslatedKey> getDeletedStatus() {
        return deletedStatus;
    }

    public CustList<TranslatedKey> getRequiredStatus() {
        return requiredStatus;
    }

    public CustList<TranslatedKey> getAchieveDisappearedPkUsingMove() {
        return achieveDisappearedPkUsingMove;
    }

    public CustList<TranslatedKey> getAbilities() {
        return abilities;
    }

    public CustList<TranslatedKey> getItems() {
        return items;
    }

    public boolean getCannotKo() {
        return cannotKo;
    }

    public CustList<TranslatedKey> getAffectedByMoves() {
        return affectedByMoves;
    }

    public DictionaryComparator<TranslatedKey,Ints> getSecEffectsByItem() {
        return secEffectsByItem;
    }

    public Ints getEffects() {
        DataBase data_ = getDataBase();
        MoveData moveData_ = data_.getMove(name);
        CustList<EffectBean> curr_ = getForms().getCurrentBean();
        curr_.clear();
        CustList<Effect> effs_ = moveData_.getEffects();
        int len_ = effs_.size();
        for (int i = 0; i < len_; i++) {
            buildOne(curr_, effs_, i);
            buildTwo(curr_, effs_, i);
            buildThree(curr_, effs_, i);
        }
        return effects;
    }

    private void buildOne(CustList<EffectBean> _curr, CustList<Effect> _effs, int _i) {
        Effect e_ = _effs.get(_i);
        if (e_ instanceof EffectDamage) {
            build(_curr, _i, new EffectDamageBean());
        }
        if (e_ instanceof EffectDamageRate) {
            build(_curr, _i, new EffectDamageRateBean());
        }
        if (e_ instanceof EffectStatistic) {
            build(_curr, _i, new EffectStatisticBean());
        }
        if (e_ instanceof EffectStatus) {
            build(_curr, _i, new EffectStatusBean());
        }
        if (e_ instanceof EffectTeam) {
            build(_curr, _i, new EffectTeamBean());
        }
        if (e_ instanceof EffectGlobal) {
            build(_curr, _i, new EffectGlobalBean());
        }
        if (e_ instanceof EffectEndRound) {
            build(_curr, _i, new EffectEndRoundMoveBean());
        }
        if (e_ instanceof EffectTeamWhileSendFoe) {
            build(_curr, _i, new EffectTeamWhileSendFoeBean());
        }
        if (e_ instanceof EffectCopyMove) {
            build(_curr, _i, new EffectCopyMoveBean());
        }
        if (e_ instanceof EffectFullHpRate) {
            build(_curr, _i, new EffectFullHpRateBean());
        }
        if (e_ instanceof EffectInvoke) {
            build(_curr, _i, new EffectInvokeBean());
        }
        if (e_ instanceof EffectSwitchTypes) {
            build(_curr, _i, new EffectSwitchTypesBean());
        }
        if (e_ instanceof EffectSwitchMoveTypes) {
            build(_curr, _i, new EffectSwitchMoveTypesBean());
        }
    }

    private void buildTwo(CustList<EffectBean> _curr, CustList<Effect> _effs, int _i) {
        Effect e_ = _effs.get(_i);
        if (e_ instanceof EffectCounterAttack) {
            build(_curr, _i, new EffectCounterAttackBean());
        }
        if (e_ instanceof EffectProtection) {
            build(_curr, _i, new EffectProtectionBean());
        }
        if (e_ instanceof EffectAccuracy) {
            build(_curr, _i, new EffectBean());
        }
        if (e_ instanceof EffectCopyFighter) {
            build(_curr, _i, new EffectCopyFighterBean());
        }
        if (e_ instanceof EffectProtectFromTypes) {
            build(_curr, _i, new EffectProtectFromTypesBean());
        }
        if (e_ instanceof EffectUnprotectFromTypes) {
            build(_curr, _i, new EffectUnprotectFromTypesBean());
        }
        if (e_ instanceof EffectAlly) {
            build(_curr, _i, new EffectAllyBean());
        }
        if (e_ instanceof EffectBatonPass) {
            build(_curr, _i, new EffectBatonPassBean());
        }
        if (e_ instanceof EffectClone) {
            build(_curr, _i, new EffectCloneBean());
        }
        if (e_ instanceof EffectCommonStatistics) {
            build(_curr, _i, new EffectCommonStatisticsBean());
        }
    }
    private void buildThree(CustList<EffectBean> _curr, CustList<Effect> _effs, int _i) {
        Effect e_ = _effs.get(_i);
        if (e_ instanceof EffectOrder) {
            build(_curr, _i, new EffectOrderBean());
        }
        if (e_ instanceof EffectRestriction) {
            build(_curr, _i, new EffectRestrictionBean());
        }
        if (e_ instanceof EffectSwitchAbilities) {
            build(_curr, _i, new EffectSwitchAbilitiesBean());
        }
        if (e_ instanceof EffectSwitchItems) {
            build(_curr, _i, new EffectSwitchItemsBean());
        }
        if (e_ instanceof EffectSwitchPointView) {
            build(_curr, _i, new EffectSwitchPointViewBean());
        }
        if (e_ instanceof EffectRemainedHpRate) {
            build(_curr, _i, new EffectRemainedHpRateBean());
        }
        if (e_ instanceof EffectMultUsedMovePower || e_ instanceof EffectMultSufferedMovePower) {
            build(_curr, _i, new EffectMultMovePowerBean());
        }
        if (e_ instanceof EffectSwitchPosition) {
            build(_curr, _i, new EffectBean());
        }
        if (e_ instanceof EffectVarPP) {
            build(_curr, _i, new EffectVarPPBean());
        }
        if (e_ instanceof EffectWinMoney) {
            build(_curr, _i, new EffectWinMoneyBean());
        }
    }

    private void build(CustList<EffectBean> _feed,int _i, EffectBean _b) {
        fwd(_b);
        _b.setMove(name);
        _b.setIndex(_i);
        _b.beforeDisplaying();
        _b.getForms().setCurrentBean(_feed);
        _feed.add(_b);
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

    public long getRankIncrementNbRound() {
        return rankIncrementNbRound;
    }

    public DictionaryComparator<LgInt,Rate> getRepeatRoundLaw() {
        return repeatRoundLaw;
    }

    public LongTreeMap<CustList<TranslatedKey>> getMovesLevelLearntByPokemon() {
        return movesLevelLearntByPokemon;
    }

    public CustList<TranslatedKey> getMovesTmLearntByPokemon() {
        return movesTmLearntByPokemon;
    }

    public CustList<TranslatedKey> getMovesHmLearntByPokemon() {
        return movesHmLearntByPokemon;
    }

    public CustList<TranslatedKey> getMovesMtLearntByPokemon() {
        return movesMtLearntByPokemon;
    }
}