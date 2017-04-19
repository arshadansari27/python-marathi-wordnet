package sivareddy.in;

import in.ac.iitb.cfilt.jhwnl.JHWNL;
import in.ac.iitb.cfilt.jhwnl.JHWNLException;
import in.ac.iitb.cfilt.jhwnl.data.IndexWord;
import in.ac.iitb.cfilt.jhwnl.data.POS;
import in.ac.iitb.cfilt.jhwnl.data.Pointer;
import in.ac.iitb.cfilt.jhwnl.data.PointerType;
import in.ac.iitb.cfilt.jhwnl.data.Synset;
import in.ac.iitb.cfilt.jhwnl.data.Word;
import in.ac.iitb.cfilt.jhwnl.dictionary.Dictionary;

public class WordnetToolsSimple {

	public static Dictionary dict;

	public static void initialize() {
		JHWNL.initialize(); // "HindiWN.properties";
		dict = Dictionary.getInstance();
	}

	public static String getRoot(String word) {
		String wnIDs = "";
		try {
			// IndexWord I=dict.lookupIndexWord(POS.NOUN, word);
			// System.out.println(I.getLemma());
			IndexWord[] I = dict.lookupMorphedIndexWords(POS.NOUN, word).getIndexWordArray();
			for (int i = 0; i < I.length; i++) {
				wnIDs += "1:" + I[i].getLemma() + ";";
			}

			I = dict.lookupMorphedIndexWords(POS.ADJECTIVE, word).getIndexWordArray();
			for (int i = 0; i < I.length; i++) {
				wnIDs += "2:" + I[i].getLemma() + ";";
			}

			I = dict.lookupMorphedIndexWords(POS.VERB, word).getIndexWordArray();
			for (int i = 0; i < I.length; i++) {
				wnIDs += "3:" + I[i].getLemma() + ";";
			}

			I = dict.lookupMorphedIndexWords(POS.ADVERB, word).getIndexWordArray();
			for (int i = 0; i < I.length; i++) {
				wnIDs += "4:" + I[i].getLemma() + ";";
			}

		} catch (Exception e) {
			System.err.println("Something wrong.. empty string returned");
		}
		return wnIDs;

	}

	public static String getwnIDs(String word, String posid) {
		String wnIDs = "";
		System.out.println("JAVA WORD--> " + word);
		try {
			// IndexWord I=dict.lookupIndexWord(POS.NOUN, word);
			// System.out.println(I.getLemma());
			if (posid.equals("1")) {
				IndexWord[] I = dict.lookupMorphedIndexWords(POS.NOUN, word).getIndexWordArray();
				for (int i = 0; i < I.length; i++) {
					long[] synsets = I[i].getSynsetOffsets();
					for (int j = 0; j < synsets.length; j++) {
						wnIDs += Long.toString(synsets[j]) + ";";
					}
				}
			} else if (posid.equals("2")) {
				IndexWord[] I = dict.lookupMorphedIndexWords(POS.ADJECTIVE, word).getIndexWordArray();
				for (int i = 0; i < I.length; i++) {
					long[] synsets = I[i].getSynsetOffsets();
					for (int j = 0; j < synsets.length; j++) {
						wnIDs += Long.toString(synsets[j]) + ";";
					}
				}
			} else if (posid.equals("3")) {
				IndexWord[] I = dict.lookupMorphedIndexWords(POS.VERB, word).getIndexWordArray();
				for (int i = 0; i < I.length; i++) {
					long[] synsets = I[i].getSynsetOffsets();
					for (int j = 0; j < synsets.length; j++) {
						wnIDs += Long.toString(synsets[j]) + ";";
					}
				}
			}

			else if (posid.equals("4")) {
				IndexWord[] I = dict.lookupMorphedIndexWords(POS.ADVERB, word).getIndexWordArray();
				for (int i = 0; i < I.length; i++) {
					long[] synsets = I[i].getSynsetOffsets();
					for (int j = 0; j < synsets.length; j++) {
						wnIDs += Long.toString(synsets[j]) + ";";
					}
				}
			}

			// System.out.println(wnIDs);
			// lemma= morph.lookupBaseForm(POS.NOUN, word).getLemma();
			// lemma= dict.getMorphologicalProcessor().lookupBaseForm(POS.NOUN,
			// word).getLemma();
		} catch (Exception e) {
			System.err.println("Something wrong.. empty string returned");
		}
		return wnIDs;
	}

