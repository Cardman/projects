package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectCounterAttack;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.moves.effects.EffectUnprotectFromTypes;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.status.Status;
import aiki.fight.status.effects.EffectPartnerStatus;
import aiki.fight.util.StatisticCategory;
import aiki.fight.util.StatisticStatus;
import aiki.fight.util.TypesDuos;
import aiki.fight.util.TypesDuo;
import aiki.game.fight.util.NbEffectFighterCoords;
import aiki.game.fight.util.RandomBoolResults;
import aiki.util.MoveTeamPositionsBoolVal;
import aiki.util.NbEffectFighterCoordss;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.*;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

final class FightSuccess {

    private FightSuccess() {
    }

    static RandomBoolResults successfulMove(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,
            String _attaque,int _noEffet,boolean _withPreviousEffect,DataBase _import){
        MoveData fAttaque_=_import.getMove(_attaque);
        if (!TeamPosition.eq(_lanceur, _cible)) {
            if(isProtectedAgainstMove(_fight,_lanceur,_cible,_attaque,_import)){
                return new RandomBoolResults(false,true);
            }
        } else if (_fight.isSending() && isProtectedAgainstMove(_fight, _cible, _cible, _attaque, _import)) {
            return new RandomBoolResults(false, true);
        }
        Effect effet_=fAttaque_.getEffet(_noEffet);
        if (effet_.getTargetChoice() == fAttaque_.getTargetChoice()) {
            if (!successfulEffectWhileIfTargetIsNotThrower(
                    _fight,
                    _lanceur, _cible, _attaque,
                    _noEffet, _withPreviousEffect, _import)) {
                return failMoveSameTarget(_fight, _cible, _import);
            }
        } else {
            boolean sucessful_ = successfulEffect(_fight, _cible, effet_);
            if (!sucessful_ && _withPreviousEffect) {
                return new RandomBoolResults(false, false);
            }
        }
        return defSuccess(_fight, _lanceur, _cible, _attaque, _noEffet, _import, effet_);
    }

    private static RandomBoolResults failMoveSameTarget(Fight _fight, TeamPosition _cible, DataBase _import) {
        Fighter target_ = _fight.getFighter(_cible);
        if (StringUtil.contains(_import.getMovesCountering(), target_.getFinalChosenMove())) {
            return new RandomBoolResults(false, target_.getEnabledCounteringMoves().getVal(target_.getFinalChosenMove()).isEnabled());
        }
        return new RandomBoolResults(false, false);
    }

    private static RandomBoolResults defSuccess(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, String _attaque, int _noEffet, DataBase _import, Effect _effet) {
        String fail_ = failEffect(_fight, _effet);
        StringMap<String> values_ = new StringMap<String>();
        if (!fail_.isEmpty()) {
            if (_fight.isSending()) {
                values_.putAllMap(FightValues.calculateSendingVariables(_fight,_lanceur, _import));
            }
            values_.putAllMap(FightValues.calculateValues(_fight,_lanceur,_cible,_import));
            values_.putAllMap(FightValues.calculateBooleanValues(_fight,_lanceur, _cible, _attaque, _noEffet, _import));
            if (_import.evaluateBoolean(fail_, values_, false)) {
                return new RandomBoolResults(false,false);
            }
        }
        if (!successEffect(_fight,_lanceur, _cible, _effet, _import)) {
            return new RandomBoolResults(false,false);
        }
        return new RandomBoolResults(true,false);
    }

