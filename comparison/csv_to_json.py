import csv, sys, re
from collections import defaultdict

with open(sys.argv[1]) as csvfile:
    fieldnames = ["static_type", "name", "filename", "signature", "dynamic_type"]
    d = csv.DictReader(csvfile, fieldnames=fieldnames, delimiter=";")
    d = list(d)

qualified_types = [
        ("ImportVocabTokenManager", "antlr.ImportVocabTokenManager"),
        ("Token", "antlr.Token"),
        ("StringLiteralElement", "antlr.StringLiteralElement"),
        ("String", "java.lang.String"),
        ("Object", "java.lang.Object"),
        (r"String\[\]", "java.lang.String[]"),
        ("Reader", "java.io.Reader"),
        ("ActionElement", "antlr.ActionElement"),
        ("LexerGrammar", "antlr.LexerGrammar"),
        ("MakeGrammar", "antlr.MakeGrammar"),
        ("LexerSharedInputState", "antlr.LexerSharedInputState"),
        ("AlternativeElement", "antlr.AlternativeElement"),
        ("GrammarFile", "antlr.preprocessor.GrammarFile"),
        ("Grammar", "antlr.Grammar"),
        ("Tool", "antlr.Tool"),
        ("CppBlockFinishingInfo", "antlr.CppBlockFinishingInfo"),
        ("JavaBlockFinishingInfo", "antlr.JavaBlockFinishingInfo"),
        ("DefineGrammarSymbols", "antlr.DefineGrammarSymbols"),
        ("RuleEndElement", "antlr.RuleEndElement"),
        ("BlockEndElement", "antlr.BlockEndElement"),
        ("Rule", "antlr.preprocessor.Rule"),
        ("ParserGrammar", "antlr.ParserGrammar"),
        ("ParserSharedInputState", "antlr.ParserSharedInputState"),
        ("CodeGenerator", "antlr.CodeGenerator"),
        ("ActionTransInfo", "antlr.ActionTransInfo"),
        ("RuleBlock", "antlr.RuleBlock"),
        ("OneOrMoreBlock", "antlr.OneOrMoreBlock"),
        ("BitSet", "antlr.collections.impl.BitSet"),
        ("CharScanner", "antlr.CharScanner"),
        ("Alternative", "antlr.Alternative"),
        ("CharFormatter", "antlr.CharFormatter"),
        ("TreeWalkerGrammar", "antlr.TreeWalkerGrammar"),
        ("SynPredBlock", "antlr.SynPredBlock"),
        (r"Lookahead\[\]", "antlr.Lookahead[]"),
        ("Lookahead", "antlr.Lookahead"),
        ("LLkAnalyzer", "antlr.LLkAnalyzer"),
        ("LLkGrammarAnalyzer", "antlr.LLkGrammarAnalyzer"),
        ("GrammarAtom", "antlr.GrammarAtom"),
        ("StringLiteralElement", "antlr.StringLiteralElement"),
        ("ExceptionHandler", "antlr.ExceptionHandler"),
        ("ExceptionSpec", "antlr.ExceptionSpec"),
        ("Exception", "java.lang.Exception"),
        ("TokenRangeElement", "antlr.TokenRangeElement"),
        ("IndexedVector", "antlr.collections.impl.IndexedVector"),
        ("Vector", "antlr.collections.impl.Vector"),
        ("InputBuffer", "antlr.InputBuffer"),
        ("Hierarchy", "antlr.preprocessor.Hierarchy"),
        ("ZeroOrMoreBlock", "antlr.ZeroOrMoreBlock"),
        ("BlockWithImpliedExitPath", "antlr.BlockWithImpliedExitPath"),
        ("WildcardElement", "antlr.WildcardElement"),
        ("CharLiteralElement", "antlr.CharLiteralElement"),
        ("CharRangeElement", "antlr.CharRangeElement"),
        ]

def create_sig(row):
    filename = row["filename"]
    filename = filename.replace("/", ".").replace(".java", "")
    if not filename.startswith("antlr"):
        filename = "antlr." + filename

    method = row["signature"]
    method = re.sub(r" [A-Za-z0-9_]+,", ",", method)
    method = re.sub(r" [A-Za-z0-9_]+\)", ")", method)
    method_name, parameter_list = method.split("(")
    for t, qt in qualified_types:
       parameter_list = re.sub(r"(?<= )" + t, qt, parameter_list)
       parameter_list = re.sub("^" + t, qt, parameter_list)
        #parameter_list = parameter_list.replace(t, qt)

    sig = filename + "::" + method_name + "(" + parameter_list
    return sig


dd = defaultdict(lambda: defaultdict(set))
for row in d:
    sig = create_sig(row)
    name = row["name"]
    dd[sig][name].add(row["dynamic_type"])

def set_to_string(s):
    res = "["
    for t in s:
        res += '"' + t + '", '

    if len(res) > 1:
        res = res[:-2]

    res += "]"
    return res

def fix_constructor(sig):
    is_constructor = re.search(r"\.(\w+)::\1\(", sig)
    if is_constructor:
        return re.sub(r"(?<=::)\w+(?=\()", "<init>", sig)
    else:
        return sig

print("[")
for i, (signature, d) in enumerate(dd.items()):
    print("  {")
    print(f'    "method_signature": "{fix_constructor(signature)}",')
    print( '    "vars": {')

    for i2, (name, types) in enumerate(d.items()):
        next = f'      "{name}": {set_to_string(types)}'
        if i2 != len(d) - 1:
          next += ","
        print(next)

    print("    }")

    next = "  }"
    if i != len(dd) - 1:
      next += ","
    print(next)

print("]")

