package aiki.beans.simulation;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.dto.PokemonLine;
import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import aiki.comparators.ComparatorTrStringBoolean;
import aiki.comparators.ComparatorTrStringGender;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.util.CustList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.opers.BaseSixtyFourUtil;
import code.util.pagination.SelectedBoolean;

public class AddPokemonBean extends CommonBean {
    private String namePk = DataBase.EMPTY_STRING;
    private String ability = DataBase.EMPTY_STRING;
    private Gender gender = Gender.NO_GENDER;
    private short level;
    private TreeMap<Gender,String> genders;
    private TreeMap<String,String> abilities;
    private CustList<PokemonLine> pokedex = new CustList<PokemonLine>();
    private String typedName = DataBase.EMPTY_STRING;
    private String typedType = DataBase.EMPTY_STRING;
    private SelectedBoolean hasEvo = SelectedBoolean.YES_AND_NO;
    private SelectedBoolean isEvo = SelectedBoolean.YES_AND_NO;
    private SelectedBoolean isLeg = SelectedBoolean.YES_AND_NO;
    private boolean wholeWord;
    private TreeMap<SelectedBoolean,String> booleans;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<SelectedBoolean,String> translatedBooleans_;
        translatedBooleans_ = data_.getTranslatedBooleans().getVal(getLanguage());
        booleans = new TreeMap<SelectedBoolean, String>(new ComparatorTrStringBoolean(translatedBooleans_));
        StringMap<String> translatedAbilities_;
        translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        abilities = new TreeMap<String, String>(new ComparatorTrStrings(translatedAbilities_));
        EnumMap<Gender,String> translatedGenders_;
        translatedGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        genders = new TreeMap<Gender, String>(new ComparatorTrStringGender(translatedGenders_));
        for (SelectedBoolean s: translatedBooleans_.getKeys()) {
            booleans.put(s, translatedBooleans_.getVal(s));
        }
        if (getForms().contains(PK_NAME)) {
            namePk = (String) getForms().getVal(PK_NAME);
            PokemonData pkData_ = data_.getPokemon(namePk);
            for (String a: pkData_.getAbilities()) {
                abilities.put(a, translatedAbilities_.getVal(a));
            }
            for (Gender g: pkData_.getGenderRep().getPossibleGenders()) {
                genders.put(g, translatedGenders_.getVal(g));
            }
        }
        StringList pokedex_ = (StringList) getForms().getVal(POKEMON_SET_SIMU);
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
    public String add() {
        if (!getForms().contains(PK_NAME)) {
            return DataBase.EMPTY_STRING;
        }
        if (!genders.contains(gender)) {
            return DataBase.EMPTY_STRING;
        }
        if (!abilities.contains(ability)) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = (DataBase) getDataBase();
        if (level < data_.getMinLevel()) {
            level = (short) data_.getMinLevel();
        }
        if (level > data_.getMaxLevel()) {
            level = (short) data_.getMaxLevel();
        }
        WildPk pk_ = new WildPk();
        pk_.setName(namePk);
        pk_.setLevel(level);
        pk_.setAbility(ability);
        pk_.setGender(gender);
        PokemonData pkData_ = data_.getPokemon(namePk);
        PokemonPlayerDto pkDto_ = new PokemonPlayerDto();
        pkDto_.setPokemon(pk_);
        pkDto_.setMoves(pkData_.getMovesAtLevel(level, data_.getNbMaxMoves()));
        getForms().put(POKEMON_ADDED, pkDto_);
        return SIMULATION;
    }
    public static String cancel() {
        return SIMULATION;
    }
    public void search() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsPk_;
        translationsPk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringList pokedex_ = new StringList();
        for (String k: data_.getPokedex().getKeys()) {
            String displayName_ = translationsPk_.getVal(k);
            if (!StringList.match(displayName_, typedName)) {
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
                    if (!StringList.quickEq(displayType_, typedType)) {
                        continue;
                    }
                } else {
                    if (!StringList.match(displayType_, typedType)) {
                        continue;
                    }
                }
                atLeastMatchType_ = true;
            }
            if (!atLeastMatchType_) {
                continue;
            }
            if (hasEvo != SelectedBoolean.YES_AND_NO) {
                if (pkData_.getEvolutions().isEmpty() && hasEvo.isSelected()) {
                    continue;
                }
                if (!pkData_.getEvolutions().isEmpty() && !hasEvo.isSelected()) {
                    continue;
                }
            }
            if (isEvo != SelectedBoolean.YES_AND_NO) {
                boolean isBaseEvo_ = StringList.quickEq(k, pkData_.getBaseEvo());
                if (isBaseEvo_ && isEvo.isSelected()) {
                    continue;
                }
                if (!isBaseEvo_ && !isEvo.isSelected()) {
                    continue;
                }
            }
            if (isLeg != SelectedBoolean.YES_AND_NO) {
                boolean isLeg_ = pkData_.getGenderRep() == GenderRepartition.LEGENDARY;
                if (isLeg_ && !isLeg.isSelected()) {
                    continue;
                }
                if (!isLeg_ && isLeg.isSelected()) {
                    continue;
                }
            }
            pokedex_.add(k);
        }
        pokedex_.sortElts(new ComparatorTrStrings(translationsPk_));
        getForms().put(POKEMON_SET_SIMU, pokedex_);
        if (pokedex_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(PK_NAME,pokedex_.first());
        }
    }
    public String getMiniImage(Long _number) {
        String name_ = pokedex.get(_number.intValue()).getName();
        DataBase data_ = (DataBase) getDataBase();
//        return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
        return BaseSixtyFourUtil.getStringByImage(data_.getMiniPk().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
    }
    public void clickLink(Long _number) {
        getForms().put(PK_NAME,pokedex.get(_number.intValue()).getName());
    }

    public String getNamePk() {
        return namePk;
    }

    public TreeMap<String,String> getAbilities() {
        return abilities;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String _ability) {
        ability = _ability;
    }

    public TreeMap<Gender,String> getGenders() {
        return genders;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender _gender) {
        gender = _gender;
    }

    public void setLevel(short _level) {
        level = _level;
    }

    public short getLevel() {
        return level;
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

    public TreeMap<SelectedBoolean,String> getBooleans() {
        return booleans;
    }

    public SelectedBoolean getHasEvo() {
        return hasEvo;
    }

    public void setHasEvo(SelectedBoolean _hasEvo) {
        hasEvo = _hasEvo;
    }

    public SelectedBoolean getIsEvo() {
        return isEvo;
    }

    public void setIsEvo(SelectedBoolean _isEvo) {
        isEvo = _isEvo;
    }

    public SelectedBoolean getIsLeg() {
        return isLeg;
    }

    public void setIsLeg(SelectedBoolean _isLeg) {
        isLeg = _isLeg;
    }

    public CustList<PokemonLine> getPokedex() {
        return pokedex;
    }
}