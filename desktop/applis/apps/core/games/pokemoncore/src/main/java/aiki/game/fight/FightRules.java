package aiki.game.fight;
import aiki.db.DataBase;
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
import aiki.util.TargetCoordsList;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

final class FightRules {

    private FightRules() {
    }

    static StringList allowedMoves(Fight _fight, TeamPosition _combattant,DataBase _import){
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        Fighter creatureCbt_=equipe_.getMembers().getVal(_combattant.getPosition());
        StringList liste_ = attaquesUtilisables(creatureCbt_);
        StringList usablesMoves_ = new StringList(liste_);
        String lastUsedMove_ = creatureCbt_.getUsedMoveLastRound();
        forceUseLastIfNeeded(creatureCbt_, liste_);
        liste_.removeDuplicates();
        for(TeamPosition c:FightOrder.frontFighters(_fight)){
            Team equipeloc_=_fight.getTeams().getVal(c.getTeam());
            Fighter creatureCbtLoc_=equipeloc_.getMembers().getVal(c.getPosition());
            privateMoves(_combattant, liste_, creatureCbtLoc_);
            trackingMovesForbidden(_import, liste_, creatureCbtLoc_);
            trackingMovesForce(_import, liste_, creatureCbtLoc_);
            if(!NumberUtil.eq(c.getTeam(),_combattant.getTeam())){
                foeTeam(_import, liste_, equipeloc_);
            }
        }
        constChoice(creatureCbt_, liste_, lastUsedMove_);
        movesAnticipation(_import, equipe_, creatureCbt_, liste_);
        for(String c:creatureCbt_.enabledIndividualMoves()){
            individualMove(_import, creatureCbt_, liste_, c);
        }
        for(String c: FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGl_=(EffectGlobal)effet_;
                StringUtil.removeAllElements(liste_, effetGl_.getUnusableMoves());
            }
        }
        if (FightOrder.nbBackPartners(_fight,_combattant) == 0) {
            StringUtil.removeAllElements(liste_, _import.getMovesFullHeal());
        }
        liste_.removeString(_import.getDefMove());
        StringUtil.retainAllElements(liste_, usablesMoves_);
        return liste_;
    }

    private static void individualMove(DataBase _import, Fighter _creatureCbt, StringList _liste, String _c) {
        MoveData fAttaque_= _import.getMove(_c);
        int nbEffets_=fAttaque_.nbEffets();
        for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_=fAttaque_.getEffet(i);
            if(!(effet_ instanceof EffectRestriction)){
                continue;
            }
            EffectRestriction effetAntiChoix_=(EffectRestriction)effet_;
            if (effetAntiChoix_.getChoiceRestriction() == MoveChoiceRestrictionType.DER) {
                _liste.removeString(_creatureCbt.getLastSuccessfulMove());
            } else if (effetAntiChoix_.getChoiceRestriction() == MoveChoiceRestrictionType.CATEGORIE_AUTRE) {
                StringList notDamage_ = new StringList();
                for(String e: _liste){
                    MoveData fAttaqueInterdite_= _import.getMove(e);
                    if(!(fAttaqueInterdite_ instanceof DamagingMoveData)){
                        notDamage_.add(e);
                    }
                }
                StringUtil.removeAllElements(_liste, notDamage_);
            }
        }
    }

    private static void movesAnticipation(DataBase _import, Team _equipe, Fighter _creatureCbt, StringList _liste) {
        boolean lance_=false;
        for (ByteMap<Anticipation> m: _equipe.getMovesAnticipationValues()) {
            for (byte p: m.getKeys()) {
                if (NumberUtil.eq(_creatureCbt.getGroundPlace(), p) && m.getVal(p).isEnabled()) {
                    lance_ = true;
                    break;
                }
            }
            if (lance_) {
                break;
            }
        }
        if(lance_){
            StringUtil.removeAllElements(_liste, _import.getMovesAnticipation());
        }
    }

    private static void constChoice(Fighter _creatureCbt, StringList _liste, String _lastUsedMove) {
        if (_creatureCbt.getEnabledMovesConstChoices().contains(_lastUsedMove) && _creatureCbt.getEnabledMovesConstChoices().getVal(_lastUsedMove).isEnabled()) {
            _liste.clear();
            _liste.add(_lastUsedMove);
        }
    }

    private static void foeTeam(DataBase _import, StringList _liste, Team _equipeloc) {
        for(String c2_: _equipeloc.enabledTeamMoves()){
            MoveData fAttaque_= _import.getMove(c2_);
            int nbEffets_=fAttaque_.nbEffets();
            for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectTeam)){
                    continue;
                }
                EffectTeam effetEq_=(EffectTeam)effet_;
                StringUtil.removeAllElements(_liste, effetEq_.getUnusableMoves());
            }
        }
    }

    private static void trackingMovesForce(DataBase _import, StringList _liste, Fighter _creatureCbtLoc) {
        for(MoveTeamPosition c2_: _creatureCbtLoc.enabledRelationsMoves()){
            MoveData fAttaque_= _import.getMove(c2_.getMove());
            int nbEffets_=fAttaque_.nbEffets();
            for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectRestriction)){
                    continue;
                }
                EffectRestriction effetAntiChoix_=(EffectRestriction)effet_;
                if (effetAntiChoix_.getChoiceRestriction() == MoveChoiceRestrictionType.FORCE) {
                    _liste.clear();
                    _liste.add(_creatureCbtLoc.getTrackingMoves().getVal(c2_).getMove());
                }
            }
        }
    }

    private static void trackingMovesForbidden(DataBase _import, StringList _liste, Fighter _creatureCbtLoc) {
        for(MoveTeamPosition c2_: _creatureCbtLoc.enabledRelationsMoves()){
            MoveData fAttaque_= _import.getMove(c2_.getMove());
            int nbEffets_=fAttaque_.nbEffets();
            for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectRestriction)){
                    continue;
                }
                EffectRestriction effetAntiChoix_=(EffectRestriction)effet_;
                if (effetAntiChoix_.getChoiceRestriction() == MoveChoiceRestrictionType.FORBIDDEN) {
                    _liste.removeString(_creatureCbtLoc.getTrackingMoves().getVal(c2_).getMove());
                }
            }
        }
    }

    private static void privateMoves(TeamPosition _combattant, StringList _liste, Fighter _creatureCbtLoc) {
        for(MoveTeamPosition c2_: _creatureCbtLoc.getPrivateMoves().getKeys()){
            if (!TeamPosition.eq(c2_.getTeamPosition(), _combattant)) {
                continue;
            }
            StringUtil.removeAllElements(_liste, _creatureCbtLoc.getPrivateMoves().getVal(c2_));
        }
    }

    private static StringList attaquesUtilisables(Fighter _creatureCbt) {
        StringList liste_=new StringList();
        for(String c: _creatureCbt.attaquesUtilisables()){
            if(_creatureCbt.powerPointsMove(c)>0){
                liste_.add(c);
            }
        }
        return liste_;
    }

    private static void forceUseLastIfNeeded(Fighter _creatureCbt, StringList _liste) {
        String lastUsedMove_ = _creatureCbt.getUsedMoveLastRound();
        if (!lastUsedMove_.isEmpty() && (_creatureCbt.isNeedingToRecharge() || _creatureCbt.getNbPrepaRound() > 0)) {
            _liste.clear();
            _liste.add(lastUsedMove_);
        }
    }

    /** _fight.getFirstPositPlayerFighters() has no duplicate for the &quot;no back&quot; values
    the &quot;no back&quot; values are different from ally places (subst ground place)*/
    static boolean substitutable(Fight _fight, Difficulty _diff, DataBase _import){
        boolean error_ = false;
        boolean autoriseEchangePositionFinTour_=_diff.getAllowedSwitchPlacesEndRound();
        Bytes places_ = new Bytes();
        Bytes playerPlaces_ = new Bytes();
        byte nbPkNonKo_=0;
        for(TeamPosition c: FightOrder.fightersBelongingToUser(_fight,true)){
            Fighter creature_ = _fight.getFighter(c);
            String name_ = _import.translatePokemon(creature_.getName());
            byte currentPos_ = _fight.getFirstPositPlayerFighters().getVal(c.getPosition());
            if(creature_.estKo()){
                if(!NumberUtil.eq(currentPos_,Fighter.BACK)){
                    _fight.addMessage(_import,Fight.ERR_SUBSTITUTE_KO_END_ROUND, name_);
                    error_ = true;
                }
                continue;
            }
            nbPkNonKo_++;
            if (!NumberUtil.eq(currentPos_, Fighter.BACK)) {
                error_ = lookForError(_fight, _import, error_, autoriseEchangePositionFinTour_, creature_, currentPos_);
                places_.add(currentPos_);
                playerPlaces_.add(currentPos_);
            }
        }
        for(TeamPosition k: FightOrder.fightersBelongingToUser(_fight,false)){
            byte currentPosPart_ = _fight.getFirstPositPlayerFighters().getVal(k.getPosition());
            if (!NumberUtil.eq(currentPosPart_, Fighter.BACK)) {
                places_.add(currentPosPart_);
            }
        }
        places_.sort();
        //increasing
        return checkDuplicate(_fight, _import, error_, places_, playerPlaces_, nbPkNonKo_);
    }

    private static boolean checkDuplicate(Fight _fight, DataBase _import, boolean _error, Bytes _places, Bytes _playerPlaces, byte _nbPkNonKo) {
        boolean error_ = _error;
        int nbPl_ = _places.size();
        for(byte i = IndexConstants.SECOND_INDEX; i<nbPl_; i++){
            if(NumberUtil.eq(_places.get(i - 1), _places.get(i))){
                _fight.addMessage(_import,Fight.ERR_SUBSTITUTE_PLACE, Long.toString(_places.get(i)));
                error_ = true;
            }
        }
        if(_places.isEmpty()){
            _fight.addMessage(_import,Fight.ERR_SUBSTITUTE_USED_PLACE);
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
        if (_nbPkNonKo > _fight.getPlayerMaxNumberFrontFighters() && _places.size() != _fight.getMult() || _nbPkNonKo <= _fight.getPlayerMaxNumberFrontFighters() && _playerPlaces.size() != _nbPkNonKo || _playerPlaces.size() > _fight.getPlayerMaxNumberFrontFighters()) {
            _fight.addMessage(_import, Fight.ERR_SUBSTITUTE_USED_PLACE);
            return false;
        }
        return !error_;
    }

    private static boolean lookForError(Fight _fight, DataBase _import, boolean _error, boolean _autoriseEchangePositionFinTour, Fighter _creature, byte _currentPos) {
        boolean error_ = _error;
        boolean belong_ = belong(_fight, _currentPos);
        if (!belong_) {
            _fight.addMessage(_import, Fight.ERR_SUBSTITUTE_BELONG);
            error_ = true;
        }
        if (!_autoriseEchangePositionFinTour && _fight.getFightType() != FightType.TMP_TRAINER && !NumberUtil.eq(_currentPos, _creature.getGroundPlace()) && !_creature.estArriere()) {
            _fight.addMessage(_import, Fight.ERR_SUBSTITUTE_NO_SWITCH_PLACE);
            error_ = true;
        }
        return error_;
    }

    private static boolean belong(Fight _fight, byte _currentPos) {
        boolean belong_ = true;
        for(TeamPosition k: FightOrder.fightersBelongingToUser(_fight,false)){
            byte currentPosPart_ = _fight.getFirstPositPlayerFighters().getVal(k.getPosition());
//                if (Numbers.eq(partner_.getGroundPlaceSubst(), c_)) {
//                    belong_ = false;
//                }
            if (NumberUtil.eq(currentPosPart_, _currentPos)) {
                belong_ = false;
            }
        }
        return belong_;
    }

    static boolean playable(Fight _fight, Player _utilisateur,Difficulty _diff,DataBase _import){
        Inventory inv_=_utilisateur.getInventory();
        Team equipeUt_=_fight.getUserTeam();
        StringMap<LgInt> utilisationsObjets_=new StringMap<LgInt>();
        //String retour_=DataBase.EMPTY_STRING;
        boolean error_ = basicError(_fight, _import);
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
            nbActions_ = incActionsNb(nbActions_, creature_);
//            if(!creature_.estKo()){
//                nbNonKo_++;
//            }
            if (!creature_.estArriere() && nbPreviousAct_ == nbActions_) {
                existFrontWithoutAct_ = true;
            } else if(creature_.getAction() instanceof ActionMove){
                error_ = checkActionMove(_fight, _diff, _import, error_, c, creature_);
            } else if(creature_.getAction() instanceof ActionSwitch){
                error_ = checkActionSwitch(_fight, _import, error_, creature_);
            } else if(creature_.getAction() instanceof ActionHeal){
                error_ = checkActionHeal(_fight, _import, error_, utilisationsObjets_, creature_);
            }
        }
        for(String c:utilisationsObjets_.getKeys()){
            if (LgInt.strGreater(utilisationsObjets_.getVal(c), inv_.getNumber(c))) {
                error_ = true;
                String item_ = _import.translateItem(c);
                _fight.addMessage(_import,Fight.ERR_TOO_MANY_ITEMS, item_);
            }
        }
        return playableSpec(_fight, _import, error_, nbActions_, nb_, existFrontWithoutAct_);
    }

    private static boolean playableSpec(Fight _fight, DataBase _import, boolean _error, byte _nbActions, byte _nb, boolean _existFrontWithoutAct) {
        Team equipeUt_=_fight.getUserTeam();
        boolean error_ = _error;
        for(byte c: equipeUt_.getMembers().getKeys()){
            Fighter creature_= equipeUt_.getMembers().getVal(c);
            if (!(creature_.getAction() instanceof ActionHeal) || creature_.getChosenHealingItem().isEmpty()) {
                continue;
            }
            error_ = updateErrorHealing(_fight, _import, error_, c, creature_);
        }
        if (_nb <= _fight.getPlayerMaxNumberFrontFighters()) {
            if (_existFrontWithoutAct) {
                error_ = true;
                //All front pk must act, it is sufficient
                _fight.addMessage(_import,Fight.ERR_TOO_FEW_ACTIONS);
            }
//            if (nbActions_ != nb_) {
//                //All front pk must act, it is sufficient
//                error_ = true;
//                _fight.addMessage(Fight.ERR_TOO_FEW_ACTIONS);
//            }
        } else {
            if (_nbActions != _fight.getPlayerMaxNumberFrontFighters()) {
                error_ = true;
                _fight.addMessage(_import,Fight.ERR_TOO_MANY_ACTIONS, Long.toString(_fight.getPlayerMaxNumberFrontFighters()), Long.toString(_nbActions));
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

    private static boolean basicError(Fight _fight, DataBase _import) {
        Team equipeUt_=_fight.getUserTeam();
        boolean error_ = false;
        Bytes remplacants_=new Bytes();
        for(byte c: equipeUt_.getMembers().getKeys()){
            Fighter creature_= equipeUt_.getMembers().getVal(c);
            if (!creature_.isBelongingToPlayer() || NumberUtil.eq(creature_.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
            if(!NumberUtil.eq(creature_.getSubstistute(),Fighter.BACK)){
                if(!remplacants_.containsObj(creature_.getSubstistute())){
                    remplacants_.add(creature_.getSubstistute());
                }else{
                    error_ = true;
                    Fighter fighter_ = equipeUt_.getMembers().getVal(creature_.getSubstistute());
                    String name_ = _import.translatePokemon(fighter_.getName());
                    _fight.addMessage(_import,Fight.ERR_SUBSTITUTE, name_);
                }
            }
        }
        return error_;
    }

    private static boolean updateErrorHealing(Fight _fight, DataBase _import, boolean _error, byte _c, Fighter _creature) {
        boolean error_ = _error;
        String name_ = _import.translatePokemon(_creature.getName());
        String nomObjet_ = _creature.getChosenHealingItem();
        String itemNameTr_ = _import.translateItem(nomObjet_);
        Item objet_ = _import.getItem(nomObjet_);
        if (objet_ instanceof Berry) {
            Berry baie_ = (Berry) objet_;
            error_ = updateErrorHealingBerry(_fight, _import, _c, _creature, error_, baie_);
        }
        if (!(objet_ instanceof HealingItem)) {
            return error_;
        }
        HealingItem soin_ = (HealingItem) objet_;
        if (soin_ instanceof HealingHp) {
            Rate pvMax_ = _creature.pvMax();
            Rate pvRestants_ = _creature.getRemainingHp();
            boolean agit_ = Rate.strLower(pvRestants_, pvMax_);
            if (!agit_) {
                error_ = true;
                _fight.addMessage(_import, Fight.ERR_NO_EFFECT, itemNameTr_, name_);
            }
        }
        if (soin_ instanceof HealingPp) {
            HealingPp soinpp_ = (HealingPp) soin_;
            if (_creature.getFirstChosenMove().isEmpty() && (soinpp_.getHealedMovePp() > 0 || soinpp_.getHealingMoveFullpp())) {
                error_ = true;
                _fight.addMessage(_import, Fight.ERR_NO_HEALED_MOVE, itemNameTr_, name_);
            }
            boolean agit_ = agitPp(_import, _creature, nomObjet_, soinpp_);
            if (!agit_) {
                error_ = true;
                _fight.addMessage(_import, Fight.ERR_NO_EFFECT, itemNameTr_, name_);
            }
        }
        if (soin_ instanceof HealingStatus) {
            boolean agit_ = agitStatus(_creature, (HealingStatus) soin_);
            if (!agit_) {
                error_ = true;
                _fight.addMessage(_import, Fight.ERR_NO_EFFECT, itemNameTr_, name_);
            }
        }
        return error_;
    }

    private static boolean updateErrorHealingBerry(Fight _fight, DataBase _import, byte _c, Fighter _creature, boolean _error, Berry _baie) {
        boolean error_ = _error;
        String name_ = _import.translatePokemon(_creature.getName());
        String nomObjet_ = _creature.getChosenHealingItem();
        String itemNameTr_ = _import.translateItem(nomObjet_);
        String attaque_ = _creature.getFirstChosenMove();
        if (attaque_.isEmpty()) {
            boolean agit_ = agitBaie(_fight, _import, _c, _creature, _baie);
            if (!agit_) {
                error_ = true;
                _fight.addMessage(_import, Fight.ERR_NO_EFFECT, itemNameTr_, name_);
            }
        } else {
            short var_ = _creature.healedPpMove(attaque_, nomObjet_, _import);
            if (var_ == 0) {
                error_ = true;
                _fight.addMessage(_import, Fight.ERR_NO_EFFECT, itemNameTr_, name_);
            }
        }
        return error_;
    }

    private static boolean agitStatus(Fighter _creature, HealingStatus _soin) {
        boolean agit_ = false;
        StringList statuts_ = new StringList();
        for (String c2_ : _creature.getStatusSet()) {
            if (_creature.getStatusNbRoundShort(c2_) == 0) {
                continue;
            }
            if (StringUtil.contains(_soin.getStatus(), c2_)) {
                statuts_.add(c2_);
            }
        }
        if (!statuts_.isEmpty()) {
            agit_ = true;
        }
        if (_soin instanceof HealingHpStatus) {
            HealingHpStatus soinPvStatut_ = (HealingHpStatus) _soin;
            if (ComparatorBoolean.eq(soinPvStatut_.getHealingKo(), _creature.estKo())) {
                agit_ = true;
            }
        }
        return agit_;
    }

    private static boolean agitPp(DataBase _import, Fighter _creature, String _nomObjet, HealingPp _soinpp) {
        boolean agit_ = false;
        if (_soinpp.getHealedMovePp() > 0 || _soinpp.getHealingMoveFullpp()) {
            short var_ = _creature.healedPpMove(_creature.getFirstChosenMove(), _nomObjet, _import);
            if (var_ > 0) {
                agit_ = true;
            }
        } else {
            for (String c2_ : _creature.getCurrentMovesSet()) {
                short var_ = _creature.healedPpMove(c2_, _nomObjet, _import);
                if (var_ > 0) {
                    agit_ = true;
                    break;
                }
            }
        }
        return agit_;
    }

    private static boolean agitBaie(Fight _fight, DataBase _import, byte _c, Fighter _creature, Berry _baie) {
        boolean agit_ = false;
        if(!_baie.getMultStat().isEmpty()){
            for(Statistic c2_: _baie.getMultStat().getKeys()){
                byte varBase_= _baie.getMultStat().getVal(c2_).getBoost();
                byte var_= FightEffects.deltaBoostStatistic(_fight,Fight.toUserFighter(_c),c2_,varBase_, _import);
                if(var_>0){
                    agit_=true;
                    break;
                }
            }
        }
        if(!_baie.getHealHp().isZero()||!_baie.getHealHpRate().isZero()||!_baie.getHealHpBySuperEffMove().isZero()){
            Rate pvRestants_= _creature.getRemainingHp();
            Rate pvMax_= _creature.pvMax();
            if(Rate.strLower(pvRestants_,pvMax_)){
                agit_=true;
            }
        }
        StringList statuts_=new StringList();
        for(String s: _creature.getStatusSet()){
            if (!NumberUtil.eq(_creature.getStatusNbRoundShort(s), 0) && StringUtil.contains(_baie.getHealStatus(), s)) {
                statuts_.add(s);
            }
        }
        if(!statuts_.isEmpty()){
            agit_=true;
        }
        return agit_;
    }

    private static boolean checkActionHeal(Fight _fight, DataBase _import, boolean _error, StringMap<LgInt> _utilisationsObjets, Fighter _creature) {
        String name_ = _import.translatePokemon(_creature.getName());
        if(_creature.getChosenHealingItem().isEmpty()){
            _fight.addMessage(_import,Fight.ERR_NO_ITEM, name_);
            return true;
        }
        if(!_utilisationsObjets.contains(_creature.getChosenHealingItem())){
            _utilisationsObjets.put(_creature.getChosenHealingItem(),LgInt.one());
        }else{
            _utilisationsObjets.getVal(_creature.getChosenHealingItem()).increment();
        }
        return _error;
    }

    private static boolean checkActionSwitch(Fight _fight, DataBase _import, boolean _error, Fighter _creature) {
        String name_ = _import.translatePokemon(_creature.getName());
        if(_creature.estArriere()){
            _fight.addMessage(_import,Fight.ERR_BACK_SWITCH, name_);
        }
        if(NumberUtil.eq(_creature.getSubstistute(),Fighter.BACK)){
            _fight.addMessage(_import,Fight.ERR_SWITCH, name_);
            return true;
        }
        return checkPartner(_fight, _import, _error, _creature);
    }

    private static boolean checkActionMove(Fight _fight, Difficulty _diff, DataBase _import, boolean _error, byte _c, Fighter _creature) {
        String name_ = _import.translatePokemon(_creature.getName());
        boolean error_ = _error;
        if(_creature.estArriere()){
            _fight.addMessage(_import,Fight.ERR_BACK_MOVE, name_);
            return true;
        }
        String attaque_= _creature.getFirstChosenMove();
        String moveName_ = _import.translateMove(attaque_);
        StringList attaquesAutorisees_=FightFacade.allowedMovesNotEmpty(_fight,Fight.toUserFighter(_c), _import);
        if (!StringUtil.contains(attaquesAutorisees_, attaque_)) {
            _fight.addMessage(_import,Fight.ERR_UNUSABLE_MOVE, moveName_);
            return true;
        }
        if (StringUtil.contains(_import.getMovesFullHeal(), attaque_) || _import.isBatonPassMove(attaque_)) {
            if(NumberUtil.eq(_creature.getSubstistute(),Fighter.BACK)){
                _fight.addMessage(_import,Fight.ERR_SWITCH, name_);
                return true;
            }
            error_ = checkPartner(_fight, _import, _error, _creature);
        }
        return checkActionMoveTarget(_fight, _diff, _import, _c, _creature, error_, attaque_);
    }
    private static boolean checkPartner(Fight _fight, DataBase _import, boolean _error, Fighter _creature) {
        boolean error_ = _error;
        Team equipeUt_=_fight.getUserTeam();
        Fighter partenaire_= equipeUt_.getMembers().getVal(_creature.getSubstistute());
        String namePart_ = _import.translatePokemon(partenaire_.getName());
        if(partenaire_.estKo()){
            error_ = true;
            _fight.addMessage(_import,Fight.ERR_KO_SUBSTITUTE, namePart_);
        }
        if (!partenaire_.isBelongingToPlayer()) {
            error_ = true;
            _fight.addMessage(_import,Fight.ERR_BELONG_SWITCH, namePart_);
        }
        if(!partenaire_.estArriere()){
            error_ = true;
            _fight.addMessage(_import,Fight.ERR_FRONT_SWITCH, namePart_);
        }
        return error_;
    }

    private static boolean checkActionMoveTarget(Fight _fight, Difficulty _diff, DataBase _import, byte _c, Fighter _creature, boolean _error, String _attaque) {
        boolean error_ = _error;
        String name_ = _import.translatePokemon(_creature.getName());
        String moveName_ = _import.translateMove(_attaque);
        TargetCoordsList cibles_= _creature.getChosenTargets();
        MoveData fAtt_= _import.getMove(_attaque);
        //If the player selects a fighter then a move,
        //there are two cases:
        //1. the target is not chosable.
        //the move is selected and the action is validated
        //2. the target is chosable.
        //For validating, the player has to select a target or a move such that a single target is selectable
        if (cibles_.isEmpty()) {
            return error_;
        }
        TargetCoords first_ = cibles_.first();
        if(fAtt_.getTargetChoice() == TargetChoice.ADJ_UNIQ){
            byte noTeam_ = (byte) first_.getTeam();
            Team equipeCible_= _fight.getTeams().getVal(noTeam_);
            Bytes cbts_=equipeCible_.fightersAtCurrentPlace(first_);
            if(cbts_.size() != DataBase.ONE_POSSIBLE_CHOICE){
                _fight.addMessage(_import,Fight.ERR_NO_CHOSEN_TARGET, moveName_, name_);
                return true;
            }
            if(!FightOrder.closestFigthers(_fight,Fight.toUserFighter(_c), _diff).containsObj(new TeamPosition(noTeam_,cbts_.first()))){
                _fight.addMessage(_import,Fight.ERR_TOO_FAR_TARGET, moveName_, name_);
                return true;
            }
            return error_;
        }
        if (fAtt_.getTargetChoice() == TargetChoice.ALLIE) {
            if (!NumberUtil.eq(first_.getTeam(), Fight.CST_PLAYER)) {
                error_ = true;
                _fight.addMessage(_import,Fight.ERR_BAD_CHOICE, moveName_, name_);
            }
            return error_;
        }
        if (fAtt_.getTargetChoice() == TargetChoice.AUTRE_UNIQ) {
            byte noTeam_ = (byte) first_.getTeam();
            Team equipeCible_= _fight.getTeams().getVal(noTeam_);
            Bytes cbts_=equipeCible_.fightersAtCurrentPlace(first_);
            if(cbts_.size() != DataBase.ONE_POSSIBLE_CHOICE){
                _fight.addMessage(_import,Fight.ERR_NO_CHOSEN_TARGET, moveName_, name_);
                return true;
            }
            if (TeamPosition.eq(Fight.toUserFighter(_c), new TeamPosition(noTeam_,cbts_.first()))) {
                error_ = true;
                _fight.addMessage(_import,Fight.ERR_BAD_CHOICE, moveName_, name_);
            }
            return error_;
        }
        if (foeCase(fAtt_, first_)) {
            _fight.addMessage(_import, Fight.ERR_BAD_CHOICE, moveName_, name_);
            return true;
        }
        return error_;
    }

    private static boolean foeCase(MoveData _fAtt, TargetCoords _first) {
        return _fAtt.getTargetChoice() == TargetChoice.ANY_FOE && NumberUtil.eq(_first.getTeam(), Fight.CST_PLAYER);
    }

    private static byte incActionsNb(byte _nbActions, Fighter _creature) {
        byte nbActions_ = _nbActions;
        if(_creature.getAction() instanceof ActionMove){
            nbActions_++;
        }
        if(_creature.getAction() instanceof ActionSwitch){
            nbActions_++;
        }
        if(_creature.getAction() instanceof ActionHeal){
            nbActions_++;
        }
        return nbActions_;
    }
}
