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

def getGloss(id):
    print id,
    for i in range(1,5):
        gloss= wordnet.getGlossfromID(id, str(i)).strip()
        if gloss=="":
            continue
        if not dict.has_key(id):
            dict[id]= {}
        dict[id][str(i)]= gloss.encode('utf-8', 'ignore').decode('utf-8', 'ignore')
   
if __name__=="__main__":
    for i in range(34115):
        getGloss(str(i))
    pickle.dump(dict, open(sys.argv[1], "w"))
