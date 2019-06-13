package aiki.comparators;
import aiki.facade.EnumFieldComparator;
import aiki.facade.LongFieldComparator;
import aiki.facade.Pagination;
import aiki.facade.StringFieldComparator;
import aiki.map.pokemon.enums.Gender;
import aiki.util.SortingPokemonPlayer;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorPokemonPlayer implements
        Comparing<SortingPokemonPlayer> {

    private LongFieldComparator cmpLevel = new LongFieldComparator();

    private StringFieldComparator cmpName = new StringFieldComparator();

    private StringFieldComparator cmpAbility = new StringFieldComparator();

    private StringFieldComparator cmpItem = new StringFieldComparator();

    private EnumFieldComparator<Gender> cmpGender = new EnumFieldComparator<Gender>();

    private LongFieldComparator cmpPossEvos = new LongFieldComparator();

    private final int nbComparators;

    public ComparatorPokemonPlayer() {
        nbComparators = 0;
    }

    public ComparatorPokemonPlayer(LongFieldComparator _cmpLevel,
            StringFieldComparator _cmpName,
            StringFieldComparator _cmpAbility,
            StringFieldComparator _cmpItem,
            EnumFieldComparator<Gender> _cmpGender,
            LongFieldComparator _cmpPossEvos, int _nbComparators) {
        cmpLevel = _cmpLevel;
        cmpName = _cmpName;
        cmpAbility = _cmpAbility;
        cmpItem = _cmpItem;
        cmpGender = _cmpGender;
        cmpPossEvos = _cmpPossEvos;
        nbComparators = _nbComparators;
    }

    @Override
    public int compare(SortingPokemonPlayer _o1, SortingPokemonPlayer _o2) {
        for (int i = nbComparators; i >= Pagination.MIN_PRIORITY; i--) {
            if (cmpLevel.getPriority() == i) {
                int res_ = cmpLevel.compare(_o1.getLevel(), _o2.getLevel());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpName.getPriority() == i) {
                int res_ = cmpName.compare(_o1.getName(), _o2.getName());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpAbility.getPriority() == i) {
                int res_ = cmpAbility.compare(_o1.getAbility(), _o2.getAbility());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpItem.getPriority() == i) {
                int res_ = cmpItem.compare(_o1.getItem(), _o2.getItem());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpGender.getPriority() == i) {
                int res_ = cmpGender.compare(_o1.getGender(), _o2.getGender());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpPossEvos.getPriority() == i) {
                int res_ = cmpPossEvos.compare(_o1.getNbPossEvos(), _o2.getNbPossEvos());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            }
        }
        return Numbers.compareLg(_o1.getIndex(), _o2.getIndex());
    }

}
