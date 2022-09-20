package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.enums.UsefulValueLaw;
import aiki.game.fight.util.MoveTarget;
import aiki.game.fight.util.StatisticsDamageMove;
import aiki.game.params.Difficulty;
import aiki.util.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.AbsMap;
import code.util.*;

import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

final class FightArtificialIntelligence {

    private FightArtificialIntelligence() {
    }

    static void choiceArtificialIntelligence(Fight _fight, Difficulty _diff,DataBase _import) {
        choiceFoeArtificialIntelligence(_fight,_diff, _import);
        if (_fight.getFightType() == FightType.TMP_TRAINER) {
            choiceAllyArtificialIntelligence(_fight, _diff, _import);
        }
    }

    static void choiceAllyArtificialIntelligence(Fight _fight, Difficulty _diff,DataBase _import) {
        _fight.setAllyChoice(new MoveTargets());
        TeamPosition userPk_ = new TeamPosition();
        TeamPositionList partners_ = FightOrder.notKoFrontFightersBelongingToUser(_fight,false);
        if (partners_.isEmpty()) {
            return;
        }
        TeamPosition partner_ = partners_.first();
        StringMap<TargetCoordsList> possibleChoicesAlly_ = possibleChoicesAlly(_fight, _import, partner_);
        StringMap<TargetCoordssRate> remoteHp_;
        CustList<TeamPosition> notKoFront_ = FightOrder.notKoFrontFightersBelongingToUser(_fight, true);
        if (!notKoFront_.isEmpty()) {
            TeamPosition f_ = notKoFront_.first();
            remoteHp_ = remainingHpFoe(_fight, _diff, _import, f_);
            userPk_ = f_;
        } else {
            remoteHp_ = new StringMap<TargetCoordssRate>();
        }
        for (String m: remoteHp_.getKeys()) {
            //m move of player pk
            TargetCoordssRate remoteHpLoc_ = remoteHp_.getVal(m);
            PlayerAllyFoeTarget fighters_ = splitFoeFightersInTwoList(userPk_, partner_, remoteHpLoc_);
            if (fighters_.getKoFoeFighters().size() == _fight.getMult()) {
                continue;
            }
            if (!fighters_.getKoFoeFighters().isEmpty() && !fighters_.getNotKoFoeFighters().isEmpty()) {
                bothAmongFoeFighters(_fight, _diff, _import, possibleChoicesAlly_, m, fighters_);
            } else if (!chosenFoe(_fight, _diff, _import, possibleChoicesAlly_, m, remoteHpLoc_, fighters_) && !possibleChoicesAlly_.isEmpty()) {
                String m2_ = possibleChoicesAlly_.firstKey();
                CustList<TargetCoords> v2_ = possibleChoicesAlly_.firstValue();
                feedChoices(_fight, m, remoteHpLoc_, m2_, v2_.first());
            }
        }
        if (notKoFront_.isEmpty()) {
            //no pk user or pk user without damage moves
            choiceAllyArtificialIntelligenceWithoutUser(_fight, partner_, possibleChoicesAlly_, _diff, _import);
        }
    }

    private static boolean chosenFoe(Fight _fight, Difficulty _diff, DataBase _import, StringMap<TargetCoordsList> _possibleChoicesAlly, String _m, TargetCoordssRate _remoteHpLoc, PlayerAllyFoeTarget _fighters) {
        boolean chosen_ = false;
        for (String m2_: _possibleChoicesAlly.getKeys()) {
            if (usableMove(_fight, _fighters.getPartner(), _fighters.getUserPk(), false, m2_, _diff, _import)) {
                // notKoFoeFighters_.size() == 0
                TargetCoordsList kos_ = koFoeFighters(_fight, _fighters.getPartner(), m2_, _possibleChoicesAlly, _diff, _import);
                if (kos_.size() == _fight.getMult()) {
                    feedChoices(_fight, _m, _remoteHpLoc, m2_, kos_.first());
                    chosen_ = true;
                    break;
                }
            }
        }
        if (chosen_) {
            return true;
        }
        //boolean chosen_ = false;
        for (String m2_: _possibleChoicesAlly.getKeys()) {
            DamagingMoveData damageMove_ = (DamagingMoveData) _import.getMove(m2_);
            if (damageMove_.getTargetChoice().isWithChoice()) {
                // notKoFoeFighters_.size() == 0
                TargetCoordsList kos_ = koFoeFighters(_fight, _fighters.getPartner(), m2_, _possibleChoicesAlly, _diff, _import);
                if (!kos_.isEmpty()) {
                    feedChoices(_fight, _m, _remoteHpLoc, m2_, kos_.first());
                    chosen_ = true;
                    break;
                }
            }
        }
        return chosen_;
    }

    private static void feedChoices(Fight _fight, String _m, TargetCoordssRate _remoteHpLoc, String _m2, TargetCoords _ko) {
        for (TargetCoords t: _remoteHpLoc.getKeys()) {
            _fight.getAllyChoice().put(new MoveTarget(_m,t), new MoveTarget(_m2, _ko));
        }
    }

