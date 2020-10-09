package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectOrder;
import aiki.fight.moves.effects.EffectSwitchPointView;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.actions.ActionHeal;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.comparators.SortedFighterEndRoundComparator;
import aiki.game.fight.comparators.SortedFighterHealActsComparator;
import aiki.game.fight.comparators.SortedFighterMoveActsComparator;
import aiki.game.fight.comparators.SortedFighterSwitchActsComparator;
import aiki.game.fight.util.NextUsers;
import aiki.game.params.Difficulty;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.util.CustList;
import code.util.EqList;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

final class FightOrder {

    private FightOrder() {
    }

    static EqList<TeamPosition> sortedFightersAmongListEndRound(Fight _fight,boolean _reverse,DataBase _import){
        EqList<TeamPosition> listeTriee_ = frontFighters(_fight);
        boolean vitessesInversees_=reverseSpeed(_fight,_import);
        if (_reverse) {
            vitessesInversees_ = !vitessesInversees_;
        }
        listeTriee_.sortElts(new SortedFighterEndRoundComparator(_fight, _import, vitessesInversees_));
        return listeTriee_;
    }

    static void sortFightersUsingMoveAmongList(
            Fight _fight, DataBase _import){
        EqList<TeamPosition> listeTriee_=_fight.getOrderedFighters();
        boolean vitessesInversees_=reverseSpeed(_fight,_import);
        listeTriee_.sortElts(new SortedFighterMoveActsComparator(_fight, _import, vitessesInversees_));
    }

    static void sortFightersSwitchingAmongList(
            Fight _fight,DataBase _import){
        EqList<TeamPosition> listeTriee_ = _fight.getOrderedFighters();
        listeTriee_.sortElts(new SortedFighterSwitchActsComparator(_fight, _import));
    }

    static void sortFightersBeingHealedAmongList(
            Fight _fight){
        EqList<TeamPosition> listeTriee_ = _fight.getOrderedFighters();
        listeTriee_.sortElts(new SortedFighterHealActsComparator(_fight));
    }

