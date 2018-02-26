package com.github.benhaixiao.text.similarity.string;

import com.github.benhaixiao.text.similarity.SimilarityException;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 15:39
 * Computes the similarity between two strings based on the longest
 * common subsequence. The implementation normalizes by the first
 * string, as proposed by Clough and Stevenson (2011).
 *
 * Paul Clough and Mark Stevenson. 2011. In Language Resources and
 * Evaluation: Special Issue on Plagiarism and Authorship Analysis,
 * 45(1):5-24.
 */
public class LongestCommonSubsequenceNormComparator 	extends LongestCommonSubsequenceComparator
{
    @Override
    public double getSimilarity(String string1, String string2)
            throws SimilarityException
    {
        if (string1.trim().isEmpty()) {
            return 0;
        }
        String lcs = getLCS(string1.toLowerCase(), string2.toLowerCase());

        return (double) lcs.length() / string1.length();
    }
    @Override
    public String getSymbol() {
        return "Longest_common_subsequence_norm";
    }
}


