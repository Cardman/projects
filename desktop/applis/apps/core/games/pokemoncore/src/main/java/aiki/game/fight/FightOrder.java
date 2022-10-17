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
import aiki.game.fight.actions.*;
import aiki.game.fight.util.NextUsers;
import aiki.game.params.Difficulty;
import aiki.util.TargetCoordsList;
import aiki.util.TeamPositionList;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.util.CustList;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

final class FightOrder {

    private FightOrder() {
    }

    static TeamPositionList sortedFightersAmongListEndRound(Fight _fight,boolean _reverse,DataBase _import){
        TeamPositionList listeTriee_ = frontFighters(_fight);
        boolean vitessesInversees_=reverseSpeed(_fight,_import);
        if (_reverse) {
            vitessesInversees_ = !vitessesInversees_;
        }
        listeTriee_.sortElts(new SortedFighterEndRoundComparator(_fight, _import, vitessesInversees_));
        return listeTriee_;
    }

    static void sortFightersUsingMoveAmongList(
            Fight _fight, DataBase _import){
        TeamPositionList listeTriee_=_fight.getOrderedFighters();
        boolean vitessesInversees_=reverseSpeed(_fight,_import);
        listeTriee_.sortElts(new SortedFighterMoveActsComparator(_fight, _import, vitessesInversees_));
    }

    static void sortFightersSwitchingAmongList(
            Fight _fight,DataBase _import){
        TeamPositionList listeTriee_ = _fight.getOrderedFighters();
        listeTriee_.sortElts(new SortedFighterSwitchActsComparator(_fight, _import));
    }

    static void sortFightersBeingHealedAmongList(
            Fight _fight){
        TeamPositionList listeTriee_ = _fight.getOrderedFighters();
        listeTriee_.sortElts(new SortedFighterHealActsComparator(_fight));
    }

