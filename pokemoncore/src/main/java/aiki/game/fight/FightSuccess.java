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
import aiki.fight.util.TypesDuo;
import aiki.game.fight.util.NbEffectFighterCoords;
import aiki.game.fight.util.RandomBoolResults;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.AbMonteCarlo;
import code.maths.montecarlo.IntMonteCarlo;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloEnum;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.Numbers;
import code.util.SortableCustList;
import code.util.StringList;
import code.util.StringMap;

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
        } else if (_fight.isSending()) {
            if(isProtectedAgainstMove(_fight,_cible,_cible,_attaque,_import)){
                return new RandomBoolResults(false,true);
            }
        }
        Effect effet_=fAttaque_.getEffet(_noEffet);
        if (effet_.getTargetChoice() == fAttaque_.getTargetChoice()) {
            if (!successfulEffectWhileIfTargetIsNotThrower(
                    _fight,
                    _lanceur, _cible, _attaque,
                    _noEffet, _withPreviousEffect, _import)) {
                Fighter target_ = _fight.getFighter(_cible);
                if (_import.getMovesCountering().containsObj(target_.getFinalChosenMove())) {
                    return new RandomBoolResults(false,target_.getEnabledCounteringMoves().getVal(target_.getFinalChosenMove()).isEnabled());
                }
                return new RandomBoolResults(false,false);
            }
        } else {
            boolean sucessful_ = true;
            for (int e: effet_.getRequiredSuccessfulEffects()) {
                boolean atLeastOneSuccessful_ = false;
                NbEffectFighterCoords hit_ = new NbEffectFighterCoords(e, _cible);
                if (_fight.getSuccessfulEffects().contains(hit_)) {
                    atLeastOneSuccessful_ = _fight.getSuccessfulEffects().getVal(hit_);
                }
                if (!atLeastOneSuccessful_) {
                    sucessful_ = false;
                    break;
                }
            }
            if (!sucessful_ && _withPreviousEffect) {
                return new RandomBoolResults(false, false);
            }
        }
        String fail_;
        StringMap<String> values_ = new StringMap<String>();
        if (!_fight.isSending()) {
            fail_ = effet_.getFail();
        } else {
            EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effet_;
            fail_ = eff_.getFailSending();
        }
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
        if (!successEffect(_fight,_lanceur, _cible, effet_, _import)) {
            return new RandomBoolResults(false,false);
        }
        return new RandomBoolResults(true,false);
    }

    static boolean isProtectedAgainstMove(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,String _attaque,DataBase _import){
        MoveData move_ = _import.getMove(_attaque);
        if (move_.getTargetChoice() == TargetChoice.ALLIE) {
            return false;
        }
        if (move_.getTargetChoice() == TargetChoice.ALLIES) {
            return false;
        }
        StringList typeAttaque_=FightMoves.moveTypes(_fight,_lanceur,_attaque,_import);
        for (String t: typeAttaque_) {
            if(isProtectedAgainstMoveType(_fight,_lanceur,_cible,t,_import)){
                return true;
            }
        }
        for (TeamPosition t_: FightOrder.frontFighters(_fight)) {
            if (!Numbers.eq(t_.getTeam(), _cible.getTeam())) {
                continue;
            }
            Fighter f_ = _fight.getFighter(t_);
            if (!f_.capaciteActive()) {
                continue;
            }
            if (f_.ficheCapaciteActuelle(_import).getImmuAllyFromMoves().containsObj(_attaque)) {
                _fight.addProtectByAllyAbilityMessage(_cible, _attaque, f_.getCurrentAbility(), _import);
                return true;
            }
        }
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        Fighter creatureCbtCible_=_fight.getFighter(_cible);
        boolean cancelImmu_ = false;
        if(FightItems.canUseItsObject(_fight,_lanceur,_import)){
            Item fObjetLanceur_=creatureCbtLanceur_.ficheObjet(_import);
            if(fObjetLanceur_ instanceof ItemForBattle){
                ItemForBattle fObjetCombatLanceur_=(ItemForBattle)fObjetLanceur_;
                if(fObjetCombatLanceur_.getCancelImmuType()){
                    cancelImmu_=true;
                }
            }
        }
        if(Numbers.eq(_lanceur.getTeam(),_cible.getTeam())){
            if(creatureCbtCible_.capaciteActive()){
                AbilityData fCapaciteCible_=creatureCbtCible_.ficheCapaciteActuelle(_import);
                if(fCapaciteCible_.isImmuDamageAllyMoves() && move_ instanceof DamagingMoveData){
                    _fight.addProtectByAbilityDamageAllyMessage(_cible, _attaque, creatureCbtCible_.getCurrentAbility(), _import);
                    return true;
                }
            }
        }
        if (cancelImmu_) {
            return false;
        }
        Rate coeffEff_= DataBase.defRateProduct();
        for (String t: typeAttaque_) {
            coeffEff_.multiplyBy(rateEffAgainstTarget(_fight,_lanceur,_cible,t,_import));
        }
        boolean ignoreCapaciteCible_=FightAbilities.ignoreTargetAbility(_fight,_lanceur,_cible,_import);
        if(!ignoreCapaciteCible_){
            //la capacite de la cible l'immunise a l'attaque du lanceur
            AbilityData fCapaciteCible_=creatureCbtCible_.ficheCapaciteActuelle(_import);
            if(fCapaciteCible_.getImmuMove().containsObj(_attaque)){
                _fight.addProtectByAbilityMessage(_cible, _attaque, creatureCbtCible_.getCurrentAbility(), _import);
                return true;
            }
            if(coeffEff_.lowerOrEqualsOne()&&fCapaciteCible_.isImmuSufferedDamageLowEff()){
                _fight.addProtectByAbilityMessage(_cible, _attaque, creatureCbtCible_.getCurrentAbility(), _import);
                return true;
            }
        }
        if(FightItems.canUseItsBerry(_fight,_cible,_import)&&coeffEff_.greaterThanOne()){
            Berry baie_=(Berry)creatureCbtCible_.ficheObjet(_import);
            if(!baie_.getHealHpBySuperEffMove().isZero()){
                _fight.addProtectByItemMessage(_cible, _attaque, creatureCbtCible_.getItem(), _import);
                return true;
            }
        }
        if (FightItems.canUseItsObject(_fight, _cible, _import)) {
            Item it_ = creatureCbtCible_.ficheObjet(_import);
            if (it_ instanceof ItemForBattle) {
                ItemForBattle itDta_ = (ItemForBattle) it_;
                if (itDta_.getImmuMoves().containsObj(_attaque)) {
                    _fight.addProtectByItemMessage(_cible, _attaque, creatureCbtCible_.getItem(), _import);
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isProtectedAgainstMoveType(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,String _type,DataBase _import){
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        Fighter creatureCbtCible_=_fight.getFighter(_cible);
        boolean ignoreCapaciteCible_=FightAbilities.ignoreTargetAbility(_fight,_lanceur,_cible,_import);
        if(FightItems.canUseItsObject(_fight,_lanceur,_import)){
            Item fObjetLanceur_=creatureCbtLanceur_.ficheObjet(_import);
            if(fObjetLanceur_ instanceof ItemForBattle){
                ItemForBattle fObjetCombatLanceur_=(ItemForBattle)fObjetLanceur_;
                if(fObjetCombatLanceur_.getCancelImmuType()){
                    ignoreCapaciteCible_=true;
                }
            }
        }
        if(TeamPosition.eq(_lanceur,_cible)){
            ignoreCapaciteCible_=false;
        }
        boolean immuAnnuleeGlobal_=false;
        for(String c:FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            Rate coeff_=DataBase.defRateProduct();
            boolean noNullSum_ = false;
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                for(String e:creatureCbtCible_.getTypes()){
                    if (!effetGlobal_.getEfficiencyMoves().contains(new TypesDuo(_type,e))) {
                        continue;
                    }
                    Rate rate_ = effetGlobal_.getEfficiencyMoves().getVal(new TypesDuo(_type,e));
                    if (!rate_.isZero()) {
                        coeff_.multiplyBy(rate_);
                        noNullSum_ = true;
                    } else {
                        _fight.addProtectTypeByGlobalMoveMessage(_cible, _type, c, _import);
                        return true;
                    }
                }
            }
            if(noNullSum_){
                immuAnnuleeGlobal_=true;
                break;
            }
        }
        boolean immuTypeAttaque_=false;
        boolean protectedTypes_ = true;
        for(String e:creatureCbtCible_.getTypes()){
            for(String e2_:creatureCbtCible_.enabledIndividualAntiImmuMoves()){
                MoveData fAttaque_=_import.getMove(e2_);
                int nbEffets_=fAttaque_.nbEffets();
                for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                    Effect effet_=fAttaque_.getEffet(i);
                    if (!(effet_ instanceof EffectUnprotectFromTypes)) {
                        continue;
                    }
                    EffectUnprotectFromTypes effetAntiImmu_=(EffectUnprotectFromTypes)effet_;
                    if(effetAntiImmu_.getAttackTargetWithTypes().containsObj(_type)){
                        protectedTypes_ = false;
                    }
                    if(effetAntiImmu_.getTypes().containsObj(new TypesDuo(_type,e))){
                        protectedTypes_ = false;
                    }
                }
            }
        }
        if (creatureCbtCible_.getProtectedAgainstMoveTypes().containsObj(_type)) {
            immuTypeAttaque_=true;
        }
        if(immuAnnuleeGlobal_){
            return false;
        }
        if(immuTypeAttaque_){
            if (protectedTypes_) {
                _fight.addProtectTypeByIndividualMoveMessage(_cible, _type, _import);
            }
            return protectedTypes_;
        }
        StringList cancelledAbilities_ = new StringList();
        for(String c:FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                cancelledAbilities_.addAllElts(effetGlobal_.getCancelProtectingAbilities());
            }
        }
        cancelledAbilities_.removeDuplicates();
        if (cancelledAbilities_.containsObj(creatureCbtCible_.getCurrentAbility())) {
            return false;
        }
        if(!ignoreCapaciteCible_&&creatureCbtCible_.capaciteActive()){
            //la capacite de la cible l'immunise a l'attaque du lanceur
            AbilityData fCapaciteCible_=creatureCbtCible_.ficheCapaciteActuelle(_import);
            StringMap<StringList> immuAttaqueType_=fCapaciteCible_.getImmuMoveTypesByWeather();
            StringList climats_=FightMoves.enabledGlobalMoves(_fight,_import);
            for(String c: climats_){
                if(!immuAttaqueType_.contains(c)){
                    continue;
                }
                if(immuAttaqueType_.getVal(c).containsObj(_type)){
                    _fight.addProtectTypeByAbilityWeatherMessage(_cible, _type, creatureCbtCible_.getCurrentAbility(), c, _import);
                    return true;
                }
            }
            if(climats_.isEmpty()){
                if(immuAttaqueType_.contains(DataBase.EMPTY_STRING)){
                    if(immuAttaqueType_.getVal(DataBase.EMPTY_STRING).containsObj(_type)){
                        _fight.addProtectTypeByAbilityMessage(_cible, _type, creatureCbtCible_.getCurrentAbility(), _import);
                        return true;
                    }
                }
            }
            if(FightItems.canUseItsObject(_fight,_cible,_import)){
                Item fObjetCible_=creatureCbtCible_.ficheObjet(_import);
                if(fObjetCible_ instanceof ItemForBattle){
                    ItemForBattle fObjetCombatCible_=(ItemForBattle)fObjetCible_;
                    if(fObjetCombatCible_.getImmuTypes().containsObj(_type)){
                        _fight.addProtectTypeByItemMessage(_cible, _type, creatureCbtCible_.getItem(), _import);
                        return true;
                    }
                }
            }
        }
        return false;
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
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        Fighter creatureCbtCible_=_fight.getFighter(_cible);
        StringMap<Boolean> priseEnCompteCoeffNul_ = new StringMap<Boolean>();
        for(String e:creatureCbtCible_.getTypes()){
            priseEnCompteCoeffNul_.put(e,true);
        }
        StringMap<Rate> coeffEffGl_ = new StringMap<Rate>();
        for(String e:creatureCbtCible_.getTypes()){
            boolean continuer_=false;
            Rate coeff_=DataBase.defRateProduct();
            boolean noNullSum_ = false;
            for(String e2_:FightMoves.enabledGlobalMoves(_fight,_import)){
                MoveData fAtt_=_import.getMove(e2_);
                int nbEffets_=fAtt_.nbEffets();
                for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                    Effect effet_=fAtt_.getEffet(i);
                    if(!(effet_ instanceof EffectGlobal)){
                        continue;
                    }
                    EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                    if(!effetGlobal_.getEfficiencyMoves().contains(new TypesDuo(_typeOff,e))){
                        continue;
                    }
                    Rate rate_ = effetGlobal_.getEfficiencyMoves().getVal(new TypesDuo(_typeOff,e));
                    if (!rate_.isZero()) {
                        coeff_.multiplyBy(rate_);
                        noNullSum_ = true;
                    } else {
                        return Rate.zero();
                    }
                }
            }
            if(noNullSum_){
                priseEnCompteCoeffNul_.put(e,false);
                coeffEffGl_.put(e,coeff_);
            }
            for(String e2_:creatureCbtCible_.enabledIndividualAntiImmuMoves()){
                MoveData fAtt_=_import.getMove(e2_);
                int nbEffets_=fAtt_.nbEffets();
                for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                    Effect effet_=fAtt_.getEffet(i);
                    if(!(effet_ instanceof EffectUnprotectFromTypes)){
                        continue;
                    }
                    EffectUnprotectFromTypes effetAntiImmu_=(EffectUnprotectFromTypes)effet_;
                    if(effetAntiImmu_.getAttackTargetWithTypes().containsObj(_typeOff)){
                        priseEnCompteCoeffNul_.put(e,false);
                        continuer_=true;
                        break;
                    }
                    if(effetAntiImmu_.getTypes().containsObj(new TypesDuo(_typeOff,e))){
                        priseEnCompteCoeffNul_.put(e,false);
                        continuer_=true;
                        break;
                    }
                }
                if(continuer_){
                    break;
                }
            }
        }
        if(creatureCbtLanceur_.capaciteActive()){
            AbilityData fCapacite_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
            for(String e:creatureCbtCible_.getTypes()){
                if(fCapacite_.getBreakFoeImmune().containsObj(new TypesDuo(_typeOff,e))){
                    priseEnCompteCoeffNul_.put(e,false);
                }
            }
        }
        if(FightItems.canUseItsObject(_fight,_lanceur,_import)){
            Item fObjetLanceur_=creatureCbtLanceur_.ficheObjet(_import);
            if(fObjetLanceur_ instanceof ItemForBattle){
                ItemForBattle fObjetCombatLanceur_=(ItemForBattle)fObjetLanceur_;
                if(fObjetCombatLanceur_.getCancelImmuType()){
                    for(String e:creatureCbtCible_.getTypes()){
                        priseEnCompteCoeffNul_.put(e,false);
                    }
                }
            }
        }
        Rate coeff_=DataBase.defRateProduct();
        for(String e:creatureCbtCible_.getTypes()){
            Rate efficacite_=_import.getTableTypes().getVal(new TypesDuo(_typeOff,e));
            if(coeffEffGl_.contains(e)){
                coeff_.multiplyBy(coeffEffGl_.getVal(e));
            } else if(priseEnCompteCoeffNul_.getVal(e)||!efficacite_.isZero()){
                coeff_.multiplyBy(efficacite_);
            }
        }
        return coeff_;
    }

    static boolean canUseDirectlyMove(Fight _fight,TeamPosition _lanceur, DataBase _import) {
        Fighter creature_ = _fight.getFighter(_lanceur);
        String attaqueLanceur_=creature_.getFinalChosenMove();
        MoveData fAtt_=_import.getMove(attaqueLanceur_);
        short prepa_= fAtt_.getNbPrepaRound();
        if(prepa_>creature_.getNbPrepaRound()){
            if(!FightItems.canUseItsObject(_fight,_lanceur,_import)){
                return false;
            }
            Item objet_=creature_.ficheObjet(_import);
            if(!(objet_ instanceof ItemForBattle)){
                return false;
            }
            ItemForBattle objetAttachable_=(ItemForBattle)objet_;
            if(!objetAttachable_.getAttacksSoon()){
                return false;
            }
        }
        return true;
    }

    static boolean canSkipRecharge(Fight _fight,TeamPosition _lanceur, DataBase _import) {
        Fighter creature_ = _fight.getFighter(_lanceur);
        if(creature_.capaciteActive()){
            AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
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
        SortableCustList<Rate> rates_ = new SortableCustList<Rate>();
        rates_.add(precision_);
        rates_.add(Rate.zero());
//        rates_.sort(new NaturalComparator<Rate>() {
//            @Override
//            public int compare(Rate _o1, Rate _o2) {
//                return _o1.compareTo(_o2);
//            }
//        });
        rates_.sort();
        if (rates_.last().isZero()) {
            //en:case negative or zero
            //fr:cas negatif ou nul
            return Rate.zero();
        }

        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        Fighter creatureCbtCible_=_fight.getFighter(_cible);
        if(creatureCbtLanceur_.capaciteActive()){
            AbilityData fCapacite_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
            if(fCapacite_.isBreakProtection()){
                if(!protectedTargetAgainstMove(_fight,_lanceur,_cible, _attaque, _import)){
                    return DataBase.determinatedRate();
                }
            }
        }
        byte boost_=creatureCbtLanceur_.getStatisBoost().getVal(Statistic.ACCURACY);
        byte baseBoost_=(byte)_import.getDefaultBoost();
        boost_ += FightStatistic.bonusBoost(_fight, Statistic.ACCURACY, _lanceur, _import);
        if(boost_>=baseBoost_){
            precision_.multiplyBy(FightStatistic.rateBoost(boost_,_import));
        }else{
            if(!fAttaque_.getIgnVarAccurUserNeg()){
                precision_.multiplyBy(FightStatistic.rateBoost(boost_,_import));
            }
        }
        boost_=creatureCbtCible_.getStatisBoost().getVal(Statistic.EVASINESS);
        boost_ += FightStatistic.bonusBoost(_fight, Statistic.EVASINESS, _cible, _import);
        boolean ignorer_=true;
        Rate evasiness_ = Rate.one();
        if(!TeamPosition.eq(_lanceur,_cible)){
            if(boost_<=baseBoost_){
                evasiness_.multiplyBy(FightStatistic.rateBoost(boost_,_import));
                ignorer_=false;
            }else{
                if(!fAttaque_.getIgnVarEvasTargetPos()){
                    evasiness_.multiplyBy(FightStatistic.rateBoost(boost_,_import));
                    ignorer_=FightAbilities.ignoreTargetAbility(_fight, _lanceur,_cible,_import);
                }
            }
        }
        if(!ignorer_){
            evasiness_.multiplyBy(FightStatistic.statisticWithoutBase(_fight, _cible, Statistic.EVASINESS, FightValues.calculateValuesFighter(_fight, _cible, _import), _import));
            if(creatureCbtCible_.capaciteActive()){
                //PAUSE _d->val_non_affecte_poudre_claire().contains(_nom_att_l)
                AbilityData fCapacite_=creatureCbtCible_.ficheCapaciteActuelle(_import);
                String categ_ = fAttaque_.getCategory();
                if(fCapacite_.getMultStatIfCat().contains(new StatisticCategory(Statistic.EVASINESS,categ_))){
                    evasiness_.multiplyBy(fCapacite_.getMultStatIfCat().getVal(new StatisticCategory(Statistic.EVASINESS,categ_)));
                }
            }
        }
        if (evasiness_.isZero()) {
            return DataBase.determinatedRate();
        }
        if(creatureCbtLanceur_.capaciteActive()){
            AbilityData fCapacite_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
            String categ_ = fAttaque_.getCategory();
            if(fCapacite_.getMultStatIfCat().contains(new StatisticCategory(Statistic.ACCURACY,categ_))){
                precision_.multiplyBy(fCapacite_.getMultStatIfCat().getVal(new StatisticCategory(Statistic.ACCURACY,categ_)));
            }
        }
        precision_.multiplyBy(FightStatistic.statisticWithoutBase(_fight, _lanceur, Statistic.ACCURACY, FightValues.calculateValuesFighter(_fight, _lanceur, _import), _import));
        for(String c: FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttGl_=_import.getMove(c);
            int nbEffets_=fAttGl_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
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

    static boolean successfulEffectWhileIfTargetIsNotThrower(
            Fight _fight,
            TeamPosition _lanceur,TeamPosition _cible,
            String _attaque,int _noEffet,boolean _withPreviousEffect,DataBase _import) {
        MoveData fAttaque_=_import.getMove(_attaque);
        Effect effet_=fAttaque_.getEffet(_noEffet);
        boolean primaire_= _noEffet == fAttaque_.indexOfPrimaryEffect();
        boolean reussi_=true;
        if (_withPreviousEffect) {
            for(int e:effet_.getRequiredSuccessfulEffects()){
                if (!_fight.getSuccessfulEffects().contains(new NbEffectFighterCoords(e,_cible))) {
                    continue;
                }
                if(!_fight.getSuccessfulEffects().getVal(new NbEffectFighterCoords(e,_cible))){
                    reussi_=false;
                    break;
                }
            }
        }
        if(!reussi_){
            return false;
        }
        if (fAttaque_.getTargetChoice() == TargetChoice.ALLIE) {
            return true;
        }
        if (fAttaque_.getTargetChoice() == TargetChoice.ALLIES) {
            return true;
        }
        Fighter creatureCbtCible_=_fight.getFighter(_cible);
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        boolean touchePseudoInvulnerable_=false;
        boolean ignoreCapaciteCible_=FightAbilities.ignoreTargetAbility(_fight, _lanceur,_cible,_import);
        if(creatureCbtLanceur_.capaciteActive()){
            AbilityData fCapaciteLanceur_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
            if(fCapaciteLanceur_.isAchievedDisappearedPk()){
                touchePseudoInvulnerable_=true;
            }
        }
        if(creatureCbtCible_.capaciteActive() && !ignoreCapaciteCible_){
            AbilityData ficheCapacite_=creatureCbtCible_.ficheCapaciteActuelle(_import);
            if(!TeamPosition.eq(_lanceur,_cible)){
                if(ficheCapacite_.isCancelSecEffectOther()&&!primaire_){
                    _fight.addProtectedAgainstSecEffMessage(_cible, _attaque, creatureCbtCible_.getCurrentAbility(), _import);
                    return false;
                }
            }
            if(TeamPosition.eq(_lanceur,_cible)){
                if(ficheCapacite_.isCancelSecEffectOwner()&&!primaire_){
                    _fight.addProtectedAgainstSecEffMessage(_cible, _attaque, creatureCbtCible_.getCurrentAbility(), _import);
                    return false;
                }
            }
        }
        boolean precisionMaxCible_=false;
        for(MoveTeamPosition c:creatureCbtLanceur_.getIncrUserAccuracy().getKeys()){
            if(!TeamPosition.eq(c.getTeamPosition(),_cible)){
                continue;
            }
            if(!creatureCbtLanceur_.getIncrUserAccuracy().getVal(c)){
                continue;
            }
            precisionMaxCible_=true;
            break;
        }
        boolean touchePrepaTourGl_=false;
        StringList typeAttaqueLanceur_=FightMoves.moveTypes(_fight,_lanceur,_attaque,_import);
        for(String c:FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttGlobal_=_import.getMove(c);
            int nbEffets_=fAttGlobal_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                Effect effetLoc_=fAttGlobal_.getEffet(i);
                if(!(effetLoc_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effetLoc_;
                for (String t: typeAttaqueLanceur_) {
                    if(effetGlobal_.getMultDamagePrepaRound().contains(t)){
                        if(effetGlobal_.getMovesUsedByTargetedFighters().containsObj(creatureCbtCible_.getFinalChosenMove())){
                            if(creatureCbtCible_.getNbPrepaRound()>0){
                                touchePrepaTourGl_=true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if(creatureCbtCible_.isDisappeared()&&!touchePseudoInvulnerable_&&!touchePrepaTourGl_){
            if(!precisionMaxCible_&&!fAttaque_.getAchieveDisappearedPkUsingMove().containsObj(creatureCbtCible_.getFinalChosenMove())){
                _fight.addProtectedByDisappearingMessage(_cible, _import);
                return false;
            }
        }
        boolean passeAbri_=false;
        if(creatureCbtLanceur_.capaciteActive()){
            AbilityData fCapaciteLanceur_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
            if(fCapaciteLanceur_.isBreakProtection()){
                passeAbri_=!Numbers.eq(_lanceur.getTeam(), _cible.getTeam());
            }
        }
        if (!passeAbri_) {
            if (protectedTargetAgainstMove(_fight, _lanceur,_cible, _attaque, _import)) {
                return false;
            }
        }
        return true;
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
            if (moves_.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    static EnumList<Statistic> successfulChangedStatistics(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectStatistic _effet,DataBase _import){
        EnumList<Statistic> statistiques_ = successfulChangedBoostedStatistics(_fight,_lanceur, _cible, _effet, _import);
        EnumMap<Statistic,Byte> varStatisCran_=_effet.getStatisVarRank();
        if(!varStatisCran_.isEmpty()){
            if (statistiques_.isEmpty()) {
                return statistiques_;
            }
            EnumMap<Statistic,String> raisonsEchec_=_effet.getLocalFailStatis();
            EnumList<Statistic> statistiquesVariant_=new EnumList<Statistic>();
            for(Statistic c:statistiques_){
                if(!raisonsEchec_.contains(c)){
                    statistiquesVariant_.add(c);
                    continue;
                }
                StringMap<String> values_=FightValues.calculateValues(_fight,_lanceur,_cible,_import);
                if (!_import.evaluateBoolean(raisonsEchec_.getVal(c), values_, false)) {
                    statistiquesVariant_.add(c);
                }
            }
            return statistiquesVariant_;
        }
        if(!_effet.getCopyBoost().isEmpty()){
            EnumList<Statistic> copieBoost_= _effet.getCopyBoost();
            Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
            Fighter creatureCbtCible_=_fight.getFighter(_cible);
            EnumList<Statistic> statistiquesCopiees_= new EnumList<Statistic>();
            for(Statistic c:copieBoost_){
                byte boostLanceur_=creatureCbtLanceur_.getStatisBoost().getVal(c);
                byte boostCible_=creatureCbtCible_.getStatisBoost().getVal(c);
                if(successChangedStatistic(_fight,_lanceur,_lanceur,c,(byte) (boostCible_-boostLanceur_),_import)){
                    statistiquesCopiees_.add(c);
                }
            }
            return statistiquesCopiees_;
        }
        if(!_effet.getCancelLowStat().isEmpty()){
            EnumList<Statistic> annuleBaisse_= _effet.getCancelLowStat();
            Fighter creatureCbtCible_=_fight.getFighter(_cible);
            EnumList<Statistic> statistiquesBaisseAnnulees_=new EnumList<Statistic>();
            for(Statistic c:annuleBaisse_){
                byte boostCible_=creatureCbtCible_.getStatisBoost().getVal(c);
                if(boostCible_<_import.getDefaultBoost()){
                    statistiquesBaisseAnnulees_.add(c);
                }
            }
            return statistiquesBaisseAnnulees_;
        }
        EnumList<Statistic> changedStatis_ = new EnumList<Statistic>();
        if(!_effet.getCancelChgtStat().isEmpty()){
            changedStatis_.addAllElts(_effet.getCancelChgtStat());
        }
        EnumList<Statistic> echangeStatis_=_effet.getSwapBoostStatis();
        if(!echangeStatis_.isEmpty()){
            EnumMap<Statistic,String> raisonsEchec_=_effet.getLocalFailSwapBoostStatis();
            Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
            Fighter creatureCbtCible_=_fight.getFighter(_cible);
            //CustList<Statistic> statistiquesEchangees_=new CustList<>();
            for(Statistic c:echangeStatis_){
                byte boostLanceur_=creatureCbtLanceur_.getStatisBoost().getVal(c);
                byte boostCible_=creatureCbtCible_.getStatisBoost().getVal(c);
                boolean passerIteration_=false;
                if(!successChangedStatistic(_fight,_lanceur,_cible,c,(byte) (boostLanceur_-boostCible_),_import)){
                    passerIteration_=true;
                }
                if(!successChangedStatistic(_fight,_cible,_lanceur,c,(byte) (boostCible_-boostLanceur_),_import)){
                    passerIteration_=true;
                }
                if(passerIteration_){
                    continue;
                }
                if(!raisonsEchec_.contains(c)){
                    changedStatis_.add(c);
                    continue;
                }
                StringMap<String> values_=FightValues.calculateValues(_fight,_lanceur,_cible,_import);
                if (!_import.evaluateBoolean(raisonsEchec_.getVal(c), values_, false)) {
                    changedStatis_.add(c);
                }
            }
        }
        return changedStatis_;
    }

    static EnumList<Statistic> successfulChangedBoostedStatistics(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,EffectStatistic _effet,DataBase _import) {
        EnumList<Statistic> statistiques_=new EnumList<Statistic>();
        EnumMap<Statistic,Byte> varStatisCran_=_effet.getStatisVarRank();
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
        if(creatureCbtLanceur_.capaciteActive()){
            AbilityData fCapaciteLanceur_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
            if(!Numbers.eq(_lanceur.getTeam(),_cible.getTeam())){
                protectionsIgnorees_.addAllElts(fCapaciteLanceur_.getIgnFoeTeamMove());
            }
        }
        return successChangedStatisticProtect(_fight,_cible,_statistique,_variation,ignoreCapaciteCible_,protectionsIgnorees_,_import);
    }

    static boolean successChangedStatisticProtect(Fight _fight,TeamPosition _combattant,Statistic _statistique,byte _variation,boolean _ignoreCapacite,StringList _protectionsIgnorees,DataBase _import){
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        boolean immuByType_ = false;
        StringList immuneTypes_ = new StringList();
        String abAlly_ = DataBase.EMPTY_STRING;
        for (byte f_: equipe_.frontFighters()) {
            Fighter part_ = _fight.getFighter(new TeamPosition(_combattant.getTeam(), f_));
            if (!part_.capaciteActive()) {
                continue;
            }
            abAlly_ = part_.getCurrentAbility();
            AbilityData ab_ = part_.ficheCapaciteActuelle(_import);
            for (String t: _import.getTypes()) {
                if (!ab_.getImmuLowStatisTypes().contains(t)) {
                    continue;
                }
                if (ab_.getImmuLowStatisTypes().getVal(t).containsObj(_statistique)) {
                    immuneTypes_.add(t);
                }
            }
        }
        immuneTypes_.removeDuplicates();
        Fighter creatureCbt_=_fight.getFighter(_combattant);
        for (String t: immuneTypes_) {
            if (creatureCbt_.getTypes().containsObj(t)) {
                immuByType_ = true;
            }
        }
        if (immuByType_ && _variation < 0) {
            _fight.addImmuLowStatAbilityAllyMessage(_combattant, _statistique, abAlly_, _import);
            return false;
        }
        byte boost_=creatureCbt_.getStatisBoost().getVal(_statistique);
        byte maxBoost_=(byte)_import.getMaxBoost();
        byte minBoost_=(byte)_import.getMinBoost();
        for(String c:equipe_.enabledTeamMoves()){
            if(_protectionsIgnorees.containsObj(c)){
                continue;
            }
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectTeam)){
                    continue;
                }
                EffectTeam effetEquipe_=(EffectTeam)effet_;
                if(effetEquipe_.getProtectAgainstLowStat().containsObj(_statistique)&&_variation<0){
                    _fight.addImmuLowStatTeamMessage(_combattant, _statistique, c, _import);
                    return false;
                }
            }
        }
        if(!_ignoreCapacite&&creatureCbt_.capaciteActive()){
            if(_variation<0){
                AbilityData fCapac_=creatureCbt_.ficheCapaciteActuelle(_import);
                if(fCapac_.getImmuLowStat().containsObj(_statistique)){
                    _fight.addImmuLowStatAbilityMessage(_combattant, _statistique, creatureCbt_.getCurrentAbility(), _import);
                    return false;
                }
                for(String c:creatureCbt_.getStatusSet()){
                    if(Numbers.eq(creatureCbt_.getStatusNbRoundShort(c), 0)){
                        continue;
                    }
                    if(fCapac_.getImmuLowStatIfStatus().containsObj(new StatisticStatus(_statistique,c))){
                        _fight.addImmuLowStatStAbilityMessage(_combattant, _statistique, creatureCbt_.getCurrentAbility(), c, _import);
                        return false;
                    }
                }
            }
        }
        if(FightItems.canUseItsObject(_fight,_combattant,_import)){
            Item objet_=creatureCbt_.ficheObjet(_import);
            if(objet_ instanceof ItemForBattle){
                ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                if(objetAttachable_.getImmuLowStatis()){
                    if (_variation < 0) {
                        _fight.addImmuLowStatItemMessage(_combattant, _statistique, creatureCbt_.getItem(), _import);
                        return false;
                    }
                }
            }
        }
        if(_variation>0){
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

    static boolean protectedTargetAgainstMove(Fight _fight, TeamPosition _user, TeamPosition _target, String _move, DataBase _import) {
        Team equipeCible_=_fight.getTeams().getVal(_target.getTeam());
        Fighter creatureCbtCible_=_fight.getFighter(_target);
        MoveData fAttaque_=_import.getMove(_move);
        if (_import.getMovesCountering().containsObj(creatureCbtCible_.getFinalChosenMove())) {
            String move_ = creatureCbtCible_.getFinalChosenMove();
            if (creatureCbtCible_.getEnabledCounteringMoves().getVal(move_).isEnabled()) {
                if (!sufferingDamageTypes(_fight, _user, _target, _move, _import).isEmpty()) {
                    _fight.addProtectedByIndividualMoveMessage(_target, _move, move_, _import);
                    return true;
                }
                if (droppedStatis(_fight, _user, _target, _move, true, _import)) {
                    _fight.addProtectedByIndividualMoveMessage(_target, _move, move_, _import);
                    return true;
                }
                if (sufferingDirectMoves(_fight, _user, _target, _move, true, _import)) {
                    _fight.addProtectedByIndividualMoveMessage(_target, _move, move_, _import);
                    return true;
                }
            }
        }
        if (fAttaque_ instanceof DamagingMoveData) {
            for(byte c:equipeCible_.getMembers().getKeys()){
                Fighter creatureCbtMembre_=equipeCible_.getMembers().getVal(c);
                if(creatureCbtMembre_.isSuccessfulMove()){
                    String move_ = creatureCbtMembre_.getFinalChosenMove();
                    if(_import.getMovesProtAgainstDamageMoves().containsObj(move_)){
                        _fight.addProtectedByTeamMoveMessage(_target, _move, move_, _import);
                        return true;
                    }
                }
            }
        }
        if (fAttaque_ instanceof StatusMoveData) {
            for(byte c:equipeCible_.getMembers().getKeys()){
                Fighter creatureCbtMembre_=equipeCible_.getMembers().getVal(c);
                if(creatureCbtMembre_.isSuccessfulMove()){
                    String move_ = creatureCbtMembre_.getFinalChosenMove();
                    if(_import.getMovesProtAgainstStatusMoves().containsObj(move_)){
                        _fight.addProtectedByTeamMoveMessage(_target, _move, move_, _import);
                        return true;
                    }
                }
            }
        }
        if(fAttaque_.getStoppableMoveSolo()&&creatureCbtCible_.isSuccessfulMove()){
            String move_ = creatureCbtCible_.getFinalChosenMove();
            if(_import.getMovesProtSingleTarget().containsObj(move_)){
                _fight.addProtectedByIndividualMoveMessage(_target, _move, move_, _import);
                return true;
            }
        }
        if(fAttaque_.getStoppableMoveMulti()){
            for(byte c:equipeCible_.getMembers().getKeys()){
                Fighter creatureCbtMembre_=equipeCible_.getMembers().getVal(c);
                if(creatureCbtMembre_.isSuccessfulMove()){
                    String move_ = creatureCbtMembre_.getFinalChosenMove();
                    if(_import.getMovesProtAgainstMultiTarget().containsObj(move_)){
                        _fight.addProtectedByTeamMoveMessage(_target, _move, move_, _import);
                        return true;
                    }
                }
            }
        }
        if(fAttaque_.getStoppableMovePrio()&&fAttaque_.getPriority()>0){
            for(byte c:equipeCible_.getMembers().getKeys()){
                Fighter creatureCbtMembre_=equipeCible_.getMembers().getVal(c);
                if(creatureCbtMembre_.isSuccessfulMove()){
                    String move_ = creatureCbtMembre_.getFinalChosenMove();
                    if(_import.getMovesProtAgainstPrio().containsObj(move_)){
                        _fight.addProtectedByTeamMoveMessage(_target, _move, move_, _import);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static StringMap<Rate> sufferingDamageTypes(Fight _fight, TeamPosition _user, TeamPosition _target, String _move, DataBase _import) {
        Fighter creatureCbtCible_=_fight.getFighter(_target);
        MoveData counteringMove_ = _import.getMove(creatureCbtCible_.getFinalChosenMove());
        StringMap<Rate> sufferd_ = new StringMap<Rate>();
        int nbEffects_ = counteringMove_.nbEffets();
        for (int i = CustList.FIRST_INDEX; i < nbEffects_; i++) {
            Effect eff_ = counteringMove_.getEffet(i);
            if (!(eff_ instanceof EffectCounterAttack)) {
                continue;
            }
            EffectCounterAttack effectLoc_ = (EffectCounterAttack) eff_;
            StringMap<String> values_ = new StringMap<String>();
            values_.putAllMap(FightValues.calculateValues(_fight,_user,_target,_import));
            values_.putAllMap(FightValues.calculateBasicBooleanValues(_fight,_user, _target, _import));
            if (_import.evaluateBoolean(effectLoc_.getProtectFail(), values_, false)) {
                continue;
            }
            for (String t: FightMoves.moveTypes(_fight, _user, _move, _import)) {
                if (effectLoc_.getSufferingDamageTypes().contains(t)) {
                    sufferd_.put(t, effectLoc_.getSufferingDamageTypes().getVal(t));
                }
            }
        }
        return sufferd_;
    }

    static boolean droppedStatis(Fight _fight, TeamPosition _user, TeamPosition _target, String _move, boolean _protect,DataBase _import) {
        Fighter creatureCbtCible_=_fight.getFighter(_target);
        MoveData fAttaque_=_import.getMove(_move);
        MoveData counteringMove_ = _import.getMove(creatureCbtCible_.getFinalChosenMove());
        if (!(fAttaque_ instanceof DamagingMoveData)) {
            return false;
        }
        int nbEffects_ = counteringMove_.nbEffets();
        boolean success_ = false;
        for (int i = CustList.FIRST_INDEX; i < nbEffects_; i++) {
            Effect eff_ = counteringMove_.getEffet(i);
            if (!(eff_ instanceof EffectCounterAttack)) {
                continue;
            }
            EffectCounterAttack effectLoc_ = (EffectCounterAttack) eff_;
            if (effectLoc_.getDroppedStatDirectMove().isEmpty()) {
                continue;
            }
            StringMap<String> values_ = new StringMap<String>();
            values_.putAllMap(FightValues.calculateValues(_fight,_user,_target,_import));
            values_.putAllMap(FightValues.calculateBasicBooleanValues(_fight,_user, _target, _import));
            if (_import.evaluateBoolean(effectLoc_.getProtectFail(), values_, false)) {
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

    static boolean sufferingDirectMoves(Fight _fight, TeamPosition _user, TeamPosition _target, String _move, boolean _protect, DataBase _import) {
        Fighter creatureCbtCible_=_fight.getFighter(_target);
        MoveData fAttaque_=_import.getMove(_move);
        MoveData counteringMove_ = _import.getMove(creatureCbtCible_.getFinalChosenMove());
        if (!(fAttaque_ instanceof DamagingMoveData)) {
            return false;
        }
        int nbEffects_ = counteringMove_.nbEffets();
        boolean success_ = false;
        for (int i = CustList.FIRST_INDEX; i < nbEffects_; i++) {
            Effect eff_ = counteringMove_.getEffet(i);
            if (!(eff_ instanceof EffectCounterAttack)) {
                continue;
            }
            EffectCounterAttack effectLoc_ = (EffectCounterAttack) eff_;
            if (effectLoc_.getSufferingDamageDirectMove().isZero()) {
                continue;
            }
            StringMap<String> values_ = new StringMap<String>();
            values_.putAllMap(FightValues.calculateValues(_fight,_user,_target,_import));
            values_.putAllMap(FightValues.calculateBasicBooleanValues(_fight,_user, _target, _import));
            if (_import.evaluateBoolean(effectLoc_.getProtectFail(), values_, false)) {
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

    static boolean successfulEffectStatus(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,EffectStatus _effet,DataBase _import){
        if(_effet.getStatusFromUser()){
            StringList statutsTranferes_=new StringList();
            Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
            for(String c:creatureCbtLanceur_.getStatusSet()){
                if(Numbers.eq(creatureCbtLanceur_.getStatusNbRoundShort(c), 0)){
                    continue;
                }
                if(successfulAffectedStatus(_fight,_lanceur,_cible,c,_import)){
                    statutsTranferes_.add(c);
                }
            }
            return !statutsTranferes_.isEmpty();
        }
        if(!_effet.getDeletedStatus().isEmpty()){
            return true;
        }
        if(_effet.getKoUserHealSubst()){
            return true;
        }
        MonteCarloString loiStatuts_=_effet.getLawStatus();
        StringMap<String> echecStatuts_=_effet.getLocalFailStatus();
        for(String c:loiStatuts_.events()){
            if(StringList.quickEq(c,DataBase.EMPTY_STRING)){
                continue;
            }
            if (echecStatuts_.contains(c) && !echecStatuts_.getVal(c).isEmpty()){
                StringMap<String> values_=FightValues.calculateValues(_fight,_lanceur,_cible,_import);
                if (_import.evaluateBoolean(echecStatuts_.getVal(c), values_, false)) {
                    continue;
                }
            }
            if(successfulAffectedStatus(_fight,_lanceur,_cible,c,_import)){
                return true;
            }
        }
        return false;
    }

    static boolean successfulAffectedStatus(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,String _statut,DataBase _import){
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        Status statut_=_import.getStatus().getVal(_statut);
        if(statut_.estActifPartenaire()){
            EffectPartnerStatus effet_=statut_.getEffectsPartner().first();
            if(effet_.getWeddingAlly()){
                if(Numbers.eq(_lanceur.getTeam(), _cible.getTeam())){
                    return true;
                }
            }
        }
        boolean ignoreCapaciteCible_=FightAbilities.ignoreTargetAbility(_fight,_lanceur,_cible,_import);
        StringList protectionsIgnorees_=new StringList();
        if(creatureCbtLanceur_.capaciteActive()){
            AbilityData fCapaciteLanceur_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
            if(!Numbers.eq(_lanceur.getTeam(),_cible.getTeam())){
                protectionsIgnorees_.addAllElts(fCapaciteLanceur_.getIgnFoeTeamMove());
            }
        }
        return successfulAffectedStatusProtect(_fight,_cible,_statut,ignoreCapaciteCible_,protectionsIgnorees_,_import);
    }

    static boolean successfulAffectedStatusProtect(Fight _fight,TeamPosition _combattant,String _statut,boolean _ignoreCapacite,StringList _protectionsIgnorees,DataBase _import){
        if (forbiddenStatus(_fight,_import).containsObj(_statut)) {
            _fight.addImmuStatGlobalMoveMessage(_combattant, _statut, _import);
            return false;
        }
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        String abAll_ = DataBase.EMPTY_STRING;
        boolean immuByType_ = false;
        StringList immuneTypes_ = new StringList();
        for (byte f_: equipe_.frontFighters()) {
            Fighter part_ = _fight.getFighter(new TeamPosition(_combattant.getTeam(), f_));
            if (!part_.capaciteActive()) {
                continue;
            }
            AbilityData ab_ = part_.ficheCapaciteActuelle(_import);
            abAll_ = part_.getCurrentAbility();
            for (String t: _import.getTypes()) {
                if (!ab_.getImmuStatusTypes().contains(t)) {
                    continue;
                }
                if (ab_.getImmuStatusTypes().getVal(t).containsObj(_statut)) {
                    immuneTypes_.add(t);
                }
            }
        }
        immuneTypes_.removeDuplicates();
        Fighter creatureCbt_=_fight.getFighter(_combattant);
        for (String t: immuneTypes_) {
            if (creatureCbt_.getTypes().containsObj(t)) {
                immuByType_ = true;
                break;
            }
        }
        if (immuByType_) {
            _fight.addImmuStatAbilityAllyMessage(_combattant, _statut, abAll_, _import);
            return false;
        }
        for(String c: equipe_.enabledTeamMoves()){
            if(_protectionsIgnorees.containsObj(c)){
                continue;
            }
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectTeam)){
                    continue;
                }
                EffectTeam effetEquipe_=(EffectTeam)effet_;
                if(effetEquipe_.getProtectAgainstStatus().containsObj(_statut)){
                    _fight.addImmuStatTeamMessage(_combattant, _statut, c, _import);
                    return false;
                }
            }
        }
        if(!_ignoreCapacite&&creatureCbt_.capaciteActive()){
            AbilityData fCapac_=creatureCbt_.ficheCapaciteActuelle(_import);
            StringList globalMoves_ = FightMoves.enabledGlobalMoves(_fight,_import);
            for(String c: globalMoves_){
                if (!fCapac_.getImmuStatus().contains(c)) {
                    continue;
                }
                if(fCapac_.getImmuStatus().getVal(c).containsObj(_statut)){
                    _fight.addImmuStatGlobalMoveAbilityMessage(_combattant, _statut, creatureCbt_.getCurrentAbility(), c, _import);
                    return false;
                }
            }
            if (fCapac_.getImmuStatus().contains(DataBase.EMPTY_STRING)) {
                if (globalMoves_.isEmpty()) {
                    if (fCapac_.getImmuStatus().getVal(DataBase.EMPTY_STRING).containsObj(_statut)) {
                        _fight.addImmuStatAbilityMessage(_combattant, _statut, creatureCbt_.getCurrentAbility(), _import);
                        return false;
                    }
                }
            }
        }
        if(FightItems.canUseItsObject(_fight,_combattant,_import)){
            Item objet_=creatureCbt_.ficheObjet(_import);
            if(objet_ instanceof ItemForBattle){
                ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                if(objetAttachable_.getImmuStatus().containsObj(_statut)){
                    _fight.addImmuStatItemMessage(_combattant, _statut, creatureCbt_.getItem(), _import);
                    return false;
                }
            }
        }
        return true;
    }

    static StringList forbiddenStatus(Fight _fight, DataBase _import) {
        StringList forbiddenStatus_ = new StringList();
        for(String c: FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAtt_=_import.getMove(c);
            int nbEffets_=fAtt_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
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
        vars_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.BOOST), Byte.toString(_boost));
        String rateBoos_ = _import.getRateBoostCriticalHit();
        rate_ = _import.evaluatePositiveExp(rateBoos_, vars_, Rate.one());
        Fighter fighter_ = _fight.getFighter(_thrower);
        if(fighter_.capaciteActive()){
            AbilityData fCapac_=fighter_.ficheCapaciteActuelle(_import);
            if(!fCapac_.getMultEvtRateCh().isZero()){
                rate_.multiplyBy(fCapac_.getMultEvtRateCh());
            }
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
        if(creature_.capaciteActive()){
            AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
            if(!fCapac_.getMultEvtRateSecEffectOwner().isZero()){
                proba_.multiplyBy(fCapac_.getMultEvtRateSecEffectOwner());
            }
        }
        proba_.multiplyBy(multProbaByComboOfMoves(_fight,_combattant.getTeam(), _import));
        return proba_;
    }

    static MonteCarloString probaEffectStatus(Fight _fight,TeamPosition _combattant,AbMonteCarlo<String> _loi,DataBase _import){
        MonteCarloString retour_ =new MonteCarloString();
        Fighter creature_=_fight.getFighter(_combattant);
        LgInt somme_=_loi.sum();
        Rate sommePartielle_=Rate.zero();
        for(String e:_loi.events()){
            if(e.isEmpty()){
                continue;
            }
            sommePartielle_.addNb(new Rate(_loi.rate(e)));
        }
        Rate coeff_=DataBase.defRateProduct();
        if(creature_.capaciteActive()){
            AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
            if(!fCapac_.getMultEvtRateSecEffectOwner().isZero()){
                sommePartielle_.multiplyBy(fCapac_.getMultEvtRateSecEffectOwner());
                coeff_.multiplyBy(fCapac_.getMultEvtRateSecEffectOwner());
            }
        }
        Rate rate_ = multProbaByComboOfMoves(_fight,_combattant.getTeam(), _import);
        sommePartielle_.multiplyBy(rate_);
        coeff_.multiplyBy(rate_);
        if(Rate.greaterEq(sommePartielle_,new Rate(somme_))){
            for(String e:_loi.events()){
                if(e.isEmpty()){
                    continue;
                }
                retour_.addEvent(e,_loi.rate(e));
            }
            return retour_;
        }
        for(String e:_loi.events()){
            if(e.isEmpty()){
                retour_.addEvent(e,Rate.minus(new Rate(somme_),sommePartielle_).intPart());
                continue;
            }
            retour_.addEvent(e,LgInt.multiply(_loi.rate(e),coeff_.intPart()));
        }
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
        return AbMonteCarlo.booleanLaw(_probaActif).editNumber(maxRd_);
    }
    static Statistic random(DataBase _db, MonteCarloEnum<Statistic> _law) {
        LgInt maxRd_ = _db.getMaxRd();
        return _law.editNumber(maxRd_);
    }

    static boolean random(DataBase _db, MonteCarloBoolean _law) {
        LgInt maxRd_ = _db.getMaxRd();
        return _law.editNumber(maxRd_);
    }
    static String random(DataBase _db, MonteCarloString _law) {
        LgInt maxRd_ = _db.getMaxRd();
        return _law.editNumber(maxRd_);
    }

    static Rate random(DataBase _db, MonteCarloNumber _law) {
        LgInt maxRd_ = _db.getMaxRd();
        return _law.editNumber(maxRd_);
    }

    static boolean isBadSimulation(Fight _fight,IntMonteCarlo _law) {
        if(_law.nbEvents() != DataBase.ONE_POSSIBLE_CHOICE){
            if (_fight.getSimulation()) {
                _fight.setAcceptableChoices(false);
                return true;
            }
        }
        return false;
    }
}
