package com.github.benhaixiao.text.similarity.string;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/16
 * Time: 19:15
 */
public class FreqencyL1CosineSimilarity  extends CosineSimilarity{
    public FreqencyL1CosineSimilarity() {
        super(WeightingModeTf.FREQUENCY,NormalizationMode.L1);
    }
    @Override
    public String getSymbol() {
        return "frequency_l1_cosine";
    }
}