    private static void bothAmongFoeFighters(Fight _fight, Difficulty _diff, DataBase _import, StringMap<TargetCoordsList> _possibleChoicesAlly, String _m, PlayerAllyFoeTarget _fighters) {
        for (String m2_: _possibleChoicesAlly.getKeys()) {
            if (!usableMove(_fight, _fighters.getPartner(), _fighters.getUserPk(), true, m2_, _diff, _import)) {
                continue;
            }
            // notKoFoeFighters_.size() == 1
            // koFoeFighters_.isEmpty() == 1
            Bytes foeFighers_ = _fight.getFoeTeam().fightersAtCurrentPlace(_fighters.getNotKoFoeFighters().first().getPosition());
            for (byte f: foeFighers_) {
                TeamPosition c2_ = Fight.toFoeFighter(f);
                if (koFoeFighter(_fight, _fighters.getPartner(), m2_, c2_, _diff, _import)) {
                    _fight.getAllyChoice().put(new MoveTarget(_m, _fighters.getKoFoeFighters().first()), new MoveTarget(m2_, _fighters.getNotKoFoeFighters().first()));
                }
            }
        }
    }

    private static PlayerAllyFoeTarget splitFoeFightersInTwoList(TeamPosition _userPk, TeamPosition _partner, TargetCoordssRate _remoteHpLoc) {
        TargetCoordsList koFoeFighters_ = new TargetCoordsList();
        TargetCoordsList notKoFoeFighters_ = new TargetCoordsList();
        for (TargetCoords t: _remoteHpLoc.getKeys()) {
            if (_remoteHpLoc.getVal(t).isZero()) {
                koFoeFighters_.add(t);
            } else {
                notKoFoeFighters_.add(t);
            }
        }
        return new PlayerAllyFoeTarget(_userPk,_partner,koFoeFighters_,notKoFoeFighters_);
    }

    private static StringMap<TargetCoordssRate> remainingHpFoe(Fight _fight, Difficulty _diff, DataBase _import, TeamPosition _f) {
        StringMap<TargetCoordssRate> remoteHp_;
        remoteHp_ = new StringMap<TargetCoordssRate>();
        for (String m: FightFacade.allowedMovesNotEmpty(_fight, _f, _import)) {
            MoveData fAtt_ = _import.getMove(m);
            if (!(fAtt_ instanceof DamagingMoveData)) {
                continue;
            }
            TargetCoordssRate remainingTargetHp_ = remainingFoeTargetHp(_fight, _f, m, _diff, _import);
            remoteHp_.put(m, remainingTargetHp_);
            for (TargetCoords t: remainingTargetHp_.getKeys()) {
                Bytes foeFighers_ = _fight.getFoeTeam().fightersAtCurrentPlace(t.getPosition());
                for (byte f2_: foeFighers_) {
                    TeamPosition c2_ = Fight.toFoeFighter(f2_);
                    if (Rate.strLower(remainingTargetHp_.getVal(t), _fight.getFighter(c2_).getRemainingHp())) {
                        _fight.getAllyChoice().put(new MoveTarget(m,t), new MoveTarget(DataBase.EMPTY_STRING, new TargetCoords(Fighter.BACK,Fighter.BACK)));
                    }
                }
            }
        }
        return remoteHp_;
    }

    private static StringMap<TargetCoordsList> possibleChoicesAlly(Fight _fight, DataBase _import, TeamPosition _partner) {
        StringMap<TargetCoordsList> possibleChoicesAlly_ = new StringMap<TargetCoordsList>();
        for (String m: FightFacade.allowedMovesNotEmpty(_fight, _partner, _import)) {
            MoveData fAtt_ = _import.getMove(m);
            if (!(fAtt_ instanceof DamagingMoveData)) {
                continue;
            }
            TargetCoordsList list_ = new TargetCoordsList();
            int mult_ = _fight.getMult();
            for (short f = IndexConstants.FIRST_INDEX; f < mult_; f++) {
                if (_fight.getFoeTeam().fightersAtCurrentPlace(f).isEmpty()) {
                    continue;
                }
                list_.add(TargetCoords.toFoeTarget(f));
            }
            possibleChoicesAlly_.put(m, list_);
        }
        return possibleChoicesAlly_;
    }

    static TargetCoordssRate remainingFoeTargetHp(Fight _fight, TeamPosition _thrower, String _move, Difficulty _diff, DataBase _import) {
        MoveData fAtt_ = _import.getMove(_move);
        int index_ = fAtt_.indexOfPrimaryEffect();
        StringList types_ = FightMoves.moveTypes(_fight,_thrower, _move, _import);
        TargetCoordssRate remoteHpLoc_ = new TargetCoordssRate();
        int mult_ = _fight.getMult();
        _fight.setSending(false);
        for(byte f = IndexConstants.FIRST_INDEX; f < mult_; f++){
            Bytes fighters_ = _fight.getFoeTeam().fightersAtCurrentPlace(f);
            if (fighters_.isEmpty()) {
                continue;
            }
            TeamPosition f_ = Fight.toFoeFighter(fighters_.first());
            Fighter foe_ = _fight.getFighter(f_);
            Rate remoteHpFoe_ = foe_.getRemainingHp();
            short pos_ = foe_.getGroundPlace();
            TargetCoords t_ = TargetCoords.toFoeTarget(pos_);
            if (!reachable(_fight, _thrower, f_, _diff, _import, (DamagingMoveData) fAtt_) || Rate.strLower(FightSuccess.accuracy(_fight, _thrower, f_, _move, _import), DataBase.determinatedRate()) || !FightSuccess.successfulMove(_fight, _thrower, f_, _move, index_, false, _import).isSuccessful() || inaffectFighter(_fight, _thrower, f_, _import, types_)) {
                remoteHpLoc_.put(t_, remoteHpFoe_);
            } else {
                AbsMap<UsefulValueLaw, Rate> statistiquesLoc_ = FightEffects.calculateMinMaxAvgVarForDamage(_fight, _thrower, f_, _move, _diff, _import);
                Rate delta_ = positive(Rate.minus(remoteHpFoe_, statistiquesLoc_.getVal(UsefulValueLaw.MINI)));
                remoteHpLoc_.put(t_, delta_);
            }
        }
        return remoteHpLoc_;
    }

