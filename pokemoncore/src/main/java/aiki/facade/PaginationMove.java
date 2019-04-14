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
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;

public final class PaginationMove extends
        Pagination<SortingMove, String> {

    private StringFieldComparator cmpName = new StringFieldComparator();

    private LongFieldComparator cmpPrice = new LongFieldComparator();

    private LongFieldComparator cmpDescription = new LongFieldComparator();

    private LongFieldComparator cmpPpp = new LongFieldComparator();

    private LongFieldComparator cmpPrio = new LongFieldComparator();

    private EnumFieldComparator<TargetChoice> cmpTargetChoice = new EnumFieldComparator<TargetChoice>();

    private StringMap<String> translatedMove;

    private StringMap<String> translatedType;

    private StringMap<Integer> translatedDescription;

    private final int nbComparators = 6;

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

    public void search(Listable<String> _list, DataBase _data) {
        moves.clear();
        int len_ = _list.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            MoveData i_ = _data.getMove(_list.get(i));
            int price_ = 0;
            // CustList<Short> tmKeys_ = _data.getTm().getKeys(_list.get(i));
            Numbers<Short> tmKeys_ = _data.getTmByMove(_list.get(i));
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
            setNumberPage(CustList.FIRST_INDEX);
        } else {
            setLine(CustList.INDEX_NOT_FOUND_ELT);
            setNumberPage(CustList.INDEX_NOT_FOUND_ELT);
            getRendered().clear();
            return;
        }
        setLine(CustList.INDEX_NOT_FOUND_ELT);
        if (sortable()) {
            sort();
        }
        calculateRendered();
    }

    @Override
    protected boolean match(String _move) {
        if (!getCriteria().matchName(_move)) {
            return false;
        }
        return true;
    }
    @Override
    public CriteriaForSearchingMove getCriteria() {
        return criteria;
    }

    @Override
    protected boolean sortable() {
        Numbers<Integer> priorities_;
        priorities_ = new Numbers<Integer>();
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
        int size_ = priorities_.size();
        priorities_.removeDuplicates();
        return size_ == priorities_.size();
    }

    @Override
    protected void sort() {
        TreeMap<SortingMove, String> items_ = new TreeMap<SortingMove, String>(
                new ComparatorMove(cmpName, cmpPrice, cmpDescription, cmpPpp,
                        cmpPrio, cmpTargetChoice, nbComparators));
        items_.putAllTreeMap(moves);
        moves = items_;
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

    public int getNbComparators() {
        return nbComparators;
    }

    @Override
    protected TreeMap<SortingMove, String> getResults() {
        return moves;
    }

    @Override
    protected CustList<SortingMove> getRendered() {
        return rendered;
    }

}
