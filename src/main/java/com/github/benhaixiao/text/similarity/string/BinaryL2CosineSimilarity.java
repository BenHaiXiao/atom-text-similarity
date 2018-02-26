package com.github.benhaixiao.text.similarity.string;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/16
 * Time: 19:21
 */
public class BinaryL2CosineSimilarity extends CosineSimilarity{
    public BinaryL2CosineSimilarity() {
        super(WeightingModeTf.BINARY,NormalizationMode.L2);
    }
    @Override
    public String getSymbol() {
        return "binary_l2_cosine";
    }
}
