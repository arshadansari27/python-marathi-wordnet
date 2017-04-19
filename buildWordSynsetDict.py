# -*- coding: utf-8 -*-
#!/usr/bin/env jython

import sys;
import re;
import commands;
import pickle


sys.path.append("./hindi-wn-simple.jar");
from sivareddy.in import WordnetToolsSimple;

wordnet= WordnetToolsSimple();
wordnet.initialize();

dict= {}   # { word: {pos: [synset1, synset2]}}

def getSynsets(word):
    print [word]
    for i in range(1, 5):
        synsets= wordnet.getwnIDs(word, str(i));
        #print synsets
        synsets= synsets.rstrip(";")
        synsets= synsets.split(";")
        if synsets==['']:
            continue;
        print "\t", synsets
        if not dict.has_key(word):
            dict[word]= {}
        dict[word][str(i)]= synsets

if __name__=="__main__":
    words= open(sys.argv[1], 'r').readlines()
    for word in words:
        word= word.strip()
        word= " ".join(word.split("_"))
        if word=='':
            continue
        word= word.split("\t")[0]
        word= word.decode('utf-8', 'ignore')
        getSynsets(word)
    pickle.dump(dict, open(sys.argv[2], 'w'))
