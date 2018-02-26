
package com.github.benhaixiao.text.similarity.string;



import com.github.benhaixiao.text.similarity.SimilarityMeasure;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GreedyStringTilingTest
{
	private static final double epsilon = 0.001;
	
    @Test
    public void test()
		throws Exception
	{
		String a1 = "The quick brown fox jumps over the lazy dog.";
		String a2 = "Lorem ipsum quick brown dolor, consectetur over the nomen tuum.";

		SimilarityMeasure measure = new GreedyStringTiling(3);

		assertEquals(0.522, measure.getSimilarity(a1, a2), epsilon);
   }
}