    static TeamPositionsRate remainingPartnerTargetHp(Fight _fight, TeamPosition _thrower, String _move, Difficulty _diff, DataBase _import) {
        TeamPositionsRate remoteHpLoc_ = new TeamPositionsRate();
        for(TeamPosition f: FightOrder.fighters(_fight, Fight.CST_PLAYER)){
            Fighter partner_ = _fight.getFighter(f);
            Rate remoteHpPartner_ = partner_.getRemainingHp();
//            SortableCustList<Rate> rates_ = new SortableCustList<Rate>();
            Rate acc_ = FightSuccess.accuracy(_fight, _thrower, f, _move, _import);
//            rates_.add(acc_);
//            rates_.add(Rate.zero());
//            rates_.sort(new NaturalComparator<Rate>() {
//                @Override
//                public int compare(Rate _o1, Rate _o2) {
//                    return _o1.compareTo(_o2);
//                }
//            });
//            rates_.sort();
            if (acc_.isZeroOrLt() || untouchablePartners(_fight, _thrower, _move, _diff, _import).containsObj(f)) {
                remoteHpLoc_.put(f, remoteHpPartner_);
                continue;
            }
            AbsMap<UsefulValueLaw, Rate> statistiquesLoc_ = FightEffects.calculateMinMaxAvgVarForDamage(_fight, _thrower, f, _move, _diff, _import);
            Rate delta_ = positive(Rate.minus(remoteHpPartner_, statistiquesLoc_.getVal(UsefulValueLaw.MAXI)));
            remoteHpLoc_.put(f, delta_);
        }
        return remoteHpLoc_;
    }

    private static Rate positive(Rate _d) {
        Rate delta_ = _d;
        if (!delta_.isZeroOrGt()) {
            delta_ = Rate.zero();
        }
        return delta_;
    }

    static boolean usableMove(Fight _fight, TeamPosition _thrower, TeamPosition _target, boolean _chooseMove, String _move, Difficulty _diff, DataBase _import) {
        DamagingMoveData damageMove_ = (DamagingMoveData) _import.getMove(_move);
        boolean usableMove_;
        if (damageMove_.getTargetChoice().isWithChoice()) {
            usableMove_ = _chooseMove;
        } else {
            usableMove_ = untouchablePartners(_fight, _thrower, _move, _diff, _import).containsObj(_target);
        }
        return usableMove_;
    }

    static boolean reachable(Fight _fight, TeamPosition _thrower, TeamPosition _target, Difficulty _diff, DataBase _import, DamagingMoveData _move) {
        int index_ = _move.indexOfPrimaryEffect();
        if (!_move.getTargetChoice().isWithChoice()) {
            EffectDamage effetDeg_=(EffectDamage) _move.getEffet(index_);
            return FightOrder.targetsEffect(_fight, _thrower, effetDeg_, _diff, _import).containsObj(_target);
        }
        if (_move.getTargetChoice() == TargetChoice.ADJ_UNIQ) {
            return FightOrder.closestFigthers(_fight,_thrower,_diff).containsObj(_target);
        }
        return true;
    }

    static TeamPositionList untouchablePartners(Fight _fight, TeamPosition _thrower, String _move, Difficulty _diff, DataBase _import) {
        MoveData damageMove_ = _import.getMove(_move);
        if (damageMove_.getTargetChoice().isWithChoice() || damageMove_.getTargetChoice() == TargetChoice.ADJ_ADV || damageMove_.getTargetChoice() == TargetChoice.TOUS_ADV) {
            return FightOrder.fighters(_fight, Fight.CST_PLAYER);
        }
        TeamPositionList untouchablePartners_ = new TeamPositionList();
        int index_ = damageMove_.indexOfPrimaryEffect();
        StringList types_ = FightMoves.moveTypes(_fight,_thrower, _move, _import);
        int mult_ = _fight.getMult();
        for (byte f = IndexConstants.FIRST_INDEX; f < mult_; f++) {
            Bytes places_ = _fight.getUserTeam().fightersAtCurrentPlace(f);
            if (places_.isEmpty()) {
                continue;
            }
            byte pos_ = places_.first();
            TeamPosition f_ = Fight.toUserFighter(pos_);
            if (f == _fight.getFighter(_thrower).getGroundPlace() || !reachable(_fight, _thrower, f_, _diff, _import, (DamagingMoveData) damageMove_) || !FightSuccess.successfulMove(_fight, _thrower, f_, _move, index_, false, _import).isSuccessful() || inaffectFighter(_fight, _thrower, f_, _import, types_)) {
                untouchablePartners_.add(f_);
            }
        }
        return untouchablePartners_;
    }

