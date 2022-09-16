package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectCopyMove;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.game.params.Difficulty;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

final class FightInvoke {

    private FightInvoke() {
    }

    static void processInvokingMove(Fight _fight, TeamPosition _lanceur,Difficulty _diff,DataBase _import) {
        _fight.setInvokedMove(false);
        _fight.setSuccessfulInvokation(true);
        Fighter creature_=_fight.getFighter(_lanceur);
        creature_.affectNoRoundBeforeUsingMove();
        creature_.setDisappeared(false);
        _fight.getSuccessfulEffects().clear();
        creature_.ajouterAttaquesDejaInvoqueesTour(creature_.getFinalChosenMove());
        creature_.setLastUsedMove(creature_.getFinalChosenMove());
        StringList invoked_ = new StringList();
        while(true){
            String attaqueInvoque_=creature_.getFinalChosenMove();
            if (!StringUtil.contains(_import.getMovesInvoking(), attaqueInvoque_)) {
                _fight.addFirstMoveMessage(_lanceur, attaqueInvoque_, _import);
                return;
            }
            _fight.addInvokeMoveMessage(_lanceur, attaqueInvoque_, _import);
            MoveData fAttInvoque_=_import.getMove(attaqueInvoque_);
            String invoquer_="";
            EffectInvoke effet_=(EffectInvoke) fAttInvoque_.getEffet(IndexConstants.FIRST_INDEX);
            CustList<TeamPosition> targets_ = FightOrder.targetsEffect(_fight, _lanceur, effet_, _diff, _import);
            if (!targets_.isEmpty()) {
                TeamPosition e_ = targets_.first();
                _fight.setSending(false);
                if(FightSuccess.successfulMove(_fight,_lanceur,e_,attaqueInvoque_, IndexConstants.FIRST_INDEX,true,_import).isSuccessful()){
                    //debugger la chaine d'invocation d'attaques
                    invoquer_ = effectInvoke(_fight,_lanceur,e_,effet_,_import,invoked_);
                    if(!_fight.getAcceptableChoices()){
                        return;
                    }
                }
            }
            if(invoquer_.isEmpty()){
                _fight.addInvokeMoveFailMessage(attaqueInvoque_, _import);
                _fight.setSuccessfulInvokation(false);
                return;
            }
            _fight.setInvokedMove(true);
            creature_.invokeMove(invoquer_);
            invoked_.add(invoquer_);
        }
    }

    static void effectInvoke(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, EffectInvoke _effet, DataBase _import){
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        effectInvoke(_fight, _lanceur, _cible, _effet, _import, creatureLanceur_.getAlreadyInvokedMovesRound());
    }
    static String effectInvoke(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,EffectInvoke _effet,DataBase _import, StringList _visited){
        Fighter creatureLanceur_ = _fight.getFighter(_lanceur);
        StringList attaquesInvocables_=invokableMoves(_fight,_lanceur,_cible,_effet,_import,_visited);
        MonteCarloString loi_ = new MonteCarloString();
        for(String e:attaquesInvocables_){
            loi_.addQuickEvent(e,DataBase.defElementaryEvent());
        }
        String obj_ = invoked(_fight,_import,loi_);
        if (obj_.isEmpty()) {
            return "";
        }
        creatureLanceur_.ajouterAttaquesDejaInvoqueesTour(obj_);
        return obj_;
    }
    private static String invoked(Fight _fight,DataBase _import,MonteCarloString _loi) {
        if (FightSuccess.isBadSimulation(_fight, _loi)) {
            return "";
        }
        return FightSuccess.random(_import, _loi);
    }

