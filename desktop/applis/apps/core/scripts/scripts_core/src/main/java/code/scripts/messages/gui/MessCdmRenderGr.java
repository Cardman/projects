package code.scripts.messages.gui;
import code.util.*;
public final class MessCdmRenderGr{
private MessCdmRenderGr(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_renders/aliases/en/attrs.properties",resourcesRendersAliasesEnAttrs());
m.addEntry("resources_renders/aliases/en/messagesrender.properties",resourcesRendersAliasesEnMessagesrender());
m.addEntry("resources_renders/aliases/en/styleattrs.properties",resourcesRendersAliasesEnStyleattrs());
m.addEntry("resources_renders/aliases/en/styleunits.properties",resourcesRendersAliasesEnStyleunits());
m.addEntry("resources_renders/aliases/en/stylevalues.properties",resourcesRendersAliasesEnStylevalues());
m.addEntry("resources_renders/aliases/en/tags.properties",resourcesRendersAliasesEnTags());
m.addEntry("resources_renders/aliases/en/types.properties",resourcesRendersAliasesEnTypes());
m.addEntry("resources_renders/aliases/en/values.properties",resourcesRendersAliasesEnValues());
m.addEntry("resources_renders/aliases/fr/attrs.properties",resourcesRendersAliasesFrAttrs());
m.addEntry("resources_renders/aliases/fr/messagesrender.properties",resourcesRendersAliasesFrMessagesrender());
m.addEntry("resources_renders/aliases/fr/styleattrs.properties",resourcesRendersAliasesFrStyleattrs());
m.addEntry("resources_renders/aliases/fr/styleunits.properties",resourcesRendersAliasesFrStyleunits());
m.addEntry("resources_renders/aliases/fr/stylevalues.properties",resourcesRendersAliasesFrStylevalues());
m.addEntry("resources_renders/aliases/fr/tags.properties",resourcesRendersAliasesFrTags());
m.addEntry("resources_renders/aliases/fr/types.properties",resourcesRendersAliasesFrTypes());
m.addEntry("resources_renders/aliases/fr/values.properties",resourcesRendersAliasesFrValues());
return m;
}
static String resourcesRendersAliasesEnAttrs(){
return resourcesRendersAliasesAttrs();
}
static String resourcesRendersAliasesEnMessagesrender(){
String f="BadInputName=The name expression must refer a field.\n";
f+="StaticInputName=The name {0} of the refered field must refer an instance field.\n";
f+="UnexpectedChildTag=The {0} block must be child of a block {1}.\n";
f+="EmptyAttr=The {0} attribute is empty.\n";
f+="UnexpectedExp=The expression is unexpected.\n";
f+="InexistantFile=The file {0} does not exist.\n";
f+="InexistantKey=The key {0} does not exist.\n";
f+="BadDocument=There is an issue in the document at {0}.\n";
return f;
}
static String resourcesRendersAliasesEnStyleattrs(){
return resourcesRendersAliasesStyleattrs();
}
static String resourcesRendersAliasesEnStyleunits(){
return resourcesRendersAliasesStyleunits();
}
static String resourcesRendersAliasesEnStylevalues(){
return resourcesRendersAliasesStylevalues();
}
static String resourcesRendersAliasesEnTags(){
String f="TagFor=for\n";
f+="TagWhile=while\n";
f+="TagDo=do\n";
f+="TagIf=if\n";
f+="TagElse=else\n";
f+="TagElseif=elseif\n";
f+="TagTry=try\n";
f+="TagFinally=finally\n";
f+="TagCatch=catch\n";
f+="TagSwitch=switch\n";
f+="TagCase=case\n";
f+="TagDefault=default\n";
f+="TagReturn=return\n";
f+="TagThrow=throw\n";
f+="TagBreak=break\n";
f+="TagContinue=continue\n";
f+="TagSet=set\n";
f+="TagImport=import\n";
f+="TagPackage=package\n";
f+="TagClass=class\n";
f+="TagField=field\n";
f+="TagForm=form\n";
f+="TagSubmit=submit\n";
f+="TagImg=img\n";
f+="TagSelect=select\n";
f+="TagMessage=message\n";
f+="TagAnchor=a\n";
f+="TagParam=param\n";
f+="TagInput=input\n";
f+="TagTextarea=textarea\n";
f+="TagSpan=span\n";
f+="TagLink=link\n";
f+="TagStyle=style\n";
f+="TagBody=body\n";
f+="TagHead=head\n";
f+="TagMap=map\n";
f+="TagLi=li\n";
f+="TagOl=ol\n";
f+="TagUl=ul\n";
f+="TagBold=b\n";
f+="TagItalic=i\n";
f+="TagPre=pre\n";
f+="TagHOne=h1\n";
f+="TagHTwo=h2\n";
f+="TagHThree=h3\n";
f+="TagHFour=h4\n";
f+="TagHFive=h5\n";
f+="TagHSix=h6\n";
f+="TagBr=br\n";
f+="TagHr=hr\n";
f+="TagPar=p\n";
f+="TagTable=table\n";
f+="TagCaption=caption\n";
f+="TagTd=td\n";
f+="TagTh=th\n";
f+="TagTr=tr\n";
f+="TagDiv=div\n";
f+="TagOption=option\n";
return f;
}
static String resourcesRendersAliasesEnTypes(){
String f="Bean=$core.Bean\n";
f+="MapKeys=keys\n";
f+="MapValues=values\n";
f+="MapIndexOfEntry=indexOfEntry\n";
f+="MapAddEntry=addEntry\n";
f+="MapGetValue=getValue\n";
f+="MapFirstValue=firstValue\n";
f+="MapLastValue=lastValue\n";
f+="MapSetValue=setValue\n";
f+="MapPut=put\n";
f+="MapContains=contains\n";
f+="MapPutAll=putAll\n";
f+="MapGetVal=getVal\n";
f+="MapRemoveKey=removeKey\n";
f+="MapGetKey=getKey\n";
f+="MapFirstKey=firstKey\n";
f+="MapLastKey=lastKey\n";
f+="MapSetKey=setKey\n";
f+="MapSize=size\n";
f+="MapIsEmpty=isEmpty\n";
f+="MapClear=clear\n";
f+="Validator=$core.Validator\n";
f+="Validate=validate\n";
f+="DataBaseField=dataBase\n";
f+="Forms=forms\n";
f+="SetForms=setForms\n";
f+="GetForms=getForms\n";
f+="Language=language\n";
f+="SetLanguage=setLanguage\n";
f+="GetLanguage=getLanguage\n";
f+="Scope=scope\n";
f+="SetScope=setScope\n";
f+="GetScope=getScope\n";
f+="SetDataBase=setDataBase\n";
f+="GetDataBase=getDataBase\n";
f+="BeforeDisplaying=beforeDisplaying\n";
f+="StringMapObject=$core.StringMapObject\n";
f+="Message=$core.Message\n";
f+="NewMessage=newStandardMessage\n";
f+="MessageFormat=format\n";
f+="MessageGetArgs=getArgs\n";
f+="MessageSetArgs=setArgs\n";
f+="Bean0SetLanguage0=a\n";
f+="Bean0SetScope0=a\n";
f+="Bean0SetDataBase0=a\n";
f+="Bean0SetForms0=a\n";
f+="StringMapObject0SetKey0=a\n";
f+="StringMapObject0SetKey1=b\n";
f+="StringMapObject0GetKey0=a\n";
f+="StringMapObject0SetValue0=a\n";
f+="StringMapObject0SetValue1=b\n";
f+="StringMapObject0GetValue0=a\n";
f+="StringMapObject0Put0=a\n";
f+="StringMapObject0Put1=b\n";
f+="StringMapObject0AddEntry0=a\n";
f+="StringMapObject0AddEntry1=b\n";
f+="StringMapObject0Contains0=a\n";
f+="StringMapObject0GetVal0=a\n";
f+="StringMapObject0IndexOfEntry0=a\n";
f+="StringMapObject0PutAll0=a\n";
f+="StringMapObject0RemoveKey0=a\n";
f+="Validator0Validate0=a\n";
f+="Validator0Validate1=b\n";
f+="Validator0Validate2=c\n";
f+="Validator0Validate3=d\n";
f+="Validator0Validate4=e\n";
f+="Validator0Validate5=f\n";
f+="Message1NewMessage0=a\n";
f+="Message0SetArgs0=a\n";
return f;
}
static String resourcesRendersAliasesEnValues(){
return resourcesRendersAliasesValues();
}
static String resourcesRendersAliasesFrAttrs(){
return resourcesRendersAliasesAttrs();
}
private static String resourcesRendersAliasesAttrs() {
String f="AttrType=type\n";
f+="AttrMultiple=multiple\n";
f+="AttrClassName=className\n";
f+="AttrConvertField=convertField\n";
f+="AttrValueMessage=valueMessage\n";
f+="AttrEscapedAmp=escapedamp\n";
f+="AttrConvertValue=convertValue\n";
f+="AttrConvertFieldValue=convertFieldValue\n";
f+="AttrVarClassName=varClassName\n";
f+="AttrKeepFields=keepfields\n";
f+="AttrKeyClassName=keyClassName\n";
f+="AttrIndexClassName=indexClassName\n";
f+="AttrKey=key\n";
f+="AttrValue=value\n";
f+="AttrEq=eq\n";
f+="AttrInit=init\n";
f+="AttrList=list\n";
f+="AttrCondition=condition\n";
f+="AttrTo=to\n";
f+="AttrMap=map\n";
f+="AttrBean=bean\n";
f+="AttrFrom=from\n";
f+="AttrChecked=checked\n";
f+="AttrSelected=selected\n";
f+="AttrVar=var\n";
f+="AttrLabel=label\n";
f+="AttrNf=n-f\n";
f+="AttrNa=n-a\n";
f+="AttrAlias=alias\n";
f+="AttrNi=n-i\n";
f+="AttrStep=step\n";
f+="AttrHref=href\n";
f+="AttrPrepare=prepare\n";
f+="AttrFor=for\n";
f+="AttrQuoted=quoted\n";
f+="AttrId=id\n";
f+="AttrClass=class\n";
f+="AttrAction=action\n";
f+="AttrParam=param\n";
f+="AttrMessage=message\n";
f+="AttrCols=cols\n";
f+="AttrForm=form\n";
f+="AttrVarValue=varValue\n";
f+="AttrRows=rows\n";
f+="AttrCommand=command\n";
f+="AttrDefault=default\n";
f+="AttrSrc=src\n";
f+="AttrEscaped=escaped\n";
f+="AttrGroupId=groupId\n";
f+="AttrTitle=title\n";
f+="AttrValidator=validator\n";
f+="AttrPage=page\n";
f+="AttrWidth=width\n";
f+="AttrDelay=delay\n";
f+="AttrRel=rel\n";
f+="AttrName=name\n";
f+="AttrConvert=convert\n";
return f;
}
static String resourcesRendersAliasesFrMessagesrender(){
String f="BadInputName=Le nom expression doit r&eacute;f&eacute;rer un champ.\n";
f+="StaticInputName=Le nom {0} du champ r&eacute;f&eacute;r&eacute; doit r&eacute;f&eacute;rer un champ d''instance.\n";
f+="UnexpectedChildTag=Le bloc {0} doit &ecirc;tre enfant d''un bloc {1}.\n";
f+="EmptyAttr=L''attribut {0} est vide.\n";
f+="UnexpectedExp=L''expression est inattendue.\n";
f+="InexistantFile=Le fichier {0} n''existe pas.\n";
f+="InexistantKey=La cl&eacute; {0} n''existe pas.\n";
f+="BadDocument=Il y a un probl&egrave;me dans le document &agrave; {0}.\n";
return f;
}
static String resourcesRendersAliasesFrStyleattrs(){
return resourcesRendersAliasesStyleattrs();
}
private static String resourcesRendersAliasesStyleattrs() {
String f="StyleAttrBackground=background\n";
f+="StyleAttrColor=color\n";
f+="StyleAttrBorder=border\n";
f+="StyleAttrFontFam=font-family\n";
f+="StyleAttrFontSize=font-size\n";
return f;
}
static String resourcesRendersAliasesFrStyleunits(){
return resourcesRendersAliasesStyleunits();
}
private static String resourcesRendersAliasesStyleunits() {
String f="StyleUnitSolid=solid\n";
f+="StyleUnitEm=em\n";
f+="StyleUnitPx=px\n";
return f;
}
static String resourcesRendersAliasesFrStylevalues(){
return resourcesRendersAliasesStylevalues();
}
private static String resourcesRendersAliasesStylevalues() {
String f="StyleValueBlue=blue\n";
f+="StyleValueGrey=grey\n";
f+="StyleValueGreen=green\n";
f+="StyleValueBlack=black\n";
f+="StyleValueMagenta=magenta\n";
f+="StyleValueYellow=yellow\n";
f+="StyleValueWhite=white\n";
f+="StyleValueCyan=cyan\n";
f+="StyleValueRed=red\n";
f+="StyleValueRgb=rgb\n";
return f;
}
static String resourcesRendersAliasesFrTags(){
String f="TagFor=pour\n";
f+="TagWhile=tantque\n";
f+="TagDo=faire\n";
f+="TagIf=si\n";
f+="TagElse=sinon\n";
f+="TagElseif=sinonsi\n";
f+="TagTry=essai\n";
f+="TagFinally=finallement\n";
f+="TagCatch=capture\n";
f+="TagSwitch=selon\n";
f+="TagCase=cas\n";
f+="TagDefault=autrement\n";
f+="TagReturn=retour\n";
f+="TagThrow=lancer\n";
f+="TagBreak=sortir\n";
f+="TagContinue=iterer\n";
f+="TagSet=maj\n";
f+="TagImport=import\n";
f+="TagPackage=paquetage\n";
f+="TagClass=classe\n";
f+="TagField=champ\n";
f+="TagForm=form\n";
f+="TagSubmit=soumission\n";
f+="TagImg=img\n";
f+="TagSelect=select\n";
f+="TagMessage=message\n";
f+="TagAnchor=a\n";
f+="TagParam=param\n";
f+="TagInput=entree\n";
f+="TagTextarea=zonetexte\n";
f+="TagSpan=noeudligne\n";
f+="TagLink=lien\n";
f+="TagStyle=style\n";
f+="TagBody=corps\n";
f+="TagHead=tete\n";
f+="TagMap=map\n";
f+="TagLi=le\n";
f+="TagOl=lo\n";
f+="TagUl=lp\n";
f+="TagBold=g\n";
f+="TagItalic=i\n";
f+="TagPre=pre\n";
f+="TagHOne=h1\n";
f+="TagHTwo=h2\n";
f+="TagHThree=h3\n";
f+="TagHFour=h4\n";
f+="TagHFive=h5\n";
f+="TagHSix=h6\n";
f+="TagBr=saut\n";
f+="TagHr=sautligne\n";
f+="TagPar=p\n";
f+="TagTable=table\n";
f+="TagCaption=entete\n";
f+="TagTd=td\n";
f+="TagTh=th\n";
f+="TagTr=tr\n";
f+="TagDiv=div\n";
f+="TagOption=option\n";
return f;
}
static String resourcesRendersAliasesFrTypes(){
String f="Bean=$coeur.Graine\n";
f+="MapKeys=cles\n";
f+="MapValues=valeurs\n";
f+="MapIndexOfEntry=indicePaire\n";
f+="MapAddEntry=ajPaire\n";
f+="MapGetValue=obtValeur\n";
f+="MapFirstValue=preVal\n";
f+="MapLastValue=derVal\n";
f+="MapSetValue=majVal\n";
f+="MapPut=ajOuMaj\n";
f+="MapContains=contient\n";
f+="MapPutAll=ajOuMajTous\n";
f+="MapGetVal=obtVal\n";
f+="MapRemoveKey=supprCle\n";
f+="MapGetKey=obtCle\n";
f+="MapFirstKey=preCle\n";
f+="MapLastKey=derCle\n";
f+="MapSetKey=majCle\n";
f+="MapSize=taille\n";
f+="MapIsEmpty=estVide\n";
f+="MapClear=suppr\n";
f+="Validator=$coeur.Validateur\n";
f+="Validate=valider\n";
f+="DataBaseField=baseDonnees\n";
f+="Forms=formulaire\n";
f+="SetForms=majFormulaire\n";
f+="GetForms=obtFormulaire\n";
f+="Language=langue\n";
f+="SetLanguage=majLangue\n";
f+="GetLanguage=obtLangue\n";
f+="Scope=scope\n";
f+="SetScope=majScope\n";
f+="GetScope=obtScope\n";
f+="SetDataBase=majBaseDonnees\n";
f+="GetDataBase=obtBaseDonnees\n";
f+="BeforeDisplaying=avantAffiche\n";
f+="StringMapObject=$coeur.StringMapObject\n";
f+="Message=$coeur.Message\n";
f+="NewMessage=nvuMessageStandard\n";
f+="MessageFormat=format\n";
f+="MessageGetArgs=obtArguments\n";
f+="MessageSetArgs=majArguments\n";
f+="Bean0SetLanguage0=a\n";
f+="Bean0SetScope0=a\n";
f+="Bean0SetDataBase0=a\n";
f+="Bean0SetForms0=a\n";
f+="StringMapObject0SetKey0=a\n";
f+="StringMapObject0SetKey1=b\n";
f+="StringMapObject0GetKey0=a\n";
f+="StringMapObject0SetValue0=a\n";
f+="StringMapObject0SetValue1=b\n";
f+="StringMapObject0GetValue0=a\n";
f+="StringMapObject0Put0=a\n";
f+="StringMapObject0Put1=b\n";
f+="StringMapObject0AddEntry0=a\n";
f+="StringMapObject0AddEntry1=b\n";
f+="StringMapObject0Contains0=a\n";
f+="StringMapObject0GetVal0=a\n";
f+="StringMapObject0IndexOfEntry0=a\n";
f+="StringMapObject0PutAll0=a\n";
f+="StringMapObject0RemoveKey0=a\n";
f+="Validator0Validate0=a\n";
f+="Validator0Validate1=b\n";
f+="Validator0Validate2=c\n";
f+="Validator0Validate3=d\n";
f+="Validator0Validate4=e\n";
f+="Validator0Validate5=f\n";
f+="Message1NewMessage0=a\n";
f+="Message0SetArgs0=a\n";
return f;
}
static String resourcesRendersAliasesFrValues(){
return resourcesRendersAliasesValues();
}
private static String resourcesRendersAliasesValues() {
String f="ValueRange=range\n";
f+="ValueText=text\n";
f+="ValueCheckbox=checkbox\n";
f+="ValueNumber=number\n";
f+="ValueRadio=radio\n";
f+="ValueSubmit=submit\n";
f+="ValueLiMajLat=I\n";
f+="ValueLiRect=rect\n";
f+="ValueStyle=stylesheet\n";
f+="ValueLiMinLet=a\n";
f+="ValueLiNb=1\n";
f+="ValueLiMajLet=A\n";
f+="ValueLiDisk=disc\n";
f+="ValueLiMinLat=i\n";
f+="ValueLiCircle=circle\n";
f+="ValueLiSquare=square\n";
return f;
}
}
