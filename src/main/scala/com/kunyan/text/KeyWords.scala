package com.kunyan.text

import com.hankcs.hanlp.HanLP
import com.hankcs.hanlp.summary.TextRankKeyword

/**
  * Created by C.J.YOU on 2016/11/29.
  */
object KeyWords {

  /**
    *
    * @param sentence
    * @param size 希望提取几个关键词
    * @return
    */
  def getKeyWords(sentence: String, size: Int) = {

    val keyWords = HanLP.extractKeyword(sentence, size)

    keyWords

  }

  /**
    * 提取所有关键词
    * @param document
    * @return
    */
  def getKeyWords(document:String) = {
    new TextRankKeyword().getKeyword(document)
  }

  /**
    * 提取关键词 包括 rank值
    * @param document
    * @return
    */
  def getTermAndRank(document: String) = {
    new TextRankKeyword().getTermAndRank(document)
  }


}