    static StringList invokableMoves(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectInvoke _effet,DataBase _import){
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        return invokableMoves(_fight, _lanceur, _cible, _effet, _import,creatureLanceur_.getAlreadyInvokedMovesRound());
    }
    static StringList invokableMoves(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectInvoke _effet,DataBase _import, StringList _visited){
        Team equipeLanceur_=_fight.getTeams().getVal(_lanceur.getTeam());
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        Fighter creatureCible_=_fight.getFighter(_cible);
        String derniereAttaqueSubie_=creatureLanceur_.getLastSufferedMove();
        StringList attaquesInvocables_ = new StringList();
        for (String m: FightMoves.enabledGlobalMoves(_fight, _import)) {
            MoveData moveDta_ = _import.getMove(m);
            int nbEffects_ = moveDta_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i < nbEffects_; i++) {
                Effect eff_ = moveDta_.getEffet(i);
                if (!(eff_ instanceof EffectGlobal)) {
                    continue;
                }
                EffectGlobal effectLoc_ = (EffectGlobal) eff_;
                if (!StringUtil.quickEq(effectLoc_.getInvokedMoveTerrain(), DataBase.EMPTY_STRING)) {
                    attaquesInvocables_.add(effectLoc_.getInvokedMoveTerrain());
                }
            }
        }
        if (attaquesInvocables_.isEmpty() && _effet.getMoveFctEnv().contains(_fight.getEnvType())) {
            attaquesInvocables_.add(_effet.getMoveFctEnv().getVal(_fight.getEnvType()));
        }
        if(_effet.getInvokingMoveButUser()){
            attaquesInvocables_.addAllElts(_import.getMoves().getKeys());
            for(String c:creatureLanceur_.attaquesUtilisables()){
                attaquesInvocables_.removeString(c);
            }
        }
        invokeCondition(creatureCible_.getFinalChosenMove(), attaquesInvocables_, _effet.getInvokingTargetChosenMove());
        if(_effet.getInvokingUserMoveWhileSleep()){
            attaquesInvocables_.addAllElts(creatureLanceur_.attaquesUtilisables());
        }
        invokeCondition(creatureCible_.getLastSuccessfulMove(), attaquesInvocables_, _effet.getInvokingTargetSuccesfulMove());
        invokeCondition(derniereAttaqueSubie_, attaquesInvocables_, _effet.getInvokingSufferedMove());
        invokingAllyMove(_lanceur, _effet, equipeLanceur_, attaquesInvocables_);
        invokingMoveByUserTypes(_effet, creatureLanceur_, attaquesInvocables_);
        attaquesInvocables_.removeDuplicates();
        StringUtil.removeAllElements(attaquesInvocables_, _effet.getMovesNotToBeInvoked());
        StringUtil.removeAllElements(attaquesInvocables_, _visited);
        return attaquesInvocables_;
    }

    private static void invokeCondition(String _move, StringList _attaquesInvocables, boolean _condition) {
        if (_condition && !_move.isEmpty()) {
            _attaquesInvocables.add(_move);
        }
    }

    private static void invokingAllyMove(TeamPosition _lanceur, EffectInvoke _effet, Team _equipeLanceur, StringList _attaquesInvocables) {
        if(_effet.getInvokingAllyMove()){
            for(byte c: _equipeLanceur.getMembers().getKeys()){
                if(NumberUtil.eq(c,_lanceur.getPosition())){
                    continue;
                }
                Fighter partenaire_= _equipeLanceur.getMembers().getVal(c);
                _attaquesInvocables.addAllElts(partenaire_.attaquesUtilisables());
            }
        }
    }

    private static void invokingMoveByUserTypes(EffectInvoke _effet, Fighter _creatureLanceur, StringList _attaquesInvocables) {
        boolean contenu_=false;
        for(String e: _creatureLanceur.getTypes()){
            if(_effet.getInvokingMoveByUserTypes().contains(e)){
                _attaquesInvocables.add(_effet.getInvokingMoveByUserTypes().getVal(e));
                contenu_=true;
            }
        }
        if (!contenu_ && _effet.getInvokingMoveByUserTypes().contains(DataBase.EMPTY_STRING)) {
            _attaquesInvocables.add(_effet.getInvokingMoveByUserTypes().getVal(DataBase.EMPTY_STRING));
        }
    }

    static StringList copiableMoves(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectCopyMove _effet,DataBase _import){
        Fighter creatureCbtCible_= _fight.getFighter(_cible);
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        StringList attaquesCopiables_ = new StringList();
        if(_effet.getCopyingMoveForUserDef()){
            if(!creatureCbtCible_.getLastUsedMove().isEmpty()){
                attaquesCopiables_.add(creatureCbtCible_.getLastUsedMove());
            }
            for(String c:creatureCbtLanceur_.attaquesUtilisables()){
                attaquesCopiables_.removeString(c);
            }
            for(String c:creatureCbtLanceur_.getMovesSet()){
                attaquesCopiables_.removeString(c);
            }
        }
        if(_effet.getCopyingMoveForUser()>0){
            if(!creatureCbtCible_.getLastUsedMove().isEmpty()){
                attaquesCopiables_.add(creatureCbtCible_.getLastUsedMove());
            }
            for(String c:creatureCbtLanceur_.attaquesUtilisables()){
                attaquesCopiables_.removeString(c);
            }
        }
        StringUtil.removeAllElements(attaquesCopiables_, _effet.getMovesNotToBeCopied());
        StringUtil.removeObj(attaquesCopiables_, _import.getDefaultMove());
        return attaquesCopiables_;
    }
}
