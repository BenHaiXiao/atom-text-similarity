package com.github.benhaixiao.text.similarity.string;

import com.wcohen.ss.Level2Levenstein;
import com.wcohen.ss.Levenstein;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 15:38
 */
public class LevenshteinSecondStringComparator     extends SecondStringComparator_ImplBase
{
    public LevenshteinSecondStringComparator()
    {
        secondStringMeasureL1 = new Levenstein();
        secondStringMeasureL2 = new Level2Levenstein();
    }

    @Override
    public String getSymbol() {
        return "Levenshtein_second_string";
    }
}