package com.kunyan.text

import com.hankcs.hanlp.summary.TextRankSentence

import scala.util.control.Breaks

/**
  * Created by C.J.YOU on 2016/11/29.
  */
object Summary {

  /**
    * 获取摘要 长度为 maxLength
    * @param doc
    * @param maxLength
    * @return
    */
  def summary(doc: String, maxLength:Int) = {
    TextRankSentence.getSummary(doc, maxLength)
  }


  /**
    * 获取最短摘要
    * @param doc
    */
  def getBestSummary(doc:String) = {

    val break = new Breaks
    break.breakable {
      for(i <-  6 to 25) {
        val summary = Summary.summary(doc, i)
        if(summary.nonEmpty) {
          println(summary)
          break.break()
        }
      }
    }
  }

}
