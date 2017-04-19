all: SynsetGloss.pk SynsetHypernym.pk SynsetHyponym.pk SynsetOnto.pk SynsetWords.pk WordSynsetDict.pk
	mv *.pk  hindi_wordnet_python

SynsetGloss.pk:
	java -jar jython-standalone-2.5.3.jar buildSynsetGloss.py $@
SynsetHypernym.pk:
	java -jar jython-standalone-2.5.3.jar buildSynsetHypernym.py $@
SynsetHyponym.pk:
	java -jar jython-standalone-2.5.3.jar buildSynsetHyponym.py $@
SynsetOnto.pk:
	java -jar jython-standalone-2.5.3.jar buildSynsetOnto.py $@
SynsetWords.pk:
	java -jar jython-standalone-2.5.3.jar buildSynsetWords.py $@
WordSynsetDict.pk: words_txt
	java -jar jython-standalone-2.5.3.jar buildWordSynsetDict.py $< $@
words_txt:
	cut -f1 -d\  database/idxadjective_txt database/idxadverb_txt database/idxnoun_txt database/idxverb_txt | sort -u > words_txt
