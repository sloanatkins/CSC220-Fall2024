package lab13;

public class WordFisherTester {
	
	public static void main(String[] args) {
		
		WordFisher alice = new WordFisher("texts/carroll-alice.txt", "stopwords.txt");
		
		WordFisher moby = new WordFisher("texts/moby-dick.txt", "stopwords.txt");
		
		//*****getWordCount() Tests*****
		//The total number of words in Moby Dick is 218,619. Alice has 27,336.
		//The word count may be off by 1 depending on your Operating System. You will receive full credit if you are off by 1 word.	
        System.out.println("***** Testing getWordCount() *****");
        System.out.println("Word count in Alice: " + alice.getWordCount());
        System.out.println("Word count in Moby Dick: " + moby.getWordCount());

        //*****getNumUniqueWords() Tests*****
        //The total number of unique words in Moby Dick is 17,139 and Alice 2,570. 
      	//The word count may be off by 1 depending on your Operating System. You will receive full credit if you are off by 1 word.
        System.out.println("\n***** Testing getNumUniqueWords() *****");
        System.out.println("Unique words in Alice: " + alice.getNumUniqueWords());
        System.out.println("Unique words in Moby Dick: " + moby.getNumUniqueWords());

		//*****getFrequency(String word) Tests*****
		//The word “whale” occurs 1,226 times in Moby Dick! 
		//“handkerchief”  occurs 5 times in Moby Dick 
		//"handkerchief" does not occur in Alice (thus, returns -1).  
        System.out.println("\n***** Testing getFrequency(String word) *****");
        System.out.println("Frequency of 'whale' in Moby Dick: " + moby.getFrequency("whale"));
        System.out.println("Frequency of 'handkerchief' in Moby Dick: " + moby.getFrequency("handkerchief"));
        System.out.println("Frequency of 'handkerchief' in Alice: " + alice.getFrequency("handkerchief"));

        //*****pruneVocabulary() Tests*****
        //After pruning, getWordCount() on Moby Dick returns 110,717 words; Alice  returns 12,241
      	//The word count may be off by 1 depending on your Operating System. You will receive full credit if you are off by 1 word. 
        System.out.println("\n***** Testing pruneVocabulary() *****");
        alice.pruneVocabulary();
        moby.pruneVocabulary();
        System.out.println("Word count in pruned Alice: " + alice.getWordCount());
        System.out.println("Word count in pruned Moby Dick: " + moby.getWordCount());

        //*****getTopWords(int n) Tests*****
		//When calling getTopWords(10) on the pruned vocabulary of Moby Dick, the  following list is returned: [whale, one, like, upon, man, ship, ahab, ye, sea, old]
        System.out.println("\n***** Testing getTopWords(int n) *****");
        System.out.println("Top 10 words in pruned Alice: " + alice.getTopWords(10));
        System.out.println("Top 10 words in pruned Moby Dick: " + moby.getTopWords(10));

        //*****common PopularWords(int n, WordFisher other) Tests*****
		//Calling this method on the pruned Moby Dick with pruned Alice and  n = 20 gives [one, like, would, time]	
        System.out.println("\n***** Testing commonPopularWords(int n, WordFisher other) *****");
        System.out.println("Common top 20 words between pruned Alice and Moby Dick: " + alice.commonPopularWords(20, moby));
	}
}
