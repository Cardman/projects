package aiki.facade;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.pagination.EnumFieldComparator;
import code.util.pagination.FieldComparator;
import code.util.pagination.Pagination;
import aiki.DataBase;
import aiki.comparators.ComparatorPokemonPlayer;
import aiki.map.pokemon.CriteriaForSearchingPokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.enums.Gender;
import aiki.util.SortingPokemonPlayer;

public final class PaginationPokemonPlayer extends Pagination<SortingPokemonPlayer,PokemonPlayer,CriteriaForSearchingPokemon> {

    private FieldComparator<Short> cmpLevel = new FieldComparator<Short>();

    private FieldComparator<String> cmpName = new FieldComparator<String>();

    private FieldComparator<String> cmpAbility = new FieldComparator<String>();

    private FieldComparator<String> cmpItem = new FieldComparator<String>();

    private EnumFieldComparator<Gender> cmpGender = new EnumFieldComparator<Gender>();

    private FieldComparator<Short> cmpPossEvos = new FieldComparator<Short>();

    private DataBase data;

    private StringMap<String> translatedPokemon;

    private StringMap<String> translatedMoves;

    private StringMap<String> translatedItems;

    private StringMap<String> translatedAbilities;

    private final int nbComparators = 6;

    private TreeMap<SortingPokemonPlayer, PokemonPlayer> pokemon = new TreeMap<SortingPokemonPlayer, PokemonPlayer>(new ComparatorPokemonPlayer());

    private EqList<SortingPokemonPlayer> rendered = new EqList<SortingPokemonPlayer>();

    public PaginationPokemonPlayer() {
        super(new CriteriaForSearchingPokemon());
    }

    public void setTranslation(DataBase _data, String _language) {
        data = _data;
        cmpGender.setTranslations(_data.getTranslatedGenders().getVal(_language));
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
            s_.setKeyAbility(pk_.getAbility());
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

    @Override
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
        if (!getCriteria().matchAbility(translatedAbilities.getVal(_pk.getAbility()))) {
            return false;
        }
        if (!getCriteria().matchGender(_pk.getGender())) {
            return false;
        }
        StringList list_ = new StringList();
        for (String m: _pk.getMoves().getKeys()) {
            list_.add(translatedMoves.getVal(m));
        }
        list_.removeDuplicates();
        if (!getCriteria().matchMoves(list_)) {
            return false;
        }
        if (!getCriteria().matchNbPossEvos(_pk.getDirectEvolutions(data).size())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sortable() {
        Numbers<Integer> priorities_;
        priorities_ = new Numbers<Integer>();
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
        int size_ = priorities_.size();
        priorities_.removeDuplicates();
        return size_ == priorities_.size();
    }

    @Override
    public void sort() {
//        TreeMap<SortingPokemonPlayer, PokemonPlayer> eggs_ = new TreeMap<new>(new Comparator<SortingPokemonPlayer>() {
//            @Override
//            public int compare(SortingPokemonPlayer _o1, SortingPokemonPlayer _o2) {
//                for (int i = nbComparators; i >= MIN_PRIORITY; i--) {
//                    if (cmpLevel.getPriority() == i) {
//                        int res_ = cmpLevel.compare(_o1.getLevel(), _o2.getLevel());
//                        if (res_ != EQUALS_ELEMENTS) {
//                            return res_;
//                        }
//                    } else if (cmpName.getPriority() == i) {
//                        int res_ = cmpName.compare(_o1.getName(), _o2.getName());
//                        if (res_ != EQUALS_ELEMENTS) {
//                            return res_;
//                        }
//                    } else if (cmpAbility.getPriority() == i) {
//                        int res_ = cmpAbility.compare(_o1.getAbility(), _o2.getAbility());
//                        if (res_ != EQUALS_ELEMENTS) {
//                            return res_;
//                        }
//                    } else if (cmpItem.getPriority() == i) {
//                        int res_ = cmpItem.compare(_o1.getItem(), _o2.getItem());
//                        if (res_ != EQUALS_ELEMENTS) {
//                            return res_;
//                        }
//                    } else if (cmpGender.getPriority() == i) {
//                        int res_ = cmpGender.compare(_o1.getGender(), _o2.getGender());
//                        if (res_ != EQUALS_ELEMENTS) {
//                            return res_;
//                        }
//                    }
//                }
//                return Integer.compare(_o1.getIndex(), _o2.getIndex());
//            }
//        });
        TreeMap<SortingPokemonPlayer, PokemonPlayer> eggs_ = new TreeMap<SortingPokemonPlayer, PokemonPlayer>(new ComparatorPokemonPlayer(cmpLevel, cmpName, cmpAbility, cmpItem, cmpGender, cmpPossEvos, nbComparators));
        eggs_.putAllTreeMap(pokemon);
        pokemon = eggs_;
    }

    public FieldComparator<Short> getCmpLevel() {
        return cmpLevel;
    }

    public void setCmpLevel(FieldComparator<Short> _cmpLevel) {
        cmpLevel = _cmpLevel;
    }

    public FieldComparator<String> getCmpName() {
        return cmpName;
    }

    public void setCmpName(FieldComparator<String> _cmpName) {
        cmpName = _cmpName;
    }

    public FieldComparator<String> getCmpAbility() {
        return cmpAbility;
    }

    public void setCmpAbility(FieldComparator<String> _cmpAbility) {
        cmpAbility = _cmpAbility;
    }

    public FieldComparator<String> getCmpItem() {
        return cmpItem;
    }

    public void setCmpItem(FieldComparator<String> _cmpItem) {
        cmpItem = _cmpItem;
    }

    public EnumFieldComparator<Gender> getCmpGender() {
        return cmpGender;
    }

    public void setCmpGender(EnumFieldComparator<Gender> _cmpGender) {
        cmpGender = _cmpGender;
    }

    public FieldComparator<Short> getCmpPossEvos() {
        return cmpPossEvos;
    }

    public void setCmpPossEvos(FieldComparator<Short> _cmpPossEvos) {
        cmpPossEvos = _cmpPossEvos;
    }

    @Override
    public EqList<SortingPokemonPlayer> getRendered() {
        return rendered;
    }

    public void setRendered(EqList<SortingPokemonPlayer> _rendered) {
        rendered = _rendered;
    }

    @Override
    protected TreeMap<SortingPokemonPlayer, PokemonPlayer> getResults() {
        return pokemon;
    }

    public int getNbComparators() {
        return nbComparators;
    }
}
