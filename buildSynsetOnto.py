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

dict= {}    # { synsetid: {pos: [ont_cat1, ont_cat2...]}}

def getOnto(id):
    print id
    for i in range(1,5):
        cats= wordnet.getSemcatfromID(id, str(i)).split(";"); 
        vcats= []
        for cat in cats:
            elements= cat.split(',')
            cat= ','.join(elements[:-1])
            cat= cat.replace(' ', '_')
            if cat!='' and cat not in vcats:
                vcats.append(cat)
        if vcats==[]:
            continue
        if not dict.has_key(id):
            dict[id]= {}
        dict[id][str(i)]= vcats
        print vcats

   
if __name__=="__main__":
    for i in range(34115):
        getOnto(str(i))
    pickle.dump(dict, open(sys.argv[1], "w"))
