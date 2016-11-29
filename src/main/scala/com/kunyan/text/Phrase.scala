package com.kunyan.text

import com.hankcs.hanlp.phrase.MutualInformationEntropyPhraseExtractor

/**
  * Created by C.J.YOU on 2016/11/29.
  */
object Phrase {

  /**
    * 获取关键短语个数
    * @param text
    * @param size
    * @return
    */
  def getPhrase(text: String, size: Int) = {

     MutualInformationEntropyPhraseExtractor.extract(text, size)

  }

}
