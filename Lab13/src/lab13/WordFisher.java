package lab13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * The WordFisher class analyzes word frequency in a text file, allowing for 
 * operations such as pruning stopwords and finding common popular words.
 */
public class WordFisher {

    /**
     * A map containing words as keys and their occurrence frequencies as values.
     */
    public HashMap<String, Integer> vocabulary;

    /**
     * A list of stopwords to exclude from word frequency analysis.
     */
    public List<String> stopwords;

    /**
     * The file path of the input text file.
     */
    private String inputTextFile;

    /**
     * The file path of the stopwords file.
     */
    private String stopwordsFile;

    /**
     * Constructs a WordFisher object and initializes its state.
     *
     * @param inputTextFile  the path to the text file for analysis
     * @param stopwordsFile  the path to the file containing stopwords
     */
    WordFisher(String inputTextFile, String stopwordsFile) {
        this.inputTextFile = inputTextFile;
        this.stopwordsFile = stopwordsFile;

        buildVocabulary();
        getStopwords();
    }

	/**
     * Reads stopwords from the specified stopwords file and stores them in the 
     * stopwords list.
     */
    public void getStopwords() {
        stopwords = new ArrayList<String>();
        String word;

        try {
            BufferedReader input = new BufferedReader(new FileReader(stopwordsFile));
            while ((word = input.readLine()) != null) {
                stopwords.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds the vocabulary by reading words from the input text file and 
     * counting their frequencies.
     */
    public void buildVocabulary() {
        vocabulary = new HashMap<String, Integer>();

        // TODO: Part 1
        // By the end of this method, vocabulary should map each word to the number of
        // times it occurs in inputTextFile.
        // Therefore, as you iterate over words, increase the value that the word maps
        // to in vocabulary by 1.
        // If it's not in the vocabulary, then add it with an occurrence of 1.
        // Use getStopwords as an example of reading from files.
        
        
        try {
            // Read the entire text file into a string
            String reader = new String(Files.readAllBytes(Paths.get(inputTextFile)));

            // Filter the text for non-alphanumeric characters and convert it to lowercase
            String[] allWords = reader.split("\\s+");

            // Iterate through each word in the array
            for (String word : allWords) {
                // Skip empty strings (e.g., caused by extra spaces)
                word = word.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
            	if (word.isEmpty()) {
                    vocabulary.put(word, vocabulary.getOrDefault(word, 0) + 1);

            	}

                // Update the word count in the vocabulary map
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Returns the total number of words in the input text file based on the 
     * vocabulary.
     *
     * @return the total word count
     */
    public int getWordCount() {
        // TODO: Part 2
        // HINT: This can be calculated using vocabulary.
        int wordCount = 0;
        
        Collection<Integer> numberOfWordsInText = vocabulary.values();
        
        for(int numberOfWords : numberOfWordsInText) {
        	wordCount += numberOfWords;
        }
        
        return wordCount;
    }

    /**
     * Returns the number of unique words in the vocabulary.
     *
     * @return the number of unique words
     */
    public int getNumUniqueWords() {
        // TODO: Part 3
        // HINT: This should be the same as the number of keys in vocabulary.
        return vocabulary.size();
    }

    /**
     * Returns the frequency of a specific word in the vocabulary.
     *
     * @param word  the word whose frequency is to be returned
     * @return the frequency of the word, or -1 if the word is not in the vocabulary
     */
    public int getFrequency(String word) {
        if (vocabulary.containsKey(word)) {
            // TODO: Part 4
            // HINT: Should be one simple line of code. Think about what vocabulary stores.
        	return vocabulary.get(word);
        }
        return -1;
    }

    /**
     * Removes all stopwords from the vocabulary.
     */
    public void pruneVocabulary() {
        // TODO: Part 5
    	Iterator<String> wordIterator = vocabulary.keySet().iterator();
    	
    	while (wordIterator.hasNext()) {
    		String word = wordIterator.next();
    		if (stopwords.contains(word)) {
    			wordIterator.remove();
    		}
    	}
    		
    }

    /**
     * Retrieves the top n words from the vocabulary based on their frequency.
     *
     * @param n  the number of top words to retrieve
     * @return a list of the top n words
     */
    public ArrayList<String> getTopWords(int n) {
        ArrayList<String> topWords = new ArrayList<String>();

        // TODO: Part 6
        
        class WordNode {
            String word;
            int frequency;

            WordNode(String word, int frequency) {
                this.word = word;
                this.frequency = frequency;
            }
        }
        
        class WordNodeComparator implements java.util.Comparator<WordNode> {
            @Override
            public int compare(WordNode o1, WordNode o2) {
                return Integer.compare(o2.frequency, o1.frequency); // Descending order
            }
        }

        // PriorityQueue for top words
        java.util.PriorityQueue<WordNode> pq = new java.util.PriorityQueue<>(new WordNodeComparator());

        // Add all vocabulary words to PriorityQueue
        for (var entry : vocabulary.entrySet()) {
            pq.offer(new WordNode(entry.getKey(), entry.getValue()));
        }

        // Extract top n words
        for (int i = 0; i < n && !pq.isEmpty(); i++) {
            topWords.add(pq.poll().word);
        }

        return topWords;
    }

    /**
     * Finds the common popular words between this WordFisher instance and another.
     *
     * @param n     the number of top words to consider
     * @param other another WordFisher instance to compare with
     * @return a list of common popular words
     */
    public ArrayList<String> commonPopularWords(int n, WordFisher other) {
        ArrayList<String> commonPopWords = new ArrayList<String>();

        // TODO: Part 7
     // Get top n words from both vocabularies
        ArrayList<String> topWordsThis = this.getTopWords(n);
        ArrayList<String> topWordsOther = other.getTopWords(n);

        // Use a nested loop to find common words
        for (String word : topWordsThis) {
            if (topWordsOther.contains(word)) {
                commonPopWords.add(word);
            }
        }

        return commonPopWords;
    }

}
