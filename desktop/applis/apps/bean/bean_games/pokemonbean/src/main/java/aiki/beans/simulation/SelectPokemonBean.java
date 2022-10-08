package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.PokemonStandards;
import aiki.beans.facade.dto.PokemonLine;
import aiki.beans.pokemon.PokedexBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.CriteriaForSearching;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import code.images.BaseSixtyFourUtil;
import code.util.*;
import code.util.core.StringUtil;

public class SelectPokemonBean extends CommonBean {
    private final CustList<PokemonLine> pokedex = new CustList<PokemonLine>();
    private String typedName = DataBase.EMPTY_STRING;
    private String typedType = DataBase.EMPTY_STRING;
    private String hasEvo = SelectedBoolean.YES_AND_NO.name();
    private String isEvo = SelectedBoolean.YES_AND_NO.name();
    private String isLeg = SelectedBoolean.YES_AND_NO.name();
    private boolean wholeWord;
    private DictionaryComparator<String,String> booleans;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        AbsMap<SelectedBoolean,String> translatedBooleans_;
        translatedBooleans_ = data_.getTranslatedBooleans().getVal(getLanguage());
        booleans = DictionaryComparatorUtil.buildBoolStr(data_,getLanguage());
        for (SelectedBoolean s: translatedBooleans_.getKeys()) {
            booleans.put(s.getBoolName(), translatedBooleans_.getVal(s));
        }
        StringList pokedex_ = getForms().getValList(CST_POKEMON_SET);
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
        return CST_POKEMON;
    }
    public String search() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsPk_;
        translationsPk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringList pokedex_ = new StringList();
        for (EntryCust<String, PokemonData> k: data_.getPokedex().entryList()) {
            String displayName_ = translationsPk_.getVal(k.getKey());
            if (!StringUtil.match(displayName_, typedName)) {
                continue;
            }
            PokemonData pkData_ = k.getValue();
            if (PokedexBean.atLeastMatchType(translationsTypes_,wholeWord,typedType,pkData_.getTypes()) && CriteriaForSearching.match(PokemonStandards.getBoolByName(hasEvo), pkData_.getEvolutions().isEmpty()) && CriteriaForSearching.match(PokemonStandards.getBoolByName(isEvo), !StringUtil.quickEq(k.getKey(), pkData_.getBaseEvo())) && CriteriaForSearching.match(PokemonStandards.getBoolByName(isLeg), pkData_.getGenderRep() == GenderRepartition.LEGENDARY)) {
                pokedex_.add(k.getKey());
            }
        }
        pokedex_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        getForms().put(CST_POKEMON_SET, pokedex_);
        if (pokedex_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(CST_POKEMON_NAME_EDIT,pokedex_.first());
            return CST_POKEMON;
        }
        return CST_POKEMON_SET;
    }
    public String getMiniImage(int _number) {
        String name_ = pokedex.get(_number).getName();
        DataBase data_ = getDataBase();
//        return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
        return BaseSixtyFourUtil.getStringByImage(data_.getMiniPk().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
    }
    public String clickLink(int _number) {
        getForms().put(CST_POKEMON_NAME_EDIT,pokedex.get(_number).getName());
        return CST_POKEMON;
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

    public DictionaryComparator<String,String> getBooleans() {
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