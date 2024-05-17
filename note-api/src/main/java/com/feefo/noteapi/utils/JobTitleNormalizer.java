package com.feefo.noteapi.utils;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class JobTitleNormalizer {
    private List<String> normalizedTitles;
    private LevenshteinDistance levenshteinDistance;
    private Analyzer analyzer;

    public JobTitleNormalizer(List<String> normalizedTitles) {
        this.normalizedTitles = normalizedTitles;
        this.levenshteinDistance = new LevenshteinDistance();
        this.analyzer = new SimpleAnalyzer();
    }

    private String preprocess(String text) throws IOException {
        List<String> result = new ArrayList<>();
        try (StringReader reader = new StringReader(text)) {
            var tokenStream = analyzer.tokenStream(null, reader);
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                CharTermAttribute attr = tokenStream.getAttribute(CharTermAttribute.class);
                result.add(attr.toString());
            }
            tokenStream.end();
            tokenStream.close();
        }
        return String.join(" ", result);
    }

    public String normalize(String inputTitle) throws IOException {
        String preprocessedInput = preprocess(inputTitle);

        String bestMatch = null;
        int bestScore = Integer.MAX_VALUE;

        for (String normalizedTitle : normalizedTitles) {
            int score = levenshteinDistance.apply(preprocessedInput, normalizedTitle);
            if (score < bestScore) {
                bestScore = score;
                bestMatch = normalizedTitle;
            }
        }

        return bestMatch;
    }

    public static void main(String[] args) throws IOException {
        List<String> normalizedTitles = List.of("Architect", "Software engineer", "Quantity surveyor", "Accountant");
        JobTitleNormalizer normalizer = new JobTitleNormalizer(normalizedTitles);

        List<String> inputs = List.of("Java engineer", "C# engineer", "Accountant", "Chief Accountant");
        for (String jobTitle : inputs) {
            String normalizedTitle = normalizer.normalize(jobTitle);
            System.out.println("Input: " + jobTitle + " -> Normalized: " + normalizedTitle);
        }
    }
}
