G_FILES := preproc.g

# What's generated from G_TARGET
preproc_g_FILES := \
   Preprocessor.java \
   PreprocessorLexer.java \
   PreprocessorTokenTypes.java \
   PreprocessorTokenTypes.txt \

ifndef TOPDIR
	TOPDIR  = $(shell pwd)/../..
endif

# Leave these above any other rules...
include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Check out all the generated stuff here..
p4_genfiles: ;
	p4 edit $(preproc_g_GENFILES)
