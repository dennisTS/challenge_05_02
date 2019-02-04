/**
 * This file is part of the CrossEngage backend.
 *
 * @copyright 2019 © by CrossEngage GmbH.
 */

package com.agileengine.model;

import org.jsoup.nodes.Element;

import java.util.Set;

public class ElementScoring {
    private String text;
    private String tag;
    private String id;
    private String title;
    private String href;
    private Set<String> classes;

    public ElementScoring(Element element) {
        text = element.text();
        tag = element.tagName();
        id = element.id();
        title = element.attr("title");
        href = element.attr("href");
        classes = element.classNames();
    }

    public ScoredElement similarity(Element element) {
        return new ScoredElement(
                (element.text().equals(text) ? 1 : 0) +
                (element.tagName().equals(tag) ? 1 : 0) +
                (element.id().equals(id) ? 1 : 0) +
                (element.attr("title").equals(title) ? 1 : 0) +
                (element.attr("href").equals(href) ? 1 : 0) +
                (element.classNames().containsAll(classes) ? 1 : 0), element);
    }
}
