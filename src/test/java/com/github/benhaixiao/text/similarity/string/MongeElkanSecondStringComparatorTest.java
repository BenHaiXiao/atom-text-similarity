
package com.github.benhaixiao.text.similarity.string;

import static org.junit.Assert.assertEquals;

import com.github.benhaixiao.text.similarity.SimilarityMeasure;
import org.junit.Test;


public class MongeElkanSecondStringComparatorTest
{
	private static final double epsilon = 0.0001;
	
    @Test
    public void test()
		throws Exception
	{
		String a1 = "test String";
		String a2 = "test Strimg";

		SimilarityMeasure measure = new MongeElkanSecondStringComparator();

		assertEquals(.9636, measure.getSimilarity(a1, a2), epsilon);
   }
}
