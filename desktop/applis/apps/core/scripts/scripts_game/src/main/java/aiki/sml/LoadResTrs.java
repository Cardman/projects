package aiki.sml;

import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.moves.enums.*;
import code.maths.*;
import code.sml.util.*;
import code.util.*;
import aiki.facade.enums.*;
import code.util.core.*;
import aiki.sml.trs.*;
import aiki.map.levels.enums.*;
import aiki.game.params.enums.*;
import aiki.map.pokemon.enums.*;

public final class LoadResTrs {

    private static final char TAB_CHAR = '\t';

    private LoadResTrs(){}
    public static void loadResources(DataBase _d, StringMap<TranslationsAppli> _defParts) {
        for (EntryCust<String,String> c: Cst.cs().entryList()) {
            short cle_ = (short) NumberUtil.parseInt(c.getKey());
            _d.getHm().addEntry(cle_, c.getValue());
        }
        for (EntryCust<String,String> c: Cst.ct().entryList()) {
            StringList infos_ = StringUtil.splitChars(c.getValue(), TAB_CHAR);
            short cle_ = (short) NumberUtil.parseInt(c.getKey());
            _d.getTm().addEntry(cle_, infos_.first().trim());
            LgInt price_ = new LgInt(infos_.last().trim());
            _d.getTmPrice().addEntry(cle_, price_);
        }
        _d.setConstNum(Cst.cn());
        for (EntryCust<String, String> l : Cst.cnn().entryList()) {
            String key_ = l.getKey();
            String value_ = l.getValue();
            _d.initValue(key_, value_);
        }
        _d.setTableTypes(Cst.eff());
        _d.setTypes(Cst.tp());
        _d.setLawsDamageRate(Cst.lg());
        _d.setExpGrowth(Cst.ex());
        _d.setRates(Cst.di());
        _d.setTypesColors(Cst.tc());
        _d.initTranslations();
        for (EntryCust<String, TranslationsAppli> e : _defParts.entryList()) {
            String l = e.getKey();
            StringMap<TranslationsFile> files_ = e.getValue().getMapping();
            IdMap<Gender, String> genders_ = trGenders(files_.getVal(MessagesTrs.TRANSLATION_GENDERS));
            _d.getTranslatedGenders().addEntry(l, genders_);
            IdMap<SelectedBoolean, String> booleans_ = trBooleans(files_.getVal(MessagesTrs.TRANSLATION_BOOLEANS));
            _d.getTranslatedBooleans().addEntry(l, booleans_);
            IdMap<DifficultyWinPointsFight, String> diffWinPts_ = trDiffWinPts(files_.getVal(MessagesTrs.TRANSLATION_WINPTS));
            _d.getTranslatedDiffWinPts().addEntry(l, diffWinPts_);
            IdMap<DifficultyModelLaw, String> diffLaw_ = trDiffLaw(files_.getVal(MessagesTrs.TRANSLATION_MODELLAW));
            _d.getTranslatedDiffModelLaw().addEntry(l, diffLaw_);
            IdMap<EnvironmentType, String> environments_ = trEnv(files_.getVal(MessagesTrs.TRANSLATION_ENVIRONMENTS));
            _d.getTranslatedEnvironment().addEntry(l, environments_);
            IdMap<Statistic, String> statistics_ = trStat(files_.getVal(MessagesTrs.TRANSLATION_STATISTICS));
            _d.getTranslatedStatistics().addEntry(l, statistics_);
            IdMap<TargetChoice, String> targets_ = trTargets(files_.getVal(MessagesTrs.TRANSLATION_TARGETS));
            _d.getTranslatedTargets().addEntry(l, targets_);
            StringMap<String> categories_ = tr(files_.getVal(MessagesTrs.TRANSLATION_CATEGORIES));
            _d.getTranslatedCategories().addEntry(l, categories_);
            StringMap<String> types_ = tr(files_.getVal(MessagesTrs.TRANSLATION_TYPES));
            _d.getTranslatedTypes().addEntry(l, types_);
            StringMap<String> pokemon_ = tr(files_.getVal(MessagesTrs.TRANSLATION_POKEMON));
            _d.getTranslatedPokemon().addEntry(l, pokemon_);
            StringMap<String> moves_ = tr(files_.getVal(MessagesTrs.TRANSLATION_MOVES));
            _d.getTranslatedMoves().addEntry(l, moves_);
            StringMap<String> items_ = tr(files_.getVal(MessagesTrs.TRANSLATION_ITEMS));
            _d.getTranslatedItems().addEntry(l, items_);
            StringMap<String> abilities_ = tr(files_.getVal(MessagesTrs.TRANSLATION_ABILITIES));
            _d.getTranslatedAbilities().addEntry(l, abilities_);
            StringMap<String> status_ = tr(files_.getVal(MessagesTrs.TRANSLATION_STATUS));
            _d.getTranslatedStatus().addEntry(l, status_);
            StringMap<String> fctsMath_ = tr(files_.getVal(MessagesTrs.TRANSLATION_MATH));
            _d.getTranslatedFctMath().addEntry(l, fctsMath_);
            StringMap<String> descrClasses_ = tr(files_.getVal(MessagesTrs.TRANSLATION_CLASSES));
            _d.getTranslatedClassesDescriptions().addEntry(l, descrClasses_);
            StringMap<String> litteral_ = files_.getVal(MessagesTrs.TRANSLATION_LITTERAL).getMapping();
            _d.getLitterals().addEntry(l, litteral_);
        }
//        _d.getConstNum().addEntry(DataBase.DEF_BASE_MOVE,new Rate("1"));
    }