    static TargetCoordsList koFoeFighters(Fight _fight, TeamPosition _thrower, String _move, StringMap<TargetCoordsList> _possibleChoicesAlly, Difficulty _diff, DataBase _import) {
        TargetCoordsList kos_ = new TargetCoordsList();
        for (TargetCoords t: _possibleChoicesAlly.getVal(_move)) {
            Bytes foeFighers_ = _fight.getFoeTeam().fightersAtCurrentPlace(t.getPosition());
            for (byte p:foeFighers_) {
                TeamPosition c2_ = Fight.toFoeFighter(p);
                if (koFoeFighter(_fight, _thrower, _move, c2_, _diff, _import)) {
                    kos_.add(t);
                }
            }
        }
        return kos_;
    }

    static boolean koFoeFighter(Fight _fight, TeamPosition _thrower, String _move, TeamPosition _foe, Difficulty _diff, DataBase _import) {
        DamagingMoveData damageMove_ = (DamagingMoveData) _import.getMove(_move);
        Fighter foe_ = _fight.getFighter(_foe);
        Rate remoteHpFoe_ = foe_.getRemainingHp();
        int nbEffets_=damageMove_.nbEffets();
        StringList types_ = FightMoves.moveTypes(_fight,_thrower, _move, _import);
        for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_=damageMove_.getEffet(i);
            if (!(effet_ instanceof EffectDamage) || Rate.strLower(FightSuccess.accuracy(_fight, _thrower, _foe, _move, _import), DataBase.determinatedRate()) || !FightSuccess.successfulMove(_fight, _thrower, _foe, _move, i, false, _import).isSuccessful() || inaffectFighter(_fight, _thrower, _foe, _import, types_)) {
                continue;
            }
            AbsMap<UsefulValueLaw,Rate> statistiquesLoc_=FightEffects.calculateMinMaxAvgVarForDamage(_fight,_thrower,_foe,_move,_diff,_import);
            if (Rate.lowerEq(remoteHpFoe_, statistiquesLoc_.getVal(UsefulValueLaw.MINI))) {
                return true;
            }
        }
        return false;
    }

    private static boolean inaffectFighter(Fight _fight, TeamPosition _thrower, TeamPosition _target, DataBase _import, StringList _types) {
        boolean affect_ = true;
        for (String t: _types) {
            if (!FightSuccess.rateEffAgainstTarget(_fight, _thrower, _target, t, _import).isZero()) {
                continue;
            }
            affect_ = false;
        }
        return !affect_;
    }

    static void choiceFoeArtificialIntelligence(Fight _fight, Difficulty _diff,DataBase _import){
        if(_fight.getFightType().isWild()){
            StringList attaquesUtilisables_= FightFacade.allowedMovesNotEmpty(_fight,Fight.toFoeFighter((byte) 0),_import);
            MonteCarloString loi_ = new MonteCarloString();
            for(String e:attaquesUtilisables_){
                loi_.addQuickEvent(e,DataBase.defElementaryEvent());
            }
            LgInt maxRd_ = _import.getMaxRd();
            String attaqueUtilisee_=loi_.editNumber(maxRd_,_import.getGenerator());
            setFirstChosenMove(_fight, Fight.toFoeFighter((byte) 0), attaqueUtilisee_, _diff, _import);
            return;
        }
        for(TeamPosition c:FightOrder.frontFighters(_fight)){
            if(NumberUtil.eq(c.getTeam(),Fight.CST_PLAYER)){
                continue;
            }
            StringList attaquesUtilisables_=FightRules.allowedMoves(_fight,c,_import);
            if(attaquesUtilisables_.isEmpty()){
                String move_ = _import.getDefMove();
                setFirstChosenMove(_fight, c, move_, _diff, _import);
                setBatonPass(_fight, c, move_, _import);
            } else {
                StringMap<DamagingMoveData> attaquesOffUtilisables_ = attaquesOffUtilisables(_import, attaquesUtilisables_);
                if (attaquesOffUtilisables_.isEmpty()) {
                    attaquesUtilisables_.sort();
                    String move_ = attaquesUtilisables_.first();
                    setFirstChosenMove(_fight, c, move_, _diff, _import);
                    setBatonPass(_fight, c, move_, _import);
                } else {
                    listeStats(_fight, _diff, _import, c, attaquesOffUtilisables_);
                }
            }
        }
    }

    private static void listeStats(Fight _fight, Difficulty _diff, DataBase _import, TeamPosition _c, StringMap<DamagingMoveData> _attaquesOffUtilisables) {
        CustList<StatisticsDamageMove> liste_=new CustList<StatisticsDamageMove>();
        for(EntryCust<String, DamagingMoveData> m: _attaquesOffUtilisables.entryList()){
            AbsMap<UsefulValueLaw, Rate> statistiques_ = statistiques(_fight, _diff, _import, _c, m);
            StatisticsDamageMove elt_;
            elt_ = new StatisticsDamageMove(statistiques_, m.getKey());
            liste_.add(elt_);
        }
//            liste_.sort(new Comparator<Pair<Map<UsefulValueLaw,Rate>,String>>() {
//                @Override
//                public int compare(Pair<Map<UsefulValueLaw,Rate>,String> _o1,
//                        Pair<Map<UsefulValueLaw,Rate>,String> _o2) {
//                    Rate resLoc_ = Rate.minus(_o2.getFirst().getVal(UsefulValueLaw.MINI), _o1.getFirst().getVal(UsefulValueLaw.MINI));
//                    if (!resLoc_.isZero()) {
//                        return (int) resLoc_.getNumerator().signumToLong();
//                    }
//                    resLoc_ = Rate.minus(_o2.getFirst().getVal(UsefulValueLaw.MAXI), _o1.getFirst().getVal(UsefulValueLaw.MAXI));
//                    if (!resLoc_.isZero()) {
//                        return (int) resLoc_.getNumerator().signumToLong();
//                    }
//                    resLoc_ = Rate.minus(_o2.getFirst().getVal(UsefulValueLaw.MOY), _o1.getFirst().getVal(UsefulValueLaw.MOY));
//                    if (!resLoc_.isZero()) {
//                        return (int) resLoc_.getNumerator().signumToLong();
//                    }
//                    resLoc_ = Rate.minus(_o2.getFirst().getVal(UsefulValueLaw.VAR), _o1.getFirst().getVal(UsefulValueLaw.VAR));
//                    if (!resLoc_.isZero()) {
//                        return (int) -resLoc_.getNumerator().signumToLong();
//                    }
//                    return _o2.getSecond().compareTo(_o1.getSecond());
//                }
//            });
        liste_.sortElts(new ComparatorLaws());
        String attaqueUtilisee_=liste_.first().getName();
        setFirstChosenMove(_fight, _c, attaqueUtilisee_, _diff, _import);
    }

    private static AbsMap<UsefulValueLaw, Rate> statistiques(Fight _fight, Difficulty _diff, DataBase _import, TeamPosition _c, EntryCust<String, DamagingMoveData> _m) {
        AbsMap<UsefulValueLaw,Rate> statistiques_=new IdMap<UsefulValueLaw,Rate>();
        statistiques_.put(UsefulValueLaw.MINI,Rate.zero());
        statistiques_.put(UsefulValueLaw.MAXI,Rate.zero());
        statistiques_.put(UsefulValueLaw.MOY,Rate.zero());
        statistiques_.put(UsefulValueLaw.VAR,Rate.zero());
        int mult_ = _fight.getMult();
        for (byte f = IndexConstants.FIRST_INDEX; f < mult_; f++) {
            Bytes places_ = _fight.getUserTeam().fightersAtCurrentPlace(f);
            if (places_.isEmpty()) {
                continue;
            }
            byte pos_ = places_.first();
            TeamPosition f_ = Fight.toUserFighter(pos_);
            if (reachable(_fight, _c, f_, _diff, _import, _m.getValue())) {
                AbsMap<UsefulValueLaw, Rate> statistiquesLoc_ = statistiquesLoc(_fight, _diff, _import, _c, _m, f_);
                statistiques_.getVal(UsefulValueLaw.MINI).addNb(statistiquesLoc_.getVal(UsefulValueLaw.MINI));
                statistiques_.getVal(UsefulValueLaw.MAXI).addNb(statistiquesLoc_.getVal(UsefulValueLaw.MAXI));
                statistiques_.getVal(UsefulValueLaw.MOY).addNb(statistiquesLoc_.getVal(UsefulValueLaw.MOY));
                statistiques_.getVal(UsefulValueLaw.VAR).addNb(statistiquesLoc_.getVal(UsefulValueLaw.VAR));
            }
        }
        return statistiques_;
    }

    private static AbsMap<UsefulValueLaw, Rate> statistiquesLoc(Fight _fight, Difficulty _diff, DataBase _import, TeamPosition _c, EntryCust<String, DamagingMoveData> _m, TeamPosition _f) {
        AbsMap<UsefulValueLaw,Rate> statistiquesLoc_;
        statistiquesLoc_=FightEffects.calculateMinMaxAvgVarForDamage(_fight, _c, _f, _m.getKey(), _diff, _import);
        Rate accuracy_ = FightSuccess.accuracy(_fight, _c, _f, _m.getKey(), _import);
        if (accuracy_.isZero()) {
            statistiquesLoc_.getVal(UsefulValueLaw.MINI).affectZero();
            statistiquesLoc_.getVal(UsefulValueLaw.MAXI).affectZero();
            statistiquesLoc_.getVal(UsefulValueLaw.MOY).affectZero();
            statistiquesLoc_.getVal(UsefulValueLaw.VAR).affectZero();
        } else if (accuracy_.lowerThanOne()) {
            statistiquesLoc_.getVal(UsefulValueLaw.MINI).affectZero();
            Rate avg_ = statistiquesLoc_.getVal(UsefulValueLaw.MOY);
            Rate var_ = statistiquesLoc_.getVal(UsefulValueLaw.VAR);
            Rate varAccuracy_ = Rate.multiply(accuracy_, Rate.minus(Rate.one(), accuracy_));
            var_.affect(ThrowerDamageLaws.varFromIndependantLaws(varAccuracy_, accuracy_, var_, avg_));
            avg_.multiplyBy(accuracy_);
        }
        return statistiquesLoc_;
    }

    private static StringMap<DamagingMoveData> attaquesOffUtilisables(DataBase _import, StringList _attaquesUtilisables) {
        StringMap<DamagingMoveData> attaquesOffUtilisables_=new StringMap<DamagingMoveData>();
        for(String e: _attaquesUtilisables){
            MoveData fAtt_= _import.getMove(e);
            if(!(fAtt_ instanceof DamagingMoveData)){
                continue;
            }
            attaquesOffUtilisables_.addEntry(e, (DamagingMoveData) fAtt_);
        }
        return attaquesOffUtilisables_;
    }

    static void setFirstChosenMove(Fight _fight, TeamPosition _foe, String _move, Difficulty _diff, DataBase _import) {
        MoveData fAtt_=_import.getMove(_move);
        TargetChoice choice_ = fAtt_.getTargetChoice();
        Fighter creature_ = _fight.getFighter(_foe);
        TeamPositionList cibles_;
        if(choice_ == TargetChoice.ALLIE){
            cibles_ = FightOrder.closestFigthersSameTeam(_fight,_foe,_diff);
        }else if (choice_.isWithChoice()){
            cibles_ = FightOrder.closestFoeFighter(_fight, _foe);
        } else {
            cibles_ = new TeamPositionList();
        }
        if (cibles_.isEmpty()) {
            creature_.setFirstChosenMove(_move);
        } else if(choice_ == TargetChoice.ALLIE){
            Fighter cible_ = _fight.getFighter(cibles_.first());
            creature_.setFirstChosenMoveTarget(_move,TargetCoords.toFoeTarget(cible_.getGroundPlace()));
        } else {
            Fighter cible_ = _fight.getFighter(cibles_.first());
            creature_.setFirstChosenMoveTarget(_move, TargetCoords.toUserTarget(cible_.getGroundPlace()));
        }
    }

    static void setBatonPass(Fight _fight, TeamPosition _foe, String _move, DataBase _import) {
        boolean relais_ = hasToForward(_move, _import);
        if(relais_){
            Fighter creature_ = _fight.getFighter(_foe);
            for(TeamPosition c2_:FightOrder.fighters(_fight,Fight.CST_FOE)){
                Fighter partenaire_=_fight.getFighter(c2_);
                if (!partenaire_.estKo() && partenaire_.estArriere()) {
                    creature_.setFirstChosenMove(_move);
                    creature_.setSubstituteForMove(c2_.getPosition());
                    //creature_.setFirstChosenMoveSubstitute(_move, c2_.getPosition());
                    break;
                }
            }
        }
    }

    static void choiceAllyArtificialIntelligenceWithoutUser(Fight _fight, TeamPosition _partner,
            StringMap<TargetCoordsList> _possibleChoicesAlly,
            Difficulty _diff, DataBase _import) {
        boolean chosen_ = false;
        for (String m2_: _possibleChoicesAlly.getKeys()) {
            // notKoFoeFighters_.size() == 0
            TargetCoordsList kos_ = koFoeFighters(_fight, _partner, m2_, _possibleChoicesAlly, _diff, _import);
            if (kos_.size() == _fight.getMult()) {
                _fight.getAllyChoice().put(new MoveTarget(DataBase.EMPTY_STRING,new TargetCoords()), new MoveTarget(m2_,kos_.first()));
                chosen_ = true;
                break;
            }
        }
        if (chosen_) {
            return;
        }
        for (String m2_: _possibleChoicesAlly.getKeys()) {
            DamagingMoveData damageMove_ = (DamagingMoveData) _import.getMove(m2_);
            if (damageMove_.getTargetChoice().isWithChoice()) {
                // notKoFoeFighters_.size() == 0
                TargetCoordsList kos_ = koFoeFighters(_fight, _partner, m2_, _possibleChoicesAlly, _diff, _import);
                if (!kos_.isEmpty()) {
                    _fight.getAllyChoice().put(new MoveTarget(DataBase.EMPTY_STRING, new TargetCoords()), new MoveTarget(m2_, kos_.first()));
                    chosen_ = true;
                    break;
                }
            }
        }
        if (chosen_) {
            return;
        }
        if (!_possibleChoicesAlly.isEmpty()) {
            String m2_ = _possibleChoicesAlly.firstKey();
            CustList<TargetCoords> v2_ = _possibleChoicesAlly.firstValue();
            _fight.getAllyChoice().put(new MoveTarget(DataBase.EMPTY_STRING,new TargetCoords()), new MoveTarget(m2_,v2_.first()));
        }
    }

    static void setFirstChosenMoveAlly(Fight _fight, TeamPosition _ally, String _move, TargetCoords _target, DataBase _import) {
        MoveData fAtt_=_import.getMove(_move);
        TargetChoice choice_ = fAtt_.getTargetChoice();
        Fighter creature_ = _fight.getFighter(_ally);
        if(choice_.isWithChoice()){
            creature_.setFirstChosenMoveTarget(_move,_target);
        }else{
            creature_.setFirstChosenMove(_move);
        }
    }

    static void setBatonPassAlly(Fight _fight, TeamPosition _ally, String _move, DataBase _import) {
        boolean relais_ = hasToForward(_move, _import);
        if(relais_){
            Fighter creature_ = _fight.getFighter(_ally);
            for(TeamPosition c2_:FightOrder.fighters(_fight,Fight.CST_PLAYER)){
                Fighter partenaire_=_fight.getFighter(c2_);
                if (!partenaire_.estKo() && !partenaire_.isBelongingToPlayer() && partenaire_.estArriere()) {
                    creature_.setFirstChosenMove(_move);
                    creature_.setSubstituteForMove(c2_.getPosition());
                    //creature_.setFirstChosenMoveSubstitute(_move, c2_.getPosition());
                    break;
                }
            }
        }
    }

    private static boolean hasToForward(String _move, DataBase _import) {
        MoveData fAtt_= _import.getMove(_move);
        boolean relais_= _import.isBatonPassMove(_move);
        int nbEffets_=fAtt_.nbEffets();
        for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_=fAtt_.getEffet(i);
            if (effet_ instanceof EffectStatus && ((EffectStatus) effet_).getKoUserHealSubst()) {
                relais_ = true;
                break;
            }
        }
        return relais_;
    }

    static void choiceForSubstituing(Fight _fight, DataBase _import){
        _fight.addEmptyMessage();
        Team foeTeam_=_fight.getFoeTeam();
        ByteMap<Byte> beforeSubstitute_;
        beforeSubstitute_ = new ByteMap<Byte>();
        beforeSubstitute_.putAllMap(_fight.getFirstPositFoeFighters());
        ByteMap<Byte> afterSubstitute_;
        afterSubstitute_ = new ByteMap<Byte>();
        Bytes remplacantsPotentiels_=new Bytes();
        Bytes pksKo_=new Bytes();
        for(byte c:foeTeam_.getMembers().getKeys()){
            Fighter membre_=foeTeam_.getMembers().getVal(c);
            if(membre_.estKo()&&!NumberUtil.eq(membre_.getGroundPlaceSubst(),Fighter.BACK)){
                pksKo_.add(c);
            }
            if(!membre_.estKo()&&membre_.estArriere()){
                remplacantsPotentiels_.add(c);
            }
        }
        int nbPksKo_=pksKo_.size();
        for(int i = IndexConstants.FIRST_INDEX; i < nbPksKo_; i++){
            Fighter membre_=foeTeam_.refPartMembres(pksKo_.get(i));
            //membre_.getGroundPlaceSubst() < _fight.getMult()
            //|| membre_.getGroundPlaceSubst() == Fighter.BACK
            //==> i < nbPksKo_ <= _fight.getMult()
            if(i >= remplacantsPotentiels_.size()){
                break;
            }
            //Fighter partner_ = foeTeam_.getMembers().getVal(remplacantsPotentiels_.get(i));
            //String name_ = _import.translatePokemon(partner_.getName());
            //_fight.addMessage(Fight.SEND_SUBSTITUTE_FOE, name_);
            _fight.getFirstPositFoeFighters().put(pksKo_.get(i), Fighter.BACK);
            _fight.getFirstPositFoeFighters().put(remplacantsPotentiels_.get(i), membre_.getGroundPlaceSubst());
        }
        Bytes free_ = freeFoePlaces(_fight);
        int nbSubst_ = remplacantsPotentiels_.size();
        for(int i = nbSubst_; i < nbPksKo_; i++){
            _fight.getFirstPositFoeFighters().put(pksKo_.get(i), Fighter.BACK);
        }
        int i_ = IndexConstants.FIRST_INDEX;
        for(int i = nbPksKo_; i < nbSubst_; i++){
            if (free_.isValidIndex(i_)) {
                //Fighter partner_ = foeTeam_.getMembers().getVal(remplacantsPotentiels_.get(i));
                //String name_ = _import.translatePokemon(partner_.getName());
                //_fight.addMessage(Fight.SEND_SUBSTITUTE_FOE, name_);
                _fight.getFirstPositFoeFighters().put(remplacantsPotentiels_.get(i), free_.get(i_));
            }
            i_++;
        }
        afterSubstitute_.putAllMap(_fight.getFirstPositFoeFighters());
        messageSubstituting(afterSubstitute_, beforeSubstitute_, foeTeam_, _import, _fight, Fight.SEND_SUBSTITUTE_FOE);
        if (_fight.getFightType() == FightType.TMP_TRAINER) {
            allySubstituting(_fight, _import);
        }
    }

    private static Bytes freeFoePlaces(Fight _fight) {
        Bytes free_ = new Bytes();
        int mult_ = _fight.getMult();
        for (byte i = IndexConstants.FIRST_INDEX; i<mult_; i++) {
            free_.add(i);
        }
        Bytes values_ = new Bytes(_fight.getFirstPositFoeFighters().values());
        values_.removeAllLong(Fighter.BACK);
        for (byte b:values_) {
            free_.removeAllLong(b);
        }
        return free_;
    }

    private static void allySubstituting(Fight _fight, DataBase _import) {
        ByteMap<Byte> beforeSubstitute_ = new ByteMap<Byte>();
        beforeSubstitute_.putAllMap(_fight.getFirstPositPlayerFighters());
        ByteMap<Byte> afterSubstitute_ = new ByteMap<Byte>();
        Team userTeam_= _fight.getUserTeam();
        Bytes remplacantsPotentiels_ = new Bytes();
        Bytes pksKo_ = new Bytes();
        for(TeamPosition c:FightOrder.fightersBelongingToUser(_fight,false)){
            Fighter membre_=userTeam_.getMembers().getVal(c.getPosition());
            if(membre_.estKo()&&!NumberUtil.eq(membre_.getGroundPlaceSubst(),Fighter.BACK)){
                pksKo_.add(c.getPosition());
            }
            if(!membre_.estKo()&&membre_.estArriere()){
                remplacantsPotentiels_.add(c.getPosition());
            }
        }
        int nbPksKo_ = pksKo_.size();
        for(int i = IndexConstants.FIRST_INDEX; i < nbPksKo_; i++){
            _fight.getFirstPositPlayerFighters().put(pksKo_.get(i), Fighter.BACK);
        }
        boolean existFree_ = existFree(_fight);
        if (existFree_ && !remplacantsPotentiels_.isEmpty()) {
            //switch if possible
            //Fighter partner_ = userTeam_.getMembers().getVal(remplacantsPotentiels_.first());
            //String name_ = _import.translatePokemon(partner_.getName());
            //_fight.addMessage(Fight.SEND_SUBSTITUTE, name_);
            replaceAllyTeam(_fight, remplacantsPotentiels_);
        }
        afterSubstitute_.putAllMap(_fight.getFirstPositPlayerFighters());
        messageSubstituting(afterSubstitute_, beforeSubstitute_, userTeam_, _import, _fight, Fight.SEND_SUBSTITUTE);
    }

    private static void replaceAllyTeam(Fight _fight, Bytes _remplacantsPotentiels) {
        int mult_ = _fight.getMult();
        boolean done_ = false;
        for (byte i = IndexConstants.FIRST_INDEX; i < mult_; i++) {
//                        if (done_) {
//                            break;
//                        }
            if (_fight.getUserTeam().fightersAtCurrentPlace(i).isEmpty()) {
                boolean existSubst_ = existSubst(_fight, i);
                if (existSubst_) {
                    _fight.getFirstPositPlayerFighters().put(_remplacantsPotentiels.first(), i);
                    done_ = true;
                    break;
                }
            }
        }
        if (!done_) {
            byte iTer_ = IndexConstants.FIRST_INDEX;
//                        while (Map.<Byte,Byte>hasNumber(_fight.getFirstPositPlayerFighters(), iTer_)) {
//                            iTer_++;
//                        }
            while (_fight.isUsedAt(iTer_)) {
                iTer_++;
            }
            _fight.getFirstPositPlayerFighters().put(_remplacantsPotentiels.first(), iTer_);
        }
    }

    private static boolean existSubst(Fight _fight, byte _i) {
        boolean existSubst_ = false;
//                        for (TeamPosition c:FightOrder.fighters(_fight,Fight.PLAYER)) {
//                            Fighter f_ = _fight.getFighter(c);
//                            if (f_.isBelongingToPlayer()) {
//                                continue;
//                            }
//                            if (f_.getGroundPlaceSubst() == i && f_.estKo()) {
//                                existSubst_ = true;
//                                break;
//                            }
//                        }
        for (TeamPosition c : FightOrder.fightersBelongingToUser(_fight, false)) {
            Fighter f_ = _fight.getFighter(c);
            if (f_.isKoAt(_i)) {
                existSubst_ = true;
                break;
            }
        }
        return existSubst_;
    }

    private static void messageSubstituting(ByteMap<Byte> _afterSubstitute, ByteMap<Byte> _beforeSubstitute, Team _team, DataBase _import, Fight _fight, String _key) {
        for (byte b: _afterSubstitute.getKeys()) {
            if (NumberUtil.eq(_afterSubstitute.getVal(b), Fighter.BACK) || !NumberUtil.eq(_beforeSubstitute.getVal(b), Fighter.BACK)) {
                continue;
            }
            Fighter partner_ = _team.getMembers().getVal(b);
            String name_ = _import.translatePokemon(partner_.getName());
            _fight.addMessage(_import, _key, name_);
        }
    }

    static boolean existFree(Fight _fight) {
        int mult_ = _fight.getMult();
        Bytes allPlayer_ = new Bytes();
        for(TeamPosition c:FightOrder.fightersBelongingToUser(_fight,true)){
            allPlayer_.add(_fight.getFighter(c).getGroundPlaceSubst());
        }
        boolean existFree_ = false;
        for (byte i = IndexConstants.FIRST_INDEX; i< mult_; i++) {
            if (!_fight.isUsedAt(i)&&!allPlayer_.containsObj(i)) {
                existFree_ = true;
            }
        }
        return existFree_;
    }
}
