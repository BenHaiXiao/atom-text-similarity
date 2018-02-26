package com.github.benhaixiao.text.similarity.string;

import com.wcohen.ss.JaroWinkler;
import com.wcohen.ss.Level2JaroWinkler;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 15:36
 */
public class JaroWinklerSecondStringComparator     extends SecondStringComparator_ImplBase
{
    public JaroWinklerSecondStringComparator()
    {
        secondStringMeasureL1 = new JaroWinkler();
        secondStringMeasureL2 = new Level2JaroWinkler();
    }
    @Override
    public String getSymbol() {
        return "jaro_winkler_second_string";
    }
}
