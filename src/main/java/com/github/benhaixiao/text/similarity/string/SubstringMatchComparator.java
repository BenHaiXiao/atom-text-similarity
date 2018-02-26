package com.github.benhaixiao.text.similarity.string;

import com.github.benhaixiao.text.similarity.SimilarityException;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 15:41
 */
public class SubstringMatchComparator	extends SimpleStringMatchComparator_ImplBase
{
    public SubstringMatchComparator()
            throws SimilarityException
    {
        this.strategy = SimpleStringMatchStrategy.SubstringMatch;
    }
    @Override
    public String getSymbol() {
        return "substring_match";
    }
}
