package com.github.benhaixiao.text.similarity.demo;

import com.github.benhaixiao.text.similarity.SimilarityMeasureSelector;

/**
 * Created by xiaobenhai
 * Date: 2018/2/26 0026
 * Time: 下午 23:21
 */
public class SimilarityDemo {

    public static void  main(String[] args){
        SimilarityMeasureSelector.getInstance().select("character_ngram");
    }
}
