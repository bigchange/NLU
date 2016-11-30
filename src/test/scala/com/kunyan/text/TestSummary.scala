package com.kunyan.text

import java.io.{FileOutputStream, PrintWriter}

import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

/**
  * Created by C.J.YOU on 2016/11/29.
  */
class TestSummary  extends FlatSpec with Matchers  {

  "summary" should "pass" in {


    val pw = new PrintWriter(new FileOutputStream("events-HAN.txt", false),true)

    val text = Source.fromFile("src/test/resources/events.txt").getLines().toList.distinct.zipWithIndex.foreach { case (text,id) =>

      val keyWords = KeyWords.getTermAndRank(text)
      val filterKeyWords = util.wordFilter(keyWords)
      val topKey = util.topKeyWords(filterKeyWords, 3)
      val top10Sentencs = Sentences.getTopSentenceList(text, 10)
      val filteredSentecnces = util.sentenceFilter(top10Sentencs, topKey)
      println(filteredSentecnces)
      val summary = Summary.getBestSummary(text, topKey)
      val res = (id + 1 + " = " + summary).toString
      // pw.write(res)
      // pw.flush()
      println(res)

      val describe = util.getBestSentence(filteredSentecnces, topKey, summary)
      println(describe + "\n")
    }
    pw.close()




  }

}
