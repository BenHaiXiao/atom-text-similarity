package com.github.benhaixiao.text.similarity.ngrams;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 14:59
 * Similar to the {@link WordNGramJaccardMeasure}, but uses the containment
 * measure by Broder (1997) to compute the n-gram overlap:
 *
 * Andrei Z. Broder. 1997. On the resemblance and containment of documents
 * In Proceedings of the Compression and Complexity of Sequences, Salerno,
 * Italy, pages 21–29.
 * <a href="http://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=666900">(pdf)</a> *
 */
public class WordNGramContainmentMeasure extends WordNGramJaccardMeasure{
    public WordNGramContainmentMeasure()
    {
        // Default constructor uses trigrams
        super(3);
    }

    public WordNGramContainmentMeasure(int n)
    {
        super(n);
    }

    @Override
    protected double getNormalizedSimilarity(Set<List<String>> suspiciousNGrams, Set<List<String>> originalNGrams)
    {
        // Compare using the Containment measure (Broder, 1997)
        Set<List<String>> commonNGrams = new HashSet<List<String>>();
        commonNGrams.addAll(suspiciousNGrams);
        commonNGrams.retainAll(originalNGrams);

        double norm = suspiciousNGrams.size();
        double sim = 0.0;

        if (norm > 0.0)
            sim = commonNGrams.size() / norm;

        return sim;
    }
    @Override
    public String getSymbol() {
        return "word_ngram_containment";
    }
}
