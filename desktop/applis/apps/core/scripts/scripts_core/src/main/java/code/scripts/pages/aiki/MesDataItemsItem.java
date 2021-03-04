package code.scripts.pages.aiki;
final class MesDataItemsItem{
private MesDataItemsItem(){}
static String en(){
String f="title=Data about the item {0}.\n";
f+="price=The item {0} is worth {1}.<br/>\n";
f+="item_type={0}\n";
f+="items=Items\n";
return f;
}
static String fr(){
String f="title=Donn&eacute;s sur l''objet {0}.\n";
f+="price=Le prix d''un objet {0} est de {1}.<br/>\n";
f+="item_type={0}\n";
f+="items=Objets\n";
return f;
}
}
