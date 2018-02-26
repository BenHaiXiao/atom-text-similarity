package com.github.benhaixiao.text.similarity.string;

import com.wcohen.ss.Jaro;
import com.wcohen.ss.Level2Jaro;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 15:34
 */
public class JaroSecondStringComparator extends SecondStringComparator_ImplBase
{
    public JaroSecondStringComparator()
    {
        secondStringMeasureL1 = new Jaro();
        secondStringMeasureL2 = new Level2Jaro();
    }
    @Override
    public String getSymbol() {
        return "jaro_second_string";
    }
}
