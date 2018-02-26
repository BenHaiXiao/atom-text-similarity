package com.github.benhaixiao.text.similarity.string;

import com.github.benhaixiao.text.similarity.SimilarityException;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 15:29
 */
public class ExactStringMatchComparator extends SimpleStringMatchComparator_ImplBase
{

    public ExactStringMatchComparator()
            throws SimilarityException
    {
        this.strategy = SimpleStringMatchStrategy.ExactStringMatch;
    }
    @Override
    public String getSymbol() {
        return "exact_string_match";
    }
}
