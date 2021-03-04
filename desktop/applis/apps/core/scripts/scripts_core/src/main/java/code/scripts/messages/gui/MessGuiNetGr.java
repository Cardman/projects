package code.scripts.messages.gui;
import code.util.*;
public final class MessGuiNetGr{
private MessGuiNetGr(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_network/messages/en/network/quitting.properties",enNetworkQuitting());
m.addEntry("resources_network/messages/fr/network/quitting.properties",frNetworkQuitting());
m.addEntry("resources_network/const_enum/en/ip.txt",enIp());
m.addEntry("resources_network/const_enum/fr/ip.txt",frIp());
return m;
}
static String enNetworkQuitting(){
String f="usedPortTitle=Used port number\n";
f+="usedPort=The port number {0} is used. You can change the port number or you can try to join a server with a different IP.\n";
return f;
}
static String frNetworkQuitting(){
String f="usedPortTitle=Num&eacute;ro de port utilis&eacute;\n";
f+="usedPort=Le num&eacute;ro de port {0} est utilis&eacute;. Vous pouvez changer le num&eacute;ro de port ou vous pouvez essayer de rejoindre un serveur avec une IP diff&eacute;rente.\n";
return f;
}
static String enIp(){
String f="network.enums.IpType.HOST_NAME:Name of computer\n";
f+="network.enums.IpType.IP_V4:Old type of ip addressing\n";
f+="network.enums.IpType.IP_V6:New type of ip addressing\n";
return f;
}
static String frIp(){
String f="network.enums.IpType.HOST_NAME:Nom d'ordinateur\n";
f+="network.enums.IpType.IP_V4:Ancien type d'adressage\n";
f+="network.enums.IpType.IP_V6:Nouveau type d'adressage\n";
return f;
}
}
