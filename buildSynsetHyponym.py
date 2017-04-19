# -*- coding: utf-8 -*-
#!/usr/bin/env jython

import sys
import re
import commands
import pickle
import codecs

sys.path.append("./hindi-wn-simple.jar");
from sivareddy.in import WordnetToolsSimple;

wordnet= WordnetToolsSimple();
wordnet.initialize();

dict= {}    # { synsetid: {pos: [hyp1, hyp2]}}

def getHyponym(id):
    print id
    for i in range(1,5):
        hyponym= wordnet.getHyponym(id, str(i)).strip()
        if hyponym=="":
            continue
        if not dict.has_key(id):
            dict[id]= {}
        dict[id][i]= hyponym.split()
   
if __name__=="__main__":
    for i in range(34115):
        getHyponym(str(i))
    pickle.dump(dict, open(sys.argv[1], "w"))
