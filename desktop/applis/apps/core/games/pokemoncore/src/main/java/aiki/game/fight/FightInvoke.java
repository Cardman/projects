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
import code.util.EqList;
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
        while(true){
            String attaqueInvoque_=creature_.getFinalChosenMove();
            if (!StringUtil.contains(_import.getMovesInvoking(), attaqueInvoque_)) {
                _fight.addFirstMoveMessage(_lanceur, attaqueInvoque_, _import);
                break;
            }
            _fight.addInvokeMoveMessage(_lanceur, attaqueInvoque_, _import);
            MoveData fAttInvoque_=_import.getMove(attaqueInvoque_);
            boolean invoquer_=false;
            EffectInvoke effet_=(EffectInvoke) fAttInvoque_.getEffet(IndexConstants.FIRST_INDEX);
            CustList<TeamPosition> targets_ = FightOrder.targetsEffect(_fight, _lanceur, effet_, _diff, _import);
            if (!targets_.isEmpty()) {
                TeamPosition e_ = targets_.first();
                _fight.setSending(false);
                if(FightSuccess.successfulMove(_fight,_lanceur,e_,attaqueInvoque_, IndexConstants.FIRST_INDEX,true,_import).isSuccessful()){
                    //debugger la chaine d'invocation d'attaques
                    effectInvoke(_fight,_lanceur,e_,effet_,_import);
                    if(!_fight.getAcceptableChoices()){
                        return;
                    }
                    invoquer_=true;
                }
            }
            if(!invoquer_){
                _fight.addInvokeMoveFailMessage(attaqueInvoque_, _import);
                _fight.setSuccessfulInvokation(false);
                break;
            }
            _fight.setInvokedMove(true);
            creature_.invokeMove();
        }
    }

    static void effectInvoke(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,EffectInvoke _effet,DataBase _import){
        Fighter creatureLanceur_ = _fight.getFighter(_lanceur);
        StringList attaquesInvocables_=invokableMoves(_fight,_lanceur,_cible,_effet,_import);
        MonteCarloString loi_ = new MonteCarloString();
        for(String e:attaquesInvocables_){
            loi_.addQuickEvent(e,DataBase.defElementaryEvent());
        }
        if (FightSuccess.isBadSimulation(_fight, loi_)) {
            return;
        }
        String obj_ = FightSuccess.random(_import, loi_);
        creatureLanceur_.ajouterAttaquesDejaInvoqueesTour(obj_);
    }

    static StringList invokableMoves(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectInvoke _effet,DataBase _import){
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
        if (attaquesInvocables_.isEmpty()) {
            if(_effet.getMoveFctEnv().contains(_fight.getEnvType())){
                attaquesInvocables_.add(_effet.getMoveFctEnv().getVal(_fight.getEnvType()));
            }
        }
        if(_effet.getInvokingMoveButUser()){
            attaquesInvocables_.addAllElts(_import.getMoves().getKeys());
            for(String c:creatureLanceur_.attaquesUtilisables()){
                attaquesInvocables_.removeString(c);
            }
        }
        if(_effet.getInvokingTargetChosenMove()){
            if(!creatureCible_.getFinalChosenMove().isEmpty()){
                attaquesInvocables_.add(creatureCible_.getFinalChosenMove());
            }
        }
        if(_effet.getInvokingUserMoveWhileSleep()){
            attaquesInvocables_.addAllElts(creatureLanceur_.attaquesUtilisables());
        }
        if(_effet.getInvokingTargetSuccesfulMove()){
            if(!creatureCible_.getLastSuccessfulMove().isEmpty()){
                attaquesInvocables_.add(creatureCible_.getLastSuccessfulMove());
            }
        }
        if(_effet.getInvokingSufferedMove()){
            if(!derniereAttaqueSubie_.isEmpty()){
                attaquesInvocables_.add(derniereAttaqueSubie_);
            }
        }
        if(_effet.getInvokingAllyMove()){
            for(byte c:equipeLanceur_.getMembers().getKeys()){
                if(NumberUtil.eq(c,_lanceur.getPosition())){
                    continue;
                }
                Fighter partenaire_=equipeLanceur_.getMembers().getVal(c);
                attaquesInvocables_.addAllElts(partenaire_.attaquesUtilisables());
            }
        }
        boolean contenu_=false;
        for(String e:creatureLanceur_.getTypes()){
            if(_effet.getInvokingMoveByUserTypes().contains(e)){
                attaquesInvocables_.add(_effet.getInvokingMoveByUserTypes().getVal(e));
                contenu_=true;
            }
        }
        if(!contenu_){
            if(_effet.getInvokingMoveByUserTypes().contains(DataBase.EMPTY_STRING)){
                attaquesInvocables_.add(_effet.getInvokingMoveByUserTypes().getVal(DataBase.EMPTY_STRING));
            }
        }
        attaquesInvocables_.removeDuplicates();
        StringUtil.removeAllElements(attaquesInvocables_, _effet.getMovesNotToBeInvoked());
        StringUtil.removeAllElements(attaquesInvocables_, creatureLanceur_.getAlreadyInvokedMovesRound());
        return attaquesInvocables_;
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
