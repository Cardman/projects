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

    private StringMap<String> translatedMove;

    private StringMap<String> translatedType;

    private StringMap<Integer> translatedDescription;

    private final ComparatorMove comparatorMove = new ComparatorMove();
    private TreeMap<SortingMove, String> moves = new TreeMap<SortingMove, String>(
            comparatorMove);

    private final CustList<SortingMove> rendered = new CustList<SortingMove>();

    private final CriteriaForSearchingMove criteria;

    public PaginationMove() {
        criteria = new CriteriaForSearchingMove();
    }

    public void setTranslation(DataBase _data, String _language) {
        comparatorMove.getCmpTargetChoice().setTranslations(_data.getTranslatedTargets().getVal(
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
            int price_ = price(_list, _data, i);
            if (!getCriteria().matchPrice(price_) || !getCriteria().matchClass(i_) || !getCriteria().matchPp(i_.getPp()) || !getCriteria().matchTargetChoice(i_.getTargetChoice()) || !getCriteria().matchPrio(i_.getPriority()) || !getCriteria().matchTypes(types(i_)) || !match(translatedMove.getVal(_list.get(i)))) {
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
            moves.addEntry(s_, _list.get(i));
        }
        search();
    }

    private StringList types(MoveData _i) {
        StringList types_ = new StringList();
        for (String t : _i.getTypes()) {
            types_.add(translatedType.getVal(t));
        }
        return types_;
    }

    private int price(CustList<String> _list, DataBase _data, int _i) {
        int price_ = 0;
        // CustList<Short> tmKeys_ = _data.getTm().getKeys(_list.get(i));
        Shorts tmKeys_ = _data.getTmByMove(_list.get(_i));
        if (!tmKeys_.isEmpty()) {
            short tm_ = tmKeys_.first();
            if (_data.getTmPrice().contains(tm_)) {
                price_ = (int) _data.getTmPrice().getVal(tm_).ll();
            }
        }
        return price_;
    }

    @Override
    protected boolean isEmpty() {
        return moves.isEmpty();
    }

    boolean match(String _move) {
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
        if (comparatorMove.getCmpPrice().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorMove.getCmpPrice().getPriority());
        }
        if (comparatorMove.getCmpName().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorMove.getCmpName().getPriority());
        }
        if (comparatorMove.getCmpDescription().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorMove.getCmpDescription().getPriority());
        }
        if (comparatorMove.getCmpPpp().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorMove.getCmpPpp().getPriority());
        }
        if (comparatorMove.getCmpPrio().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorMove.getCmpPrio().getPriority());
        }
        if (comparatorMove.getCmpTargetChoice().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorMove.getCmpTargetChoice().getPriority());
        }
        return !priorities_.hasDuplicates();
    }

    @Override
    protected void sort() {
        TreeMap<SortingMove, String> items_ = new TreeMap<SortingMove, String>(
                comparatorMove);
        items_.putAllMap(moves);
        moves = items_;
    }

    public String currentObject() {
        int index_ = getIndex();
        if (!getMoves().getKeys().isValidIndex(index_)) {
            return "";
        }
        return getMoves().getValue(index_);
    }
    public StringFieldComparator getCmpName() {
        return comparatorMove.getCmpName();
    }

    public LongFieldComparator getCmpPrice() {
        return comparatorMove.getCmpPrice();
    }

    public LongFieldComparator getCmpDescription() {
        return comparatorMove.getCmpDescription();
    }

    public LongFieldComparator getCmpPpp() {
        return comparatorMove.getCmpPpp();
    }

    public LongFieldComparator getCmpPrio() {
        return comparatorMove.getCmpPrio();
    }

    public EnumFieldComparator<TargetChoice> getCmpTargetChoice() {
        return comparatorMove.getCmpTargetChoice();
    }
    protected void excludeResults() {
        Listable<SortingMove> list_ = getMoves().getKeys();
        for (SortingMove k: list_) {
            String value_ = getMoves().getVal(k);
            if (match(value_)) {
                continue;
            }
            getMoves().removeKey(k);
        }
    }

    @Override
    protected boolean hasNoRendered() {
        return getRendered().isEmpty();
    }
    protected boolean hasNoResult() {
        return getMoves().isEmpty();
    }
    protected void updateRendered(int _end) {
        getRendered().addAllElts(getMoves().getKeys().sub(getFullCount(), _end));
    }
    protected void clearResults() {
        getMoves().clear();
    }
    protected int getResultsSize() {
        return getMoves().size();
    }

    protected int getIndex(int _index) {
        return getMoves().getKey(_index).getIndex();
    }

    protected boolean isValidIndex(int _index) {
        return getMoves().getKeys().isValidIndex(_index);
    }

    TreeMap<SortingMove, String> getMoves() {
        return moves;
    }

    CustList<SortingMove> getRendered() {
        return rendered;
    }

}
