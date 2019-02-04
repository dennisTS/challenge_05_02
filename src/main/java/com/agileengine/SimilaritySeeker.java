/**
 * This file is part of the CrossEngage backend.
 *
 * @copyright 2019 © by CrossEngage GmbH.
 */

package com.agileengine;

import com.agileengine.model.ElementScoring;
import com.agileengine.model.ScoredElement;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class SimilaritySeeker {

    public Optional<Element> findTheMostSimilar(Element target, Document sample) {
        ElementScoring scoring = new ElementScoring(target);
        return getScoredElements(sample, scoring)
                .max(comparing(ScoredElement::getScore))
                .map(ScoredElement::getElement);
    }

    private Stream<ScoredElement> getScoredElements(Document sample, ElementScoring scoring) {
        return sample.getAllElements()
                .stream()
                .map(scoring::similarity)
                .filter(ScoredElement::isInteresting);
    }
}
