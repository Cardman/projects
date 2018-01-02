package cards.tarot.enumerations;
import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

/**Prime pour le chelem au tarot*/
public enum BonusTarot implements Displayable {
    SLAM(400),
    SMALL_BOUND(10);
    private final int points;
    BonusTarot(int _points){
        points = _points;
    }
    public int getPoints() {
        return points;
    }
    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_BONUS,name());
    }
}
