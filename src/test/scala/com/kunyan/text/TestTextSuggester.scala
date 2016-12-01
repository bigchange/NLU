package com.kunyan.text

import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

/**
  * Created by C.J.YOU on 2016/12/1.
  */
class TestTextSuggester extends FlatSpec with Matchers  {

  "TestTextSuggester" should "pass" in {

    val text = Source.fromFile("src/test/resources/events.txt").getLines().toList.distinct.zipWithIndex.foreach { case (text, id) =>

        val summary = Summary.summary(text)
        println(summary._1)

        val keyWords = KeyWords.getTermAndRank(text)
        val filterKeyWords = util.wordFilter(keyWords)
        val topKey = util.topKeyWords(filterKeyWords, 5)
        val topSentences = Sentences.getTopSentenceList(text, 5)
        val phrase = util.phraseFilter(Phrase.getPhrase(text,10), topKey)
        println("phrase:" + phrase)
        val suggester = TextSuggester.getSuggester
        for (sen <- topSentences)
          suggester.addSentence(sen)
        println(suggester.suggest(phrase(0), 1) + "\n")
        suggester.removeAllSentences()
    }
  }

}
