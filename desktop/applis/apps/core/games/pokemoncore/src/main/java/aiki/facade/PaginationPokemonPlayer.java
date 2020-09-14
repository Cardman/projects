package aiki.facade;

import aiki.comparators.ComparatorPokemonPlayer;
import aiki.db.DataBase;
import aiki.map.pokemon.CriteriaForSearchingPokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.enums.Gender;
import aiki.util.SortingPokemonPlayer;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;

public final class PaginationPokemonPlayer
        extends
        Pagination {

    public static final int NB_COMPARATORS = 6;

    private LongFieldComparator cmpLevel = new LongFieldComparator();

    private StringFieldComparator cmpName = new StringFieldComparator();

    private StringFieldComparator cmpAbility = new StringFieldComparator();

    private StringFieldComparator cmpItem = new StringFieldComparator();

    private EnumFieldComparator<Gender> cmpGender = new EnumFieldComparator<Gender>();

    private LongFieldComparator cmpPossEvos = new LongFieldComparator();

    private DataBase data;

    private StringMap<String> translatedPokemon;

    private StringMap<String> translatedMoves;

    private StringMap<String> translatedItems;

    private StringMap<String> translatedAbilities;

    private TreeMap<SortingPokemonPlayer, PokemonPlayer> pokemon = new TreeMap<SortingPokemonPlayer, PokemonPlayer>(
            new ComparatorPokemonPlayer());

    private CustList<SortingPokemonPlayer> rendered = new CustList<SortingPokemonPlayer>();

    private CriteriaForSearchingPokemon criteria;

    public PaginationPokemonPlayer() {
        criteria = new CriteriaForSearchingPokemon();
    }

    public void setTranslation(DataBase _data, String _language) {
        data = _data;
        cmpGender.setTranslations(_data.getTranslatedGenders()
                .getVal(_language));
        translatedPokemon = _data.getTranslatedPokemon().getVal(_language);
        translatedMoves = _data.getTranslatedMoves().getVal(_language);
        translatedItems = _data.getTranslatedItems().getVal(_language);
        translatedAbilities = _data.getTranslatedAbilities().getVal(_language);
    }

    public void search(CustList<UsablePokemon> _pokemon) {
        pokemon.clear();
        int len_ = _pokemon.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            UsablePokemon us_ = _pokemon.get(i);
            if (!(us_ instanceof PokemonPlayer)) {
                continue;
            }
            PokemonPlayer pk_ = (PokemonPlayer) us_;
            if (!match(pk_)) {
                continue;
            }
            SortingPokemonPlayer s_ = new SortingPokemonPlayer();
            s_.setIndex(i);
            s_.setName(translatedPokemon.getVal(pk_.getName()));
            s_.setKeyName(pk_.getName());
            s_.setAbility(translatedAbilities.getVal(pk_.getAbility()));
            if (!pk_.getItem().isEmpty()) {
                s_.setItem(translatedItems.getVal(pk_.getItem()));
            } else {
                s_.setItem(pk_.getItem());
            }
            s_.setKeyItem(pk_.getItem());
            s_.setLevel(pk_.getLevel());
            s_.setNbPossEvos((short) pk_.getDirectEvolutions(data).size());
            s_.setGender(pk_.getGender());
            pokemon.put(s_, pk_);
        }
        if (!pokemon.isEmpty()) {
            setNumberPage(CustList.FIRST_INDEX);
        } else {
            setLine(CustList.INDEX_NOT_FOUND_ELT);
            setNumberPage(CustList.INDEX_NOT_FOUND_ELT);
            rendered.clear();
            return;
        }
        setLine(CustList.INDEX_NOT_FOUND_ELT);
        if (sortable()) {
            sort();
        }
        calculateRendered();
    }

    protected boolean match(PokemonPlayer _pk) {
        if (!getCriteria().matchName(translatedPokemon.getVal(_pk.getName()))) {
            return false;
        }
        if (!getCriteria().matchLevel(_pk.getLevel())) {
            return false;
        }
        if (_pk.getItem().isEmpty()) {
            if (!getCriteria().matchItem(_pk.getItem())) {
                return false;
            }
        } else {
            if (!getCriteria().matchItem(translatedItems.getVal(_pk.getItem()))) {
                return false;
            }
        }
        if (!getCriteria().matchAbility(
                translatedAbilities.getVal(_pk.getAbility()))) {
            return false;
        }
        if (!getCriteria().matchGender(_pk.getGender())) {
            return false;
        }
        StringList list_ = new StringList();
        for (String m : _pk.getMoves().getKeys()) {
            list_.add(translatedMoves.getVal(m));
        }
        if (!getCriteria().matchMoves(list_)) {
            return false;
        }
        return getCriteria()
                .matchNbPossEvos(_pk.getDirectEvolutions(data).size());
    }

    public CriteriaForSearchingPokemon getCriteria() {
        return criteria;
    }

    protected void clearRendered() {
        getRendered().clear();
    }
    @Override
    protected boolean sortable() {
        Ints priorities_;
        priorities_ = new Ints();
        if (cmpLevel.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpLevel.getPriority());
        }
        if (cmpName.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpName.getPriority());
        }
        if (cmpAbility.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpAbility.getPriority());
        }
        if (cmpItem.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpItem.getPriority());
        }
        if (cmpGender.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpGender.getPriority());
        }
        if (cmpPossEvos.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpPossEvos.getPriority());
        }
        return !priorities_.hasDuplicates();
    }

    @Override
    protected void sort() {
        TreeMap<SortingPokemonPlayer, PokemonPlayer> eggs_ = new TreeMap<SortingPokemonPlayer, PokemonPlayer>(
                new ComparatorPokemonPlayer(cmpLevel, cmpName, cmpAbility,
                        cmpItem, cmpGender, cmpPossEvos, NB_COMPARATORS));
        eggs_.putAllMap(pokemon);
        pokemon = eggs_;
    }

    public PokemonPlayer currentObject() {
        int index_ = getIndex();
        if (!getResults().getKeys().isValidIndex(index_)) {
            return null;
        }
        return getResults().getValue(index_);
    }
    public LongFieldComparator getCmpLevel() {
        return cmpLevel;
    }

    public StringFieldComparator getCmpName() {
        return cmpName;
    }

    public StringFieldComparator getCmpAbility() {
        return cmpAbility;
    }

    public StringFieldComparator getCmpItem() {
        return cmpItem;
    }

    public EnumFieldComparator<Gender> getCmpGender() {
        return cmpGender;
    }

    public LongFieldComparator getCmpPossEvos() {
        return cmpPossEvos;
    }
    protected void excludeResults() {
        Listable<SortingPokemonPlayer> list_ = getResults().getKeys();
        for (SortingPokemonPlayer k: list_) {
            PokemonPlayer value_ = getResults().getVal(k);
            if (match(value_)) {
                continue;
            }
            getResults().removeKey(k);
        }
    }

    @Override
    protected boolean hasNoRendered() {
        return getRendered().isEmpty();
    }
    protected boolean hasNoResult() {
        return getResults().isEmpty();
    }
    protected void updateRendered(int end_) {
        getRendered().addAllElts(getResults().getKeys().sub(getFullCount(), end_));
    }
    protected void clearResults() {
        getResults().clear();
    }
    protected int getResultsSize() {
        return getResults().size();
    }

    protected CustList<SortingPokemonPlayer> getRendered() {
        return rendered;
    }

    protected int getIndex(int index_) {
        return getResults().getKey(index_).getIndex();
    }

    protected boolean isValidIndex(int index_) {
        return getResults().getKeys().isValidIndex(index_);
    }

    protected TreeMap<SortingPokemonPlayer, PokemonPlayer> getResults() {
        return pokemon;
    }

}
