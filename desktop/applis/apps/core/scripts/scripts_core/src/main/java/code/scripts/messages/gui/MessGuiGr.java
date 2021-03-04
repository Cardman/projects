package code.scripts.messages.gui;
import code.util.*;
public final class MessGuiGr{
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
String f="yes=Yes\n";
f+="no=No\n";
f+="cancel=Cancel\n";
f+="ok=Ok\n";
return f;
}
static String frGuiConfirmdialog(){
String f="yes=Oui\n";
f+="no=Non\n";
f+="cancel=Annuler\n";
f+="ok=Ok\n";
return f;
}
static String enGuiFiledialog(){
String f="files=Files\n";
f+="filesParam=Files {0}\n";
f+="name=Name\n";
return f;
}
static String frGuiFiledialog(){
String f="files=Fichiers\n";
f+="filesParam=Fichers {0}\n";
f+="name=Nom\n";
return f;
}
static String enGuiFileopendialog(){
String f="ok=Ok\n";
f+="open=Open\n";
f+="cancel=Cancel\n";
f+="typeText=Type text\n";
f+="search=Search\n";
f+="fileCount=Number of searched files: {0}\n";
f+="resultCount=Number of found files: {0}\n";
f+="stopSearching=Stop searching\n";
f+="cancelSearching=Cancel searching\n";
f+="errorTitle=Inexistant file\n";
f+="errorMessage=The file {0} does not exist.\n";
f+="errorTyping=The file name must not be empty.\n";
return f;
}
static String frGuiFileopendialog(){
String f="ok=Ok\n";
f+="open=Ouvrir\n";
f+="cancel=Annuler\n";
f+="typeText=Saissisez le texte\n";
f+="search=Rechercher\n";
f+="fileCount=Nombre de fichiers cherch&eacute;s: {0}\n";
f+="resultCount=Nombre de fichiers trouv&eacute;s: {0}\n";
f+="stopSearching=Arr&ecirc;ter la recherche\n";
f+="cancelSearching=Annuler la recherche\n";
f+="errorTitle=Fichier inexistant\n";
f+="errorMessage=Le fichier {0} n''existe pas.\n";
f+="errorTyping=Le nom de fichier ne doit pas &ecirc;tre vide.\n";
return f;
}
static String enGuiFilesavedialog(){
String f="ok=Ok\n";
f+="save=Save\n";
f+="cancel=Cancel\n";
f+="titleConf=Existing file\n";
f+="bodyConf=Would you delete the file {0}?\n";
f+="forbidden=Error\n";
f+="forbiddenSpecialChars=The characters < > ? \" * / \\ | : are forbidden.\n";
f+="forbiddenSpaces=The name must contain at least one visible character.\n";
f+="folderName=Name of the folder to be made.\n";
return f;
}
static String frGuiFilesavedialog(){
String f="ok=Ok\n";
f+="save=Sauvegarder\n";
f+="cancel=Annuler\n";
f+="titleConf=Fichier existant\n";
f+="bodyConf=Voulez vous &eacute;craser le fichier {0}?\n";
f+="forbidden=Erreur\n";
f+="forbiddenSpecialChars=Les caract&egrave;res < > ? \" * / \\ | : sont interdits.\n";
f+="forbiddenSpaces=Le nom doit contenir au moins un caract&egrave;re visible.\n";
f+="folderName=Nom du dossier &agrave; cr&eacute;er.\n";
return f;
}
static String enGuiFiletable(){
String f="name=Name\n";
f+="modified=Last Modified\n";
f+="size=Size\n";
f+="path=Relative path\n";
return f;
}
static String frGuiFiletable(){
String f="name=Nom\n";
f+="modified=Derni&egrave;re modification\n";
f+="size=Taille\n";
f+="path=Chemin relatif\n";
return f;
}
static String enGuiFolderopendialog(){
String f="ok=Ok\n";
f+="open=Open\n";
f+="cancel=Cancel\n";
f+="typeText=Type text\n";
f+="search=Search\n";
return f;
}
static String frGuiFolderopendialog(){
String f="ok=Ok\n";
f+="open=Ouvrir\n";
f+="cancel=Annuler\n";
f+="typeText=Saissisez le texte\n";
f+="search=Rechercher\n";
return f;
}
static String enGuiGroupframe(){
String f="title=Error of changing language\n";
f+="message=The language cannot be changed\n";
return f;
}
static String frGuiGroupframe(){
String f="title=Erreur de changement de langue\n";
f+="message=La langue ne peut pas &ecirc;tre chang&eacute;e.\n";
return f;
}
}
