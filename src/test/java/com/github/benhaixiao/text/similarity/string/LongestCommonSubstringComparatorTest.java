
package com.github.benhaixiao.text.similarity.string;

import static org.junit.Assert.assertEquals;

import com.github.benhaixiao.text.similarity.SimilarityMeasure;
import org.junit.Test;


public class LongestCommonSubstringComparatorTest
{
	private static final double epsilon = 0.0001;
	
    @Test
    public void test()
		throws Exception
	{
		String a1 = "test String1";
		String a2 = "test String2";

		String b1 = "This is my string";
		String b2 = "That is your string";

		SimilarityMeasure measure = new LongestCommonSubstringComparator();

		assertEquals(0.9166, measure.getSimilarity(a1, a2), epsilon);
		assertEquals(0.3888, measure.getSimilarity(b1, b2), epsilon);
   }
}
