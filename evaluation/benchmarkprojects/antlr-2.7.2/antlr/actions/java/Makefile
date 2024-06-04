G_FILES=action.g

# What's generated from G_TARGET
action_g_FILES := \
	ActionLexer.java \
	ActionLexerTokenTypes.java \
	ActionLexerTokenTypes.txt

ifndef TOPDIR
	TOPDIR  = $(shell pwd)/../../..
endif

# Leave these above any other rules...
include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Check out all the generated stuff here..
p4_genfiles: ;
	p4 edit $(action_g_FILES)
