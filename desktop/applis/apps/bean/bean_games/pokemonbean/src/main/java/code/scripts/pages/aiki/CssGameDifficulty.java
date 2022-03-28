package code.scripts.pages.aiki;
final class CssGameDifficulty{
private CssGameDifficulty(){}
static String in(){
String f="p{\n";
f+="\ttext-indent:25px;\n";
f+="}\n";
f+="body{\n";
f+="\ttext-align:justify;\n";
f+="}\n";
f+="td{\n";
f+="\tborder:1px solid black;\n";
f+="}\n";
f+="th{\n";
f+="\tbackground: yellow;\n";
f+="\tborder:1px solid black;\n";
f+="}\n";
f+="table{\n";
f+="\tborder-spacing:0;\n";
f+="}\n";
f+="h1{\n";
f+="\tcolor:red;\n";
f+="}\n";
f+="h2{\n";
f+="\tcolor:blue;\n";
f+="}\n";
f+="span.errormessage{\n";
f+="\tcolor:red;\n";
f+="}\n";
return f;
}
}
