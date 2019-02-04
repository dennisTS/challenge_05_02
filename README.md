# challenge_05_02
Running:
```
cd challenge_05_02
java -jar challenge.jar samples/sample-0-origin.html samples/sample-1-evil-gemini.html
```

Points I could improve if spent less time googling for jsoup usecases and trying to find my git creds:
1. Tests. I love TDD and usually try to go baby-steps with those. Unfortunatelly, I spent too much time playing around with jsoup initially, and had to make a difficult decision of going berserk and having no tests
2. Scoring and algorithm. The approach taken is naïve and brute-forsish in a way that it goes through all the elements and assigns a linear score based on some of the element properties (like tag and id). It would make sense to:
 - filter out some of the elements at once
 - have a progressive scoring (e.g., text might be more important than classes)
 - have better predicates (e.g., for text1 being similar to text2 (and not equal), for classes to have some intersection (instead of complete equality)) or even use some text similarity analisys library
3. Functionality. Most of the stuff is hardcoded (scoring grade, minimum score for an element to be interesting, selector etc.), but it would be quite easy to make that configurable. The output could also be improved to actually show the path
4. Code quality and readability. Nuff said. One of the things I'd improve first would be the scoring grade (e.g., it could be extracted to an enum with scoring values and a method to compute the score)
