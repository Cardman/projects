package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.comparators.ComparatorLaws;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.enums.UsefulValueLaw;
import aiki.game.fight.util.MoveTarget;
import aiki.game.fight.util.StatisticsDamageMove;
import aiki.game.params.Difficulty;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.*;
import code.util.ObjectMap;
import code.util.SortableCustList;
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
        _fight.setAllyChoice(new ObjectMap<MoveTarget,MoveTarget>());
        StringMap<EqList<TargetCoords>> possibleChoicesAlly_ = new StringMap<EqList<TargetCoords>>();
        TeamPosition userPk_ = new TeamPosition();
        EqList<TeamPosition> partners_ = FightOrder.notKoFrontFightersBelongingToUser(_fight,false);
        if (partners_.isEmpty()) {
            return;
        }
        TeamPosition partner_ = partners_.first();
        for (String m: FightFacade.allowedMovesNotEmpty(_fight,partner_, _import)) {
            MoveData fAtt_ = _import.getMove(m);
            if (!(fAtt_ instanceof DamagingMoveData)) {
                continue;
            }
            EqList<TargetCoords> list_ = new EqList<TargetCoords>();
            int mult_ = _fight.getMult();
            for (short f = IndexConstants.FIRST_INDEX; f < mult_; f++) {
                if (_fight.getFoeTeam().fightersAtCurrentPlace(f).isEmpty()) {
                    continue;
                }
                list_.add(TargetCoords.toFoeTarget(f));
            }
            possibleChoicesAlly_.put(m, list_);
        }
        StringMap<ObjectMap<TargetCoords,Rate>> remoteHp_ = new StringMap<ObjectMap<TargetCoords,Rate>>();
        boolean existUser_ = false;
        for (TeamPosition f: FightOrder.notKoFrontFightersBelongingToUser(_fight,true)) {
            for (String m: FightFacade.allowedMovesNotEmpty(_fight,f, _import)) {
                MoveData fAtt_ = _import.getMove(m);
                if (!(fAtt_ instanceof DamagingMoveData)) {
                    continue;
                }
                ObjectMap<TargetCoords,Rate> remainingTargetHp_;
                remainingTargetHp_ = remainingFoeTargetHp(_fight, f, m, _diff, _import);
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
            userPk_ = f;
            existUser_ = true;
            break;
        }
        for (String m: remoteHp_.getKeys()) {
            //m move of player pk
            EqList<TargetCoords> koFoeFighters_ = new EqList<TargetCoords>();
            EqList<TargetCoords> notKoFoeFighters_ = new EqList<TargetCoords>();
            ObjectMap<TargetCoords,Rate> remoteHpLoc_ = remoteHp_.getVal(m);
            for (TargetCoords t: remoteHpLoc_.getKeys()) {
                if (remoteHpLoc_.getVal(t).isZero()) {
                    koFoeFighters_.add(t);
                } else {
                    notKoFoeFighters_.add(t);
                }
            }
            if (koFoeFighters_.size() == _fight.getMult()) {
                continue;
            }
            if (!koFoeFighters_.isEmpty() && !notKoFoeFighters_.isEmpty()) {
                for (String m2_: possibleChoicesAlly_.getKeys()) {
                    if (!usableMove(_fight, partner_, userPk_, true, m2_, _diff, _import)) {
                        continue;
                    }
                    // notKoFoeFighters_.size() == 1
                    // koFoeFighters_.isEmpty() == 1
                    Bytes foeFighers_ = _fight.getFoeTeam().fightersAtCurrentPlace(notKoFoeFighters_.first().getPosition());
                    for (byte f: foeFighers_) {
                        TeamPosition c2_ = Fight.toFoeFighter(f);
                        if (koFoeFighter(_fight, partner_, m2_, c2_, _diff, _import)) {
                            _fight.getAllyChoice().put(new MoveTarget(m,koFoeFighters_.first()), new MoveTarget(m2_,notKoFoeFighters_.first()));
                        }
                    }
                }
                continue;
            }
            boolean chosen_ = false;
            for (String m2_: possibleChoicesAlly_.getKeys()) {
                if (!usableMove(_fight, partner_, userPk_, false, m2_, _diff, _import)) {
                    continue;
                }
                // notKoFoeFighters_.size() == 0
                EqList<TargetCoords> kos_ = koFoeFighters(_fight, partner_, m2_, possibleChoicesAlly_, _diff, _import);
                if (kos_.size() == _fight.getMult()) {
                    for (TargetCoords t: remoteHpLoc_.getKeys()) {
                        _fight.getAllyChoice().put(new MoveTarget(m,t), new MoveTarget(m2_,kos_.first()));
                    }
                    chosen_ = true;
                    break;
                }
            }
            if (chosen_) {
                continue;
            }
            //boolean chosen_ = false;
            for (String m2_: possibleChoicesAlly_.getKeys()) {
                DamagingMoveData damageMove_ = (DamagingMoveData) _import.getMove(m2_);
                if (!damageMove_.getTargetChoice().isWithChoice()) {
                    continue;
                }
                // notKoFoeFighters_.size() == 0
                EqList<TargetCoords> kos_ = koFoeFighters(_fight, partner_, m2_, possibleChoicesAlly_, _diff,_import);
                if (!kos_.isEmpty()) {
                    for (TargetCoords t: remoteHpLoc_.getKeys()) {
                        _fight.getAllyChoice().put(new MoveTarget(m,t), new MoveTarget(m2_,kos_.first()));
                    }
                    chosen_ = true;
                    break;
                }
            }
            if (chosen_) {
                continue;
            }
            for (String m2_: possibleChoicesAlly_.getKeys()) {
                for (TargetCoords t: remoteHpLoc_.getKeys()) {
                    _fight.getAllyChoice().put(new MoveTarget(m,t), new MoveTarget(m2_,possibleChoicesAlly_.getVal(m2_).first()));
                }
                break;
            }
        }
        if (!existUser_) {
            //no pk user or pk user without damage moves
            choiceAllyArtificialIntelligenceWithoutUser(_fight, partner_, possibleChoicesAlly_, _diff, _import);
        }
    }

    static ObjectMap<TargetCoords,Rate> remainingFoeTargetHp(Fight _fight, TeamPosition _thrower, String _move, Difficulty _diff, DataBase _import) {
        MoveData fAtt_ = _import.getMove(_move);
        int index_ = fAtt_.indexOfPrimaryEffect();
        StringList types_ = FightMoves.moveTypes(_fight,_thrower, _move, _import);
        ObjectMap<TargetCoords,Rate> remoteHpLoc_ = new ObjectMap<TargetCoords,Rate>();
        int mult_ = _fight.getMult();
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
            if (!reachable(_fight, _thrower, f_, _move, _diff, _import)) {
                remoteHpLoc_.put(t_, remoteHpFoe_);
                continue;
            }
            if (Rate.strLower(FightSuccess.accuracy(_fight,_thrower, f_, _move, _import),DataBase.determinatedRate())) {
                remoteHpLoc_.put(t_, remoteHpFoe_);
                continue;
            }
            _fight.setSending(false);
            if (!FightSuccess.successfulMove(_fight,_thrower, f_,_move,index_,false,_import).isSuccessful()) {
                remoteHpLoc_.put(t_, remoteHpFoe_);
                continue;
            }
            boolean affect_ = true;
            for (String t: types_) {
                if (!FightSuccess.rateEffAgainstTarget(_fight,_thrower, f_, t, _import).isZero()) {
                    continue;
                }
                affect_ = false;
            }
            if (!affect_) {
                remoteHpLoc_.put(t_, remoteHpFoe_);
                continue;
            }
            EnumMap<UsefulValueLaw,Rate> statistiquesLoc_;
            statistiquesLoc_= FightEffects.calculateMinMaxAvgVarForDamage(_fight,_thrower,f_,_move, _diff,_import);
            Rate delta_ = Rate.minus(remoteHpFoe_, statistiquesLoc_.getVal(UsefulValueLaw.MINI));
            if (delta_.isZeroOrGt()) {
                remoteHpLoc_.put(t_, delta_);
            } else {
                remoteHpLoc_.put(t_, Rate.zero());
            }
        }
        return remoteHpLoc_;
    }

    static ObjectMap<TeamPosition,Rate> remainingPartnerTargetHp(Fight _fight, TeamPosition _thrower, String _move, Difficulty _diff, DataBase _import) {
        ObjectMap<TeamPosition,Rate> remoteHpLoc_ = new ObjectMap<TeamPosition,Rate>();
        for(TeamPosition f: FightOrder.fighters(_fight, Fight.PLAYER)){
            Fighter partner_ = _fight.getFighter(f);
            Rate remoteHpPartner_ = partner_.getRemainingHp();
            SortableCustList<Rate> rates_ = new SortableCustList<Rate>();
            rates_.add(FightSuccess.accuracy(_fight,_thrower, f, _move, _import));
            rates_.add(Rate.zero());
//            rates_.sort(new NaturalComparator<Rate>() {
//                @Override
//                public int compare(Rate _o1, Rate _o2) {
//                    return _o1.compareTo(_o2);
//                }
//            });
            rates_.sort();
            if (rates_.last().isZero()) {
                remoteHpLoc_.put(f, remoteHpPartner_);
                continue;
            }
            if (untouchablePartners(_fight, _thrower, _move, _diff, _import).containsObj(f)) {
                remoteHpLoc_.put(f, remoteHpPartner_);
                continue;
            }
            EnumMap<UsefulValueLaw,Rate> statistiquesLoc_;
            statistiquesLoc_= FightEffects.calculateMinMaxAvgVarForDamage(_fight,_thrower,f,_move, _diff,_import);
            Rate delta_ = Rate.minus(remoteHpPartner_, statistiquesLoc_.getVal(UsefulValueLaw.MAXI));
            if (delta_.isZeroOrGt()) {
                remoteHpLoc_.put(f, delta_);
            } else {
                remoteHpLoc_.put(f, Rate.zero());
            }
        }
        return remoteHpLoc_;
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

    static boolean reachable(Fight _fight, TeamPosition _thrower, TeamPosition _target, String _move, Difficulty _diff, DataBase _import) {
        DamagingMoveData fAtt_ = (DamagingMoveData) _import.getMove(_move);
        int index_ = fAtt_.indexOfPrimaryEffect();
        if (!fAtt_.getTargetChoice().isWithChoice()) {
            EffectDamage effetDeg_=(EffectDamage)fAtt_.getEffet(index_);
            return FightOrder.targetsEffect(_fight, _thrower, effetDeg_, _diff, _import).containsObj(_target);
        }
        if (fAtt_.getTargetChoice() == TargetChoice.ADJ_UNIQ) {
            return FightOrder.closestFigthers(_fight,_thrower,_diff).containsObj(_target);
        }
        return true;
    }

    static EqList<TeamPosition> untouchablePartners(Fight _fight, TeamPosition _thrower, String _move, Difficulty _diff, DataBase _import) {
        MoveData damageMove_ = _import.getMove(_move);
        if (damageMove_.getTargetChoice().isWithChoice()) {
            return FightOrder.fighters(_fight, Fight.PLAYER);
        }
        if (damageMove_.getTargetChoice() == TargetChoice.ADJ_ADV) {
            return FightOrder.fighters(_fight, Fight.PLAYER);
        }
        if (damageMove_.getTargetChoice() == TargetChoice.TOUS_ADV) {
            return FightOrder.fighters(_fight, Fight.PLAYER);
        }
        EqList<TeamPosition> untouchablePartners_ = new EqList<TeamPosition>();
        int index_ = damageMove_.indexOfPrimaryEffect();
        StringList types_ = FightMoves.moveTypes(_fight,_thrower, _move, _import);
        int mult_ = _fight.getMult();
        for (byte f = IndexConstants.FIRST_INDEX; f < mult_; f++) {
            if (_fight.getUserTeam().fightersAtCurrentPlace(f).isEmpty()) {
                continue;
            }
            byte pos_ = _fight.getUserTeam().fightersAtCurrentPlace(f).first();
            TeamPosition f_ = Fight.toUserFighter(pos_);
            if (f == _fight.getFighter(_thrower).getGroundPlace()) {
                untouchablePartners_.add(f_);
                continue;
            }
            if (!reachable(_fight, _thrower, f_, _move, _diff, _import)) {
                untouchablePartners_.add(f_);
                continue;
            }
            if (!FightSuccess.successfulMove(_fight,_thrower, f_,_move,index_,false,_import).isSuccessful()) {
                untouchablePartners_.add(f_);
                continue;
            }
            boolean affect_ = true;
            for (String t: types_) {
                if (!FightSuccess.rateEffAgainstTarget(_fight,_thrower, f_, t, _import).isZero()) {
                    continue;
                }
                affect_ = false;
            }
            if (!affect_) {
                untouchablePartners_.add(f_);
            }
        }
        return untouchablePartners_;
    }

    static EqList<TargetCoords> koFoeFighters(Fight _fight, TeamPosition _thrower, String _move, StringMap<EqList<TargetCoords>> _possibleChoicesAlly, Difficulty _diff, DataBase _import) {
        EqList<TargetCoords> kos_ = new EqList<TargetCoords>();
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
            if(!(effet_ instanceof EffectDamage)){
                continue;
            }
            if (Rate.strLower(FightSuccess.accuracy(_fight,_thrower, _foe, _move, _import),DataBase.determinatedRate())) {
                continue;
            }
            if (!FightSuccess.successfulMove(_fight,_thrower, _foe,_move,i,false,_import).isSuccessful()) {
                continue;
            }
            boolean affect_ = true;
            for (String t: types_) {
                if (!FightSuccess.rateEffAgainstTarget(_fight,_thrower, _foe, t, _import).isZero()) {
                    continue;
                }
                affect_ = false;
            }
            if (!affect_) {
                continue;
            }
            EnumMap<UsefulValueLaw,Rate> statistiquesLoc_=FightEffects.calculateMinMaxAvgVarForDamage(_fight,_thrower,_foe,_move,_diff,_import);
            if (Rate.lowerEq(remoteHpFoe_, statistiquesLoc_.getVal(UsefulValueLaw.MINI))) {
                return true;
            }
        }
        return false;
    }
    static void choiceFoeArtificialIntelligence(Fight _fight, Difficulty _diff,DataBase _import){
        if(_fight.getFightType().isWild()){
            StringList attaquesUtilisables_= FightFacade.allowedMovesNotEmpty(_fight,Fight.toFoeFighter((byte) 0),_import);
            MonteCarloString loi_ = new MonteCarloString();
            for(String e:attaquesUtilisables_){
                loi_.addEvent(e,DataBase.defElementaryEvent());
            }
            LgInt maxRd_ = _import.getMaxRd();
            String attaqueUtilisee_=loi_.editNumber(maxRd_,_import.getGenerator());
            setFirstChosenMove(_fight, Fight.toFoeFighter((byte) 0), attaqueUtilisee_, _diff, _import);
            return;
        }
        for(TeamPosition c:FightOrder.frontFighters(_fight)){
            if(NumberUtil.eq(c.getTeam(),Fight.PLAYER)){
                continue;
            }
            StringList attaquesUtilisables_=FightRules.allowedMoves(_fight,c,_import);
            if(attaquesUtilisables_.isEmpty()){
                String move_ = _import.getDefaultMove();
                setFirstChosenMove(_fight, c, move_, _diff, _import);
                setBatonPass(_fight, c, move_, _import);
                continue;
            }
            StringList attaquesOffUtilisables_=new StringList();
            for(String e:attaquesUtilisables_){
                MoveData fAtt_=_import.getMove(e);
                if(!(fAtt_ instanceof DamagingMoveData)){
                    continue;
                }
                attaquesOffUtilisables_.add(e);
            }
            if(attaquesOffUtilisables_.isEmpty()){
                attaquesUtilisables_.sort();
                String move_ = attaquesUtilisables_.first();
                setFirstChosenMove(_fight, c, move_, _diff, _import);
                setBatonPass(_fight, c, move_, _import);
                continue;
            }
            CustList<StatisticsDamageMove> liste_=new CustList<StatisticsDamageMove>();
            for(String m:attaquesOffUtilisables_){
                EnumMap<UsefulValueLaw,Rate> statistiques_=new EnumMap<UsefulValueLaw,Rate>();
                statistiques_.put(UsefulValueLaw.MINI,Rate.zero());
                statistiques_.put(UsefulValueLaw.MAXI,Rate.zero());
                statistiques_.put(UsefulValueLaw.MOY,Rate.zero());
                statistiques_.put(UsefulValueLaw.VAR,Rate.zero());
                int mult_ = _fight.getMult();
                for (byte f = IndexConstants.FIRST_INDEX; f < mult_; f++) {
                    if (_fight.getUserTeam().fightersAtCurrentPlace(f).isEmpty()) {
                        continue;
                    }
                    byte pos_ = _fight.getUserTeam().fightersAtCurrentPlace(f).first();
                    TeamPosition f_ = Fight.toUserFighter(pos_);
                    if (!reachable(_fight, c, f_, m, _diff, _import)) {
                        continue;
                    }
                    EnumMap<UsefulValueLaw,Rate> statistiquesLoc_;
                    statistiquesLoc_=FightEffects.calculateMinMaxAvgVarForDamage(_fight,c,f_,m,_diff,_import);
                    Rate accuracy_ = FightSuccess.accuracy(_fight,c, f_, m, _import);
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
                    for (UsefulValueLaw u: UsefulValueLaw.values()) {
                        statistiques_.getVal(u).addNb(statistiquesLoc_.getVal(u));
                    }
                }
                StatisticsDamageMove elt_;
                elt_ = new StatisticsDamageMove(statistiques_, m);
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
            setFirstChosenMove(_fight, c, attaqueUtilisee_, _diff, _import);
        }
    }

    static void setFirstChosenMove(Fight _fight, TeamPosition _foe, String _move, Difficulty _diff, DataBase _import) {
        MoveData fAtt_=_import.getMove(_move);
        TargetChoice choice_ = fAtt_.getTargetChoice();
        Fighter creature_ = _fight.getFighter(_foe);
        if(choice_ == TargetChoice.ALLIE){
            EqList<TeamPosition> cibles_= FightOrder.closestFigthersSameTeam(_fight,_foe,_diff);
            if (!cibles_.isEmpty()) {
                Fighter cible_ = _fight.getFighter(cibles_.first());
                creature_.setFirstChosenMoveTarget(_move,TargetCoords.toFoeTarget(cible_.getGroundPlace()));
            } else {
                creature_.setFirstChosenMove(_move);
            }
        }else if(choice_.isWithChoice()){
            EqList<TeamPosition> cibles_= FightOrder.closestFoeFighter(_fight, _foe);
            Fighter cible_ = _fight.getFighter(cibles_.first());
            creature_.setFirstChosenMoveTarget(_move,TargetCoords.toUserTarget(cible_.getGroundPlace()));
        }else{
            creature_.setFirstChosenMove(_move);
        }
    }

    static void setBatonPass(Fight _fight, TeamPosition _foe, String _move, DataBase _import) {
        MoveData fAtt_=_import.getMove(_move);
        boolean relais_ = _import.isBatonPassMove(_move);
        int nbEffets_=fAtt_.nbEffets();
        for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_=fAtt_.getEffet(i);
            if(!(effet_ instanceof EffectStatus)){
                continue;
            }
            if (((EffectStatus)effet_).getKoUserHealSubst()) {
                relais_=true;
                break;
            }
        }
        if(relais_){
            Fighter creature_ = _fight.getFighter(_foe);
            for(TeamPosition c2_:FightOrder.fighters(_fight,Fight.FOE)){
                Fighter partenaire_=_fight.getFighter(c2_);
                if (partenaire_.estKo()) {
                    continue;
                }
                if (!partenaire_.estArriere()) {
                    continue;
                }
                creature_.setFirstChosenMove(_move);
                creature_.setSubstituteForMove(c2_.getPosition());
                //creature_.setFirstChosenMoveSubstitute(_move, c2_.getPosition());
                break;
            }
        }
    }

    static void choiceAllyArtificialIntelligenceWithoutUser(Fight _fight, TeamPosition _partner,
            StringMap<EqList<TargetCoords>> _possibleChoicesAlly,
            Difficulty _diff, DataBase _import) {
        boolean chosen_ = false;
        for (String m2_: _possibleChoicesAlly.getKeys()) {
            // notKoFoeFighters_.size() == 0
            EqList<TargetCoords> kos_ = koFoeFighters(_fight, _partner, m2_, _possibleChoicesAlly, _diff, _import);
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
            if (!damageMove_.getTargetChoice().isWithChoice()) {
                continue;
            }
            // notKoFoeFighters_.size() == 0
            EqList<TargetCoords> kos_ = koFoeFighters(_fight, _partner, m2_, _possibleChoicesAlly, _diff,_import);
            if (!kos_.isEmpty()) {
                _fight.getAllyChoice().put(new MoveTarget(DataBase.EMPTY_STRING,new TargetCoords()), new MoveTarget(m2_,kos_.first()));
                chosen_ = true;
                break;
            }
        }
        if (chosen_) {
            return;
        }
        for (String m2_: _possibleChoicesAlly.getKeys()) {
            _fight.getAllyChoice().put(new MoveTarget(DataBase.EMPTY_STRING,new TargetCoords()), new MoveTarget(m2_,_possibleChoicesAlly.getVal(m2_).first()));
            break;
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
        MoveData fAtt_=_import.getMove(_move);
        boolean relais_=_import.isBatonPassMove(_move);
        int nbEffets_=fAtt_.nbEffets();
        for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_=fAtt_.getEffet(i);
            if(!(effet_ instanceof EffectStatus)){
                continue;
            }
            if (((EffectStatus)effet_).getKoUserHealSubst()) {
                relais_=true;
                break;
            }
        }
        if(relais_){
            Fighter creature_ = _fight.getFighter(_ally);
            for(TeamPosition c2_:FightOrder.fighters(_fight,Fight.PLAYER)){
                Fighter partenaire_=_fight.getFighter(c2_);
                if (partenaire_.estKo()) {
                    continue;
                }
                if (partenaire_.isBelongingToPlayer()) {
                    continue;
                }
                if (!partenaire_.estArriere()) {
                    continue;
                }
                creature_.setFirstChosenMove(_move);
                creature_.setSubstituteForMove(c2_.getPosition());
                //creature_.setFirstChosenMoveSubstitute(_move, c2_.getPosition());
                break;
            }
        }
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
        for (byte b: afterSubstitute_.getKeys()) {
            if (NumberUtil.eq(afterSubstitute_.getVal(b), Fighter.BACK)) {
                continue;
            }
            if (!NumberUtil.eq(beforeSubstitute_.getVal(b), Fighter.BACK)) {
                continue;
            }
            Fighter partner_ = foeTeam_.getMembers().getVal(b);
            String name_ = _import.translatePokemon(partner_.getName());
            _fight.addMessage(_import,Fight.SEND_SUBSTITUTE_FOE, name_);
        }
        if (_fight.getFightType() == FightType.TMP_TRAINER) {
            beforeSubstitute_ = new ByteMap<Byte>();
            beforeSubstitute_.putAllMap(_fight.getFirstPositPlayerFighters());
            afterSubstitute_ = new ByteMap<Byte>();
            Team userTeam_=_fight.getUserTeam();
            remplacantsPotentiels_=new Bytes();
            pksKo_=new Bytes();
            Bytes frontPk_ = new Bytes();
            for(TeamPosition c:FightOrder.fightersBelongingToUser(_fight,false)){
                Fighter membre_=userTeam_.getMembers().getVal(c.getPosition());
                if(membre_.estKo()&&!NumberUtil.eq(membre_.getGroundPlaceSubst(),Fighter.BACK)){
                    pksKo_.add(c.getPosition());
                }
                if(!membre_.estKo()&&membre_.estArriere()){
                    remplacantsPotentiels_.add(c.getPosition());
                }
                if (!membre_.estArriere()) {
                    frontPk_.add(membre_.getGroundPlace());
                }
            }
            nbPksKo_=pksKo_.size();
            for(int i = IndexConstants.FIRST_INDEX; i < nbPksKo_; i++){
                _fight.getFirstPositPlayerFighters().put(pksKo_.get(i), Fighter.BACK);
            }
            if (frontPk_.isEmpty()) {
                //switch if possible
                if (!remplacantsPotentiels_.isEmpty()) {
                    //Fighter partner_ = userTeam_.getMembers().getVal(remplacantsPotentiels_.first());
                    //String name_ = _import.translatePokemon(partner_.getName());
                    //_fight.addMessage(Fight.SEND_SUBSTITUTE, name_);
                    boolean done_ = false;
                    for (byte i = IndexConstants.FIRST_INDEX; i<mult_; i++) {
//                        if (done_) {
//                            break;
//                        }
                        if (!_fight.getUserTeam().fightersAtCurrentPlace(i).isEmpty()) {
                            continue;
                        }
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
                        for (TeamPosition c:FightOrder.fightersBelongingToUser(_fight, false)) {
                            Fighter f_ = _fight.getFighter(c);
                            if (f_.isKoAt(i)) {
                                existSubst_ = true;
                                break;
                            }
                        }
                        if (existSubst_) {
                            _fight.getFirstPositPlayerFighters().put(remplacantsPotentiels_.first(), i);
                            done_ = true;
                            break;
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
                        _fight.getFirstPositPlayerFighters().put(remplacantsPotentiels_.first(), iTer_);
                    }
                }
            }
            afterSubstitute_.putAllMap(_fight.getFirstPositPlayerFighters());
            for (byte b: afterSubstitute_.getKeys()) {
                if (NumberUtil.eq(afterSubstitute_.getVal(b), Fighter.BACK)) {
                    continue;
                }
                if (!NumberUtil.eq(beforeSubstitute_.getVal(b), Fighter.BACK)) {
                    continue;
                }
                Fighter partner_ = userTeam_.getMembers().getVal(b);
                String name_ = _import.translatePokemon(partner_.getName());
                _fight.addMessage(_import,Fight.SEND_SUBSTITUTE, name_);
            }
        }
    }
}
