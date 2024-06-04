file=Results


all: pdf out
	make pdf
	bibtex $(file)
	make pdf
	make pdf

out:
	if  [ -f $(file).out ] ; then cp $(file).out tmp.out; fi ;
	sed 's/BOOKMARK/dtxmark/g;' tmp.out > x.out; mv x.out tmp.out ;

pdf:
	pdflatex $(file).tex

clean:
	rm -f *.aux *.bbl *.blg *.log *.out *.pdf *.toc *.dvi *.ps *~ *.bak *.tmp 