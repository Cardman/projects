package code.format;
import code.resources.ResourceFiles;
import code.sml.DocumentBuilder;
import code.util.StringList;
import code.util.StringMap;

/**Classe gerant l'affichage des constantes enumerees notamment pour l'encodage et le formattage de chaines de caracteres*/
public final class Format {

    public static final String RETURNE_LINE = "\n";
    public static final String SEPARATOR = ":";

    private static final String PROPERTIES = "properties";

    private static final char DOT_CHAR = '.';

    private static final String DOT = ".";

    private Format(){}
    public static String getConstanteLangue(String _dossier,String _fichier, String _loc, String _group ,String _nomConstante) {
        return getConstanteLangue(_dossier, _fichier, _loc, StringList.concat(_group,Format.DOT,_nomConstante));
    }
    public static String getConstanteLangue(String _dossier,String _fichier, String _loc,String _nomConstante) {
        StringMap<String> constantes_ = new StringMap<String>();
        String fichier_ = ResourceFiles.ressourceFichier(StringList.concat(_dossier,ResourceFiles.SEPARATEUR,_loc,ResourceFiles.SEPARATEUR,_fichier));
        for(String line_: StringList.splitStrings(fichier_, RETURNE_LINE)){
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

    /**@see getConstante
    @param _dossier chemin de la suite de dossiers du jar ou se trouve le fichier
    @param _fichier nom du fichier dans lequel se trouve la constante a chercher
    @param _nomConstante nom de la constante en rapport avec la chaine de caracteres
    @param _variables liste de parametres remplacant les expressions comme {0} {1}...
    @return la chaine de caracteres retournee par la methode getConstante qui est formatte avec _variables*/
    public static String formatter(String _dossier,String _fichier, String _loc,String _nomConstante,String... _variables){
        String constante_ = getConstanteLangue(_dossier,_fichier, _loc,_nomConstante);
        return StringList.simpleStringsFormat(constante_, _variables);
    }

    public static String getClassProperties(String _class) {
        return StringList.concat(StringList.replace(_class, DOT, ResourceFiles.SEPARATEUR),DOT,PROPERTIES);
    }

    public static String keepOnlyWordCharsDot(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            if (!StringList.isWordChar(c)) {
                if (c != DOT_CHAR) {
                    continue;
                }
            }
            str_.append(c);
        }
        return str_.toString();
    }
}
