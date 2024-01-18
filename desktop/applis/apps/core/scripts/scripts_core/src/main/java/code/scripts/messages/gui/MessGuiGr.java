package code.scripts.messages.gui;
import code.util.*;
public final class MessGuiGr{
private static final String ADD_MESS_2_74="title=Error of changing language\n";
private static final String ADD_MESS_2_75="message=The language cannot be changed\n";
private static final String ADD_MESS_2_76="title=Erreur de changement de langue\n";
private static final String ADD_MESS_2_77="message=La langue ne peut pas &ecirc;tre chang&eacute;e.\n";
private MessGuiGr(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_gui/gui/components/en/gui/groupframe.properties",enGuiGroupframe());
m.addEntry("resources_gui/gui/components/fr/gui/groupframe.properties",frGuiGroupframe());
return m;
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
