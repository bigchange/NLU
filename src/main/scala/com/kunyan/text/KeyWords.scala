package com.kunyan.text

import com.hankcs.hanlp.HanLP

/**
  * Created by C.J.YOU on 2016/11/29.
  */
object KeyWords {

  def getKeyWords(sentence: String, windowSize: Int) = {

    val keyWords = HanLP.extractKeyword(sentence, windowSize)

    keyWords

  }

}
