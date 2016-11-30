package com.kunyan.text

import com.hankcs.hanlp.summary.TextRankSentence

import scala.collection.mutable.ListBuffer

/**
  * Created by C.J.YOU on 2016/11/29.
  */
object Sentences {

  /**
    * 获取top size 的关键句
    * @param document
    * @param size
    * @return
    */
  def getTopSentenceList(document: String, size: Int) = {

    val list = new ListBuffer[String]
    val topList = TextRankSentence.getTopSentenceList(document, size)
    val iterator = topList.iterator()
    while (iterator.hasNext) {
      list.+=(iterator.next())
    }
    list
  }

}
