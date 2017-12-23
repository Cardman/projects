package aiki.beans.moves;
import code.bean.Accessible;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.NatCmpTreeMap;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.abilities.AbilityData;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.Boost;
import aiki.fight.items.EvolvingItem;
import aiki.fight.items.EvolvingStone;
import aiki.fight.items.Fossil;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingStatus;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.items.Repel;
import aiki.fight.items.SellingItem;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectAccuracy;
import aiki.fight.moves.effects.EffectAlly;
import aiki.fight.moves.effects.EffectBatonPass;
import aiki.fight.moves.effects.EffectClone;
import aiki.fight.moves.effects.EffectCommonStatistics;
import aiki.fight.moves.effects.EffectCopyFighter;
import aiki.fight.moves.effects.EffectCopyMove;
import aiki.fight.moves.effects.EffectCounterAttack;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectDamageRate;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectFullHpRate;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.effects.EffectMultSufferedMovePower;
import aiki.fight.moves.effects.EffectMultUsedMovePower;
import aiki.fight.moves.effects.EffectOrder;
import aiki.fight.moves.effects.EffectProtectFromTypes;
import aiki.fight.moves.effects.EffectProtection;
import aiki.fight.moves.effects.EffectRemainedHpRate;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.effects.EffectSwitchAbilities;
import aiki.fight.moves.effects.EffectSwitchItems;
import aiki.fight.moves.effects.EffectSwitchMoveTypes;
import aiki.fight.moves.effects.EffectSwitchPointView;
import aiki.fight.moves.effects.EffectSwitchPosition;
import aiki.fight.moves.effects.EffectSwitchTypes;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.moves.effects.EffectUnprotectFromTypes;
import aiki.fight.moves.effects.EffectVarPP;
import aiki.fight.moves.effects.EffectWinMoney;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.util.LevelMove;

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

    @Accessible
    private String name;

    @Accessible
    private String displayName;

    @Accessible
    private short pp;

    @Accessible
    private boolean hasDefaultTypes;

    @Accessible
    private StringList types;

    @Accessible
    private String category;

    @Accessible
    private StringList boostedTypes;

    @Accessible
    private byte priority;

    @Accessible
    private String accuracy;

    @Accessible
    private Numbers<Integer> effects;

    @Accessible
    private short nbPrepaRound;

    @Accessible
    private boolean disappearBeforeUse;

    @Accessible
    private NatCmpTreeMap<LgInt,Rate> repeatRoundLaw;

    @Accessible
    private short rankIncrementNbRound;

    @Accessible
    private boolean rechargeRound;

    @Accessible
    private boolean constUserChoice;

    @Accessible
    private boolean secEffectIfNoDamage;

    @Accessible
    private TreeMap<String, Numbers<Integer>> secEffectsByItem;

    @Accessible
    private boolean ignVarAccurUserNeg;

    @Accessible
    private boolean ignVarEvasTargetPos;

    @Accessible
    private StringList achieveDisappearedPkUsingMove;

    private SwitchType switchType;

//    @Accessible
//    private CustList<ItemTypeLine> typesByOwnedItem;

    @Accessible
    private TreeMap<String,String> typesByOwnedItems;

