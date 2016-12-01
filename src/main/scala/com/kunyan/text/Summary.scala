package com.kunyan.text

import java.util

import com.hankcs.hanlp.summary.TextRankSentence

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks

/**
  * Created by C.J.YOU on 2016/11/29.
  */
object Summary {


  def getSummary(doc: String, maxLength:Int) = {

    val summary =TextRankSentence.getSummary(doc, maxLength)

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
