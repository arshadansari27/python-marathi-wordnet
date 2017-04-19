# -*- coding: utf-8 -*-
#!/usr/bin/env jython

import sys;
import re;
import commands;
import pickle
import codecs

sys.path.append("./hindi-wn-simple.jar");
from sivareddy.in import WordnetToolsSimple;

wordnet= WordnetToolsSimple();
wordnet.initialize();

morph= {}   # { word: {pos: [form1, form2]}}
f= codecs.open("output.txt", "w", 'utf-8')


def getRoot(word):
    #word= word.decode("utf-8", "ignore")
    #print [word]
    roots= wordnet.getRoot(word);
    roots= roots.rstrip(";")
    roots= roots.strip()
    if roots=='':
        return;
    f.write("\t%s\n" %(roots))
    roots= roots.split(";")
    #print "\t", roots 
    for root in roots:
        (posid, lemma)= root.split(":")
        if not morph.has_key(word):
            morph[word]= {}
        if not morph[word].has_key(posid):
            morph[word][posid]= []
        morph[word][posid].append(lemma)

if __name__=="__main__":
    words= codecs.open(sys.argv[1], 'r', 'utf-8').readlines()
    for word in words:
        word= word.strip()
        if word=='':
            continue
        word= word.split("\t")[0]
        f.write(word);
        #word= word.decode('utf-8', 'ignore')
        getRoot(word)
    #pickle.dump(morph, open(sys.argv[2], 'w'))
    f.close();
#   simplejson.dump(morph, open(sys.argv[2]+".json", 'w'))
