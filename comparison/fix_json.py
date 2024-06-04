import sys, re

with open(sys.argv[1]) as f:
    lines = f.readlines()

repls = [
    ("antlr.JavaCodeGenerator::getLookaheadTestExpression(Lookahead[], int)",                            "antlr.JavaCodeGenerator::getLookaheadTestExpression(antlr.Lookahead[], int)"),
    ("antlr.preprocessor.Tool::processArguments(String[])",                                          "antlr.preprocessor.Tool::processArguments(java.lang.String[])"),
    ("antlr.LLkAnalyzer::lookaheadEquivForApproxAndFullAnalysis(Lookahead[], int)",                      "antlr.LLkAnalyzer::lookaheadEquivForApproxAndFullAnalysis(antlr.Lookahead[], int)"),
    ("antlr.LexerGrammar::processArguments(String[])",                                               "antlr.LexerGrammar::processArguments(java.lang.String[])"),
    ("antlr.Tool::checkForInvalidArguments(String[], antlr.collections.impl.BitSet)",                "antlr.Tool::checkForInvalidArguments(java.lang.String[], antlr.collections.impl.BitSet)"),
    ("antlr.Tool::processArguments(String[])",                                                       "antlr.Tool::processArguments(java.lang.String[])"),
    ("antlr.DefineGrammarSymbols::<init>(antlr.Tool, String[], antlr.LLkAnalyzer)",                  "antlr.DefineGrammarSymbols::<init>(antlr.Tool, java.lang.String[], antlr.LLkAnalyzer)"),
    ("antlr.MismatchedTokenException::<init>(String[], antlr.Token, int, boolean, java.lang.String)","antlr.MismatchedTokenException::<init>(java.lang.String[], antlr.Token, int, boolean, java.lang.String)"),
    ("antlr.ParserGrammar::processArguments(String[])",                                              "antlr.ParserGrammar::processArguments(java.lang.String[])"),
    ("antlr.CppCodeGenerator::getLookaheadTestExpression(Lookahead[], int)",                             "antlr.CppCodeGenerator::getLookaheadTestExpression(antlr.Lookahead[], int)"),
    ("antlr.Tool::doEverything(String[])",                                                           "antlr.Tool::doEverything(java.lang.String[])"),
    ("antlr.MakeGrammar::<init>(antlr.Tool, String[], antlr.LLkAnalyzer)",                           "antlr.MakeGrammar::<init>(antlr.Tool, java.lang.String[], antlr.LLkAnalyzer)"),
    ("antlr.Tool::main(String[])",                                                                   "antlr.Tool::main(java.lang.String[])"),
]

def replace(line):
    line = re.sub(r"(\w+)::\1\(", r"\g<1>::<init>(", line)
    for old, new in repls:
        line = line.replace(old, new)
    return line

lines = [replace(line) for line in lines]

for line1, line2 in zip(lines, lines[1:]):
    if "method_signature" in line1:
        print(line1.strip() + ",")
    elif line1.startswith("      ") and line2.startswith("      "):
        print(line1.strip() + ",")
    else:
        print(line1.strip())

print("]")
