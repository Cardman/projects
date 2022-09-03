package aiki.comparators;
import aiki.facade.*;
import aiki.map.pokemon.enums.Gender;
import aiki.util.SortingPokemonPlayer;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorPokemonPlayer implements
        Comparing<SortingPokemonPlayer> {

    private final LongFieldComparator cmpLevel = new LongFieldComparator();

    private final StringFieldComparator cmpName = new StringFieldComparator();

    private final StringFieldComparator cmpAbility = new StringFieldComparator();

    private final StringFieldComparator cmpItem = new StringFieldComparator();

    private final EnumFieldComparator<Gender> cmpGender = new EnumFieldComparator<Gender>();

    private final LongFieldComparator cmpPossEvos = new LongFieldComparator();

    @Override
    public int compare(SortingPokemonPlayer _o1, SortingPokemonPlayer _o2) {
        for (int i = PaginationPokemonPlayer.NB_COMPARATORS; i >= Pagination.MIN_PRIORITY; i--) {
            int res_ = res(_o1,_o2,i);
            if (res_ != SortConstants.EQ_CMP) {
                return res_;
            }
        }
        return NumberUtil.compareLg(_o1.getIndex(), _o2.getIndex());
    }
    private int res(SortingPokemonPlayer _o1, SortingPokemonPlayer _o2, int _i){
        if (cmpLevel.getPriority() == _i) {
            return cmpLevel.compare(_o1.getLevel(), _o2.getLevel());
        } else if (cmpName.getPriority() == _i) {
            return cmpName.compare(_o1.getName(), _o2.getName());
        } else if (cmpAbility.getPriority() == _i) {
            return cmpAbility.compare(_o1.getAbility(), _o2.getAbility());
        } else if (cmpItem.getPriority() == _i) {
            return cmpItem.compare(_o1.getItem(), _o2.getItem());
        } else if (cmpGender.getPriority() == _i) {
            return cmpGender.compare(_o1.getGender(), _o2.getGender());
        } else if (cmpPossEvos.getPriority() == _i) {
            return cmpPossEvos.compare(_o1.getNbPossEvos(), _o2.getNbPossEvos());
        }
        return SortConstants.EQ_CMP;
    }

    public StringFieldComparator getCmpName() {
        return cmpName;
    }

    public EnumFieldComparator<Gender> getCmpGender() {
        return cmpGender;
    }

    public LongFieldComparator getCmpLevel() {
        return cmpLevel;
    }

    public LongFieldComparator getCmpPossEvos() {
        return cmpPossEvos;
    }

    public StringFieldComparator getCmpAbility() {
        return cmpAbility;
    }

    public StringFieldComparator getCmpItem() {
        return cmpItem;
    }
}
