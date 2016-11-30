package com.kunyan.text

import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

/**
  * Created by C.J.YOU on 2016/11/29.
  */
class TestSummary  extends FlatSpec with Matchers  {

  "summary" should "pass" in {
    
    val text = Source.fromFile("D:/out/events-test.txt").getLines().toList.mkString
    // println(text)

    val keyWords = KeyWords.getTermAndRank(text)
    val filterKeyWords = util.wordFilter(keyWords)
    // println("keyWords:" + filterKeyWords)
    val phrase = Phrase.getPhrase(text, 10)
    println("topPhrase:" + phrase.mkString("\n"))
    val top10Sentencs = Sentences.getTopSentenceList(text, 10)
    println("top10Sentencs:" + top10Sentencs.mkString("\n"))
    val sentences = util.getBestSentence(Sentences.getTopSentenceList(text, 10), filterKeyWords)
    println("getBestSentence:" + sentences)
    val summary = Summary.getBestSummary(text)
    println(summary)

  }

}
