
package com.github.benhaixiao.text.similarity.string;


import com.github.benhaixiao.text.similarity.SimilarityMeasure;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class LevenshteinComparatorTest
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

        SimilarityMeasure measure = new LevenshteinComparator();

        assertEquals(1, measure.getSimilarity(a1, a2), epsilon);
        assertEquals(6, measure.getSimilarity(b1, b2), epsilon);
    }

    @Test
    public void testCollection()
        throws Exception
    {
        String[] a1 = "test String1".split(" ");
        String[] a2 = "test String2".split(" ");

        String[] b1 = "This is my string".split(" ");
        String[] b2 = "That is your string".split(" ");

        SimilarityMeasure measure = new LevenshteinComparator();

        assertEquals(1, measure.getSimilarity(a1, a2), epsilon);
        assertEquals(6, measure.getSimilarity(b1, b2), epsilon);
    }
}
