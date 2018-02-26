package com.github.benhaixiao.text.similarity.ngrams;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/16
 * Time: 19:07
 */
public class Word3GramContainmentMeasure extends WordNGramContainmentMeasure{
    public Word3GramContainmentMeasure() {
        super(3);
    }
    @Override
    public String getSymbol() {
        return "word_3gram_containment";
    }
}