    private static String failEffect(Fight _fight, Effect _effet) {
        String fail_;
        if (!_fight.isSending()) {
            fail_ = _effet.getFail();
        } else {
            EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) _effet;
            fail_ = eff_.getFailSending();
        }
        return fail_;
    }

    private static boolean successfulEffect(Fight _fight, TeamPosition _cible, Effect _effet) {
        boolean sucessful_ = true;
        for (int e: _effet.getRequiredSuccessfulEffects()) {
            boolean atLeastOneSuccessful_ = false;
            NbEffectFighterCoords hit_ = new NbEffectFighterCoords(e, _cible);
            if (_fight.getSuccessfulEffects().contains(hit_)) {
                atLeastOneSuccessful_ = _fight.getSuccessfulEffects().getVal(hit_) == BoolVal.TRUE;
            }
            if (!atLeastOneSuccessful_) {
                sucessful_ = false;
                break;
            }
        }
        return sucessful_;
    }

    static boolean isProtectedAgainstMove(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,String _attaque,DataBase _import){
        MoveData move_ = _import.getMove(_attaque);
        if (move_.getTargetChoice() == TargetChoice.ALLIE || move_.getTargetChoice() == TargetChoice.ALLIES) {
            return false;
        }
        StringList typeAttaque_=FightMoves.moveTypes(_fight,_lanceur,_attaque,_import);
        for (String t: typeAttaque_) {
            if(isProtectedAgainstMoveType(_fight,_lanceur,_cible,t,_import)){
                return true;
            }
        }
        for (TeamPosition t_: FightOrder.frontFighters(_fight)) {
            if (!NumberUtil.eq(t_.getTeam(), _cible.getTeam())) {
                continue;
            }
            Fighter f_ = _fight.getFighter(t_);
            AbilityData ab_ = f_.ficheCapaciteActuelle(_import);
            if (ab_ != null && StringUtil.contains(ab_.getImmuAllyFromMoves(), _attaque)) {
                _fight.addProtectByAllyAbilityMessage(_cible, _attaque, f_.getCurrentAbility(), _import);
                return true;
            }
        }
        Fighter creatureCbtCible_=_fight.getFighter(_cible);
        boolean cancelImmu_ = cancelImmu(_fight, _lanceur, _import);
        if (protItAlly(_fight, _lanceur, _cible, _attaque, _import, move_, creatureCbtCible_)) {
            return true;
        }
        if (cancelImmu_) {
            return false;
        }
        Rate coeffEff_= DataBase.defRateProduct();
        for (String t: typeAttaque_) {
            coeffEff_.multiplyBy(rateEffAgainstTarget(_fight,_lanceur,_cible,t,_import));
        }
        if (proAbility(_fight, _lanceur, _cible, _attaque, _import, creatureCbtCible_, coeffEff_)) {
            return true;
        }
        return proItem(_fight, _cible, _attaque, _import, creatureCbtCible_, coeffEff_);
    }

    private static boolean protItAlly(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, String _attaque, DataBase _import, MoveData _move, Fighter _creatureCbtCible) {
        if(NumberUtil.eq(_lanceur.getTeam(), _cible.getTeam())){
            AbilityData fCapaciteCible_= _creatureCbtCible.ficheCapaciteActuelle(_import);
            if (fCapaciteCible_ != null && fCapaciteCible_.isImmuDamageAllyMoves() && _move instanceof DamagingMoveData) {
                _fight.addProtectByAbilityDamageAllyMessage(_cible, _attaque, _creatureCbtCible.getCurrentAbility(), _import);
                return true;
            }
        }
        return false;
    }

    private static boolean proItem(Fight _fight, TeamPosition _cible, String _attaque, DataBase _import, Fighter _creatureCbtCible, Rate _coeffEff) {
        Berry baie_ = FightItems.useItsBerry(_fight, _cible, _import);
        if (baie_ != null && _coeffEff.greaterThanOne() && !baie_.getHealHpBySuperEffMove().isZero()) {
            _fight.addProtectByItemMessage(_cible, _attaque, _creatureCbtCible.getItem(), _import);
            return true;
        }
        Item it_ = FightItems.useItsObject(_fight, _cible, _import);
        if (it_ instanceof ItemForBattle && StringUtil.contains(((ItemForBattle) it_).getImmuMoves(), _attaque)) {
            _fight.addProtectByItemMessage(_cible, _attaque, _creatureCbtCible.getItem(), _import);
            return true;
        }
        return false;
    }

    private static boolean proAbility(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, String _attaque, DataBase _import, Fighter _creatureCbtCible, Rate _coeffEff) {
        AbilityData fCapaciteCible_ = FightAbilities.ignoredTargetAbility(_fight, _lanceur, _cible, _import);
        boolean ignoreCapaciteCible_= fCapaciteCible_ == null;
        if(!ignoreCapaciteCible_){
            //la capacite de la cible l'immunise a l'attaque du lanceur
            if(StringUtil.contains(fCapaciteCible_.getImmuMove(), _attaque)){
                _fight.addProtectByAbilityMessage(_cible, _attaque, _creatureCbtCible.getCurrentAbility(), _import);
                return true;
            }
            if(_coeffEff.lowerOrEqualsOne()&&fCapaciteCible_.isImmuSufferedDamageLowEff()){
                _fight.addProtectByAbilityMessage(_cible, _attaque, _creatureCbtCible.getCurrentAbility(), _import);
                return true;
            }
        }
        return false;
    }

    private static boolean cancelImmu(Fight _fight, TeamPosition _lanceur, DataBase _import) {
        boolean cancelImmu_ = false;
        Item fObjetLanceur_ = FightItems.useItsObject(_fight, _lanceur, _import);
        if (fObjetLanceur_ instanceof ItemForBattle) {
            ItemForBattle fObjetCombatLanceur_ = (ItemForBattle) fObjetLanceur_;
            if (fObjetCombatLanceur_.getCancelImmuType()) {
                cancelImmu_ = true;
            }
        }
        return cancelImmu_;
    }

    static boolean isProtectedAgainstMoveType(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,String _type,DataBase _import){
        Fighter creatureCbtCible_=_fight.getFighter(_cible);
        boolean ignoreCapaciteCible_ = ignoreCapaciteCibleMoveType(_fight, _lanceur, _cible, _import);
        BoolVal immuAnnuleeGlobal_ = immuAnnuleeGlobal(_fight, _cible, _type, _import, creatureCbtCible_);
        if (immuAnnuleeGlobal_ == null) {
            return true;
        }
        boolean protectedTypes_ = protectedTypes(_type, _import, creatureCbtCible_);
        boolean immuTypeAttaque_ = StringUtil.contains(creatureCbtCible_.getProtectedAgainstMoveTypes(), _type);
        if(immuAnnuleeGlobal_ == BoolVal.TRUE){
            return false;
        }
        if(immuTypeAttaque_){
            if (protectedTypes_) {
                _fight.addProtectTypeByIndividualMoveMessage(_cible, _type, _import);
            }
            return protectedTypes_;
        }
        StringList cancelledAbilities_ = cancelledAbilities(_fight, _import);
        if (StringUtil.contains(cancelledAbilities_, creatureCbtCible_.getCurrentAbility())) {
            return false;
        }
        AbilityData fCapaciteCible_=creatureCbtCible_.ficheCapaciteActuelle(_import);
        if (ignoreCapaciteCible_ || fCapaciteCible_ == null) {
            return false;
        }
        //la capacite de la cible l'immunise a l'attaque du lanceur
        StringMap<StringList> immuAttaqueType_=fCapaciteCible_.getImmuMoveTypesByWeather();
        StringList climats_=FightMoves.enabledGlobalMoves(_fight,_import);
        for(String c: climats_){
            if(!immuAttaqueType_.contains(c)){
                continue;
            }
            if(StringUtil.contains(immuAttaqueType_.getVal(c), _type)){
                _fight.addProtectTypeByAbilityWeatherMessage(_cible, _type, creatureCbtCible_.getCurrentAbility(), c, _import);
                return true;
            }
        }
        if (climats_.isEmpty() && immuAttaqueType_.contains(DataBase.EMPTY_STRING) && StringUtil.contains(immuAttaqueType_.getVal(DataBase.EMPTY_STRING), _type)) {
            _fight.addProtectTypeByAbilityMessage(_cible, _type, creatureCbtCible_.getCurrentAbility(), _import);
            return true;
        }
        return isProtectedAgainstMoveTypeItem(_fight, _cible, _type, _import, creatureCbtCible_);
    }

    private static boolean isProtectedAgainstMoveTypeItem(Fight _fight, TeamPosition _cible, String _type, DataBase _import, Fighter _creatureCbtCible) {
        Item fObjetCible_ = FightItems.useItsObject(_fight, _cible, _import);
        if (fObjetCible_ instanceof ItemForBattle && StringUtil.contains(((ItemForBattle) fObjetCible_).getImmuTypes(), _type)) {
            _fight.addProtectTypeByItemMessage(_cible, _type, _creatureCbtCible.getItem(), _import);
            return true;
        }
        return false;
    }

    private static StringList cancelledAbilities(Fight _fight, DataBase _import) {
        StringList cancelledAbilities_ = new StringList();
        for(String c:FightMoves.enabledGlobalMoves(_fight, _import)){
            MoveData fAttaque_= _import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                cancelledAbilities_.addAllElts(effetGlobal_.getCancelProtectingAbilities());
            }
        }
        return cancelledAbilities_;
    }

    private static boolean protectedTypes(String _type, DataBase _import, Fighter _creatureCbtCible) {
        boolean protectedTypes_ = true;
        for(String e: _creatureCbtCible.getTypes()){
            for(String e2_: _creatureCbtCible.enabledIndividualAntiImmuMoves()){
                MoveData fAttaque_= _import.getMove(e2_);
                int nbEffets_=fAttaque_.nbEffets();
                for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                    Effect effet_=fAttaque_.getEffet(i);
                    if (!(effet_ instanceof EffectUnprotectFromTypes)) {
                        continue;
                    }
                    protectedTypes_ = protectedTypesChanged(_type, protectedTypes_, e, (EffectUnprotectFromTypes) effet_);
                }
            }
        }
        return protectedTypes_;
    }

    private static boolean protectedTypesChanged(String _type, boolean _protectedTypes, String _e, EffectUnprotectFromTypes _effet) {
        boolean protectedTypes_ = _protectedTypes;
        if(StringUtil.contains(_effet.getAttackTargetWithTypes(), _type)){
            protectedTypes_ = false;
        }
        if(TypesDuos.contains(_effet.getTypes(),new TypesDuo(_type, _e))){
            protectedTypes_ = false;
        }
        return protectedTypes_;
    }

    private static BoolVal immuAnnuleeGlobal(Fight _fight, TeamPosition _cible, String _type, DataBase _import, Fighter _creatureCbtCible) {
        CustList<Rate> sums_ = new CustList<Rate>();
        for(String e: _creatureCbtCible.getTypes()){
            Rate coeff_=noNullSumRate(_fight,_cible,_type,_import,e,sums_);
            if (coeff_.isZero()) {
                return null;
            }
        }
        if (!sums_.isEmpty()) {
            return BoolVal.TRUE;
        }
        return BoolVal.FALSE;
    }

    private static boolean ignoreCapaciteCibleMoveType(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, DataBase _import) {
        boolean ignoreCapaciteCible_=FightAbilities.ignoreTargetAbility(_fight, _lanceur, _cible, _import);
        Item fObjetLanceur_ = FightItems.useItsObject(_fight, _lanceur, _import);
        if (fObjetLanceur_ instanceof ItemForBattle && ((ItemForBattle) fObjetLanceur_).getCancelImmuType()) {
            ignoreCapaciteCible_ = true;
        }
        if(TeamPosition.eq(_lanceur, _cible)){
            ignoreCapaciteCible_=false;
        }
        return ignoreCapaciteCible_;
    }

    static Rate rateEffAgainstTargetMove(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,DataBase _import) {
        Fighter creature_=_fight.getFighter(_lanceur);
        String attaqueLanceur_ =creature_.getFinalChosenMove();
        if (attaqueLanceur_.isEmpty()) {
            return Rate.zero();
        }
        return rateEffAgainstTargetMove(_fight, _lanceur, attaqueLanceur_, _cible, _import);
    }

    static Rate rateEffAgainstTargetMove(Fight _fight,TeamPosition _lanceur, String _move,TeamPosition _cible,DataBase _import) {
        Rate efficiency_ = Rate.one();
        StringList typeAttaque_=FightMoves.moveTypes(_fight,_lanceur,_move,_import);
        for (String t:typeAttaque_) {
            Rate rate_ = rateEffAgainstTarget(_fight,_lanceur,_cible,t,_import);
            if(rate_.isZero()){
                efficiency_.affectZero();
                break;
            }
            efficiency_.multiplyBy(rate_);
        }
        return efficiency_;
    }

    static Rate rateEffAgainstTarget(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,String _typeOff,DataBase _import){
        Fighter creatureCbtCible_=_fight.getFighter(_cible);
        StringMap<Rate> coeffEffGl_ = new StringMap<Rate>();
        for(String e:creatureCbtCible_.getTypes()){
            CustList<Rate> sums_ = new CustList<Rate>();
            Rate coeff_ = noNullSumRate(_fight, _cible,_typeOff, _import, e,sums_);
            if (coeff_.isZero()) {
                return Rate.zero();
            }
            if(!sums_.isEmpty()){
                coeffEffGl_.put(e,coeff_);
            }
        }
        StringMap<BoolVal> priseEnCompteCoeffNul_ = priseEnCompteCoeffNul(_fight, _lanceur, _typeOff, _import, creatureCbtCible_, coeffEffGl_);
        return concludeRateEffAgainstTarget(_typeOff, _import, creatureCbtCible_, priseEnCompteCoeffNul_, coeffEffGl_);
    }

    private static StringMap<BoolVal> priseEnCompteCoeffNul(Fight _fight, TeamPosition _lanceur, String _typeOff, DataBase _import, Fighter _creatureCbtCible, StringMap<Rate> _coeffEffGl) {
        StringMap<BoolVal> priseEnCompteCoeffNul_ = priseEnCompteCoeffNulInit(_typeOff, _import, _creatureCbtCible);
        for (EntryCust<String,Rate> e: _coeffEffGl.entryList()) {
            priseEnCompteCoeffNul_.put(e.getKey(),BoolVal.FALSE);
        }
        Fighter creatureCbtLanceur_= _fight.getFighter(_lanceur);
        AbilityData fCapacite_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
        if(fCapacite_ != null){
            for(String e: _creatureCbtCible.getTypes()){
                if(TypesDuos.contains(fCapacite_.getBreakFoeImmune(),new TypesDuo(_typeOff,e))){
                    priseEnCompteCoeffNul_.put(e,BoolVal.FALSE);
                }
            }
        }
        Item fObjetLanceur_ = FightItems.useItsObject(_fight, _lanceur, _import);
        if (fObjetLanceur_ instanceof ItemForBattle && ((ItemForBattle) fObjetLanceur_).getCancelImmuType()) {
            for (String e : _creatureCbtCible.getTypes()) {
                priseEnCompteCoeffNul_.put(e, BoolVal.FALSE);
            }
        }
        return priseEnCompteCoeffNul_;
    }

    private static StringMap<BoolVal> priseEnCompteCoeffNulInit(String _typeOff, DataBase _import, Fighter _creatureCbtCible) {
        StringMap<BoolVal> priseEnCompteCoeffNul_ = new StringMap<BoolVal>();
        for(String e: _creatureCbtCible.getTypes()){
            priseEnCompteCoeffNul_.put(e,BoolVal.TRUE);
            for(String e2_: _creatureCbtCible.enabledIndividualAntiImmuMoves()){
                boolean continuer_ = foundAntiImmu(_typeOff, _import, e, e2_);
                if(continuer_){
                    priseEnCompteCoeffNul_.put(e, BoolVal.FALSE);
                    break;
                }
            }
        }
        return priseEnCompteCoeffNul_;
    }

    private static Rate concludeRateEffAgainstTarget(String _typeOff, DataBase _import, Fighter _creatureCbtCible, StringMap<BoolVal> _priseEnCompteCoeffNul, StringMap<Rate> _coeffEffGl) {
        Rate coeff_=DataBase.defRateProduct();
        for(String e:_creatureCbtCible.getTypes()){
            Rate efficacite_=_import.getTableTypes().getVal(new TypesDuo(_typeOff,e));
            if(_coeffEffGl.contains(e)){
                coeff_.multiplyBy(_coeffEffGl.getVal(e));
            } else if(_priseEnCompteCoeffNul.getVal(e) == BoolVal.TRUE||!efficacite_.isZero()){
                coeff_.multiplyBy(efficacite_);
            }
        }
        return coeff_;
    }

    private static Rate noNullSumRate(Fight _fight, TeamPosition _cible, String _typeOff, DataBase _import, String _t, CustList<Rate> _sums) {
        Rate r_ = DataBase.defRateProduct();
        for(String e2_:FightMoves.enabledGlobalMoves(_fight, _import)){
            MoveData fAtt_= _import.getMove(e2_);
            int nbEffets_=fAtt_.nbEffets();
            Rate sum_ = Rate.zero();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAtt_.getEffet(i);
                if (!(effet_ instanceof EffectGlobal) || !((EffectGlobal) effet_).getEfficiencyMoves().contains(new TypesDuo(_typeOff, _t))) {
                    continue;
                }
                Rate rate_ = ((EffectGlobal)effet_).getEfficiencyMoves().getVal(new TypesDuo(_typeOff, _t));
                if (!rate_.isZero()) {
                    sum_.addNb(rate_);
                }
                r_.multiplyBy(rate_);
            }
            if (!sum_.isZero()) {
                _sums.add(sum_);
            }
            if (r_.isZero()) {
                _fight.addProtectTypeByGlobalMoveMessage(_cible, _typeOff, e2_, _import);
            }
        }
        return r_;
    }

    private static boolean foundAntiImmu(String _typeOff, DataBase _import, String _t, String _m) {
        boolean continuer_ = false;
        MoveData fAtt_= _import.getMove(_m);
        int nbEffets_=fAtt_.nbEffets();
        for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_=fAtt_.getEffet(i);
            if (effet_ instanceof EffectUnprotectFromTypes && (StringUtil.contains(((EffectUnprotectFromTypes) effet_).getAttackTargetWithTypes(), _typeOff) || TypesDuos.contains(((EffectUnprotectFromTypes) effet_).getTypes(), new TypesDuo(_typeOff, _t)))) {
                continuer_ = true;
                break;
            }
        }
        return continuer_;
    }

    static boolean canUseDirectlyMove(Fight _fight,TeamPosition _lanceur, DataBase _import) {
        Fighter creature_ = _fight.getFighter(_lanceur);
        String attaqueLanceur_=creature_.getFinalChosenMove();
        MoveData fAtt_=_import.getMove(attaqueLanceur_);
        short prepa_= fAtt_.getNbPrepaRound();
        if(prepa_>creature_.getNbPrepaRound()){
            Item objet_ = FightItems.useItsObject(_fight, _lanceur, _import);
            if (!(objet_ instanceof ItemForBattle)) {
                return false;
            }
            ItemForBattle objetAttachable_=(ItemForBattle)objet_;
            return objetAttachable_.getAttacksSoon();
        }
        return true;
    }

    static boolean canSkipRecharge(Fight _fight,TeamPosition _lanceur, DataBase _import) {
        Fighter creature_ = _fight.getFighter(_lanceur);
        AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
        if(fCapac_ != null){
            return fCapac_.isImmuRechargeRound();
        }
        return false;
    }

    static Rate accuracy(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,String _attaque,DataBase _import){
        MoveData fAttaque_=_import.getMove(_attaque);
        Rate precision_ = DataBase.defRateProduct();
        StringMap<String> variables_;
        variables_ = FightValues.calculateValues(_fight,_lanceur,_cible,_import);
        precision_ = _import.evaluateNumericable(fAttaque_.getAccuracy(), variables_, precision_);
//        SortableCustList<Rate> rates_ = new SortableCustList<Rate>();
//        rates_.add(precision_);
//        rates_.add(Rate.zero());
//        rates_.sort(new NaturalComparator<Rate>() {
//            @Override
//            public int compare(Rate _o1, Rate _o2) {
//                return _o1.compareTo(_o2);
//            }
//        });
//        rates_.sort();
        if (precision_.isZeroOrLt()) {
            //en:case negative or zero
            //fr:cas negatif ou nul
            return Rate.zero();
        }

        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        Fighter creatureCbtCible_=_fight.getFighter(_cible);
        AbilityData fCapaciteLoc_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
        if (fCapaciteLoc_ != null && fCapaciteLoc_.isBreakProtection() && !protectedTargetAgainstMove(_fight, _lanceur, _cible, _attaque, _import)) {
            return DataBase.determinatedRate();
        }
        multAcc(_fight, _lanceur, _import, fAttaque_, precision_, creatureCbtLanceur_);
        Rate evasiness_ = evasiness(_fight, _lanceur, _cible, _import, fAttaque_, creatureCbtCible_);
        if (evasiness_.isZero()) {
            return DataBase.determinatedRate();
        }
        multStat(precision_, _fight, _lanceur, Statistic.ACCURACY, _import, creatureCbtLanceur_, fAttaque_);
        for(String c: FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttGl_=_import.getMove(c);
            int nbEffets_=fAttGl_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttGl_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                if(!effetGlobal_.getMultAccuracy().isZero()){
                    precision_.multiplyBy(effetGlobal_.getMultAccuracy());
                }
            }
        }
        return Rate.divide(precision_, evasiness_);
    }

    private static void multAcc(Fight _fight, TeamPosition _lanceur, DataBase _import, MoveData _fAttaque, Rate _precision, Fighter _creatureCbtLanceur) {
        byte boost_= _creatureCbtLanceur.getStatisBoost().getVal(Statistic.ACCURACY);
        byte baseBoost_=(byte) _import.getDefaultBoost();
        boost_ += FightStatistic.bonusBoost(_fight, Statistic.ACCURACY, _lanceur, _import);
        if(boost_>=baseBoost_){
            _precision.multiplyBy(FightStatistic.rateBoost(boost_, _import));
        }else{
            if(!_fAttaque.getIgnVarAccurUserNeg()){
                _precision.multiplyBy(FightStatistic.rateBoost(boost_, _import));
            }
        }
    }

    private static Rate evasiness(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, DataBase _import, MoveData _fAttaque, Fighter _creatureCbtCible) {
        byte baseBoost_=(byte)_import.getDefaultBoost();
        byte boost_;
        boost_= _creatureCbtCible.getStatisBoost().getVal(Statistic.EVASINESS);
        boost_ += FightStatistic.bonusBoost(_fight, Statistic.EVASINESS, _cible, _import);
        boolean ignorer_=true;
        Rate evasiness_ = Rate.one();
        if(!TeamPosition.eq(_lanceur, _cible)){
            if(boost_<= baseBoost_){
                evasiness_.multiplyBy(FightStatistic.rateBoost(boost_, _import));
                ignorer_=false;
            }else{
                if(!_fAttaque.getIgnVarEvasTargetPos()){
                    evasiness_.multiplyBy(FightStatistic.rateBoost(boost_, _import));
                    ignorer_=FightAbilities.ignoreTargetAbility(_fight, _lanceur, _cible, _import);
                }
            }
        }
        if(!ignorer_){
            //PAUSE _d->val_non_affecte_poudre_claire().contains(_nom_att_l)
            multStat(evasiness_, _fight, _cible, Statistic.EVASINESS, _import, _creatureCbtCible, _fAttaque);
        }
        return evasiness_;
    }

    private static void multStat(Rate _valueStat, Fight _fight, TeamPosition _cbt, Statistic _stat, DataBase _import, Fighter _creature, MoveData _fAttaque) {
        _valueStat.multiplyBy(FightStatistic.statisticWithoutBase(_fight, _cbt, _stat, FightValues.calculateValuesFighter(_fight, _cbt, _import), _import));
        AbilityData fCapacite_= _creature.ficheCapaciteActuelle(_import);
        if(fCapacite_ != null){
            String categ_ = _fAttaque.getCategory();
            if(fCapacite_.getMultStatIfCat().contains(new StatisticCategory(_stat,categ_))){
                _valueStat.multiplyBy(fCapacite_.getMultStatIfCat().getVal(new StatisticCategory(_stat,categ_)));
            }
        }
    }

    static boolean successfulEffectWhileIfTargetIsNotThrower(
            Fight _fight,
            TeamPosition _lanceur,TeamPosition _cible,
            String _attaque,int _noEffet,boolean _withPreviousEffect,DataBase _import) {
        MoveData fAttaque_=_import.getMove(_attaque);
        Effect effet_=fAttaque_.getEffet(_noEffet);
        boolean primaire_= _noEffet == fAttaque_.indexOfPrimaryEffect();
        boolean reussi_ = reussiEffet(_cible, _withPreviousEffect, effet_, _fight.getSuccessfulEffects());
        if(!reussi_){
            return false;
        }
        if (fAttaque_.getTargetChoice() == TargetChoice.ALLIE || fAttaque_.getTargetChoice() == TargetChoice.ALLIES) {
            return true;
        }
        Fighter creatureCbtCible_=_fight.getFighter(_cible);
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        AbilityData ficheCapacite_ = FightAbilities.ignoredTargetAbility(_fight, _lanceur, _cible, _import);
        AbilityData fCapaciteLanceurLoc_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
        boolean touchePseudoInvulnerable_ = fCapaciteLanceurLoc_ != null && fCapaciteLanceurLoc_.isAchievedDisappearedPk();
        if (ficheCapacite_ != null && (!TeamPosition.eq(_lanceur, _cible) && ficheCapacite_.isCancelSecEffectOther() && !primaire_ || TeamPosition.eq(_lanceur, _cible) && ficheCapacite_.isCancelSecEffectOwner() && !primaire_)) {
            _fight.addProtectedAgainstSecEffMessage(_cible, _attaque, creatureCbtCible_.getCurrentAbility(), _import);
            return false;
        }
        boolean precisionMaxCible_ = precisionMaxCible(_cible, creatureCbtLanceur_.getIncrUserAccuracy());
        boolean touchePrepaTourGl_ = touchePrepaTourGl(_fight, _lanceur, _attaque, _import, creatureCbtCible_);
        if (creatureCbtCible_.isDisappeared() && !touchePseudoInvulnerable_ && !touchePrepaTourGl_ && !precisionMaxCible_ && !StringUtil.contains(fAttaque_.getAchieveDisappearedPkUsingMove(), creatureCbtCible_.getFinalChosenMove())) {
            _fight.addProtectedByDisappearingMessage(_cible, _import);
            return false;
        }
        AbilityData fCapaciteLanceur_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
        boolean passeAbri_ = fCapaciteLanceur_ != null && fCapaciteLanceur_.isBreakProtection() && !NumberUtil.eq(_lanceur.getTeam(), _cible.getTeam());
        return passeAbri_ || !protectedTargetAgainstMove(_fight, _lanceur, _cible, _attaque, _import);
    }

    private static boolean touchePrepaTourGl(Fight _fight, TeamPosition _lanceur, String _attaque, DataBase _import, Fighter _creatureCbtCible) {
        boolean touchePrepaTourGl_=false;
        StringList typeAttaqueLanceur_=FightMoves.moveTypes(_fight,_lanceur,_attaque,_import);
        for(String c:FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttGlobal_=_import.getMove(c);
            int nbEffets_=fAttGlobal_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effetLoc_=fAttGlobal_.getEffet(i);
                if(!(effetLoc_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effetLoc_;
                for (String t: typeAttaqueLanceur_) {
                    if (effetGlobal_.getMultDamagePrepaRound().contains(t) && StringUtil.contains(effetGlobal_.getMovesUsedByTargetedFighters(), _creatureCbtCible.getFinalChosenMove()) && _creatureCbtCible.getNbPrepaRound() > 0) {
                        touchePrepaTourGl_ = true;
                        break;
                    }
                }
            }
        }
        return touchePrepaTourGl_;
    }

    private static boolean precisionMaxCible(TeamPosition _cible, MoveTeamPositionsBoolVal _incrUserAccuracy) {
        boolean precisionMaxCible_=false;
        for(MoveTeamPosition c: _incrUserAccuracy.getKeys()){
            if (TeamPosition.eq(c.getTeamPosition(), _cible) && _incrUserAccuracy.getVal(c) == BoolVal.TRUE) {
                precisionMaxCible_ = true;
                break;
            }
        }
        return precisionMaxCible_;
    }

    private static boolean reussiEffet(TeamPosition _cible, boolean _withPreviousEffect, Effect _effet, NbEffectFighterCoordss _successfulEffects) {
        boolean reussi_=true;
        if (_withPreviousEffect) {
            for(int e: _effet.getRequiredSuccessfulEffects()){
                if (_successfulEffects.contains(new NbEffectFighterCoords(e, _cible)) && _successfulEffects.getVal(new NbEffectFighterCoords(e, _cible)) != BoolVal.TRUE) {
                    reussi_ = false;
                    break;
                }
            }
        }
        return reussi_;
    }

    static boolean successEffect(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,
            Effect _effect,
            DataBase _import) {
        if(_effect instanceof EffectStatistic){
            EffectStatistic effetStatistique_=(EffectStatistic)_effect;
            EnumList<Statistic> statistiquesMod_=successfulChangedStatistics(_fight,_lanceur, _cible,effetStatistique_,_import);
            if(statistiquesMod_.isEmpty()){
                return false;
            }
        }
        if(_effect instanceof EffectStatus){
            EffectStatus effetStatut_=(EffectStatus)_effect;
            if(!successfulEffectStatus(_fight,_lanceur, _cible,effetStatut_,_import)){
                return false;
            }
        }
        if(_effect instanceof EffectInvoke){
            EffectInvoke effectInvoke_ = (EffectInvoke) _effect;
            StringList moves_ = FightInvoke.invokableMoves(_fight, _lanceur, _cible, effectInvoke_, _import);
            return !moves_.isEmpty();
        }
        return true;
    }

    static EnumList<Statistic> successfulChangedStatistics(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectStatistic _effet,DataBase _import){
        if(!_effet.getStatisVarRank().isEmpty()){
            return successfulChangedStatisticsCrans(_fight, _lanceur, _cible, _effet, _import);
        }
        if(!_effet.getCopyBoost().isEmpty()){
            return successfulChangedStatisticsCopyBoost(_fight, _lanceur, _cible, _effet, _import);
        }
        if(!_effet.getCancelLowStat().isEmpty()){
            return successfulChangedStatisticsCancelLowStat(_fight, _cible, _effet, _import);
        }
        EnumList<Statistic> changedStatis_ = new EnumList<Statistic>();
        changedStatis_.addAllElts(_effet.getCancelChgtStat());
        EnumList<Statistic> echangeStatis_=_effet.getSwapBoostStatis();
        AbsMap<Statistic,String> raisonsEchec_=_effet.getLocalFailSwapBoostStatis();
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        Fighter creatureCbtCible_=_fight.getFighter(_cible);
        //CustList<Statistic> statistiquesEchangees_=new CustList<>();
        for(Statistic c:echangeStatis_){
            byte boostLanceur_=creatureCbtLanceur_.getStatisBoost().getVal(c);
            byte boostCible_=creatureCbtCible_.getStatisBoost().getVal(c);
            boolean passerIteration_= !successChangedStatistic(_fight, _lanceur, _cible, c, (byte) (boostLanceur_ - boostCible_), _import);
            if(!successChangedStatistic(_fight,_cible,_lanceur,c,(byte) (boostCible_-boostLanceur_),_import)){
                passerIteration_=true;
            }
            if(passerIteration_){
                continue;
            }
            if (!raisonsEchec_.contains(c) || !_import.evaluateBoolean(raisonsEchec_.getVal(c), FightValues.calculateValues(_fight, _lanceur, _cible, _import), false)) {
                changedStatis_.add(c);
            }
        }
        return changedStatis_;
    }

    private static EnumList<Statistic> successfulChangedStatisticsCancelLowStat(Fight _fight, TeamPosition _cible, EffectStatistic _effet, DataBase _import) {
        EnumList<Statistic> annuleBaisse_= _effet.getCancelLowStat();
        Fighter creatureCbtCible_= _fight.getFighter(_cible);
        EnumList<Statistic> statistiquesBaisseAnnulees_=new EnumList<Statistic>();
        for(Statistic c:annuleBaisse_){
            byte boostCible_=creatureCbtCible_.getStatisBoost().getVal(c);
            if(boostCible_< _import.getDefaultBoost()){
                statistiquesBaisseAnnulees_.add(c);
            }
        }
        return statistiquesBaisseAnnulees_;
    }

    private static EnumList<Statistic> successfulChangedStatisticsCopyBoost(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, EffectStatistic _effet, DataBase _import) {
        EnumList<Statistic> copieBoost_= _effet.getCopyBoost();
        Fighter creatureCbtLanceur_= _fight.getFighter(_lanceur);
        Fighter creatureCbtCible_= _fight.getFighter(_cible);
        EnumList<Statistic> statistiquesCopiees_= new EnumList<Statistic>();
        for(Statistic c:copieBoost_){
            byte boostLanceur_=creatureCbtLanceur_.getStatisBoost().getVal(c);
            byte boostCible_=creatureCbtCible_.getStatisBoost().getVal(c);
            if(successChangedStatistic(_fight, _lanceur, _lanceur,c,(byte) (boostCible_-boostLanceur_), _import)){
                statistiquesCopiees_.add(c);
            }
        }
        return statistiquesCopiees_;
    }

    private static EnumList<Statistic> successfulChangedStatisticsCrans(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, EffectStatistic _effet, DataBase _import) {
        EnumList<Statistic> statistiques_ = successfulChangedBoostedStatistics(_fight,_lanceur, _cible, _effet, _import);
        AbsMap<Statistic,String> raisonsEchec_= _effet.getLocalFailStatis();
        EnumList<Statistic> statistiquesVariant_=new EnumList<Statistic>();
        for(Statistic c: statistiques_){
            if (!raisonsEchec_.contains(c) || !_import.evaluateBoolean(raisonsEchec_.getVal(c), FightValues.calculateValues(_fight, _lanceur, _cible, _import), false)) {
                statistiquesVariant_.add(c);
            }
        }
        return statistiquesVariant_;
    }

    static EnumList<Statistic> successfulChangedBoostedStatistics(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,EffectStatistic _effet,DataBase _import) {
        EnumList<Statistic> statistiques_=new EnumList<Statistic>();
        AbsMap<Statistic,Byte> varStatisCran_=_effet.getStatisVarRank();
        for (Statistic s: _effet.getStatisVarRank().getKeys()) {
            if(successChangedStatistic(_fight,_lanceur,_cible,s,varStatisCran_.getVal(s),_import)){
                statistiques_.add(s);
            }
        }
        return statistiques_;
    }

    static boolean successChangedStatistic(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,Statistic _statistique,byte _variation,DataBase _import){
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        boolean ignoreCapaciteCible_=FightAbilities.ignoreTargetAbility(_fight,_lanceur,_cible,_import);
        StringList protectionsIgnorees_=new StringList();
        AbilityData fCapaciteLanceur_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
        if (fCapaciteLanceur_ != null && !NumberUtil.eq(_lanceur.getTeam(), _cible.getTeam())) {
            protectionsIgnorees_.addAllElts(fCapaciteLanceur_.getIgnFoeTeamMove());
        }
        return successChangedStatisticProtect(_fight,_cible,_statistique,_variation,ignoreCapaciteCible_,protectionsIgnorees_,_import);
    }

    static boolean successChangedStatisticProtect(Fight _fight,TeamPosition _combattant,Statistic _statistique,byte _variation,
                                                  boolean _ignoreCapacite,StringList _protectionsIgnorees,DataBase _import){
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        Fighter creatureCbt_=_fight.getFighter(_combattant);
        boolean immuByType_ = immuStatisByType(_fight, _combattant, _statistique, _variation, _import, equipe_, creatureCbt_);
        if (immuByType_) {
            return false;
        }
        if (immuStatisByTeam(_fight, _combattant, _statistique, _variation, _protectionsIgnorees, _import, equipe_)) {
            return false;
        }
        AbilityData fCapac_=creatureCbt_.ficheCapaciteActuelle(_import);
        if (!_ignoreCapacite && fCapac_ != null && _variation < 0) {
            if (fCapac_.getImmuLowStat().containsObj(_statistique)) {
                _fight.addImmuLowStatAbilityMessage(_combattant, _statistique, creatureCbt_.getCurrentAbility(), _import);
                return false;
            }
            for (String c : creatureCbt_.getStatusSet()) {
                if (!NumberUtil.eq(creatureCbt_.getStatusNbRoundShort(c), 0) && fCapac_.containsStatisticStatus(new StatisticStatus(_statistique, c))) {
                    _fight.addImmuLowStatStAbilityMessage(_combattant, _statistique, creatureCbt_.getCurrentAbility(), c, _import);
                    return false;
                }
            }
        }
        Item objet_ = FightItems.useItsObject(_fight, _combattant, _import);
        if (objet_ instanceof ItemForBattle && ((ItemForBattle) objet_).getImmuLowStatis() && _variation < 0) {
            _fight.addImmuLowStatItemMessage(_combattant, _statistique, creatureCbt_.getItem(), _import);
            return false;
        }
        return checkBoost(_fight, _combattant, _statistique, _variation, _import, creatureCbt_);
    }

    private static boolean checkBoost(Fight _fight, TeamPosition _combattant, Statistic _statistique, byte _variation, DataBase _import, Fighter _creatureCbt) {
        byte boost_= _creatureCbt.getStatisBoost().getVal(_statistique);
        byte maxBoost_=(byte) _import.getMaxBoost();
        byte minBoost_=(byte) _import.getMinBoost();
        if(_variation >0){
            if(boost_==maxBoost_){
                _fight.addImmuChgtStatMaxMessage(_combattant, _statistique, _import);
                return false;
            }
            return true;
        }
        if(boost_==minBoost_){
            _fight.addImmuChgtStatMinMessage(_combattant, _statistique, _import);
            return false;
        }
        return true;
    }

    private static boolean immuStatisByTeam(Fight _fight, TeamPosition _combattant, Statistic _statistique, byte _variation, StringList _protectionsIgnorees, DataBase _import, Team _equipe) {
        StringMap<CustList<EffectTeam>> effectTeams_ = effectsTeamProt(_import, _equipe, _protectionsIgnorees);
        int nbEffects_ = effectTeams_.size();
        for (int c = 0; c < nbEffects_; c++) {
            CustList<EffectTeam> ls_ = effectTeams_.getValue(c);
            int nbEffets_= ls_.size();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                if(ls_.get(i).getProtectAgainstLowStat().containsObj(_statistique)&& _variation <0){
                    _fight.addImmuLowStatTeamMessage(_combattant, _statistique, effectTeams_.getKey(c), _import);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean immuStatisByType(Fight _fight, TeamPosition _combattant, Statistic _statistique, byte _variation, DataBase _import, Team _equipe, Fighter _creatureCbt) {
        boolean immuByType_ = false;
        for (byte f_: _equipe.frontFighters()) {
            Fighter part_ = _fight.getFighter(new TeamPosition(_combattant.getTeam(), f_));
            AbilityData ab_ = part_.ficheCapaciteActuelle(_import);
            if (ab_ == null) {
                continue;
            }
            for (String t: _import.getTypes()) {
                if (ab_.getImmuLowStatisTypes().contains(t) && ab_.getImmuLowStatisTypes().getVal(t).containsObj(_statistique) && StringUtil.contains(_creatureCbt.getTypes(), t) && _variation < 0) {
                    _fight.addImmuLowStatAbilityAllyMessage(_combattant, _statistique, part_.getCurrentAbility(), _import);
                    immuByType_ = true;
                }
            }
        }
        return immuByType_;
    }

    static boolean protectedTargetAgainstMove(Fight _fight, TeamPosition _user, TeamPosition _target, String _move, DataBase _import) {
        Team equipeCible_=_fight.getTeams().getVal(_target.getTeam());
        Fighter creatureCbtCible_=_fight.getFighter(_target);
        MoveData fAttaque_=_import.getMove(_move);
        if (StringUtil.contains(_import.getMovesCountering(), creatureCbtCible_.getFinalChosenMove())) {
            String move_ = creatureCbtCible_.getFinalChosenMove();
            if (creatureCbtCible_.getEnabledCounteringMoves().getVal(move_).isEnabled() && (!sufferingDamageTypes(_fight, _user, _target, _move, _import).isEmpty() || droppedStatis(_fight, _user, _target, _move, true, _import) || sufferingDirectMoves(_fight, _user, _target, _move, true, _import))) {
                _fight.addProtectedByIndividualMoveMessage(_target, _move, move_, _import);
                return true;
            }
        }
        if (protectedTargetAgainstKindMove(fAttaque_ instanceof DamagingMoveData, equipeCible_, _import.getMovesProtAgainstDamageMoves(), _fight, _target, _move, _import)) {
            return true;
        }
        if (protectedTargetAgainstKindMove(fAttaque_ instanceof StatusMoveData, equipeCible_, _import.getMovesProtAgainstStatusMoves(), _fight, _target, _move, _import)) {
            return true;
        }
        if(fAttaque_.getStoppableMoveSolo()&&creatureCbtCible_.isSuccessfulMove()){
            String move_ = creatureCbtCible_.getFinalChosenMove();
            if(StringUtil.contains(_import.getMovesProtSingleTarget(), move_)){
                _fight.addProtectedByIndividualMoveMessage(_target, _move, move_, _import);
                return true;
            }
        }
        if (protectedTargetAgainstKindMove(fAttaque_.getStoppableMoveMulti(), equipeCible_, _import.getMovesProtAgainstMultiTarget(), _fight, _target, _move, _import)) {
            return true;
        }
        return protectedTargetAgainstKindMove(fAttaque_.getStoppableMovePrio() && fAttaque_.getPriority() > 0, equipeCible_, _import.getMovesProtAgainstPrio(), _fight, _target, _move, _import);
    }

    private static boolean protectedTargetAgainstKindMove(boolean _condition, Team _equipeCible, StringList _moves, Fight _fight, TeamPosition _target, String _move, DataBase _data) {
        if (_condition) {
            for(byte c: _equipeCible.getMembers().getKeys()){
                Fighter creatureCbtMembre_= _equipeCible.getMembers().getVal(c);
                if(creatureCbtMembre_.isSuccessfulMove()){
                    String move_ = creatureCbtMembre_.getFinalChosenMove();
                    if(StringUtil.contains(_moves, move_)){
                        _fight.addProtectedByTeamMoveMessage(_target, _move, move_, _data);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static StringMap<Rate> sufferingDamageTypes(Fight _fight, TeamPosition _user, TeamPosition _target, String _move, DataBase _import) {
        Fighter creatureCbtCible_=_fight.getFighter(_target);
        MoveData fAttaque_=_import.getMove(_move);
        MoveData counteringMove_ = _import.getMove(creatureCbtCible_.getFinalChosenMove());
        StringMap<Rate> sufferd_ = new StringMap<Rate>();
        if (!(fAttaque_ instanceof DamagingMoveData)) {
            return sufferd_;
        }
        int nbEffects_ = counteringMove_.nbEffets();
        for (int i = IndexConstants.FIRST_INDEX; i < nbEffects_; i++) {
            Effect eff_ = counteringMove_.getEffet(i);
            if (!(eff_ instanceof EffectCounterAttack)) {
                continue;
            }
            EffectCounterAttack effectLoc_ = (EffectCounterAttack) eff_;
            StringMap<String> values_ = values(_fight, _user, _target, _import);
            if (!_import.evaluateBoolean(effectLoc_.getProtectFail(), values_, false)) {
                for (String t : FightMoves.moveTypes(_fight, _user, _move, _import)) {
                    if (effectLoc_.getSufferingDamageTypes().contains(t)) {
                        sufferd_.put(t, effectLoc_.getSufferingDamageTypes().getVal(t));
                    }
                }
            }
        }
        return sufferd_;
    }

    static boolean droppedStatis(Fight _fight, TeamPosition _user, TeamPosition _target, String _move, boolean _protect,DataBase _import) {
        return sufferingCommon(_fight,_user,_target,_move,_protect,_import,new EffectCounterAttackCheckerDropStat());
//        Fighter creatureCbtCible_=_fight.getFighter(_target);
//        MoveData fAttaque_=_import.getMove(_move);
//        MoveData counteringMove_ = _import.getMove(creatureCbtCible_.getFinalChosenMove());
//        if (!(fAttaque_ instanceof DamagingMoveData)) {
//            return false;
//        }
//        int nbEffects_ = counteringMove_.nbEffets();
//        boolean success_ = false;
//        for (int i = IndexConstants.FIRST_INDEX; i < nbEffects_; i++) {
//            Effect eff_ = counteringMove_.getEffet(i);
//            if (!(eff_ instanceof EffectCounterAttack)) {
//                continue;
//            }
//            EffectCounterAttack effectLoc_ = (EffectCounterAttack) eff_;
//            if (effectLoc_.getDroppedStatDirectMove().isEmpty()) {
//                continue;
//            }
//            StringMap<String> values_ = new StringMap<String>();
//            values_.putAllMap(FightValues.calculateValues(_fight,_user,_target,_import));
//            values_.putAllMap(FightValues.calculateBasicBooleanValues(_fight,_user, _target, _import));
//            if (_import.evaluateBoolean(effectLoc_.getProtectFail(), values_, false)) {
//                continue;
//            }
//            success_ = true;
//        }
//        if (success_) {
//            if (_protect) {
//                return true;
//            }
//            DamagingMoveData damMove_ = (DamagingMoveData) fAttaque_;
//            return damMove_.isDirect();
//        }
//        return false;
    }

    static boolean sufferingDirectMoves(Fight _fight, TeamPosition _user, TeamPosition _target, String _move, boolean _protect, DataBase _import) {
        return sufferingCommon(_fight,_user,_target,_move,_protect,_import,new EffectCounterAttackCheckerDirectMove());
//        Fighter creatureCbtCible_=_fight.getFighter(_target);
//        MoveData fAttaque_=_import.getMove(_move);
//        MoveData counteringMove_ = _import.getMove(creatureCbtCible_.getFinalChosenMove());
//        if (!(fAttaque_ instanceof DamagingMoveData)) {
//            return false;
//        }
//        int nbEffects_ = counteringMove_.nbEffets();
//        boolean success_ = false;
//        for (int i = IndexConstants.FIRST_INDEX; i < nbEffects_; i++) {
//            Effect eff_ = counteringMove_.getEffet(i);
//            if (!(eff_ instanceof EffectCounterAttack)) {
//                continue;
//            }
//            EffectCounterAttack effectLoc_ = (EffectCounterAttack) eff_;
//            if (effectLoc_.getSufferingDamageDirectMove().isZero()) {
//                continue;
//            }
//            StringMap<String> values_ = new StringMap<String>();
//            values_.putAllMap(FightValues.calculateValues(_fight,_user,_target,_import));
//            values_.putAllMap(FightValues.calculateBasicBooleanValues(_fight,_user, _target, _import));
//            if (_import.evaluateBoolean(effectLoc_.getProtectFail(), values_, false)) {
//                continue;
//            }
//            success_ = true;
//        }
//        if (success_) {
//            if (_protect) {
//                return true;
//            }
//            DamagingMoveData damMove_ = (DamagingMoveData) fAttaque_;
//            return damMove_.isDirect();
//        }
//        return false;
    }
    private static boolean sufferingCommon(Fight _fight, TeamPosition _user, TeamPosition _target, String _move, boolean _protect, DataBase _import, AbsEffectCounterAttackChecker _filter) {
        Fighter creatureCbtCible_=_fight.getFighter(_target);
        MoveData fAttaque_=_import.getMove(_move);
        MoveData counteringMove_ = _import.getMove(creatureCbtCible_.getFinalChosenMove());
        if (!(fAttaque_ instanceof DamagingMoveData)) {
            return false;
        }
        int nbEffects_ = counteringMove_.nbEffets();
        boolean success_ = false;
        for (int i = IndexConstants.FIRST_INDEX; i < nbEffects_; i++) {
            Effect eff_ = counteringMove_.getEffet(i);
            if (!(eff_ instanceof EffectCounterAttack) || _filter.skip((EffectCounterAttack) eff_) || _import.evaluateBoolean(((EffectCounterAttack) eff_).getProtectFail(), values(_fight, _user, _target, _import), false)) {
                continue;
            }
            success_ = true;
        }
        if (success_) {
            if (_protect) {
                return true;
            }
            DamagingMoveData damMove_ = (DamagingMoveData) fAttaque_;
            return damMove_.isDirect();
        }
        return false;
    }

    private static StringMap<String> values(Fight _fight, TeamPosition _user, TeamPosition _target, DataBase _import) {
        StringMap<String> values_ = new StringMap<String>();
        values_.putAllMap(FightValues.calculateValues(_fight, _user, _target, _import));
        values_.putAllMap(FightValues.calculateBasicBooleanValues(_fight, _user, _target, _import));
        return values_;
    }

    static boolean successfulEffectStatus(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,EffectStatus _effet,DataBase _import){
        if(_effet.getStatusFromUser()){
            return successfulEffectStatusFromUser(_fight, _lanceur, _cible, _import);
        }
        if (!_effet.getDeletedStatus().isEmpty() || _effet.getKoUserHealSubst()) {
            return true;
        }
        MonteCarloString loiStatuts_=_effet.getLawStatus();
        StringMap<String> echecStatuts_=_effet.getLocalFailStatus();
        for(String c:loiStatuts_.events()){
            if (!StringUtil.quickEq(c, DataBase.EMPTY_STRING) && (!echecStatuts_.contains(c) || echecStatuts_.getVal(c).isEmpty() || !_import.evaluateBoolean(echecStatuts_.getVal(c), FightValues.calculateValues(_fight, _lanceur, _cible, _import), false)) && successfulAffectedStatus(_fight, _lanceur, _cible, c, _import)) {
                return true;
            }
        }
        return false;
    }

    private static boolean successfulEffectStatusFromUser(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, DataBase _import) {
        StringList statutsTranferes_=new StringList();
        Fighter creatureCbtLanceur_= _fight.getFighter(_lanceur);
        for(String c:creatureCbtLanceur_.getStatusSet()){
            if (!NumberUtil.eq(creatureCbtLanceur_.getStatusNbRoundShort(c), 0) && successfulAffectedStatus(_fight, _lanceur, _cible, c, _import)) {
                statutsTranferes_.add(c);
            }
        }
        return !statutsTranferes_.isEmpty();
    }

    static boolean successfulAffectedStatus(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,String _statut,DataBase _import){
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        Status statut_=_import.getStatus().getVal(_statut);
        if(statut_.estActifPartenaire()){
            EffectPartnerStatus effet_=statut_.getEffectsPartner().first();
            if (effet_.getWeddingAlly() && NumberUtil.eq(_lanceur.getTeam(), _cible.getTeam())) {
                return true;
            }
        }
        boolean ignoreCapaciteCible_=FightAbilities.ignoreTargetAbility(_fight,_lanceur,_cible,_import);
        StringList protectionsIgnorees_=new StringList();
        AbilityData fCapaciteLanceur_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
        if (fCapaciteLanceur_ != null && !NumberUtil.eq(_lanceur.getTeam(), _cible.getTeam())) {
            protectionsIgnorees_.addAllElts(fCapaciteLanceur_.getIgnFoeTeamMove());
        }
        return successfulAffectedStatusProtect(_fight,_cible,_statut,ignoreCapaciteCible_,protectionsIgnorees_,_import);
    }

    static boolean successfulAffectedStatusProtect(Fight _fight,TeamPosition _combattant,String _statut,boolean _ignoreCapacite,StringList _protectionsIgnorees,DataBase _import){
        if (StringUtil.contains(forbiddenStatus(_fight, _import), _statut)) {
            _fight.addImmuStatGlobalMoveMessage(_combattant, _statut, _import);
            return false;
        }
        return successfulAffectedStatusProtectLocal(_fight, _combattant, _statut, _ignoreCapacite, _protectionsIgnorees, _import);
    }

    private static boolean successfulAffectedStatusProtectLocal(Fight _fight, TeamPosition _combattant, String _statut, boolean _ignoreCapacite, StringList _protectionsIgnorees, DataBase _import) {
        Team equipe_= _fight.getTeams().getVal(_combattant.getTeam());
        Fighter creatureCbt_= _fight.getFighter(_combattant);
        boolean immuByType_ = immuStatusByType(_fight, _combattant, _statut, _import, equipe_, creatureCbt_);
        if (immuByType_) {
            return false;
        }
        if (immuStatusByTeam(_fight, _combattant, _statut, _protectionsIgnorees, _import, equipe_)) {
            return false;
        }
        AbilityData fCapac_=creatureCbt_.ficheCapaciteActuelle(_import);
        if(!_ignoreCapacite &&fCapac_ != null){
            StringList globalMoves_ = FightMoves.enabledGlobalMoves(_fight, _import);
            for(String c: globalMoves_){
                if (fCapac_.getImmuStatus().contains(c) && StringUtil.contains(fCapac_.getImmuStatus().getVal(c), _statut)) {
                    _fight.addImmuStatGlobalMoveAbilityMessage(_combattant, _statut, creatureCbt_.getCurrentAbility(), c, _import);
                    return false;
                }
            }
            if (fCapac_.getImmuStatus().contains(DataBase.EMPTY_STRING) && globalMoves_.isEmpty() && StringUtil.contains(fCapac_.getImmuStatus().getVal(DataBase.EMPTY_STRING), _statut)) {
                _fight.addImmuStatAbilityMessage(_combattant, _statut, creatureCbt_.getCurrentAbility(), _import);
                return false;
            }
        }
        Item objet_ = FightItems.useItsObject(_fight, _combattant, _import);
        if (objet_ instanceof ItemForBattle && StringUtil.contains(((ItemForBattle) objet_).getImmuStatus(), _statut)) {
            _fight.addImmuStatItemMessage(_combattant, _statut, creatureCbt_.getItem(), _import);
            return false;
        }
        return true;
    }

    private static boolean immuStatusByTeam(Fight _fight, TeamPosition _combattant, String _statut, StringList _protectionsIgnorees, DataBase _import, Team _equipe) {
        StringMap<CustList<EffectTeam>> effectTeams_ = effectsTeamProt(_import, _equipe, _protectionsIgnorees);
        int nbEffects_ = effectTeams_.size();
        for (int c = 0; c < nbEffects_; c++) {
            CustList<EffectTeam> ls_ = effectTeams_.getValue(c);
            int nbEffets_= ls_.size();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                if(StringUtil.contains(ls_.get(i).getProtectAgainstStatus(), _statut)){
                    _fight.addImmuStatTeamMessage(_combattant, _statut, effectTeams_.getKey(c), _import);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean immuStatusByType(Fight _fight, TeamPosition _combattant, String _statut, DataBase _import, Team _equipe, Fighter _creatureCbt) {
        boolean immuByType_ = false;
        for (byte f_: _equipe.frontFighters()) {
            Fighter part_ = _fight.getFighter(new TeamPosition(_combattant.getTeam(), f_));
            AbilityData ab_ = part_.ficheCapaciteActuelle(_import);
            if (ab_ == null) {
                continue;
            }
            for (String t: _import.getTypes()) {
                if (ab_.getImmuStatusTypes().contains(t) && StringUtil.contains(ab_.getImmuStatusTypes().getVal(t), _statut) && StringUtil.contains(_creatureCbt.getTypes(), t)) {
                    _fight.addImmuStatAbilityAllyMessage(_combattant, _statut, part_.getCurrentAbility(), _import);
                    immuByType_ = true;
                }
            }
        }
        return immuByType_;
    }

    static StringMap<CustList<EffectTeam>> effectsTeamProt(DataBase _import, Team _equipe,StringList _protectionsIgnorees) {
        StringMap<CustList<EffectTeam>> effs_ = new StringMap<CustList<EffectTeam>>();
        for(String c: _equipe.enabledTeamMoves()) {
            if (StringUtil.contains(_protectionsIgnorees, c)) {
                continue;
            }
            effs_.addEntry(c,effectsTeamMove(_import,c));
        }
        return effs_;
    }
    static CustList<EffectTeam> effectsTeamMove(DataBase _import, String _c) {
        CustList<EffectTeam> list_ = new CustList<EffectTeam>();
        MoveData fAttEquipe_=_import.getMove(_c);
        int nbEffets_=fAttEquipe_.nbEffets();
        for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_=fAttEquipe_.getEffet(i);
            if(!(effet_ instanceof EffectTeam)){
                continue;
            }
            EffectTeam effetEquipe_=(EffectTeam)effet_;
            list_.add(effetEquipe_);
        }
        return list_;
    }

    static StringList forbiddenStatus(Fight _fight, DataBase _import) {
        StringList forbiddenStatus_ = new StringList();
        for(String c: FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAtt_=_import.getMove(c);
            int nbEffets_=fAtt_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAtt_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                forbiddenStatus_.addAllElts(effetGlobal_.getPreventStatus());
            }
        }
        return forbiddenStatus_;
    }

    static Rate rateCriticalHit(Fight _fight, TeamPosition _thrower,byte _boost,DataBase _import) {
        Rate rate_;
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.BOOST), Long.toString(_boost));
        String rateBoos_ = _import.getRateBoostCriticalHit();
        rate_ = _import.evaluatePositiveExp(rateBoos_, vars_, Rate.one());
        Fighter fighter_ = _fight.getFighter(_thrower);
        AbilityData fCapac_=fighter_.ficheCapaciteActuelle(_import);
        if (fCapac_ != null && !fCapac_.getMultEvtRateCh().isZero()) {
            rate_.multiplyBy(fCapac_.getMultEvtRateCh());
        }
        return rate_;
    }

    static Rate probaEffectStatistic(Fight _fight,TeamPosition _combattant,EffectStatistic _effet,
            boolean _begin,DataBase _import){
        return probaEffectStatistic(_fight, _combattant, _effet.getEvtRate(), _begin, _import);
    }

    static Rate probaEffectStatistic(Fight _fight,TeamPosition _combattant,Rate _rate,
            boolean _begin,DataBase _import){
        Rate proba_=new Rate(_rate);
        if (_begin) {
            return proba_;
        }
        Fighter creature_=_fight.getFighter(_combattant);
        AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
        if (fCapac_ != null && !fCapac_.getMultEvtRateSecEffectOwner().isZero()) {
            proba_.multiplyBy(fCapac_.getMultEvtRateSecEffectOwner());
        }
        proba_.multiplyBy(multProbaByComboOfMoves(_fight,_combattant.getTeam(), _import));
        return proba_;
    }

    static MonteCarloString probaEffectStatus(Fight _fight,TeamPosition _combattant,MonteCarloString _loi,DataBase _import){
        MonteCarloString retour_ =new MonteCarloString();
        Fighter creature_=_fight.getFighter(_combattant);
        LgInt somme_=_loi.sum();
        Rate sommePartielle_=Rate.zero();
        int nb_ = _loi.nbEvents();
        for (int i = 0; i < nb_; i++) {
            String e_ = _loi.getEvent(i);
            if(e_.isEmpty()){
                continue;
            }
            sommePartielle_.addNb(new Rate(_loi.getFreq(i)));
        }
//        for(String e:_loi.events()){
//            if(e.isEmpty()){
//                continue;
//            }
//            sommePartielle_.addNb(new Rate(_loi.rate(e)));
//        }
        Rate coeff_=DataBase.defRateProduct();
        AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
        if (fCapac_ != null && !fCapac_.getMultEvtRateSecEffectOwner().isZero()) {
            sommePartielle_.multiplyBy(fCapac_.getMultEvtRateSecEffectOwner());
            coeff_.multiplyBy(fCapac_.getMultEvtRateSecEffectOwner());
        }
        Rate rate_ = multProbaByComboOfMoves(_fight,_combattant.getTeam(), _import);
        sommePartielle_.multiplyBy(rate_);
        coeff_.multiplyBy(rate_);
        if(Rate.greaterEq(sommePartielle_,new Rate(somme_))){
            for (int i = 0; i < nb_; i++) {
                String e_ = _loi.getEvent(i);
                if(e_.isEmpty()){
                    continue;
                }
                retour_.addQuickEvent(e_,_loi.getFreq(i));
            }
//            for(String e:_loi.events()){
//                if(e.isEmpty()){
//                    continue;
//                }
//                retour_.addQuickEvent(e,_loi.rate(e));
//            }
            return retour_;
        }
        for (int i = 0; i < nb_; i++) {
            String e_ = _loi.getEvent(i);
            if(e_.isEmpty()){
                retour_.addQuickEvent(e_,Rate.minus(new Rate(somme_),sommePartielle_).intPart());
                continue;
            }
            retour_.addQuickEvent(e_,LgInt.multiply(_loi.getFreq(i),coeff_.intPart()));
        }
//        for(String e:_loi.events()){
//            if(e.isEmpty()){
//                retour_.addQuickEvent(e,Rate.minus(new Rate(somme_),sommePartielle_).intPart());
//                continue;
//            }
//            retour_.addQuickEvent(e,LgInt.multiply(_loi.rate(e),coeff_.intPart()));
//        }
        return retour_;
    }

    static Rate multProbaByComboOfMoves(Fight _fight,byte _noTeam, DataBase _import) {
        Team equipe_ = _fight.getTeams().getVal(_noTeam);
        Rate coeff_ = DataBase.defRateProduct();
        for(StringList c:equipe_.enabledTeamGroupMoves()){
            EffectCombo effet_=_import.getCombos().getEffects().getVal(c);
            if(effet_.getMultEvtRateSecEff().isZero()){
                continue;
            }
            coeff_.multiplyBy(effet_.getMultEvtRateSecEff());
        }
        return coeff_;
    }

    static boolean tirage(DataBase _db, Rate _probaActif){
        LgInt maxRd_ = _db.getMaxRd();
        return tr(MonteCarloUtil.booleanLaw(_probaActif).editNumber(maxRd_,_db.getGenerator()));
    }
    static Statistic random(DataBase _db, MonteCarloEnum<Statistic> _law) {
        LgInt maxRd_ = _db.getMaxRd();
        return _law.editNumber(maxRd_,_db.getGenerator());
    }

    static boolean random(DataBase _db, MonteCarloBoolean _law) {
        LgInt maxRd_ = _db.getMaxRd();
        return tr(_law.editNumber(maxRd_,_db.getGenerator()));
    }
    static boolean tr(BoolVal _b) {
        return _b == BoolVal.TRUE;
    }
    static String random(DataBase _db, MonteCarloString _law) {
        LgInt maxRd_ = _db.getMaxRd();
        if (_law.events().isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        return _law.editNumber(maxRd_,_db.getGenerator());
    }

    static Rate random(DataBase _db, MonteCarloNumber _law) {
        LgInt maxRd_ = _db.getMaxRd();
        return _law.editNumber(maxRd_,_db.getGenerator());
    }

    static boolean isBadSimulation(Fight _fight,IntMonteCarlo _law) {
        if (_law.nbEvents() != DataBase.ONE_POSSIBLE_CHOICE && _fight.getSimulation()) {
            _fight.setAcceptableChoices(false);
            return true;
        }
        return false;
    }
}
