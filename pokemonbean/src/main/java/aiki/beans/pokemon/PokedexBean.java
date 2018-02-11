package aiki.beans.pokemon;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.dto.PokemonLine;
import aiki.comparators.ComparatorTrStringBoolean;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import code.images.ConverterBufferedImage;
import code.util.CustList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.pagination.SelectedBoolean;

public class PokedexBean extends CommonBean {
    private CustList<PokemonLine> pokedex = new CustList<PokemonLine>();
    private String typedName = DataBase.EMPTY_STRING;
    private String typedType = DataBase.EMPTY_STRING;
    private SelectedBoolean hasEvo = SelectedBoolean.YES_AND_NO;
    private String typedMinNbPossEvos = DataBase.EMPTY_STRING;
    private String typedMaxNbPossEvos = DataBase.EMPTY_STRING;
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
        for (SelectedBoolean s: translatedBooleans_.getKeys()) {
            booleans.put(s, translatedBooleans_.getVal(s));
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
        typedMinNbPossEvos = escapedStringQuote(typedMinNbPossEvos);
        typedMaxNbPossEvos = escapedStringQuote(typedMaxNbPossEvos);
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
            if (!typedMinNbPossEvos.isEmpty()) {
                try {
                    short min_ = Short.parseShort(typedMinNbPossEvos);
                    if (pkData_.getDirectEvolutions().size() < min_) {
                        continue;
                    }
                } catch (RuntimeException _0) {
                }
            }
            if (!typedMaxNbPossEvos.isEmpty()) {
                try {
                    short max_ = Short.parseShort(typedMaxNbPossEvos);
                    if (pkData_.getDirectEvolutions().size() > max_) {
                        continue;
                    }
                } catch (RuntimeException _0) {
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
        getForms().put(POKEMON_SET, pokedex_);
        if (pokedex_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(PK,pokedex_.first());
            return POKEMON;
        }
        return POKEMON_SET;
    }
    public String getMiniImage(Long _number) {
        String name_ = pokedex.get(_number.intValue()).getName();
        DataBase data_ = (DataBase) getDataBase();
//        return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
        return ConverterBufferedImage.surroundImage(data_.getMiniPk().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
    }
    public String clickLink(Long _number) {
        getForms().put(PK,pokedex.get(_number.intValue()).getName());
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

    public void setTypedMinNbPossEvos(String _typedMinNbPossEvos) {
        typedMinNbPossEvos = _typedMinNbPossEvos;
    }

    public String getTypedMinNbPossEvos() {
        return typedMinNbPossEvos;
    }

    public void setTypedMaxNbPossEvos(String _typedMaxNbPossEvos) {
        typedMaxNbPossEvos = _typedMaxNbPossEvos;
    }

    public String getTypedMaxNbPossEvos() {
        return typedMaxNbPossEvos;
    }

    public TreeMap<SelectedBoolean,String> getBooleans() {
        return booleans;
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