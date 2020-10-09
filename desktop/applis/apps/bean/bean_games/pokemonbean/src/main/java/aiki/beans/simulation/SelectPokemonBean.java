package aiki.beans.simulation;
import aiki.beans.CommonBean;
import aiki.beans.PokemonStandards;
import aiki.beans.facade.comparators.ComparatorTrString;
import aiki.beans.facade.dto.PokemonLine;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.facade.CriteriaForSearching;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import code.images.BaseSixtyFourUtil;
import code.util.*;
import aiki.facade.enums.SelectedBoolean;
import code.util.core.StringUtil;

public class SelectPokemonBean extends CommonBean {
    private CustList<PokemonLine> pokedex = new CustList<PokemonLine>();
    private String typedName = DataBase.EMPTY_STRING;
    private String typedType = DataBase.EMPTY_STRING;
    private String hasEvo = SelectedBoolean.YES_AND_NO.name();
    private String isEvo = SelectedBoolean.YES_AND_NO.name();
    private String isLeg = SelectedBoolean.YES_AND_NO.name();
    private boolean wholeWord;
    private TreeMap<String,String> booleans;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<SelectedBoolean,String> translatedBooleans_;
        translatedBooleans_ = data_.getTranslatedBooleans().getVal(getLanguage());
        StringMap<String> translated_ = new StringMap<String>();
        for (EntryCust<SelectedBoolean,String> s: translatedBooleans_.entryList()) {
            translated_.addEntry(s.getKey().name(),s.getValue());
        }
        booleans = new TreeMap<String, String>(new ComparatorTrString(translated_));
        for (SelectedBoolean s: translatedBooleans_.getKeys()) {
            booleans.put(s.name(), translatedBooleans_.getVal(s));
        }
        StringList pokedex_ = (StringList) getForms().getVal(POKEMON_SET);
        pokedex.clear();
        StringMap<String> translationsPk_;
        translationsPk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        for (String k: pokedex_) {
            PokemonData pkData_ = data_.getPokedex().getVal(k);
            PokemonLine line_ = new PokemonLine();
            line_.setName(k);
            line_.setDisplayName(translationsPk_.getVal(k));
            StringList types_ = new StringList();
            for (String t: pkData_.getTypes()) {
                types_.add(translationsTypes_.getVal(t));
            }
            line_.setTypes(types_);
            line_.setEvolutions(pkData_.getEvolutions().size());
            pokedex.add(line_);
        }
        typedName = escapedStringQuote(typedName);
        typedType = escapedStringQuote(typedType);
    }
    public static String cancel() {
        return POKEMON;
    }
    public String search() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsPk_;
        translationsPk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringList pokedex_ = new StringList();
        for (String k: data_.getPokedex().getKeys()) {
            String displayName_ = translationsPk_.getVal(k);
            if (!StringUtil.match(displayName_, typedName)) {
                continue;
            }
            PokemonData pkData_ = data_.getPokedex().getVal(k);
            boolean atLeastMatchType_ = false;
            for (String t: pkData_.getTypes()) {
                String displayType_;
                displayType_ = translationsTypes_.getVal(t);
                if (wholeWord) {
                    if (typedType == null) {
                        continue;
                    }
                    if (!StringUtil.quickEq(displayType_, typedType)) {
                        continue;
                    }
                } else {
                    if (!StringUtil.match(displayType_, typedType)) {
                        continue;
                    }
                }
                atLeastMatchType_ = true;
            }
            if (!atLeastMatchType_) {
                continue;
            }
            if (!CriteriaForSearching.match(PokemonStandards.getBoolByName(hasEvo),pkData_.getEvolutions().isEmpty())) {
                continue;
            }
            if (!CriteriaForSearching.match(PokemonStandards.getBoolByName(isEvo),!StringUtil.quickEq(k, pkData_.getBaseEvo()))) {
                continue;
            }
            if (!CriteriaForSearching.match(PokemonStandards.getBoolByName(isLeg),pkData_.getGenderRep() == GenderRepartition.LEGENDARY)) {
                continue;
            }
            pokedex_.add(k);
        }
        pokedex_.sortElts(new ComparatorTrStrings(translationsPk_));
        getForms().put(POKEMON_SET, pokedex_);
        if (pokedex_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(POKEMON_NAME_EDIT,pokedex_.first());
            return POKEMON;
        }
        return POKEMON_SET;
    }
    public String getMiniImage(Long _number) {
        String name_ = pokedex.get(_number.intValue()).getName();
        DataBase data_ = (DataBase) getDataBase();
//        return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
        return BaseSixtyFourUtil.getStringByImage(data_.getMiniPk().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
    }
    public String clickLink(Long _number) {
        getForms().put(POKEMON_NAME_EDIT,pokedex.get(_number.intValue()).getName());
        return POKEMON;
    }

    public void setTypedName(String _typedName) {
        typedName = _typedName;
    }

    public String getTypedName() {
        return typedName;
    }

    public void setTypedType(String _typedType) {
        typedType = _typedType;
    }

    public String getTypedType() {
        return typedType;
    }

    public void setWholeWord(boolean _wholeWord) {
        wholeWord = _wholeWord;
    }

    public boolean getWholeWord() {
        return wholeWord;
    }

    public TreeMap<String,String> getBooleans() {
        return booleans;
    }

    public String getHasEvo() {
        return hasEvo;
    }

    public void setHasEvo(String _hasEvo) {
        hasEvo = _hasEvo;
    }

    public String getIsEvo() {
        return isEvo;
    }

    public void setIsEvo(String _isEvo) {
        isEvo = _isEvo;
    }

    public String getIsLeg() {
        return isLeg;
    }

    public void setIsLeg(String _isLeg) {
        isLeg = _isLeg;
    }

    public CustList<PokemonLine> getPokedex() {
        return pokedex;
    }
}