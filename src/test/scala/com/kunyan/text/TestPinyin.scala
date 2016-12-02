package com.kunyan.text

import com.hankcs.hanlp.HanLP
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by C.J.YOU on 2016/12/2.
  */
class TestPinyin extends FlatSpec with Matchers  {

  "TestPinyin" should "pass" in {

    val text = "我是谁不重要，重要的是我在上班！"

    val jList = HanLP.convertToPinyinList(text)
    println(jList)


  }

}
