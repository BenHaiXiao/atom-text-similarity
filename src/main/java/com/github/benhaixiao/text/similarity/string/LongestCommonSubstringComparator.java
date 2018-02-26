package com.github.benhaixiao.text.similarity.string;

import com.github.benhaixiao.text.similarity.SimilarityException;
import com.github.benhaixiao.text.similarity.SimilarityMeasureBase;

import com.github.benhaixiao.text.similarity.string.util.gst.GeneralizedSuffixTree;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 15:39
 */
public class LongestCommonSubstringComparator 	extends SimilarityMeasureBase
{
    @Override
    public double getSimilarity(Collection<String> stringList1,
                                Collection<String> stringList2)
            throws SimilarityException
    {
        return getSimilarity(StringUtils.join(stringList1, " "), StringUtils.join(stringList2, " "));
    }

    /**
     * Considers both parameters to be full texts, not individual terms.
     *
     * Implementation based on generalized suffix trees:
     * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
     *
     * Direct download:
     * http://illya.yolasite.com/resources/suffix-tree-6.zip
     */
    @Override
    public double getSimilarity(String string1, String string2)
            throws SimilarityException
    {
        if (string1.trim().isEmpty()) {
            return 0;
        }
        String lcs = getLCS(string1, string2);

        // Normalize
        double numerator = string1.length() + string2.length() - 2 * lcs.length();
        double denominator = string1.length() + string2.length();
        double score = 1.0 - (numerator / denominator);

        return score;
    }

    private String getLCS(String string1, String string2)
    {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree(string1, string2);
        return gst.getLcsAsString();
    }
    @Override
    public String getSymbol() {
        return "longest_commonS_substring";
    }

}