	public static String process(String ontoString) {
		String ret = "";
		for (int i = 0; i < ontoString.length(); i++) {
			if (ontoString.charAt(i) == '(') {
				int ans = 1;
				String temp = "";
				for (int j = i + 1; j < ontoString.length(); j++) {
					if (ontoString.charAt(j) == ')')
						break;
					if (ontoString.charAt(j) == ' ' || (ontoString.charAt(j) >= 'a' && ontoString.charAt(j) <= 'z')
							|| (ontoString.charAt(j) >= 'A' && ontoString.charAt(j) <= 'Z')) {
					} else {
						ans = 0;
					}
					temp += ontoString.charAt(j);

				}
				if (ans == 1) {
					if (ret == "") {
						ret += temp;
					} else {
						ret += "," + temp;
					}

					if (temp.indexOf("Top Level Node") != -1) {
						ret += ";";
					}
				}
			}
		}
		return ret;
	}

	public static String getGlossfromID(String SynsetID, String posid) throws JHWNLException {
		try {
			Synset s = null;
			if (posid.equals("1")) {
				s = dict.getSynsetAt(POS.NOUN, Integer.parseInt(SynsetID));
			} else if (posid.equals("3")) {
				s = dict.getSynsetAt(POS.VERB, Integer.parseInt(SynsetID));
			} else if (posid.equals("4")) {
				s = dict.getSynsetAt(POS.ADVERB, Integer.parseInt(SynsetID));
			} else if (posid.equals("2")) {
				s = dict.getSynsetAt(POS.ADJECTIVE, Integer.parseInt(SynsetID));

			}
			if (s == null)
				return "";
			System.out.println(s.getGloss());
			return s.getGloss();
		} catch (Exception e) {
			System.err.println("Something wrong.. empty string returned");
			return "";
		}
	}

	public static String getHypernym(String SynsetID, String posid) throws JHWNLException {
		try {
			Synset s = null;
			if (posid.equals("1")) {
				s = dict.getSynsetAt(POS.NOUN, Integer.parseInt(SynsetID));
			} else if (posid.equals("3")) {
				s = dict.getSynsetAt(POS.VERB, Integer.parseInt(SynsetID));
			} else if (posid.equals("4")) {
				s = dict.getSynsetAt(POS.ADVERB, Integer.parseInt(SynsetID));
			} else if (posid.equals("2")) {
				s = dict.getSynsetAt(POS.ADJECTIVE, Integer.parseInt(SynsetID));
			}

			if (s == null)
				return "";

			String hypernyms = "";
			Pointer[] pointers = s.getPointers();
			for (int j = 0; j < pointers.length; j++) {
				if (pointers[j].getType().equals(PointerType.HYPERNYM)) {
					if (pointers[j].getTargetSynset() != null)
						hypernyms += pointers[j].getTargetSynset().getOffset() + "\t";
					// hypernyms += pointers[j].getTargetSynset().toString() +
					// "\t";
				}
			}
			return hypernyms;
		} catch (Exception e) {
			System.err.println("Something wrong.. empty string returned");
			return "";
		}
	}

	public static String getHyponym(String SynsetID, String posid) throws JHWNLException {
		try {
			Synset s = null;
			if (posid.equals("1")) {
				s = dict.getSynsetAt(POS.NOUN, Integer.parseInt(SynsetID));
			} else if (posid.equals("3")) {
				s = dict.getSynsetAt(POS.VERB, Integer.parseInt(SynsetID));
			} else if (posid.equals("4")) {
				s = dict.getSynsetAt(POS.ADVERB, Integer.parseInt(SynsetID));
			} else if (posid.equals("2")) {
				s = dict.getSynsetAt(POS.ADJECTIVE, Integer.parseInt(SynsetID));
			}

			if (s == null)
				return "";

			String hyponyms = "";
			Pointer[] pointers = s.getPointers();
			for (int j = 0; j < pointers.length; j++) {
				if (pointers[j].getType().equals(PointerType.HYPONYM)) {
					if (pointers[j].getTargetSynset() != null)
						hyponyms += pointers[j].getTargetSynset().getOffset() + "\t";
					// hypernyms += pointers[j].getTargetSynset().toString() +
					// "\t";
				}
			}
			return hyponyms;
		} catch (Exception e) {
			System.err.println("Something wrong.. empty string returned");
			return "";
		}
	}

