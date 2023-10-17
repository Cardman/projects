package code.scripts.messages.gui;
import code.util.*;
public final class MessCdmGuiGr{
private static final String CODE_1_1_4="resources_lg_gui/gui/messages/en/launcher/mainwindow.properties";
private static final String CODE_1_1_5="resources_lg_gui/gui/messages/fr/launcher/mainwindow.properties";
private static final String CODE_1_6_0="title=Launcher\n";
private static final String CODE_1_6_1="file=File\n";
private static final String CODE_1_6_2="open=Open\n";
private static final String CODE_1_6_3="configuration=Configuration\n";
private static final String CODE_1_6_4="launch=Launch\n";
private static final String CODE_1_7_0="title=Lanceur\n";
private static final String CODE_1_7_1="file=Fichier\n";
private static final String CODE_1_7_2="open=Ouvrir\n";
private static final String CODE_1_7_3="configuration=Configuration\n";
private static final String CODE_1_7_4="launch=Lancer\n";
private MessCdmGuiGr(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry(CODE_1_1_4,resourcesLgGuiGuiMessagesEnLauncherMainwindow());
m.addEntry(CODE_1_1_5,resourcesLgGuiGuiMessagesFrLauncherMainwindow());
return m;
}
static String resourcesLgGuiGuiMessagesEnLauncherMainwindow(){
String f=CODE_1_6_0;
f+=CODE_1_6_1;
f+=CODE_1_6_2;
f+=CODE_1_6_3;
f+=CODE_1_6_4;
return f;
}
static String resourcesLgGuiGuiMessagesFrLauncherMainwindow(){
String f=CODE_1_7_0;
f+=CODE_1_7_1;
f+=CODE_1_7_2;
f+=CODE_1_7_3;
f+=CODE_1_7_4;
return f;
}
}