    private static IdMap<TargetChoice, String> trTargets(TranslationsFile _str) {
        IdMap<TargetChoice, String> targets_ = new IdMap<TargetChoice, String>();
        for (EntryCust<String, String> l2_ : _str.getMapping().entryList()) {
            targets_.addEntry(
                    TargetChoice.getTargetChoiceByName(l2_.getKey()),
                    l2_.getValue());
        }
        return targets_;
    }

    private static IdMap<Statistic, String> trStat(TranslationsFile _str) {
        IdMap<Statistic, String> statistics_ = new IdMap<Statistic, String>();
        for (EntryCust<String, String> l2_ : _str.getMapping().entryList()) {
            statistics_.addEntry(Statistic.getStatisticByName(l2_.getKey()),
                    l2_.getValue());
        }
        return statistics_;
    }

    private static IdMap<EnvironmentType, String> trEnv(TranslationsFile _str) {
        IdMap<EnvironmentType, String> environments_ = new IdMap<EnvironmentType, String>();
        for (EntryCust<String, String> l2_ : _str.getMapping().entryList()) {
            environments_.addEntry(EnvironmentType.getEnvByName(l2_.getKey()),
                    l2_.getValue());
        }
        return environments_;
    }

    private static IdMap<DifficultyModelLaw, String> trDiffLaw(TranslationsFile _str) {
        IdMap<DifficultyModelLaw, String> diffLaw_ = new IdMap<DifficultyModelLaw, String>();
        for (EntryCust<String, String> l2_ : _str.getMapping().entryList()) {
            diffLaw_.addEntry(DifficultyModelLaw.getModelByName(l2_.getKey()),
                    l2_.getValue());
        }
        return diffLaw_;
    }

    private static IdMap<DifficultyWinPointsFight, String> trDiffWinPts(TranslationsFile _str) {
        IdMap<DifficultyWinPointsFight, String> diffWinPts_ = new IdMap<DifficultyWinPointsFight, String>();
        for (EntryCust<String, String> l2_ : _str.getMapping().entryList()) {
            diffWinPts_.addEntry(
                    DifficultyWinPointsFight.getDiffWonPtsByName(l2_.getKey()), l2_.getValue());
        }
        return diffWinPts_;
    }

    private static IdMap<SelectedBoolean, String> trBooleans(TranslationsFile _str) {
        IdMap<SelectedBoolean, String> booleans_ = new IdMap<SelectedBoolean, String>();
        for (EntryCust<String, String> l2_ : _str.getMapping().entryList()) {
            booleans_.addEntry(SelectedBoolean.getBoolByName(l2_.getKey()),
                    l2_.getValue());
        }
        return booleans_;
    }

    private static IdMap<Gender, String> trGenders(TranslationsFile _str) {
        IdMap<Gender, String> genders_ = new IdMap<Gender, String>();
        for (EntryCust<String, String> l2_ : _str.getMapping().entryList()) {
            genders_.addEntry(Gender.getGenderByName(l2_.getKey()),
                    l2_.getValue());
        }
        return genders_;
    }

    private static StringMap<String> tr(TranslationsFile _str) {
        return _str.getMapping();
    }

}
