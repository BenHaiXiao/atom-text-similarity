package com.github.benhaixiao.text.similarity;

import com.github.benhaixiao.text.similarity.ngrams.CharacterNGramMeasureTest;
import com.github.benhaixiao.text.similarity.ngrams.WordNGramMeasureTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 16:37
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CharacterNGramMeasureTest.class,
        WordNGramMeasureTest.class,
        com.github.benhaixiao.text.similarity.string.CosineSimilarityTest.class,
        com.github.benhaixiao.text.similarity.string.GreedyStringTilingTest.class,
        com.github.benhaixiao.text.similarity.string.LevenshteinComparatorTest.class,
        com.github.benhaixiao.text.similarity.string.LongestCommonSubsequenceComparatorTest.class,
        com.github.benhaixiao.text.similarity.string.LongestCommonSubstringComparatorTest.class,
        com.github.benhaixiao.text.similarity.string.MongeElkanSecondStringComparatorTest.class,
        com.github.benhaixiao.text.similarity.string.SimpleStringMatchComparatorTest.class
})
public class SimilarityTestSuite {
}
