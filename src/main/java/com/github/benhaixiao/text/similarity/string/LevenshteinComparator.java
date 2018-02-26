package com.github.benhaixiao.text.similarity.string;

import com.github.benhaixiao.text.similarity.SimilarityException;
import com.github.benhaixiao.text.similarity.SimilarityMeasureBase;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 15:36
 * SecondString comparator fails for some texts. Used the implementation from
 * http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
 */
public class LevenshteinComparator extends SimilarityMeasureBase
{
    public LevenshteinComparator()
    {
        // nothing yet
    }

    @Override
    public double getSimilarity(String s1, String s2)
            throws SimilarityException
    {
        return computeLevenshteinDistance(s1, s2);
    }

    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private int computeLevenshteinDistance(CharSequence str1, CharSequence str2)
    {
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            distance[i][0] = i;
        }
        for (int j = 0; j <= str2.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1]
                                + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0
                                : 1));
            }
        }

        return distance[str1.length()][str2.length()];
    }

    @Override
    public double getSimilarity(Collection<String> strings1, Collection<String> strings2)
            throws SimilarityException
    {
        if (strings1.size() == 0 || strings2.size() == 0) {
            return 0.0;
        }

        String concatenatedString1 = StringUtils.join(strings1, " ");
        String concatenatedString2 = StringUtils.join(strings2, " ");

        if (concatenatedString1.length() == 0 || concatenatedString2.length() == 0) {
            return 0.0;
        }

        double distance = getSimilarity(concatenatedString1,  concatenatedString2);

        return distance;
    }
    @Override
    public String getSymbol() {
        return "levenshtein";
    }
}