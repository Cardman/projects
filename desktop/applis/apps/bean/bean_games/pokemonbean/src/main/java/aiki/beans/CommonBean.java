package aiki.beans;

import aiki.beans.facade.dto.PokemonLine;
import aiki.beans.pokemon.PokedexBean;
import aiki.beans.simulation.SelectItemBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.CriteriaForSearching;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import code.bean.Bean;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class CommonBean extends Bean implements WithFacade,WithForms {
    protected static final String CST_ABILITIES = "abilities";
    protected static final String CST_ABILITIES_SET = "abilities_set";
    protected static final String CST_ABILITY = "ability";
    protected static final String CST_ALLY = "ally";
    protected static final String CST_AREA = "area";
    protected static final String CST_BALL = "ball";
    protected static final String CST_BERRY = "berry";
    protected static final String CST_BOOST = "boost";
    protected static final String CST_COMBO = "combo";
    protected static final String CST_SIMULATION = "simulation";
    protected static final String CST_SIMULATION_STATE = "simulation_state";
    protected static final String CST_CURRENT_TILE = "current_tile";
    protected static final String CST_DEALER = "dealer";
    protected static final String CST_DUAL = "dual";
    protected static final String CST_EVO_ITEM = "evo_item";
    protected static final String CST_EVO_STONE = "evo_stone";
    protected static final String CST_EVOLVINGITEM = "evolvingitem";
    protected static final String CST_EVOLVINGSTONE = "evolvingstone";
    protected static final String CST_FOSSIL = "fossil";
    protected static final String CST_FROM_LIST = "from_list";
    protected static final String CST_HEALINGHP = "healinghp";
    protected static final String CST_HEALINGHPSTATUS = "healinghpstatus";
    protected static final String CST_HEALINGITEM = "healingitem";
    protected static final String CST_HEALINGPP = "healingpp";
    protected static final String CST_HEALINGSTATUS = "healingstatus";
    protected static final String CST_INSIDE = "inside";
    protected static final String CST_ITEM = "item";
    protected static final String CST_ITEMFORBATTLE = "itemforbattle";
    protected static final String CST_ITEMS = "items";
    protected static final String CST_ITEMS_SET = "items_set";
    protected static final String CST_LEARNT = "learnt";
    protected static final String CST_LEARNT_MOVES = "learnt_moves";
    protected static final String CST_LEG_PK = "leg_pk";
    protected static final String CST_LEVEL = "level";
    protected static final String CST_LEVEL_MAP = "level_map";
    protected static final String CST_LEVEL_MAP_INDEX = "level_map_index";
    protected static final String CST_MOVE = "move";
    protected static final String CST_MOVES = "moves";
    protected static final String CST_MOVES_SET = "moves_set";
    protected static final String CST_OTHER_ITEM = "other_item";
    protected static final String CST_OTHER_WEATHER = "other_weather";
    protected static final String CST_PK = "pk";
    protected static final String CST_PLACE_MAP_INDEX = "place_map_index";
    protected static final String CST_POKEMON = "pokemon";
    protected static final String CST_POKEMON_SET = "pokemon_set";
    protected static final String CST_PROPONE_LINK = "propone_link";
    protected static final String CST_COORDS = "coords";
    protected static final String CST_NO_FIGHT = "no_fight";
    protected static final String CST_POKEMON_SET_SIMU = "pokemon_set_simu";
    protected static final String CST_POKEMON_ADDED = "pokemon_added";
    protected static final String CST_POKEMON_EDIT = "pokemon_edit";
    protected static final String CST_POKEMON_INDEX_EDIT = "pokemon_index_edit";
    protected static final String CST_POKEMON_NAME_EDIT = "pokemon_name_edit";
    protected static final String CST_POKEMON_LEVEL_EDIT = "pokemon_level_edit";
    protected static final String CST_POKEMON_EXPERIENCE = "pokemon_experience";
    protected static final String CST_POKEMON_HAPPINESS = "pokemon_happiness";
    protected static final String CST_POKEMON_HP = "pokemon_hp";
    protected static final String CST_POKEMON_EV_VAR = "pokemon_ev_";
    protected static final String CST_HEAL_EDIT_PK = "heal_edit_pk";
    protected static final String CST_CATCHING_BALL = "catching_ball";
    protected static final String CST_ITEM_EDIT = "item_edit";
    protected static final String CST_POKEMON_MOVES_EDIT = "pokemon_moves_edit";
    protected static final String CST_POKEMON_ABILITY_EDIT = "pokemon_ability_edit";
    protected static final String CST_POKEMON_GENDER_EDIT = "pokemon_gender_edit";
    protected static final String CST_POKEMON_FOE = "pokemon_foe";
    protected static final String CST_ADDING_TRAINER_PK = "adding_pk";
    protected static final String CST_POKEMON_ITEM_EDIT = "pokemon_item_edit";
    protected static final String CST_ITEMS_SET_EDIT = "items_set_edit";
    protected static final String CST_VALIDATE_TRAINER_PK = "validate_trainer_pk";
    protected static final String CST_ADD_POKEMON_PLAYER = "add_pokemon_player";
    protected static final String CST_EDIT_POKEMON_PLAYER = "edit_pokemon_player";
    protected static final String CST_IS_POKEMON_PLAYER_MOVES = "is_pokemon_player_moves";
    protected static final String CST_PK_NAME = "pk_name";
    /**exception naming*/
    protected static final String CST_PROPONE_LINK_VAR = "propone_link_";
    protected static final String CST_PROPONE_TILE = "propone_tile";
    protected static final String CST_REPEL = "repel";
    protected static final String CST_SEE_AREA = "see_area";
    protected static final String CST_SELLER = "seller";
    protected static final String CST_SELLINGITEM = "sellingitem";
    protected static final String CST_STATUS = "status";
    protected static final String CST_STATUS_SET = "status_set";
    protected static final String CST_TRAINER = "trainer";
    protected static final String CST_TRAINER_MULTI_FIGHT = "trainer_multi_fight";
    protected static final String CST_TRAINER_ONE_FIGHT = "trainer_one_fight";
    protected static final String CST_SPACE = " ";
    protected static final String CST_SEP_DASH = " - ";
    protected static final String CST_CENT = "100";
    protected static final char CST_UNDERSCORE = '_';
    protected static final String CST_LEFT_BRACE = "{";
    protected static final String CST_RIGHT_BRACE = "}";
    protected static final String CST_QUOTED_LEFT_BRACE = "'{";
    protected static final String CST_QUOTED_RIGHT_BRACE = "}'";
    protected static final String CST_QUOTE = "'";
    protected static final String CST_ESCAPED_QUOTE = "''";
    protected static final char CST_LEFT_PAR = '(';
    protected static final char CST_RIGHT_PAR = ')';
    protected static final char CST_PIPE_CHAR = '|';

    private FacadeGame dataBase;
    private String typedAbility = DataBase.EMPTY_STRING;
    private String typedName = DataBase.EMPTY_STRING;
    private String typedPrice = DataBase.EMPTY_STRING;

    private String typedClass = DataBase.EMPTY_STRING;
    private String typedType = DataBase.EMPTY_STRING;
    private String typedStatus = DataBase.EMPTY_STRING;
    private String typedCategory = DataBase.EMPTY_STRING;
    private boolean wholeWord;
    private String hasEvo = SelectedBoolean.YES_AND_NO.name();
    private String isEvo = SelectedBoolean.YES_AND_NO.name();
    private String isLeg = SelectedBoolean.YES_AND_NO.name();
    private String typedMinNbPossEvos = DataBase.EMPTY_STRING;
    private String typedMaxNbPossEvos = DataBase.EMPTY_STRING;
    private String minPower = DataBase.EMPTY_STRING;
    private String maxPower = DataBase.EMPTY_STRING;
    private String minAccuracy = DataBase.EMPTY_STRING;
    private String maxAccuracy = DataBase.EMPTY_STRING;
    private DictionaryComparator<String,String> booleans;
    private StringList sortedAbilities = new StringList();
    private final CustList<PokemonLine> pokedex = new CustList<PokemonLine>();

    public static Rate rateTrue(MonteCarloBoolean _law) {
        if (_law.isZero()) {
            return Rate.zero();
        }
        return _law.normalizedRate(BoolVal.TRUE);
    }

    public DataBase getDataBase() {
        return db().getData();
    }

    @Override
    public FacadeGame db() {
        return dataBase;
    }

    @Override
    public void setDataBase(FacadeGame _dataBase) {
        dataBase = _dataBase;
    }

    public StringMapObject getForms() {
        return (StringMapObject) getBaseForms();
    }

    public void setForms(StringMapObject _forms) {
        setBaseForms(_forms);
    }

    protected void setupPokedex(StringList _pokedex) {
        DataBase data_ = getDataBase();
        getPokedex().clear();
        StringMap<String> translationsPk_;
        translationsPk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        for (String k: _pokedex) {
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
            getPokedex().add(line_);
        }
        escapeInputs();
    }
    protected void escapeInputs() {
        setTypedName(escapedStringQuote(getTypedName()));
        setTypedType(escapedStringQuote(getTypedType()));
        setTypedMinNbPossEvos(escapedStringQuote(getTypedMinNbPossEvos()));
        setTypedMaxNbPossEvos(escapedStringQuote(getTypedMaxNbPossEvos()));
        setTypedAbility(escapedStringQuote(getTypedAbility()));
        setTypedPrice(escapedStringQuote(getTypedPrice()));
        setTypedClass(escapedStringQuote(getTypedClass()));
        minPower = escapedStringQuote(minPower);
        maxPower = escapedStringQuote(maxPower);
        minAccuracy = escapedStringQuote(minAccuracy);
        maxAccuracy = escapedStringQuote(maxAccuracy);
        setTypedStatus(escapedStringQuote(getTypedStatus()));
    }
    protected static String escapedStringQuote(String _string) {
        StringMap<String> map_ = new StringMap<String>();
        map_.put(CST_QUOTE, CST_ESCAPED_QUOTE);
        map_.put(CST_LEFT_BRACE, StringUtil.concat(CST_QUOTED_LEFT_BRACE, CST_QUOTE));
        map_.put(CST_RIGHT_BRACE, StringUtil.concat(CST_QUOTE, CST_QUOTED_RIGHT_BRACE));
        return StringUtil.formatBasic(_string, map_, false);
    }
    protected static StringList getFormattedReasons(DataBase _data, StringList _reasons, String _language) {
//      Map<String,String> locHtml_ = new Map<>();
//        locHtml_.put(EAMP, E_AMP);
//        locHtml_.put(EGT, E_GT);
//        locHtml_.put(ELT, E_LT);
//        locHtml_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        locHtml_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        StringList reasons_;
        reasons_ = new StringList();
        for (String f: _reasons) {
            String formula_ = _data.getFormula(f, _language);
//            formula_ = StringList.replace(formula_, locHtml_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            formula_ = formula_.replace(EAMP, E_AMP);
//            formula_ = formula_.replace(EGT, E_GT);
//            formula_ = formula_.replace(ELT, E_LT);
            reasons_.add(formula_);
        }
        return reasons_;
    }

    protected static NatStringTreeMap<String> getMapVarsFail(DataBase _data, String _fail, String _language) {
        NatStringTreeMap<String> mapVars_ = _data.getDescriptions(_fail, _language);
        NatStringTreeMap<String> mapVarsFail_ = new NatStringTreeMap<String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        return mapVarsFail_;
    }

    protected static StringList getReasons(String _booleanString) {
        StringList reasons_;
        reasons_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        int iPostSep_ = IndexConstants.FIRST_INDEX;
        int nbLeftPar_ = 0;
        int nbRightPar_ = 0;
        while (i_ < _booleanString.length()) {
            if (_booleanString.charAt(i_) == CST_LEFT_PAR) {
                nbLeftPar_++;
            }
            if (_booleanString.charAt(i_) == CST_RIGHT_PAR) {
                nbRightPar_++;
            }
            if (_booleanString.charAt(i_) == CST_PIPE_CHAR && nbLeftPar_ == nbRightPar_) {
                reasons_.add(_booleanString.substring(iPostSep_, i_));
                iPostSep_ = i_ + 1;
            }
            i_++;
        }
        if (iPostSep_ < _booleanString.length()) {
            reasons_.add(_booleanString.substring(iPostSep_));
        }
        return reasons_;
    }

    protected StringList pokedex() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsPk_;
        translationsPk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringList pokedex_ = new StringList();
        for (EntryCust<String, PokemonData> k: data_.getPokedex().entryList()) {
            String displayName_ = translationsPk_.getVal(k.getKey());
            if (!StringUtil.match(displayName_, getTypedName())) {
                continue;
            }
            PokemonData pkData_ = k.getValue();
            if (atLeastMatchType(translationsTypes_,pkData_.getTypes()) && (getTypedMinNbPossEvos().isEmpty() || pkData_.getDirectEvolutions().size() >= NumberUtil.parseLongZero(getTypedMinNbPossEvos())) && (getTypedMaxNbPossEvos().isEmpty() || pkData_.getDirectEvolutions().size() <= NumberUtil.parseLongZero(getTypedMaxNbPossEvos())) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getHasEvo()), pkData_.getEvolutions().isEmpty()) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getIsEvo()), !StringUtil.quickEq(k.getKey(), pkData_.getBaseEvo())) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getIsLeg()), pkData_.getGenderRep() == GenderRepartition.LEGENDARY)) {
                pokedex_.add(k.getKey());
            }
        }
        pokedex_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        return pokedex_;
    }
    protected StringList movesAmong(StringMap<MoveData> _m) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_;
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        moves_ = new StringList();
        for (EntryCust<String, MoveData> k: _m.entryList()) {
            String displayName_ = translationsMoves_.getVal(k.getKey());
            if (!StringUtil.match(displayName_, getTypedName())) {
                continue;
            }
            MoveData moveData_ = k.getValue();
            if (atLeastMatchType(translationsTypes_, moveData_.getTypes()) && (StringUtil.quickEq(getTypedCategory(), DataBase.EMPTY_STRING) || StringUtil.quickEq(getTypedCategory(), moveData_.getCategory())) && !excludeByAccuracy(moveData_) && !excludeByPower(moveData_)) {
                moves_.add(k.getKey());
            }
        }
        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return moves_;
    }

    private boolean excludeByAccuracy(MoveData _move) {
        if (Rate.isValid(getMinAccuracy())) {
            String accuraryStr_ = _move.getAccuracy();
            if (!Rate.isValid(accuraryStr_) || !Rate.greaterEq(new Rate(accuraryStr_), new Rate(getMinAccuracy()))) {
                return true;
            }
        }
        if (Rate.isValid(getMaxAccuracy())) {
            String accuraryStr_ = _move.getAccuracy();
            return Rate.isValid(accuraryStr_) && !Rate.lowerEq(new Rate(accuraryStr_), new Rate(getMaxAccuracy()));
        }
        return false;
    }
    private boolean excludeByPower(MoveData _move) {
        if (Rate.isValid(getMinPower())) {
            if (!(_move instanceof DamagingMoveData)) {
                return true;
            }
            DamagingMoveData damage_ = (DamagingMoveData) _move;
            EffectDamage eff_ = (EffectDamage) damage_.getEffet(damage_.indexOfPrimaryEffect());
            Rate power_ = new Rate(getMinPower());
            if (!power_.isZeroOrLt() && (!Rate.isValid(eff_.getPower()) || !Rate.greaterEq(new Rate(eff_.getPower()), power_))) {
                return true;
            }
        }
        if (Rate.isValid(getMaxPower()) && _move instanceof DamagingMoveData) {
            DamagingMoveData damage_ = (DamagingMoveData) _move;
            EffectDamage eff_ = (EffectDamage) damage_.getEffet(damage_.indexOfPrimaryEffect());
            Rate power_ = new Rate(getMaxPower());
            return Rate.isValid(eff_.getPower()) && !Rate.lowerEq(new Rate(eff_.getPower()), power_);
        }
        return false;
    }
    protected StringList sortedAbilities() {
        StringList sortedAbilities_;
        sortedAbilities_ = new StringList();
        DataBase data_ = getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        for (String i: data_.getAbilities().getKeys()) {
            String ab_ = translationsAbilities_.getVal(i);
            if (StringUtil.match(ab_, getTypedAbility())) {
                sortedAbilities_.add(i);
            }
        }
        sortedAbilities_.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        return sortedAbilities_;
    }

    protected boolean atLeastMatchType(StringMap<String> _translationsTypes, StringList _types) {
        return PokedexBean.atLeastMatchType(_translationsTypes,getWholeWord(),getTypedType(),_types);
    }
    protected StringList sortedItems(DataBase _data) {
        return SelectItemBean.sortedItems(_data,getTypedPrice(),getTypedName(),getTypedClass(),getLanguage());
    }
    public String getTrSortedAbility(int _index) {
        String ability_ = sortedAbilities.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translationsAbilities_.getVal(ability_);
    }
    public void setTypedAbility(String _typedAbility) {
        typedAbility = _typedAbility;
    }

    public String getTypedAbility() {
        return typedAbility;
    }

    public void setTypedName(String _typedName) {
        typedName = _typedName;
    }

    public String getTypedName() {
        return typedName;
    }

    public void setTypedPrice(String _typedPrice) {
        typedPrice = _typedPrice;
    }

    public String getTypedPrice() {
        return typedPrice;
    }

    public void setTypedClass(String _typedClass) {
        typedClass = _typedClass;
    }

    public String getTypedClass() {
        return typedClass;
    }

    public void setTypedType(String _typedType) {
        typedType = _typedType;
    }

    public String getTypedType() {
        return typedType;
    }

    public void setTypedStatus(String _typedStatus) {
        typedStatus = _typedStatus;
    }

    public String getTypedStatus() {
        return typedStatus;
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

    public DictionaryComparator<String,String> getBooleans() {
        return booleans;
    }

    public void setBooleans(DictionaryComparator<String, String> _b) {
        this.booleans = _b;
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

    public void setMinAccuracy(String _minAccuracy) {
        minAccuracy = _minAccuracy;
    }

    public String getMinAccuracy() {
        return minAccuracy;
    }

    public void setMaxAccuracy(String _maxAccuracy) {
        maxAccuracy = _maxAccuracy;
    }

    public String getMaxAccuracy() {
        return maxAccuracy;
    }

    public void setMinPower(String _minPower) {
        minPower = _minPower;
    }

    public String getMinPower() {
        return minPower;
    }

    public void setMaxPower(String _maxPower) {
        maxPower = _maxPower;
    }

    public String getMaxPower() {
        return maxPower;
    }

    public StringList getSortedAbilities() {
        return sortedAbilities;
    }

    public void setSortedAbilities(StringList _ab) {
        this.sortedAbilities = _ab;
    }

    public String getTypedCategory() {
        return typedCategory;
    }

    public void setTypedCategory(String _c) {
        this.typedCategory = _c;
    }
    public CustList<PokemonLine> getPokedex() {
        return pokedex;
    }

}