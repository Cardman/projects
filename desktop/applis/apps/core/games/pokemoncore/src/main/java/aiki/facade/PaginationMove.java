package aiki.facade;

import aiki.comparators.ComparatorMove;
import aiki.db.DataBase;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.enums.TargetChoice;
import aiki.map.pokemon.CriteriaForSearchingMove;
import aiki.util.SortingMove;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.core.IndexConstants;
import code.util.ints.Listable;

public final class PaginationMove extends
        Pagination {

    public static final int NB_CMPARATORS = 6;

    private StringFieldComparator cmpName = new StringFieldComparator();

    private LongFieldComparator cmpPrice = new LongFieldComparator();

    private LongFieldComparator cmpDescription = new LongFieldComparator();

    private LongFieldComparator cmpPpp = new LongFieldComparator();

    private LongFieldComparator cmpPrio = new LongFieldComparator();

    private EnumFieldComparator<TargetChoice> cmpTargetChoice = new EnumFieldComparator<TargetChoice>();

    private StringMap<String> translatedMove;

    private StringMap<String> translatedType;

    private StringMap<Integer> translatedDescription;

    private TreeMap<SortingMove, String> moves = new TreeMap<SortingMove, String>(
            new ComparatorMove());

    private CustList<SortingMove> rendered = new CustList<SortingMove>();

    private CriteriaForSearchingMove criteria;

    public PaginationMove() {
        criteria = new CriteriaForSearchingMove();
    }

    public void setTranslation(DataBase _data, String _language) {
        cmpTargetChoice.setTranslations(_data.getTranslatedTargets().getVal(
                _language));
        translatedMove = _data.getTranslatedMoves().getVal(_language);
        translatedType = _data.getTranslatedTypes().getVal(_language);
        translatedDescription = new StringMap<Integer>();
        translatedDescription.put(DamagingMoveData.MOVE,
                translatedDescription.size());
        translatedDescription.put(StatusMoveData.MOVE,
                translatedDescription.size());
    }

    public void search(CustList<String> _list, DataBase _data) {
        moves.clear();
        int len_ = _list.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            MoveData i_ = _data.getMove(_list.get(i));
            int price_ = 0;
            // CustList<Short> tmKeys_ = _data.getTm().getKeys(_list.get(i));
            Shorts tmKeys_ = _data.getTmByMove(_list.get(i));
            if (!tmKeys_.isEmpty()) {
                short tm_ = tmKeys_.first();
                if (_data.getTmPrice().contains(tm_)) {
                    price_ = (int) _data.getTmPrice().getVal(tm_).ll();
                }
            }
            if (!getCriteria().matchPrice(price_)) {
                continue;
            }
            if (!getCriteria().matchClass(i_)) {
                continue;
            }
            if (!getCriteria().matchPp(i_.getPp())) {
                continue;
            }
            if (!getCriteria().matchTargetChoice(i_.getTargetChoice())) {
                continue;
            }
            if (!getCriteria().matchPrio(i_.getPriority())) {
                continue;
            }
            StringList types_ = new StringList();
            for (String t : i_.getTypes()) {
                types_.add(translatedType.getVal(t));
            }
            if (!getCriteria().matchTypes(types_)) {
                continue;
            }
            if (!match(translatedMove.getVal(_list.get(i)))) {
                continue;
            }
            SortingMove s_ = new SortingMove();
            s_.setIndex(i);
            s_.setKeyName(_list.get(i));
            s_.setName(translatedMove.getVal(_list.get(i)));
            s_.setPrice(price_);
            s_.setPp(i_.getPp());
            s_.setPriority(i_.getPriority());
            s_.setTargetChoice(i_.getTargetChoice());
            s_.setMoveClass(translatedDescription.getVal(i_.getMoveType()));
            moves.put(s_, _list.get(i));
        }
        search(new StringList(moves.values()));
    }

    protected void search(Listable<String> _list) {
        if (!_list.isEmpty()) {
            setNumberPage(IndexConstants.FIRST_INDEX);
        } else {
            setLine(IndexConstants.INDEX_NOT_FOUND_ELT);
            setNumberPage(IndexConstants.INDEX_NOT_FOUND_ELT);
            getRendered().clear();
            return;
        }
        setLine(IndexConstants.INDEX_NOT_FOUND_ELT);
        if (sortable()) {
            sort();
        }
        calculateRendered();
    }

    protected boolean match(String _move) {
        return getCriteria().matchName(_move);
    }

    public CriteriaForSearchingMove getCriteria() {
        return criteria;
    }

    protected void clearRendered() {
        getRendered().clear();
    }
    @Override
    protected boolean sortable() {
        Ints priorities_;
        priorities_ = new Ints();
        if (cmpPrice.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpPrice.getPriority());
        }
        if (cmpName.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpName.getPriority());
        }
        if (cmpDescription.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpDescription.getPriority());
        }
        if (cmpPpp.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpPpp.getPriority());
        }
        if (cmpPrio.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpPrio.getPriority());
        }
        if (cmpTargetChoice.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpTargetChoice.getPriority());
        }
        return !priorities_.hasDuplicates();
    }

    @Override
    protected void sort() {
        TreeMap<SortingMove, String> items_ = new TreeMap<SortingMove, String>(
                new ComparatorMove(cmpName, cmpPrice, cmpDescription, cmpPpp,
                        cmpPrio, cmpTargetChoice, NB_CMPARATORS));
        items_.putAllMap(moves);
        moves = items_;
    }

    public String currentObject() {
        int index_ = getIndex();
        if (!getResults().getKeys().isValidIndex(index_)) {
            return "";
        }
        return getResults().getValue(index_);
    }
    public StringFieldComparator getCmpName() {
        return cmpName;
    }

    public LongFieldComparator getCmpPrice() {
        return cmpPrice;
    }

    public LongFieldComparator getCmpDescription() {
        return cmpDescription;
    }

    public LongFieldComparator getCmpPpp() {
        return cmpPpp;
    }

    public LongFieldComparator getCmpPrio() {
        return cmpPrio;
    }

    public EnumFieldComparator<TargetChoice> getCmpTargetChoice() {
        return cmpTargetChoice;
    }
    protected void excludeResults() {
        Listable<SortingMove> list_ = getResults().getKeys();
        for (SortingMove k: list_) {
            String value_ = getResults().getVal(k);
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

    protected int getIndex(int index_) {
        return getResults().getKey(index_).getIndex();
    }

    protected boolean isValidIndex(int index_) {
        return getResults().getKeys().isValidIndex(index_);
    }

    protected TreeMap<SortingMove, String> getResults() {
        return moves;
    }

    protected CustList<SortingMove> getRendered() {
        return rendered;
    }

}
