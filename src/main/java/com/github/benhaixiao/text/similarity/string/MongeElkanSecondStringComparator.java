package com.github.benhaixiao.text.similarity.string;

import com.wcohen.ss.Level2MongeElkan;
import com.wcohen.ss.MongeElkan;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 15:40
 */
public class MongeElkanSecondStringComparator   extends SecondStringComparator_ImplBase
{
    public MongeElkanSecondStringComparator()
    {
        secondStringMeasureL1 = new MongeElkan();
        secondStringMeasureL2 = new Level2MongeElkan();
    }
    @Override
    public String getSymbol() {
        return "monge_elkan_second_string";
    }
}