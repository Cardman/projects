package cards.tarot.enumerations;
import code.format.Format;

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

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_BONUS,name());
    }
}
