package com.github.benhaixiao.text.similarity.string;

import com.wcohen.ss.AbstractStringDistance;
import com.wcohen.ss.api.StringWrapper;
import com.github.benhaixiao.text.similarity.SimilarityException;
import com.github.benhaixiao.text.similarity.SimilarityMeasureBase;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 15:34
 */
public abstract class SecondStringComparator_ImplBase   extends SimilarityMeasureBase
{

    protected AbstractStringDistance secondStringMeasureL1;
    protected AbstractStringDistance secondStringMeasureL2;

    public enum SecondStringTextSimilarityMeasure {
        Jaro,
        JaroWinkler,
        Levenshtein,
        MongeElkan
    }

    @Override
    public double getSimilarity(String s1, String s2)
            throws SimilarityException
    {
        // preparing the string saves some cycles through caching, if many comparisons with the same string are done.
        StringWrapper wrappedString1 = secondStringMeasureL1.prepare(s1);
        StringWrapper wrappedString2 = secondStringMeasureL1.prepare(s2);

        return secondStringMeasureL1.score(wrappedString1, wrappedString2);
    }

    @Override
    public double getSimilarity(Collection<String> s1, Collection<String> s2)
            throws SimilarityException
    {
        if (s1.size() == 0 || s2.size() == 0) {
            return 0.0;
        }

        String concatenatedString1 = StringUtils.join(s1, " ");
        String concatenatedString2 = StringUtils.join(s2, " ");

        if (concatenatedString1.length() == 0 || concatenatedString2.length() == 0) {
            return 0.0;
        }

        // find tokens (I know that we already know what the tokens are, but the SecondString implementation needs it that way)
        StringWrapper wrappedString1 = secondStringMeasureL2.prepare(concatenatedString1);
        StringWrapper wrappedString2 = secondStringMeasureL2.prepare(concatenatedString2);

        double distance = secondStringMeasureL2.score(wrappedString1, wrappedString2);

        return distance;
    }
}