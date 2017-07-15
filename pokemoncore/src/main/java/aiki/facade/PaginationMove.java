package aiki.facade;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;
import code.util.pagination.EnumFieldComparator;
import code.util.pagination.FieldComparator;
import code.util.pagination.Pagination;
import aiki.DataBase;
import aiki.comparators.ComparatorMove;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.enums.TargetChoice;
import aiki.map.pokemon.CriteriaForSearchingMove;
import aiki.util.SortingMove;

public class PaginationMove extends Pagination<SortingMove, String, CriteriaForSearchingMove> {

    private FieldComparator<String> cmpName = new FieldComparator<String>();

    private FieldComparator<Integer> cmpPrice = new FieldComparator<Integer>();

    private FieldComparator<Integer> cmpDescription = new FieldComparator<Integer>();

    private FieldComparator<Short> cmpPpp = new FieldComparator<Short>();

    private FieldComparator<Byte> cmpPrio = new FieldComparator<Byte>();

    private EnumFieldComparator<TargetChoice> cmpTargetChoice = new EnumFieldComparator<TargetChoice>();

    private StringMap<String> translatedMove;

    private StringMap<String> translatedType;

    private StringMap<Integer> translatedDescription;

    private final int nbComparators = 6;

    private boolean enabledTechnicalMove;

    private TreeMap<SortingMove, String> moves = new TreeMap<SortingMove, String>(new ComparatorMove());

    private EqList<SortingMove> rendered = new EqList<SortingMove>();

    public PaginationMove() {
        super(new CriteriaForSearchingMove());
    }

    public void setTranslation(DataBase _data, String _language) {
        cmpTargetChoice.setTranslations(_data.getTranslatedTargets().getVal(_language));
        translatedMove = _data.getTranslatedMoves().getVal(_language);
        translatedType = _data.getTranslatedTypes().getVal(_language);
        translatedDescription = new StringMap<Integer>();
        translatedDescription.put(DamagingMoveData.MOVE, translatedDescription.size());
        translatedDescription.put(StatusMoveData.MOVE, translatedDescription.size());
    }

    public void search(Listable<String> _list,DataBase _data) {
        moves.clear();
        int len_ = _list.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            MoveData i_ = _data.getMove(_list.get(i));
            int price_ = 0;
//            CustList<Short> tmKeys_ = _data.getTm().getKeys(_list.get(i));
            Numbers<Short> tmKeys_ = _data.getTmByMove(_list.get(i));
            if (!tmKeys_.isEmpty()) {
                short tm_ = tmKeys_.first();
                if (_data.getTmPrice().contains(tm_)) {
                    price_ = (int) _data.getTmPrice().getVal(tm_).ll();
                }
            }
            /*if (!getCriteria().matchTechnicalMove(!tmKeys_.isEmpty())) {
                continue;
            }*/
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
            /*if (!getCriteria().matchSwitchType(i_.getSwitchType())) {
                continue;
            }*/
            if (!getCriteria().matchPrio(i_.getPriority())) {
                continue;
            }
            StringList types_ = new StringList();
            for (String t: i_.getTypes()) {
                types_.add(translatedType.getVal(t));
            }
            if (!getCriteria().matchTypes(types_)) {
                continue;
            }
            /*if (!getCriteria().matchDisappearingRound(i_.getDisappearBeforeUse())) {
                continue;
            }
            if (!getCriteria().matchRechargeRound(i_.getRechargeRound())) {
                continue;
            }
            if (!getCriteria().matchConstUserChoice(i_.getConstUserChoice())) {
                continue;
            }*/
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

//    @Override
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
//        TreeMap<SortingMove, String> items_ = new TreeMap<new>(new Comparator<SortingMove>() {
//            @Override
//            public int compare(SortingMove _o1, SortingMove _o2) {
//                for (int i = nbComparators; i >= MIN_PRIORITY; i--) {
//                    if (cmpPrice.getPriority() == i) {
//                        int res_ = cmpPrice.compare(_o1.getPrice(), _o2.getPrice());
//                        if (res_ != EQUALS_ELEMENTS) {
//                            return res_;
//                        }
//                    } else if (cmpPpp.getPriority() == i) {
//                        int res_ = cmpPpp.compare(_o1.getPp(), _o2.getPp());
//                        if (res_ != EQUALS_ELEMENTS) {
//                            return res_;
//                        }
//                    } else if (cmpPrio.getPriority() == i) {
//                        int res_ = cmpPrio.compare(_o1.getPriority(), _o2.getPriority());
//                        if (res_ != EQUALS_ELEMENTS) {
//                            return res_;
//                        }
//                    } else if (cmpTargetChoice.getPriority() == i) {
//                        int res_ = cmpTargetChoice.compare(_o1.getTargetChoice(), _o2.getTargetChoice());
//                        if (res_ != EQUALS_ELEMENTS) {
//                            return res_;
//                        }
//                    } else if (cmpName.getPriority() == i) {
//                        int res_ = cmpName.compare(_o1.getName(), _o2.getName());
//                        if (res_ != EQUALS_ELEMENTS) {
//                            return res_;
//                        }
//                    } else if (cmpDescription.getPriority() == i) {
//                        int res_ = cmpDescription.compare(_o1.getMoveClass(), _o2.getMoveClass());
//                        if (res_ != EQUALS_ELEMENTS) {
//                            return res_;
//                        }
//                    }
//                }
//                return Integer.compare(_o1.getIndex(), _o2.getIndex());
//            }
//        });
        TreeMap<SortingMove, String> items_ = new TreeMap<SortingMove, String>(new ComparatorMove(cmpName, cmpPrice, cmpDescription, cmpPpp, cmpPrio, cmpTargetChoice, nbComparators));
        items_.putAllTreeMap(moves);
        moves = items_;
    }

    public FieldComparator<String> getCmpName() {
        return cmpName;
    }

    public void setCmpName(FieldComparator<String> _cmpName) {
        cmpName = _cmpName;
    }

    public FieldComparator<Integer> getCmpPrice() {
        return cmpPrice;
    }

    public void setCmpPrice(FieldComparator<Integer> _cmpPrice) {
        cmpPrice = _cmpPrice;
    }

    public FieldComparator<Integer> getCmpDescription() {
        return cmpDescription;
    }

    public void setCmpDescription(FieldComparator<Integer> _cmpDescription) {
        cmpDescription = _cmpDescription;
    }

    public FieldComparator<Short> getCmpPpp() {
        return cmpPpp;
    }

    public void setCmpPpp(FieldComparator<Short> _cmpPpp) {
        cmpPpp = _cmpPpp;
    }

    public FieldComparator<Byte> getCmpPrio() {
        return cmpPrio;
    }

    public void setCmpPrio(FieldComparator<Byte> _cmpPrio) {
        cmpPrio = _cmpPrio;
    }

    public EnumFieldComparator<TargetChoice> getCmpTargetChoice() {
        return cmpTargetChoice;
    }

    public void setCmpTargetChoice(EnumFieldComparator<TargetChoice> _cmpTargetChoice) {
        cmpTargetChoice = _cmpTargetChoice;
    }

    public boolean isEnabledTechnicalMove() {
        return enabledTechnicalMove;
    }

    public void setEnabledTechnicalMove(boolean _enabledTechnicalMove) {
        enabledTechnicalMove = _enabledTechnicalMove;
    }

    public int getNbComparators() {
        return nbComparators;
    }

    @Override
    protected TreeMap<SortingMove, String> getResults() {
        return moves;
    }

    @Override
    protected EqList<SortingMove> getRendered() {
        return rendered;
    }

}
