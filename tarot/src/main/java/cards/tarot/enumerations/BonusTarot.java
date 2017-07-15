package cards.tarot.enumerations;
import code.format.Format;
import code.util.consts.Constants;

/**Prime pour le chelem au tarot*/
public enum BonusTarot {
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
    public String toString() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_BONUS,this);
    }
}
