package com.kunyan.text

import com.hankcs.hanlp.summary.TextRankSentence

import scala.util.control.Breaks

/**
  * Created by C.J.YOU on 2016/11/29.
  */
object Summary {

  def summary(doc: String)  = {

    val keyWords = KeyWords.getTermAndRank(doc)
    val filterKeyWords = util.wordFilter(keyWords)
    val topKey = util.topKeyWords(filterKeyWords, 3)

    val topTenSentences = Sentences.getTopSentenceList(doc, 10)
    val filteredSentences = util.sentenceFilter(topTenSentences, topKey)

    val summary = getBestSummary(doc, topKey)
    val describe = util.getBestSentence(filteredSentences, topKey, summary)

    (summary, describe)

  }


  private  def getSummary(doc: String, maxLength:Int) = {

    TextRankSentence.getSummary(doc, maxLength)

  }


  /**
    * 获取最短摘要
    * @param doc 文章
    */
  def getBestSummary(doc:String, topKeyWords: List[(String, Float)]) = {
    var res = ""
    val break = new Breaks

    break.breakable {

      for(i <-  6 to 100) {

        val summary = Summary.getSummary(doc, i)
        if(summary.nonEmpty ) {
          res = summary
          break.break()
        }
      }
    }
    res
  }

}