    static boolean reverseSpeed(Fight _fight,DataBase _import) {
        boolean vitessesInversees_=false;
        for(String c:FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGl_=(EffectGlobal)effet_;
                if(effetGl_.getReverseOrderOfSortBySpeed()){
                    vitessesInversees_=true;
                    break;
                }
            }
        }
        return vitessesInversees_;
    }
    static NextUsers sortFightersByWornBerry(
            Fight _fight,
            EqList<TeamPosition> _cbts,DataBase _import){
        NextUsers retour_;
        retour_=new NextUsers(_cbts, new EqList<TeamPosition>());
        int nbCombattants_=_cbts.size();
        EqList<TeamPosition> itemUsers_ = retour_.getItemUsers();
        for(int i = IndexConstants.FIRST_INDEX; i<nbCombattants_; i++){
            int iFighter_ = i;
            iFighter_++;
            for(int i2_=iFighter_;i2_<nbCombattants_;i2_++){
                TeamPosition cbtOne_=retour_.getNextFighters().get(i);
                TeamPosition cbtTwo_=retour_.getNextFighters().get(i2_);
                Fighter creatureOne_=_fight.getFighter(cbtOne_);
                Fighter creatureTwo_=_fight.getFighter(cbtTwo_);
                boolean baieGagnantPrioOne_=false;
                boolean baieGagnantPrioTwo_=false;
                if(FightItems.canUseItsBerry(_fight,cbtOne_,_import)){
                    Berry baie_=(Berry)creatureOne_.ficheObjet(_import);
                    baieGagnantPrioOne_=baie_.getLawForAttackFirst();
                }
                if(FightItems.canUseItsBerry(_fight,cbtTwo_,_import)){
                    Berry baie_=(Berry)creatureTwo_.ficheObjet(_import);
                    baieGagnantPrioTwo_=baie_.getLawForAttackFirst();
                }
                if (baieGagnantPrioOne_) {
                    continue;
                }
                if (!baieGagnantPrioTwo_) {
                    continue;
                }
                EqList<TeamPosition> fs_ = retour_.getNextFighters();
                TeamPosition one_ = fs_.get(i);
                TeamPosition two_ = fs_.get(i2_);
                fs_.set(i, two_);
                fs_.set(i2_, one_);
                addIfPossible(itemUsers_,cbtTwo_);
            }
        }
        return retour_;
    }
    static void addIfPossible(CustList<TeamPosition> _fighters, TeamPosition _f) {
        boolean found_ = false;
        for (TeamPosition t: _fighters) {
            if (_f.eq(t)) {
                found_ = true;
                break;
            }
        }
        if (!found_) {
            _fighters.add(_f);
        }
    }

    static EqList<TeamPosition> randomFigtherHavingToAct(Fight _fight,EqList<TeamPosition> _cbts,DataBase _import) {
        EqList<TeamPosition> tmp_ = fightersUsingMoveWithBerry(_fight,_cbts, _import);
        if(!tmp_.isEmpty()){
            return new EqList<TeamPosition>(tmp_.first());
        }
        tmp_ = _cbts;
        if(tmp_.isEmpty()){
            return new EqList<TeamPosition>();
        }
        int indexRemoving_= indexOfRemoving(_fight,tmp_, _import);
        if(indexRemoving_ <= IndexConstants.FIRST_INDEX){
            return new EqList<TeamPosition>(tmp_.first());
        }
        return randomFigtherHavingToAct(_fight,tmp_,indexRemoving_,_import);
    }

    static EqList<TeamPosition> notKoFrontFightersBelongingToUser(Fight _fight, boolean _user) {
        EqList<TeamPosition> list_ = new EqList<TeamPosition>();
        for (TeamPosition f: fightersBelongingToUser(_fight, _user)) {
            Fighter fighter_ = _fight.getFighter(f);
            if (fighter_.estArriere()) {
                continue;
            }
            list_.add(f);
        }
        return list_;
    }

    static EqList<TeamPosition> notKoBackFightersBelongingToUser(Fight _fight, boolean _user) {
        EqList<TeamPosition> list_ = new EqList<TeamPosition>();
        for (TeamPosition f: fightersBelongingToUser(_fight, _user)) {
            Fighter fighter_ = _fight.getFighter(f);
            if (fighter_.estKo()) {
                continue;
            }
            if (!fighter_.estArriere()) {
                continue;
            }
            list_.add(f);
        }
        return list_;
    }

    static EqList<TeamPosition> fightersUsingMoveWithBerry(Fight _fight,EqList<TeamPosition> _cbts,DataBase _import) {
        EqList<TeamPosition> fightersWithBerry_=new EqList<TeamPosition>();
        int nbCombattants_=_cbts.size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbCombattants_; i++){
            TeamPosition cbtOne_=_cbts.get(i);
            Fighter creatureOne_=_fight.getFighter(cbtOne_);
            if(!(creatureOne_.getAction() instanceof ActionMove)){
                continue;
            }
            boolean baieGagnantPrioOne_=false;
            if(FightItems.canUseItsBerry(_fight,cbtOne_,_import)){
                Berry baie_=(Berry)creatureOne_.ficheObjet(_import);
                baieGagnantPrioOne_=baie_.getLawForAttackFirst();
            }
            if(!baieGagnantPrioOne_){
                continue;
            }
            fightersWithBerry_.add(cbtOne_);
        }
        return fightersWithBerry_;
    }

    static EqList<TeamPosition> fightersUsingMove(Fight _fight,EqList<TeamPosition> _cbts) {
        EqList<TeamPosition> fighters_=new EqList<TeamPosition>();
        int nbCombattants_=_cbts.size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbCombattants_; i++){
            TeamPosition cbtOne_=_cbts.get(i);
            Fighter creatureOne_=_fight.getFighter(cbtOne_);
            if(!(creatureOne_.getAction() instanceof ActionMove)){
                continue;
            }
            fighters_.add(cbtOne_);
        }
        return fighters_;
    }

    static EqList<TeamPosition> fightersSwitching(Fight _fight,EqList<TeamPosition> _cbts) {
        EqList<TeamPosition> fighters_=new EqList<TeamPosition>();
        int nbCombattants_=_cbts.size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbCombattants_; i++){
            TeamPosition cbtOne_=_cbts.get(i);
            Fighter creatureOne_=_fight.getFighter(cbtOne_);
            if(!(creatureOne_.getAction() instanceof ActionSwitch)){
                continue;
            }
            fighters_.add(cbtOne_);
        }
        return fighters_;
    }

    static EqList<TeamPosition> fightersBeingHealed(Fight _fight,EqList<TeamPosition> _cbts) {
        EqList<TeamPosition> fighters_=new EqList<TeamPosition>();
        int nbCombattants_=_cbts.size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbCombattants_; i++){
            TeamPosition cbtOne_=_cbts.get(i);
            Fighter creatureOne_=_fight.getFighter(cbtOne_);
            if(!(creatureOne_.getAction() instanceof ActionHeal)){
                continue;
            }
            fighters_.add(cbtOne_);
        }
        return fighters_;
    }

    static int indexOfRemoving(Fight _fight,EqList<TeamPosition> _cbts, DataBase _import) {
        int i_=IndexConstants.INDEX_NOT_FOUND_ELT;
        int indiceTirage_= IndexConstants.INDEX_NOT_FOUND_ELT;
        for(TeamPosition e:_cbts){
            i_++;
            Fighter creatureOne_=_fight.getFighter(e);
            if(!FightItems.canUseItsObject(_fight,e,_import)){
                continue;
            }
            Item objet_=creatureOne_.ficheObjet(_import);
            if(!(objet_ instanceof ItemForBattle)){
                continue;
            }
            ItemForBattle objetAttachable_=(ItemForBattle)objet_;
            if(objetAttachable_.getLawForAttackFirst().events().size() <= DataBase.ONE_POSSIBLE_CHOICE){
                continue;
            }
            if(indiceTirage_== IndexConstants.INDEX_NOT_FOUND_ELT){
                indiceTirage_=i_;
            }
        }
        return indiceTirage_;
    }

    static EqList<TeamPosition> randomFigtherHavingToAct(Fight _fight,
            EqList<TeamPosition> _cbts, int _index,DataBase _import) {
        TeamPosition fighter_ = _cbts.get(_index);
        Fighter creatureOne_=_fight.getFighter(fighter_);
        ItemForBattle objetAttachable_=(ItemForBattle)creatureOne_.ficheObjet(_import);
        MonteCarloBoolean law_ = objetAttachable_.getLawForAttackFirst();
        if (FightSuccess.isBadSimulation(_fight, law_)) {
            return new EqList<TeamPosition>();
        }
        boolean permuter_ = FightSuccess.random(_import, law_);
        if(permuter_){
            return new EqList<TeamPosition>(fighter_);
        }
        return new EqList<TeamPosition>(_cbts.first());
    }

    static Rate speed(Fight _fight, TeamPosition _cbt,DataBase _import){
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        byte cran_=creatureCbt_.getStatisBoost().getVal(Statistic.SPEED);
        cran_ += FightStatistic.bonusBoost(_fight,Statistic.SPEED, _cbt, _import);
        Rate boostSpeed_ = FightStatistic.rateBoost(cran_,_import);
        Rate speed_ = creatureCbt_.statistiqueGlobaleEvIv(Statistic.SPEED);
        speed_.multiplyBy(FightStatistic.statisticWithoutBase(_fight, _cbt, Statistic.SPEED, FightValues.calculateValuesFighter(_fight, _cbt, _import), _import));
        speed_.multiplyBy(boostSpeed_);
        return speed_;
    }

    static EqList<TeamPosition> fightersHavingToAct(Fight _fight, boolean _dernier,DataBase _import){
        EqList<TeamPosition> ls_ = new EqList<TeamPosition>();
        for(TeamPosition c:FightOrder.fighters(_fight)){
            Fighter creature_=_fight.getFighter(c);
            if(creature_.isActed()){
                continue;
            }
//            if(_dernier != lastToUseMove(_fight,c,_import)){
//                continue;
//            }
            if(ComparatorBoolean.diff(_dernier, lastToUseMove(_fight,c,_import))){
                continue;
            }
            if (creature_.getAction() instanceof ActionMove) {
                if(!creature_.estKo()){
                    ls_.add(c);
                }
                continue;
            }
            if (creature_.getAction() instanceof ActionHeal) {
                ls_.add(c);
                continue;
            }
            if (creature_.getAction() instanceof ActionSwitch) {
                if(!creature_.estKo()){
                    ls_.add(c);
                }
                continue;
            }
        }
        return ls_;
    }
    static boolean lastToUseMove(Fight _fight, TeamPosition _target,DataBase _import){
        Team equipeCbt_=_fight.getTeams().getVal(_target.getTeam());
        for(TeamPosition c:frontFighters(_fight)){
            Fighter creature_=_fight.getFighter(c);
            if(!creature_.isSuccessfulMove()){
                continue;
            }
            boolean cible_=false;
            EqList<TargetCoords> targets_ = creature_.getChosenTargets();
            if(!targets_.isEmpty()){
                if(equipeCbt_.fightersAtCurrentPlace(targets_.first().getPosition()).containsObj(_target.getPosition())){
                    cible_=true;
                }
            }
            if(!cible_){
                continue;
            }
            if (creature_.getFinalChosenMove().isEmpty()) {
                continue;
            }
            MoveData fAttaque_=_import.getMove(creature_.getFinalChosenMove());
            int nbEffets_=fAttaque_.nbEffets();
            for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectOrder)){
                    continue;
                }
                EffectOrder effetOrdre_=(EffectOrder)effet_;
                if(effetOrdre_.getTargetAttacksLast()){
                    return true;
                }
            }
        }
        return false;
    }

    static EqList<TeamPosition> fighters(Fight _fight){
        EqList<TeamPosition> cbts_ = new EqList<TeamPosition>();
        cbts_.addAllElts(fighters(_fight,Fight.FOE));
        cbts_.addAllElts(fighters(_fight,Fight.PLAYER));
        return cbts_;
    }

    static EqList<TeamPosition> fighters(Fight _fight,byte _noTeam) {
        EqList<TeamPosition> cbts_ = new EqList<TeamPosition>();
        Team equipe_=_fight.getTeams().getVal(_noTeam);
        for(byte c2_:equipe_.getMembers().getKeys()){
            cbts_.add(new TeamPosition(_noTeam,c2_));
        }
        return cbts_;
    }

    static EqList<TeamPosition> frontFighters(Fight _fight){
        EqList<TeamPosition> cbts_ = new EqList<TeamPosition>();
        for(byte c:_fight.getTeams().getKeys()){
            Team equipe_=_fight.getTeams().getVal(c);
            for(byte c2_:equipe_.getMembers().getKeys()){
                if(!equipe_.getMembers().getVal(c2_).estArriere()){
                    cbts_.add(new TeamPosition(c,c2_));
                }
            }
        }
        return cbts_;
    }

    static int nbBackPartners(Fight _fight,TeamPosition _combattant) {
        int nbPartenairesArriere_=0;
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        for(byte c:equipe_.getMembers().getKeys()){
            if(NumberUtil.eq(c,_combattant.getPosition())){
                continue;
            }
            Fighter partenaire_=equipe_.getMembers().getVal(c);
            if(partenaire_.estKo()){
                continue;
            }
            if(partenaire_.estArriere()){
                nbPartenairesArriere_++;
            }
        }
        return nbPartenairesArriere_;
    }

    static int nbFrontPartners(Fight _fight,TeamPosition _combattant) {
        int nbPartenairesArriere_=0;
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        for(byte c:equipe_.getMembers().getKeys()){
            if(NumberUtil.eq(c,_combattant.getPosition())){
                continue;
            }
            Fighter partenaire_=equipe_.getMembers().getVal(c);
            if(partenaire_.estKo()){
                continue;
            }
            if(!partenaire_.estArriere()){
                nbPartenairesArriere_++;
            }
        }
        return nbPartenairesArriere_;
    }

    static EqList<TeamPosition> fightersWearingExpObject(
            Fight _fight,
            EqList<TeamPosition> _list, DataBase _import) {
        EqList<TeamPosition> list_ = new EqList<TeamPosition>();
        for (TeamPosition f: _list) {
            Fighter membre_= _fight.getFighter(f);
            if(membre_.estKo()){
                continue;
            }
            if(!membre_.hasExpObject()){
                continue;
            }
            ItemForBattle objet_=(ItemForBattle) membre_.dataExpObject(_import);
            if(!objet_.getBoostExp()){
                continue;
            }
            list_.add(f);
        }
        return list_;
    }

    static EqList<TeamPosition> fightersBelongingToUser(Fight _fight,
            boolean _user) {
        EqList<TeamPosition> list_ = new EqList<TeamPosition>();
        ByteMap<Fighter> map_ = _fight.getUserTeam().getMembers();
        for(byte c:map_.getKeys()){
            if (ComparatorBoolean.diff(map_.getVal(c).isBelongingToPlayer(), _user)) {
                continue;
            }
            list_.add(Fight.toUserFighter(c));
        }
        return list_;
    }

    static Bytes fightersBelongingToUserHavingBeaten(Fight _fight,byte _pos) {
        Team equipeUt_=_fight.getUserTeam();
        Bytes liste_=new Bytes();
        for(TeamPosition c: fightersBelongingToUser(_fight,true)){
            if(!equipeUt_.playerFightersAgainstFoeHas(c.getPosition(), _pos)){
                continue;
            }
            //calcul des points
            liste_.add(c.getPosition());
        }
        return liste_;
    }

    static EqList<TeamPosition> targetsEffect(Fight _fight,TeamPosition _lanceur,Effect _effet,Difficulty _diff,DataBase _import){
        return chosenTargets(_fight,_lanceur, _effet.getTargetChoice(), _diff, _import);
    }
    static EqList<TeamPosition> chosenTargets(Fight _fight,TeamPosition _lanceur,TargetChoice _choice,Difficulty _diff,DataBase _import){
        EqList<TeamPosition> cbts_ = new EqList<TeamPosition>();
        if (_choice == TargetChoice.NOTHING) {
            return cbts_;
        }
        if (_choice == TargetChoice.LANCEUR) {
            cbts_.add(_lanceur);
            return cbts_;
        }
        byte noEq_=_lanceur.getTeam();
        byte noEqAdv_=Fight.foe(noEq_);
        //fightersAtCurrentPlace
        if(_choice == TargetChoice.ALLIE){
            Team equipe_=_fight.getTeams().getVal(noEq_);
            Fighter creature_=equipe_.getMembers().getVal(_lanceur.getPosition());
            EqList<TargetCoords> ciblesChoisies_=creature_.getChosenTargets();
            if(ciblesChoisies_.isEmpty()){
                return cbts_;
            }
            Bytes cbtsEq_=equipe_.fightersAtCurrentPlace(ciblesChoisies_.first().getPosition());
            if(cbtsEq_.isEmpty()){
                return cbts_;
            }
            cbts_.add(new TeamPosition(noEq_,cbtsEq_.first()));
            return cbts_;
        }
        if(_choice == TargetChoice.ALLIES){
            ByteMap<Fighter> membres_=_fight.getTeams().getVal(noEq_).getMembers();
            for(byte c:membres_.getKeys()){
                Fighter membre_=membres_.getVal(c);
                if(!membre_.estArriere()){
                    cbts_.add(new TeamPosition(noEq_,c));
                }
            }
            return cbts_;
        }
        if(_choice == TargetChoice.TOUS_ADV){
            ByteMap<Fighter> membres_=_fight.getTeams().getVal(noEqAdv_).getMembers();
            for(byte c:membres_.getKeys()){
                Fighter membre_=membres_.getVal(c);
                if(!membre_.estArriere()){
                    cbts_.add(new TeamPosition(noEqAdv_,c));
                }
            }
            return cbts_;
        }
        if(_choice == TargetChoice.GLOBALE){
            for(byte c:_fight.getTeams().getKeys()){
                ByteMap<Fighter> membres_=_fight.getTeams().getVal(c).getMembers();
                for(byte c2_:membres_.getKeys()){
                    Fighter membre_=membres_.getVal(c2_);
                    if(!membre_.estArriere()){
                        cbts_.add(new TeamPosition(c,c2_));
                    }
                }
            }
            return cbts_;
        }
        if(_choice == TargetChoice.PSEUDO_GLOBALE){
            ByteMap<Fighter> membres_=_fight.getTeams().getVal(noEqAdv_).getMembers();
            for(byte c2_:membres_.getKeys()){
                Fighter membre_=membres_.getVal(c2_);
                if(!membre_.estArriere()){
                    cbts_.add(new TeamPosition(noEqAdv_,c2_));
                }
            }
            membres_=_fight.getTeams().getVal(noEq_).getMembers();
            for(byte c2_:membres_.getKeys()){
                if(NumberUtil.eq(c2_,_lanceur.getPosition())){
                    continue;
                }
                Fighter membre_=membres_.getVal(c2_);
                if(!membre_.estArriere()){
                    cbts_.add(new TeamPosition(noEq_,c2_));
                }
            }
            return cbts_;
        }
        if (_choice == TargetChoice.ADJ_ADV) {
            return closestFigthersFoeTeam(_fight,_lanceur,_diff);
        }
        if (_choice == TargetChoice.ADJ_MULT) {
            return closestFigthers(_fight,_lanceur,_diff);
        }
        Team equipe_=_fight.getTeams().getVal(noEq_);
        Fighter creature_=equipe_.getMembers().getVal(_lanceur.getPosition());
        EqList<TargetCoords> ciblesChoisies_=creature_.getChosenTargets();
        if(ciblesChoisies_.isEmpty()){
            return cbts_;
        }
        if(NumberUtil.eq(ciblesChoisies_.first().getTeam(),noEqAdv_)){
            //existence partenaire non ko de la cible initial vise. ce partenaire utilise PAR_ICI ou POUDREFUREUR
            Bytes combattantsAttirant_ = new Bytes();
            Team foeTeam_ = _fight.getTeams().getVal(noEqAdv_);
            ByteMap<Fighter> membres_=foeTeam_.getMembers();
            for(byte c:membres_.getKeys()){
                Fighter creatureCbt_=membres_.getVal(c);
                if(creatureCbt_.estArriere()){
                    continue;
                }
                if(!creatureCbt_.isSuccessfulMove()){
                    continue;
                }
                String attaqueCbt_=creatureCbt_.getFinalChosenMove();
                if (getPointViewChangementType(attaqueCbt_, _import) == PointViewChangementType.ATTRACT_DAMAGES_MOVES) {
                    combattantsAttirant_.add(c);
                }
            }
            //attraction des effets
            if(!combattantsAttirant_.isEmpty()){
                EqList<TeamPosition> cbtsModif_=closestFightersAmongList(_fight,_lanceur,combattantsAttirant_);
                Bytes cbtsListe_ = new Bytes();
                ByteMap<TeamPosition> positions_ = new ByteMap<TeamPosition>();
                for(TeamPosition c:cbtsModif_){
                    Fighter creatureCbt_=membres_.getVal(c.getPosition());
                    byte key_= creatureCbt_.getGroundPlace();
                    cbtsListe_.add(key_);
                    positions_.put(key_, c);
                }
                cbtsListe_.sort();
                for (byte p: cbtsListe_) {
                    cbts_.add(positions_.getVal(p));
                }
                return cbts_;
            }
            Bytes cbtsEq_=foeTeam_.fightersAtCurrentPlace(ciblesChoisies_.first().getPosition());
            if(cbtsEq_.isEmpty()){
                cbts_=closestFoeFighter(_fight,_lanceur);
            }else{
                cbts_.add(new TeamPosition(noEqAdv_,cbtsEq_.first()));
            }
            return cbts_;
        }
        Bytes cbtsEq_=equipe_.fightersAtCurrentPlace(ciblesChoisies_.first().getPosition());
        if(cbtsEq_.isEmpty()){
            return cbts_;
        }
        cbts_.add(new TeamPosition(noEq_,cbtsEq_.first()));
        return cbts_;
    }

    static EqList<TeamPosition> closestFigthers(Fight _fight,TeamPosition _combattant,Difficulty _diff){
        EqList<TeamPosition> cbts_ = closestFigthersFoeTeam(_fight,_combattant,_diff);
        cbts_.addAllElts(closestFigthersSameTeam(_fight,_combattant,_diff));
        return cbts_;
    }

    static EqList<TeamPosition> closestFigthersFoeTeam(Fight _fight,TeamPosition _combattant,Difficulty _diff){
        byte noEq_=_combattant.getTeam();
        byte noEqAdv_=Fight.foe(noEq_);
        byte fighterPlace_ = _fight.getFighter(_combattant).getGroundPlace();
        EqList<TeamPosition> cbts_ = closestFigthersTeam(fighterPlace_, noEqAdv_, _fight.getTeams().getVal(noEqAdv_), _diff);
        if(cbts_.isEmpty()){
            cbts_=closestFoeFighter(_fight,_combattant);
        }
        return cbts_;
    }

    static EqList<TeamPosition> closestFigthersSameTeam(Fight _fight,TeamPosition _combattant,Difficulty _diff){
        byte noEq_=_combattant.getTeam();
        byte fighterPlace_ = _fight.getFighter(_combattant).getGroundPlace();
        EqList<TeamPosition> cbts_ = closestFigthersTeam(fighterPlace_, noEq_, _fight.getTeams().getVal(noEq_), _diff);
        cbts_.removeObj(_combattant);
        return cbts_;
    }

    static EqList<TeamPosition> closestFigthersTeam(byte _fighterPlace,byte _noTeam, Team _team, Difficulty _diff) {
        EqList<TeamPosition> cbts_ = new EqList<TeamPosition>();
        ByteMap<Fighter> membresAdv_=_team.getMembers();
        for(byte c:membresAdv_.getKeys()){
            Fighter membre_=membresAdv_.getVal(c);
            if(membre_.estArriere()){
                continue;
            }
            int diff_=membre_.getGroundPlace()-_fighterPlace;
            if(Math.abs(diff_) <= 1||!_diff.getEnabledClosing()){
                cbts_.add(new TeamPosition(_noTeam,c));
            }
        }
        return cbts_;
    }

    static EqList<TeamPosition> closestFoeFighter(Fight _fight,TeamPosition _cbt){
        byte noEquipe_=_cbt.getTeam();
        byte noEquipeAdv_=Fight.foe(noEquipe_);
        Team equipeAdvCbt_=_fight.getTeams().getVal(noEquipeAdv_);
        ByteMap<Fighter> membresEquipeAdv_=equipeAdvCbt_.getMembers();
        return closestFightersAmongList(_fight,_cbt,membresEquipeAdv_.getKeys());
    }

    static EqList<TeamPosition> closestFightersAmongList(Fight _fight,TeamPosition _cbt,CustList<Byte> _liste){
        byte noEquipe_=_cbt.getTeam();
        byte noEquipeAdv_=Fight.foe(noEquipe_);
        Team equipeAdvCbt_=_fight.getTeams().getVal(noEquipeAdv_);
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        byte posCbt_=creatureCbt_.getGroundPlace();
        Bytes posAdv_ = new Bytes();
        byte diff_=Fighter.BACK;
        ByteMap<Fighter> membresEquipeAdv_=equipeAdvCbt_.getMembers();
        for(byte e:_liste){
            Fighter membre_=membresEquipeAdv_.getVal(e);
            if(!membre_.estArriere()){
                byte posCbtAdv_=membre_.getGroundPlace();
                posAdv_.add(posCbtAdv_);
            }
        }
        for(byte e:posAdv_){
            int currDiff_ = Math.abs(e-posCbt_);
            int sgn_ = Integer.signum(e-posCbt_);
            if(NumberUtil.eq(diff_,Fighter.BACK)){
                diff_=(byte) currDiff_;
                continue;
            }
            if (sgn_ != 0) {
                if(diff_>currDiff_){
                    diff_=(byte) currDiff_;
                }
                continue;
            }
            diff_=0;
        }
        EqList<TeamPosition> cbtsProches_ = new EqList<TeamPosition>();
        for(byte c:_liste){
            Fighter membre_=membresEquipeAdv_.getVal(c);
            byte posCbtAdv_=membre_.getGroundPlace();
            if (Math.abs(posCbtAdv_ - posCbt_) == diff_) {
                cbtsProches_.add(new TeamPosition(noEquipeAdv_,c));
            }
        }
        return cbtsProches_;
    }
    static PointViewChangementType getPointViewChangementType(String _move,DataBase _import) {
        MoveData fAttCible_ = _import.getMove(_move);
        int nbEffetsCible_=fAttCible_.nbEffets();
        for (int i = IndexConstants.FIRST_INDEX; i<nbEffetsCible_; i++){
            Effect effetLoc_=fAttCible_.getEffet(i);
            if(!(effetLoc_ instanceof EffectSwitchPointView)){
                continue;
            }
            EffectSwitchPointView effetChgtPointVueAttLoc_=(EffectSwitchPointView)effetLoc_;
            return effetChgtPointVueAttLoc_.getPointViewChangement();
        }
        return PointViewChangementType.NOTHING;
    }
}
