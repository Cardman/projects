package cards.president.enumerations;
import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

public enum Playing implements Displayable {
    CAN_PLAY,SKIPPED,HAS_TO_EQUAL,PASS,FINISH,DO_NOT_EQUAL;

    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.PRESIDENT_PLAY,name());
    }
}
