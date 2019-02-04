/**
 * This file is part of the CrossEngage backend.
 *
 * @copyright 2019 © by CrossEngage GmbH.
 */

package com.agileengine;

import org.jsoup.nodes.Element;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;

@Slf4j
public class App {

    public static void main(String... args) {
        if (args.length != 2) {
            log.error("Illegal arguments passed\n" +
                    "Should be: <origin_path> <sample path>");
            return;
        }

        String originPath = args[0];
        String samplePath = args[1];
        if (originPath == null || samplePath == null) {
            log.error("You have to provide both the origin and the sample files as arguments");
            return;
        }

        SimilaritySeeker similaritySeeker = new SimilaritySeeker();
        DOMService domService = new DOMService(similaritySeeker);

        doTheMagic(originPath, samplePath, domService);
    }

    private static void doTheMagic(String originPath, String samplePath, DOMService domService) {
        try {
            printElement(domService.findTheMostSimilarOkButton(originPath, samplePath));
        } catch (Exception exc) {
            log.error("It's an unlucky day to be searching for similarities. So sorry for dat", exc);
        }
    }

    private static void printElement(Element element) {
        Iterator<Element> reversedParents = new LinkedList<>(element.parents()).descendingIterator();
        log.info(stream(spliteratorUnknownSize(reversedParents, 0), false)
                .map(Element::tagName)
                .collect(Collectors.joining(" > ")) + " > " + element.tagName());
        log.info(element.toString());
    }

}
