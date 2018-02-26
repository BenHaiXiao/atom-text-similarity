package com.github.benhaixiao.text.similarity.string;

import com.github.benhaixiao.text.similarity.SimilarityException;
import com.github.benhaixiao.text.similarity.SimilarityMeasureBase;
import com.github.benhaixiao.text.similarity.util.FrequencyDistribution;
import com.github.benhaixiao.text.similarity.vsm.InnerVectorProduct;
import com.github.benhaixiao.text.similarity.vsm.VectorNorm;

import no.uib.cipr.matrix.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 15:15
 */
public class CosineSimilarity extends SimilarityMeasureBase {
    public enum WeightingModeTf
    {
        BINARY, FREQUENCY, FREQUENCY_LOG, FREQUENCY_LOGPLUSONE
    }

    public enum WeightingModeIdf
    {
        LOG, PASSTHROUGH, LOGPLUSONE
    }

    public enum NormalizationMode
    {
        L1, L2
    }

    // TZ 2014-08-19
    // removed the IDF only weighting mode, as it always needs to be used together with TF
    // otherwise both vectors will be equal and similarity will always be 1 no matter what weighting is used
    private enum WeightingMode
    {
        TF, TFIDF
    }

    private WeightingMode weightingMode;
    private WeightingModeTf weightingModeTf;
    private WeightingModeIdf weightingModeIdf;
    private NormalizationMode normalizationMode;
    private Map<String, Double> idfScores;

    public CosineSimilarity()
    {
        initialize(WeightingModeTf.FREQUENCY, null, NormalizationMode.L2, null);
    }

    public CosineSimilarity(WeightingModeTf modeTf, NormalizationMode normMode)
    {
        initialize(modeTf, null, normMode, null);
    }

    /**
     * @param modeTf What kind of TF weighting should be used
     * @param modeIdf What kind of IDF weighting should be used
     * @param normMode What kind of normalization should be used
     * @param idfScores A map of strings to IDF scores
     */
    public CosineSimilarity(WeightingModeTf modeTf, WeightingModeIdf modeIdf,
                            NormalizationMode normMode, Map<String, Double> idfScores)
    {
        initialize(modeTf, modeIdf, normMode, idfScores);
    }