    static boolean reverseSpeed(Fight _fight,DataBase _import) {
        boolean vitessesInversees_=false;
        for(String c:FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttaque_.getEffet(i);
                if (effet_ instanceof EffectGlobal && ((EffectGlobal) effet_).getReverseOrderOfSortBySpeed()) {
                    vitessesInversees_ = true;
                    break;
                }
            }
        }
        return vitessesInversees_;
    }
    static NextUsers sortFightersByWornBerry(
            Fight _fight,
            TeamPositionList _cbts,DataBase _import){
        NextUsers retour_;
        retour_=new NextUsers(_cbts, new TeamPositionList());
        int nbCombattants_=_cbts.size();
        TeamPositionList itemUsers_ = retour_.getItemUsers();
        for(int i = IndexConstants.FIRST_INDEX; i<nbCombattants_; i++){
            int iFighter_ = i;
            iFighter_++;
            for(int i2_=iFighter_;i2_<nbCombattants_;i2_++){
                TeamPosition cbtOne_=retour_.getNextFighters().get(i);
                TeamPosition cbtTwo_=retour_.getNextFighters().get(i2_);
                Berry baie1_ = FightItems.useItsBerry(_fight, cbtOne_, _import);
                boolean baieGagnantPrioOne_ = lawForAttackFirst(baie1_);
                Berry baie2_ = FightItems.useItsBerry(_fight, cbtTwo_, _import);
                boolean baieGagnantPrioTwo_ = lawForAttackFirst(baie2_);
                if (baieGagnantPrioOne_ || !baieGagnantPrioTwo_) {
                    continue;
                }
                TeamPositionList fs_ = retour_.getNextFighters();
                TeamPosition one_ = fs_.get(i);
                TeamPosition two_ = fs_.get(i2_);
                fs_.set(i, two_);
                fs_.set(i2_, one_);
                addIfPossible(itemUsers_,cbtTwo_);
            }
        }
        return retour_;
    }

    private static boolean lawForAttackFirst(Berry _baie) {
        return _baie != null && _baie.getLawForAttackFirst();
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

    static TeamPositionList randomFigtherHavingToAct(Fight _fight,TeamPositionList _cbts,DataBase _import) {
        TeamPositionList tmp_ = fightersUsingMoveWithBerry(_fight,_cbts, _import);
        if(!tmp_.isEmpty()){
            return TeamPositionList.newList(tmp_.first());
        }
        if(_cbts.isEmpty()){
            return new TeamPositionList();
        }
        return randomFigtherHavingToActCh(_fight, _cbts, _import);
    }

    static TeamPositionList notKoFrontFightersBelongingToUser(Fight _fight, boolean _user) {
        TeamPositionList list_ = new TeamPositionList();
        for (TeamPosition f: fightersBelongingToUser(_fight, _user)) {
            Fighter fighter_ = _fight.getFighter(f);
            if (fighter_.estArriere()) {
                continue;
            }
            list_.add(f);
        }
        return list_;
    }

    static TeamPositionList notKoBackFightersBelongingToUser(Fight _fight, boolean _user) {
        TeamPositionList list_ = new TeamPositionList();
        for (TeamPosition f: fightersBelongingToUser(_fight, _user)) {
            Fighter fighter_ = _fight.getFighter(f);
            if (fighter_.estKo() || !fighter_.estArriere()) {
                continue;
            }
            list_.add(f);
        }
        return list_;
    }

    static TeamPositionList fightersUsingMoveWithBerry(Fight _fight,TeamPositionList _cbts,DataBase _import) {
        TeamPositionList fightersWithBerry_=new TeamPositionList();
        int nbCombattants_=_cbts.size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbCombattants_; i++){
            TeamPosition cbtOne_=_cbts.get(i);
            Fighter creatureOne_=_fight.getFighter(cbtOne_);
            if(!(creatureOne_.getAction() instanceof ActionMove)){
                continue;
            }
            Berry baie_ = FightItems.useItsBerry(_fight, cbtOne_, _import);
            boolean baieGagnantPrioOne_ = lawForAttackFirst(baie_);
            if (baieGagnantPrioOne_) {
                fightersWithBerry_.add(cbtOne_);
            }
        }
        return fightersWithBerry_;
    }

    static TeamPositionList fightersUsingMove(Fight _fight,TeamPositionList _cbts) {
        TeamPositionList fighters_=new TeamPositionList();
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

    static TeamPositionList fightersSwitching(Fight _fight,TeamPositionList _cbts) {
        TeamPositionList fighters_=new TeamPositionList();
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

    static TeamPositionList fightersBeingHealed(Fight _fight,TeamPositionList _cbts) {
        TeamPositionList fighters_=new TeamPositionList();
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

    static TeamPositionList randomFigtherHavingToActCh(Fight _fight, TeamPositionList _cbts, DataBase _import) {
        UsedItemForBattle indexRemoving_= indexOfRemovingItem(_fight, _cbts, _import);
        if(indexRemoving_.getItemForBattle() == null){
            return TeamPositionList.newList(_cbts.first());
        }
        return randomFigtherHavingToAct(_fight, _cbts, indexRemoving_, _import);
    }

    static UsedItemForBattle indexOfRemovingItem(Fight _fight,TeamPositionList _cbts, DataBase _import) {
        int nb_ = _cbts.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            TeamPosition cbt_ = _cbts.get(i);
            Item objet_ = FightItems.useItsObject(_fight, cbt_, _import);
            if (objet_ instanceof ItemForBattle && ((ItemForBattle) objet_).getLawForAttackFirst().events().size() > DataBase.ONE_POSSIBLE_CHOICE) {
                return new UsedItemForBattle((ItemForBattle) objet_,i, cbt_);
            }
        }
        return new UsedItemForBattle(null,IndexConstants.INDEX_NOT_FOUND_ELT, new TeamPosition());
    }

    static TeamPositionList randomFigtherHavingToAct(Fight _fight,
            TeamPositionList _cbts, UsedItemForBattle _index,DataBase _import) {
        TeamPosition fighter_ = _index.getTp();
        MonteCarloBoolean law_ = _index.getItemForBattle().getLawForAttackFirst();
        if (FightSuccess.isBadSimulation(_fight, law_)) {
            return new TeamPositionList();
        }
        boolean permuter_ = FightSuccess.random(_import, law_);
        if(permuter_){
            return TeamPositionList.newList(fighter_);
        }
        return TeamPositionList.newList(_cbts.first());
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

    static TeamPositionList fightersHavingToAct(Fight _fight, boolean _dernier,DataBase _import){
        TeamPositionList ls_ = new TeamPositionList();
//            if(_dernier != lastToUseMove(_fight,c,_import)){
//                continue;
//            }
        for(TeamPosition c:FightOrder.fighters(_fight)){
            Fighter creature_=_fight.getFighter(c);
            if (creature_.isActed() || ComparatorBoolean.diff(_dernier, lastToUseMove(_fight, c, _import)) || creature_.getAction() == null) {
                continue;
            }
            if (creature_.getAction().getKindAction() == KindAction.HEAL || !creature_.estKo()) {
                ls_.add(c);
            }
        }
        return ls_;
    }
    static boolean lastToUseMove(Fight _fight, TeamPosition _target,DataBase _import){
        Team equipeCbt_=_fight.getTeams().getVal(_target.getTeam());
        for(TeamPosition c:frontFighters(_fight)){
            Fighter creature_=_fight.getFighter(c);
            if (!creature_.isSuccessfulMove() || !cibleDerLanceur(_target, equipeCbt_, creature_)) {
                continue;
            }
            MoveData fAttaque_=_import.getMove(creature_.getFinalChosenMove());
            if (fAttaque_ != null && lastUsing(fAttaque_)) {
                return true;
            }
        }
        return false;
    }

    private static boolean lastUsing(MoveData _fAttaque) {
        int nbEffets_= _fAttaque.nbEffets();
        for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_= _fAttaque.getEffet(i);
            if(!(effet_ instanceof EffectOrder)){
                continue;
            }
            EffectOrder effetOrdre_=(EffectOrder)effet_;
            if(effetOrdre_.getTargetAttacksLast()){
                return true;
            }
        }
        return false;
    }

    private static boolean cibleDerLanceur(TeamPosition _target, Team _equipeCbt, Fighter _creature) {
        return !_creature.getChosenTargets().isEmpty() && _equipeCbt.fightersAtCurrentPlace(_creature.getChosenTargets().first()).containsObj(_target.getPosition());
    }

    static TeamPositionList fighters(Fight _fight){
        TeamPositionList cbts_ = new TeamPositionList();
        cbts_.addAllElts(fighters(_fight,Fight.CST_FOE));
        cbts_.addAllElts(fighters(_fight,Fight.CST_PLAYER));
        return cbts_;
    }

    static TeamPositionList fighters(Fight _fight,byte _noTeam) {
        TeamPositionList cbts_ = new TeamPositionList();
        Team equipe_=_fight.getTeams().getVal(_noTeam);
        for(byte c2_:equipe_.getMembers().getKeys()){
            cbts_.add(new TeamPosition(_noTeam,c2_));
        }
        return cbts_;
    }

    static TeamPositionList frontFighters(Fight _fight){
        TeamPositionList cbts_ = new TeamPositionList();
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
            if (!partenaire_.estKo() && partenaire_.estArriere()) {
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
            if (!partenaire_.estKo() && !partenaire_.estArriere()) {
                nbPartenairesArriere_++;
            }
        }
        return nbPartenairesArriere_;
    }
    static Bytes fightersWearingExpObject(
            Fight _fight,
            TeamPositionList _list, DataBase _import) {
        Bytes list_ = new Bytes();
        for (TeamPosition f: _list) {
            Fighter membre_= _fight.getFighter(f);
            if(membre_.estKo()){
                continue;
            }
            ItemForBattle objet_ = membre_.dataExpObject(_import);
            if (objet_ != null && objet_.getBoostExp()) {
                list_.add(f.getPosition());
            }
        }
        return list_;
    }

    static TeamPositionList fightersBelongingToUser(Fight _fight,
            boolean _user) {
        TeamPositionList list_ = new TeamPositionList();
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

    static ByteMap<Fighter> fighters(Fight _fight, byte _team, Bytes _list) {
        ByteMap<Fighter> m_ = new ByteMap<Fighter>();
        for (EntryCust<Byte, Fighter> f: _fight.getTeams().getVal(_team).getMembers().entryList()) {
            if (!_list.containsObj(f.getKey())) {
                continue;
            }
            m_.addEntry(f.getKey(),f.getValue());
        }
        return m_;
    }
    static TeamPositionList targetsEffect(Fight _fight,TeamPosition _lanceur,Effect _effet,Difficulty _diff,DataBase _import){
        return chosenTargets(_fight,_lanceur, _effet.getTargetChoice(), _diff, _import);
    }
    static TeamPositionList chosenTargets(Fight _fight,TeamPosition _lanceur,TargetChoice _choice,Difficulty _diff,DataBase _import){
        if (_choice == TargetChoice.NOTHING) {
            return new TeamPositionList();
        }
        if (_choice == TargetChoice.LANCEUR) {
            TeamPositionList cbts_ = new TeamPositionList();
            cbts_.add(_lanceur);
            return cbts_;
        }
        //fightersAtCurrentPlace
        if(_choice == TargetChoice.ALLIE){
            return allie(_fight, _lanceur);
        }
        if(_choice == TargetChoice.ALLIES){
            return allies(_fight, _lanceur);
        }
        if(_choice == TargetChoice.TOUS_ADV){
            return tousAdv(_fight, _lanceur);
        }
        if(_choice == TargetChoice.GLOBALE){
            return globale(_fight);
        }
        if(_choice == TargetChoice.PSEUDO_GLOBALE){
            return pseudoGlobale(_fight, _lanceur);
        }
        if (_choice == TargetChoice.ADJ_ADV) {
            return closestFigthersFoeTeam(_fight,_lanceur,_diff);
        }
        if (_choice == TargetChoice.ADJ_MULT) {
            return closestFigthers(_fight,_lanceur,_diff);
        }
        byte noEq_=_lanceur.getTeam();
        byte noEqAdv_=Fight.foe(noEq_);
        Team equipe_=_fight.getTeams().getVal(noEq_);
        Fighter creature_=equipe_.getMembers().getVal(_lanceur.getPosition());
        TargetCoordsList ciblesChoisies_=creature_.getChosenTargets();
        if(ciblesChoisies_.isEmpty()){
            return new TeamPositionList();
        }
        if(NumberUtil.eq(ciblesChoisies_.first().getTeam(),noEqAdv_)){
            //existence partenaire non ko de la cible initial vise. ce partenaire utilise PAR_ICI ou POUDREFUREUR
            return closestFoeFighterProc(_fight, _lanceur, _import, ciblesChoisies_);
        }
        Bytes cbtsEq_=equipe_.fightersAtCurrentPlace(ciblesChoisies_.first());
        if(cbtsEq_.isEmpty()){
            return new TeamPositionList();
        }
        TeamPositionList cbts_ = new TeamPositionList();
        cbts_.add(new TeamPosition(noEq_,cbtsEq_.first()));
        return cbts_;
    }

    private static TeamPositionList closestFoeFighterProc(Fight _fight, TeamPosition _lanceur, DataBase _import, TargetCoordsList _ciblesChoisies) {
        byte noEq_=_lanceur.getTeam();
        byte noEqAdv_=Fight.foe(noEq_);
        Team foeTeam_ = _fight.getTeams().getVal(noEqAdv_);
        ByteMap<Fighter> membres_=foeTeam_.getMembers();
        ByteMap<Fighter> combattantsAttirant_ = combattantsAttirant(_import, membres_);
        //attraction des effets
        if(!combattantsAttirant_.isEmpty()){
            TeamPositionList cbtsModif_= closestFoeFightersAmongList(noEqAdv_,combattantsAttirant_, _fight.getFighter(_lanceur).getGroundPlace());
            Bytes cbtsListe_ = new Bytes();
            ByteMap<TeamPosition> positions_ = new ByteMap<TeamPosition>();
            for(TeamPosition c:cbtsModif_){
                Fighter creatureCbt_=membres_.getVal(c.getPosition());
                byte key_= creatureCbt_.getGroundPlace();
                cbtsListe_.add(key_);
                positions_.put(key_, c);
            }
            cbtsListe_.sort();
            TeamPositionList cbts_ = new TeamPositionList();
            for (byte p: cbtsListe_) {
                cbts_.add(positions_.getVal(p));
            }
            return cbts_;
        }
        Bytes cbtsEq_=foeTeam_.fightersAtCurrentPlace(_ciblesChoisies.first());
        TeamPositionList cbts_ = new TeamPositionList();
        if(cbtsEq_.isEmpty()){
            cbts_=closestFoeFighter(_fight, _lanceur);
        }else{
            cbts_.add(new TeamPosition(noEqAdv_,cbtsEq_.first()));
        }
        return cbts_;
    }

    private static ByteMap<Fighter> combattantsAttirant(DataBase _import, ByteMap<Fighter> _membres) {
        ByteMap<Fighter> combattantsAttirant_ = new ByteMap<Fighter>();
        for(byte c: _membres.getKeys()){
            Fighter creatureCbt_= _membres.getVal(c);
            if (creatureCbt_.estArriere() || !creatureCbt_.isSuccessfulMove()) {
                continue;
            }
            String attaqueCbt_=creatureCbt_.getFinalChosenMove();
            if (getPointViewChangementType(attaqueCbt_, _import) == PointViewChangementType.ATTRACT_DAMAGES_MOVES) {
                combattantsAttirant_.addEntry(c,creatureCbt_);
            }
        }
        return combattantsAttirant_;
    }

    private static TeamPositionList pseudoGlobale(Fight _fight, TeamPosition _lanceur) {
        TeamPositionList cbts_ = new TeamPositionList();
        byte noEq_= _lanceur.getTeam();
        byte noEqAdv_=Fight.foe(noEq_);
        ByteMap<Fighter> membres_= _fight.getTeams().getVal(noEqAdv_).getMembers();
        for(byte c2_:membres_.getKeys()){
            Fighter membre_=membres_.getVal(c2_);
            if(!membre_.estArriere()){
                cbts_.add(new TeamPosition(noEqAdv_,c2_));
            }
        }
        membres_= _fight.getTeams().getVal(noEq_).getMembers();
        for(byte c2_:membres_.getKeys()){
            if(NumberUtil.eq(c2_, _lanceur.getPosition())){
                continue;
            }
            Fighter membre_=membres_.getVal(c2_);
            if(!membre_.estArriere()){
                cbts_.add(new TeamPosition(noEq_,c2_));
            }
        }
        return cbts_;
    }

    private static TeamPositionList globale(Fight _fight) {
        TeamPositionList cbts_ = new TeamPositionList();
        for(byte c: _fight.getTeams().getKeys()){
            ByteMap<Fighter> membres_= _fight.getTeams().getVal(c).getMembers();
            for(byte c2_:membres_.getKeys()){
                Fighter membre_=membres_.getVal(c2_);
                if(!membre_.estArriere()){
                    cbts_.add(new TeamPosition(c,c2_));
                }
            }
        }
        return cbts_;
    }

    private static TeamPositionList tousAdv(Fight _fight, TeamPosition _lanceur) {
        byte noEqAdv_=Fight.foe(_lanceur.getTeam());
        return equipe(_fight, noEqAdv_);
    }

    private static TeamPositionList allies(Fight _fight, TeamPosition _lanceur) {
        byte noEq_= _lanceur.getTeam();
        return equipe(_fight, noEq_);
    }

    private static TeamPositionList equipe(Fight _fight, byte _noEq) {
        TeamPositionList cbts_ = new TeamPositionList();
        ByteMap<Fighter> membres_= _fight.getTeams().getVal(_noEq).getMembers();
        for(byte c:membres_.getKeys()){
            Fighter membre_=membres_.getVal(c);
            if(!membre_.estArriere()){
                cbts_.add(new TeamPosition(_noEq,c));
            }
        }
        return cbts_;
    }

    private static TeamPositionList allie(Fight _fight, TeamPosition _lanceur) {
        TeamPositionList cbts_ = new TeamPositionList();
        byte noEq_= _lanceur.getTeam();
        Team equipe_= _fight.getTeams().getVal(noEq_);
        Fighter creature_=equipe_.getMembers().getVal(_lanceur.getPosition());
        TargetCoordsList ciblesChoisies_=creature_.getChosenTargets();
        if(ciblesChoisies_.isEmpty()){
            return cbts_;
        }
        Bytes cbtsEq_=equipe_.fightersAtCurrentPlace(ciblesChoisies_.first());
        if(cbtsEq_.isEmpty()){
            return cbts_;
        }
        cbts_.add(new TeamPosition(noEq_,cbtsEq_.first()));
        return cbts_;
    }

    static TeamPositionList closestFigthers(Fight _fight,TeamPosition _combattant,Difficulty _diff){
        TeamPositionList cbts_ = closestFigthersFoeTeam(_fight,_combattant,_diff);
        cbts_.addAllElts(closestFigthersSameTeam(_fight,_combattant,_diff));
        return cbts_;
    }

    static TeamPositionList closestFigthersFoeTeam(Fight _fight,TeamPosition _combattant,Difficulty _diff){
        byte noEq_=_combattant.getTeam();
        byte noEqAdv_=Fight.foe(noEq_);
        byte fighterPlace_ = _fight.getFighter(_combattant).getGroundPlace();
        TeamPositionList cbts_ = closestFigthersTeam(fighterPlace_, noEqAdv_, _fight.getTeams().getVal(noEqAdv_), _diff);
        if(cbts_.isEmpty()){
            cbts_=closestFoeFighter(_fight,_combattant);
        }
        return cbts_;
    }

    static TeamPositionList closestFigthersSameTeam(Fight _fight,TeamPosition _combattant,Difficulty _diff){
        byte noEq_=_combattant.getTeam();
        byte fighterPlace_ = _fight.getFighter(_combattant).getGroundPlace();
        TeamPositionList cbts_ = closestFigthersTeam(fighterPlace_, noEq_, _fight.getTeams().getVal(noEq_), _diff);
        cbts_.removeObj(_combattant);
        return cbts_;
    }

    static TeamPositionList closestFigthersTeam(byte _fighterPlace,byte _noTeam, Team _team, Difficulty _diff) {
        TeamPositionList cbts_ = new TeamPositionList();
        ByteMap<Fighter> membresAdv_=_team.getMembers();
        for(byte c:membresAdv_.getKeys()){
            Fighter membre_=membresAdv_.getVal(c);
            if(membre_.estArriere()){
                continue;
            }
            int diff_=membre_.getGroundPlace()-_fighterPlace;
            if(NumberUtil.abs(diff_) <= 1||!_diff.getEnabledClosing()){
                cbts_.add(new TeamPosition(_noTeam,c));
            }
        }
        return cbts_;
    }

    static TeamPositionList closestFoeFighter(Fight _fight,TeamPosition _cbt){
        byte noEquipe_=_cbt.getTeam();
        byte noEquipeAdv_=Fight.foe(noEquipe_);
        Team equipeAdvCbt_=_fight.getTeams().getVal(noEquipeAdv_);
        ByteMap<Fighter> membresEquipeAdv_=equipeAdvCbt_.getMembers();
        return closestFoeFightersAmongList(noEquipeAdv_,membresEquipeAdv_, _fight.getFighter(_cbt).getGroundPlace());
    }

    static TeamPositionList closestFoeFightersAmongList(byte _noEquipeAdv, ByteMap<Fighter> _liste, byte _posCbt){
        Bytes posAdv_ = new Bytes();
        byte diff_=Fighter.BACK;
        for(EntryCust<Byte, Fighter> e:_liste.entryList()){
            Fighter membre_=e.getValue();
            if(!membre_.estArriere()){
                byte posCbtAdv_=membre_.getGroundPlace();
                posAdv_.add(posCbtAdv_);
            }
        }
        for(byte e:posAdv_){
            int currDiff_ = NumberUtil.abs(e- _posCbt);
            if (NumberUtil.eq(diff_, Fighter.BACK) || diff_ > currDiff_) {
                diff_ = (byte) currDiff_;
            }
        }
        TeamPositionList cbtsProches_ = new TeamPositionList();
        for(EntryCust<Byte, Fighter> c:_liste.entryList()){
            Fighter membre_=c.getValue();
            if (!membre_.estArriere() && NumberUtil.abs(membre_.getGroundPlace() - _posCbt) == diff_) {
                cbtsProches_.add(new TeamPosition(_noEquipeAdv, c.getKey()));
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
