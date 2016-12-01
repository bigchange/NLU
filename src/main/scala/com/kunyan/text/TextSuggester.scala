package com.kunyan.text

import com.hankcs.hanlp.suggest.Suggester

/**
  * Created by C.J.YOU on 2016/12/1.
  */
object TextSuggester {

  private var suggester: Suggester = null

  def getSuggester = {
    if (suggester != null)
      suggester
    else
      suggester = new Suggester()

    suggester

  }

}
