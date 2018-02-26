package com.github.benhaixiao.text.similarity;

import java.util.Collection;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 14:38
 */
public interface SimilarityMeasure {
    /**
     * 计算相似度
     * @param strings1
     * @param strings2
     * @return
     * @throws SimilarityException
     */
    double getSimilarity(Collection<String> strings1, Collection<String> strings2) throws SimilarityException;

    /**
     * 计算相似度
     * @param strings1
     * @param strings2
     * @return
     * @throws SimilarityException
     */
    double getSimilarity(String[] strings1, String[] strings2) throws SimilarityException;

    /**
     * 计算相似度
     * @param string1
     * @param string2
     * @return
     * @throws SimilarityException
     */
    double getSimilarity(String string1, String string2)throws SimilarityException;
    /**
     * 相似度算法名称
     * @return 相似度算法名称
     */
    String getName();

    /**
     * 算法符号
     * @return
     */
    String getSymbol() ;

    boolean isMe(String symbol) ;
}
