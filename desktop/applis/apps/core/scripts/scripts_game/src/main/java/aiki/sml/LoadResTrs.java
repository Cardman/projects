package aiki.sml;

import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.moves.enums.*;
import code.maths.*;
import code.util.*;
import aiki.facade.enums.*;
import code.util.core.*;
import aiki.sml.trs.*;
import aiki.map.levels.enums.*;
import aiki.game.params.enums.*;
import aiki.map.pokemon.enums.*;
import code.sml.*;

public final class LoadResTrs {

    private static final char TAB_CHAR = '\t';
    private static final String TAB = "\t";

    private static final char RETURN_LINE_CHAR = '\n';
    private LoadResTrs(){}
    public static void loadResources(DataBase _d) {
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
        StringMap<String> trs_ = Trs.tr();
        for (String l : _d.getLanguages()) {
            IdMap<Gender, String> genders_ = trGenders(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_GENDERS));
            _d.getTranslatedGenders().addEntry(l, genders_);
            IdMap<SelectedBoolean, String> booleans_ = trBooleans(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_BOOLEANS));
            _d.getTranslatedBooleans().addEntry(l, booleans_);
            IdMap<DifficultyWinPointsFight, String> diffWinPts_ = trDiffWinPts(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_DIFF_WIN_PTS));
            _d.getTranslatedDiffWinPts().addEntry(l, diffWinPts_);
            IdMap<DifficultyModelLaw, String> diffLaw_ = trDiffLaw(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_DIFF_MODEL_LAW));
            _d.getTranslatedDiffModelLaw().addEntry(l, diffLaw_);
            IdMap<EnvironmentType, String> environments_ = trEnv(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_ENVIRONMENTS));
            _d.getTranslatedEnvironment().addEntry(l, environments_);
            IdMap<Statistic, String> statistics_ = trStat(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_STATISTICS));
            _d.getTranslatedStatistics().addEntry(l, statistics_);
            IdMap<TargetChoice, String> targets_ = trTargets(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_TARGETS));
            _d.getTranslatedTargets().addEntry(l, targets_);
            StringMap<String> categories_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_CATEGORIES));
            _d.getTranslatedCategories().addEntry(l, categories_);
            StringMap<String> types_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_TYPES));
            _d.getTranslatedTypes().addEntry(l, types_);
            StringMap<String> pokemon_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_POKEMON));
            _d.getTranslatedPokemon().addEntry(l, pokemon_);
            StringMap<String> moves_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_MOVES));
            _d.getTranslatedMoves().addEntry(l, moves_);
            StringMap<String> items_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_ITEMS));
            _d.getTranslatedItems().addEntry(l, items_);
            StringMap<String> abilities_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_ABILITIES));
            _d.getTranslatedAbilities().addEntry(l, abilities_);
            StringMap<String> status_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_STATUS));
            _d.getTranslatedStatus().addEntry(l, status_);
            StringMap<String> fctsMath_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_MATH));
            _d.getTranslatedFctMath().addEntry(l, fctsMath_);
            StringMap<String> descrClasses_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_CLASSES));
            _d.getTranslatedClassesDescriptions().addEntry(l, descrClasses_);
            StringMap<String> litteral_ = trLitt(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_LITTERAL));
            _d.getLitterals().addEntry(l, litteral_);
        }
        _d.getConstNum().addEntry(DataBase.DEF_BASE_MOVE,new Rate("1"));
    }

    private static StringMap<String> trLitt(String _str) {
        StringMap<String> litteral_ = new StringMap<String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            litteral_
                    .addEntry(infos_.first(), DocumentBuilder
                            .transformSpecialChars(StringUtil
                                    .join(infos_.leftMinusOne(
                                            infos_.size()), TAB)));
        }
        return litteral_;
    }

    private static IdMap<TargetChoice, String> trTargets(String _str) {
        IdMap<TargetChoice, String> targets_ = new IdMap<TargetChoice, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            targets_.addEntry(
                    TargetChoice.getTargetChoiceByName(infos_.first()),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return targets_;
    }

    private static IdMap<Statistic, String> trStat(String _str) {
        IdMap<Statistic, String> statistics_ = new IdMap<Statistic, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            statistics_.addEntry(Statistic.getStatisticByName(infos_.first()),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return statistics_;
    }

    private static IdMap<EnvironmentType, String> trEnv(String _str) {
        IdMap<EnvironmentType, String> environments_ = new IdMap<EnvironmentType, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            environments_.addEntry(EnvironmentType.getEnvByName(infos_.first()),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return environments_;
    }

    private static IdMap<DifficultyModelLaw, String> trDiffLaw(String _str) {
        IdMap<DifficultyModelLaw, String> diffLaw_ = new IdMap<DifficultyModelLaw, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            diffLaw_.addEntry(DifficultyModelLaw.getModelByName(infos_.first()),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return diffLaw_;
    }

    private static IdMap<DifficultyWinPointsFight, String> trDiffWinPts(String _str) {
        IdMap<DifficultyWinPointsFight, String> diffWinPts_ = new IdMap<DifficultyWinPointsFight, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            diffWinPts_.addEntry(
                    DifficultyWinPointsFight.getDiffWonPtsByName(infos_.first()), DocumentBuilder
                            .transformSpecialChars(infos_.last()));
        }
        return diffWinPts_;
    }

    private static IdMap<SelectedBoolean, String> trBooleans(String _str) {
        IdMap<SelectedBoolean, String> booleans_ = new IdMap<SelectedBoolean, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            booleans_.addEntry(SelectedBoolean.getBoolByName(infos_.first()),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return booleans_;
    }

    private static IdMap<Gender, String> trGenders(String _str) {
        IdMap<Gender, String> genders_ = new IdMap<Gender, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            genders_.addEntry(Gender.getGenderByName(infos_.first()),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return genders_;
    }

    private static StringMap<String> tr(String _str) {
        StringMap<String> out_ = new StringMap<String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            out_.addEntry(infos_.first(),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return out_;
    }

}
