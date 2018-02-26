
package com.github.benhaixiao.text.similarity.ngrams;


import com.github.benhaixiao.text.similarity.SimilarityMeasure;
import com.github.benhaixiao.text.similarity.SimilarityMeasureSelector;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class CharacterNGramMeasureTest
{
	private static final double epsilon = 0.001;
	
    @Test
    public void test()
		throws Exception
	{
    	Map<String,Double> idfValues = new HashMap<String,Double>();
    	idfValues.put("the", 0.01);
    	idfValues.put("ick", 0.3);
    	idfValues.put("fox", 2.5);
    	idfValues.put("dog", 2.1);
    	idfValues.put("laz", 0.28);
    	idfValues.put("azy", 0.27);
    	
		String a1 = "The quick brown fox jumps over the lazy dog.";
		String a2 = "The quick brown dog jumps.";
		
		String b1 = "The quick brown fox jumps over the lazy dog.";
		String b2 = "The quick brown fox jumps.";

		SimilarityMeasure measure = new CharacterNGramMeasure(3, idfValues);
//		SimilarityMeasure measure =  SimilarityMeasureSelector.getInstance().select("character_ngram");
		assertEquals(0.117, measure.getSimilarity(a1, a2), epsilon);
		assertEquals(0.988, measure.getSimilarity(b1, b2), epsilon);
   }
}
