package com.github.benhaixiao.text.similarity;

import org.apache.commons.lang3.StringUtils;

import static java.util.Arrays.asList;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 14:41
 */
public abstract class SimilarityMeasureBase   implements SimilarityMeasure {

    @Override
    public double getSimilarity(final String aTerm1, final String aTerm2) throws SimilarityException
    {
        return getSimilarity(asList(aTerm1), asList(aTerm2));
    }

    @Override
    public double getSimilarity(final String[] strings1, final String[] strings2) throws SimilarityException
    {
        return getSimilarity(asList(strings1), asList(strings2));
    }

    @Override
    public String getName()
    {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean isMe(String symbol) {
        return StringUtils.equalsIgnoreCase(symbol, getSymbol());
    }

}
