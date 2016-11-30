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
      val topKey = util.topKeyWords(filterKeyWords, 5)
      val top10Sentencs = Sentences.getTopSentenceList(text, 5)
      val summary = Summary.getBestSummary(text, topKey)
      val res = (id + 1 + " = " + summary + "\n").toString
      // pw.write(res)
      // pw.flush()
      println(res)

      val describe = util.getBestSentence(top10Sentencs, topKey)
      println(describe)
    }
    pw.close()




  }

}
