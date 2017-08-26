package aiki.game.fight;
import aiki.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingStatus;
import aiki.fight.items.Item;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.actions.ActionHeal;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.enums.FightType;
import aiki.game.params.Difficulty;
import aiki.game.player.Inventory;
import aiki.game.player.Player;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.EqList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;

final class FightRules {

    private FightRules() {
    }

    static StringList allowedMoves(Fight _fight, TeamPosition _combattant,DataBase _import){
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        Fighter creatureCbt_=equipe_.getMembers().getVal(_combattant.getPosition());
        StringList liste_=new StringList();
        for(String c:creatureCbt_.attaquesUtilisables()){
            if(creatureCbt_.powerPointsMove(c)>0){
                liste_.add(c);
            }
        }
        StringList usablesMoves_ = new StringList(liste_);
        String lastUsedMove_ = creatureCbt_.getUsedMoveLastRound();
        if (!lastUsedMove_.isEmpty()) {
            if(creatureCbt_.isNeedingToRecharge()||creatureCbt_.getNbPrepaRound()>0){
                liste_.clear();
                liste_.add(lastUsedMove_);
            }
        }
        liste_.removeDuplicates();
        for(TeamPosition c:FightOrder.frontFighters(_fight)){
            Team equipeloc_=_fight.getTeams().getVal(c.getTeam());
            Fighter creatureCbtLoc_=equipeloc_.getMembers().getVal(c.getPosition());
            for(MoveTeamPosition c2_:creatureCbtLoc_.getPrivateMoves().getKeys()){
                if (!TeamPosition.eq(c2_.getTeamPosition(), _combattant)) {
                    continue;
                }
                liste_.removeAllElements(creatureCbtLoc_.getPrivateMoves().getVal(c2_));
            }
            for(MoveTeamPosition c2_:creatureCbtLoc_.enabledRelationsMoves()){
                MoveData fAttaque_=_import.getMove(c2_.getMove());
                int nbEffets_=fAttaque_.nbEffets();
                for(int i=CustList.FIRST_INDEX;i<nbEffets_;i++){
                    Effect effet_=fAttaque_.getEffet(i);
                    if(!(effet_ instanceof EffectRestriction)){
                        continue;
                    }
                    EffectRestriction effetAntiChoix_=(EffectRestriction)effet_;
                    if (effetAntiChoix_.getChoiceRestriction() == MoveChoiceRestrictionType.FORBIDDEN) {
                        liste_.removeString(creatureCbtLoc_.getTrackingMoves().getVal(c2_).getMove());
                    }
                }
            }
            for(MoveTeamPosition c2_:creatureCbtLoc_.enabledRelationsMoves()){
                MoveData fAttaque_=_import.getMove(c2_.getMove());
                int nbEffets_=fAttaque_.nbEffets();
                for(int i=CustList.FIRST_INDEX;i<nbEffets_;i++){
                    Effect effet_=fAttaque_.getEffet(i);
                    if(!(effet_ instanceof EffectRestriction)){
                        continue;
                    }
                    EffectRestriction effetAntiChoix_=(EffectRestriction)effet_;
                    if (effetAntiChoix_.getChoiceRestriction() == MoveChoiceRestrictionType.FORCE) {
                        liste_.clear();
                        liste_.add(creatureCbtLoc_.getTrackingMoves().getVal(c2_).getMove());
                    }
                }
            }
            if(!Numbers.eq(c.getTeam(),_combattant.getTeam())){
                for(String c2_:equipeloc_.enabledTeamMoves()){
                    MoveData fAttaque_=_import.getMove(c2_);
                    int nbEffets_=fAttaque_.nbEffets();
                    for(int i=CustList.FIRST_INDEX;i<nbEffets_;i++){
                        Effect effet_=fAttaque_.getEffet(i);
                        if(!(effet_ instanceof EffectTeam)){
                            continue;
                        }
                        EffectTeam effetEq_=(EffectTeam)effet_;
                        liste_.removeAllElements(effetEq_.getUnusableMoves());
                    }
                }
            }
        }
        if (creatureCbt_.getEnabledMovesConstChoices().contains(lastUsedMove_)) {
            if(creatureCbt_.getEnabledMovesConstChoices().getVal(lastUsedMove_).isEnabled()){
                liste_.clear();
                liste_.add(lastUsedMove_);
            }
        }
        boolean lance_=false;
        for (NumberMap<Byte,Anticipation> m: equipe_.getMovesAnticipationValues()) {
            for (byte p: m.getKeys()) {
                if (!Numbers.eq(creatureCbt_.getGroundPlace(),p)) {
                    continue;
                }
                if(Numbers.eq(m.getVal(p).getTargetPosition().getPosition(),Fighter.BACK)){
                    continue;
                }
                lance_=true;
                break;
            }
            if (lance_) {
                break;
            }
        }
        if(lance_){
            liste_.removeAllElements(_import.getMovesAnticipation());
        }
        for(String c:creatureCbt_.enabledIndividualMoves()){
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for(int i=CustList.FIRST_INDEX;i<nbEffets_;i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectRestriction)){
                    continue;
                }
                EffectRestriction effetAntiChoix_=(EffectRestriction)effet_;
                if (effetAntiChoix_.getChoiceRestriction() == MoveChoiceRestrictionType.DER) {
                    liste_.removeString(creatureCbt_.getLastSuccessfulMove());
                } else if (effetAntiChoix_.getChoiceRestriction() == MoveChoiceRestrictionType.CATEGORIE_AUTRE) {
                    StringList notDamage_ = new StringList();
                    for(String e: liste_){
                        MoveData fAttaqueInterdite_=_import.getMove(e);
                        if(!(fAttaqueInterdite_ instanceof DamagingMoveData)){
                            notDamage_.add(e);
                        }
                    }
                    liste_.removeAllElements(notDamage_);
                }
            }
        }
        for(String c: FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for(int i=CustList.FIRST_INDEX;i<nbEffets_;i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGl_=(EffectGlobal)effet_;
                liste_.removeAllElements(effetGl_.getUnusableMoves());
            }
        }
        if (FightOrder.nbBackPartners(_fight,_combattant) == 0) {
            liste_.removeAllElements(_import.getMovesFullHeal());
        }
        liste_.removeString(_import.getDefaultMove());
        liste_.retainAllElements(usablesMoves_);
        return liste_;
    }

    /** _fight.getFirstPositPlayerFighters() has no duplicate for the &quot;no back&quot; values
    the &quot;no back&quot; values are different from ally places (subst ground place)*/
    static boolean substitutable(Fight _fight, Difficulty _diff, DataBase _import){
        boolean error_ = false;
        boolean autoriseEchangePositionFinTour_=_diff.getAllowedSwitchPlacesEndRound();
        Numbers<Byte> places_ = new Numbers<Byte>();
        Numbers<Byte> usedPlaces_ = new Numbers<Byte>();
        Numbers<Byte> playerPlaces_ = new Numbers<Byte>();
        byte nbPkNonKo_=0;
        for(TeamPosition c: FightOrder.fightersBelongingToUser(_fight,true)){
            Fighter creature_ = _fight.getFighter(c);
            String name_ = _import.translatePokemon(creature_.getName());
            byte currentPos_ = _fight.getFirstPositPlayerFighters().getVal(c.getPosition());
            byte c_ = currentPos_;
            if(creature_.estKo()){
                if(!Numbers.eq(c_,Fighter.BACK)){
                    _fight.addMessage(Fight.ERR_SUBSTITUTE_KO_END_ROUND, name_);
                    error_ = true;
                }
                continue;
            }
            nbPkNonKo_++;
            if(Numbers.eq(c_,Fighter.BACK)){
                continue;
            }
            boolean belong_ = true;
            for(TeamPosition k: FightOrder.fightersBelongingToUser(_fight,false)){
                byte currentPosPart_ = _fight.getFirstPositPlayerFighters().getVal(k.getPosition());
                Fighter partner_ = _fight.getFighter(k);
//                if (Numbers.eq(partner_.getGroundPlaceSubst(), c_)) {
//                    belong_ = false;
//                }
                if (Numbers.eq(currentPosPart_, currentPos_)) {
                    belong_ = false;
                }
                if (!Numbers.eq(partner_.getGroundPlaceSubst(), Fighter.BACK)) {
                    usedPlaces_.add(partner_.getGroundPlaceSubst());
                }
            }
            if (!belong_) {
                _fight.addMessage(Fight.ERR_SUBSTITUTE_BELONG);
                error_ = true;
            }
            if (!autoriseEchangePositionFinTour_ && _fight.getFightType() != FightType.TMP_TRAINER) {
                if (!Numbers.eq(c_,creature_.getGroundPlace())){
                    if (!creature_.estArriere()) {
                        _fight.addMessage(Fight.ERR_SUBSTITUTE_NO_SWITCH_PLACE);
                        error_ = true;
                    }
                }
            }
            places_.add(currentPos_);
            playerPlaces_.add(currentPos_);
        }
        for(TeamPosition k: FightOrder.fightersBelongingToUser(_fight,false)){
            byte currentPosPart_ = _fight.getFirstPositPlayerFighters().getVal(k.getPosition());
            if (!Numbers.eq(currentPosPart_, Fighter.BACK)) {
                places_.add(currentPosPart_);
            }
        }
        places_.sort();
        //increasing
        int nbPl_ = places_.size();
        for(byte i=CustList.SECOND_INDEX;i<nbPl_;i++){
            if(Numbers.eq(places_.get(i - 1),places_.get(i))){
                _fight.addMessage(Fight.ERR_SUBSTITUTE_PLACE, places_.get(i));
                error_ = true;
            }
        }
        if(places_.isEmpty()){
            _fight.addMessage(Fight.ERR_SUBSTITUTE_USED_PLACE);
            return false;
        }
        /*if(nbPkNonKo_>_fight.getPlayerMaxNumberFrontFighters()) {
            if (usedPlaces_.isEmpty() || usedPlaces_.getMaximum() >= places_.getMaximum()) {
                if (places_.getMaximum().intValue() != places_.size() - 1) {
                    _fight.addMessage(Fight.ERR_SUBSTITUTE_USED_PLACE);
                    return false;
                }
            }
        }*/
//        if(nbPkNonKo_>_fight.getPlayerMaxNumberFrontFighters()&&places_.size()!=_fight.getPlayerMaxNumberFrontFighters()){
//            _fight.addMessage(Fight.ERR_SUBSTITUTE_USED_PLACE);
//            return false;
//        }
        if(nbPkNonKo_>_fight.getPlayerMaxNumberFrontFighters()&&places_.size()!=_fight.getMult()){
            _fight.addMessage(Fight.ERR_SUBSTITUTE_USED_PLACE);
            return false;
        }
        if(nbPkNonKo_<=_fight.getPlayerMaxNumberFrontFighters() && playerPlaces_.size()!=nbPkNonKo_){
            _fight.addMessage(Fight.ERR_SUBSTITUTE_USED_PLACE);
            return false;
        }
        if (playerPlaces_.size() > _fight.getPlayerMaxNumberFrontFighters()) {
            _fight.addMessage(Fight.ERR_SUBSTITUTE_USED_PLACE);
            return false;
        }
        return !error_;
    }

    static boolean playable(Fight _fight, Player _utilisateur,Difficulty _diff,DataBase _import){
        Inventory inv_=_utilisateur.getInventory();
        boolean error_ = false;
        //String retour_=DataBase.EMPTY_STRING;
        Team equipeUt_=_fight.getUserTeam();
        Numbers<Byte> remplacants_=new Numbers<Byte>();
        StringMap<LgInt> utilisationsObjets_=new StringMap<LgInt>();
        for(byte c:equipeUt_.getMembers().getKeys()){
            Fighter creature_=equipeUt_.getMembers().getVal(c);
            if (!creature_.isBelongingToPlayer()) {
                continue;
            }
            if(Numbers.eq(creature_.getGroundPlaceSubst(),Fighter.BACK)){
                continue;
            }
            if(!Numbers.eq(creature_.getSubstistute(),Fighter.BACK)){
                if(!remplacants_.containsObj(creature_.getSubstistute())){
                    remplacants_.add(creature_.getSubstistute());
                }else{
                    error_ = true;
                    Fighter fighter_ = equipeUt_.getMembers().getVal(creature_.getSubstistute());
                    String name_ = _import.translatePokemon(fighter_.getName());
                    _fight.addMessage(Fight.ERR_SUBSTITUTE, name_);
                }
            }
        }
        byte nbActions_=0;
        //byte nbNonKo_=0;
        byte nb_ = 0;
        //byte nbFront_ = 0;
        boolean existFrontWithoutAct_ = false;
        for(byte c:equipeUt_.getMembers().getKeys()){
            Fighter creature_=equipeUt_.getMembers().getVal(c);
            if (!creature_.isBelongingToPlayer()) {
                continue;
            }
            nb_++;
            int nbPreviousAct_ = nbActions_;
            if(creature_.getAction() instanceof ActionMove){
                nbActions_++;
            }
            if(creature_.getAction() instanceof ActionSwitch){
                nbActions_++;
            }
            if(creature_.getAction() instanceof ActionHeal){
                nbActions_++;
            }
//            if(!creature_.estKo()){
//                nbNonKo_++;
//            }
            if (!creature_.estArriere()) {
                if (nbPreviousAct_ == nbActions_) {
                    existFrontWithoutAct_ = true;
                    continue;
                }
            }
            String name_ = _import.translatePokemon(creature_.getName());
            if(creature_.getAction() instanceof ActionMove){
                if(creature_.estArriere()){
                    error_ = true;
                    _fight.addMessage(Fight.ERR_BACK_MOVE, name_);
                    continue;
                }
                String attaque_=creature_.getFirstChosenMove();
                String moveName_ = _import.translateMove(attaque_);
                StringList attaquesAutorisees_=FightFacade.allowedMovesNotEmpty(_fight,Fight.toUserFighter(c),_import);
                if(attaquesAutorisees_.containsObj(attaque_)){
                    if (_import.getMovesFullHeal().containsObj(attaque_) || _import.isBatonPassMove(attaque_)) {
                        if(Numbers.eq(creature_.getSubstistute(),Fighter.BACK)){
                            error_ = true;
                            _fight.addMessage(Fight.ERR_SWITCH, name_);
                            continue;
                        }
                        Fighter partenaire_=equipeUt_.getMembers().getVal(creature_.getSubstistute());
                        String namePart_ = _import.translatePokemon(partenaire_.getName());
                        if(partenaire_.estKo()){
                            error_ = true;
                            _fight.addMessage(Fight.ERR_KO_SUBSTITUTE, namePart_);
                        }
                        if (!partenaire_.isBelongingToPlayer()) {
                            error_ = true;
                            _fight.addMessage(Fight.ERR_BELONG_SWITCH, namePart_);
                        }
                        if(!partenaire_.estArriere()){
                            error_ = true;
                            _fight.addMessage(Fight.ERR_FRONT_SWITCH, namePart_);
                        }
                    }
                    EqList<TargetCoords> cibles_=creature_.getChosenTargets();
                    MoveData fAtt_=_import.getMove(attaque_);
                    //If the player selects a fighter then a move,
                    //there are two cases:
                    //1. the target is not chosable.
                    //the move is selected and the action is validated
                    //2. the target is chosable.
                    //For validating, the player has to select a target or a move such that a single target is selectable
                    if(!cibles_.isEmpty()){
                        if(fAtt_.getTargetChoice() == TargetChoice.ADJ_UNIQ){
                            byte noTeam_ = (byte) cibles_.first().getTeam();
                            Team equipeCible_=_fight.getTeams().getVal(noTeam_);
                            Numbers<Byte> cbts_=equipeCible_.fightersAtCurrentPlace(cibles_.first().getPosition());
                            if(cbts_.size() != DataBase.ONE_POSSIBLE_CHOICE){
                                error_ = true;
                                _fight.addMessage(Fight.ERR_NO_CHOSEN_TARGET, moveName_, name_);
                                continue;
                            }
                            if(!FightOrder.closestFigthers(_fight,Fight.toUserFighter(c),_diff).containsObj(new TeamPosition(noTeam_,cbts_.first()))){
                                error_ = true;
                                _fight.addMessage(Fight.ERR_TOO_FAR_TARGET, moveName_, name_);
                                continue;
                            }
                        } else if (fAtt_.getTargetChoice() == TargetChoice.ALLIE) {
                            if (!Numbers.eq(cibles_.first().getTeam(), Fight.PLAYER)) {
                                error_ = true;
                                _fight.addMessage(Fight.ERR_BAD_CHOICE, moveName_, name_);
                            }
                        } else if (fAtt_.getTargetChoice() == TargetChoice.AUTRE_UNIQ) {
                            byte noTeam_ = (byte) cibles_.first().getTeam();
                            Team equipeCible_=_fight.getTeams().getVal(noTeam_);
                            Numbers<Byte> cbts_=equipeCible_.fightersAtCurrentPlace(cibles_.first().getPosition());
                            if(cbts_.size() != DataBase.ONE_POSSIBLE_CHOICE){
                                error_ = true;
                                _fight.addMessage(Fight.ERR_NO_CHOSEN_TARGET, moveName_, name_);
                                continue;
                            }
                            if (TeamPosition.eq(Fight.toUserFighter(c), new TeamPosition(noTeam_,cbts_.first()))) {
                                error_ = true;
                                _fight.addMessage(Fight.ERR_BAD_CHOICE, moveName_, name_);
                            }
                        } else if (fAtt_.getTargetChoice() == TargetChoice.ANY_FOE) {
                            if (Numbers.eq(cibles_.first().getTeam(), Fight.PLAYER)) {
                                error_ = true;
                                _fight.addMessage(Fight.ERR_BAD_CHOICE, moveName_, name_);
                            }
                        }
                    }
                }else{
                    error_ = true;
                    _fight.addMessage(Fight.ERR_UNUSABLE_MOVE, moveName_);
                }
                continue;
            }
            if(creature_.getAction() instanceof ActionSwitch){
                if(creature_.estArriere()){
                    error_ = true;
                    _fight.addMessage(Fight.ERR_BACK_SWITCH, name_);
                }
                if(Numbers.eq(creature_.getSubstistute(),Fighter.BACK)){
                    error_ = true;
                    _fight.addMessage(Fight.ERR_SWITCH, name_);
                    continue;
                }
                Fighter partenaire_=equipeUt_.getMembers().getVal(creature_.getSubstistute());
                String namePart_ = _import.translatePokemon(partenaire_.getName());
                if(partenaire_.estKo()){
                    error_ = true;
                    _fight.addMessage(Fight.ERR_KO_SUBSTITUTE, namePart_);
                }
                if (!partenaire_.isBelongingToPlayer()) {
                    error_ = true;
                    _fight.addMessage(Fight.ERR_BELONG_SWITCH, namePart_);
                }
                if(!partenaire_.estArriere()){
                    error_ = true;
                    _fight.addMessage(Fight.ERR_FRONT_SWITCH, namePart_);
                }
                continue;
            }
            if(creature_.getAction() instanceof ActionHeal){
                if(creature_.getChosenHealingItem().isEmpty()){
                    error_ = true;
                    _fight.addMessage(Fight.ERR_NO_ITEM, name_);
                    continue;
                }
                if(!utilisationsObjets_.contains(creature_.getChosenHealingItem())){
                    utilisationsObjets_.put(creature_.getChosenHealingItem(),LgInt.one());
                }else{
                    utilisationsObjets_.getVal(creature_.getChosenHealingItem()).increment();
                }
                continue;
            }
        }
        for(String c:utilisationsObjets_.getKeys()){
            if (LgInt.strGreater(utilisationsObjets_.getVal(c), inv_.getNumber(c))) {
                error_ = true;
                String item_ = _import.translateItem(c);
                _fight.addMessage(Fight.ERR_TOO_MANY_ITEMS, item_);
            }
        }
        for(byte c:equipeUt_.getMembers().getKeys()){
            Fighter creature_=equipeUt_.getMembers().getVal(c);
            if(creature_.getAction() instanceof ActionHeal){
                String name_ = _import.translatePokemon(creature_.getName());
                if(creature_.getChosenHealingItem().isEmpty()){
                    continue;
                }
                String nomObjet_=creature_.getChosenHealingItem();
                String itemNameTr_ = _import.translateItem(nomObjet_);
                Item objet_=_import.getItem(nomObjet_);
                if(objet_ instanceof Berry){
                    Berry baie_=(Berry)objet_;
                    String attaque_=creature_.getFirstChosenMove();
                    boolean agit_=false;
                    if(attaque_.isEmpty()){
                        if(!baie_.getMultStat().isEmpty()){
                            for(Statistic c2_:baie_.getMultStat().getKeys()){
                                byte varBase_=baie_.getMultStat().getVal(c2_).getBoost();
                                byte var_= FightEffects.deltaBoostStatistic(_fight,Fight.toUserFighter(c),c2_,varBase_,_import);
                                if(var_>0){
                                    agit_=true;
                                    break;
                                }
                            }
                        }
                        if(!baie_.getHealHp().isZero()||!baie_.getHealHpRate().isZero()||!baie_.getHealHpBySuperEffMove().isZero()){
                            Rate pvRestants_=creature_.getRemainingHp();
                            Rate pvMax_=creature_.pvMax();
                            if(Rate.strLower(pvRestants_,pvMax_)){
                                agit_=true;
                            }
                        }
                        StringList statuts_=new StringList();
                        for(String s:creature_.getStatusSet()){
                            if(Numbers.eq(creature_.getStatusNbRoundShort(s), 0)){
                                continue;
                            }
                            if(baie_.getHealStatus().containsObj(s)){
                                statuts_.add(s);
                            }
                        }
                        if(!statuts_.isEmpty()){
                            agit_=true;
                        }
                        if(!agit_){
                            error_ = true;
                            _fight.addMessage(Fight.ERR_NO_EFFECT, itemNameTr_, name_);
                        }
                    }else{
                        short var_=creature_.healedPpMove(attaque_,nomObjet_,_import);
                        if(var_==0){
                            error_ = true;
                            _fight.addMessage(Fight.ERR_NO_EFFECT, itemNameTr_, name_);
                        }
                    }
                }
                if(objet_ instanceof HealingItem){
                    HealingItem soin_=(HealingItem)objet_;
                    if(soin_ instanceof HealingHp){
                        Rate pvMax_=creature_.pvMax();
                        Rate pvRestants_=creature_.getRemainingHp();
                        boolean agit_=false;
                        if(Rate.strLower(pvRestants_,pvMax_)){
                            agit_=true;
                        }
                        if(!agit_){
                            error_ = true;
                            _fight.addMessage(Fight.ERR_NO_EFFECT, itemNameTr_, name_);
                        }
                    }
                    if(soin_ instanceof HealingPp){
                        HealingPp soinpp_=(HealingPp)soin_;
                        if(creature_.getFirstChosenMove().isEmpty()&&(soinpp_.getHealedMovePp()>0||soinpp_.getHealingMoveFullpp())){
                            error_ = true;
                            _fight.addMessage(Fight.ERR_NO_HEALED_MOVE, itemNameTr_, name_);
                        }
                        boolean agit_=false;
                        if(soinpp_.getHealedMovePp()>0||soinpp_.getHealingMoveFullpp()){
                            short var_=creature_.healedPpMove(creature_.getFirstChosenMove(),nomObjet_,_import);
                            if(var_>0){
                                agit_=true;
                            }
                        }else{
                            for(String c2_:creature_.getCurrentMovesSet()){
                                short var_=creature_.healedPpMove(c2_,nomObjet_,_import);
                                if(var_>0){
                                    agit_=true;
                                    break;
                                }
                            }
                        }
                        if(!agit_){
                            error_ = true;
                            _fight.addMessage(Fight.ERR_NO_EFFECT, itemNameTr_, name_);
                        }
                    }
                    if(soin_ instanceof HealingStatus){
                        HealingStatus soinStatut_=(HealingStatus)soin_;
                        boolean agit_=false;
                        StringList statuts_=new StringList();
                        for(String c2_:creature_.getStatusSet()){
                            if(creature_.getStatusNbRoundShort(c2_) == 0){
                                continue;
                            }
                            if(soinStatut_.getStatus().containsObj(c2_)){
                                statuts_.add(c2_);
                            }
                        }
                        if(!statuts_.isEmpty()){
                            agit_=true;
                        }
                        if(soin_ instanceof HealingHpStatus){
                            HealingHpStatus soinPvStatut_=(HealingHpStatus)soin_;
                            if(ComparatorBoolean.eq(soinPvStatut_.getHealingKo(), creature_.estKo())){
                                agit_=true;
                            }
                        }
                        if(!agit_){
                            error_ = true;
                            _fight.addMessage(Fight.ERR_NO_EFFECT, itemNameTr_, name_);
                        }
                    }
                }
            }
        }
        if (nb_ <= _fight.getPlayerMaxNumberFrontFighters()) {
            if (existFrontWithoutAct_) {
                error_ = true;
                //All front pk must act, it is sufficient
                _fight.addMessage(Fight.ERR_TOO_FEW_ACTIONS);
            }
//            if (nbActions_ != nb_) {
//                //All front pk must act, it is sufficient
//                error_ = true;
//                _fight.addMessage(Fight.ERR_TOO_FEW_ACTIONS);
//            }
        } else {
            if (nbActions_ != _fight.getPlayerMaxNumberFrontFighters()) {
                error_ = true;
                _fight.addMessage(Fight.ERR_TOO_MANY_ACTIONS, _fight.getPlayerMaxNumberFrontFighters(), nbActions_);
            }
        }
//        if (nbNonKo_ <= _fight.getPlayerMaxNumberFrontFighters()) {
//            if (nbActions_ != nbNonKo_) {
//                error_ = true;
//                _fight.addMessage(Fight.ERR_TOO_FEW_ACTIONS);
//            }
//        } else {
//            if (nbActions_ != _fight.getPlayerMaxNumberFrontFighters()) {
//                error_ = true;
//                _fight.addMessage(Fight.ERR_TOO_MANY_ACTIONS, _fight.getPlayerMaxNumberFrontFighters(), nbActions_);
//            }
//        }
        //_fight.setRecentComment(retour_);
//        return retour_.isEmpty();
        return !error_;
    }
}
