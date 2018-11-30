package cards.tarot.comparators;
import cards.tarot.enumerations.Handfuls;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class AllowedHandfulDefaultComparator implements Comparing<Handfuls> {

    private int nbCardsPerPlayer;

    public AllowedHandfulDefaultComparator(int _nbCardsPerPlayer) {
        nbCardsPerPlayer = _nbCardsPerPlayer;
    }

    @Override
    public int compare(Handfuls _o1, Handfuls _o2) {
        NumberMap<Integer,Integer> conf1_ = Handfuls.getConfigurationParDefautAnnoncePoignee(_o1);
        int nb1_ = conf1_.getVal(nbCardsPerPlayer);
        NumberMap<Integer,Integer> conf2_ = Handfuls.getConfigurationParDefautAnnoncePoignee(_o2);
        int nb2_ = conf2_.getVal(nbCardsPerPlayer);
        return Numbers.compare(nb1_, nb2_);
    }

}
