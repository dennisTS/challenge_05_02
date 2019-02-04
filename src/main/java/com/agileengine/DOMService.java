/**
 * This file is part of the CrossEngage backend.
 *
 * @copyright 2019 © by CrossEngage GmbH.
 */

package com.agileengine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class DOMService {

    private static final String THE_ULTIMATE_QUERY = "#make-everything-ok-button";

    private SimilaritySeeker similaritySeeker;

    public DOMService(SimilaritySeeker similaritySeeker) {
        this.similaritySeeker = similaritySeeker;
    }

    public Element findTheMostSimilarOkButton(String originPath, String samplePath) {
        try {
            Document sample = Jsoup.parse(new File(samplePath), UTF_8.name());
            return Jsoup.parse(new File(originPath), UTF_8.name())
                    .select(THE_ULTIMATE_QUERY)
                    .stream()
                    .findFirst()
                    .flatMap(target -> similaritySeeker.findTheMostSimilar(target, sample))
                    .orElseThrow(() -> new RuntimeException("Could not find the similarity"));
        } catch (IOException exc) {
            log.error("Could not parse the file: {}", originPath, exc);
            throw new RuntimeException(exc);
        }
    }
}