	public static String getSynsetWords(String SynsetID, String posid) throws JHWNLException {
		try {
			Synset s = null;
			if (posid.equals("1")) {
				s = dict.getSynsetAt(POS.NOUN, Integer.parseInt(SynsetID));
			} else if (posid.equals("3")) {
				s = dict.getSynsetAt(POS.VERB, Integer.parseInt(SynsetID));
			} else if (posid.equals("4")) {
				s = dict.getSynsetAt(POS.ADVERB, Integer.parseInt(SynsetID));
			} else if (posid.equals("2")) {
				s = dict.getSynsetAt(POS.ADJECTIVE, Integer.parseInt(SynsetID));
			}

			if (s == null)
				return "";
			Word[] words = s.getWords();
			String words_str = "";
			for (int i = 0; i < words.length; i++) {
				words_str += words[i].toString() + "\t";
			}
			return words_str;
		} catch (Exception e) {
			System.err.println("Something wrong.. empty string returned");
			return "";
		}
	}

	public static String getSemcatfromID(String SynsetID, String posid) throws JHWNLException {
		try {
			Synset s;
			String ret = "";
			if (posid.equals("1")) {
				s = dict.getSynsetAt(POS.NOUN, Integer.parseInt(SynsetID));
				if (s != null) {
					Pointer[] pointers = s.getPointers();
					for (int j = 0; j < pointers.length; j++) {
						if (pointers[j].getType().equals(PointerType.ONTO_NODES)) {
							if (dict.getOntoSynset(pointers[j].getOntoPointer()) != null)
								ret += process(dict.getOntoSynset(pointers[j].getOntoPointer()).getOntoNodes());
						}
					}
				}
			} else if (posid.equals("3")) {
				s = dict.getSynsetAt(POS.VERB, Integer.parseInt(SynsetID));
				if (s != null) {
					Pointer[] pointers = s.getPointers();
					for (int j = 0; j < pointers.length; j++) {
						if (pointers[j].getType().equals(PointerType.ONTO_NODES)) {
							if (dict.getOntoSynset(pointers[j].getOntoPointer()) != null)
								ret += process(dict.getOntoSynset(pointers[j].getOntoPointer()).getOntoNodes());
						}
					}
				}
			} else if (posid.equals("4")) {
				s = dict.getSynsetAt(POS.ADVERB, Integer.parseInt(SynsetID));
				if (s != null) {
					Pointer[] pointers = s.getPointers();
					for (int j = 0; j < pointers.length; j++) {
						if (pointers[j].getType().equals(PointerType.ONTO_NODES)) {
							if (dict.getOntoSynset(pointers[j].getOntoPointer()) != null)
								ret += process(dict.getOntoSynset(pointers[j].getOntoPointer()).getOntoNodes());
						}
					}
				}
			} else if (posid.equals("2")) {
				s = dict.getSynsetAt(POS.ADJECTIVE, Integer.parseInt(SynsetID));
				if (s != null) {
					Pointer[] pointers = s.getPointers();
					for (int j = 0; j < pointers.length; j++) {
						if (pointers[j].getType().equals(PointerType.ONTO_NODES)) {
							if (dict.getOntoSynset(pointers[j].getOntoPointer()) != null)
								ret += process(dict.getOntoSynset(pointers[j].getOntoPointer()).getOntoNodes());
						}
					}
				}
			}

			return ret;

		} catch (Exception e) {
			System.err.println("Something wrong.. empty string returned");
			return "";
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws JHWNLException {
		// TODO Auto-generated method stub
		initialize();
		System.out.println(getRoot("बच्चे"));
		System.out.println(getRoot("से"));
		// System.out.println(getwnIDs("रंग", "1"));
		System.out.println(getwnIDs("हस्बेमामूल", "4"));
		System.out.println(getGlossfromID("24615", "4"));
		System.out.println(getwnIDs("रंग", "1"));
		System.out.println(getGlossfromID("282", "1"));
		System.out.println(getHypernym("282", "1"));
		System.out.println(getHypernym("744", "1"));
		System.out.println(getHypernym("923", "1"));
		System.out.println(getHypernym("3259", "1"));
		System.out.println(getwnIDs("द्वारा", "1"));
		System.out.println(getHypernym("798", "1"));
		System.out.println(getHypernym("5828", "1"));
		System.out.println(getSemcatfromID("282", "1"));
		System.out.println(getHyponym("5828", "1"));
		System.out.println(getSynsetWords("5828", "1"));
		/*
		 * System.out.println(getSemcatfromID("7298"));
		 * System.out.println(getSemcatfromID("7083"));
		 * System.out.println(getSemcatfromID("7943"));
		 * System.out.println(getSemcatfromID("7963"));
		 */
	}
}
