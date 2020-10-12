package code.format;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import code.util.core.StringUtil;

/**Classe gerant l'affichage des constantes enumerees notamment pour l'encodage et le formattage de chaines de caracteres*/
public final class Format {

    private static final String DOT = ".";

    private static final String RETURNE_LINE = "\n";
    private static final String SEPARATOR = ":";

    private static final char DOT_CHAR = '.';

    private Format(){}

    public static String getConstanteLangue(String fichier_,String _group ,String _nomConstante) {
        return getConstanteLangue(concatParts(_group, _nomConstante),fichier_);
    }

    public static String concatParts(String _group, String _nomConstante) {
        return StringUtil.concat(_group,Format.DOT,_nomConstante);
    }

    public static String getConstanteLangue(String _nomConstante, String fichier_) {
        StringMap<String> constantes_ = new StringMap<String>();
        for(String line_: StringUtil.splitStrings(fichier_, RETURNE_LINE)){
            if(!line_.contains(SEPARATOR)){
                continue;
            }
            int indice_ = line_.indexOf(SEPARATOR);
            String cle_ = line_.substring(0, indice_);
//            cle_ = cle_.replaceAll(NO_WORD_DOT, EMPTY_STRING);
            cle_ = keepOnlyWordCharsDot(cle_);
            String valeur_ = line_.substring(indice_+1);
            valeur_ = DocumentBuilder.transformSpecialChars(valeur_);
            constantes_.put(cle_, valeur_);
        }
        return constantes_.getVal(_nomConstante);
    }

    public static String keepOnlyWordCharsDot(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            if (!StringUtil.isWordChar(c)) {
                if (c != DOT_CHAR) {
                    continue;
                }
            }
            str_.append(c);
        }
        return str_.toString();
    }
}
