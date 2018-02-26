package com.github.benhaixiao.text.similarity;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/16
 * Time: 18:23
 */
public class SimilarityMeasureSelector {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimilarityMeasureSelector.class);

    private static final SimilarityMeasureSelector INSTANCE = new SimilarityMeasureSelector();

    private final Map<String, SimilarityMeasure> similarityMeasureMap = Maps.newHashMap();

    private SimilarityMeasureSelector() {
        init();
    }

    public static SimilarityMeasureSelector getInstance() {
        return INSTANCE;
    }

    private void init() {
        Set<Class<? extends SimilarityMeasure>> reg = Sets.newHashSet();
        reg.add(com.github.benhaixiao.text.similarity.string.SubstringMatchComparator.class);
        reg.add(com.github.benhaixiao.text.similarity.string.MongeElkanSecondStringComparator.class);
        reg.add(com.github.benhaixiao.text.similarity.string.LongestCommonSubstringComparator.class);
        reg.add(com.github.benhaixiao.text.similarity.string.LongestCommonSubsequenceNormComparator.class);
        reg.add(com.github.benhaixiao.text.similarity.string.LevenshteinSecondStringComparator.class);
        reg.add(com.github.benhaixiao.text.similarity.string.LevenshteinComparator.class);
        reg.add(com.github.benhaixiao.text.similarity.string.JaroWinklerSecondStringComparator.class);
        reg.add(com.github.benhaixiao.text.similarity.string.GreedyStringTiling.class);
        reg.add(com.github.benhaixiao.text.similarity.string.ExactStringMatchComparator.class);
        reg.add(com.github.benhaixiao.text.similarity.string.BoundedSubstringMatchComparator.class);
        reg.add(com.github.benhaixiao.text.similarity.ngrams.Word3GramJaccardMeasure.class);
        reg.add(com.github.benhaixiao.text.similarity.ngrams.Word3GramContainmentMeasure.class);
        reg.add(com.github.benhaixiao.text.similarity.ngrams.CharacterNGramMeasure.class);
        reg.add(com.github.benhaixiao.text.similarity.string.FreqencyL2CosineSimilarity.class);
        reg.add(com.github.benhaixiao.text.similarity.string.FreqencyL1CosineSimilarity.class);
        reg.add(com.github.benhaixiao.text.similarity.string.BinaryL2CosineSimilarity.class);
        register(reg);
    }

    /**
     * 将所有运算符注册
     *
     * @param reg
     */
    private void register(Set<Class<? extends SimilarityMeasure>> reg) {
        for (Class<? extends SimilarityMeasure> clz : reg) {
            try {
                SimilarityMeasure similarityMeasure = clz.newInstance();
                similarityMeasureMap.put(similarityMeasure.getSymbol(), similarityMeasure);
            } catch (Exception e) {
                LOGGER.error("SimilarityMeasure init registry error,class name = {},emsg:{},e:{}", clz.getName(), e.getMessage(),e);
            }
        }
    }

    /**
     * 选择适当的运算符
     *
     * @param symbol 运算符的符号表示
     * @return 运算符
     */
    public SimilarityMeasure select(String symbol) {
        SimilarityMeasure similarityMeasure = similarityMeasureMap.get(symbol);
        if (similarityMeasure != null) {
            return similarityMeasure;
        }
        return null;
    }
}
