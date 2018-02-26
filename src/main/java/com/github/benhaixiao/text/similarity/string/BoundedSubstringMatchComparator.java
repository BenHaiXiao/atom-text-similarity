package com.github.benhaixiao.text.similarity.string;

import com.github.benhaixiao.text.similarity.SimilarityException;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 15:29
 */
public class BoundedSubstringMatchComparator extends SimpleStringMatchComparator_ImplBase {
    public BoundedSubstringMatchComparator() throws SimilarityException
    {
        this.strategy = SimpleStringMatchStrategy.BoundedSubstringMatch;
    }
    @Override
    public String getSymbol() {
        return "bounded_substring_match";
    }
}
