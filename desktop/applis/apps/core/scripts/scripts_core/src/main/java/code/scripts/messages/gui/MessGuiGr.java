package code.scripts.messages.gui;
import code.util.*;
public final class MessGuiGr{
private static final String ADD_MESS_2_0="yes=Yes\n";
private static final String ADD_MESS_2_10="name=Name\n";
private static final String ADD_MESS_2_11="files=Fichiers\n";
private static final String ADD_MESS_2_12="filesParam=Fichers {0}\n";
private static final String ADD_MESS_2_13="name=Nom\n";
private static final String ADD_MESS_2_14="ok=Ok\n";
private static final String ADD_MESS_2_15="open=Open\n";
private static final String ADD_MESS_2_16="cancel=Cancel\n";
private static final String ADD_MESS_2_17="typeText=Type text\n";
private static final String ADD_MESS_2_18="search=Search\n";
private static final String ADD_MESS_2_19="fileCount=Number of searched files: {0}\n";
private static final String ADD_MESS_2_1="no=No\n";
private static final String ADD_MESS_2_20="resultCount=Number of found files: {0}\n";
private static final String ADD_MESS_2_21="stopSearching=Stop searching\n";
private static final String ADD_MESS_2_22="cancelSearching=Cancel searching\n";
private static final String ADD_MESS_2_23="errorTitle=Inexistant file\n";
private static final String ADD_MESS_2_24="errorMessage=The file {0} does not exist.\n";
private static final String ADD_MESS_2_25="errorTyping=The file name must not be empty.\n";
private static final String ADD_MESS_2_26="ok=Ok\n";
private static final String ADD_MESS_2_27="open=Ouvrir\n";
private static final String ADD_MESS_2_28="cancel=Annuler\n";
private static final String ADD_MESS_2_29="typeText=Saissisez le texte\n";
private static final String ADD_MESS_2_2="cancel=Cancel\n";
private static final String ADD_MESS_2_30="search=Rechercher\n";
private static final String ADD_MESS_2_31="fileCount=Nombre de fichiers cherch&eacute;s: {0}\n";
private static final String ADD_MESS_2_32="resultCount=Nombre de fichiers trouv&eacute;s: {0}\n";
private static final String ADD_MESS_2_33="stopSearching=Arr&ecirc;ter la recherche\n";
private static final String ADD_MESS_2_34="cancelSearching=Annuler la recherche\n";
private static final String ADD_MESS_2_35="errorTitle=Fichier inexistant\n";
private static final String ADD_MESS_2_36="errorMessage=Le fichier {0} n''existe pas.\n";
private static final String ADD_MESS_2_37="errorTyping=Le nom de fichier ne doit pas &ecirc;tre vide.\n";
private static final String ADD_MESS_2_38="ok=Ok\n";
private static final String ADD_MESS_2_39="save=Save\n";
private static final String ADD_MESS_2_3="ok=Ok\n";
private static final String ADD_MESS_2_40="cancel=Cancel\n";
private static final String ADD_MESS_2_41="titleConf=Existing file\n";
private static final String ADD_MESS_2_42="bodyConf=Would you delete the file {0}?\n";
private static final String ADD_MESS_2_43="forbidden=Error\n";
private static final String ADD_MESS_2_44="forbiddenSpecialChars=The characters < > ? \" * / \\ | : are forbidden.\n";
private static final String ADD_MESS_2_45="forbiddenSpaces=The name must contain at least one visible character.\n";
private static final String ADD_MESS_2_46="folderName=Name of the folder to be made.\n";
private static final String ADD_MESS_2_47="ok=Ok\n";
private static final String ADD_MESS_2_48="save=Sauvegarder\n";
private static final String ADD_MESS_2_49="cancel=Annuler\n";
private static final String ADD_MESS_2_4="yes=Oui\n";
private static final String ADD_MESS_2_50="titleConf=Fichier existant\n";
private static final String ADD_MESS_2_51="bodyConf=Voulez vous &eacute;craser le fichier {0}?\n";
private static final String ADD_MESS_2_52="forbidden=Erreur\n";
private static final String ADD_MESS_2_53="forbiddenSpecialChars=Les caract&egrave;res < > ? \" * / \\ | : sont interdits.\n";
private static final String ADD_MESS_2_54="forbiddenSpaces=Le nom doit contenir au moins un caract&egrave;re visible.\n";
private static final String ADD_MESS_2_55="folderName=Nom du dossier &agrave; cr&eacute;er.\n";
private static final String ADD_MESS_2_56="name=Name\n";
private static final String ADD_MESS_2_57="modified=Last Modified\n";
private static final String ADD_MESS_2_58="size=Size\n";
private static final String ADD_MESS_2_59="path=Relative path\n";
private static final String ADD_MESS_2_5="no=Non\n";
private static final String ADD_MESS_2_60="name=Nom\n";
private static final String ADD_MESS_2_61="modified=Derni&egrave;re modification\n";
private static final String ADD_MESS_2_62="size=Taille\n";
private static final String ADD_MESS_2_63="path=Chemin relatif\n";
private static final String ADD_MESS_2_64="ok=Ok\n";
private static final String ADD_MESS_2_65="open=Open\n";
private static final String ADD_MESS_2_66="cancel=Cancel\n";
private static final String ADD_MESS_2_67="typeText=Type text\n";
private static final String ADD_MESS_2_68="search=Search\n";
private static final String ADD_MESS_2_69="ok=Ok\n";
private static final String ADD_MESS_2_6="cancel=Annuler\n";
private static final String ADD_MESS_2_70="open=Ouvrir\n";
private static final String ADD_MESS_2_71="cancel=Annuler\n";
private static final String ADD_MESS_2_72="typeText=Saissisez le texte\n";
private static final String ADD_MESS_2_73="search=Rechercher\n";
private static final String ADD_MESS_2_74="title=Error of changing language\n";
private static final String ADD_MESS_2_75="message=The language cannot be changed\n";
private static final String ADD_MESS_2_76="title=Erreur de changement de langue\n";
private static final String ADD_MESS_2_77="message=La langue ne peut pas &ecirc;tre chang&eacute;e.\n";
private static final String ADD_MESS_2_7="ok=Ok\n";
private static final String ADD_MESS_2_8="files=Files\n";
private static final String ADD_MESS_2_9="filesParam=Files {0}\n";
private MessGuiGr(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_gui/gui/components/en/gui/confirmdialog.properties",enGuiConfirmdialog());
m.addEntry("resources_gui/gui/components/fr/gui/confirmdialog.properties",frGuiConfirmdialog());
m.addEntry("resources_gui/gui/components/en/gui/filedialog.properties",enGuiFiledialog());
m.addEntry("resources_gui/gui/components/fr/gui/filedialog.properties",frGuiFiledialog());
m.addEntry("resources_gui/gui/components/en/gui/fileopendialog.properties",enGuiFileopendialog());
m.addEntry("resources_gui/gui/components/fr/gui/fileopendialog.properties",frGuiFileopendialog());
m.addEntry("resources_gui/gui/components/en/gui/filesavedialog.properties",enGuiFilesavedialog());
m.addEntry("resources_gui/gui/components/fr/gui/filesavedialog.properties",frGuiFilesavedialog());
m.addEntry("resources_gui/gui/components/en/gui/filetable.properties",enGuiFiletable());
m.addEntry("resources_gui/gui/components/fr/gui/filetable.properties",frGuiFiletable());
m.addEntry("resources_gui/gui/components/en/gui/folderopendialog.properties",enGuiFolderopendialog());
m.addEntry("resources_gui/gui/components/fr/gui/folderopendialog.properties",frGuiFolderopendialog());
m.addEntry("resources_gui/gui/components/en/gui/groupframe.properties",enGuiGroupframe());
m.addEntry("resources_gui/gui/components/fr/gui/groupframe.properties",frGuiGroupframe());
return m;
}
static String enGuiConfirmdialog(){
String f=ADD_MESS_2_0;
f+=ADD_MESS_2_1;
f+=ADD_MESS_2_2;
f+=ADD_MESS_2_3;
return f;
}
static String frGuiConfirmdialog(){
String f=ADD_MESS_2_4;
f+=ADD_MESS_2_5;
f+=ADD_MESS_2_6;
f+=ADD_MESS_2_7;
return f;
}
static String enGuiFiledialog(){
String f=ADD_MESS_2_8;
f+=ADD_MESS_2_9;
f+=ADD_MESS_2_10;
return f;
}
static String frGuiFiledialog(){
String f=ADD_MESS_2_11;
f+=ADD_MESS_2_12;
f+=ADD_MESS_2_13;
return f;
}
static String enGuiFileopendialog(){
String f=ADD_MESS_2_14;
f+=ADD_MESS_2_15;
f+=ADD_MESS_2_16;
f+=ADD_MESS_2_17;
f+=ADD_MESS_2_18;
f+=ADD_MESS_2_19;
f+=ADD_MESS_2_20;
f+=ADD_MESS_2_21;
f+=ADD_MESS_2_22;
f+=ADD_MESS_2_23;
f+=ADD_MESS_2_24;
f+=ADD_MESS_2_25;
return f;
}
static String frGuiFileopendialog(){
String f=ADD_MESS_2_26;
f+=ADD_MESS_2_27;
f+=ADD_MESS_2_28;
f+=ADD_MESS_2_29;
f+=ADD_MESS_2_30;
f+=ADD_MESS_2_31;
f+=ADD_MESS_2_32;
f+=ADD_MESS_2_33;
f+=ADD_MESS_2_34;
f+=ADD_MESS_2_35;
f+=ADD_MESS_2_36;
f+=ADD_MESS_2_37;
return f;
}
static String enGuiFilesavedialog(){
String f=ADD_MESS_2_38;
f+=ADD_MESS_2_39;
f+=ADD_MESS_2_40;
f+=ADD_MESS_2_41;
f+=ADD_MESS_2_42;
f+=ADD_MESS_2_43;
f+=ADD_MESS_2_44;
f+=ADD_MESS_2_45;
f+=ADD_MESS_2_46;
return f;
}
static String frGuiFilesavedialog(){
String f=ADD_MESS_2_47;
f+=ADD_MESS_2_48;
f+=ADD_MESS_2_49;
f+=ADD_MESS_2_50;
f+=ADD_MESS_2_51;
f+=ADD_MESS_2_52;
f+=ADD_MESS_2_53;
f+=ADD_MESS_2_54;
f+=ADD_MESS_2_55;
return f;
}
static String enGuiFiletable(){
String f=ADD_MESS_2_56;
f+=ADD_MESS_2_57;
f+=ADD_MESS_2_58;
f+=ADD_MESS_2_59;
return f;
}
static String frGuiFiletable(){
String f=ADD_MESS_2_60;
f+=ADD_MESS_2_61;
f+=ADD_MESS_2_62;
f+=ADD_MESS_2_63;
return f;
}
static String enGuiFolderopendialog(){
String f=ADD_MESS_2_64;
f+=ADD_MESS_2_65;
f+=ADD_MESS_2_66;
f+=ADD_MESS_2_67;
f+=ADD_MESS_2_68;
return f;
}
static String frGuiFolderopendialog(){
String f=ADD_MESS_2_69;
f+=ADD_MESS_2_70;
f+=ADD_MESS_2_71;
f+=ADD_MESS_2_72;
f+=ADD_MESS_2_73;
return f;
}
static String enGuiGroupframe(){
String f=ADD_MESS_2_74;
f+=ADD_MESS_2_75;
return f;
}
static String frGuiGroupframe(){
String f=ADD_MESS_2_76;
f+=ADD_MESS_2_77;
return f;
}
}
