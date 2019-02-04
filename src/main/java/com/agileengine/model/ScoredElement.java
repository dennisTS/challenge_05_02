/**
 * This file is part of the CrossEngage backend.
 *
 * @copyright 2019 © by CrossEngage GmbH.
 */

package com.agileengine.model;

import org.jsoup.nodes.Element;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScoredElement {
    private static final int DEFAULT_MIN_SCORE = 2;

    private int score;
    private Element element;

    public boolean isInteresting() {
        return score > DEFAULT_MIN_SCORE;
    }
}
