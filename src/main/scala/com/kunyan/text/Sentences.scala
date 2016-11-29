package com.kunyan.text

import com.hankcs.hanlp.HanLP

/**
  * Created by C.J.YOU on 2016/11/29.
  */
object Sentences {

  def getSentences(document: String, windowSize: Int) = {

    val sentenceList = HanLP.extractSummary(document, windowSize)

    sentenceList

  }

}
