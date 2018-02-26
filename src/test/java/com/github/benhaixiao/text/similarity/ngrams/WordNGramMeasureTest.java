
package com.github.benhaixiao.text.similarity.ngrams;

import static org.junit.Assert.assertEquals;


import com.github.benhaixiao.text.similarity.SimilarityMeasure;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;

public class WordNGramMeasureTest
{
	private static final double epsilon = 0.001;
	
    @Test
    public void jaccardTest()
		throws Exception
	{
        List<String> l1 = asList("The quick brown fox jumps over the lazy dog".split(" "));
        List<String> l2 = asList("The quick brown fox jumps over the lazy dog".split(" "));
        List<String> l3 = asList("The quick brown dog jumps".split(" "));

		SimilarityMeasure measure = new WordNGramJaccardMeasure(3);

		assertEquals(1.0, measure.getSimilarity(l1, l2), epsilon);
		assertEquals(0.111, measure.getSimilarity(l1, l3), epsilon);
	}

    @Test
    public void containmentTest()
		throws Exception
	{
        List<String> l1 = asList("The quick brown fox jumps over the lazy dog".split(" "));
        List<String> l2 = asList("The quick brown fox jumps over the lazy dog".split(" "));
        List<String> l3 = asList("The quick brown dog jumps".split(" "));

		SimilarityMeasure measure = new WordNGramContainmentMeasure(3);

		assertEquals(1.0, measure.getSimilarity(l1, l2), epsilon);
		assertEquals(0.142, measure.getSimilarity(l1, l3), epsilon);
   }
}