//    @Accessible
//    private CustList<WeatherTypeLine> typesByWeather;

    @Accessible
    private TreeMap<String,String> typesByWeathers;

    @Accessible
    private TargetChoice targetChoice;

    @Accessible
    private StringList deletedStatus;

    @Accessible
    private StringList requiredStatus;

    @Accessible
    private StringList abilities;

    @Accessible
    private StringList items;

    @Accessible
    private NatTreeMap<String,String> mapVarsAccuracy;

    @Accessible
    private boolean cannotKo;

    @Accessible
    private StringList affectedByMoves;

    @Accessible
    private NatTreeMap<Short,StringList> movesLevelLearntByPokemon;

    @Accessible
    private StringList movesTmLearntByPokemon;

    @Accessible
    private StringList movesHmLearntByPokemon;

    @Accessible
    private StringList movesMtLearntByPokemon;

    @Override
    public void beforeDisplaying() {
        String name_ = (String) getForms().getVal(MOVE);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        name = name_;
        displayName = translatedMoves_.getVal(name_);
        MoveData moveData_ = data_.getMove(name_);
        StringMap<String> translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        category = translatedCategories_.getVal(moveData_.getCategory());
        pp = moveData_.getPp();
        priority = moveData_.getPriority();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
//        CustList<ItemTypeLine> typesByOwnedItem_;
//        typesByOwnedItem_ = new CustList<>();
        TreeMap<String,String> typesByOwnedItems_;
        typesByOwnedItems_ = new TreeMap<String, String>(new ComparatorTrStrings(translatedItems_));
        boolean hasDefault_ = false;
        for (String k: moveData_.getTypesByOwnedItem().getKeys()) {
            //ItemTypeLine line_ = new ItemTypeLine();
            if (!translatedItems_.contains(k)) {
                if (!moveData_.getTypesByWeather().isEmpty()) {
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
            String type_ = moveData_.getTypesByOwnedItem().getVal(k);
            //line_.setType(translatedTypes_.getVal(type_));
            typesByOwnedItems_.put(k, translatedTypes_.getVal(type_));
            //typesByOwnedItem_.add(line_);
        }
        //typesByOwnedItem = typesByOwnedItem_;
        typesByOwnedItems = typesByOwnedItems_;

//        CustList<WeatherTypeLine> typesByWeather_;
//        typesByWeather_ = new CustList<>();
        TreeMap<String,String> typesByWeathers_;
        typesByWeathers_ = new TreeMap<String, String>(new ComparatorTrStrings(translatedMoves_));
        for (String k: moveData_.getTypesByWeather().getKeys()) {
            //WeatherTypeLine line_ = new WeatherTypeLine();
//            if (translatedMoves_.contains(k)) {
//                line_.setWeather(translatedMoves_.getVal(k));
//            } else {
//                line_.setWeather(messages_.getVal(OTHER_WEATHER));
//                hasDefault_ = true;
//            }
            if (!translatedMoves_.contains(k)) {
                //line_.setWeather(translatedMoves_.getVal(k));
                hasDefault_ = true;
            }
//            else {
//                //line_.setWeather(messages_.getVal(OTHER_WEATHER));
//                //hasDefault_ = true;
//            }
            String type_ = moveData_.getTypesByWeather().getVal(k);
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
            for (String t: moveData_.getTypes()) {
                types_.add(translatedTypes_.getVal(t));
            }
            types_.sort();
            types = types_;
        }
        StringList boostedTypes_ = new StringList();
        for (String t: moveData_.getBoostedTypes()) {
            boostedTypes_.add(translatedTypes_.getVal(t));
        }
        boostedTypes_.sort();
        boostedTypes = boostedTypes_;
        hasDefaultTypes = hasDefault_;
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        accuracy = data_.getFormula(moveData_.getAccuracy(),getLanguage());
//        accuracy = StringList.replace(accuracy, loc_);
//        accuracy = accuracy.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        accuracy = accuracy.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        NatTreeMap<String,String> mapVars_ = data_.getDescriptions(moveData_.getAccuracy(),getLanguage());
        NatTreeMap<String,String> mapVarsAccuracy_ = new NatTreeMap<String,String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsAccuracy_.put(k, mapVars_.getVal(k));
        }
        mapVarsAccuracy = mapVarsAccuracy_;
        ignVarAccurUserNeg = moveData_.getIgnVarAccurUserNeg();
        ignVarEvasTargetPos = moveData_.getIgnVarEvasTargetPos();
        nbPrepaRound = moveData_.getNbPrepaRound();
        disappearBeforeUse = moveData_.getDisappearBeforeUse();
        targetChoice = moveData_.getTargetChoice();
        StringList achieveDisappearedPkUsingMove_ = new StringList();
        for (String m: moveData_.getAchieveDisappearedPkUsingMove()) {
            achieveDisappearedPkUsingMove_.add(m);
        }
        achieveDisappearedPkUsingMove_.sortElts(new ComparatorTrStrings(translatedMoves_));
        achieveDisappearedPkUsingMove = achieveDisappearedPkUsingMove_;
        StringList abilities_ = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getImmuMove().containsObj(name_)) {
                abilities_.add(a);
            }
        }
        abilities_.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilities = abilities_;
        StringList items_ = new StringList();
        for (String a: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(a);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle itBattle_ = (ItemForBattle) it_;
            if (itBattle_.getImmuMoves().containsObj(name_)) {
                items_.add(a);
            }
        }
        items_.sortElts(new ComparatorTrStrings(translatedItems_));
        items = items_;
        if (moveData_ instanceof DamagingMoveData) {
            cannotKo = ((DamagingMoveData)moveData_).getCannotKo();
        }
        StringList affectedByMoves_ = new StringList();
        if (moveData_.getStoppableMoveMulti()) {
            for (String move_ : data_.getMovesProtAgainstMultiTarget()) {
                affectedByMoves_.add(move_);
            }
        }
        if (moveData_.getStoppableMovePrio() && priority > 0) {
            for (String move_ : data_.getMovesProtAgainstPrio()) {
                affectedByMoves_.add(move_);
            }
        }
        if (moveData_.getStoppableMoveSolo()) {
            for (String move_ : data_.getMovesProtSingleTarget()) {
                affectedByMoves_.add(move_);
            }
        }
        if (moveData_ instanceof DamagingMoveData) {
            for (String move_ : data_.getMovesProtAgainstDamageMoves()) {
                affectedByMoves_.add(move_);
            }
            DamagingMoveData damag_ = (DamagingMoveData) moveData_;
            if (damag_.getStoppableMoveKoSingle()) {
                for (String move_ : data_.getMovesProtSingleTargetAgainstKo()) {
                    affectedByMoves_.add(move_);
                }
            }
        }
        if (moveData_ instanceof StatusMoveData) {
            for (String move_ : data_.getMovesProtAgainstStatusMoves()) {
                affectedByMoves_.add(move_);
            }
            StatusMoveData stMove_ = (StatusMoveData) moveData_;
            if (stMove_.getThievableMove()) {
                for (String move_ : getMovesThieve()) {
                    affectedByMoves_.add(move_);
                }
            }
            if (stMove_.getCounterableMove()) {
                for (String move_ : getMovesCounter()) {
                    affectedByMoves_.add(move_);
                }
            }
        }
        affectedByMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        affectedByMoves_.removeDuplicates();
        affectedByMoves = affectedByMoves_;
        Numbers<Integer> effects_ = new Numbers<Integer>();
        int nbEffects_ = moveData_.nbEffets();
        for (int i = CustList.FIRST_INDEX; i < nbEffects_; i++) {
            effects_.add(i);
        }
        effects = effects_;
        NatCmpTreeMap<LgInt, Rate> repeatRoundLaw_;
        repeatRoundLaw_ = new NatCmpTreeMap<LgInt, Rate>();
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
        deletedStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        deletedStatus = deletedStatus_;
        StringList requiredStatus_;
        requiredStatus_ = new StringList();
        for (String s: moveData_.getRequiredStatus()) {
            requiredStatus_.add(s);
        }
        requiredStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        requiredStatus = requiredStatus_;
        TreeMap<String, Numbers<Integer>> secEffectsByItem_;
        secEffectsByItem_ = new TreeMap<String, Numbers<Integer>>(new ComparatorTrStrings(translatedItems_));
        for (String s: moveData_.getSecEffectsByItem().getKeys()) {
            secEffectsByItem_.put(s, moveData_.getSecEffectsByItem().getVal(s));
        }
        secEffectsByItem = secEffectsByItem_;
        NatTreeMap<Short,StringList> movesLevelLearntByPokemon_;
        movesLevelLearntByPokemon_ = new NatTreeMap<Short,StringList>();
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pkData_ = data_.getPokemon(p);
            for (LevelMove l: pkData_.getLevMoves()) {
                if (!StringList.quickEq(l.getMove(), name)) {
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
            v.sortElts(new ComparatorTrStrings(translatedPokemon_));
        }
        movesLevelLearntByPokemon = movesLevelLearntByPokemon_;
        StringList movesTmLearntByPokemon_;
        movesTmLearntByPokemon_ = new StringList();
        Numbers<Short> tms_ = data_.getTmByMove(name_);
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
            movesTmLearntByPokemon_.sortElts(new ComparatorTrStrings(translatedPokemon_));
        }
        movesTmLearntByPokemon = movesTmLearntByPokemon_;
        StringList movesHmLearntByPokemon_;
        movesHmLearntByPokemon_ = new StringList();
        Numbers<Short> hms_ = data_.getHmByMove(name_);
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
            movesHmLearntByPokemon_.sortElts(new ComparatorTrStrings(translatedPokemon_));
        }
        movesHmLearntByPokemon = movesHmLearntByPokemon_;
        StringList movesMtLearntByPokemon_;
        movesMtLearntByPokemon_ = new StringList();
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pkData_ = data_.getPokemon(p);
            if (pkData_.getMoveTutors().containsObj(name_)) {
                movesMtLearntByPokemon_.add(p);
            }
        }
        movesMtLearntByPokemon_.sortElts(new ComparatorTrStrings(translatedPokemon_));
        movesMtLearntByPokemon = movesMtLearntByPokemon_;
    }

    @Accessible
    private String clickMoves() {
        getForms().put(MOVES_SET, new StringList());
        return MOVES;
    }

    private StringList getMovesThieve() {
        DataBase data_ = (DataBase) getDataBase();
        StringList moves_ = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData fAttCible_ = data_.getMove(m);
            int nbEffetsCible_=fAttCible_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffetsCible_;i++){
                Effect effetLoc_=fAttCible_.getEffet(i);
                if(!(effetLoc_ instanceof EffectSwitchPointView)){
                    continue;
                }
                EffectSwitchPointView effetChgtPointVueAttLoc_=(EffectSwitchPointView)effetLoc_;
                if (effetChgtPointVueAttLoc_.getPointViewChangement() != PointViewChangementType.THIEF_BONUSES) {
                    continue;
                }
                moves_.add(m);
            }
        }
        return moves_;
    }

    private StringList getMovesCounter() {
        DataBase data_ = (DataBase) getDataBase();
        StringList moves_ = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData fAttCible_ = data_.getMove(m);
            int nbEffetsCible_=fAttCible_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffetsCible_;i++){
                Effect effetLoc_=fAttCible_.getEffet(i);
                if(!(effetLoc_ instanceof EffectSwitchPointView)){
                    continue;
                }
                EffectSwitchPointView effetChgtPointVueAttLoc_=(EffectSwitchPointView)effetLoc_;
                if (effetChgtPointVueAttLoc_.getPointViewChangement() != PointViewChangementType.MIRROR_AGAINST_THROWER) {
                    continue;
                }
                moves_.add(m);
            }
        }
        return moves_;
    }

    @Accessible
    private boolean typesDependOnWeatherAndItem() {
        String name_ = (String) getForms().getVal(MOVE);
        DataBase data_ = (DataBase) getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        if (!moveData_.getTypesByOwnedItem().isEmpty()) {
            if (!moveData_.getTypesByWeather().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean typesDependOnlyOnItem() {
        String name_ = (String) getForms().getVal(MOVE);
        DataBase data_ = (DataBase) getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        if (!moveData_.getTypesByOwnedItem().isEmpty()) {
            if (moveData_.getTypesByWeather().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean typesDependOnlyOnWeather() {
        String name_ = (String) getForms().getVal(MOVE);
        DataBase data_ = (DataBase) getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        if (moveData_.getTypesByOwnedItem().isEmpty()) {
            if (!moveData_.getTypesByWeather().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean isDamagingMove() {
        String name_ = (String) getForms().getVal(MOVE);
        DataBase data_ = (DataBase) getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return moveData_ instanceof DamagingMoveData;
    }

    @Accessible
    private boolean isDamagingDirectMove() {
        String name_ = (String) getForms().getVal(MOVE);
        DataBase data_ = (DataBase) getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        DamagingMoveData damaging_ = (DamagingMoveData) moveData_;
        return damaging_.isDirect();
    }

    @Accessible
    private boolean isZeroPriority() {
        return priority == 0;
    }

    @Accessible
    private boolean isConstAccuracy() {
        return Rate.isValid(accuracy);
    }

    @Accessible
    private boolean isZeroPrepaRound() {
        return nbPrepaRound == 0;
    }

    @Accessible
    private boolean isBeforePrimaryEffect(int _long) {
        String name_ = (String) getForms().getVal(MOVE);
        DataBase data_ = (DataBase) getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return _long < moveData_.indexOfPrimaryEffect();
    }

    @Accessible
    private boolean isPrimaryEffect(int _long) {
        String name_ = (String) getForms().getVal(MOVE);
        DataBase data_ = (DataBase) getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return _long == moveData_.indexOfPrimaryEffect();
    }

    @Accessible
    private boolean isAfterPrimaryEffect(int _long) {
        String name_ = (String) getForms().getVal(MOVE);
        DataBase data_ = (DataBase) getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return _long > moveData_.indexOfPrimaryEffect();
    }

    @Accessible
    private boolean isEndRoundEffect(int _long) {
        String name_ = (String) getForms().getVal(MOVE);
        DataBase data_ = (DataBase) getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        return moveData_.getEffet(_long) instanceof EffectEndRound;
    }

    @Accessible
    private boolean isRepeatedRound() {
        return !repeatRoundLaw.isEmpty();
    }

    @Accessible
    private boolean switchAfterUsingMove() {
        return switchType == SwitchType.LANCEUR;
    }

    @Accessible
    private String getTrAchieveDisappearedPkUsingMove(Long _index) {
        String move_ = achieveDisappearedPkUsingMove.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }

    @Accessible
    private String clickRequiredStatus(Long _index) {
        String key_ = getRequiredStatusKey(_index);
        getForms().put(STATUS, key_);
        return STATUS;
    }

    @Accessible
    private String getRequiredStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String key_ = getRequiredStatusKey(_index);
        return translatedStatus_.getVal(key_);
    }

    private String getRequiredStatusKey(Long _index) {
        return requiredStatus.get(_index.intValue());
    }

    @Accessible
    private String clickDeletedStatus(Long _index) {
        String key_ = getDeletedStatusKey(_index);
        getForms().put(STATUS, key_);
        return STATUS;
    }

    @Accessible
    private String getDeletedStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String key_ = getDeletedStatusKey(_index);
        return translatedStatus_.getVal(key_);
    }

    private String getDeletedStatusKey(Long _index) {
        return deletedStatus.get(_index.intValue());
    }

    @Accessible
    private boolean isItem(Long _index) {
        return !typesByOwnedItems.getKey(_index.intValue()).isEmpty();
    }

    @Accessible
    private String clickTypesByOwnedItems(Long _index) {
        String item_ = typesByOwnedItems.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        getForms().put(ITEM, item_);
        Item it_ = data_.getItem(item_);
        if (it_ instanceof Ball) {
            return BALL;
        }
        if (it_ instanceof Berry) {
            return BERRY;
        }
        if (it_ instanceof Boost) {
            return BOOST;
        }
        if (it_ instanceof EvolvingItem) {
            return EVOLVINGITEM;
        }
        if (it_ instanceof EvolvingStone) {
            return EVOLVINGSTONE;
        }
        if (it_ instanceof Fossil) {
            return FOSSIL;
        }
        if (it_ instanceof HealingHpStatus) {
            return HEALINGHPSTATUS;
        }
        if (it_ instanceof HealingStatus) {
            return HEALINGSTATUS;
        }
        if (it_ instanceof HealingHp) {
            return HEALINGHP;
        }
        if (it_ instanceof HealingPp) {
            return HEALINGPP;
        }
        if (it_ instanceof HealingItem) {
            return HEALINGITEM;
        }
        if (it_ instanceof ItemForBattle) {
            return ITEMFORBATTLE;
        }
        if (it_ instanceof Repel) {
            return REPEL;
        }
        if (it_ instanceof SellingItem) {
            return SELLINGITEM;
        }
        return ITEM;
    }

    @Accessible
    private String getTrTypesByOwnedItems(Long _index) {
        String item_ = typesByOwnedItems.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(item_);
    }

    @Accessible
    private boolean isWeather(Long _index) {
        return !typesByWeathers.getKey(_index.intValue()).isEmpty();
    }

    @Accessible
    private String clickTypesByWeathers(Long _index) {
        String item_ = typesByWeathers.getKey(_index.intValue());
        getForms().put(MOVE, item_);
        return MOVE;
    }

    @Accessible
    private String getTrTypesByWeathers(Long _index) {
        String item_ = typesByWeathers.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(item_);
    }

    @Accessible
    private String clickItemSecEffect(Long _index) {
        String item_ = getItemSecEffect(_index);
        DataBase data_ = (DataBase) getDataBase();
        getForms().put(ITEM, item_);
        Item it_ = data_.getItem(item_);
        if (it_ instanceof Ball) {
            return BALL;
        }
        if (it_ instanceof Berry) {
            return BERRY;
        }
        if (it_ instanceof Boost) {
            return BOOST;
        }
        if (it_ instanceof EvolvingItem) {
            return EVOLVINGITEM;
        }
        if (it_ instanceof EvolvingStone) {
            return EVOLVINGSTONE;
        }
        if (it_ instanceof Fossil) {
            return FOSSIL;
        }
        if (it_ instanceof HealingHpStatus) {
            return HEALINGHPSTATUS;
        }
        if (it_ instanceof HealingStatus) {
            return HEALINGSTATUS;
        }
        if (it_ instanceof HealingHp) {
            return HEALINGHP;
        }
        if (it_ instanceof HealingPp) {
            return HEALINGPP;
        }
        if (it_ instanceof HealingItem) {
            return HEALINGITEM;
        }
        if (it_ instanceof ItemForBattle) {
            return ITEMFORBATTLE;
        }
        if (it_ instanceof Repel) {
            return REPEL;
        }
        if (it_ instanceof SellingItem) {
            return SELLINGITEM;
        }
        return ITEM;
    }

    @Accessible
    private String translateItemSecEffect(Long _index) {
        String it_ = getItemSecEffect(_index);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(it_);
    }

    private String getItemSecEffect(Long _index) {
        String it_ = secEffectsByItem.getKey(_index.intValue());
        return it_;
    }

    @Accessible
    private String clickAbility(Long _index) {
        String key_ = abilities.get(_index.intValue());
        getForms().put(ABILITY, key_);
        return ABILITY;
    }

    @Accessible
    private String getTrAbility(Long _index) {
        String ab_ = abilities.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(ab_);
    }

    @Accessible
    private String clickItem(Long _index) {
        String key_ = items.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        getForms().put(ITEM, key_);
        Item it_ = data_.getItem(key_);
        if (it_ instanceof Ball) {
            return BALL;
        }
        if (it_ instanceof Berry) {
            return BERRY;
        }
        if (it_ instanceof Boost) {
            return BOOST;
        }
        if (it_ instanceof EvolvingItem) {
            return EVOLVINGITEM;
        }
        if (it_ instanceof EvolvingStone) {
            return EVOLVINGSTONE;
        }
        if (it_ instanceof Fossil) {
            return FOSSIL;
        }
        if (it_ instanceof HealingHpStatus) {
            return HEALINGHPSTATUS;
        }
        if (it_ instanceof HealingStatus) {
            return HEALINGSTATUS;
        }
        if (it_ instanceof HealingHp) {
            return HEALINGHP;
        }
        if (it_ instanceof HealingPp) {
            return HEALINGPP;
        }
        if (it_ instanceof HealingItem) {
            return HEALINGITEM;
        }
        if (it_ instanceof ItemForBattle) {
            return ITEMFORBATTLE;
        }
        if (it_ instanceof Repel) {
            return REPEL;
        }
        if (it_ instanceof SellingItem) {
            return SELLINGITEM;
        }
        return ITEM;
    }

    @Accessible
    private String getTrItem(Long _index) {
        String ab_ = items.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedAbilities_.getVal(ab_);
    }

    @Accessible
    private String clickMove(Long _index) {
        String key_ = affectedByMoves.get(_index.intValue());
        getForms().put(MOVE, key_);
        return MOVE;
    }

    @Accessible
    private String getTrMove(Long _index) {
        String ab_ = affectedByMoves.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedAbilities_.getVal(ab_);
    }

    @Accessible
    private String clickPokemon(Long _indexLevel, Long _indexPk) {
        StringList pks_ = movesLevelLearntByPokemon.getValue(_indexLevel.intValue());
        String pk_ = pks_.get(_indexPk.intValue());
        getForms().put(PK, pk_);
        return POKEMON;
    }

    @Accessible
    private String getTrPokemon(Long _indexLevel, Long _indexPk) {
        StringList pks_ = movesLevelLearntByPokemon.getValue(_indexLevel.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        String pk_ = pks_.get(_indexPk.intValue());
        return translatedPokemon_.getVal(pk_);
    }

    @Accessible
    private String clickPokemonTm(Long _indexPk) {
        String pk_ = movesTmLearntByPokemon.get(_indexPk.intValue());
        getForms().put(PK, pk_);
        return POKEMON;
    }

    @Accessible
    private String getTrPokemonTm(Long _indexPk) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        String pk_ = movesTmLearntByPokemon.get(_indexPk.intValue());
        return translatedPokemon_.getVal(pk_);
    }

    @Accessible
    private String clickPokemonHm(Long _indexPk) {
        String pk_ = movesHmLearntByPokemon.get(_indexPk.intValue());
        getForms().put(PK, pk_);
        return POKEMON;
    }

    @Accessible
    private String getTrPokemonHm(Long _indexPk) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        String pk_ = movesHmLearntByPokemon.get(_indexPk.intValue());
        return translatedPokemon_.getVal(pk_);
    }

    @Accessible
    private String clickPokemonMt(Long _indexPk) {
        String pk_ = movesMtLearntByPokemon.get(_indexPk.intValue());
        getForms().put(PK, pk_);
        return POKEMON;
    }

    @Accessible
    private String getTrPokemonMt(Long _indexPk) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        String pk_ = movesMtLearntByPokemon.get(_indexPk.intValue());
        return translatedPokemon_.getVal(pk_);
    }

    @Accessible
    private boolean canBeLearnt() {
        if (!movesLevelLearntByPokemon.isEmpty()) {
            return true;
        }
        if (!movesTmLearntByPokemon.isEmpty()) {
            return true;
        }
        if (!movesHmLearntByPokemon.isEmpty()) {
            return true;
        }
        if (!movesMtLearntByPokemon.isEmpty()) {
            return true;
        }
        return false;
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

    @Accessible
    private String getPage(Long _long) {
        String name_ = (String) getForms().getVal(MOVE);
        DataBase data_ = (DataBase) getDataBase();
        MoveData moveData_ = data_.getMove(name_);
        Effect eff_ = moveData_.getEffet(_long.intValue());
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
        if (eff_ instanceof EffectCounterAttack) {
            return PAGE_COUNTERATTACK;
        }
        if (eff_ instanceof EffectProtection) {
            return PAGE_PROTECTION;
        }
        if (eff_ instanceof EffectAccuracy) {
            return PAGE_ACCURACY;
        }
        if (eff_ instanceof EffectCopyFighter) {
            return PAGE_COPYFIGHTER;
        }
        if (eff_ instanceof EffectProtectFromTypes) {
            return PAGE_PROTECTFROMTYPES;
        }
        if (eff_ instanceof EffectUnprotectFromTypes) {
            return PAGE_UNPROTECTFROMTYPES;
        }
        if (eff_ instanceof EffectAlly) {
            return PAGE_ALLY;
        }
        if (eff_ instanceof EffectBatonPass) {
            return PAGE_BATONPASS;
        }
        if (eff_ instanceof EffectClone) {
            return PAGE_CLONE;
        }
        if (eff_ instanceof EffectCommonStatistics) {
            return PAGE_COMMONSTATISTICS;
        }
        if (eff_ instanceof EffectOrder) {
            return PAGE_ORDER;
        }
        if (eff_ instanceof EffectRestriction) {
            return PAGE_RESTRICTION;
        }
        if (eff_ instanceof EffectSwitchAbilities) {
            return PAGE_SWITCHABILITIES;
        }
        if (eff_ instanceof EffectSwitchItems) {
            return PAGE_SWITCHITEMS;
        }
        if (eff_ instanceof EffectSwitchTypes) {
            return PAGE_SWITCHTYPES;
        }
        if (eff_ instanceof EffectSwitchPointView) {
            return PAGE_SWITCHPOINTVIEW;
        }
        if (eff_ instanceof EffectRemainedHpRate) {
            return PAGE_REMAINEDHPRATE;
        }
        if (eff_ instanceof EffectMultUsedMovePower) {
            return PAGE_MULTUSEDMOVEPOWER;
        }
        if (eff_ instanceof EffectMultSufferedMovePower) {
            return PAGE_MULTSUFFEREDMOVEPOWER;
        }
        if (eff_ instanceof EffectSwitchPosition) {
            return PAGE_SWITCHPOSITION;
        }
        if (eff_ instanceof EffectVarPP) {
            return PAGE_VARPP;
        }
        if (eff_ instanceof EffectWinMoney) {
            return PAGE_WINMONEY;
        }
        return DataBase.EMPTY_STRING;
    }
}
