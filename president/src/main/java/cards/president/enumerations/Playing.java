package cards.president.enumerations;
import code.format.Format;
import code.util.consts.Constants;

public enum Playing {
    CAN_PLAY,SKIPPED,HAS_TO_EQUAL,PASS,FINISH,DO_NOT_EQUAL;

    @Override
    public String toString() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.PRESIDENT_PLAY,name());
    }
}
