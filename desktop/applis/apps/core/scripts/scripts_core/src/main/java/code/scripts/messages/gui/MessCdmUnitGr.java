package code.scripts.messages.gui;
import code.util.*;
public final class MessCdmUnitGr{
private MessCdmUnitGr(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_unit/gui/messages/en/unit/mainwindow.properties",resourcesUnitGuiMessagesEnUnitMainwindow());
m.addEntry("resources_unit/gui/messages/en/unit/simplefilesframe.properties",resourcesUnitGuiMessagesEnUnitSimplefilesframe());
m.addEntry("resources_unit/gui/messages/fr/unit/mainwindow.properties",resourcesUnitGuiMessagesFrUnitMainwindow());
m.addEntry("resources_unit/gui/messages/fr/unit/simplefilesframe.properties",resourcesUnitGuiMessagesFrUnitSimplefilesframe());
return m;
}
static String resourcesUnitGuiMessagesEnUnitMainwindow(){
String f="title=Unit tests\n";
f+="file=File\n";
f+="open=Open\n";
f+="status=Status\n";
f+="archive=Archive\n";
f+="stop=Stop\n";
f+="configuration=Configuration\n";
f+="launch=Launch\n";
f+="tests=Tests\n";
f+="method=Method\n";
f+="number=Number\n";
f+="params=Parameters\n";
f+="success=Success\n";
f+="fail=Fail\n";
f+="memory=Memory\n";
return f;
}
static String resourcesUnitGuiMessagesEnUnitSimplefilesframe(){
String f="title=Unit tests\n";
f+="file=File\n";
f+="open=Open\n";
f+="status=Status\n";
f+="stop=Stop\n";
f+="configuration=Configuration\n";
f+="launchByFile=Launch by file\n";
f+="launch=Launch\n";
f+="tests=Tests\n";
f+="method=Method\n";
f+="number=Number\n";
f+="params=Parameters\n";
f+="success=Success\n";
f+="failLoad=Fail Load {0}\n";
f+="failLoadThread=Fail Load {0} because of living thread\n";
f+="failLoadPath=Fail Load {0} because {1} is not absolute\n";
f+="failLoadContent=Fail Load {0} because of error of reading\n";
f+="successLoad=Successful Load {0}\n";
f+="fail=Fail\n";
f+="folder=Folder\n";
f+="src=Sources\n";
f+="files=Data\n";
return f;
}
static String resourcesUnitGuiMessagesFrUnitMainwindow(){
String f="title=Tests unitaires\n";
f+="file=Fichier\n";
f+="open=Ouvrir\n";
f+="status=Statut\n";
f+="archive=Archive\n";
f+="stop=Stop\n";
f+="configuration=Configuration\n";
f+="launch=Lancer\n";
f+="tests=Tests\n";
f+="method=M&eacute;thode\n";
f+="number=Num&eacute;ro\n";
f+="params=Param&egrave;tres\n";
f+="success=Succ&egrave;s\n";
f+="fail=&Eacute;chec\n";
f+="memory=M&eacute;moire\n";
return f;
}
static String resourcesUnitGuiMessagesFrUnitSimplefilesframe(){
String f="title=Tests unitaires\n";
f+="file=Fichier\n";
f+="open=Ouvrir\n";
f+="status=Statut\n";
f+="stop=Stop\n";
f+="configuration=Configuration\n";
f+="launchByFile=Lancer par fichier\n";
f+="launch=Lancer\n";
f+="tests=Tests\n";
f+="method=M&eacute;thode\n";
f+="number=Num&eacute;ro\n";
f+="params=Param&egrave;tres\n";
f+="success=Succ&egrave;s\n";
f+="failLoad=Chargement &Eacute;chou&eacute; {0}\n";
f+="failLoadThread=Chargement &Eacute;chou&eacute; {0} &agrave; cause d''une tache active\n";
f+="failLoadPath=Chargement &Eacute;chou&eacute;{0} &agrave; cause que {1} n''est pas absolu\n";
f+="failLoadContent=Chargement &Eacute;chou&eacute; {0} &agrave; cause d''erreur de lecture\n";
f+="successLoad=Chargement r&eacute;ussi {0}\n";
f+="fail=&Eacute;chec\n";
f+="folder=Dossier\n";
f+="src=Sources\n";
f+="files=Donn&eacute;es\n";
return f;
}
}
