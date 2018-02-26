package com.github.benhaixiao.text.similarity.ngrams;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/16
 * Time: 19:05
 */
public class Word3GramJaccardMeasure  extends WordNGramJaccardMeasure{
    public Word3GramJaccardMeasure() {
        super(3);
    }
    @Override
    public String getSymbol() {
        return "word_3gram_jaccard";
    }
}