    public CosineSimilarity(WeightingModeIdf modeIdf, NormalizationMode normMode,
                            String idfScoresFile)
    {
        HashMap<String, Double> idfValues = null;
        if(idfScoresFile != null)
        {
            idfValues = new HashMap<String,Double>();
            try {
                for (String line : FileUtils.readLines(new File(idfScoresFile)))
                {
                    if (line.length() > 0)
                    {
                        String[] cols = line.split("\t");
                        idfValues.put(cols[0], Double.parseDouble(cols[1]));
                    }
                }
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            idfValues = null;
        }
        initialize(null, modeIdf, normMode, idfValues);
    }

    public CosineSimilarity(WeightingModeTf modeTf, WeightingModeIdf modeIdf,
                            NormalizationMode normMode, String idfScoresFile)
    {
        HashMap<String, Double> idfValues;
        if (idfScoresFile != null) {
            idfValues = new HashMap<String, Double>();
            try {
                for (String line : FileUtils.readLines(new File(idfScoresFile))) {
                    if (line.length() > 0) {
                        String[] cols = line.split("\t");
                        idfValues.put(cols[0], Double.parseDouble(cols[1]));
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            idfValues = null;
        }
        initialize(modeTf, modeIdf, normMode, idfValues);
    }


    private void initialize(WeightingModeTf modeTf, WeightingModeIdf modeIdf,
                            NormalizationMode normMode, Map<String, Double> idfScores)
    {
        if (modeTf == null && modeIdf != null) {
            this.weightingMode = WeightingMode.TFIDF;
        }
        else if (modeTf != null && modeIdf == null) {
            this.weightingMode = WeightingMode.TF;
        }
        else {
            this.weightingMode = WeightingMode.TFIDF;
        }

        this.weightingModeTf = modeTf;
        this.weightingModeIdf = modeIdf;
        this.normalizationMode = normMode;
        this.idfScores = idfScores;
    }

    @Override
    public double getSimilarity(Collection<String> terms1, Collection<String> terms2)
            throws SimilarityException
    {
        // fix for issue #11
        // all equal, return 1.0
        Set<String> unionTermSet = new HashSet<String>(terms1);
        unionTermSet.addAll(terms2);
        if (terms1.size() == terms2.size() && terms1.size() == unionTermSet.size()) {
            return 1.0;
        }

        // Get TF/IDF/TFIDF vectors
        no.uib.cipr.matrix.Vector vector1 = getVector(unionTermSet, terms1);
        no.uib.cipr.matrix.Vector vector2 = getVector(unionTermSet, terms2);

        // Numerator
        double num = InnerVectorProduct.COSINE.apply(vector1, vector2);

        double norm;
        switch (normalizationMode) {
            case L1:
                norm = VectorNorm.L1.apply(vector1, vector2);
                break;
            case L2:
                norm = VectorNorm.L2.apply(vector1, vector2);
                break;
            default:
                throw new IllegalStateException("Unsupported norm: "+normalizationMode);
        }

        if (norm == 0.0) {
            return 0.0;
        }

        // Normalized score
        return num / norm;
    }

    /*
     * Up to 2.0.0, this used to treat the strings as representations of whole sentences that were silently tokenized.
     * I think that this is counter-intuitive and intransparent.
     * From 2.1.0 onwards, this will instead do a character-based cosine similarity on the strings.
     */
    @Override
    public double getSimilarity(String string1, String string2) throws SimilarityException
    {
        List<String> parts1 = new ArrayList<String>();
        List<String> parts2 = new ArrayList<String>();

        for (char c : string1.toCharArray()) {
            parts1.add(Character.toString(c));
        }

        for (char c : string2.toCharArray()) {
            parts2.add(Character.toString(c));
        }

        return getSimilarity(parts1, parts2);
    }

    private no.uib.cipr.matrix.Vector getVector(Collection<String> allTerms, Collection<String> docTerms)
    {
        no.uib.cipr.matrix.Vector vector = new DenseVector(allTerms.size());

        // TF
        if (weightingMode == WeightingMode.TF || weightingMode == WeightingMode.TFIDF) {
            FrequencyDistribution<String> fd = new FrequencyDistribution<String>();
            fd.incAll(docTerms);

            int i = 0;
            for (String term : allTerms) {
                double score = fd.getCount(term);

                if (weightingModeTf == WeightingModeTf.BINARY) {
                    if (score >= 1) {
                        score = 1.0;
                    }
                }
                else if (weightingModeTf == WeightingModeTf.FREQUENCY_LOG) {
                    if (score > 0.0) {
                        score = Math.log(score);
                    }
                }
                else if (weightingModeTf == WeightingModeTf.FREQUENCY_LOGPLUSONE) {
                    if (score > 0.0) {
                        score = Math.log(score) + 1;
                    }
                }
                else if (weightingModeTf == WeightingModeTf.FREQUENCY) {
                    // do nothing, we already have the frequency as the score
                }

                else {
                    throw new IllegalArgumentException("Unhandled weighting parameter: " + weightingModeTf);
                }

                vector.set(i, score);
                i++;
            }
        }

        // IDF
        if (weightingMode == WeightingMode.TFIDF) {
            int i = 0;

            // get the smallest IDF value in the map - it will be used for weighting unseen tokens
            double minScore = 1.0;
            for (String term : allTerms) {
                if (idfScores.containsKey(term) && idfScores.get(term) < minScore) {
                    minScore = idfScores.get(term);
                }
            }

            for (String term : allTerms) {
                double score = 0.0;

                if (idfScores.containsKey(term)) {
                    score = idfScores.get(term);
                }
                else {
                    // we do not want to have a 0.0 zero value, as this will lead to NaN with log-weighting
                    // so we use the smallest recorded IDF value
                    score = minScore;
                }

                // it is a bit unclear what binary IDF should actually be
                if (weightingModeIdf == WeightingModeIdf.LOG) {
                    score = Math.log(score);
                }
                else if (weightingModeIdf == WeightingModeIdf.LOGPLUSONE) {
                    score = Math.log(score) + 1;
                }
                else if (weightingModeIdf == WeightingModeIdf.PASSTHROUGH) {
                    // do nothing, we already have the idf as the score
                }
                else {
                    throw new IllegalArgumentException("Unhandled weighting parameter: " + weightingModeIdf);
                }

                vector.set(i, vector.get(i) * score);
                i++;
            }
        }

        return vector;
    }

    @Override
    public String getSymbol() {
        return "cosine";
    }
}
