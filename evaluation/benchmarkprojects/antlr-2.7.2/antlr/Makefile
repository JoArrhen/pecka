# This file basically only rebuilds the ANTLR generated stuff.
G_FILES := antlr.g tokdef.g

SUB_DIRS := preprocessor actions collections debug
ALL_SUB_DIRS := $(SUB_DIRS)

# What's generated from antlr.g
antlr_g_FILES := \
   ANTLRParser.java \
   ANTLRTokenTypes.java \
   ANTLRTokenTypes.txt \
   ANTLRLexer.java

# What's generated from tokdef.g
tokdef_g_FILES := \
   ANTLRTokdefParser.java \
   ANTLRTokdefLexer.java \
   ANTLRTokdefParserTokenTypes.java \
   ANTLRTokdefParserTokenTypes.txt

ifndef TOPDIR
	TOPDIR  = $(shell pwd)/..
endif

# Leave these above any other rules...
include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Check out all the generated stuff here..
p4_genfiles: ;
	p4 edit $(antlr_g_FILES) $(tokdef_g_FILES)
