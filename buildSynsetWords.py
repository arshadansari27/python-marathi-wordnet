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

dict= {}    # { synsetid: {pos: gloss}}

def getSynsetWords(id):
    print id
    for i in range(1,5):
        words= wordnet.getSynsetWords(id, str(i)).strip()
        if words=="":
            continue
        if not dict.has_key(id):
            dict[id]= {}
        dict[id][str(i)]= words.encode('utf-8', 'ignore').decode('utf-8', 'ignore').split()
   
if __name__=="__main__":
    for i in range(34115):
        getSynsetWords(str(i))
    pickle.dump(dict, open(sys.argv[1], "w"))
