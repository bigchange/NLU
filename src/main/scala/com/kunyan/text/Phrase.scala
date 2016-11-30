package com.kunyan.text

import com.hankcs.hanlp.phrase.MutualInformationEntropyPhraseExtractor

import scala.collection.mutable.ListBuffer

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

    val listBuffer = new ListBuffer[String]
    val list = MutualInformationEntropyPhraseExtractor.extract(text, size)
    val iterator = list.iterator()
    while (iterator.hasNext) {
      listBuffer.+=(iterator.next())
    }
    listBuffer
  }

}
