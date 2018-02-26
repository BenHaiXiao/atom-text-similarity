package com.github.benhaixiao.text.similarity.string;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/16
 * Time: 19:19
 */
public class FreqencyL2CosineSimilarity extends CosineSimilarity {
    public FreqencyL2CosineSimilarity() {
        super(CosineSimilarity.WeightingModeTf.FREQUENCY, CosineSimilarity.NormalizationMode.L2);
    }
    @Override
    public String getSymbol() {
        return "frequency_l2_cosine";
    }
}
