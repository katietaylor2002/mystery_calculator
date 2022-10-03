Mystery Calculator

I decided to tackle the problem behind the infamous mystery calculator - most frequently seen in Christmas crackers. I used my problem solving and pattern recognition skills to realise that the way that the answer is not a "mystery" but in fact simply involves adding the first number from each of the cards that your number appears. In order to code this, however, I needed to understand why this works not just how. It relates back to binary which correlates the number of cards to the maximum number of cards (a max number of 64 will have 6 cards because 2^6 = 64).

Knowing this I then used Java to program it as I realised the problem is well suited to an Object-Oriented approach. I created 3 classes: Card, Game and Menu. Where each game was composed of multiple cards. For the UI and the menu class I used Java Swing as it was something we had touched on briefly in lectures however I had not had the chance to use it as we did not get set a coursework with it.
