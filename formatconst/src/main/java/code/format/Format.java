package code.format;
import code.resources.ResourceFiles;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;
import code.xml.XmlParser;

/**Classe gerant l'affichage des constantes enumerees notamment pour l'encodage et le formattage de chaines de caracteres*/
public final class Format {

    private static final String RETURNE_LINE = "\n";
    private static final String SEPARATOR = ":";

    private static final String PROPERTIES = "properties";

    private static final char DOT_CHAR = '.';

    private static final String DOT = ".";

//    private static final String EMPTY_STRING = "";

//    private static final String NO_WORD_DOT = "[^A-Za-z0-9_\\.]+";

    private static final StringMap<StringMap<String>> CONSTANTES_FICHIERS = new StringMap<StringMap<String>>();
    private Format(){}
    /** Cette methode retourne la chaine de caracteres associee au chemin relatif d'acces a un fichier et a une constante
    le fichier doit avoir le format suivant:<br/>
    nom_paquetage_enumeration.nom_enumeration:chaine_a_retourner<br/>
    Exemple:<br/>
    si un dossier ressources/bienvenue existe<br/>
    si un fichier messages.txt existe dans ce dossier<br/>
    si un paquatage enumerations.bienvenue existe<br/>
    si une enumeration MotBienvenue existe dans ce paquatage<br/>
    si une constante Bonjour dans cette enumeration existe<br/>
    si dans le fichier message.txt, une seule ligne valant enumerations.bienvenue.MotBienvenue:bonjour existe<br/>
    si le nom du dossier est passe en argument<br/>
    si le nom du fichier est passe en argument<br/>
    si le nom de la constante dans ce fichier est passe en argument<br/>
    alors bonjour est retourne<br/>
    @param _dossier chemin de la suite de dossiers du jar ou se trouve le fichier
    @param _fichier nom du fichier dans lequel se trouve la constante a chercher
    @param _constante nom de la constante en rapport avec la chaine de caracteres
    @return une chaine de caracteres provenant d'un fichier de ressource*/
    public static String getConstante(String _dossier,String _fichier,String _nomConstante) {
        if(!CONSTANTES_FICHIERS.contains(_dossier+StreamTextFile.SEPARATEUR+_fichier)){
            StringMap<String> constantes_ = new StringMap<String>();
            String fichier_ = ResourceFiles.ressourceFichier(_dossier+StreamTextFile.SEPARATEUR+ _fichier);
            for(String line_: StringList.splitStrings(fichier_,RETURNE_LINE)){
                if(!line_.contains(SEPARATOR)){
                    continue;
                }
                int indice_ = line_.indexOf(SEPARATOR);
                String cle_ = line_.substring(0, indice_);
//                cle_ = cle_.replaceAll(NO_WORD_DOT, EMPTY_STRING);
                cle_ = keepOnlyWordCharsDot(cle_);
                String valeur_ = line_.substring(indice_+1);
                valeur_ = XmlParser.transformSpecialChars(valeur_);
                constantes_.put(cle_, valeur_);
            }
            CONSTANTES_FICHIERS.put(_dossier+StreamTextFile.SEPARATEUR+_fichier, constantes_);
        }
        if(!CONSTANTES_FICHIERS.getVal(_dossier+StreamTextFile.SEPARATEUR+_fichier).contains(_nomConstante)){
            return _nomConstante.substring(_nomConstante.lastIndexOf(DOT)+1);
        }
        return CONSTANTES_FICHIERS.getVal(_dossier+StreamTextFile.SEPARATEUR+_fichier).getVal(_nomConstante);
    }

    public static String getConstanteLangue(String _dossier,String _fichier, String _loc, String _group ,Enum<?> _nomConstante) {
        return getConstanteLangue(_dossier, _fichier, _loc, _group+Format.DOT+_nomConstante.name());
    }
    public static String getConstanteLangue(String _dossier,String _fichier, String _loc,String _nomConstante) {
        if(!CONSTANTES_FICHIERS.contains(_dossier+StreamTextFile.SEPARATEUR+_loc+StreamTextFile.SEPARATEUR+_fichier)){
            StringMap<String> constantes_ = new StringMap<String>();
            String fichier_ = ResourceFiles.ressourceFichier(_dossier+StreamTextFile.SEPARATEUR+_loc+StreamTextFile.SEPARATEUR+ _fichier);
            for(String line_: StringList.splitStrings(fichier_, RETURNE_LINE)){
                if(!line_.contains(SEPARATOR)){
                    continue;
                }
                int indice_ = line_.indexOf(SEPARATOR);
                String cle_ = line_.substring(0, indice_);
//                cle_ = cle_.replaceAll(NO_WORD_DOT, EMPTY_STRING);
                cle_ = keepOnlyWordCharsDot(cle_);
                String valeur_ = line_.substring(indice_+1);
                valeur_ = XmlParser.transformSpecialChars(valeur_);
                constantes_.put(cle_, valeur_);
            }
            CONSTANTES_FICHIERS.put(_dossier+StreamTextFile.SEPARATEUR+_loc+StreamTextFile.SEPARATEUR+_fichier, constantes_);
        }
        if(!CONSTANTES_FICHIERS.getVal(_dossier+StreamTextFile.SEPARATEUR+_loc+StreamTextFile.SEPARATEUR+_fichier).contains(_nomConstante)){
            return _nomConstante.substring(_nomConstante.lastIndexOf(DOT)+1);
        }
        return CONSTANTES_FICHIERS.getVal(_dossier+StreamTextFile.SEPARATEUR+_loc+StreamTextFile.SEPARATEUR+_fichier).getVal(_nomConstante);
    }

    /**@see getConstante
    @param _dossier chemin de la suite de dossiers du jar ou se trouve le fichier
    @param _fichier nom du fichier dans lequel se trouve la constante a chercher
    @param _nomConstante nom de la constante en rapport avec la chaine de caracteres
    @param _variables liste de parametres remplacant les expressions comme {0} {1}...
    @return la chaine de caracteres retournee par la methode getConstante qui est formatte avec _variables*/
    public static String formatter(String _dossier,String _fichier, String _loc,String _nomConstante,Object... _variables){
        String constante_ = getConstanteLangue(_dossier,_fichier, _loc,_nomConstante);
        return StringList.simpleFormat(constante_, _variables);
    }

    public static String getClassProperties(String _class) {
        return StringList.replace(_class, DOT, StreamTextFile.SEPARATEUR).toLowerCase()+DOT+PROPERTIES;
    }

    private static String keepOnlyWordCharsDot(String _string) {
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
